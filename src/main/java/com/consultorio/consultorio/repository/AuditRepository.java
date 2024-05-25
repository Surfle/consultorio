package com.consultorio.consultorio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.consultorio.consultorio.entity.Audit;

@Repository
public interface AuditRepository extends JpaRepository<Audit, Long> {
}

