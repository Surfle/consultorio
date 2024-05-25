package com.consultorio.consultorio.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.consultorio.consultorio.entity.Audit;
import com.consultorio.consultorio.entity.Endereco;
import com.consultorio.consultorio.entity.User;
import com.consultorio.consultorio.repository.AuditRepository;
import com.consultorio.consultorio.repository.EnderecoRepository;



@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;
    
    @Autowired
    private AuditRepository auditRepository;

    public List<Endereco> getAllEnderecos() {
        return enderecoRepository.findAll();
    }

    public Endereco getEnderecoById(Long id) {
        Optional<Endereco> optionalEndereco = enderecoRepository.findById(id);
        return optionalEndereco.orElse(null);
    }

    public Endereco addEndereco(Endereco endereco) {
    	logEndereco("INSERT", endereco);
        return enderecoRepository.save(endereco);
    }

    public Endereco updateEndereco(Long id, Endereco endereco) {
        Optional<Endereco> optionalEndereco = enderecoRepository.findById(id);
        if (optionalEndereco.isPresent()) {
            endereco.setId(id);
        	logEndereco("UPDATE", endereco);
            return enderecoRepository.save(endereco);
        } else {
            return null;
        }
    }

    public void deleteEndereco(Long id) {
    	
    	Endereco entity = new Endereco();
    	entity.setId(id);
    	logEndereco("DELETE", entity);
        enderecoRepository.deleteById(id);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    private void logEndereco(String operation, Endereco entity) {
        Long userId = getCurrentUserId();
            
        
        Audit audit = new Audit();
        audit.setEntityName("ENDERECO");
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

        if (entity instanceof Endereco) {
            return ((Endereco) entity).getId();
        }
        return null;
    }
}