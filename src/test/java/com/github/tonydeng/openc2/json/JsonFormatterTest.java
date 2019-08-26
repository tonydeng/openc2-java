package com.github.tonydeng.openc2.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tonydeng.openc2.OpenC2Command;
import com.github.tonydeng.openc2.OpenC2Message;
import com.github.tonydeng.openc2.OpenC2Response;
import com.github.tonydeng.openc2.action.ActionType;
import com.github.tonydeng.openc2.actuators.NetworkSensor;
import com.github.tonydeng.openc2.args.Args;
import com.github.tonydeng.openc2.targets.IpAddr;
import com.github.tonydeng.openc2.targets.TargetType;
import com.github.tonydeng.openc2.utilities.StatusCode;
import com.github.tonydeng.openc2.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author dengtao
 **/
@Slf4j
public class JsonFormatterTest {

    private static String IP_VAULE = "1.2.3.4";

    private static OpenC2Message command;

    private static String commandJson;

    private static OpenC2Message response;

    @BeforeAll
    static void init() throws JsonProcessingException {
        command = OpenC2Command.builder().action(ActionType.COPY).target(new IpAddr(IP_VAULE))
                .actuator(new NetworkSensor()).args(new Args())
                .build();
        commandJson = JsonFormatter.getJson(command, false);

        response = new OpenC2Response();
    }

    @Test
    void testCommandToJson() throws JsonProcessingException {
        val json = JsonFormatter.getJson(command, true);
        assertNotNull(json);
        log.info("{}", json);
    }

    @Test
    void testJsonToCommand() throws IOException {
        val inCommand = JsonFormatter.readOpenC2Command(commandJson);
        val target = (IpAddr) inCommand.getTarget();
        assertEquals(IP_VAULE, target.getIpAddr());
        assertEquals(commandJson, inCommand.toJson());
    }

    @Test
    void testReadOpenC2Command() throws IOException {
        val inCommand = JsonFormatter.readOpenC2Command(FileUtils.readResourcesFile("command/test1.json"));

        assertNotNull(inCommand);

        assertEquals(ActionType.COPY.toString(), inCommand.getAction());
        assertEquals("target", inCommand.getTarget().getSectionType());
        assertEquals(TargetType.IP_ADDR.toString(), inCommand.getTarget().getObjectType());
        assertEquals(IP_VAULE, ((IpAddr) inCommand.getTarget()).getIpAddr());
    }

    @Test
    void testReadOpenC2Response() throws IOException {
        val inResponse = JsonFormatter.readOpenC2Response(FileUtils.readResourcesFile("response/expected1.json"));

        assertNotNull(inResponse);

        assertEquals(StatusCode.OK.getValue(), inResponse.getStatus());

        assertEquals("CommandResp", inResponse.getId());
        assertEquals("complete", inResponse.getIdRef());
        assertNull(inResponse.getStatusText());
        assertNull(inResponse.getResults());
    }
}
