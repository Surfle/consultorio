package com.consultorio.consultorio.controller;

import com.consultorio.consultorio.entity.Consulta;
import com.consultorio.consultorio.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;

    @Autowired
    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @GetMapping
    public List<Consulta> getAllConsultas() {
        return consultaService.getAllConsultas();
    }

    @GetMapping("/{id}")
    public Consulta getConsultaById(@PathVariable Long id) {
        return consultaService.getConsultaById(id);
    }

    @PostMapping
    public Consulta addConsulta(@RequestBody Consulta consulta) {
        return consultaService.addConsulta(consulta);
    }

    @PutMapping("/{id}")
    public Consulta updateConsulta(@PathVariable Long id, @RequestBody Consulta consulta) {
        return consultaService.updateConsulta(id, consulta);
    }

    @DeleteMapping("/{id}")
    public void deleteConsulta(@PathVariable Long id) {
        consultaService.deleteConsulta(id);
    }
}

