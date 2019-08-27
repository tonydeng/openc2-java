package com.github.tonydeng.openc2;

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
import com.github.tonydeng.openc2.utilities.StatusCode;
import com.github.tonydeng.openc2.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import lombok.var;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

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

    private static String test1Json;
    private static String test2Json;
    private static String test3Json;
    private static String test4Json;
    private static String test5Json;
    private static String test6Json;
    private static String test7Json;
    private static String test8Json;


    @BeforeAll
    static void setUp() throws IOException {
        test1Json = FileUtils.readResourcesFile("command/test1.json");
        test2Json = FileUtils.readResourcesFile("command/test2.json");
        test3Json = FileUtils.readResourcesFile("command/test3.json");
        test4Json = FileUtils.readResourcesFile("command/test4.json");
        test5Json = FileUtils.readResourcesFile("command/test5.json");
        test6Json = FileUtils.readResourcesFile("command/test6.json");
        test7Json = FileUtils.readResourcesFile("command/test7.json");
        test8Json = FileUtils.readResourcesFile("command/test8.json");
//
    }

    @Test
    void testHeader() {
        val message = new OpenC2Command();
        message.setHeader(new Header());


        assertFalse(message.hasHeader());

    }

    @Test
    void testCodeCoverage() throws Exception {
        StatusCode.valueOf("OK");
        StatusCode.OK.toString();
        TargetType.values();
        TargetType.valueOf("ARTIFACT");
        ActuatorType.values();
        ActuatorType.valueOf("ENDPOINT");

        val message = new OpenC2Command("", ActionType.COPY, new IpAddr(IP_VALUE));
        message.setActuator(new NetworkSensor());
        message.setArgs(new Args());
        log.info("{}", message.toPrettyJson());
        assertFalse(message.hasId());        // test empty id
        assertFalse(message.hasActuator()); // test empty actuator
        assertFalse(message.hasArgs());     // test empty args
    }

    @Test
    void testTest1Json() throws Exception {

        val message = new OpenC2Command(ActionType.COPY, new IpAddr(IP_VALUE));
        val message2 = JsonFormatter.readOpenC2Command(message.toJson());

        // Create JsonNode objects for comparison
        val messageJN = new ObjectMapper().readTree(message.toJson());
        val message2JN = new ObjectMapper().readTree(message2.toJson());
        val message3JN = new ObjectMapper().readTree(test1Json);
        val message4JN = new ObjectMapper().readTree(test2Json);

        assertEquals(messageJN, message2JN);  // Verify that the object created from a string is the same
        assertEquals(messageJN, message3JN);  // Verify that the object from an external JSON string is the same
        assertNotEquals(messageJN, message4JN);

        val inMsg = JsonFormatter.readOpenC2Command(test1Json);
        assertEquals(inMsg.getAction(), ActionType.COPY.toString());
        assertNull(inMsg.getId());
        assertTrue(inMsg.getTarget() instanceof IpAddr);
        val target = (IpAddr) inMsg.getTarget();
        assertEquals(IP_VALUE, target.getIpAddr());
        assertNull(inMsg.getActuator());
        assertNull(inMsg.getArgs());
    }

    @Test
    void testTestJson2() throws Exception {

        val message = new OpenC2Command(ActionType.COPY, new IpAddr(IP_VALUE));
        message.setId(ID_VALUE);
        val message2 = JsonFormatter.readOpenC2Command(message.toJson());

        // Create JsonNode objects for comparison
        val messageJN = new ObjectMapper().readTree(message.toJson());
        val message2JN = new ObjectMapper().readTree(message2.toJson());
        val message3JN = new ObjectMapper().readTree(test2Json);
        val message4JN = new ObjectMapper().readTree(test1Json);

        assertEquals(messageJN, message2JN);  // Verify that the object created from a string is the same
        assertEquals(messageJN, message3JN);  // Verify that the object from an external JSON string is the same
        assertNotEquals(messageJN, message4JN); // Verify that two different objects are not equal


        val inMsg = JsonFormatter.readOpenC2Command(test2Json);
        assertEquals(inMsg.getAction(), ActionType.COPY.toString());
        assertEquals(ID_VALUE, inMsg.getId());
        assertTrue(inMsg.getTarget() instanceof IpAddr);
        val target = (IpAddr) inMsg.getTarget();
        assertEquals(IP_VALUE, target.getIpAddr());
        assertNull(inMsg.getActuator());
        assertNull(inMsg.getArgs());
    }

    @Test
    void testTest3() throws Exception {

        val message = new OpenC2Command(ActionType.COPY, new IpAddr(IP_VALUE));
        message.setId(ID_VALUE);
        message.setActuator(new Endpoint(ENDPOINT_VALUE));
        val message2 = JsonFormatter.readOpenC2Command(message.toJson());

        // Create JsonNode objects for comparison
        val messageJN = new ObjectMapper().readTree(message.toJson());
        val message2JN = new ObjectMapper().readTree(message2.toJson());
        val message3JN = new ObjectMapper().readTree(test3Json);
        val message4JN = new ObjectMapper().readTree(test1Json);

        assertEquals(messageJN, message2JN);  // Verify that the object created from a string is the same
        assertEquals(messageJN, message3JN);  // Verify that the object from an external JSON string is the same
        assertNotEquals(messageJN, message4JN); // Verify that two different objects are not equal


        val inMsg = JsonFormatter.readOpenC2Command(test3Json);
        assertEquals(inMsg.getAction(), ActionType.COPY.toString());
        assertEquals(ID_VALUE, inMsg.getId());
        assertTrue(inMsg.getTarget() instanceof IpAddr);
        val target = (IpAddr) inMsg.getTarget();
        assertEquals(IP_VALUE, target.getIpAddr());
        assertNotNull(inMsg.getActuator());
        assertTrue(inMsg.getActuator() instanceof Endpoint);
        val actuator = (Endpoint) inMsg.getActuator();
        assertEquals(ENDPOINT_VALUE, actuator.getEndpoint());
        assertNull(inMsg.getArgs());
    }

    @Test
    public void testTest4() throws Exception {

        val message = new OpenC2Command(ActionType.COPY, new IpAddr(IP_VALUE));
        message.setId(ID_VALUE);
        message.setArgs(new Args()
                .addArg(ARG1_KEY, ARG1_VALUE)
                .addArg(ARG2_KEY, ARG2_VALUE));
        val message2 = JsonFormatter.readOpenC2Command(message.toJson());

        // Create JsonNode objects for comparison
        val messageJN = new ObjectMapper().readTree(message.toJson());
        val message2JN = new ObjectMapper().readTree(message2.toJson());
        val message3JN = new ObjectMapper().readTree(test4Json);
        val message4JN = new ObjectMapper().readTree(test1Json);

        assertEquals(messageJN, message2JN);  // Verify that the object created from a string is the same
        assertEquals(messageJN, message3JN);  // Verify that the object from an external JSON string is the same
        assertNotEquals(messageJN, message4JN); // Verify that two different objects are not equal

        val inMsg = JsonFormatter.readOpenC2Command(test4Json);
        assertEquals(inMsg.getAction(), ActionType.COPY.toString());
        assertEquals(ID_VALUE, inMsg.getId());
        assertTrue(inMsg.getTarget() instanceof IpAddr);
        val target = (IpAddr) inMsg.getTarget();
        assertEquals(IP_VALUE, target.getIpAddr());
        assertNull(inMsg.getActuator());
        assertNotNull(inMsg.getArgs());
        val args = (Args) inMsg.getArgs();
        assertEquals(ARG1_VALUE, args.getArg(ARG1_KEY));
        assertEquals(ARG2_VALUE, args.getArg(ARG2_KEY));
    }

    @Test
    public void testTest5() throws Exception {

        val message = OpenC2Command.builder()
                .action(ActionType.COPY).target(new IpAddr(IP_VALUE))
                .header(Header.builder().commandId(ID_VALUE).version(VERSION_VALUE).contentType(CONTENT_VALUE).build())
                .build();

        val message2 = JsonFormatter.readOpenC2Command(message.toJson());

        // Create JsonNode objects for comparison
        val messageJN = new ObjectMapper().readTree(message.toJson());
        val message2JN = new ObjectMapper().readTree(message2.toJson());
        val message3JN = new ObjectMapper().readTree(test5Json);
        val message4JN = new ObjectMapper().readTree(test2Json);

        assertEquals(messageJN, message2JN);  // Verify that the object created from a string is the same
        assertEquals(messageJN, message3JN);  // Verify that the object from an external JSON string is the same
        assertNotEquals(messageJN, message4JN); // Verify that two different objects are not equal


        val inMsg = JsonFormatter.readOpenC2Command(test5Json);
        assertTrue(inMsg.hasHeader());
        assertEquals(VERSION_VALUE, inMsg.getHeader().getVersion());
        assertEquals(ID_VALUE, inMsg.getHeader().getCommandId());
        assertEquals(CONTENT_VALUE, inMsg.getHeader().getContentType());
        assertEquals(inMsg.getAction(), ActionType.COPY.toString());
        assertNull(inMsg.getId());
        assertTrue(inMsg.getTarget() instanceof IpAddr);
        val target = (IpAddr) inMsg.getTarget();
        assertEquals(IP_VALUE, target.getIpAddr());
        assertNull(inMsg.getActuator());
        assertNull(inMsg.getArgs());
    }

    @Test
    void testTest6() throws Exception {
        var inMsg = JsonFormatter.readOpenC2Command(test6Json);

        assertTrue(inMsg.hasHeader());
        assertEquals(VERSION_VALUE, inMsg.getHeader().getVersion());
        assertEquals(ID_VALUE, inMsg.getHeader().getCommandId());
        assertEquals(CONTENT_VALUE, inMsg.getHeader().getContentType());
        assertEquals(inMsg.getAction(), ActionType.COPY.toString());
        assertNull(inMsg.getId());
        assertTrue(inMsg.getTarget() instanceof IpAddr);
        val target = (IpAddr) inMsg.getTarget();
        assertEquals(IP_VALUE, target.getIpAddr());
        assertTrue(inMsg.hasActuator());
        assertTrue(inMsg.getActuator() instanceof Endpoint);
        val actuator = (Endpoint) inMsg.getActuator();
        assertEquals(ENDPOINT_VALUE, actuator.getEndpoint());
        val args = (Args) inMsg.getArgs();
        assertEquals(ARG1_VALUE, args.getArg(ARG1_KEY));
        assertEquals(ARG2_VALUE, args.getArg(ARG2_KEY));

        // Test to read target and actuators that are objects
        // Just reading the JSON string is success
        inMsg = JsonFormatter.readOpenC2Command(test7Json);
        assertNotNull(inMsg);
        inMsg = JsonFormatter.readOpenC2Command(test8Json);
        assertNotNull(inMsg);
    }

}
