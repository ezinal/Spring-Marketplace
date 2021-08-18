package com.tr.obss.jss.jss_marketplace.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tr.obss.jss.jss_marketplace.dto.JwtResponse;
import com.tr.obss.jss.jss_marketplace.dto.LoginRequest;
import com.tr.obss.jss.jss_marketplace.dto.MessageResponse;
import com.tr.obss.jss.jss_marketplace.dto.SignupRequest;
import com.tr.obss.jss.jss_marketplace.model.Role;
import com.tr.obss.jss.jss_marketplace.model.RoleType;
import com.tr.obss.jss.jss_marketplace.model.User;
import com.tr.obss.jss.jss_marketplace.security.JwtUtils;
import com.tr.obss.jss.jss_marketplace.security.MyUserDetails;
import com.tr.obss.jss.jss_marketplace.service.RoleService;
import com.tr.obss.jss.jss_marketplace.service.UserService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final RoleService roleService;

    private final PasswordEncoder encoder;

    private final JwtUtils jwtUtils;

    @PostMapping("/signin")
    public JwtResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
        return JwtResponse
                .builder()
                .token(jwt)
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .roles(roles)
                .build();
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account

        User user = User
                .builder()
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(encoder.encode(signUpRequest.getPassword()))
                .build();
        System.out.println("created user");

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleService.getRole(RoleType.ROLE_USER);
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                if ("admin".equals(role)) {
                    Role adminRole = roleService.getRole(RoleType.ROLE_ADMIN);
                    roles.add(adminRole);
                } else {
                    Role userRole = roleService.getRole(RoleType.ROLE_USER);
                    roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userService.createNewUser(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
