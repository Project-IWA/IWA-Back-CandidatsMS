package com.iwa.candidats.init;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iwa.candidats.model.Candidat;
import com.iwa.candidats.repository.CandidatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class DataInitializer {

    @Autowired
    private CandidatRepository candidatRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    @PostConstruct
    public void initData() {
        if (candidatRepository.count() == 0) {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Candidat>> typeReference = new TypeReference<>() {};
            InputStream inputStream;
            try {
                // Assuming the JSON file is in the resources directory
                inputStream = resourceLoader.getResource("classpath:candidats.json").getInputStream();
                List<Candidat> candidats = mapper.readValue(inputStream, typeReference);
                candidatRepository.saveAll(candidats);
                System.out.println("Candidats Saved!");
            } catch (IOException e){
                System.out.println("Unable to save candidats: " + e.getMessage());
            }
        }
    }
}
