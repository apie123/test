package be.kdg.reference.model;

public final class SessionId {
    private static final String INVALID_SESSION = "INVALID SESSION";
    public static final SessionId INVALID_SESSION_ID = new SessionId(INVALID_SESSION);
    private final String value;

    public SessionId(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @SuppressWarnings({"StringEquality"})
    public boolean isValid() {
        return value != INVALID_SESSION;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SessionId sessionId = (SessionId) o;

        if (value != null ? !value.equals(sessionId.value) : sessionId.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return value;
    }
}
