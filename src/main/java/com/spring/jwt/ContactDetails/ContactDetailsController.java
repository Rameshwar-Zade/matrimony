package com.spring.jwt.ContactDetails;


import com.spring.jwt.entity.ContactDetails;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact-details")
public class ContactDetailsController {

    @Autowired
    private ContactDetailsService service;

    @PostMapping("/create")
    public ResponseEntity<String> createContactDetails(@Valid @RequestBody ContactDetailsDTO dto) {
        service.createContactDetails(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Contact Details Saved");
    }

    @GetMapping("/get")
    public ResponseEntity<ContactDetails> getContactDetails(){
        ContactDetails details=service.getContactDetils();
        return ResponseEntity.status(HttpStatus.OK).body(details);
    }

}
