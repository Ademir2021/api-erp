package br.com.centroinfo.api.controllers.authenticationcontroller;

import br.com.centroinfo.api.dtos.userDTO.AuthenticationDTO;
import br.com.centroinfo.api.dtos.userDTO.LoginResponseDTO;
import br.com.centroinfo.api.dtos.userDTO.RegisterDTO;
import br.com.centroinfo.api.dtos.userDTO.UpdateUserDTO;
import br.com.centroinfo.api.entities.users.User;
import br.com.centroinfo.api.providers.security.TokenService;
import br.com.centroinfo.api.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;

    // @PostMapping("/login")
    // public ResponseEntity<?> login(@RequestBody @Validated AuthenticationDTO
    // authenticationDTO) {
    // var usernamePassword = new
    // UsernamePasswordAuthenticationToken(authenticationDTO.login(),
    // authenticationDTO.password());
    // var auth = this.authenticationManager.authenticate(usernamePassword);
    // var token = tokenService.generateToken((User) auth.getPrincipal());
    // return ResponseEntity.ok(new LoginResponseDTO(token));
    // }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Validated AuthenticationDTO authenticationDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.login(),
                authenticationDTO.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        User user = (User) auth.getPrincipal();
        var token = tokenService.generateToken(user);
        // Coletando todos os papéis
        List<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new LoginResponseDTO(user.getId(), token, user.getLogin(), roles));
    }

    // @PostMapping("/register")
    // public ResponseEntity<?> register(@RequestBody @Validated RegisterDTO authenticationDTO) {
    //     if (this.repository.findByLogin(authenticationDTO.login()) != null)
    //         // throw new ResourceNotFoundException("Usuário já existe");
    //     return ResponseEntity.badRequest().build();
    //     String encryptedPassword = new BCryptPasswordEncoder().encode(authenticationDTO.password());
    //     User newUser = new User(authenticationDTO.login(), encryptedPassword, authenticationDTO.role());
    //     this.repository.save(newUser);
    //     return ResponseEntity.ok().build();
    // }

    @PostMapping("/register")
public ResponseEntity<?> register(@RequestBody @Validated RegisterDTO authenticationDTO) {
    if (this.repository.findByLogin(authenticationDTO.login()) != null) {
        return ResponseEntity.badRequest().build();
    }

    String encryptedPassword = new BCryptPasswordEncoder()
            .encode(authenticationDTO.password());

    User newUser = new User(
            authenticationDTO.login(),
            encryptedPassword,
            authenticationDTO.role()
    );

    this.repository.save(newUser);

    return ResponseEntity.ok().build();
}

    @GetMapping("/users")
    public List<User> list() {
        return repository.findAll();
    }

    @PutMapping("/register")
    public ResponseEntity<?> update(@RequestBody @Validated UpdateUserDTO updateUserDTO) {
        if (this.repository.findByLogin(updateUserDTO.getLogin()) != null)
            return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(updateUserDTO.getPassword());
        User newUser = new User(updateUserDTO.getId(), updateUserDTO.getLogin(), encryptedPassword,
                updateUserDTO.getRole());
        this.repository.save(newUser);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/register/{id}")
    public List<User> delete(@PathVariable("id") Long id) {
        this.repository.deleteById(id);
        return repository.findAll();
    }

}
