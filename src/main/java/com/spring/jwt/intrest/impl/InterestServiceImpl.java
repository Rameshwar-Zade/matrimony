package com.spring.jwt.intrest.impl;

import com.spring.jwt.entity.Interest;
import com.spring.jwt.entity.User;
import com.spring.jwt.enums.InterestStatus;
import com.spring.jwt.exception.DuplicateInterestException;
import com.spring.jwt.exception.InterestNotFoundException;
import com.spring.jwt.exception.UnauthorizedException;
import com.spring.jwt.intrest.InterestDTO;
import com.spring.jwt.intrest.InterestMapper;
import com.spring.jwt.intrest.InterestService;
import com.spring.jwt.repository.InterestRepository;
import com.spring.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InterestServiceImpl implements InterestService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InterestRepository interestRepository;

    // Send Interest
    @Override
    public void sendInterest(Integer receiverId) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email);
        Integer senderId =user.getId();

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


        if (interestRepository.findBySenderAndReceiver(receiver, sender).isPresent()) {
            throw new DuplicateInterestException(
                    "This user has already sent you an interest");
        }

        Interest interest = new Interest();
        interest.setSender(sender);
        interest.setReceiver(receiver);
        interest.setStatus(InterestStatus.PENDING);

        interestRepository.save(interest);
    }

    // Response to Interest
    @Override
    public void respondInterest(Integer interestId, InterestStatus status) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email);
        Integer receiverId = user.getId();

        Interest interest = interestRepository.findById(interestId)
                .orElseThrow(() -> new InterestNotFoundException("Interest not found"));

        if (!interest.getReceiver().getId().equals(receiverId)) {
            throw new UnauthorizedException("Unauthorized");
        }

        if (interest.getStatus() != InterestStatus.PENDING) {
            throw new DuplicateInterestException("Already responded");
        }

        interest.setStatus(status);
        interestRepository.save(interest);
    }

    //get all sent interest
    @Override
    public List<InterestDTO> getSentInterests() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        User sender = userRepository.findByEmail(email);

        // Fetch interests
        List<Interest> interests = interestRepository.findBySender(sender);

        // Manually convert to DTO list
        List<InterestDTO> dtoList = new ArrayList<>();

        for (Interest interest : interests) {
            InterestDTO dto = InterestMapper.toDTO(interest);
            dtoList.add(dto);
        }

        return dtoList;
    }


    // get Received Interest
    @Override
    public List<InterestDTO> getReceivedInterests() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User receiver = userRepository.findByEmail(email);

        List<Interest> interests = interestRepository.findByReceiver(receiver);

        List<InterestDTO> dtoList = new ArrayList<>();

        for (Interest interest : interests) {
            InterestDTO dto = InterestMapper.toDTO(interest);
            dtoList.add(dto);
        }

        return dtoList;
    }


    // Cancel interest
    @Override
    public void cancelInterest(Integer interestId) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email);
        Integer senderId =user.getId();

        Interest interest = interestRepository.findById(senderId)
                .orElseThrow(() -> new InterestNotFoundException("Interest not found"));


        if (interest.getStatus() != InterestStatus.PENDING) {
            throw new DuplicateInterestException("Cannot cancel after response");
        }

        interestRepository.delete(interest);
    }


}
