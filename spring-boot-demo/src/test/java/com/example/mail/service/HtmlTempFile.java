package com.example.mail.service;

import com.example.mail.ColorOutput;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author wjx
 * @version 1.0.0
 */
class HtmlTempFile {
    private static final String HOME_DIR = System.getProperty("user.home");

    private static final Path BASE_DIR = Paths
            .get(HOME_DIR, "temp", "mail_html");

    /**
     * 在 user.home/temp/mail_html 下面存储 html 邮件，方便查看。
     *
     * @param fileName 文件名
     * @param content  html 内容
     * @throws IOException 略
     */
    static void save(String fileName, String content)
            throws IOException {

        Path filePath = BASE_DIR.resolve(fileName);
        ColorOutput.println(
                String.format("<!-- 存储[%s]到： %s -->", filePath.getFileName(), filePath));

        Files.createDirectories(filePath.getParent());

        //        Files.writeString(filePath, content);
        FileWriter writer = new FileWriter(filePath.toFile());
        writer.write(content);
        writer.close();
    }
}
