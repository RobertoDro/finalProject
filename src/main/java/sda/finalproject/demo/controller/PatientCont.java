package sda.finalproject.demo.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sda.finalproject.demo.model.Patient;
import sda.finalproject.demo.service.DocumentService;
import sda.finalproject.demo.service.ImageService;
import sda.finalproject.demo.service.PatientService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@ComponentScan
public class PatientCont {

    private final PatientService patientService;
    private final ImageService imageService;
    private final DocumentService documentService;

    public PatientCont(PatientService patientService, ImageService imageService, DocumentService documentService) {
        this.patientService = patientService;
        this.imageService = imageService;
        this.documentService = documentService;
    }

    @GetMapping("/patients")
    public String showAllPatient(Model model) {

        List<Patient> patients = patientService.getAllPatient();
        model.addAttribute("patients", patients);

        return "patients";
    }
    @GetMapping("/addpatient")
    public String addPatient(Model model) {
        model.addAttribute("patient", new Patient());
        // initial bind with the form, to say to the webpage
        // what is the type of student th:object
        return "/addpatient";
    }
    @PostMapping("/addpatient")
    public String addPatient(@ModelAttribute Patient patient){
        patientService.savePatient(patient);
        return "redirect:/patients";
    }
    @GetMapping("/deleteppatient/{id}")
    public String deletePatient(@PathVariable Long id) {
        patientService.deleteById(id);

        return "redirect:/patients";
    }
    @GetMapping("/editpatient/{id}")
    public String editPatient(Model model, @PathVariable Long id) {
        Patient patient = patientService.getPatientById(id);
        model.addAttribute("patient", patient);
        // initial bind with the form, to say to the webpage what is the type of student th:object

        return "/editpatient";
    }
    @PostMapping("/editpatient/{id}")
    public String editPatient(@ModelAttribute Patient patient, @PathVariable Long id){
        patientService.savePatient(patient);
        return "redirect:/patients";
    }
    @GetMapping("/patient/{id}")
    public String getPatientById(Model model, @PathVariable Long id){
        Patient patient = patientService.getPatientById(id);
        model.addAttribute("patient", patient);
        return "patientprofile";
    }

    @PostMapping("upload/{id}")
    public String uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file){
         imageService.saveImageFile(id, file);
         return "redirect:/patient/{id}";
    }

    @GetMapping(value = "/patient/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void fromDatabaseAsHttpServResp(@PathVariable("id") Long id, HttpServletResponse response)
            throws IOException {
        Optional<Patient> patient = Optional.ofNullable(patientService.getPatientById(id));
        if(patient.isPresent()){
            byte[] image = patient.get().getImage();
            StreamUtils.copy(image, response.getOutputStream());
        }
    }

    @PostMapping("document/{id}")
    public String uploadDocument(@PathVariable Long id,@RequestParam("file") MultipartFile file ){
        documentService.saveDocument(id,file);
        return "redirect:/patient/{id}";
    }

    @GetMapping(value = "/patient/document/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public void documentFromDb(@PathVariable Long id, HttpServletResponse response) throws IOException{
       Optional<Patient> patient = Optional.ofNullable(patientService.getPatientById(id));
        if(patient.isPresent()){
            byte[] document = patient.get().getDocument();
            StreamUtils.copy(document,response.getOutputStream());
        }
    }
}


