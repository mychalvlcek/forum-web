package cz.cvut.forum.validator;

import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Password validator. This validator is defined using the jsf's @FacesValidator
 * and it is referenced by the validatorId attribute in the h:validator.
 * @author vlcekmi3
 */
@FacesValidator(value = "passwordValidator")
public class PasswordValidator implements Validator {

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        String password = (String) o;
        UIInput confirmComponent = (UIInput) uic.getAttributes().get("confirm");
        String confirm = (String) confirmComponent.getSubmittedValue();
        if (password.length() < 6) {
            confirmComponent.setValid(false);
            throw new ValidatorException(new FacesMessage("PasswordValidatorEx", "Minimálně 6 znaků"));
        }
        if (password == null || password.isEmpty() || confirm == null || confirm.isEmpty()) {
            return;
        }
        if (!password.equals(confirm)) {
            confirmComponent.setValid(false);
            throw new ValidatorException(new FacesMessage("PasswordValidatorEx", "Hesla se neshodují"));
        }
    }

}