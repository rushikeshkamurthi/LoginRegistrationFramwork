package marcos.Login.LoginFramwork.registration;

import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class EmailValidator implements Predicate<String> {
    
    @Override
    public boolean test(String email) {
        // Implement your email validation logic here
        // For example, you can use regular expressions to validate the email format
        // Return true if the email is valid, otherwise return false
        return true; // or false based on the validation
    }
}
