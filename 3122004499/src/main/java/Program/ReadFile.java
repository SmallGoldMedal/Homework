package Program;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadFile {
    /*这里不知道为啥提示我用final*/
    private final String fileName;
    //传入路径参数
    public ReadFile(String fileName) {
        this.fileName = fileName;
    }
    public String[] getSentences() {
        try {
            String content = new String(Files.readAllBytes(Paths.get(fileName)));
            //以“。”或换行符为标志对文章进行切割
            return content.split("[。\\n]");
        } catch (IOException e) {
            //出错处理
            System.err.println("读取文件错误: " + fileName);
            e.printStackTrace();
            return new String[0];
        }
    }
}