package cn.yunhe.control;

import cn.yunhe.beans.DVD;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileMenu {
    List<DVD> dvdList = new ArrayList<>();
    String fileReadName = "DVDListRead";
    String fileWriteName = "DVDListWrite";
    Scanner input = new Scanner(System.in);
    int choose = 0;
    File file = new File(fileWriteName + ".txt");

    public FileMenu() throws IOException {
        this.dvdList = readfile();
    }


    public List<DVD> readfile() throws IOException {
        // 使用文件名称创建流对象

        if (!file.exists()) {
            file.createNewFile();
            startMenu();
        }
        try {
            // read file content from file
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            String str;
            while ((str = br.readLine()) != null) {
                String[] temp = str.split(",");
                DVD dvd6 = new DVD();
                dvd6.setState(Integer.parseInt(temp[0]));
                dvd6.setDvdName(temp[1]);
                dvd6.setBorrowTime(temp[2]);
                dvd6.setNumber(Integer.parseInt(temp[3]));
                dvdList.add(dvd6);
            }

            br.close();
            isr.close();
            fis.close();


        } catch (Exception exception) {
            System.out.println("出异常了");
        }
        return dvdList;
    }

    public void writeFile() {
        StringBuffer sbf = new StringBuffer();
        String[] temp = new String[dvdList.size()];
        for (int i = 0; i < dvdList.size(); i++) {
            temp[i] = dvdList.get(i).toString();
            sbf.append(temp[i] + ",\n");
        }
        try {
            FileOutputStream fos = new FileOutputStream(new File(fileWriteName + ".txt"));
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(sbf.toString());
            bw.close();
            osw.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startMenu() {

        System.out.print("欢 迎 使 用 迷 你 DVD 管 理 器\n" +
                "-------------------------------------\n" +
                "0. 借出排行榜\n" +
                "1. 新 增 DVD\n" +
                "2. 查 看 DVD\n" +
                "3. 删 除 DVD\n" +
                "4. 借 出 DVD\n" +
                "5. 归 还 DVD\n" +
                "6. 退         出 \n" +
                "--------------------------------------\n" +
                "请选择：\n");
        choose = input.nextInt();
        secondMenu();
    }

    public void secondMenu() {
        switch (choose) {
            case 0:
                topMenu();
                break;
            case 1:
                addMenu();
                break;
            case 2:
                seeMenu();
                break;
            case 3:
                delMenu();
                break;
            case 4:
                borMenu();
                break;
            case 5:
                retMenu();
                break;
            case 6:
                writeFile();
                break;
        }
    }

    public void topMenu() {
        isEnpty();
        //定义一个数组用来存放借出旳次数和索引
        int[] shorta = new int[dvdList.size()];
        for (int i = 0; i < shorta.length; i++) {
            shorta[i] = dvdList.get(i).getNumber();
        }
        //定义一个数组用来排序
        for (int i = 0; i < shorta.length; i++) {
            for (int j = 0; j < shorta.length - 1 - i; j++) {
                if (shorta[j] < shorta[j + 1]) {
                    int temp = shorta[j];
                    shorta[j] = shorta[j + 1];
                    shorta[j + 1] = temp;
                }
            }
        }
        //创建一个集合存放无重复旳次数
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < shorta.length; i++) {
            if (!list.contains(shorta[i])) {
                list.add(shorta[i]);
            }
        }


        System.out.println("排名\t\t 名称\t\t 次数\t\t");
        for (int i = 0; i < list.size(); i++) {
            for (DVD d : dvdList) {
                if (list.get(i) == d.getNumber()) {
                    System.out.println((i + 1) + "\t\t" + d.getDvdName() + "\t\t" + d.getNumber());
                }
            }
        }
        back();
    }

    public void addMenu() {
        DVD dvd = new DVD();
        System.out.println("请输入DVD名称：");
        dvd.setDvdName(input.next());//设置DVD名字
        dvd.setState(0);//设置DVD旳状态 0 未借出 1 借出
        dvd.setNumber(0);//设置DVD旳借出次数
        if (!dvdList.isEmpty()) {
            //如果数组不为空 遍历数组旳对象 取其name属性判断是否存在
            for (DVD d : dvdList) {
                if (d.getDvdName().equals(dvd.getDvdName())) {
                    System.out.println("添加失败，已经存在");
                    back();
                }
            }
        }
        System.out.println(dvd.getDvdName());
        System.out.println("添加成功");
        dvdList.add(dvd);
        back();
    }

    public void seeMenu() {
        isEnpty();
        System.out.println("序号\t\t状 态\t\t名称\t\t借出日期");
        //遍历查询
        for (int i = 0; i < dvdList.size(); i++) {
            DVD d = dvdList.get(i);
            System.out.print((i + 1) + "\t\t");
            if (d.getState() == 0) {
                System.out.print("可借\t\t");
            } else if (d.getState() == 1) {
                System.out.print("不可借\t\t");
            }
            System.out.print(d.getDvdName() + "\t\t");
            if (d.getBorrowTime() != null) {
                System.out.print(d.getBorrowTime() + "\n");
            } else {
                System.out.print("--" + "\n");
            }

        }
        back();

    }

    public void delMenu() {
        isEnpty();
        System.out.println("请输入要删除旳DVD名称：");
        Iterator<DVD> dvdIterator = dvdList.iterator();
        String name = input.next();//读取DVD名字
        int flag = 1;
        while (dvdIterator.hasNext()) {
            DVD d = dvdIterator.next();
            if (name.equals(d.getDvdName())) {
                if (d.getState() == 1) {
                    System.out.println("该DVD为借出状态，删除失败");
                    back();
                } else {
                    System.out.println("删除成功");
                    dvdIterator.remove();
                    back();
                }
                flag = 0;
            }
        }
        if (flag==1){
            System.out.println("未找到 删除失败");
            back();
        }
    }


    public void borMenu() {
        isEnpty();
        System.out.println("请输入要借旳DVD名称：");
        String name = input.next();//读取DVD名字
        for (DVD d : dvdList) {
            if (d.getDvdName().equals(name)) {
                if (d.getState() == 0) {
                    System.out.println("可以借出");
                    System.out.println("请输入借出时间 格式为'yyyy-MM-dd'");
                    String time = input.next();
                    d.setState(1);
                    d.setNumber(d.getNumber() + 1);
                    d.setBorrowTime(time);
                    back();
                } else if (d.getState() == 1) {
                    System.out.println("已借出，不可借");
                    back();
                }
            }
        }
        System.out.println("未找到");
        back();

    }

    public void retMenu() {
        isEnpty();
        System.out.println("请输入要还旳DVD名称：");
        String name = input.next();//读取DVD名字
        for (DVD d : dvdList) {
            if (d.getDvdName().equals(name) && d.getState() == 1) {
                System.out.println("请输入归还时间");
                String date = input.next();
                System.out.println("归还成功");
                d.setState(0);
                System.out.println("借出时间为：" + d.getBorrowTime());
                System.out.println("归还时间为：" + date);
                Double rent = rentClac(date, d.getBorrowTime());
                System.out.println("应付租金：" + rent);
                d.setBorrowTime("");
                back();
            }
        }
        System.out.println("未找到该DVD");
        back();
    }


    public Double rentClac(String time1, String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        double rent = 0;
        try {
            rent = ((sdf.parse(time1).getTime() - sdf.parse(time).getTime()) / 1.0 / 1000 / 60 / 60 / 24) * 10;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rent;
    }

    public void back() {
        System.out.println("输入0返回");
        choose = input.nextInt();
        if (0 == choose) {
            startMenu();
        }
    }

    public void isEnpty() {
        if (dvdList.isEmpty()) {
            System.out.println("DVD列表为空，请添加后重试");
            back();
        }
    }
}

