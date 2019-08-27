package com.github.tonydeng.openc2.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Tony Deng
 * @version V1.0
 * @date 2019-08-25 08:18
 **/
@Slf4j
public class FileUtilsTest {
    @Test
    void testReadTest1() throws IOException {
        String test1 = FileUtils.readResourcesFile("command/test1.json");
        assertNotNull(test1);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8})
    void testWithJson(int arg) throws IOException {
        String json = FileUtils.readResourcesFile("command/test" + arg + ".json");
        assertNotNull(json);
    }
}
