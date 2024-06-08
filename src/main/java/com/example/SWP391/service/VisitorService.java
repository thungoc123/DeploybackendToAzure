package com.example.SWP391.service;


import com.example.SWP391.entity.Account;
import com.example.SWP391.entity.AccountRole;
import com.example.SWP391.entity.Role;
import com.example.SWP391.entity.Visitor;
import com.example.SWP391.payload.Request.VisitorSignUp;
import com.example.SWP391.payload.Request.VisitorSignUp;
import com.example.SWP391.repository.AccountRepository;
import com.example.SWP391.repository.AccountRoleRepository;
import com.example.SWP391.repository.RoleRepository;
import com.example.SWP391.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VisitorService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private VisitorRepository visitorRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AccountRoleRepository accountRoleRepository;


//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = Exception.class)
    public void signUpVisitor(VisitorSignUp visitorSignUp) throws Exception {
        if (accountRepository.existsByEmail(visitorSignUp.getEmail())) {
            throw new Exception("Email already exists");
        }

        if (!visitorSignUp.getPassword().equals(visitorSignUp.getConfirmPassword())) {
            throw new Exception("Passwords do not match");
        }

        // Tạo mới một Account
        Account account = new Account();
        account.setEmail(visitorSignUp.getEmail());
        account.setPassword(visitorSignUp.getPassword());
//        // Mã hóa mật khẩu trước khi lưu
//        account.setPassword(passwordEncoder.encode(visitorSignUp.getPassword()));
        // Lưu Account vào cơ sở dữ liệu
        accountRepository.save(account);

        // Tạo mới một Visitor và gán thông tin
        Visitor visitor = new Visitor();
        visitor.setInformation(visitorSignUp.getInformation());
        visitor.setAccount(account);

        // Lưu Visitor vào cơ sở dữ liệu
        visitorRepository.save(visitor);

        // Tạo một vai trò "VISITOR" nếu chưa tồn tại
        Role role = roleRepository.findByRoleName("VISITOR");
        if (role == null) {
            role = new Role();
            role.setRoleName("VISITOR");
            role = roleRepository.save(role);
        }

        // Tạo một bản ghi AccountRole và gán Account và Role
        AccountRole accountRole = new AccountRole();
        accountRole.setAccount(account);
        accountRole.setRole(role);

        // Lưu bản ghi AccountRole vào cơ sở dữ liệu
        accountRoleRepository.save(accountRole);
    }
}
