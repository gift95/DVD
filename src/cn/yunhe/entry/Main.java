package cn.yunhe.entry;

import cn.yunhe.control.FileMenu;


import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
       /* List<DVD> dvdList = new ArrayList<>(50);
        DVD dvd1 = new DVD(0, "罗马假日", null, 10);
        DVD dvd2 = new DVD(0, "西游记", null, 10);
        DVD dvd3 = new DVD(0, "红楼梦", null, 17);
        DVD dvd4 = new DVD(0, "红楼梦4", null, 18);
        DVD dvd5 = new DVD(0, "红楼梦2", null, 20);
        dvdList.add(dvd1);
        dvdList.add(dvd2);
        dvdList.add(dvd3);
        dvdList.add(dvd4);
        dvdList.add(dvd5);*/

        FileMenu menu = new FileMenu();
        menu.startMenu();

   /*     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1=null;
        Date date=null;
        try {
             date1= sdf.parse("2020-6-9");
             date= sdf.parse("2020-6-10");
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            System.out.println(date+"\n"+date1+"\n"+(date.getTime()-date1.getTime()));
        }*/

    }
}
