package com.spring.jwt.CompleteProfile;

import com.spring.jwt.entity.*;

public class CompleteProfileMapper {

    private CompleteProfileMapper() {}


    public static PublicProfileDTO toPublicProfileDTO(
            UserProfile userProfile,
            EducationAndProfession education,
            HoroscopeDetails horoscope
    ) {
        PublicProfileDTO dto = new PublicProfileDTO();

        dto.setUserId(userProfile.getUser().getId());
        dto.setLastName(userProfile.getLastName());
        dto.setGender(userProfile.getGender());
        dto.setHeight(userProfile.getHeight());
        dto.setDistrict(userProfile.getDistrict());
        dto.setCaste(userProfile.getCaste());
        dto.setDateOfBirth(horoscope.getDob());
        if (education != null) {
            dto.setEducation(education.getEducation());
            dto.setOccupation(education.getOccupation());
        }


        return dto;
    }


    public static FullProfileDTO toFullProfileDTO(
            UserProfile up,
            ContactDetails cd,
            HoroscopeDetails hd,
            PartnerExpectation pe,
            EducationAndProfession ep,
            FamilyBackground fb
    ) {

        FullProfileDTO dto = new FullProfileDTO();

        /* ================= USER PROFILE ================= */
        if (up != null) {
            dto.setFullName(
                    up.getFirstName() + " " +
                            (up.getMiddleName() != null ? up.getMiddleName() + " " : "") +
                            up.getLastName()
            );
            dto.setAge(up.getAge());
            dto.setHeight(up.getHeight());
            dto.setGender(up.getGender());
            dto.setCity(up.getDistrict());
            dto.setCaste(up.getCaste());
            dto.setWeight(up.getWeight());
            dto.setBloodGroup(up.getBloodGroup());
            dto.setSpectacle(up.getSpectacle());
            dto.setComplexion(up.getComplexion());
            dto.setPhysicallyChallenged(up.getPhysicallyChallenged());
            dto.setDiet(up.getDiet());
            dto.setLens(up.getLens());
        }

        /* ================= EDUCATION & PROFESSION ================= */
        if (ep != null) {
            dto.setEducation(ep.getEducation());
            dto.setDegree(ep.getEducationDetails());
            dto.setOccupation(ep.getOccupation());
            dto.setOccupationDetails(ep.getOccupationDetails());
            dto.setIncomePerYear(ep.getIncomePerYear());
        }

        /* ================= HOROSCOPE ================= */
        if (hd != null) {
            dto.setDob(hd.getDob());
            dto.setTime(hd.getTime());
            dto.setBirthPlace(hd.getBirthPlace());
            dto.setRashi(hd.getRashi());
            dto.setNakshatra(hd.getNakshatra());
            dto.setCharan(hd.getCharan());
            dto.setNadi(hd.getNadi());
            dto.setGan(hd.getGan());
            dto.setMangal(hd.getMangal());
            dto.setGotra(hd.getGotra());
            dto.setDevak(hd.getDevak());
        }

        /* ================= FAMILY BACKGROUND ================= */
        if (fb != null) {
            dto.setFather(fb.getFatherName());
            dto.setMother(fb.getMotherName());
            dto.setBrothers(fb.getBrother());
            dto.setMarriedBrothers(fb.getMarriedBrothers());
            dto.setSisters(fb.getSisters());
            dto.setMarriedSisters(fb.getMarriedSisters());
            dto.setParentResiding(fb.getParentResiding());
            dto.setParentOccupation("Father:"+fb.getFatherOccupation()+",Mother: "+fb.getMotherOccupation());
            dto.setFamilyWealth("1000000");
            dto.setMamaSurname(fb.getMamaSurname());
            dto.setMamaPlace(fb.getMamaPlace());
            dto.setRelativeSurnames(fb.getRelativeSurnames());
        }

        /* ================= PARTNER EXPECTATION ================= */
        if (pe != null) {
            dto.setPreferredCity("pe.getPreferredCity()");
            dto.setPreferredEducation(pe.getEducation());
            dto.setPreferredOccupation("Software Developer");
            dto.setExpectedIncome(1000000);
            dto.setMangalAcceptable(true);
            dto.setExpectedCaste(pe.getCaste());
            dto.setExpectedHeight(Double.valueOf(pe.getHeightFeet()+"."+ pe.getHeightFeet()));
        }

        /* ================= CONTACT DETAILS ================= */
        if (cd != null) {
            dto.setFullAddress(cd.getFullAddress());
            dto.setPinCode(cd.getPinCode());
            dto.setMobileNumber(cd.getMobileNumber());
            dto.setAlternateNumber(cd.getAlternateNumber());
        }

        return dto;
    }


    public static BioDataDisplayDTO toBioDataDTO(
            UserProfile up,
            EducationAndProfession ep,
            HoroscopeDetails hd,
            FamilyBackground fb,
            PartnerExpectation pe
    ) {
        BioDataDisplayDTO dto = new BioDataDisplayDTO();

        // UserProfile
        dto.setFullName(up.getFirstName()+" "+up.getLastName());
        dto.setAge(up.getAge());
        dto.setHeight(up.getHeight());
        dto.setGender(up.getGender());
        dto.setCity(up.getDistrict());
        dto.setCaste(up.getCaste());
        dto.setWeight(up.getWeight());
        dto.setBloodGroup(up.getBloodGroup());
        dto.setSpectacle(up.getSpectacle());
        dto.setComplexion(up.getComplexion());
        dto.setPhysicallyChallenged(up.getPhysicallyChallenged());
        dto.setDiet(up.getDiet());
        dto.setLens(up.getLens());

        // Education
        if (ep != null) {
            dto.setEducation(ep.getEducation());
            dto.setDegree(ep.getEducationDetails());
            dto.setOccupation(ep.getOccupation());
            dto.setOccupationDetails(ep.getOccupationDetails());
            dto.setIncomePerYear(ep.getIncomePerYear());
        }

        // Horoscope
        if (hd != null) {
            dto.setDob(hd.getDob());
            dto.setTime(hd.getTime());
            dto.setBirthPlace(hd.getBirthPlace());
            dto.setRashi(hd.getRashi());
            dto.setNakshatra(hd.getNakshatra());
            dto.setCharan(hd.getCharan());
            dto.setNadi(hd.getNadi());
            dto.setGan(hd.getGan());
            dto.setMangal(hd.getMangal());
            dto.setGotra(hd.getGotra());
            dto.setDevak(hd.getDevak());
        }

        // Family
        if (fb != null) {
            dto.setFather(fb.getFatherName());
            dto.setMother(fb.getMotherName());
            dto.setBrothers(fb.getMarriedBrothers());
            dto.setMarriedBrothers(fb.getMarriedBrothers());
            dto.setSisters(fb.getSisters());
            dto.setMarriedSisters(fb.getMarriedSisters());
            dto.setParentResiding(fb.getParentResiding());
            dto.setParentOccupation("Father: "+fb.getFatherOccupation()+","+"mother: "+fb.getMotherOccupation());
            dto.setFamilyWealth("Not declered in entity");
            dto.setMamaSurname(fb.getMamaSurname());
            dto.setMamaPlace(fb.getMamaPlace());
            dto.setRelativeSurnames(fb.getRelativeSurnames());
        }

        // Partner Expectations
        if (pe != null) {
            dto.setPreferredCity("pe.getPreferredCity()");
            dto.setPreferredEducation(pe.getEducation());
            dto.setPreferredOccupation("pe.getOccupation()");
            dto.setExpectedIncome(100000);
            dto.setMangalAcceptable(true);
            dto.setExpectedCaste(pe.getCaste());
            dto.setExpectedHeight(Double.valueOf(pe.getHeightFeet()+"."+ pe.getHeightInches()));
        }

        return dto;
    }
}

