package gov.cms.madie.models.measure;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder=true)
@NoArgsConstructor
@AllArgsConstructor
public class ReviewMetaData {
    private Instant approvalDate;
    private Instant lastReviewDate;
}