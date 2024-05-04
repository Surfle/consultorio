package service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.Paciente;
import repository.PacienteRepository;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> getAllPacientes() {
        return pacienteRepository.findAll();
    }

    public Paciente getPacienteById(Long id) {
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);
        return optionalPaciente.orElse(null);
    }

    public Paciente addPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public Paciente updatePaciente(Long id, Paciente paciente) {
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);
        if (optionalPaciente.isPresent()) {
            paciente.setId(id);
            return pacienteRepository.save(paciente);
        } else {
            return null;
        }
    }

    public void deletePaciente(Long id) {
        pacienteRepository.deleteById(id);
    }
}
