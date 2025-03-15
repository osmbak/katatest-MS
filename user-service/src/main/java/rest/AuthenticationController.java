package rest;

import com.kataTest.back.dto.auth.AuthResponseDto;
import com.kataTest.back.dto.auth.LoginRequestDto;
import com.kataTest.back.dto.auth.RegisterRequestDto;
import com.kataTest.back.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
/*    private final AuthentificationService service;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request){
        System.out.println("register web");
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        System.out.println("authenticate web");
        return ResponseEntity.ok(service.authenticate(request));
    }*/
private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto registerRequestDto) {
        //ResponseEntity<AuthResponseDto> response =  ResponseEntity.ok(authService.login(registerRequestDto));
        //String tok = response.getBody().getToken();
        //System.out.print("tokenn is "+tok);
        return ResponseEntity.ok(authService.login(registerRequestDto));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody RegisterRequestDto registerRequestDto) {
        return ResponseEntity.ok(authService.register(registerRequestDto));
    }

    @GetMapping("/test")
    public String register() {
        return "Salut";
    }
}
