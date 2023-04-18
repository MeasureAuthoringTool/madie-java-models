package gov.cms.madie.models.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

import gov.cms.madie.models.common.ModelType;
import gov.cms.madie.models.measure.Measure;
import gov.cms.madie.models.measure.MeasureScoring;


public class QDMMeasureScoringValidator implements ConstraintValidator<ValidQDMMeasureScoring, Measure> {

	@Override
	public boolean isValid(Measure measure, ConstraintValidatorContext context) {
		if(measure!=null && StringUtils.hasLength(measure.getModel()) 
				&&  measure.getModel().equalsIgnoreCase(ModelType.QDM_5_6.toString()) 
				) {
			if(!StringUtils.hasLength(measure.getScoring())) {
				return false;
			} else {
				MeasureScoring scoring = MeasureScoring.valueOfText(measure.getScoring());
				if(scoring==null) {
					return false;
				}
			}
		} 
		else {
			return true;
		}
		return true;
	}

}
