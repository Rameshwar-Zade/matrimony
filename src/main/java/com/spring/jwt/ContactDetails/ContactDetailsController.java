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
    public ResponseEntity<String> create(@Valid @RequestBody ContactDetailsDTO dto) {
        service.createContactDetails(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Contact Details Saved");
    }

    @GetMapping("/get")
    public ResponseEntity<ContactDetails> get(){
        ContactDetails details=service.getContactDetils();
        return ResponseEntity.status(HttpStatus.OK).body(details);
    }

    @PatchMapping("/update/{userId}")
    public ResponseEntity<ContactDetails> update(@PathVariable Integer userId, @RequestBody ContactDetailsDTO dto){
        ContactDetails updated=service.updateContact(userId, dto);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> delete(@PathVariable Integer userId){
        service.deleteByUserID(userId);
       return ResponseEntity.status(HttpStatus.OK).body("deleted Successfully");
    }

}
