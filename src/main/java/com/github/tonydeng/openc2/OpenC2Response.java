package com.github.tonydeng.openc2;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.tonydeng.openc2.header.Header;
import com.github.tonydeng.openc2.json.OpenC2ResponseDeserializer;
import com.github.tonydeng.openc2.json.OpenC2ResponseSerializer;
import lombok.*;

/**
 * OpenC2Response is to process the message that is returned to a
 * program that sends a OpenC2 message to a service.
 *
 * @author dengtao
 **/
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@JsonSerialize(using = OpenC2ResponseSerializer.class)
@JsonDeserialize(using = OpenC2ResponseDeserializer.class)
public class OpenC2Response implements OpenC2Message {
    private Header header;
    private String id;
    private String idRef;
    @NonNull
    private int status;
    private String statusText;
    private Object results;


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
}
