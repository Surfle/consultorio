package com.consultorio.consultorio.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consultorio.consultorio.entity.Audit;
import com.consultorio.consultorio.repository.AuditRepository;

@Service
public class AuditService {

    @Autowired
    private AuditRepository auditRepository;

    public void log(String entityName, Long entityId, String operation, Long userId) {
        Audit audit = new Audit();
        audit.setEntityName(entityName);
        audit.setEntityId(entityId);
        audit.setOperation(operation);
        audit.setUserId(userId);
        audit.setTimestamp(new Timestamp(System.currentTimeMillis()));
        auditRepository.save(audit);
    }
    
}

