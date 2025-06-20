package com.kushbhakkad.nseapicall.service;

import com.kushbhakkad.nseapicall.dto.AnnouncementDto;
import com.kushbhakkad.nseapicall.model.Announcement;
import com.kushbhakkad.nseapicall.repository.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

@Service
public class NseApiService {

    @Autowired
    private AnnouncementRepository repository;

    private static final String NSE_URL = "https://www.nseindia.com/api/corporate-announcements?index=equities";

    public void fetchAndSaveAnnouncements() {
        RestTemplate restTemplate = new RestTemplate();
        String jsonResponse = restTemplate.getForObject(NSE_URL, String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<AnnouncementDto> dtoList = objectMapper.readValue(jsonResponse, new TypeReference<List<AnnouncementDto>>() {});
            for (AnnouncementDto dto : dtoList) {
                Announcement a = new Announcement();
                a.setSmName(dto.getSmName());
                a.setAttchmntText(dto.getAttchmntText());
                repository.save(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}