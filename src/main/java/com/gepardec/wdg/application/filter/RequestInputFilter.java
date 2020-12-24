package com.gepardec.wdg.application.filter;

import com.gepardec.wdg.challenge.model.Answer;
import com.gepardec.wdg.challenge.model.ConstraintViolationResponse;
import org.jboss.logging.Logger;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Provider
public class RequestInputFilter implements ContainerRequestFilter {

    @Context
    UriInfo info;

    private static final org.jboss.logging.Logger log = Logger.getLogger(RequestInputFilter.class);

    private static final Jsonb jsonb = JsonbBuilder.create();

    private static final Pattern ANSWER_PATH_REGEX_PATTER = Pattern.compile("(/challenge/)\\d+(/answer)");
    private static final Pattern URL_PATH_REGEX_PATTER = Pattern.compile("(/challenge/)\\d+(/url)");

    @Override
    public void filter(ContainerRequestContext ctx) throws IOException {

        String path = info.getPath();
        Matcher pathMatcherChallenge1 = ANSWER_PATH_REGEX_PATTER.matcher(path);
        Matcher pathMatcherChallenge2 = URL_PATH_REGEX_PATTER.matcher(path);

        if(pathMatcherChallenge1.matches()||pathMatcherChallenge2.matches()) {
            String input;

            // extract input stream
            try (BufferedReader br = new BufferedReader(new InputStreamReader(ctx.getEntityStream()))) {
                input = br.lines().collect(Collectors.joining("\n"));
            }

            try {
                // reset input stream to request context
                ctx.setEntityStream(new ByteArrayInputStream(input.getBytes()));

                jsonb.fromJson(input, Answer.class);
            } catch (JsonbException e) {
                log.error("Error while parsing body input! " + e.getMessage());
                ctx.abortWith(Response.status(Response.Status.BAD_REQUEST)
                        .entity(ConstraintViolationResponse.error("Bitte überprüfe das Format vom Request-Body, hier stimmt irgendetwas nicht ganz! :-)"))
                        .build());
            }
        }
    }
}
