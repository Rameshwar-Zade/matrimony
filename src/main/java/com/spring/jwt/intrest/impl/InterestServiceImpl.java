package com.spring.jwt.intrest.impl;

import com.spring.jwt.entity.Interest;
import com.spring.jwt.entity.User;
import com.spring.jwt.enums.InterestStatus;
import com.spring.jwt.exception.*;
import com.spring.jwt.intrest.InterestDTO;
import com.spring.jwt.intrest.InterestMapper;
import com.spring.jwt.intrest.InterestResponseDTO;
import com.spring.jwt.intrest.InterestService;
import com.spring.jwt.jwt.JwtService;
import com.spring.jwt.repository.InterestRepository;
import com.spring.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class InterestServiceImpl implements InterestService {

    @Autowired
    private InterestRepository interestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Override
    public InterestResponseDTO sendInterest(Integer receiverId) {

        Integer senderId = jwtService.extractUserId(jwtService.extractToken());

        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new InterestNotFoundException("Sender not found"));

        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new InterestNotFoundException("Receiver not found"));

        if (sender.getId().equals(receiver.getId())) {
            throw new UnauthorizedException("Cannot send interest to yourself");
        }

        if (interestRepository.findBySenderAndReceiver(sender, receiver).isPresent()) {
            throw new DuplicateInterestException("Interest already sent");
        }


        Interest interest = new Interest();
        interest.setSender(sender);
        interest.setReceiver(receiver);
        interest.setStatus(InterestStatus.PENDING);

        interestRepository.save(interest);
        InterestResponseDTO response = new InterestResponseDTO();
        response.setSenderID(sender.getId());
        response.setReceiverID(receiver.getId());
        response.setCode("200");
        response.setMessage("Interest sent successfully");

        return response;
    }

//    @Override
//    public InterestResponseDTO respondInterest(Integer interestId, InterestStatus status) {
//
//        Integer userId = jwtService.extractUserId(jwtService.extractToken());
//
//        Interest interest = interestRepository.findById(interestId)
//                .orElseThrow(() -> new InterestNotFoundException("Interest not found"));
//
//        if (!interest.getReceiver().getId().equals(userId)) {
//            throw new UnauthorizedException("You are not allowed to respond");
//        }
//
//        if (interest.getStatus() != InterestStatus.PENDING) {
//            throw new DuplicateInterestException("Already responded");
//        }
//
//        interest.setStatus(status);
//        interestRepository.save(interest);
//
//        InterestResponseDTO response = new InterestResponseDTO();
//        response.setSenderID(sender.getId());
//        response.setReceiverID(receiver.getId());
//        response.setCode("200");
//        response.setMessage("Interest sent successfully");
//
//        return response;
//    }

    @Override
    public InterestResponseDTO acceptInterest(Integer interestId) {

        Integer receiverId = jwtService.extractUserId(jwtService.extractToken());

        if (interestId == null) {
            throw new EmptyFieldException("Interest ID cannot be null");
        }

        Interest interest = interestRepository.findById(interestId)
                .orElseThrow(() -> new InterestNotFoundException("Interest request not found"));

        if (!interest.getReceiver().getId().equals(receiverId)) {
            throw new UnauthorizedException("You are not authorized to accept this request");
        }

        interest.setStatus(InterestStatus.ACCEPTED);
        interest.setUpdatedAt(Instant.now());
        interestRepository.save(interest);

        InterestResponseDTO response = new InterestResponseDTO();
        response.setSenderID(interest.getSender().getId());
        response.setReceiverID(interest.getReceiver().getId());
        response.setCode("200");
        response.setMessage("Accepted Interest");

        return response;

    }

    @Override
    public InterestResponseDTO declineInterest(Integer interestId) {

        Integer receiverId = jwtService.extractUserId(jwtService.extractToken());

        if (interestId == null) {
            throw new EmptyFieldException("Interest ID cannot be null");
        }

        Interest interest = interestRepository.findById(interestId)
                .orElseThrow(() -> new InterestNotFoundException("Interest request not found"));

        if (!interest.getReceiver().getId().equals(receiverId)) {
            throw new UnauthorizedException("You are not authorized to accept this request");
        }

        interest.setStatus(InterestStatus.DENIED);
        interest.setUpdatedAt(Instant.now());
        interestRepository.save(interest);

        InterestResponseDTO response = new InterestResponseDTO();
        response.setSenderID(interest.getSender().getId());
        response.setReceiverID(interest.getReceiver().getId());
        response.setCode("200");
        response.setMessage("Interest declined");

        return response;

    }
    @Override
    public List<InterestDTO> getSentInterests() {

        Integer senderId = jwtService.extractUserId(jwtService.extractToken());

        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new UserNotFoundExceptions("User not found"));

        List<Interest> interests = interestRepository.findBySender(sender);

        List<InterestDTO> dtoList = new ArrayList<>();

        for (Interest interest : interests) {
            InterestDTO dto = InterestMapper.toDTO(interest);
            dtoList.add(dto);
        }

        return dtoList;
    }


    @Override
    public List<InterestDTO> getReceivedInterests() {

        Integer userId = jwtService.extractUserId(jwtService.extractToken());
        User receiver = userRepository.findById(userId)
                .orElseThrow(() -> new InterestNotFoundException("User not found"));

        List<Interest> interests = interestRepository.findByReceiver(receiver);

        List<InterestDTO> dtoList = new ArrayList<>();

        for (Interest interest : interests) {
            InterestDTO dto = InterestMapper.toDTO(interest);
            dtoList.add(dto);
        }

        return dtoList;
    }

    @Override
    public void cancelInterest(Integer interestId) {

        Integer userId = jwtService.extractUserId(jwtService.extractToken());

        Interest interest = interestRepository.findById(interestId)
                .orElseThrow(() -> new InterestNotFoundException("Interest not found"));

        if (!interest.getSender().getId().equals(userId)) {
            throw new UnauthorizedException("You cannot cancel this interest");
        }

        if (interest.getStatus() != InterestStatus.PENDING) {
            throw new DuplicateInterestException("Cannot cancel after response");
        }

        interestRepository.delete(interest);
    }
}
