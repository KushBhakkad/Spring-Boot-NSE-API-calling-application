package com.kushbhakkad.nseapicall.controller;


import com.kushbhakkad.nseapicall.service.NseApiService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiController {

    @Autowired
    private NseApiService nseApiService;

    @GetMapping("/fetch-announcements")
    public String fetch() throws JSONException {
        nseApiService.fetchAndSaveAnnouncements();
        return "Announcements fetched and saved";
    }
}
