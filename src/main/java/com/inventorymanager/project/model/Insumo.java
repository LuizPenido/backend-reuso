package com.inventorymanager.project.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
public class Insumo {
    private Long id;

    private String fornecedor;
    private String contato;
    private String produto;
    private LocalDate vencimento;
    private String comprador;
    private Integer quantidade;
    private BigDecimal preco;

    public Insumo() {}

    public Insumo(String fornecedor, String contato, String produto, LocalDate vencimento, String comprador, Integer quantidade, BigDecimal preco) {
        this.fornecedor = fornecedor;
        this.contato = contato;
        this.produto = produto;
        this.vencimento = vencimento;
        this.comprador = comprador;
        this.quantidade = quantidade;
        this.preco = preco;
    }

}