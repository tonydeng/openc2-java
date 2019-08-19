package com.github.tonydeng.openc2;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.tonydeng.openc2.header.Header;
import com.github.tonydeng.openc2.json.JsonFormatter;
import com.github.tonydeng.openc2.json.OpenC2ResponseDeserializer;
import com.github.tonydeng.openc2.json.OpenC2ResponseSerializer;
import com.github.tonydeng.openc2.utilities.StatusCode;
import lombok.Getter;

/**
 * OpenC2Response is to process the message that is returned to a
 * program that sends a OpenC2 message to a service.
 *
 * @author dengtao
 **/
@Getter
@JsonSerialize(using = OpenC2ResponseSerializer.class)
@JsonDeserialize(using = OpenC2ResponseDeserializer.class)
public class OpenC2Response implements OpenC2Message {
    private Header header;
    private String id;
    private String idRef;
    private int status;
    private String statusText;
    private Object results;
    /**
     * This constructor only exists for Jackson processing and should
     * not be used directly
     */
    public OpenC2Response() {
    }

    /**
     * Constructor to assign the id, id ref and status to the response
     *
     * @param id     id of the response
     * @param idRef  id of the command that induced this response
     * @param status An integer status code
     */
    public OpenC2Response(String id, String idRef, StatusCode status) {
        this.id = id;
        this.idRef = idRef;
        this.status = status.getValue();
    }

    /**
     * Constructor to assign the id, id ref, status, status text and results to the response
     *
     * @param id         id of the response
     * @param idRef      id of the command that induced this response
     * @param status     An integer status code
     * @param statusText A free-form human redable description of the response status
     * @param results    Data or extended status information that was requested from an OpenC2 command
     */
    public OpenC2Response(String id, String idRef, StatusCode status, String statusText, Object results) {
        this(id, idRef, status);
        this.statusText = statusText;
        this.results = results;
    }


    public OpenC2Response setHeader(Header header) {
        this.header = header;
        return this;
    }

    public OpenC2Response setId(String id) {
        this.id = id;
        return this;
    }

    public OpenC2Response setIdRef(String idRef) {
        this.idRef = idRef;
        return this;
    }

    public OpenC2Response setStatus(int status) {
        this.status = status;
        return this;
    }

    @JsonIgnore
    public OpenC2Response setStatus(StatusCode status) {
        this.status = status.getValue();
        return this;
    }

    public OpenC2Response setStatusText(String statusText) {
        this.statusText = statusText;
        return this;
    }

    public OpenC2Response setResults(Object results) {
        this.results = results;
        return this;
    }

    /**
     * Check if the id value has been set
     *
     * @return true if the id value has been set
     */
    public boolean hasHeader() {
        return (header != null && !header.isEmpty());
    }

    /**
     * Check if the id value has been set
     *
     * @return true if the id value has been set
     */
    public boolean hasStatusText() {
        return (statusText != null && !statusText.isEmpty());
    }

    /**
     * Check if the actuator object has been created
     *
     * @return true if the actuator object has been set
     */
    public boolean hasResults() {
        return (results != null);
    }

    /**
     * Convert the OpenC2Command object to a JSON string
     *
     * @return String containing the JSON
     * @throws JsonProcessingException Exception thrown by the Jackson library
     */
    public String toJson() throws JsonProcessingException {
        return JsonFormatter.getJson(this, false);
    }

    /**
     * Convert the OpenC2Command object to a JSON string that is more
     * reader friendly.
     *
     * @return String containing the JSON in a human readable format
     * @throws JsonProcessingException Exception thrown by the Jackson library
     */
    public String toPrettyJson() throws JsonProcessingException {
        return JsonFormatter.getJson(this, true);
    }
}
