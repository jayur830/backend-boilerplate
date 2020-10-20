package kr.co.kesti.utils;

import org.springframework.boot.json.GsonJsonParser;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class ResourceUtils {
    public static File readFile(final String resourcePath) {
        File file = null;
        try {
            file = getResource(resourcePath).getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static String readText(final String resourcePath) {
        StringBuilder s = new StringBuilder();
        try {
            InputStream inputStream = getResource(resourcePath).getInputStream();
            while (true) {
                byte[] buf = new byte[256];
                int len = inputStream.read(buf);
                s.append(new String(buf), 0, len);
                if (len != 256) break;
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s.toString();
    }

    public static Map<String, Object> readJSON(final String resourcePath) {
        return new GsonJsonParser().parseMap(readText(resourcePath));
    }

    private static Resource getResource(final String resourcePath) {
        return new ClassPathResource(resourcePath);
    }
}
