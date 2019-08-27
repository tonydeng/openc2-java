package com.github.tonydeng.openc2.header;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import lombok.var;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    static Header header;

    @BeforeAll
    static void setUp() {
        header = Header.builder()
                .commandId(ID).version(VERSION)
                .contentType(CONTENT).created(CREATED)
                .sender(SENDER).build();
    }

    @Test
    void testValueEquals() {

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
        var header = new Header();
        assertTrue(header.isEmpty());

        header = Header.builder()
                .commandId("").version("")
                .contentType("").created("")
                .build();


        assertTrue(header.isEmpty());
    }

    @Test
    void testEquals() {
        val header2 = new Header(ID, VERSION, CONTENT, CREATED, SENDER);

        assertTrue(header.equals(header2));

        header2.setCommandId("abc");
        header2.setContentType(CONTENT2);
        header2.setVersion(VERSION2);

        assertFalse(header.equals(header2));
    }

    @Test
    void testHashCode() {
        val header2 = new Header();
        assertNotNull(header2.hashCode());
        log.info("{}", header2.hashCode());
    }
}
