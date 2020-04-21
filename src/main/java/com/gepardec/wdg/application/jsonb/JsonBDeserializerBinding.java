package com.gepardec.wdg.application.jsonb;

import com.gepardec.wdg.client.personio.Source;

import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.serializer.DeserializationContext;
import javax.json.bind.serializer.JsonbDeserializer;
import javax.json.stream.JsonParser;
import java.lang.reflect.Type;

@ApplicationScoped
public class JsonBDeserializerBinding implements JsonbDeserializer<Source> {
    @Override
    public Source deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Type type) {
        return Source.valueOf(jsonParser.getString().toUpperCase());
    }
}
