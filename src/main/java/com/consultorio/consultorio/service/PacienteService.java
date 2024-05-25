package com.consultorio.consultorio.service;

import com.consultorio.consultorio.entity.Audit;
import com.consultorio.consultorio.entity.Paciente;
import com.consultorio.consultorio.entity.User;
import com.consultorio.consultorio.repository.AuditRepository;
import com.consultorio.consultorio.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
	
	@Autowired
    private final PacienteRepository pacienteRepository;

    @Autowired
    private AuditRepository auditRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public List<Paciente> getAllPacientes() {
        return pacienteRepository.findAll();
    }

    public Paciente getPacienteById(Long id) {
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);
        return optionalPaciente.orElse(null);
    }

    public Paciente addPaciente(Paciente paciente) {
    	logPaciente("INSERT", paciente);
        return pacienteRepository.save(paciente);
    }

    public Paciente updatePaciente(Long id, Paciente paciente) {
        paciente.setId(id);
    	logPaciente("UPDATE", paciente);
        return pacienteRepository.save(paciente);
    }

    public void deletePaciente(Long id) {
    	Paciente entity = new Paciente();
    	entity.setId(id);
    	logPaciente("DELETE", entity);
        pacienteRepository.deleteById(id);
    }
    
    
    
    
    private void logPaciente(String operation, Paciente entity) {
        Long userId = getCurrentUserId();
            
        
        Audit audit = new Audit();
        audit.setEntityName("PACIENTE");
        audit.setEntityId(getEntityId(entity));
        audit.setOperation(operation);
        audit.setUserId(userId);
        audit.setTimestamp(new Timestamp(System.currentTimeMillis()));
        auditRepository.save(audit);
    }

    private Long getCurrentUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((User) principal).getId();
        } else {
            return null;
        }
    }

    private Long getEntityId(Object entity) {

        if (entity instanceof Paciente) {
            return ((Paciente) entity).getId();
        }
        return null;
    }
}
