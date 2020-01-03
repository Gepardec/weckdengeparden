package com.gepardec.wdg.application.jsonb;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.serializer.JsonbSerializer;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class JsonUtil {

    private static JsonbSerializer<InputStream> inputStreamJsonbSerializer = new InputStreamSerializer();

    private JsonUtil() {
    }

    public static String toJson(Object obj) {
        if (obj == null) {
            return "";
        }
        try (final Jsonb jsonb = createJsonB()) {
            return jsonb.toJson(obj);
        } catch (Exception e) {
            return "not_serializable: " + e.getMessage();
        }
    }

    public static Jsonb createJsonB() {
        final JsonbConfig config = new JsonbConfig()
                .withEncoding(StandardCharsets.UTF_8.name())
                .withSerializers(inputStreamJsonbSerializer);
        return JsonbBuilder.newBuilder().withConfig(config).build();
    }
}
