package gov.cms.madie.models.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gov.cms.madie.models.common.ModelType;
import gov.cms.madie.models.library.CqlLibrary;
import gov.cms.madie.models.measure.Measure;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidLibraryNameValidator  implements ConstraintValidator<ValidLibraryName, Object> {
  @Override
  public boolean isValid(Object object, ConstraintValidatorContext context) {
  	String model = "";
  	String cqlLibraryName = "";

  	if(object instanceof Measure) {
  		Measure measure = (Measure) object;
  		model = measure.getModel();
  		cqlLibraryName = measure.getCqlLibraryName();
  	} else if (object instanceof CqlLibrary) {
  		CqlLibrary cqlLibrary = (CqlLibrary) object;
  		model = cqlLibrary.getModel();
  		cqlLibraryName = cqlLibrary.getCqlLibraryName();
  	}
  	
  	if(ModelType.QDM_5_6.getValue().equalsIgnoreCase(model)) {
  		Pattern pattern = Pattern.compile("^[A-Z][a-zA-Z0-9_]*$");
  		Matcher matcher = pattern.matcher(cqlLibraryName);
  		if(!matcher.matches()) {
  			return false;
  		}
  	}else if(ModelType.QI_CORE.getValue().equalsIgnoreCase(model)) {
  		Pattern pattern = Pattern.compile("^[A-Z][a-zA-Z0-9]*$");
 		 	Matcher matcher = pattern.matcher(cqlLibraryName);
 		 	if(!matcher.matches()) {
 		 		return false;
 		 	}
  	}

  	return true;
  }


}
