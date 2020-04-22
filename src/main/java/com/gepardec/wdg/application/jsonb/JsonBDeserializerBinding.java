package com.gepardec.wdg.application.jsonb;

import com.gepardec.wdg.client.personio.Source;

import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.serializer.DeserializationContext;
import javax.json.bind.serializer.JsonbDeserializer;
import javax.json.stream.JsonParser;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class JsonBDeserializerBinding implements JsonbDeserializer<Source> {
    private static final List<String> enumNames = Stream.of(Source.values()).map(Enum::name).collect(Collectors.toList());

    @Override
    public Source deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Type type) {

        if(!enumNames.contains(jsonParser.getString())) {
            return Source.ERROR;
        }

        return Source.valueOf(jsonParser.getString());
    }
}
