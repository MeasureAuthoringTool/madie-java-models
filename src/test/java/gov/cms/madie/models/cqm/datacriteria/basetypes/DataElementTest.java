package gov.cms.madie.models.cqm.datacriteria.basetypes;

import gov.cms.madie.models.cqm.datacriteria.EncounterPerformed;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class DataElementTest {

  @Test
  void shiftDateByYearAfterYear9999() {
    EncounterPerformed encounterPerformed = new EncounterPerformed();
    ZonedDateTime dateTime = ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("UTC"));
    encounterPerformed.setAuthorDatetime(dateTime);
    ZonedDateTime shiftedDateTime = encounterPerformed.shiftDateByYear(dateTime, 100000);
    assertThat(shiftedDateTime).isEqualTo(dateTime.withYear(9999));
  }

  @Test
  void shiftDateByYearBeforeYear0() {
    EncounterPerformed encounterPerformed = new EncounterPerformed();
    ZonedDateTime dateTime = ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("UTC"));
    encounterPerformed.setAuthorDatetime(dateTime);
    ZonedDateTime shiftedDateTime = encounterPerformed.shiftDateByYear(dateTime, -100000);
    assertThat(shiftedDateTime).isEqualTo(dateTime.withYear(1900));
  }
}
