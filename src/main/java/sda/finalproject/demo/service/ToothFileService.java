package sda.finalproject.demo.service;

import lombok.Synchronized;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sda.finalproject.demo.model.DentalFile;
import sda.finalproject.demo.model.ToothFile;
import sda.finalproject.demo.repository.DentalFileRepository;
import sda.finalproject.demo.repository.ToothFileRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ToothFileService {

    private final ToothFileRepository toothFileRepository;
    private final DentalFileRepository dentalFileRepository;

    public ToothFileService(ToothFileRepository toothFileRepository, DentalFileRepository dentalFileRepository) {
        this.toothFileRepository = toothFileRepository;
        this.dentalFileRepository = dentalFileRepository;
    }


    public ToothFile getToothById(Long id){
        Optional<ToothFile> tooth = toothFileRepository.findById(id);
        return tooth.get();
    }

    @Transactional
    public List<ToothFile> getAllTeethByDentalFileId(Long id){
        Optional<List<ToothFile>> teeth = Optional.ofNullable(toothFileRepository.findAllByDentalFileId(id));
        return teeth.get();
    }


    public void deleteToothFile(Long id){
        Optional<ToothFile> toothFile = toothFileRepository.findById(id);
        if (toothFile.isPresent()){
            toothFileRepository.deleteById(id);
        }
    }

    @Transactional
    public void addToothToDentalFile(Long dentalFileId, ToothFile toothFile){
        Optional<DentalFile> dentalFile = dentalFileRepository.findById(dentalFileId);
        if(dentalFile.isPresent()){
            toothFile.setDentalFile(dentalFile.get());
            toothFileRepository.save(toothFile);
        }
    }



}
