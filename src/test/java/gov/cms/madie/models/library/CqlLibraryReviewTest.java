package gov.cms.madie.models.library;

import gov.cms.madie.models.common.ReviewStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CqlLibraryReviewTest {

  @Test
  void testCqlLibraryReviewRoundTrip() {
    CqlLibraryReview review =
        CqlLibraryReview.builder()
            .id("review-1")
            .libraryId("lib-1")
            .librarySetId("set-1")
            .status(ReviewStatus.READY_FOR_REVIEW)
            .comment("Looks good")
            .build();

    assertEquals("review-1", review.getId());
    assertEquals("lib-1", review.getLibraryId());
    assertEquals("set-1", review.getLibrarySetId());
    assertEquals(ReviewStatus.READY_FOR_REVIEW, review.getStatus());
    assertEquals("Looks good", review.getComment());
  }

  @Test
  void testCqlLibraryReviewBuilderDefaults() {
    CqlLibraryReview review = CqlLibraryReview.builder().build();
    assertNotNull(review);
  }
}
