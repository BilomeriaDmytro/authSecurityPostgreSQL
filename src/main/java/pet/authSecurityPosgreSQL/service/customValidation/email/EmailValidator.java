package pet.authSecurityPosgreSQL.service.customValidation.email;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class EmailValidator implements ConstraintValidator<EmailValid, String>{
	private Pattern pattern;
    private Matcher matcher;
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@" + 
    "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";
    
    @Override
    public void initialize(EmailValid constraintAnnotation) {       
    }
    

    @Override
	public boolean isValid(String value, ConstraintValidatorContext context){
		return (validateEmail(value));
	}

    private boolean validateEmail(String email) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);

        if (matcher.matches()) {
            return true;
        }

        log.info("Invalid email - {}", email);
        return false;
    }
}