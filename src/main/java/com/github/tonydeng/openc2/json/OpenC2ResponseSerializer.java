package com.github.tonydeng.openc2.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.tonydeng.openc2.OpenC2Response;
import com.github.tonydeng.openc2.utilities.Keys;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Customized Serializer for OpenC2 response messages
 *
 * @author dengtao
 **/
@Slf4j
public class OpenC2ResponseSerializer extends JsonSerializer<OpenC2Response> {
    @Override
    public void serialize(OpenC2Response response, JsonGenerator generator, SerializerProvider provider) throws IOException {
        log.debug("openc2 response serializer start......");
        generator.writeStartObject();

        if (response.hasHeader()) {
            generator.writeObjectField(Keys.HEADER, response.getHeader());
            generator.writeFieldName(Keys.RESPONSE);
            generator.writeStartObject();
        }

        generator.writeObjectField(Keys.ID, response.getId());
        generator.writeObjectField("id_ref", response.getIdRef());
        generator.writeObjectField("status", response.getStatus());

        if (response.hasStatusText()) {
            generator.writeObjectField("status_text", response.getStatusText());
        }

        if (response.hasResults()) {
            generator.writeObjectField("results", response.getResults());
        }

        if (response.hasHeader()) {
            generator.writeEndObject();
        }

        generator.writeEndObject();
        log.debug("openc2 response serializer end......");
    }
}
