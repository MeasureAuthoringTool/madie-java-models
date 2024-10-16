package gov.cms.madie.models.cqm.datacriteria.basetypes;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import gov.cms.madie.models.cqm.datacriteria.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "_type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = AdverseEvent.class, name = "QDM::AdverseEvent"),
  @JsonSubTypes.Type(value = AllergyIntolerance.class, name = "QDM::AllergyIntolerance"),
  @JsonSubTypes.Type(value = AssessmentOrder.class, name = "QDM::AssessmentOrder"),
  @JsonSubTypes.Type(value = AssessmentPerformed.class, name = "QDM::AssessmentPerformed"),
  @JsonSubTypes.Type(value = AssessmentRecommended.class, name = "QDM::AssessmentRecommended"),
  @JsonSubTypes.Type(value = CareGoal.class, name = "QDM::CareGoal"),
  @JsonSubTypes.Type(value = CommunicationPerformed.class, name = "QDM::CommunicationPerformed"),
  @JsonSubTypes.Type(value = DeviceOrder.class, name = "QDM::DeviceOrder"),
  @JsonSubTypes.Type(value = DeviceRecommended.class, name = "QDM::DeviceRecommended"),
  @JsonSubTypes.Type(value = Diagnosis.class, name = "QDM::Diagnosis"),
  @JsonSubTypes.Type(value = DiagnosticStudyOrder.class, name = "QDM::DiagnosticStudyOrder"),
  @JsonSubTypes.Type(
      value = DiagnosticStudyPerformed.class,
      name = "QDM::DiagnosticStudyPerformed"),
  @JsonSubTypes.Type(
      value = DiagnosticStudyRecommended.class,
      name = "QDM::DiagnosticStudyRecommended"),
  @JsonSubTypes.Type(value = EncounterOrder.class, name = "QDM::EncounterOrder"),
  @JsonSubTypes.Type(value = EncounterPerformed.class, name = "QDM::EncounterPerformed"),
  @JsonSubTypes.Type(value = EncounterRecommended.class, name = "QDM::EncounterRecommended"),
  @JsonSubTypes.Type(value = FamilyHistory.class, name = "QDM::FamilyHistory"),
  @JsonSubTypes.Type(
      value = ImmunizationAdministered.class,
      name = "QDM::ImmunizationAdministered"),
  @JsonSubTypes.Type(value = ImmunizationOrder.class, name = "QDM::ImmunizationOrder"),
  @JsonSubTypes.Type(value = InterventionOrder.class, name = "QDM::InterventionOrder"),
  @JsonSubTypes.Type(value = InterventionPerformed.class, name = "QDM::InterventionPerformed"),
  @JsonSubTypes.Type(value = InterventionRecommended.class, name = "QDM::InterventionRecommended"),
  @JsonSubTypes.Type(value = LaboratoryTestOrder.class, name = "QDM::LaboratoryTestOrder"),
  @JsonSubTypes.Type(value = LaboratoryTestPerformed.class, name = "QDM::LaboratoryTestPerformed"),
  @JsonSubTypes.Type(
      value = LaboratoryTestRecommended.class,
      name = "QDM::LaboratoryTestRecommended"),
  @JsonSubTypes.Type(value = MedicationActive.class, name = "QDM::MedicationActive"),
  @JsonSubTypes.Type(value = MedicationAdministered.class, name = "QDM::MedicationAdministered"),
  @JsonSubTypes.Type(value = MedicationDischarge.class, name = "QDM::MedicationDischarge"),
  @JsonSubTypes.Type(value = MedicationDispensed.class, name = "QDM::MedicationDispensed"),
  @JsonSubTypes.Type(value = MedicationOrder.class, name = "QDM::MedicationOrder"),
  @JsonSubTypes.Type(value = Participation.class, name = "QDM::Participation"),
  @JsonSubTypes.Type(value = PatientCareExperience.class, name = "QDM::PatientCareExperience"),
  @JsonSubTypes.Type(value = PatientCharacteristic.class, name = "QDM::PatientCharacteristic"),
  @JsonSubTypes.Type(
      value = PatientCharacteristicBirthdate.class,
      name = "QDM::PatientCharacteristicBirthdate"),
  @JsonSubTypes.Type(
      value = PatientCharacteristicClinicalTrialParticipant.class,
      name = "QDM::PatientCharacteristicClinicalTrialParticipant"),
  @JsonSubTypes.Type(
      value = PatientCharacteristicEthnicity.class,
      name = "QDM::PatientCharacteristicEthnicity"),
  @JsonSubTypes.Type(
      value = PatientCharacteristicExpired.class,
      name = "QDM::PatientCharacteristicExpired"),
  @JsonSubTypes.Type(
      value = PatientCharacteristicPayer.class,
      name = "QDM::PatientCharacteristicPayer"),
  @JsonSubTypes.Type(
      value = PatientCharacteristicRace.class,
      name = "QDM::PatientCharacteristicRace"),
  @JsonSubTypes.Type(
      value = PatientCharacteristicSex.class,
      name = "QDM::PatientCharacteristicSex"),
  @JsonSubTypes.Type(value = PhysicalExamOrder.class, name = "QDM::PhysicalExamOrder"),
  @JsonSubTypes.Type(value = PhysicalExamPerformed.class, name = "QDM::PhysicalExamPerformed"),
  @JsonSubTypes.Type(value = PhysicalExamRecommended.class, name = "QDM::PhysicalExamRecommended"),
  @JsonSubTypes.Type(value = ProcedureOrder.class, name = "QDM::ProcedureOrder"),
  @JsonSubTypes.Type(value = ProcedurePerformed.class, name = "QDM::ProcedurePerformed"),
  @JsonSubTypes.Type(value = ProcedureRecommended.class, name = "QDM::ProcedureRecommended"),
  @JsonSubTypes.Type(value = ProviderCareExperience.class, name = "QDM::ProviderCareExperience"),
  @JsonSubTypes.Type(value = SubstanceAdministered.class, name = "QDM::SubstanceAdministered"),
  @JsonSubTypes.Type(value = SubstanceOrder.class, name = "QDM::SubstanceOrder"),
  @JsonSubTypes.Type(value = SubstanceRecommended.class, name = "QDM::SubstanceRecommended"),
  @JsonSubTypes.Type(value = Symptom.class, name = "QDM::Symptom"),
  @JsonSubTypes.Type(value = RelatedPerson.class, name = "QDM::RelatedPerson")
})
public class DataElement {

  // Codes that describe this datatype.
  private List<Code> dataElementCodes;

  // Optional description.
  private String description;

  // Valueset oid of the specific type.
  private String codeListId;
  private String desc;
  private String codeId;

  private String _id;

  public Interval shiftIntervalByYear(Interval interval, int year) {
    if (interval == null) {
      return null;
    }
    if (interval.getLow() != null) {
      interval.setLow(shiftDateByYear(interval.getLow(), year));
    }
    if (interval.getHigh() != null) {
      interval.setHigh(shiftDateByYear(interval.getHigh(), year));
    }
    return interval;
  }

  public ZonedDateTime shiftDateByYear(ZonedDateTime dateTime, int year) {
    if (dateTime == null) {
      return null;
    }
    ZonedDateTime shiftedDateTime = dateTime.plusYears(year);
    if (shiftedDateTime.getYear() > 9999) {
      return shiftedDateTime.withYear(9999);
    }
    if (shiftedDateTime.getYear() < 1900) {
      return shiftedDateTime.withYear(1900);
    }
    return shiftedDateTime;
  }

  public Object shiftDateByYearForObject(Object object, int year) {
    if (object == null) {
      return null;
    }

    Object convertedObj = object;
    // Date type
    try {
      LocalDate converted = convertToLocalDate(object);
      if (converted != null) {
        converted = converted.plusYears(year);
        if (converted.getYear() > 9999) {
          converted.withYear(9999);
        }
        if (converted.getYear() < 1900) {
          converted.withYear(1900);
        }
        convertedObj = converted.toString();
      }
    } catch (Exception ex) {
      log.error("Object not a Date type! exception: " + ex.getMessage());
    }
    // DateTime type
    try {
      ZonedDateTime converted = convertToZonedDateTime(object);
      if (converted != null) {
        converted = shiftDateByYear(converted, year);
        if (converted.getYear() > 9999) {
          converted.withYear(9999);
        }
        if (converted.getYear() < 1900) {
          converted.withYear(1900);
        }
        convertedObj = converted.toString().replace("Z", ":00.000+00:00");
      }
    } catch (Exception ex) {
      log.error("Object not a Date Time type! exception: " + ex.getMessage());
    }
    return convertedObj;
  }

  private LocalDate convertToLocalDate(Object object) {
    LocalDate localDate =
        LocalDate.parse(
            object.toString(),
            DateTimeFormatter.ofPattern(LocalDateTimeFormatConstant.LOCAL_DATE_PATTERN));
    return localDate;
  }

  private ZonedDateTime convertToZonedDateTime(Object object) {
    OffsetDateTime odt =
        OffsetDateTime.parse(
            object.toString(),
            DateTimeFormatter.ofPattern(LocalDateTimeFormatConstant.LOCAL_DATE_TIME_PATTERN));
    return odt.toZonedDateTime();
  }
}
