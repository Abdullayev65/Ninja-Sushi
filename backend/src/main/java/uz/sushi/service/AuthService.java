package uz.sushi.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.sushi.entity.Role;
import uz.sushi.entity.User;
import uz.sushi.exceptions.RestException;
import uz.sushi.payload.ApiResult;
import uz.sushi.payload.SignDTO;
import uz.sushi.repository.UserRepository;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Role roleUser;
    private final AuthenticationManager authenticationManager;


    @Value("${jwt.access.expiration-time}")
    private Long ACCESS_TOKEN_EXPIRATION_TIME;

    @Value("${jwt.access.key}")
    private String ACCESS_TOKEN_KEY;

    public ApiResult<String> signUp(SignDTO signDTO) {
        if (userRepository.existsByEmail(signDTO.getEmail()))
            throw RestException.restThrow(
                    "EMAIL_ALREADY_EXIST",
                    HttpStatus.CONFLICT);
        User user = new User(
                signDTO.getEmail(),
                passwordEncoder.encode(signDTO.getPassword()));
        user.setRole(roleUser);

        userRepository.save(user);
        return ApiResult.successResponse(null, generateToken(user.getEmail()));
    }

    public ApiResult<String> signIn(SignDTO signDTO) {

        // TODO Find why we need this
/*        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signDTO.getEmail(),
                        signDTO.getPassword()
                ));

        User user = (User) authentication.getPrincipal();*/

        User user = new User(signDTO.getEmail(),signDTO.getPassword());
        return ApiResult.successResponse(
                "SUCCESSFULLY_TOKEN_GENERATED",
                generateToken(user.getEmail()));
    }

    public String generateToken(String email) {
        Date expiredDate = new Date(new Date().getTime() + ACCESS_TOKEN_EXPIRATION_TIME);

        return "Bearer ".concat(Jwts
                .builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS512, ACCESS_TOKEN_KEY)
                .compact());
    }


}
