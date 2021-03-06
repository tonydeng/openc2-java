package com.github.tonydeng.openc2.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tonydeng.openc2.OpenC2Response;
import com.github.tonydeng.openc2.header.Header;
import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.github.tonydeng.openc2.utilities.Keys.*;

/**
 * Customized deserializer for OpenC2 response messages
 *
 * @author dengtao
 **/
@Slf4j
public class OpenC2ResponseDeserializer extends JsonDeserializer<OpenC2Response> {
    @Override
    public OpenC2Response deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        val watch = Stopwatch.createStarted();

        val response = new OpenC2Response();

        val mapper = new ObjectMapper();

        JsonNode nodes = parser.getCodec().readTree(parser);
        val keys = nodes.fieldNames();

        while (keys.hasNext()) {
            val key = keys.next();

            switch (key) {
                case HEADER:
                    val headerJson = mapper.writeValueAsString(nodes.get(key));
                    response.setHeader(
                            mapper.readValue(
                                    new JsonFactory().createParser(headerJson),
                                    Header.class
                            )
                    );
                    break;
                case RESPONSE:
                    deserializeBody(nodes.get(key), response);
                    break;
                default:
                    deserializeIdAndStatus(key, nodes, response);
                    break;
            }
        }
        watch.stop();
        log.debug("openc2 response deserializer {} microseconds ......", watch.elapsed(TimeUnit.MICROSECONDS));
        return response;
    }

    /**
     * Method to parse the body of a header based JSON string
     *
     * @param nodes    Body JsonNode
     * @param response OpenC2Response
     */
    private void deserializeBody(JsonNode nodes, OpenC2Response response) {

        val keys = nodes.fieldNames();
        while (keys.hasNext()) {
            String key = keys.next();
            deserializeIdAndStatus(key, nodes, response);
        }
    }

    /**
     * Method to parse the Id,IdRef,Status,StatusText,Results JSON string
     *
     * @param key      Json key
     * @param nodes    Parser JsonNode
     * @param response OpenC2Response
     */
    private void deserializeIdAndStatus(String key, JsonNode nodes, OpenC2Response response) {
        switch (key) {
            case ID:
                response.setId(nodes.get(key).asText());
                break;
            case "id_ref":
                response.setIdRef(nodes.get(key).asText());
                break;
            case "status":
                response.setStatus(nodes.get(key).asInt());
                break;
            case "status_text":
                response.setStatusText(nodes.get(key).asText());
                break;
            case "results":
                response.setResults(nodes.get(key).asText());
                break;
            default:
                // Ignore any other keys
                break;
        }
    }
}
