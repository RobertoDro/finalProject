package sda.finalproject.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sda.finalproject.demo.model.DentalFile;

import java.util.List;

@Repository
public interface DentalFileRepository extends JpaRepository<DentalFile, Long> {
    List<DentalFile> findByPatientId(Long id);
}

