package lt.insoft.gallery.ui.validator;

import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.Validator;

public class NotEmptyValidator implements Validator {
    @Override
    public void validate(ValidationContext validationContext) {
        String input = (String) validationContext.getBindContext().getValidatorArg("notEmpty");
        if (validationContext.getProperty().getValue() instanceof String) {
            String value = (String) validationContext.getProperty().getValue();
            if (value.length() < input.length()) {
                validationContext.setInvalid();
            }
        } else {
            validationContext.setInvalid();
        }
    }
}
