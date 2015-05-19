package cz.cvut.forum.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Email validator. This validator is defined using the jsf's @FacesValidator
 * and it is referenced by the validatorId attribute in the h:validator.
 * @author vlcekmi3
 */
@FacesValidator(value = "emailValidator")
public class EmailValidator implements Validator {

    private Pattern pattern;
    private Matcher matcher;
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        String input = (String) o;
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(input);
        if (matcher.matches() == false)
            throw new ValidatorException(new FacesMessage("EmailValidatorEx", "Email is not valid"));
    }

}