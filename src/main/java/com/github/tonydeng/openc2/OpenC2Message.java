package com.github.tonydeng.openc2;

import com.github.tonydeng.openc2.header.Header;
import lombok.Builder;
import lombok.Data;

/**
 * @author dengtao
 **/

@Data
@Builder
public class OpenC2Message {
    private String id;
    private Header header;

    /**
     * Check if the id value has been set
     *
     * @return true if the id value has been set
     */
    public boolean hasHeader() {
        return (header != null && !header.isEmpty());
    }
}
