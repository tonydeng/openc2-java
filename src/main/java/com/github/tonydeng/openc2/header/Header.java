package com.github.tonydeng.openc2.header;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * @author dengtao
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Header {
    private String version; // Message Protocol version
    private String commandId;   // The Message Command ID
    private String created;     // Creation date/time of the content
    private String sender;      //  The Message Sender
    private String contentType; // The type and version of the message body

    @JSONField(serialize = false)
    public final boolean isEmpty() {
        return StringUtils.isEmpty(version)
                && StringUtils.isEmpty(commandId) && StringUtils.isEmpty(created)
                && StringUtils.isEmpty(sender) && StringUtils.isEmpty(contentType);
    }
}
