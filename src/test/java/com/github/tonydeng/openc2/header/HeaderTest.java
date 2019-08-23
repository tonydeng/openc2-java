package com.github.tonydeng.openc2.header;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
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
        val header = new Header().setCommandId(ID)
                .setContentType(CONTENT).setVersion(VERSION)
                .setCreated(CREATED).setSender(SENDER);

        assertEquals(VERSION, header.getVersion());
        assertEquals(ID, header.getCommandId());
        assertEquals(CREATED, header.getCreated());
        assertEquals(SENDER, header.getSender());
        assertEquals(CONTENT, header.getContentType());


        header.setVersion(VERSION2);
        header.setContentType(CONTENT2);

        assertEquals(VERSION2, header.getVersion());
        assertEquals(CONTENT2, header.getContentType());

    }

    @Test
    void testIsEmpty() {
        Header header = new Header();
        assertTrue(header.isEmpty());

        header = new Header().setVersion("")
                .setCommandId("").setCreated("").setSender("");

        assertTrue(header.isEmpty());
    }

}
