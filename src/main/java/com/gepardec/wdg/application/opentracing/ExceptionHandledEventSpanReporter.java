package com.gepardec.wdg.application.opentracing;

import com.gepardec.wdg.application.exception.ExceptionHandledEvent;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.noop.NoopSpan;
import io.opentracing.tag.Tags;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.Optional;

/**
 * Reports an handled exception on the current active span.
 */
@ApplicationScoped
public class ExceptionHandledEventSpanReporter {

    @Inject
    Tracer tracer;

    protected void reportException(@Observes final ExceptionHandledEvent event) {
        final Span span = tracer.activeSpan();
        if(span != null) {
            if (!(span instanceof NoopSpan)) {
                span.setTag("error.kind", "Exception");
                span.setTag("error.message", event.message);
                span.setTag("exception.type", event.exception.getClass().getName());
                span.setTag("exception.message", Optional.ofNullable(event.exception.getMessage()).orElse(""));
                if (event.error) {
                    Tags.ERROR.set(span, true);
                    span.log(Map.of("exception.stacktrace", throwableToString(event.exception)));
                }
            }
        }
    }

    private String throwableToString(final Throwable throwable) {
        try (final StringWriter stringWriter = new StringWriter()) {
            try (final PrintWriter printWriter = new PrintWriter(stringWriter)) {
                throwable.printStackTrace(printWriter);
                return stringWriter.toString();
            }
        } catch (Exception e) {
            return "stacktrace_not_serializable: " + e.getMessage();
        }
    }
}
