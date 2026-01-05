package com.spring.jwt.Contact_Us;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact-us")
public class ContactUsController {

    @Autowired
    private ContactUsService contactUsService;

    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestBody ContactUsRequestDTO request) {
        contactUsService.submitMessage(request);
        return ResponseEntity.ok("Message sent successfully");
    }
}

