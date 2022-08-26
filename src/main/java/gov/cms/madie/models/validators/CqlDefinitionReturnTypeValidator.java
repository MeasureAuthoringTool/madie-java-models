package gov.cms.madie.models.validators;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import gov.cms.madie.models.measure.Group;
import gov.cms.madie.models.measure.Measure;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.CaseUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Slf4j
public class CqlDefinitionReturnTypeValidator implements
  ConstraintValidator<ValidCqlDefinitionReturnType, Measure> {

  @Override
  public boolean isValid(Measure measure, ConstraintValidatorContext constraintValidatorContext) {
    // for measure with no cql
    if (StringUtils.isEmpty(measure.getElmJson())) {
      return true;
    }

    boolean isValid = false;
    try {
      Map<String, String> cqlDefinitionReturnTypes = getCqlDefinitionReturnTypes(measure.getElmJson());
      List<Group> groups = measure.getGroups();
      isValid = groups.stream()
        .allMatch(
          group -> group.getPopulations()
            .stream().allMatch(population -> {
              if (StringUtils.isNotBlank(population.getDefinition())) {
                String returnType = cqlDefinitionReturnTypes.get(population.getDefinition());
                String populationBasis = CaseUtils.toCamelCase(
                  group.getPopulationBasis(), true, ' ');
                return StringUtils.equals(returnType, populationBasis);
              } else {
                return true;
              }
            }));
    } catch (JsonProcessingException ex) {
      log.error("An error occurred while validating population " +
        "definition return types for measure {}", measure.getId(), ex);
    }
    return isValid;
  }

  /**
   * This method generates the map of cql definitions & their return types.
   * @param elmJson
   * @return
   * @throws JsonProcessingException
   */
  private Map<String, String> getCqlDefinitionReturnTypes(String elmJson) throws JsonProcessingException {
    if (StringUtils.isNotBlank(elmJson)) {
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode rootNode = objectMapper.readTree(elmJson);
      ArrayNode allDefinitions = (ArrayNode) rootNode.get("library").get("statements").get("def");
      Iterator<JsonNode> nodeIterator = allDefinitions.iterator();
      Map<String, String> returnTypes = new HashMap<>();
      while (nodeIterator.hasNext()) {
        JsonNode node = nodeIterator.next();
        if (node.get("resultTypeName") != null) {
          String dataType = node.get("resultTypeName").asText();
          returnTypes.put(node.get("name").asText(),
            dataType.split("}")[1]);
        } else if (node.get("resultTypeSpecifier") != null) {
          String type = node.get("resultTypeSpecifier")
            .get("elementType").get("type").asText();
          if ("NamedTypeSpecifier".equals(type)) {
            String dataType = node.get("resultTypeSpecifier")
              .get("elementType").get("name").asText();
            returnTypes.put(node.get("name").asText(),
              dataType.split("}")[1]);
          } else {
            returnTypes.put(node.get("name").asText(), "NA");
          }
        } else {
          returnTypes.put(node.get("name").asText(), "NA");
        }
      }
      return returnTypes;
    }
    return null;
  }
}
