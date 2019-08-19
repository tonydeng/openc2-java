package com.github.tonydeng.openc2.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tonydeng.openc2.OpenC2Command;
import com.github.tonydeng.openc2.OpenC2Message;
import com.github.tonydeng.openc2.OpenC2Response;
import com.github.tonydeng.openc2.action.ActionType;
import com.github.tonydeng.openc2.actuators.NetworkSensor;
import com.github.tonydeng.openc2.args.Args;
import com.github.tonydeng.openc2.targets.IpAddr;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        command = new OpenC2Command("", ActionType.COPY, new IpAddr(IP_VAULE))
                .setActuator(new NetworkSensor())
                .setArgs(new Args());
        commandJson = JsonFormatter.getJson(command, false);

        response = new OpenC2Response();
    }

    @Test
    void testCommandToJson() throws JsonProcessingException {
        String json = JsonFormatter.getJson(command, true);
        assertNotNull(json);
        log.info("{}", json);
    }

    @Test
    void testJsonToCommand() throws IOException {
        OpenC2Command inCommand = JsonFormatter.readOpenC2Message(commandJson);
        IpAddr target = (IpAddr) inCommand.getTarget();
        assertEquals(IP_VAULE, target.getIpAddr());
        assertEquals(commandJson, inCommand.toJson());
    }
}
