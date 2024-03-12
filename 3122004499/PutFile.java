package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PutFile {
    public static void putContent(String fileName, String content) {
        try {
            Files.write(Paths.get(fileName), content.getBytes());
        } catch (IOException e) {
            //出错处理
            System.err.println("写入文件出错: " + fileName);
            e.printStackTrace();
        }
    }
}