package com.spring.jwt.intrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interests")
public class InterestController {

    @Autowired
    private InterestService interestService;

    // ================= SEND INTEREST =================

    @PostMapping("/send/{receiverId}")
    public ResponseEntity<InterestResponseDTO> sendInterest(@PathVariable Integer receiverId) {

        InterestResponseDTO response = interestService.sendInterest(receiverId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ================= ACCEPT INTEREST =================

    @PutMapping("/accept/{interestId}")
    public ResponseEntity<InterestResponseDTO> acceptInterest(@PathVariable Integer interestId) {

        InterestResponseDTO response = interestService.acceptInterest(interestId);
        return ResponseEntity.ok(response);
    }

    // ================= DECLINE INTEREST =================

    @PutMapping("/decline/{interestId}")
    public ResponseEntity<InterestResponseDTO> declineInterest(@PathVariable Integer interestId) {

        InterestResponseDTO response = interestService.declineInterest(interestId);
        return ResponseEntity.ok(response);
    }

    // ================= SENT INTERESTS =================

    @GetMapping("/sent")
    public ResponseEntity<List<InterestDTO>> getSentInterests() {

        List<InterestDTO> list = interestService.getSentInterests();
        return ResponseEntity.ok(list);
    }

    // ================= RECEIVED INTERESTS =================

    @GetMapping("/received")
    public ResponseEntity<List<InterestDTO>> getReceivedInterests() {

        List<InterestDTO> list = interestService.getReceivedInterests();
        return ResponseEntity.ok(list);
    }

    // ================= CANCEL INTEREST =================

    @DeleteMapping("/cancel/{interestId}")
    public ResponseEntity<String> cancelInterest(
            @PathVariable Integer interestId) {

        interestService.cancelInterest(interestId);
        return ResponseEntity.ok("Interest cancelled successfully");
    }
}
