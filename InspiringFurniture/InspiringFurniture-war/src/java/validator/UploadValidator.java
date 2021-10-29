/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

/**
 *
 * @author DuyAnh
 */
@FacesValidator("UploadValidator")
public class UploadValidator implements Validator {

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object value) throws ValidatorException {
        Part p = (Part) value;
        
        // Images must png|jpg
        if (!p.getContentType().equals("image/png")
                && !p.getContentType().equals("image/jpeg")) {

            FacesMessage msg = new FacesMessage("Image need: png/jpg");
            throw new ValidatorException(msg);
        }

        // Size of Image less than 5MB
        if (p.getSize() > 5242880) {
            FacesMessage msg = new FacesMessage("Image size <= 5MB");
            throw new ValidatorException(msg);
        }
    }
}
