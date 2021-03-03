package sda.finalproject.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sda.finalproject.demo.model.Patient;
import sda.finalproject.demo.repository.PatientRepository;

@Slf4j
@Service
public class ImageService {
    private final PatientRepository patientRepository;

    public ImageService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    @Transactional
    public void saveImageFile(Long patientId, MultipartFile file){
        try {
            Patient patient = patientRepository.findById(patientId).get();
            byte[] byteObject = new byte[file.getBytes().length];
            int i = 0;
            for (byte b : file.getBytes()){
                byteObject[i++] = b;
            }
            patient.setImage(byteObject);
            patientRepository.save(patient);
        } catch (Exception e) {
            log.error("Something happend here", e.getStackTrace());
        }
    }

}
