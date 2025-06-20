package com.kushbhakkad.nseapicall.service;

import com.kushbhakkad.nseapicall.dto.AnnouncementDto;
import com.kushbhakkad.nseapicall.model.Announcement;
import com.kushbhakkad.nseapicall.repository.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
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
    private static final String NSE_HOME = "https://www.nseindia.com/";

    public void fetchAndSaveAnnouncements() {
        try {
            RestTemplate restTemplate = new RestTemplate();

            // Step 1: Hit NSE homepage to retrieve cookies
            HttpHeaders homeHeaders = new HttpHeaders();
            homeHeaders.set("User-Agent", "Mozilla/5.0");
            homeHeaders.set("Accept", "text/html,application/xhtml+xml,application/xml");
            homeHeaders.set("Accept-Language", "en-US,en;q=0.9");

            HttpEntity<String> homeRequest = new HttpEntity<>(homeHeaders);
            ResponseEntity<String> homeResponse = restTemplate.exchange(NSE_HOME, HttpMethod.GET, homeRequest, String.class);

            List<String> cookies = homeResponse.getHeaders().get("Set-Cookie");
            String cookieHeader = String.join("; ", cookies);

            // Step 2: Use cookies to hit the API
            HttpHeaders apiHeaders = new HttpHeaders();
            apiHeaders.set("User-Agent", "Mozilla/5.0");
            apiHeaders.set("Accept", "application/json");
            apiHeaders.set("Referer", "https://www.nseindia.com/");
            apiHeaders.set("Cookie", cookieHeader);

            HttpEntity<String> apiRequest = new HttpEntity<>(apiHeaders);
            ResponseEntity<String> response = restTemplate.exchange(NSE_URL, HttpMethod.GET, apiRequest, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                String jsonResponse = response.getBody();
                ObjectMapper mapper = new ObjectMapper();
                List<AnnouncementDto> dtoList = mapper.readValue(jsonResponse, new TypeReference<List<AnnouncementDto>>() {});
                for (AnnouncementDto dto : dtoList) {
                    Announcement announcement = new Announcement();
                    announcement.setSmName(dto.getSmName());
                    announcement.setAttchmntText(dto.getAttchmntText());
                    repository.save(announcement);
                    System.out.println("Saving: " + dto.getSmName() + " | " + dto.getAttchmntText());
                }
            } else {
                System.out.println("Failed to fetch data from NSE. Status code: " + response.getStatusCode());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}