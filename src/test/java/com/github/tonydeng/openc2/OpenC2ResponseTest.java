package com.github.tonydeng.openc2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tonydeng.openc2.header.Header;
import com.github.tonydeng.openc2.json.JsonFormatter;
import com.github.tonydeng.openc2.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.github.tonydeng.openc2.utilities.StatusCode.BAD_REQUEST;
import static com.github.tonydeng.openc2.utilities.StatusCode.OK;
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
    private static final String RESP_ID = "CommandResp";

    private static String expected1;
    private static String expected2;
    private static String expected3;

    @BeforeAll
    static void setUp() throws IOException {
        expected1 = FileUtils.readResourcesFile("response/expected1.json");
        expected2 = FileUtils.readResourcesFile("response/expected2.json");
        expected3 = FileUtils.readResourcesFile("response/expected3.json");
    }

    @Test
    void testCodeCoverage() throws Exception {
        val response = OpenC2Response.builder()
                .status(OK.getValue())
                .id("CommandResp")
                .build();
        assertEquals(OK.getValue(), response.getStatus());
        response.setStatus(BAD_REQUEST.getValue());
        assertEquals(BAD_REQUEST.getValue(), response.getStatus());

        log.info("{}", response.toPrettyJson());
    }

    @Test
    void test1() throws Exception {

        val response = OpenC2Response.builder().status(OK.getValue())
                .id(RESP_ID).idRef("complete")
                .build();

        val response2 = JsonFormatter.readOpenC2Response(response.toJson());
        val response3 = JsonFormatter.readOpenC2Response(expected1);


        val responseJN = new ObjectMapper().readTree(response.toJson());
        val response2JN = new ObjectMapper().readTree(response2.toJson());
        val response3JN = new ObjectMapper().readTree(response3.toJson());

        assertEquals(responseJN, response2JN);
        assertEquals(responseJN, response3JN);
    }

    @Test
    void test2() throws Exception {

        val response = OpenC2Response.builder().status(OK.getValue()).statusText("Successful")
                .id(RESP_ID).idRef("complete")
                .results(RESULTS_VALUE).build();

        val response2 = JsonFormatter.readOpenC2Response(response.toJson());
        val response3 = JsonFormatter.readOpenC2Response(expected2);

        val responseJN = new ObjectMapper().readTree(response.toJson());
        val response2JN = new ObjectMapper().readTree(response2.toJson());
        val response3JN = new ObjectMapper().readTree(response3.toJson());

        assertEquals(responseJN, response2JN);  // Verify that the object created from a string is the same
        assertEquals(responseJN, response3JN);  // Verify that the object from an external JSON string is the same

    }

    @Test
    void test3() throws Exception {

        OpenC2Response response = OpenC2Response.builder().status(OK.getValue())
                .id(RESP_ID).idRef("complete").statusText(STATUS_TEXT_VALUE)
                .results(RESULTS_VALUE)
                .header(Header.builder().commandId(ID_VALUE).version(VERSION_VALUE).contentType(CONTENT_VALUE).build())
                .build();

        val response2 = JsonFormatter.readOpenC2Response(response.toJson());
        val response3 = JsonFormatter.readOpenC2Response(expected3);

        log.info("response json \n {}", response.toPrettyJson());
        log.info("response2 json \n {}", response2.toPrettyJson());
        log.info("response3 json \n {}", response3.toPrettyJson());

        val responseJN = new ObjectMapper().readTree(response.toJson());
        val response2JN = new ObjectMapper().readTree(response2.toJson());
        val response3JN = new ObjectMapper().readTree(response3.toJson());

        assertEquals(responseJN, response2JN);
        assertEquals(responseJN, response3JN);

        val inMsg = JsonFormatter.readOpenC2Response(expected3);
        assertTrue(inMsg.hasHeader());
        assertEquals(ID_VALUE, inMsg.getHeader().getCommandId());
        assertEquals(CONTENT_VALUE, inMsg.getHeader().getContentType());
    }
}
