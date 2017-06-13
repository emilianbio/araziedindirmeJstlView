package validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import forms.Kullanici;

@Component
public class KullaniciValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		Kullanici kullanici = (Kullanici) target;

		if (kullanici.getAdi().equals(null)) {
			errors.rejectValue("adi", "required.adi");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "isimSoyisim", "required.isimSoyisim");
		ValidationUtils.rejectIfEmpty(errors, "adi", "required.adi");
	}

}
   