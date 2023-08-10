package marcos.Login.LoginFramwork.appuser;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import marcos.Login.LoginFramwork.registration.token.ConfirmationToken;
import marcos.Login.LoginFramwork.registration.token.ConfirmationTokenService;

@Service
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "user with Email %s not found";
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    
    public AppUserService(AppUserRepository appUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder,ConfirmationTokenService confirmationTokenService) {
        this.appUserRepository = appUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.confirmationTokenService = confirmationTokenService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(AppUser appUser) {
        boolean userExist = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
        if (userExist) {
            throw new IllegalStateException("User already exists");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        appUserRepository.save(appUser);
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
        		token,
        		LocalDateTime.now(),
        		LocalDateTime.now().plusMinutes(15),
        		appUser
        		);
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        
        return token;
    }
    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }
    
    public AppUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new IllegalStateException("No user authenticated");
        }
        
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        String email = userDetails.getUsername();
        return appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }
}
