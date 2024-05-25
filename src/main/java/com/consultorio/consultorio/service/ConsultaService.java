package com.consultorio.consultorio.service;

import com.consultorio.consultorio.entity.Audit;
import com.consultorio.consultorio.entity.Consulta;
import com.consultorio.consultorio.entity.User;
import com.consultorio.consultorio.repository.AuditRepository;
import com.consultorio.consultorio.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {
    @Autowired
    private final ConsultaRepository consultaRepository;

    
    @Autowired
    private AuditRepository auditRepository;

    @Autowired
    public ConsultaService(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    public List<Consulta> getAllConsultas() {
        return consultaRepository.findAll();
    }

    public Consulta getConsultaById(Long id) {
        Optional<Consulta> optionalConsulta = consultaRepository.findById(id);
        return optionalConsulta.orElse(null);
    }

    public Consulta addConsulta(Consulta consulta) {
    	logConsulta("INSERT", consulta);
        return consultaRepository.save(consulta);
    }

    public Consulta updateConsulta(Long id, Consulta consulta) {
        consulta.setId(id); // Garante que o id correto seja definido para a atualização
    	logConsulta("UPDATE", consulta);
        return consultaRepository.save(consulta);
    }

    public void deleteConsulta(Long id) {
    	Consulta entity = new Consulta();
    	entity.setId(id);
    	logConsulta("DELETE", entity);
        consultaRepository.deleteById(id);
    }
    
    
    
    
    
    
    
    
    
    
    private void logConsulta(String operation, Consulta entity) {
        Long userId = getCurrentUserId();
            
        
        Audit audit = new Audit();
        audit.setEntityName("CONSULTA");
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

        if (entity instanceof Consulta) {
            return ((Consulta) entity).getId();
        }
        return null;
    }
}
