package com.spring.jwt.ContactDetails;

import com.spring.jwt.utils.BaseResponseDTO;
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
    public ResponseEntity<BaseResponseDTO> create(@Valid @RequestBody ContactDetailsDTO dto) {
         BaseResponseDTO response=  service.createContactDetails(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/get")
    public ResponseEntity<ContactDetailsDTO> get() {
        return ResponseEntity.ok(service.getContactDetails());
    }

    @PatchMapping("/update")
    public ResponseEntity<String> update(@RequestBody ContactDetailsDTO dto) {

        service.updateContact(dto);
        return ResponseEntity.ok("Contact details updated successfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<BaseResponseDTO> delete() {

        return ResponseEntity.ok(service.deleteContactDetails());
    }

}
