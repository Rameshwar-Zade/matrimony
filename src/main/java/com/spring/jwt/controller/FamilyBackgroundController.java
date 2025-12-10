package com.spring.jwt.controller;


import com.spring.jwt.dto.FamilyBackgroundDto;
import com.spring.jwt.service.FamilyBackgroundService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/family-background")
public class FamilyBackgroundController {

    private final FamilyBackgroundService service;


    public FamilyBackgroundController(FamilyBackgroundService service) {
        this.service = service;
    }


    @PostMapping("/create")
    public ResponseEntity<FamilyBackgroundDto> create(@RequestBody FamilyBackgroundDto dto) {
        FamilyBackgroundDto savedDto = service.create(dto);
        return ResponseEntity.ok(savedDto);
    }

    @GetMapping("/{id}")
   public ResponseEntity<FamilyBackgroundDto> getFamilyBackgroundDetails(@PathVariable("id") Integer id) {
        FamilyBackgroundDto dto = service.getById(id);
       return ResponseEntity.ok(dto);
   }

   @GetMapping
   public ResponseEntity<List<FamilyBackgroundDto>>getAllFamilyBackgroundDetails() {
       List<FamilyBackgroundDto> list = service.getAll();
       return ResponseEntity.ok(list);
   }


    @PutMapping("/{id}")
   public ResponseEntity<FamilyBackgroundDto> updateFamilyBackgroundDetails(@PathVariable Integer id,
                                                                            @RequestBody FamilyBackgroundDto dto){
        FamilyBackgroundDto updated= service.update(id,dto);
        return ResponseEntity.ok(updated);
   }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFamilyBackground(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok("Family Background deleted successfully with id: " + id);
    }

}


