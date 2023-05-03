package gov.cms.madie.models.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.util.CollectionUtils;

import gov.cms.madie.models.measure.Group;
import gov.cms.madie.models.measure.QdmMeasure;

public class QDMGroupScoringValidator implements ConstraintValidator<ValidQDMGroupScoring, QdmMeasure> {
  @Override
  public boolean isValid(QdmMeasure measure, ConstraintValidatorContext context) {
  	if(measure==null || CollectionUtils.isEmpty(measure.getGroups())) {
			return true;
		}
  	if(measure.getScoring()!=null && !measure.getScoring().equalsIgnoreCase(measure.getGroups().get(0).getScoring())) {
  		return false;
  	}
  	
  	if (!measure.getGroups().stream().map(Group::getScoring).allMatch(scoring -> measure.getGroups().get(0).getScoring().equalsIgnoreCase(scoring))) {
  		return false;
  	}
  	return true;
  }

}
