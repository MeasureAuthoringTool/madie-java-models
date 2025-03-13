package gov.cms.madie.models.cqm.datacriteria.basetypes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DataElementTest2 {

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
