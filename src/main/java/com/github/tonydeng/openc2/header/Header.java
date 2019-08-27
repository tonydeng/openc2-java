package com.github.tonydeng.openc2.header;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

/**
 * @author dengtao
 **/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
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


    @JsonSetter("id")
    public void setCommandId(@NonNull String commandId) {
        this.commandId = commandId;
    }


    @JsonSetter("content_type")
    public void setContentType(@NonNull String contentType) {
        this.contentType = contentType;
    }

    @JsonIgnore
    public final boolean isEmpty() {
        return StringUtils.isAllEmpty(
                commandId, version, contentType, created, sender
        );
    }
}
