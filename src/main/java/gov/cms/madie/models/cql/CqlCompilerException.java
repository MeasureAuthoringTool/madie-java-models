package gov.cms.madie.models.cql;

public final class CqlCompilerException {

  private CqlCompilerException() {}

  public enum ErrorSeverity {
    Info,
    Warning,
    Error
  }
}
