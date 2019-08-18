package com.github.tonydeng.openc2.utilities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

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
  void testToJSON() {
//    assertEquals("\""+StatusCode.OK.name()+"\"", JSON.toJSONString(StatusCode.OK));
//    log.info("{}", JSON.toJSONString(StatusCode.OK, SerializerFeature.PrettyFormat));
  }
}
