package com.github.tonydeng.openc2.actuators;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tonydeng.openc2.OpenC2Command;
import com.github.tonydeng.openc2.action.ActionType;
import com.github.tonydeng.openc2.targets.IpAddr;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import lombok.var;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void test() throws IOException {
        val target = new IpAddr(IP_VALUE);
        val actuator = new Endpoint(ENDPOINT_VALUE);
        var message = new OpenC2Command(ActionType.DENY, target);
        message.setActuator(actuator);
        log.info("{}", message.toJson());

        val expected = new ObjectMapper().readTree(expect);
        val actual = new ObjectMapper().readTree(message.toJson());

        assertEquals(expected, actual);
    }
}
