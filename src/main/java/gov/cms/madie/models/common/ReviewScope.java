package gov.cms.madie.models.common;

/**
 * Which slice of the review work a review search asks for, the review counterpart of {@link
 * OwnershipType} on the measure and library searches.
 */
public enum ReviewScope {
  /** Everything under review, whoever it is assigned to: the "All Reviews" tab. */
  ALL,
  /** Only what is assigned to the requesting reviewer and not finished: the "My Reviews" tab. */
  ASSIGNED
}
