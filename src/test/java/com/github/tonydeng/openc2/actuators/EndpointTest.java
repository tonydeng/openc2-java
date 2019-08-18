package com.github.tonydeng.openc2.actuators;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tonydeng.openc2.OpenC2Message;
import com.github.tonydeng.openc2.action.ActionType;
import com.github.tonydeng.openc2.targets.IpAddr;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author Tony Deng
 * @version V1.0
 * @date 2019-08-18 00:24
 **/
@Slf4j
public class EndpointTest {
    private static final boolean toConsole = false;
    private static final String IP_VALUE = "1.2.3.4";
    private static final String ENDPOINT_VALUE = "router";

    private static final String expect = "{\"action\":\"deny\",\"target\":{\"ip_addr\":\"1.2.3.4\"},\"actuator\":{\"endpoint\":{\"actuator_id\":\"router\"}}}";


    @Test
    void test() throws JsonProcessingException {
        IpAddr target = new IpAddr(IP_VALUE);
        Endpoint actuator = new Endpoint(ENDPOINT_VALUE);
        OpenC2Message message = new OpenC2Message(ActionType.DENY, target).setActuator(actuator);
        log.info("{}", message.toJson());
//    JSONObject expected = JSON.parseObject(expect);
//    JSONObject actual = JSON.parseObject(message.toJson());
//    assertEquals(expected, actual);
    }
}
