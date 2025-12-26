package com.spring.jwt.controller;


import com.spring.jwt.dto.EducationAndProfessionDto;
import com.spring.jwt.dto.ResponseDto;
import com.spring.jwt.service.EducationAndProfessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/education-profession")
//@CrossOrigin(origins = "${app.cors.allowed-origins}", maxAge = 3600)
public class EducationAndProfessionController {

    private final EducationAndProfessionService service;

    public EducationAndProfessionController(EducationAndProfessionService service) {
        this.service = service;

    }

    @PostMapping("/create")
    public ResponseEntity<EducationAndProfessionDto> create(@RequestBody EducationAndProfessionDto dto) {
        EducationAndProfessionDto savedDto = service.create(dto);
        return ResponseEntity.ok(savedDto);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EducationAndProfessionDto> getEducationAndProfessionalDetails(@PathVariable Integer id) {
        EducationAndProfessionDto dto = service.getById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping()
    public ResponseEntity<EducationAndProfessionDto> getByCurrentUser(){
        EducationAndProfessionDto dto = service.getByLoggedInUser();
        return ResponseEntity.ok(dto);
    }


    @DeleteMapping(value="/{id}")
    public void deleteEducationalAndProfessionalDetails(@PathVariable Integer id){
        service.delete(id);
    }

    @PatchMapping(value="/{id}")
    public ResponseEntity<EducationAndProfessionDto> patchEducationAndProfessionalDetails(@PathVariable Integer id,
                                                                                          @RequestBody
                                                                                                  EducationAndProfessionDto dto){
        EducationAndProfessionDto patchDto=service.partialUpdate(id,dto);
        return ResponseEntity.ok(patchDto);

    }


}

