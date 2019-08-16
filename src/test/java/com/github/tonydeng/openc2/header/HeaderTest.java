package com.github.tonydeng.openc2.header;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author dengtao
 **/
@Slf4j
public class HeaderTest {

    static final String VERSION = "version";
    static final String VERSION2 = "version2";
    static final String ID = "command_id";
    static final String CREATED = "CREATED today";
    static final String SENDER = "sender";
    static final String CONTENT = "content";
    static final String CONTENT2 = "content2";

    @Test
    void testEquals() {
        Header header = new Header(
                VERSION, ID, CREATED, SENDER, CONTENT
        );

        assertEquals(VERSION, header.getVersion());
        assertEquals(ID, header.getCommandId());
        assertEquals(CREATED, header.getCreated());
        assertEquals(SENDER, header.getSender());
        assertEquals(CONTENT, header.getContentType());

        log.info("{}", JSON.toJSONString(header, SerializerFeature.PrettyFormat));

        header.setVersion(VERSION2);
        header.setContentType(CONTENT2);

        assertEquals(VERSION2, header.getVersion());
        assertEquals(CONTENT2, header.getContentType());

        log.info("{}", JSON.toJSONString(header, SerializerFeature.PrettyFormat));
    }

    @Test
    void testIsEmpty() {
        Header header = new Header();

        assertTrue(header.isEmpty());

        header = Header.builder().version("")
                .commandId("").created("").sender("")
                .build();

        assertTrue(header.isEmpty());
    }

}
