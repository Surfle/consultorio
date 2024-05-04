package service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.Consulta;
import repository.ConsultaRepository;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    public List<Consulta> getAllConsultas() {
        return consultaRepository.findAll();
    }

    public Consulta getConsultaById(Long id) {
        Optional<Consulta> optionalConsulta = consultaRepository.findById(id);
        return optionalConsulta.orElse(null);
    }

    public Consulta addConsulta(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    public Consulta updateConsulta(Long id, Consulta consulta) {
        Optional<Consulta> optionalConsulta = consultaRepository.findById(id);
        if (optionalConsulta.isPresent()) {
            consulta.setId(id);
            return consultaRepository.save(consulta);
        } else {
            return null;
        }
    }

    public void deleteConsulta(Long id) {
        consultaRepository.deleteById(id);
    }
}
