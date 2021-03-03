package sda.finalproject.demo.service;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sda.finalproject.demo.model.Patient;
import sda.finalproject.demo.repository.PatientRepository;

import java.util.*;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }


    public Patient getPatientById(Long id){
        Optional<Patient> patient = patientRepository.findById(id);
        return patient.get();
    }

    public List<Patient> getAllPatient(){
        List<Patient> patients = new ArrayList<>();
        patientRepository.findAll().iterator().forEachRemaining(patients::add);
        return patients;
    }
    public void deleteById(Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isPresent()) {
            patientRepository.deleteById(id);
        }
    }
    @Transactional
    public void savePatient(Patient patient){
        patientRepository.save(patient);
    }

}
