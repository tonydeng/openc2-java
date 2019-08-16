package com.github.tonydeng.openc2;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.tonydeng.openc2.header.Header;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * @author dengtao
 **/
@Slf4j
public class OpenC2MessageTest {

    @Test
    void test() {
        OpenC2Message message = OpenC2Message.builder()
                .header(new Header()).build();

        log.info("{}", JSON.toJSONString(message, SerializerFeature.PrettyFormat));

        assertFalse(message.hasHeader());

    }
}
