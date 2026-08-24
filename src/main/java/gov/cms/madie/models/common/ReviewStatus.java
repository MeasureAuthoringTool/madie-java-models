package gov.cms.madie.models.common;

public enum ReviewStatus {
  READY_FOR_REVIEW,
  IN_PROGRESS,
  COMPLETE,
  NOT_READY_FOR_REVIEW;

  public ActionType toActionType() {
    switch (this) {
      case READY_FOR_REVIEW:
        return ActionType.READY_FOR_REVIEW;
      case IN_PROGRESS:
        return ActionType.REVIEW_IN_PROGRESS;
      case COMPLETE:
        return ActionType.REVIEW_COMPLETE;
      default:
        return ActionType.NOT_READY_FOR_REVIEW;
    }
  }
}
