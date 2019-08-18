package com.github.tonydeng.openc2.actuators;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tonydeng.openc2.OpenC2Message;
import com.github.tonydeng.openc2.action.ActionType;
import com.github.tonydeng.openc2.json.JsonFormatter;
import com.github.tonydeng.openc2.targets.IpAddr;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Tony Deng
 * @version V1.0
 * @date 2019-08-18 20:49
 **/
@Slf4j
public class NetworkSensorTest {
    private static final String IP_VALUE = "1.2.3.4";
    private static final String NAME_VALUE = "cisco";
    private static final String PATH_VALUE = "www.router.com";

    private static final String expect = "{\"action\":\"deny\",\"target\":{\"ip_addr\":\"1.2.3.4\"},\"actuator\":{\"network_sensor\":{\"name\":\"cisco\",\"path\":\"www.router.com\"}}}";

    @Test
    void test() throws IOException {
        IpAddr target = new IpAddr(IP_VALUE);
        NetworkSensor actuator = new NetworkSensor().setName(NAME_VALUE).setPath(PATH_VALUE);
        OpenC2Message message = new OpenC2Message(ActionType.DENY, target).setActuator(actuator);

        log.info("{}", message.toPrettyJson());

        JsonNode expected = new ObjectMapper().readTree(expect);
        JsonNode actual = new ObjectMapper().readTree(message.toJson());
        assertEquals(expected, actual);


        OpenC2Message inMsg = JsonFormatter.readOpenC2Message(expect);
        assertTrue(inMsg.getActuator() instanceof NetworkSensor);
        NetworkSensor inActuator = (NetworkSensor) inMsg.getActuator();
        assertEquals(NAME_VALUE, inActuator.getName());
        assertEquals(PATH_VALUE, inActuator.getPath());
    }
}
