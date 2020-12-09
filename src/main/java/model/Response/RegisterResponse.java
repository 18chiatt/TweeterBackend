package model.Response;

import java.util.Objects;

public class RegisterResponse {
    public RegisterResponse(boolean wasSuccessful) {
        this.wasSuccessful = wasSuccessful;
    }

    public boolean isWasSuccessful() {
        return wasSuccessful;
    }

    private boolean wasSuccessful;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterResponse that = (RegisterResponse) o;
        return wasSuccessful == that.wasSuccessful;
    }

    @Override
    public int hashCode() {
        return Objects.hash(wasSuccessful);
    }
}
