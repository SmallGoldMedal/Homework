package Program;

public class Test {
    public static void main(String[] args) {
        String test1= "src/main/resources/orig.txt";
        String test2= "src/main/resources/orig_0.8_add.txt";
        String test3= "src/main/resources/orig_0.8_del.txt";
        String test4= "src/main/resources/orig_0.8_dis_1.txt";
        String test5= "src/main/resources/orig_0.8_dis_10.txt";
        String test6= "src/main/resources/orig_0.8_dis_15.txt";
        String test7= "src/main/resources/test_1.txt";
        String ans1="src/main/resources/ans.txt";

        System.out.println("Test1：");
        test(test1,test2,ans1);
        System.out.println("Test2：");
        test(test1,test3,ans1);
        System.out.println("Test3：");
        test(test1,test4,ans1);
        System.out.println("Test4：");
        test(test1,test5,ans1);
        System.out.println("Test5：");
        test(test1,test6,ans1);
        System.out.println("Test6：");
        test(test1,test7,ans1);
    }

    public static void test(String path1, String path2, String path3) {
        //传入ReadFile以分解文本
        ReadFile File1 = new ReadFile(path1);
        ReadFile File2 = new ReadFile(path2);
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
        PutFile.putContent(path3, result);

    }
}
