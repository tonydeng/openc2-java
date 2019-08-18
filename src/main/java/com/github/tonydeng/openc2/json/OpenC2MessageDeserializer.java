package com.github.tonydeng.openc2.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tonydeng.openc2.OpenC2Message;
import com.github.tonydeng.openc2.header.Header;
import com.github.tonydeng.openc2.targets.TargetType;
import com.github.tonydeng.openc2.utilities.OpenC2Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;

import java.io.IOException;
import java.util.Iterator;

import static com.github.tonydeng.openc2.utilities.Keys.HEADER;
import static com.github.tonydeng.openc2.utilities.Keys.TARGET;

/**
 * Customized deserializer for processing an OpenC2 message
 *
 * @author Tony Deng
 * @version V1.0
 * @date 2019-08-18 11:28
 **/
public class OpenC2MessageDeserializer extends JsonDeserializer<OpenC2Message> {


    @Override
    public OpenC2Message deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
        OpenC2Message message = new OpenC2Message();
        ObjectMapper mapper = new ObjectMapper();

        JsonNode nodes = parser.getCodec().readTree(parser);

        Iterator<String> keys = nodes.fieldNames();

        while (keys.hasNext()) {
            String key = keys.next();
            switch (key) {
                case HEADER:
                    String headerJson = mapper.writeValueAsString(nodes.get(key));
                    JsonParser p = new JsonFactory().createParser(headerJson);
                    message.setHeader(mapper.readValue(p, Header.class));
                    break;
                case TARGET:
                    JsonNode targetNode = nodes.get(key);

                    JsonParser targetParser;
                    String targetName = targetNode.fieldNames().next();
                    if (targetNode.get(targetName).isObject()) {
                        targetParser = new JsonFactory()
                                .createParser(
                                        mapper.writeValueAsString(targetNode.get(targetName))
                                );
                    } else {
                        targetParser = new JsonFactory().createParser(
                                mapper.writeValueAsString(targetNode)
                        );
                    }

                    try {
                        Class<?> clazz = Class.forName(getTargetClassName(targetName));
                        message.setTarget((OpenC2Map<TargetType>) mapper.readValue(targetParser, clazz));
                    } catch (ClassNotFoundException e) {
                        throw new IOException("Unknown target type '" + getTargetClassName(targetName) + "' found in JSON");
                    }
                    break;
                default:
                    break;
            }

        }

        return message;
    }

    /**
     * Gets a target object name based on the key in the JSON string
     *
     * @param name target object name
     * @return
     */
    private String getTargetClassName(String name) {
        return "com.github.tonydeng.openc2.target" + getCamelCase(name);
    }


    private String getActuatorClassName(String name) {
        return "com.github.tonydeng.openc2.actuators" + getCamelCase(name);
    }

    private String getCamelCase(String name) {
        return StringUtils.remove(WordUtils.capitalizeFully(name,
                '_'), "_");
    }
}

