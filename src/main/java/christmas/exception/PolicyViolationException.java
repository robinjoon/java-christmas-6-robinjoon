package christmas.exception;

public class PolicyViolationException extends IllegalArgumentException {
    public PolicyViolationException(PolicyViolationType policyViolationType) {
        super(policyViolationType.getMessage());
    }
}
