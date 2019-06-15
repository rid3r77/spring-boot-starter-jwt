package mx.com.softdom.springframework.security.controller;

import mx.com.softdom.springframework.security.contracts.JwtUser;
import mx.com.softdom.springframework.security.utils.RequestUtils;
import mx.com.softdom.springframework.security.form.LoginForm;
import mx.com.softdom.springframework.security.jwt.JwtProperties;
import mx.com.softdom.springframework.security.jwt.JwtTokenVM;
import mx.com.softdom.springframework.security.jwt.TokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api")
public class JwtAuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final JwtProperties jwtProperties;
    private final RequestUtils requestUtils;

    public JwtAuthController(AuthenticationManager authenticationManager,
                             TokenProvider tokenProvider,
                             JwtProperties jwtProperties,
                             RequestUtils requestUtils) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.jwtProperties = jwtProperties;
        this.requestUtils = requestUtils;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<JwtTokenVM> authenticate(@Valid @RequestBody LoginForm loginForm) {

        // Perform authentication
        final Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginForm.getUsername(), loginForm.getPassword()));

        // Generate a JWT token for the auth user
        final JwtUser user = (JwtUser) auth.getPrincipal();
        final String token = tokenProvider.generateToken(user);

        // Return JWT token as a JwtTokenVM
        return ResponseEntity.ok(new JwtTokenVM(token, jwtProperties.getExpireLength()));

    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/refreshToken", method = RequestMethod.POST)
    public ResponseEntity<JwtTokenVM> refreshToken(HttpServletRequest httpServletRequest) {

        final String currentToken = requestUtils.getToken(httpServletRequest);

        // Return refreshed JWT token as a JwtTokenVM
        return ResponseEntity.ok(new JwtTokenVM(
                tokenProvider.refreshToken(currentToken), jwtProperties.getExpireLength()));

    }

}
