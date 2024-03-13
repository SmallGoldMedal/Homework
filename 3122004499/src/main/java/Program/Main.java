package Program;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //输入路径
        System.out.println("请输入原文文件路径：");
        String Path1 = scanner.nextLine();
        System.out.println("请输入抄袭版论文的文件路径：");
        String Path2 = scanner.nextLine();
        System.out.println("请输入答案文件路径：");
        String Path3 = scanner.nextLine();
        scanner.close();

        //传入ReadFile以分解文本
        ReadFile File1 = new ReadFile(Path1);
        ReadFile File2 = new ReadFile(Path2);
        String[] Sentences1 = File1.getSentences();
        String[] Sentences2 = File2.getSentences();

        //空文本报错
        if (Sentences1.length == 0 || Sentences2.length == 0) {
            System.out.println("文档有误或路径有误。");
            return;
        }

        //进行统计
        int sCount = 0;
        for (String pSentence : Sentences2) {
            for (String oSentence : Sentences1) {
                int sameWords = 0;
                String[] pWords = pSentence.split("\\s+");
                String[] oWords = oSentence.split("\\s+");

                for (String pWord : pWords) {
                    for (String oWord : oWords) {
                        if (pWord.equals(oWord)) {
                            sameWords++;
                            break;
                        }
                    }
                }

                double similarity = (double) sameWords / pWords.length;
                if (similarity >= 0.5) {
                    sCount++;
                    break;
                }
            }
        }

        double pRate = (double) sCount / Sentences2.length * 100;
        String result = String.format("%.2f%%", pRate);

        //输出重复率
        PutFile.putContent(Path3, result);
    }
}
