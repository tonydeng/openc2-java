package com.github.tonydeng.openc2.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Tony Deng
 * @version V1.0
 * @date 2019-08-17 21:56
 **/
@Slf4j
public class StatusCodeTest {
    @Test
    void test() {
        assertEquals(200, StatusCode.OK.getValue());
        assertEquals("200", StatusCode.OK.toString());
    }

    @Test
    void testToJSON() throws JsonProcessingException {
        assertEquals("\"" + StatusCode.OK.name() + "\"", new ObjectMapper().writeValueAsString(StatusCode.OK));
        log.info("{}", new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(StatusCode.OK));
    }
}
