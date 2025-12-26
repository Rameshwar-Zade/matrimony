package com.spring.jwt.service;

import com.spring.jwt.dto.EducationAndProfessionDto;

public interface EducationAndProfessionService {
    EducationAndProfessionDto create(EducationAndProfessionDto dto);

    EducationAndProfessionDto update(Integer id, EducationAndProfessionDto dto);

//    EducationAndProfessionDto getById(Integer id);

    void delete(Integer id);

    EducationAndProfessionDto partialUpdate(Integer id, EducationAndProfessionDto dto);

    EducationAndProfessionDto getByLoggedInUser();

}


