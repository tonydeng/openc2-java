package com.github.tonydeng.openc2.targets;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tonydeng.openc2.OpenC2Command;
import com.github.tonydeng.openc2.action.ActionType;
import com.github.tonydeng.openc2.json.JsonFormatter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Tony Deng
 * @version V1.0
 * @date 2019-08-18 10:26
 **/
@Slf4j
public class IpAddrTest {

    private static final String IP_VALUE = "1.2.3.4";
    private static final String expect = "{\"action\":\"deny\",\"target\":{\"ip_addr\":\"1.2.3.4\"}}";

    private static IpAddr target;
    private static OpenC2Command message;

    @BeforeAll
    static void setUp() {
        target = new IpAddr(IP_VALUE);
        message = new OpenC2Command(ActionType.DENY, target);
    }

    @Test
    void testJson() throws IOException {
        message = new OpenC2Command(ActionType.DENY, target);


        log.info("{}", message.toPrettyJson());

        JsonNode expected = new ObjectMapper().readTree(expect);
        JsonNode actual = new ObjectMapper().readTree(message.toJson());

        assertEquals(expected, actual);
    }


    @Test
    void testIpAddr() throws IOException {
        OpenC2Command inMsg = JsonFormatter.readOpenC2Message(message.toJson());
        assertTrue(inMsg.getTarget() instanceof IpAddr);
        IpAddr inTarget = (IpAddr) inMsg.getTarget();
        assertEquals(IP_VALUE, inTarget.getIpAddr());
    }

    @AfterAll
    static void clear() {
        target = null;
        message = null;
    }
}
