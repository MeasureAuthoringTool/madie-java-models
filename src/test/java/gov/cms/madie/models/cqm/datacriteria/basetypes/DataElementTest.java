package gov.cms.madie.models.cqm.datacriteria.basetypes;

import gov.cms.madie.models.cqm.datacriteria.EncounterPerformed;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

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

  @Test
  void testSuperClassThrowsException() {
    DataElement dataElement = new DataElement();

    ShiftDatesException thrown =
        assertThrowsExactly(
            ShiftDatesException.class,
            () -> dataElement.shiftDates(0),
            "Expected doThing() to throw, but it didn't");

    assertEquals(thrown.getMessage(), "There isn't an implementation of ShiftDates for this type");
  }
}
