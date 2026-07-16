package gov.cms.madie.models.library;

import gov.cms.madie.models.common.Review;
import gov.cms.madie.models.common.ReviewStatus;
import gov.cms.madie.models.common.Version;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CqlLibraryReviewTest {

  @Test
  void testCqlLibraryReviewRoundTrip() {
    Review review =
        Review.builder().status(ReviewStatus.READY_FOR_REVIEW).comment("Looks good").build();

    CqlLibrary library =
        CqlLibrary.builder()
            .id("lib-1")
            .librarySetId("set-1")
            .cqlLibraryName("TestLibrary")
            .model("QI-Core v4.1.1")
            .version(new Version(0, 0, 1))
            .review(review)
            .build();

    assertEquals(ReviewStatus.READY_FOR_REVIEW, library.getReview().getStatus());
    assertEquals("Looks good", library.getReview().getComment());
  }

  @Test
  void testCqlLibraryReviewDefaultsToNull() {
    CqlLibrary library = new CqlLibrary();
    assertNull(library.getReview());
  }
}
