package com.github.tonydeng.openc2.actuators;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tonydeng.openc2.OpenC2Command;
import com.github.tonydeng.openc2.action.ActionType;
import com.github.tonydeng.openc2.json.JsonFormatter;
import com.github.tonydeng.openc2.targets.IpAddr;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Tony Deng
 * @version V1.0
 * @date 2019-08-18 20:47
 **/
public class NetworkTest {
    private static final boolean toConsole = false;
    private static final String IP_VALUE = "1.2.3.4";
    private static final String NETWORK_VALUE = "router";

    private static final String expect = "{\"action\":\"deny\",\"target\":{\"ip_addr\":\"1.2.3.4\"},\"actuator\":{\"network\":\"router\"}}";

    @Test
    void test() throws IOException {
        IpAddr target = new IpAddr(IP_VALUE);
        Network actuator = new Network(NETWORK_VALUE);
        OpenC2Command message = new OpenC2Command(ActionType.DENY, target);
        message.setActuator(actuator);

        JsonNode expected = new ObjectMapper().readTree(expect);
        JsonNode actual = new ObjectMapper().readTree(message.toJson());
        assertEquals(expected, actual);


        OpenC2Command inMsg = JsonFormatter.readOpenC2Message(expect);
        assertTrue(inMsg.getActuator() instanceof Network);
        Network inActuator = (Network) inMsg.getActuator();
        assertEquals(NETWORK_VALUE, inActuator.getNetwork());
    }
}