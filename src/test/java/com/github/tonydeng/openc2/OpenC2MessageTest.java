package com.github.tonydeng.openc2;

import static org.junit.jupiter.api.Assertions.assertFalse;

import com.github.tonydeng.openc2.action.ActionType;
import com.github.tonydeng.openc2.actuators.ActuatorType;
import com.github.tonydeng.openc2.actuators.NetworkSensor;
import com.github.tonydeng.openc2.args.Args;
import com.github.tonydeng.openc2.header.Header;
import com.github.tonydeng.openc2.json.JsonFormatter;
import com.github.tonydeng.openc2.targets.IpAddr;
import com.github.tonydeng.openc2.targets.TargetType;
import com.github.tonydeng.openc2.utilities.Keys;
import com.github.tonydeng.openc2.utilities.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author dengtao
 **/
@Slf4j
public class OpenC2MessageTest {
    private static final String IP_VALUE = "1.2.3.4";
    private static final String ID_VALUE = "TEST-id-1";
    private static final String ARG1_KEY = "start_time";
    private static final String ARG1_VALUE = "now";
    private static final String ARG2_KEY = "response_requested";
    private static final String ARG2_VALUE = "Ack";
    private static final String ENDPOINT_VALUE = "router";
    private static final String CONTENT_VALUE = "context";
    private static final String VERSION_VALUE = "0.1.0";

    private static final String test1Json = "{\"action\":\"copy\",\"target\":{\"ip_addr\":\"1.2.3.4\"}}";
    private static final String test2Json = "{\"id\":\"TEST-id-1\",\"action\":\"copy\",\"target\":{\"ip_addr\":\"1.2.3.4\"}}";
    private static final String test3Json = "{\"id\":\"TEST-id-1\",\"action\":\"copy\",\"target\":{\"ip_addr\":\"1.2.3.4\"},\"actuator\":{\"endpoint\":{\"actuator_id\":\"router\"}}}";
    private static final String test4Json = "{\"id\":\"TEST-id-1\",\"action\":\"copy\",\"target\":{\"ip_addr\":\"1.2.3.4\"},\"args\":{\"start_time\":\"now\",\"response_requested\":\"Ack\"}}";
    private static final String test5Json = "{\"header\":{\"version\":\"0.1.0\",\"id\":\"TEST-id-1\",\"content_type\":\"context\"},\"command\":{\"action\":\"copy\",\"target\":{\"ip_addr\":\"1.2.3.4\"}}}";
    private static final String test6Json = "{\"header\":{\"version\":\"0.1.0\",\"id\":\"TEST-id-1\",\"content_type\":\"context\"},\"command\":{\"action\":\"copy\",\"target\":{\"ip_addr\":\"1.2.3.4\"},\"args\":{\"start_time\":\"now\",\"response_requested\":\"Ack\"},\"actuator\":{\"endpoint\":{\"actuator_id\":\"router\"}}}}";
    private static final String test7Json = "{\"action\":\"copy\",\"target\":{\"artifact\":{\"mime_type\":\"Test mime\",\"payload_bin\":[10,15,20],\"url\":\"test url\",\"hashes\":{\"hashkey2\":\"value2\",\"hashkey1\":\"value1\"}}},\"actuator\":{\"network_sensor\":{\"name\":\"cisco\",\"path\":\"www.router.com\"}}}";
    private static final String test8Json = "{\"header\":{\"version\":\"0.1.0\",\"id\":\"TEST-id-1\",\"content_type\":\"context\"},\"command\":{\"id\":\"TEST-id-1\",\"action\":\"copy\",\"target\":{\"artifact\":{\"mime_type\":\"Test mime\",\"payload_bin\":[10,15,20],\"url\":\"test url\",\"hashes\":{\"hashkey2\":\"value2\",\"hashkey1\":\"value1\"}}},\"actuator\":{\"network_sensor\":{\"name\":\"cisco\",\"path\":\"www.router.com\"}}}}";


    @Test
    void testHeader() {
        OpenC2Message message = new OpenC2Message();
        message.setHeader(new Header());


        assertFalse(message.hasHeader());

    }

    @Test
    public void testCodeCoverage() throws Exception {
        new Keys();
        new JsonFormatter();
        StatusCode.valueOf("OK");
        StatusCode.OK.toString();
        TargetType.values();
        TargetType.valueOf("ARTIFACT");
        ActuatorType.values();
        ActuatorType.valueOf("ENDPOINT");

        OpenC2Message message = new OpenC2Message("", ActionType.COPY, new IpAddr(IP_VALUE))
                .setActuator(new NetworkSensor())
                .setArgs(new Args());
        log.info("{}", message.toPrettyJson());
        assertFalse(message.hasId());        // test empty id
        assertFalse(message.hasActuator()); // test empty actuator
        assertFalse(message.hasArgs());     // test empty args
    }
}
