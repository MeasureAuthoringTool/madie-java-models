package gov.cms.madie.models.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gov.cms.madie.models.common.ModelType;
import gov.cms.madie.models.measure.Measure;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidLibraryNameValidator  implements ConstraintValidator<ValidLibraryName, Measure> {
  @Override
  public boolean isValid(Measure measure, ConstraintValidatorContext context) {
  	if(ModelType.QDM_5_6.getValue().equalsIgnoreCase(measure.getModel())) {
  		Pattern pattern = Pattern.compile("^[A-Z][a-zA-Z0-9_]*$");
  		Matcher matcher = pattern.matcher(measure.getCqlLibraryName());
  		if(!matcher.matches()) {
  			return false;
  		}
  	}else if(ModelType.QI_CORE.getValue().equalsIgnoreCase(measure.getModel())) {
  		Pattern pattern = Pattern.compile("^[A-Z][a-zA-Z0-9]*$");
 		 	Matcher matcher = pattern.matcher(measure.getCqlLibraryName());
 		 	if(!matcher.matches()) {
 		 		return false;
 		 	}
  	}

  	return true;
  }

}
