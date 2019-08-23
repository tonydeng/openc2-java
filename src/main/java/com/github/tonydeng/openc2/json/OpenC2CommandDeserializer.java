package com.github.tonydeng.openc2.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tonydeng.openc2.OpenC2Command;
import com.github.tonydeng.openc2.actuators.ActuatorType;
import com.github.tonydeng.openc2.args.Args;
import com.github.tonydeng.openc2.header.Header;
import com.github.tonydeng.openc2.targets.TargetType;
import com.github.tonydeng.openc2.utilities.OpenC2Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;

import java.io.IOException;
import java.util.Iterator;

import static com.github.tonydeng.openc2.utilities.Keys.*;

/**
 * @author Tony Deng
 * @version V1.0
 * @date 2019-08-18 15:27
 **/
@Slf4j
public class OpenC2CommandDeserializer extends JsonDeserializer<OpenC2Command> {
    @Override
    public OpenC2Command deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        log.debug("openc2 command deserialize start......");
        OpenC2Command message = new OpenC2Command();
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
                case BODY:
                    deserializeBody(mapper.writeValueAsString(nodes.get(key)), message);
                    break;
                default:
                    deserializeOtherProperties(key, nodes, mapper, message);
                    break;
            }

        }
        log.debug("openc2 command deserialize end......");
        return message;
    }

    /**
     * 反序列化Body
     *
     * @param json    Body JSON
     * @param message OpenC2Command Message
     * @return OpenC2Command
     * @throws IOException
     */
    private OpenC2Command deserializeBody(String json, OpenC2Command message) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        JsonNode nodes = mapper.readTree(json);

        Iterator<String> keys = nodes.fieldNames();

        while (keys.hasNext()) {
            String key = keys.next();
            deserializeOtherProperties(key, nodes, mapper, message);
        }

        return message;
    }

    /**
     * 设置除了Header和Body的所有属性
     *
     * @param key     Properties Key
     * @param nodes   JsonNode
     * @param mapper  Object Mapper
     * @param message OpenC2Command Message
     * @throws IOException
     */
    private void deserializeOtherProperties(String key, JsonNode nodes, ObjectMapper mapper, OpenC2Command message)
            throws IOException {
        switch (key) {
            case ID:
                message.setId(nodes.get(key).asText());
                break;
            case ACTION:
                message.setAction(nodes.get(key).asText());
                break;
            case TARGET:
                setTarget(nodes.get(key), message, mapper);
                break;
            case ACTUATOR:
                setActuator(nodes.get(key), message, mapper);
                break;
            case ARGUMENTS:
                JsonNode node = nodes.get(key);
                String argumentsJson = mapper.writeValueAsString(node);
                JsonParser argumentsParser = new JsonFactory().createParser(argumentsJson);
                message.setArgs(mapper.readValue(argumentsParser, Args.class));
                break;
            default:
                break;
        }
    }

    /**
     * Set Actuator to OpenC2Command
     *
     * @param actuatorNode Target Jacksn JsonNode
     * @param message      set to OpenC2Command
     * @param mapper       Jackson ObjectMapper
     * @throws IOException
     */
    private void setActuator(JsonNode actuatorNode, OpenC2Command message, ObjectMapper mapper) throws IOException {

        String actuatorName = actuatorNode.fieldNames().next();
        final JsonParser actuatorParser = getJsonParser(actuatorNode, actuatorName, mapper);

        try {
            Class<?> clazz = Class.forName(getActuatorClassName(actuatorName));
            message.setActuator((OpenC2Map<ActuatorType>) mapper.readValue(actuatorParser, clazz));
        } catch (ClassNotFoundException e) {
            throw new IOException("Unknown actuator type '" + getActuatorClassName(actuatorName) + "' found in JSON");
        }
    }

    /**
     * Set Target to OpenC2Command
     *
     * @param targetNode Target Jackson JsonNode
     * @param message    set to OpenC2Command
     * @param mapper     Jaskcon ObjectMapper
     * @throws IOException
     */
    private void setTarget(JsonNode targetNode, OpenC2Command message, ObjectMapper mapper) throws IOException {

        String targetName = targetNode.fieldNames().next();

        final JsonParser targetParser = getJsonParser(targetNode, targetName, mapper);

        try {
            Class<?> clazz = Class.forName(getTargetClassName(targetName));
            message.setTarget((OpenC2Map<TargetType>) mapper.readValue(targetParser, clazz));
        } catch (ClassNotFoundException e) {
            throw new IOException("Unknown target type '" + getTargetClassName(targetName) + "' found in JSON");
        }
    }

    /**
     * Gets a JsonParser based on the JsonNode
     *
     * @param node   Target JsonNode
     * @param name   node name
     * @param mapper Jackson ObjectMapper
     * @return the Target JsonNode JsonParser
     * @throws IOException
     */
    private JsonParser getJsonParser(JsonNode node, String name, ObjectMapper mapper) throws IOException {
        final JsonParser parser;

        if (node.get(name).isObject()) {
            parser = new JsonFactory().createParser(
                    mapper.writeValueAsString(node.get(name))
            );
        } else {
            parser = new JsonFactory().createParser(
                    mapper.writeValueAsString(node)
            );
        }

        return parser;
    }

    /**
     * Gets a target object name based on the key in the JSON string
     *
     * @param name target object name
     * @return
     */
    private String getTargetClassName(String name) {
        return "com.github.tonydeng.openc2.targets." + getCamelCase(name);
    }


    private String getActuatorClassName(String name) {
        return "com.github.tonydeng.openc2.actuators." + getCamelCase(name);
    }

    private String getCamelCase(String name) {
        return StringUtils.remove(WordUtils.capitalizeFully(name,
                '_'), "_");
    }
}