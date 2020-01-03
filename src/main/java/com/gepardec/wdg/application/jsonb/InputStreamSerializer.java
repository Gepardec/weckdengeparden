package com.gepardec.wdg.application.jsonb;

import javax.json.bind.serializer.JsonbSerializer;
import javax.json.bind.serializer.SerializationContext;
import javax.json.stream.JsonGenerator;
import java.io.InputStream;

public class InputStreamSerializer implements JsonbSerializer<InputStream> {

    @Override
    public void serialize(InputStream obj, JsonGenerator generator, SerializationContext ctx) {
        ctx.serialize("input-stream", generator);
    }
}
