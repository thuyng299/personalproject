package com.nonit.personalproject.authenticate;

import com.nonit.personalproject.entity.Employee;
import com.nonit.personalproject.exception.WarehouseException;
import com.nonit.personalproject.repository.EmployeeRepository;
import com.nonit.personalproject.security.jwt.JwtRequest;
import com.nonit.personalproject.security.jwt.JwtResponse;
import com.nonit.personalproject.security.jwt.JwtUtils;
import com.nonit.personalproject.security.service.impl.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController{
    private final AuthenticationManager authenticationManager;
    private  final EmployeeRepository employeeRepository;
    private final JwtUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public ResponseEntity<?> authenticateUser(JwtRequest loginRequest) {
       Employee employee = employeeRepository.findByUsername(loginRequest.getUsername()).get();
       if(!employee.getEmployeeStatus()) {
           throw WarehouseException.badRequest("InactiveEmployee", "You do not have permission to access");
       }
        if (!passwordEncoder.matches(loginRequest.getPassword(), employee.getPassword())){
            throw WarehouseException.badRequest("WrongPassword", "Wrong password. Please try again!");
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getUsername(),
                roles));
    }
}
