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
import sda.finalproject.demo.model.ToothFile;
import sda.finalproject.demo.service.DentalFileServices;
import sda.finalproject.demo.service.PatientService;
import sda.finalproject.demo.service.ToothFileService;

import java.util.List;

@Controller
@ComponentScan
public class ToothController {

    private final ToothFileService toothFileService;
    private final DentalFileServices dentalFileServices;
    private final PatientService  patientService;

    public ToothController(ToothFileService toothFileService, DentalFileServices dentalFileServices, PatientService patientService) {
        this.toothFileService = toothFileService;
        this.dentalFileServices = dentalFileServices;
        this.patientService = patientService;
    }

    @GetMapping("/dentalfile/teeth/{id}")
    public String showDentalFileTeeth(@PathVariable Long id, Model model){
        List<ToothFile> teeth = toothFileService.getAllTeethByDentalFileId(id);
        model.addAttribute("teeth", teeth);
        return "";
    }
    @GetMapping("{patientId}/addtooth/{id}")
    public String addToothFile(Model model, @PathVariable Long id, @PathVariable Long patientId){
        DentalFile dentalFile = dentalFileServices.getDentalFileById(id);
        Patient patient = patientService.getPatientById(patientId);
        model.addAttribute("dentalFile", dentalFile);
        model.addAttribute("patient", patient);
        model.addAttribute("tooth", new ToothFile());
        return "/addtooth";
    }

    @PostMapping("{patientId}/addtooth/{id}")
    public String addToothFile(@PathVariable Long patientId, @PathVariable Long id ,@ModelAttribute ToothFile toothFile){
        toothFileService.addToothToDentalFile(id,toothFile);
        return "redirect:/patient/{patientId}";
    }
}
