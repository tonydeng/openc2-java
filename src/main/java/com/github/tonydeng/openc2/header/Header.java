package com.github.tonydeng.openc2.header;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

/**
 * @author dengtao
 **/

@Getter
public class Header {

    /**
     * Message Protocol version
     */
    private String version;
    /**
     * The Message Command ID
     */
    private String commandId;
    /**
     * Creation date/time of the content
     */
    private String created;
    /**
     * The Message Sender
     */
    private String sender;
    /**
     * The type and version of the message body
     */
    private String contentType;

    public Header() {
    }

    public Header(String version, String contentType) {
        this.version = version;
        this.contentType = contentType;
    }


    public Header setVersion(String version) {
        this.version = version;
        return this;
    }

    @JsonSetter("id")
    public Header setCommandId(String commandId) {
        this.commandId = commandId;
        return this;
    }


    public Header setCreated(String created) {
        this.created = created;
        return this;
    }

    public Header setSender(String sender) {
        this.sender = sender;
        return this;
    }

    @JsonSetter("content_type")
    public Header setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    @JsonIgnore
    public final boolean isEmpty() {
        return StringUtils.isEmpty(version)
                && StringUtils.isEmpty(commandId) && StringUtils.isEmpty(created)
                && StringUtils.isEmpty(sender) && StringUtils.isEmpty(contentType);
    }
}
