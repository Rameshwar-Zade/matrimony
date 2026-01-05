package com.spring.jwt.Contact_Us;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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

    @GetMapping("/export-to-file")
    public String exportToSystemFile() throws IOException {
        contactUsService.exportContactUsToExcelFile();
        return "Exported Successfully";
    }

}

