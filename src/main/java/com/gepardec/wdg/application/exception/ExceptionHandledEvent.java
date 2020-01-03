package com.gepardec.wdg.application.exception;

import java.util.Objects;

public class ExceptionHandledEvent {

    public final String message;
    public final boolean error;
    public final Throwable exception;

    ExceptionHandledEvent(final Builder builder) {
        Objects.requireNonNull(builder, "Builder must not be null");
        this.exception = builder.throwable;
        this.message = builder.message;
        this.error = builder.error;
    }

    public static class Builder {

        public String message;
        public boolean error;
        public final Throwable throwable;

        Builder(final Throwable throwable) {
            this.throwable = Objects.requireNonNull(throwable, "Throwable instance must not be null");
        }

        public static Builder newBuilder(final Throwable throwable) {
            return new Builder(throwable);
        }


        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder withIsError(boolean error) {
            this.error = error;
            return this;
        }

        public ExceptionHandledEvent build() {
            return new ExceptionHandledEvent(this);
        }
    }
}
