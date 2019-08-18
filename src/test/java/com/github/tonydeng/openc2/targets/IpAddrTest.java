package com.github.tonydeng.openc2.targets;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author Tony Deng
 * @version V1.0
 * @date 2019-08-18 10:26
 **/
@Slf4j
public class IpAddrTest {

  private static final String IP_VALUE = "1.2.3.4";

  @Test
  void test() {
    IpAddr target = new IpAddr(IP_VALUE);

  }
}
