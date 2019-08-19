package com.github.tonydeng.openc2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tonydeng.openc2.json.JsonFormatter;

public interface OpenC2Message {

    /**
     * Convert the OpenC2Message object to a JSON string
     *
     * @return String containing the JSON
     * @throws JsonProcessingException Exception thrown by the Jackson library
     */
    default String toJson() throws JsonProcessingException {
        return JsonFormatter.getJson(this, false);
    }

    /**
     * Convert the OpenC2Message object to a JSON string that is more
     * reader friendly.
     *
     * @return String containing the JSON in a human readable format
     * @throws JsonProcessingException Exception thrown by the Jackson library
     */
    default String toPrettyJson() throws JsonProcessingException {
        return JsonFormatter.getJson(this, true);
    }
}
