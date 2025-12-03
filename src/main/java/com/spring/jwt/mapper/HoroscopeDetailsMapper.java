package com.spring.jwt.mapper;

import com.spring.jwt.dto.HoroscopeDetailsRequestDTO;
import com.spring.jwt.dto.HoroscopeDetailsResponseDTO;
import com.spring.jwt.entity.HoroscopeDetails;

public class HoroscopeDetailsMapper {


    public static HoroscopeDetails toEntity(HoroscopeDetailsRequestDTO dto) {
        HoroscopeDetails entity = new HoroscopeDetails();

        entity.setDob(java.sql.Date.valueOf(dto.getDob()));
        entity.setTime(dto.getTime());
        entity.setBirthPlace(dto.getBirthPlace());
        entity.setRashi(dto.getRashi());
        entity.setNakshatra(dto.getNakshatra());
        entity.setCharan(dto.getCharan());
        entity.setNadi(dto.getNadi());
        entity.setGan(dto.getGan());
        entity.setMangal(dto.getMangal());
        entity.setGotra(dto.getGotra());
        entity.setDevak(dto.getDevak());
        entity.setStatus1(dto.getStatus1());
        entity.setUserId(dto.getUserId());

        return entity;
    }


    public static void updateEntity(HoroscopeDetails entity, HoroscopeDetailsRequestDTO dto) {

        entity.setDob(java.sql.Date.valueOf(dto.getDob()));
        entity.setTime(dto.getTime());
        entity.setBirthPlace(dto.getBirthPlace());
        entity.setRashi(dto.getRashi());
        entity.setNakshatra(dto.getNakshatra());
        entity.setCharan(dto.getCharan());
        entity.setNadi(dto.getNadi());
        entity.setGan(dto.getGan());
        entity.setMangal(dto.getMangal());
        entity.setGotra(dto.getGotra());
        entity.setDevak(dto.getDevak());
        entity.setStatus1(dto.getStatus1());
        entity.setUserId(dto.getUserId());
    }


    public static void patchEntity(HoroscopeDetails entity, HoroscopeDetailsRequestDTO dto) {

        if (dto.getDob() != null)
            entity.setDob(java.sql.Date.valueOf(dto.getDob()));
        if (dto.getTime() != null)
            entity.setTime(dto.getTime());
        if (dto.getBirthPlace() != null)
            entity.setBirthPlace(dto.getBirthPlace());
        if (dto.getRashi() != null)
            entity.setRashi(dto.getRashi());
        if (dto.getNakshatra() != null)
            entity.setNakshatra(dto.getNakshatra());
        if (dto.getCharan() != null)
            entity.setCharan(dto.getCharan());
        if (dto.getNadi() != null)
            entity.setNadi(dto.getNadi());
        if (dto.getGan() != null)
            entity.setGan(dto.getGan());
        if (dto.getMangal() != null)
            entity.setMangal(dto.getMangal());
        if (dto.getGotra() != null)
            entity.setGotra(dto.getGotra());
        if (dto.getDevak() != null)
            entity.setDevak(dto.getDevak());
        if (dto.getStatus1() != null)
            entity.setStatus1(dto.getStatus1());
        if (dto.getUserId() != null)
            entity.setUserId(dto.getUserId());
    }


    public static HoroscopeDetailsResponseDTO toDTO(HoroscopeDetails entity) {
        HoroscopeDetailsResponseDTO dto = new HoroscopeDetailsResponseDTO();

        dto.setHoroscopeDetailsId(entity.getHoroscopeDetailsId());
        dto.setDob(entity.getDob().toString());
        dto.setTime(entity.getTime());
        dto.setBirthPlace(entity.getBirthPlace());
        dto.setRashi(entity.getRashi());
        dto.setNakshatra(entity.getNakshatra());
        dto.setCharan(entity.getCharan());
        dto.setNadi(entity.getNadi());
        dto.setGan(entity.getGan());
        dto.setMangal(entity.getMangal());
        dto.setGotra(entity.getGotra());
        dto.setDevak(entity.getDevak());
        dto.setStatus1(entity.getStatus1());
        dto.setUserId(entity.getUserId());

        return dto;
    }
}
