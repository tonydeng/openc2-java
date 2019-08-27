package com.github.tonydeng.openc2.targets;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tonydeng.openc2.OpenC2Command;
import com.github.tonydeng.openc2.action.ActionType;
import com.github.tonydeng.openc2.json.JsonFormatter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Tony Deng
 * @version V1.0
 * @date 2019-08-18 18:49
 **/
@Slf4j
public class FileTest {
    private static final String NAME_VALUE = "testfile";
    private static final String PATH_VALUE = "/tmp/testfile";
    //	private static final String HASH_VALUE = "123456789";
    private static final String HASHKEY1_VALUE = "hashkey1";
    private static final String VALUE1_VALUE = "value1";
    private static final String HASHKEY2_VALUE = "hashkey2";
    private static final String VALUE2_VALUE = "value2";
    private static final Map<String, Object> MAP_VALUE;

    private static final String expect = "{\"action\":\"deny\",\"target\":{\"file\":{\"name\":\"testfile\"}}}";
    private static final String expect2 = "{\"action\":\"deny\",\"target\":{\"file\":{\"path\":\"/tmp/testfile\"}}}";
    private static final String expect3 = "{\"action\":\"deny\",\"target\":{\"file\":{\"hashes\":{\"hashkey2\":\"value2\",\"hashkey1\":\"value1\"}}}}";
    private static final String expect4 = "{\"action\":\"deny\",\"target\":{\"file\":{\"name\":\"testfile\",\"path\":\"/tmp/testfile\",\"hashes\":{\"hashkey2\":\"value2\",\"hashkey1\":\"value1\"}}}}";

    static {
        MAP_VALUE = new HashMap<String, Object>();
        MAP_VALUE.put(HASHKEY1_VALUE, VALUE1_VALUE);
        MAP_VALUE.put(HASHKEY2_VALUE, VALUE2_VALUE);
    }

    @Test
    void test() throws IOException {
        File target = new File().setName(NAME_VALUE);
        OpenC2Command message = new OpenC2Command(ActionType.DENY, target);
        log.info("{}", message.toPrettyJson());
        JsonNode expected = new ObjectMapper().readTree(expect);
        JsonNode actual = new ObjectMapper().readTree(message.toJson());
        assertEquals(expected, actual);
    }

    @Test
    void testGetName() throws IOException {
        OpenC2Command inMsg = JsonFormatter.readOpenC2Command(expect);
        assertTrue(inMsg.getTarget() instanceof File);
        File inTarget = (File) inMsg.getTarget();

        assertEquals(NAME_VALUE, inTarget.getName());
    }

    @Test
    void testHash() throws IOException {
        File target = new File().setHash(MAP_VALUE);
        OpenC2Command message = new OpenC2Command(ActionType.DENY, target);

        JsonNode expected = new ObjectMapper().readTree(expect3);
        JsonNode actual = new ObjectMapper().readTree(message.toJson());
        assertEquals(expected, actual);


        OpenC2Command inMsg = JsonFormatter.readOpenC2Command(expect3);
        assertTrue(inMsg.getTarget() instanceof File);
        File inTarget = (File) inMsg.getTarget();
        assertEquals(MAP_VALUE, inTarget.getHashes());
    }

    @Test
    void testAll() throws IOException {
        File target = new File().setName(NAME_VALUE).setPath(PATH_VALUE).setHash(MAP_VALUE);
        OpenC2Command message = new OpenC2Command(ActionType.DENY, target);

        JsonNode expected = new ObjectMapper().readTree(expect4);
        JsonNode actual = new ObjectMapper().readTree(message.toJson());
        assertEquals(expected, actual);

        OpenC2Command inMsg = JsonFormatter.readOpenC2Command(expect4);
        assertTrue(inMsg.getTarget() instanceof File);
        File inTarget = (File)inMsg.getTarget();
        assertEquals(NAME_VALUE, inTarget.getName());
        assertEquals(PATH_VALUE, inTarget.getPath());
        assertEquals(MAP_VALUE, inTarget.getHashes());
    }
}
