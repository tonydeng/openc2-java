package com.github.tonydeng.openc2;

import static org.junit.jupiter.api.Assertions.assertFalse;

import com.github.tonydeng.openc2.header.Header;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author dengtao
 **/
@Slf4j
public class OpenC2MessageTest {

    @Test
    void test() {
        OpenC2Message message = new OpenC2Message();
        message.setHeader(new Header());


        assertFalse(message.hasHeader());

    }
}
