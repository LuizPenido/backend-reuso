package com.inventorymanager.project.service;

import com.inventorymanager.project.model.Insumo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class InsumoService {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUser;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    public List<Insumo> getAllRemedios() {
        List<Insumo> insumos = new ArrayList<>();
        String sql = "SELECT InsumoId as id, Nome as nome, Fornecedor as fornecedor, Qnt as quantidade, Endereco AS endereco, Vencimento as vencimento FROM dbo.Insumos";

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Insumo insumo = new Insumo();
                insumo.setId(rs.getLong("id"));
                insumo.setProduto(rs.getString("nome"));  // Mapeando para o campo produto
                insumo.setFornecedor(rs.getString("fornecedor"));
                insumo.setQuantidade(rs.getInt("quantidade"));
                insumo.setContato(rs.getString("endereco"));  // Mapeando contato para endereço
                insumo.setVencimento(rs.getDate("vencimento").toLocalDate());
                insumos.add(insumo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insumos;
    }


    public Insumo getRemedioById(Long id) {
        Insumo insumo = null;
        String sql = "SELECT id, fornecedor, contato, produto, vencimento, comprador, quantidade, preco FROM remedios WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    insumo = new Insumo();
                    insumo.setId(rs.getLong("id"));
                    insumo.setFornecedor(rs.getString("fornecedor"));
                    insumo.setContato(rs.getString("contato"));
                    insumo.setProduto(rs.getString("produto"));
                    insumo.setVencimento(rs.getDate("vencimento").toLocalDate());
                    insumo.setComprador(rs.getString("comprador"));
                    insumo.setQuantidade(rs.getInt("quantidade"));
                    insumo.setPreco(rs.getBigDecimal("preco"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insumo;
    }

    public void saveRemedio(Insumo insumo) {
        String sql = "INSERT INTO remedios (fornecedor, contato, produto, vencimento, comprador, quantidade, preco) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, insumo.getFornecedor());
            pstmt.setString(2, insumo.getContato());
            pstmt.setString(3, insumo.getProduto());
            pstmt.setDate(4, Date.valueOf(insumo.getVencimento()));
            pstmt.setString(5, insumo.getComprador());
            pstmt.setInt(6, insumo.getQuantidade());
            pstmt.setBigDecimal(7, insumo.getPreco());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteInsumoById(Long id) {
        String sql = "DELETE FROM dbo.Insumos WHERE InsumoId = ?";

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                System.out.println("Nenhum insumo encontrado para o ID fornecido: " + id);
            } else {
                System.out.println("Insumo com ID " + id + " foi excluído com sucesso.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao excluir insumo com ID " + id);
        }
    }

    public void saveInsumo(Insumo insumo) {
        String sql = "INSERT INTO dbo.Insumos (Nome, Fornecedor, Qnt, Endereco, Vencimento) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, insumo.getProduto());
            pstmt.setString(2, insumo.getFornecedor());
            pstmt.setInt(3, insumo.getQuantidade());
            pstmt.setString(4, insumo.getContato());
            pstmt.setDate(5, Date.valueOf(insumo.getVencimento()));

            pstmt.executeUpdate();
            System.out.println("Insumo cadastrado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao cadastrar insumo: " + e.getMessage());
        }
    }

    public void updateInsumo(Long id, Insumo insumo) {
        String sql = "UPDATE dbo.Insumos SET Nome = ?, Fornecedor = ?, Qnt = ?, Endereco = ?, Vencimento = ? WHERE InsumoId = ?";

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, insumo.getProduto());
            pstmt.setString(2, insumo.getFornecedor());
            pstmt.setInt(3, insumo.getQuantidade());
            pstmt.setString(4, insumo.getContato());
            pstmt.setDate(5, Date.valueOf(insumo.getVencimento()));
            pstmt.setLong(6, id);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Nenhum insumo encontrado com o ID fornecido.");
            }
            System.out.println("Insumo atualizado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar insumo: " + e.getMessage());
        }
    }

}
