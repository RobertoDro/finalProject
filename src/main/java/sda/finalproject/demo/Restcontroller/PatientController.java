package sda.finalproject.demo.Restcontroller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sda.finalproject.demo.model.Patient;
import sda.finalproject.demo.service.PatientService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }


    @GetMapping(path = "/patients")
    public ResponseEntity<List<Patient>> getAllPatientsTest(){
        List<Patient> patients = patientService.getAllPatient();
        return ResponseEntity.ok(patients);
    }

    @GetMapping(path = "/patient/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id){
        Patient patient = patientService.getPatientById(id);
        log.info("get with success");
        return ResponseEntity.ok(patient);
    }
    @DeleteMapping(path = "/delete/patient/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable Long id){
        patientService.deleteById(id);
        log.info("delete with success");
        return ResponseEntity.noContent().build();
    }
    @PutMapping(path = "/edit/patient")
    public ResponseEntity<Patient> editPatient(@RequestBody Patient patient){
        patientService.savePatient(patient);
        log.info("edit with success");
        return ResponseEntity.ok().build();
    }
    @PostMapping(path = "/patient")
    public ResponseEntity<Patient> savePatient(@RequestBody Patient patient){
        patientService.savePatient(patient);
        log.info("add with success");
        return ResponseEntity.ok().build();
    }
}
