package sda.finalproject.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sda.finalproject.demo.model.DentalFile;
import sda.finalproject.demo.model.Patient;
import sda.finalproject.demo.repository.DentalFileRepository;
import sda.finalproject.demo.repository.PatientRepository;

import java.util.*;

@Service
public class DentalFileServices {
    private final DentalFileRepository dentalFileRepository;
    private final PatientRepository patientRepository;

    public DentalFileServices(DentalFileRepository dentalFileRepository, PatientRepository patientRepository) {
        this.dentalFileRepository = dentalFileRepository;
        this.patientRepository = patientRepository;
    }

    public List<DentalFile> getAllDentalFiles(){
        List<DentalFile> dentalFiles = dentalFileRepository.findAll();
        return dentalFiles;
    }
    public DentalFile getDentalFileById(Long id){
        Optional<DentalFile> dentalFile = dentalFileRepository.findById(id);
        return dentalFile.get();
    }
    public void deleteDentalFile(Long id){
        Optional<DentalFile> dentalFile = dentalFileRepository.findById(id);
        if(dentalFile.isPresent()){
            dentalFileRepository.deleteById(id);
        }
    }
    @Transactional
    public void addDentalToPatient(Long patientId, DentalFile dentalFile){
        Optional<Patient> patient = patientRepository.findById(patientId);
        if(patient.isPresent()){
            dentalFile.setPatient(patient.get());
            dentalFileRepository.save(dentalFile);
        }
    }
    @Transactional
    public List<DentalFile> getDentalFilesByPatientId(Long id){
       Optional<List<DentalFile>> dentalFileList = Optional.ofNullable(dentalFileRepository.findByPatientId(id));
        return dentalFileList.get();
    }




}
