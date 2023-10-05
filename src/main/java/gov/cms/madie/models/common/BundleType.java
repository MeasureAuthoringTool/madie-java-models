package gov.cms.madie.models.common;

public enum BundleType {
	  TRANSACTION("transaction"),
	  COLLECTION("collection");

	  private final String bundleType;

	  private BundleType(String s) {
	    bundleType = s;
	  }

	  public boolean equalsType(String otherType) {
	    return bundleType.equals(otherType);
	  }

	  public String toString() {
	    return this.bundleType;
	  }
	}

