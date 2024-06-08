package com.example.SWP391.model.mapper;

import com.example.SWP391.entity.Sponsor;
import com.example.SWP391.model.dto.SponsorDto;

public class SponsorMapper {
    public static SponsorDto toSponsorDto(Sponsor sponsor) {
        SponsorDto sponsorDto = new SponsorDto();
        sponsorDto.setId(sponsor.getId());
        sponsorDto.setInformation(sponsor.getInformation());
        sponsorDto.setCompanyName(sponsor.getCompanyName());
        sponsorDto.setCompanyID(sponsor.getCompanyID());
        sponsorDto.setFptStaffEmail(sponsor.getFptStaffEmail());
        return sponsorDto;
    }
}
