package sda.finalproject.demo.Restcontroller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sda.finalproject.demo.model.DentalFile;
import sda.finalproject.demo.service.DentalFileServices;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class DentalFileController {

    private final DentalFileServices dentalFileServices;

    public DentalFileController(DentalFileServices dentalFileServices) {
        this.dentalFileServices = dentalFileServices;
    }

    @GetMapping(path = "/files")
    public ResponseEntity<List<DentalFile>> getAllDentalFiles(){
        List<DentalFile> dentalFiles = dentalFileServices.getAllDentalFiles();
        log.info("get with success");
        return ResponseEntity.ok(dentalFiles);
    }
    @GetMapping(path = "/file/{id}")
    public ResponseEntity<DentalFile> getDentalFileById(@PathVariable Long id){
        DentalFile file = dentalFileServices.getDentalFileById(id);
        log.info("get with success");
        return ResponseEntity.ok(file);
    }
//    @PutMapping(path = "/edit/file/patient/{id}")
//    public ResponseEntity<DentalFile> editDentalFile(@PathVariable Long id, @RequestBody DentalFile file){
//        dentalFileServices.addDentalToPatient(id, file);
//        log.info("edit with success");
//        return ResponseEntity.ok().build();
//    }
//    @PostMapping(path = "/file/patient/{id}")
//    public ResponseEntity<DentalFile> saveDentalFileToPatient(@PathVariable Long id, @RequestBody DentalFile file){
//        dentalFileServices.addDentalToPatient(id, file);
//        log.info("add with success");
//        return ResponseEntity.ok().build();
//    }
    @DeleteMapping(path = "/delete/file/{id}")
    public ResponseEntity<?> deleteDentalFile(@PathVariable Long id){
        dentalFileServices.deleteDentalFile(id);
        log.info("delete with success");
        return ResponseEntity.noContent().build();
    }
    @GetMapping(path = "/files/patient/{id}")
    public ResponseEntity<List<DentalFile>> getPatientDentalFiles(@PathVariable Long id){
        List<DentalFile> dentalFiles = dentalFileServices.getDentalFilesByPatientId(id);
        log.info("get with success");
        return ResponseEntity.ok(dentalFiles);
    }
}
