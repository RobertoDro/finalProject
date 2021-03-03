package sda.finalproject.demo.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import sda.finalproject.demo.model.DentalFile;
import sda.finalproject.demo.model.Patient;
import sda.finalproject.demo.service.DentalFileServices;
import sda.finalproject.demo.service.PatientService;

import java.util.List;

@Controller
@ComponentScan
public class DentalController {

    private final DentalFileServices dentalFileServices;
    private final PatientService patientService;

    public DentalController(DentalFileServices dentalFileServices, PatientService patientService) {
        this.dentalFileServices = dentalFileServices;
        this.patientService = patientService;
    }

    @GetMapping("/patient/dentalfile/{id}")
    public String getAllDentalFilesForPatient(Model model, @PathVariable Long id){
        List<DentalFile> dentalFiles = dentalFileServices.getDentalFilesByPatientId(id);
        model.addAttribute("dentalFiles", dentalFiles);
        return " ";
    }

    @GetMapping("/{patientId}/deletedentalfile/{id}")
    public String deleteDentalFile(@PathVariable Long patientId ,@PathVariable Long id){
        dentalFileServices.deleteDentalFile(id);
        return "redirect:/patient/{patientId}";
    }

    @GetMapping("/adddentalfile/{id}")
    public String addDentalFile(Model model, @PathVariable Long id){
        Patient patient = patientService.getPatientById(id);
        model.addAttribute("patient", patient);
        model.addAttribute("dentalFile", new DentalFile());
        return "/adddentalfile";
    }

    @PostMapping("/adddentalfile/{id}")
    public String addDentalFile(@PathVariable Long id ,@ModelAttribute DentalFile dentalFile){
        dentalFileServices.addDentalToPatient(id ,dentalFile);
        return "redirect:/patient/{id}";
    }

}
