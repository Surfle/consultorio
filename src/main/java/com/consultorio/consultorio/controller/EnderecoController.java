package com.consultorio.consultorio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consultorio.consultorio.entity.Endereco;
import com.consultorio.consultorio.service.EnderecoService;



@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<List<Endereco>> getAllEnderecos() {
        List<Endereco> enderecos = enderecoService.getAllEnderecos();
        return new ResponseEntity<>(enderecos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> getEnderecoById(@PathVariable("id") Long id) {
        Endereco endereco = enderecoService.getEnderecoById(id);
        if (endereco != null) {
            return new ResponseEntity<>(endereco, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Endereco> addEndereco(@RequestBody Endereco endereco) {
        Endereco newEndereco = enderecoService.addEndereco(endereco);
        return new ResponseEntity<>(newEndereco, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> updateEndereco(@PathVariable("id") Long id, @RequestBody Endereco endereco) {
        Endereco updatedEndereco = enderecoService.updateEndereco(id, endereco);
        if (updatedEndereco != null) {
            return new ResponseEntity<>(updatedEndereco, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEndereco(@PathVariable("id") Long id) {
        enderecoService.deleteEndereco(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
