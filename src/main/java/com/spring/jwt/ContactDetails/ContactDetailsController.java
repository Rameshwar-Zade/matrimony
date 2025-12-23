package com.spring.jwt.ContactDetails;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
    public ResponseEntity<String> create(
            @Valid @RequestBody ContactDetailsDTO dto) {

        service.createContactDetails(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Contact details saved successfully");
    }

    @GetMapping("/get")
    public ResponseEntity<ContactDetailsDTO> get() {
        return ResponseEntity.ok(service.getContactDetails());
    }

    @PatchMapping("/update")
    public ResponseEntity<String> update(
            @RequestBody ContactDetailsDTO dto) {

        service.updateContact(dto);
        return ResponseEntity.ok("Contact details updated successfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete() {
        service.deleteContactDetails();
        return ResponseEntity.ok("Contact details deleted successfully");
    }
}
