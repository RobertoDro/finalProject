package sda.finalproject.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sda.finalproject.demo.model.ToothFile;

import java.util.List;

@Repository
public interface ToothFileRepository extends JpaRepository<ToothFile, Long> {
    List<ToothFile> findAllByDentalFileId(Long id);
}
