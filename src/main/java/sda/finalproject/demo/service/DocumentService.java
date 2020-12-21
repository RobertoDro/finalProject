package sda.finalproject.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sda.finalproject.demo.model.Patient;
import sda.finalproject.demo.repository.PatientRepository;

import java.io.IOException;
@Slf4j
@Service
public class DocumentService {

    private final PatientRepository patientRepository;

    public DocumentService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    @Transactional
    public void saveDocument(Long patientId, MultipartFile docFile){
        try {
            Patient patient = patientRepository.findById(patientId).get();
            byte[] byteDocument = new byte[docFile.getBytes().length];
            int i = 0;
            for (byte b : byteDocument){
                byteDocument[i++] = b;
            }
            patient.setDocument(byteDocument);
            patientRepository.save(patient);
        } catch (IOException e) {
            log.error("Something happend here", e.getStackTrace());

        }
    }
}
