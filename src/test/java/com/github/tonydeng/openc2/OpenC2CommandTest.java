package com.github.tonydeng.openc2;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tonydeng.openc2.action.ActionType;
import com.github.tonydeng.openc2.actuators.ActuatorType;
import com.github.tonydeng.openc2.actuators.Endpoint;
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

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author dengtao
 **/
@Slf4j
public class OpenC2CommandTest {
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
        OpenC2Command message = new OpenC2Command();
        message.setHeader(new Header());


        assertFalse(message.hasHeader());

    }

    @Test
    public void testCodeCoverage() throws Exception {
        StatusCode.valueOf("OK");
        StatusCode.OK.toString();
        TargetType.values();
        TargetType.valueOf("ARTIFACT");
        ActuatorType.values();
        ActuatorType.valueOf("ENDPOINT");

        OpenC2Command message = new OpenC2Command("", ActionType.COPY, new IpAddr(IP_VALUE))
                .setActuator(new NetworkSensor())
                .setArgs(new Args());
        log.info("{}", message.toPrettyJson());
        assertFalse(message.hasId());        // test empty id
        assertFalse(message.hasActuator()); // test empty actuator
        assertFalse(message.hasArgs());     // test empty args
    }

    @Test
    void testTest1Json() throws Exception {

        OpenC2Command message = new OpenC2Command(ActionType.COPY, new IpAddr(IP_VALUE));
        OpenC2Command message2 = JsonFormatter.readOpenC2Message(message.toJson());

        // Create JsonNode objects for comparison
        JsonNode messageJN = new ObjectMapper().readTree(message.toJson());
        JsonNode message2JN = new ObjectMapper().readTree(message2.toJson());
        JsonNode message3JN = new ObjectMapper().readTree(test1Json);
        JsonNode message4JN = new ObjectMapper().readTree(test2Json);

        assertEquals(messageJN, message2JN);  // Verify that the object created from a string is the same
        assertEquals(messageJN, message3JN);  // Verify that the object from an external JSON string is the same
        assertNotEquals(messageJN,message4JN);

        OpenC2Command inMsg = JsonFormatter.readOpenC2Message(test1Json);
        assertEquals(inMsg.getAction(), ActionType.COPY.toString());
        assertNull(inMsg.getId());
        assertTrue(inMsg.getTarget() instanceof IpAddr);
        IpAddr target = (IpAddr) inMsg.getTarget();
        assertEquals(IP_VALUE, target.getIpAddr());
        assertNull(inMsg.getActuator());
        assertNull(inMsg.getArgs());
    }

    @Test
     void testTestJson2() throws Exception {

        OpenC2Command message = new OpenC2Command(ActionType.COPY, new IpAddr(IP_VALUE)).setId(ID_VALUE);
        OpenC2Command message2 = JsonFormatter.readOpenC2Message(message.toJson());

        // Create JsonNode objects for comparison
        JsonNode messageJN = new ObjectMapper().readTree(message.toJson());
        JsonNode message2JN = new ObjectMapper().readTree(message2.toJson());
        JsonNode message3JN = new ObjectMapper().readTree(test2Json);
        JsonNode message4JN = new ObjectMapper().readTree(test1Json);

        assertEquals(messageJN, message2JN);  // Verify that the object created from a string is the same
        assertEquals(messageJN, message3JN);  // Verify that the object from an external JSON string is the same
        assertNotEquals(messageJN, message4JN); // Verify that two different objects are not equal


        OpenC2Command inMsg = JsonFormatter.readOpenC2Message(test2Json);
        assertEquals(inMsg.getAction(), ActionType.COPY.toString());
        assertEquals(ID_VALUE, inMsg.getId());
        assertTrue(inMsg.getTarget() instanceof IpAddr);
        IpAddr target = (IpAddr)inMsg.getTarget();
        assertEquals(IP_VALUE, target.getIpAddr());
        assertNull(inMsg.getActuator());
        assertNull(inMsg.getArgs());
    }

    @Test
     void testTest3() throws Exception {

        OpenC2Command message = new OpenC2Command(ActionType.COPY, new IpAddr(IP_VALUE)).setId(ID_VALUE).setActuator(new Endpoint(ENDPOINT_VALUE));
        OpenC2Command message2 = JsonFormatter.readOpenC2Message(message.toJson());

        // Create JsonNode objects for comparison
        JsonNode messageJN = new ObjectMapper().readTree(message.toJson());
        JsonNode message2JN = new ObjectMapper().readTree(message2.toJson());
        JsonNode message3JN = new ObjectMapper().readTree(test3Json);
        JsonNode message4JN = new ObjectMapper().readTree(test1Json);

        assertEquals(messageJN, message2JN);  // Verify that the object created from a string is the same
        assertEquals(messageJN, message3JN);  // Verify that the object from an external JSON string is the same
        assertNotEquals(messageJN,message4JN); // Verify that two different objects are not equal


        OpenC2Command inMsg = JsonFormatter.readOpenC2Message(test3Json);
        assertEquals(inMsg.getAction(), ActionType.COPY.toString());
        assertEquals(ID_VALUE, inMsg.getId());
        assertTrue(inMsg.getTarget() instanceof IpAddr);
        IpAddr target = (IpAddr)inMsg.getTarget();
        assertEquals(IP_VALUE, target.getIpAddr());
        assertNotNull(inMsg.getActuator());
        assertTrue(inMsg.getActuator() instanceof Endpoint);
        Endpoint actuator = (Endpoint)inMsg.getActuator();
        assertEquals(ENDPOINT_VALUE, actuator.getEndpoint());
        assertNull(inMsg.getArgs());
    }

    @Test
    public void testTest4() throws Exception {

        OpenC2Command message = new OpenC2Command(ActionType.COPY, new IpAddr(IP_VALUE))
                .setId(ID_VALUE)
                .setArgs(new Args()
                        .addArg(ARG1_KEY, ARG1_VALUE)
                        .addArg(ARG2_KEY, ARG2_VALUE));
        OpenC2Command message2 = JsonFormatter.readOpenC2Message(message.toJson());

        // Create JsonNode objects for comparison
        JsonNode messageJN = new ObjectMapper().readTree(message.toJson());
        JsonNode message2JN = new ObjectMapper().readTree(message2.toJson());
        JsonNode message3JN = new ObjectMapper().readTree(test4Json);
        JsonNode message4JN = new ObjectMapper().readTree(test1Json);

        assertEquals(messageJN, message2JN);  // Verify that the object created from a string is the same
        assertEquals(messageJN, message3JN);  // Verify that the object from an external JSON string is the same
        assertNotEquals(messageJN,message4JN); // Verify that two different objects are not equal

        OpenC2Command inMsg = JsonFormatter.readOpenC2Message(test4Json);
        assertEquals(inMsg.getAction(), ActionType.COPY.toString());
        assertEquals(ID_VALUE, inMsg.getId());
        assertTrue(inMsg.getTarget() instanceof IpAddr);
        IpAddr target = (IpAddr)inMsg.getTarget();
        assertEquals(IP_VALUE, target.getIpAddr());
        assertNull(inMsg.getActuator());
        assertNotNull(inMsg.getArgs());
        Args args = (Args)inMsg.getArgs();
        assertEquals(ARG1_VALUE, args.getArg(ARG1_KEY));
        assertEquals(ARG2_VALUE, args.getArg(ARG2_KEY));
    }

    @Test
    public void testTest5() throws Exception {

        OpenC2Command message = new OpenC2Command(ActionType.COPY, new IpAddr(IP_VALUE)).setHeader(new Header(VERSION_VALUE, CONTENT_VALUE).setCommandId(ID_VALUE));

        OpenC2Command message2 = JsonFormatter.readOpenC2Message(message.toJson());

        // Create JsonNode objects for comparison
        JsonNode messageJN = new ObjectMapper().readTree(message.toJson());
        JsonNode message2JN = new ObjectMapper().readTree(message2.toJson());
        JsonNode message3JN = new ObjectMapper().readTree(test5Json);
        JsonNode message4JN = new ObjectMapper().readTree(test2Json);

        assertEquals(messageJN, message2JN);  // Verify that the object created from a string is the same
        assertEquals(messageJN, message3JN);  // Verify that the object from an external JSON string is the same
        assertNotEquals(messageJN,message4JN); // Verify that two different objects are not equal


        OpenC2Command inMsg = JsonFormatter.readOpenC2Message(test5Json);
        assertTrue(inMsg.hasHeader());
        assertEquals(VERSION_VALUE, inMsg.getHeader().getVersion());
        assertEquals(ID_VALUE, inMsg.getHeader().getCommandId());
        assertEquals(CONTENT_VALUE, inMsg.getHeader().getContentType());
        assertEquals(inMsg.getAction(), ActionType.COPY.toString());
        assertNull(inMsg.getId());
        assertTrue(inMsg.getTarget() instanceof IpAddr);
        IpAddr target = (IpAddr)inMsg.getTarget();
        assertEquals(IP_VALUE, target.getIpAddr());
        assertNull(inMsg.getActuator());
        assertNull(inMsg.getArgs());
    }

    @Test
     void testTest6() throws Exception {
        OpenC2Command inMsg = JsonFormatter.readOpenC2Message(test6Json);

        assertTrue(inMsg.hasHeader());
        assertEquals(VERSION_VALUE, inMsg.getHeader().getVersion());
        assertEquals(ID_VALUE, inMsg.getHeader().getCommandId());
        assertEquals(CONTENT_VALUE, inMsg.getHeader().getContentType());
        assertEquals(inMsg.getAction(), ActionType.COPY.toString());
        assertNull(inMsg.getId());
        assertTrue(inMsg.getTarget() instanceof IpAddr);
        IpAddr target = (IpAddr)inMsg.getTarget();
        assertEquals(IP_VALUE, target.getIpAddr());
        assertTrue(inMsg.hasActuator());
        assertTrue(inMsg.getActuator() instanceof Endpoint);
        Endpoint actuator = (Endpoint)inMsg.getActuator();
        assertEquals(ENDPOINT_VALUE, actuator.getEndpoint());
        Args args = (Args)inMsg.getArgs();
        assertEquals(ARG1_VALUE, args.getArg(ARG1_KEY));
        assertEquals(ARG2_VALUE, args.getArg(ARG2_KEY));

        // Test to read target and actuators that are objects
        // Just reading the JSON string is success
        inMsg = JsonFormatter.readOpenC2Message(test7Json);
        inMsg = JsonFormatter.readOpenC2Message(test8Json);
    }

}
