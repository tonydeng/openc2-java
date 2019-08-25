package com.github.tonydeng.openc2.utils;

import com.google.common.io.CharStreams;
import lombok.Cleanup;
import lombok.val;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Tony Deng
 * @version V1.0
 * @date 2019-08-25 08:13
 **/
public class FileUtils {
    /**
     * Resources File To InputStream
     *
     * @param filename
     * @return
     */
    public static InputStream getResourcesFileInputStream(String filename) {
        return Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(filename);
    }

    /**
     * 读取文本文件内容
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static String readResourcesFile(String filename) throws IOException {
        @Cleanup val is = getResourcesFileInputStream(filename);

        @Cleanup val reader = new InputStreamReader(is);

        return CharStreams.toString(reader);
    }

}
