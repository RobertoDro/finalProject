//package sda.finalproject.demo.Restcontroller;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.tomcat.util.http.fileupload.IOUtils;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import sda.finalproject.demo.model.Patient;
//import sda.finalproject.demo.service.ImageService;
//import sda.finalproject.demo.service.PatientService;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//
//@Slf4j
//@RestController
//public class ImageController {
//
//    private final PatientService patientService;
//    private final ImageService imageService;
//
//    public ImageController(PatientService patientService, ImageService imageService) {
//        this.patientService = patientService;
//        this.imageService = imageService;
//    }
//
//    @PostMapping("patient/{id}/image")
//    public void handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile file){
//        imageService.saveImageFile(Long.valueOf(id), file);
//        log.info("add with success");
//    }
//
//    @GetMapping("patient/{id}/image")
//    public void renderImageFromDB(@PathVariable Long id, HttpServletResponse response) throws IOException {
//        Patient patient = patientService.getPatientById(id);
//
//        if (patient.getImage() != null) {
//            byte[] byteArray = new byte[patient.getImage().length];
//            int i = 0;
//
//            for (Byte wrappedByte : patient.getImage()){
//                byteArray[i++] = wrappedByte;
//            }
//
//            response.setContentType("image/jpeg");
//            InputStream is = new ByteArrayInputStream(byteArray);
//            IOUtils.copy(is, response.getOutputStream());
//        }
//        log.info("get with success");
//    }
//}
