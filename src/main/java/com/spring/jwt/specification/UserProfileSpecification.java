package com.spring.jwt.specification;

import com.spring.jwt.dto.BrideFilterDto;
import com.spring.jwt.entity.UserProfile;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;


public class UserProfileSpecification {

    public static Specification<UserProfile> filterBy(BrideFilterDto dto){
        return(root, query, cb)-> {
            Predicate predicate = cb.conjunction();

            if (dto.getGender() != null && !dto.getGender().isEmpty()) {
                predicate = cb.and(predicate, cb.equal(root.get("gender"), dto.getGender()));
            }

            if (dto.getCurrentCity() != null && !dto.getCurrentCity().isEmpty()) {
                predicate = cb.and(predicate, cb.equal(root.get("currentCity"), dto.getCurrentCity()));
            }

            if (dto.getMaritalStatus() != null && !dto.getMaritalStatus().isEmpty()) {
                predicate = cb.and(predicate, cb.equal(root.get("maritalStatus"), dto.getMaritalStatus()));
            }

            if (dto.getCountry() != null && !dto.getCountry().isEmpty()) {
                predicate = cb.and(predicate, cb.equal(root.get("country"), dto.getCountry()));
            }

            return predicate;

        };


    }

}
