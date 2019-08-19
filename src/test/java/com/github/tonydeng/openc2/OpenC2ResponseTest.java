package com.github.tonydeng.openc2;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tonydeng.openc2.header.Header;
import com.github.tonydeng.openc2.json.JsonFormatter;
import com.github.tonydeng.openc2.utilities.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author dengtao
 **/
@Slf4j
public class OpenC2ResponseTest {
    private static final String ID_VALUE = "TEST-id-1";
    private static final String VERSION_VALUE = "0.1.0";
    private static final String CONTENT_VALUE = "context";
    private static final String STATUS_TEXT_VALUE = "Successful";
    private static final String RESULTS_VALUE = "These are the results";

    private static String expected1 = "{\"id\":\"CommandResp\",\"id_ref\":\"complete\",\"status\":200}";
    private static String expected2 = "{\"id\":\"CommandResp\",\"id_ref\":\"complete\",\"status\":200,\"status_text\":\"Successful\",\"results\":\"These are the results\"}";
    private static String expected3 = "{\"header\":{\"version\":\"0.1.0\",\"id\":\"TEST-id-1\",\"content_type\":\"context\"},\"response\":{\"id\":\"CommandResp\",\"id_ref\":\"complete\",\"status\":200,\"status_text\":\"Successful\",\"results\":\"These are the results\"}}}";

    @Test
    public void testCodeCoverage() throws Exception {
        OpenC2Response response = new OpenC2Response("CommandResp", "complete", StatusCode.OK);
        response.setStatus(StatusCode.BAD_REQUEST);
        assertEquals(StatusCode.BAD_REQUEST.getValue(), response.getStatus());

        log.info("{}", response.toPrettyJson());
    }

    @Test
    public void test1() throws Exception {

        OpenC2Response response = new OpenC2Response("CommandResp", "complete", StatusCode.OK);
        OpenC2Response response2 = JsonFormatter.readOpenC2Response(response.toJson());
        OpenC2Response response3 = JsonFormatter.readOpenC2Response(expected1);


        JsonNode responseJN = new ObjectMapper().readTree(response.toJson());
        JsonNode response2JN = new ObjectMapper().readTree(response2.toJson());
        JsonNode response3JN = new ObjectMapper().readTree(response3.toJson());

        assertEquals(responseJN, response2JN);
        assertEquals(responseJN, response3JN);
    }

    @Test
    public void test2() throws Exception {

        OpenC2Response response = new OpenC2Response("CommandResp", "complete", StatusCode.OK, "Successful", "These are the results");
        OpenC2Response response2 = JsonFormatter.readOpenC2Response(response.toJson());
        OpenC2Response response3 = JsonFormatter.readOpenC2Response(expected2);

        JsonNode responseJN = new ObjectMapper().readTree(response.toJson());
        JsonNode response2JN = new ObjectMapper().readTree(response2.toJson());
        JsonNode response3JN = new ObjectMapper().readTree(response3.toJson());

        assertEquals(responseJN, response2JN);  // Verify that the object created from a string is the same
        assertEquals(responseJN, response3JN);  // Verify that the object from an external JSON string is the same

    }

    @Test
    public void test3() throws Exception {

        OpenC2Response response = new OpenC2Response("CommandResp", "complete", StatusCode.OK)
                .setStatusText(STATUS_TEXT_VALUE)
                .setResults(RESULTS_VALUE)
                .setHeader(new Header(VERSION_VALUE, CONTENT_VALUE)
                        .setCommandId(ID_VALUE));
        OpenC2Response response2 = JsonFormatter.readOpenC2Response(response.toJson());
        OpenC2Response response3 = JsonFormatter.readOpenC2Response(expected3);

        log.info("response json \n {}",response.toPrettyJson());
        log.info("response2 json \n {}",response2.toPrettyJson());
        log.info("response3 json \n {}",response3.toPrettyJson());

        JsonNode responseJN = new ObjectMapper().readTree(response.toJson());
        JsonNode response2JN = new ObjectMapper().readTree(response2.toJson());
        JsonNode response3JN = new ObjectMapper().readTree(response3.toJson());

        assertEquals(responseJN, response2JN);
        assertEquals(responseJN, response3JN);

        OpenC2Response inMsg = JsonFormatter.readOpenC2Response(expected3);
        assertTrue(inMsg.hasHeader());
        assertEquals(ID_VALUE, inMsg.getHeader().getCommandId());
        assertEquals(CONTENT_VALUE, inMsg.getHeader().getContentType());
    }
}
