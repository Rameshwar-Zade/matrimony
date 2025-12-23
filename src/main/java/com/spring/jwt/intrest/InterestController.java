package com.spring.jwt.intrest;

import com.spring.jwt.enums.InterestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interests")
public class InterestController {

    @Autowired
    private InterestService interestService;


    @PostMapping("/send/{receiverId}")
    public ResponseEntity<String> sendInterest(@PathVariable Integer receiverId) {

        interestService.sendInterest(receiverId);
        return ResponseEntity.ok("Interest sent successfully");
    }


    @PutMapping("/respond/{interestId}")
    public ResponseEntity<String> respondInterest(
            @PathVariable Integer interestId,
            @RequestParam InterestStatus status) {

        interestService.respondInterest(interestId, status);
        return ResponseEntity.ok("Interest " + status.name().toLowerCase());
    }


    @GetMapping("/sent")
    public ResponseEntity<List<InterestDTO>> getSentInterests() {

        List<InterestDTO> sentInterests = interestService.getSentInterests();
        return ResponseEntity.ok(sentInterests);
    }


    @GetMapping("/received")
    public ResponseEntity<List<InterestDTO>> getReceivedInterests() {

        List<InterestDTO> receivedInterests = interestService.getReceivedInterests();
        return ResponseEntity.ok(receivedInterests);
    }


    @DeleteMapping("/cancel/{interestId}")
    public ResponseEntity<String> cancelInterest(@PathVariable Integer interestId) {

        interestService.cancelInterest(interestId);
        return ResponseEntity.ok("Interest cancelled successfully");
    }
}
