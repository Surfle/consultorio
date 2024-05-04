package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entity.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

}
