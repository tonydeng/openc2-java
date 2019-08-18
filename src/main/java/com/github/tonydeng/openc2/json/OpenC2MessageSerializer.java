package com.github.tonydeng.openc2.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.tonydeng.openc2.OpenC2Message;
import com.github.tonydeng.openc2.utilities.Keys;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Map;

/**
 * Customized serializer to encode a OpenC2Message object into a JSON string
 *
 * @author Tony Deng
 * @version V1.0
 * @date 2019-08-18 12:00
 **/
@Slf4j
public class OpenC2MessageSerializer extends JsonSerializer<OpenC2Message> {
    @Override
    public void serialize(OpenC2Message message, JsonGenerator generator, SerializerProvider provider) throws IOException {
        log.debug("openc2 message serializer start......");
        generator.writeStartObject();

        if (message.hasHeader()) {
            generator.writeObjectField(Keys.HEADER, message.getHeader());
            generator.writeFieldName(Keys.BODY);
            generator.writeStartObject();
        }

        if (message.hasId()) {
            generator.writeObjectField(Keys.ID, message.getId());
        }
        generator.writeObjectField(Keys.ACTION, message.getAction());

        //Map target object to JSON
        String targetType = message.getTarget().getObjectType();
        Map<String, Object> target = message.getTarget().getAll();
        // Target object name is not present
        if (target.get(targetType) == null) {
            generator.writeObjectFieldStart(Keys.TARGET);
            generator.writeObjectField(targetType, message.getTarget().getAll());
            generator.writeEndObject();
        } else {
            generator.writeObjectField(Keys.TARGET, message.getTarget().getAll());
        }

        // Map actuator object to JSON
        if (message.hasActuator()) {
            String actuatorType = message.getActuator().getObjectType();
            Map<String, Object> actuator = message.getActuator().getAll();

            if (actuator.get(actuatorType) == null) {
                generator.writeObjectFieldStart(Keys.ACTION);
                generator.writeObjectField(actuatorType, actuator);
                generator.writeEndObject();
            } else {
                generator.writeObjectField(Keys.ACTUATOR, actuator);
            }
        }

        if (message.hasArgs()) {
            generator.writeObjectField(Keys.ARGUMENTS, message.getArgs().getAll());
        }

        if (message.hasHeader()) {
            generator.writeEndObject();
        }
        generator.writeEndObject();

        log.debug("openc2 message serializer end......");
    }
}
