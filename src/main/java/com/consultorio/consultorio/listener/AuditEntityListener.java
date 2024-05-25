package com.consultorio.consultorio.listener;


import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.consultorio.consultorio.entity.Audit;
import com.consultorio.consultorio.entity.Endereco;
import com.consultorio.consultorio.entity.User;
import com.consultorio.consultorio.repository.AuditRepository;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;

public class AuditEntityListener {

    @Autowired
    private AuditRepository auditRepository;
    
    @PrePersist
    public void prePersist(Endereco entity) {
        log("INSERT", entity);
    }

    @PreUpdate
    public void preUpdate(Endereco entity) {
        log("UPDATE", entity);
    }

    @PreRemove
    public void preRemove(Endereco entity) {
        log("DELETE", entity);
    }

    private void log(String operation, Endereco entity) {
        Long userId = getCurrentUserId();
            
        
        Audit audit = new Audit();
        audit.setEntityName("endereco");
        audit.setEntityId(getEntityId(entity));
        audit.setOperation(operation);
        audit.setUserId(userId);
        audit.setTimestamp(new Timestamp(System.currentTimeMillis()));
        auditRepository.save(audit);
    }

    private Long getCurrentUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
        	System.out.println("id = "+((User) principal).getId());
            return ((User) principal).getId();
        } else {
        	System.out.println("retornou nulo");
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
