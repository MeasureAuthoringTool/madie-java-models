package gov.cms.madie.models.validators;

import java.util.List;
import java.util.stream.Stream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

import gov.cms.madie.models.measure.Group;
import gov.cms.madie.models.measure.Population;


public class GroupPopulationIdValidator implements ConstraintValidator<ValidGroupPopulationId, Group> {
	@Override
  public boolean isValid(Group group, ConstraintValidatorContext context) {
    if (group == null) {
      return true;
    }

    List<Population> populations = group.getPopulations();
    Stream<Population> pops = populations.stream().filter(p -> !StringUtils.hasText(p.getId()));

    return pops.count()>0?false:true;    
  }
}
