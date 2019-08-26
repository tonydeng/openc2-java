package com.github.tonydeng.openc2.targets;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tonydeng.openc2.OpenC2Command;
import com.github.tonydeng.openc2.action.ActionType;
import com.github.tonydeng.openc2.json.JsonFormatter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Tony Deng
 * @version V1.0
 * @date 2019-08-18 19:27
 **/
@Slf4j
public class DomainNameTest {
    private static final String DOMAIN_VALUE = "www.my.domain";

    private static final String expect = "{\"action\":\"locate\",\"target\":{\"domain_name\":\"www.my.domain\"}}";


    @Test
    void test() throws IOException {
        DomainName target = new DomainName(DOMAIN_VALUE);
        OpenC2Command message = new OpenC2Command(ActionType.LOCATE, target);

        JsonNode expected = new ObjectMapper().readTree(expect);
        JsonNode actual = new ObjectMapper().readTree(message.toJson());

        assertEquals(expected, actual);

        OpenC2Command inMsg = JsonFormatter.readOpenC2Command(expect);
        assertTrue(inMsg.getTarget() instanceof DomainName);
        DomainName inTarget = (DomainName) inMsg.getTarget();
        assertEquals(DOMAIN_VALUE, inTarget.getDomainName());

        log.info("{}", message.toPrettyJson());
    }
}
