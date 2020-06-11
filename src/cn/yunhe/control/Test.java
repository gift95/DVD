package cn.yunhe.control;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        // 使用文件名称创建流对象
        File file = new File("DVDListWrite.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        try {
            // read file content from file
            StringBuffer sbf = new StringBuffer();
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            String str = null;
            List<String[]> dvd = new ArrayList<>();
            while ((str = br.readLine()) != null) {
                sbf.append(str + "\n");
                // System.out.println(str);
                dvd.add(str.split(","));

            }
     /*       for (String[] r:dvd) {
                for (String e: r) {
                    System.out.println(e);
                }
            }*/
            br.close();
            reader.close();
            // write string to file
            FileWriter writer = new FileWriter("DVDListRead.txt");
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(sbf.toString());
            bw.close();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

