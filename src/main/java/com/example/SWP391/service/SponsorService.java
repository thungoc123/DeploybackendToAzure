package com.example.SWP391.service;

import com.example.SWP391.entity.Account;
import com.example.SWP391.entity.AccountRole;
import com.example.SWP391.entity.Role;
import com.example.SWP391.entity.Sponsor;
import com.example.SWP391.Payload.Request.SponsorSignUp;
import com.example.SWP391.model.dto.SponsorDto;
import com.example.SWP391.model.mapper.SponsorMapper;
import com.example.SWP391.repository.AccountRepository;
import com.example.SWP391.repository.AccountRoleRepository;
import com.example.SWP391.repository.RoleRepository;
import com.example.SWP391.repository.SponsorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SponsorService {
    @Autowired
    private SponsorRepository sponsorRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AccountRoleRepository accountroleRepository;

    @Transactional(rollbackFor = Exception.class)
    public void signUpSponsor(SponsorSignUp sponsorSignUp) throws Exception {
        if (accountRepository.existsByEmail(sponsorSignUp.getEmail())) {
            throw new Exception("Email already exists");
        }

        if (!sponsorSignUp.getPassword().equals(sponsorSignUp.getConfirm_password())) {
            throw new Exception("Passwords do not match");
        }
        Account account = new Account();
        account.setEmail(sponsorSignUp.getEmail());
        account.setPassword(sponsorSignUp.getPassword());
        accountRepository.save(account);

        Sponsor sponsor = new Sponsor();
        sponsor.setCompanyName(sponsorSignUp.getCompany_name());
        sponsor.setCompanyID(sponsorSignUp.getCompany_id());
        sponsor.setFptStaffEmail(sponsorSignUp.getFpt_staff_email());
        sponsor.setInformation(sponsorSignUp.getInformation());
        sponsor.setAccount(account);
        sponsorRepository.save(sponsor);

        Role role = roleRepository.findByRoleName("SPONSOR");
        if (role == null) {
            role = new Role();
            role.setRoleName("SPONSOR");
            role = roleRepository.save(role);
        }

        AccountRole accountRole = new AccountRole();
        accountRole.setAccount(account);
        accountRole.setRole(role);

        // Lưu bản ghi AccountRole vào cơ sở dữ liệu
        accountroleRepository.save(accountRole);
    }

    public List<SponsorDto> getAllSponsors() {
        List<Sponsor> sponsors = sponsorRepository.findAll();
        return sponsors.stream()
                .map(SponsorMapper::toSponsorDto)
                .collect(Collectors.toList());
    }

    public Optional<SponsorDto> getSponsorById(Long id) {
        Optional<Sponsor> sponsorOptional = sponsorRepository.findById(id);
        if (sponsorOptional.isPresent()) {
            Sponsor sponsor = sponsorOptional.get();
            return Optional.of(SponsorMapper.toSponsorDto(sponsor));
        } else {
            return Optional.empty();
        }
    }
}
