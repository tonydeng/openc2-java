package com.github.tonydeng.openc2.targets;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tonydeng.openc2.OpenC2Message;
import com.github.tonydeng.openc2.action.ActionType;
import com.github.tonydeng.openc2.json.JsonFormatter;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Tony Deng
 * @version V1.0
 * @date 2019-08-18 20:41
 **/
public class ArtifactTest {
    private static final String MIME_VALUE = "Test mime";
    private static final Byte[] PAYLOAD_VALUE = {10,15,20};
    private static final String URL_VALUE = "test url";
    private static final String HASHKEY1_VALUE = "hashkey1";
    private static final String VALUE1_VALUE = "value1";
    private static final String HASHKEY2_VALUE = "hashkey2";
    private static final String VALUE2_VALUE = "value2";
    private static final Map<String, Object> MAP_VALUE;

    private static final String expect = "{\"action\":\"report\",\"target\":{\"artifact\":{\"mime_type\":\"Test mime\",\"payload_bin\":[10,15,20],\"url\":\"test url\",\"hashes\":{\"hashkey2\":\"value2\",\"hashkey1\":\"value1\"}}}}";

    static {
        MAP_VALUE = new HashMap<String, Object>();
        MAP_VALUE.put(HASHKEY1_VALUE, VALUE1_VALUE);
        MAP_VALUE.put(HASHKEY2_VALUE, VALUE2_VALUE);
    }

    @Test
    public void test1() throws Exception {

        Artifact target = new Artifact()
                .setMime(MIME_VALUE)
                .setPayloadBin(PAYLOAD_VALUE)
                .setUrl(URL_VALUE)
                .setHashes(MAP_VALUE);
        OpenC2Message message = new OpenC2Message(ActionType.REPORT, target);

        JsonNode expected = new ObjectMapper().readTree(expect);
        JsonNode actual = new ObjectMapper().readTree(message.toJson());
        assertEquals(expected, actual);


        OpenC2Message inMsg = JsonFormatter.readOpenC2Message(expect);
        assertTrue(inMsg.getTarget() instanceof Artifact);
        Artifact inTarget = (Artifact)inMsg.getTarget();
        assertEquals(MIME_VALUE, inTarget.getMime());
        assertArrayEquals(PAYLOAD_VALUE, inTarget.getPayloadBin());
        assertEquals(URL_VALUE, inTarget.getUrl());
        assertEquals(MAP_VALUE, inTarget.getHashes());

    }

}
