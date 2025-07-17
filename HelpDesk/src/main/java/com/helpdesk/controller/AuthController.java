package com.helpdesk.Controller;

import com.helpdesk.Repository.UserRepo;
import com.helpdesk.Security.auth.AuthenticationService;
import com.helpdesk.Security.request.LoginRequest;
import com.helpdesk.Security.request.RegisterRequest;
import com.helpdesk.Security.response.MassageResponse;
import com.helpdesk.Security.response.RegisterResponse;
import com.helpdesk.Security.response.UserInfoResponse;
import com.helpdesk.Security.services.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;
    private final UserRepo userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
                @RequestBody RegisterRequest request){

        if(userRepository.existsByEmail(request.getEmail())){
            return ResponseEntity
                    .badRequest()
                    .body(new MassageResponse("Error: Email is already taken!"));
        }

        RegisterResponse registerResponse = authenticationService.register(request);

        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, registerResponse.getJwtCookie().toString())
                .body(registerResponse.getUserInfo());
        }

    @PostMapping("/signin")
    public ResponseEntity<UserInfoResponse>  authenticate(@RequestBody LoginRequest request){
        return authenticationService.authenticate(request);
    }

    @GetMapping("/userinfo")
    public ResponseEntity<UserInfoResponse> currentUser(Authentication authentication) {

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(item -> item.getAuthority())
                .toList();

        UserInfoResponse userInfoResponse = new UserInfoResponse(userDetails.getUserId(),userDetails.getFirstName(),userDetails.getLastName(),userDetails.getBatchNo(),userDetails.getDepartment(),userDetails.getEmail(),roles);

        return ResponseEntity.ok().body(userInfoResponse);
    }


    @PostMapping("/signout")
    public ResponseEntity<String> signout(){
        return authenticationService.signOut();
    }
}

