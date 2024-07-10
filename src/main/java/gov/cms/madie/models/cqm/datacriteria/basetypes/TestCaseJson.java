package gov.cms.madie.models.cqm.datacriteria.basetypes;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TestCaseJson {
  private String _id;
  private String qdmVersion = "5.6";
  private List<DataElement> dataElements;

  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = LocalDateTimeFormatConstant.LOCAL_DATE_TIME_PATTERN)
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private ZonedDateTime birthDatetime;

  public ZonedDateTime shiftDateByYear(int year) {
    if (this.birthDatetime == null) {
      return null;
    }
    return this.birthDatetime.plusYears(year);
  }
}
