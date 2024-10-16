package com.inventorymanager.project.controller;

import com.inventorymanager.project.model.Insumo;
import com.inventorymanager.project.service.InsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/insumo")
public class InsumoController {

    @Autowired
    private InsumoService insumoService;

    @PostMapping("/register")
    public void registerRemedio(@RequestBody Insumo insumo) {
        insumoService.saveInsumo(insumo);
    }

    @GetMapping("/all")
    public List<Insumo> getAllRemedios() {
        return insumoService.getAllRemedios();
    }

    @GetMapping("/{id}")
    public Insumo getRemedioById(@PathVariable Long id) {
        return insumoService.getRemedioById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInsumoById(@PathVariable Long id) {
        try {
            insumoService.deleteInsumoById(id);
            return new ResponseEntity<>("Insumo excluído com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao excluir o insumo: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateInsumo(@PathVariable Long id, @RequestBody Insumo insumo) {
        try {
            insumoService.updateInsumo(id, insumo);
            return new ResponseEntity<>("Insumo atualizado com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao atualizar insumo: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
