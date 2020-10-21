package kr.co.kesti.utils;

import org.springframework.boot.json.GsonJsonParser;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class ResourceUtils {
    public static File readFile(final String resourcePath) throws IOException {
        return getResource(resourcePath).getFile();
    }

    public static BufferedImage readImage(final String resourcePath) throws IOException {
        return ImageIO.read(readFile(resourcePath));
    }

    public static String readText(final String resourcePath) throws IOException {
        StringBuilder s = new StringBuilder();
        InputStream inputStream = getResource(resourcePath).getInputStream();
        while (true) {
            byte[] buf = new byte[256];
            int len = inputStream.read(buf);
            s.append(new String(buf), 0, len);
            if (len != 256) break;
        }
        inputStream.close();
        return s.toString();
    }

    public static Map<String, Object> readJSON(final String resourcePath) throws IOException {
        return new GsonJsonParser().parseMap(readText(resourcePath));
    }

    private static Resource getResource(final String resourcePath) {
        return new ClassPathResource(resourcePath);
    }
}
