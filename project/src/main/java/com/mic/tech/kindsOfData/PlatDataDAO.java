package com.mic.tech.kindsOfData;
import com.mic.tech.Film;
import com.mic.tech.Plat;
import com.mic.tech.PlatDAO;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlatDataDAO implements PlatDAO {
    public PlatDataDAO() {
        try {
            FileWriter fileWriter = new FileWriter("E:\\homework\\code\\j\\FilmHubManage\\platData.txt", false);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Plat> list = new ArrayList<>();

    public void addPlat(Plat plat) {
        list.add(plat);
        try {
            FileWriter fileWriter = new FileWriter("E:\\homework\\code\\j\\FilmHubManage\\platData.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.newLine();
            bufferedWriter.write("<platTime:" + plat.getTime() + ">" + "platScreeningHall:" + plat.getScreeningHall());
            bufferedWriter.newLine();
            bufferedWriter.write("<platTime:" + plat.getTime() + ">" + "platPrice:" + plat.getPrice());
            bufferedWriter.newLine();
            bufferedWriter.write("<platTime:" + plat.getTime() + ">" + "platTime:" + plat.getTime());
            bufferedWriter.newLine();
            bufferedWriter.write("<platTime:" + plat.getTime() + ">" + "platFilmTitle:" + plat.getFilm().getTitle());
            bufferedWriter.newLine();
            bufferedWriter.write("<platTime:" + plat.getTime() + ">" + "platFilmSynopsis:" + plat.getFilm().getSynopsis());
            bufferedWriter.newLine();
            bufferedWriter.write("<platTime:" + plat.getTime() + ">" + "platFilmDirector:" + plat.getFilm().getDirector());
            bufferedWriter.newLine();
            bufferedWriter.write("<platTime:" + plat.getTime() + ">" + "platFilmDuration:" + plat.getFilm().getDuration());
            bufferedWriter.newLine();
            bufferedWriter.write("<platTime:" + plat.getTime() + ">" + "platFilmStarring:" + plat.getFilm().getStarring());
            bufferedWriter.newLine();
            /*
            bufferedWriter.write("<platTime:"+plat.getTime()+">"+"platAvailableSeat:"+plat.getAvailableSeat());
            bufferedWriter.newLine();
            bufferedWriter.write("<platTime:"+plat.getTime()+">"+"platTotalNumberSeat:"+plat.getTotalNumberSeat());
            bufferedWriter.newLine();
            bufferedWriter.write("<platTime:"+plat.getTime()+">"+"platShow:"+" 1 2 3 4 5 6 7 8 9 10 11 12");
            */
            String show = null;
            for (int i = 0; i < 7; i++) {
                show = null;
                for (int j = 0; j < 12; j++) {
                    show = show + plat.getSeatBySeatId(i, j);
                }
                show = i + show;
                bufferedWriter.newLine();
                bufferedWriter.write("<platTime:" + plat.getTime() + ">" + "platShow:" + show);
            }

            bufferedWriter.flush();
            fileWriter.close();
            bufferedWriter.close();
            System.out.println("文件写入成功");
        } catch (IOException e) {
            System.out.println("文件不存在!");
        }
    }

    public void updatePlatByData(Plat plat, String screeningHall, int price, String time, Film film,String seat[][]) {
        changeData("<platTime:" + plat.getTime() + ">" + "platScreeningHall:" + plat.getScreeningHall(), "<platTime:" + plat.getTime() + ">" + "platScreeningHall:"  + screeningHall);
        changeData("<platTime:" + plat.getTime() + ">" + "platPrice:" + plat.getPrice(), "<platTime:" + plat.getTime() + ">" + "platPrice:" + price);
        changeData("<platTime:" + plat.getTime() + ">" + "platTime:" + plat.getTime(), "<platTime:" + plat.getTime() + ">" + "platTime:" + time);
        changeData("<platTime:" + plat.getTime() + ">" + "platFilmTitle:" + plat.getFilm().getTitle(), "<platTime:" + plat.getTime() + ">" + "platFilmTitle:" + film.getTitle());
        changeData("<platTime:" + plat.getTime() + ">" + "platFilmSynopsis:" + plat.getFilm().getSynopsis(), "<platTime:" + plat.getTime() + ">" + "platFilmSynopsis:" +film.getSynopsis());
        changeData("<platTime:" + plat.getTime() + ">" + "platFilmDirector:" + plat.getFilm().getDirector(),"<platTime:" + plat.getTime() + ">" + "platFilmDirector:" + film.getDirector());
        changeData("<platTime:" + plat.getTime() + ">" + "platFilmDuration:" + plat.getFilm().getDuration(),"<platTime:" + plat.getTime() + ">" + "platFilmDuration:" + film.getDuration());
        changeData("<platTime:" + plat.getTime() + ">" + "platFilmStarring:" + plat.getFilm().getStarring(),"<platTime:" + plat.getTime() + ">" + "platFilmStarring:" + film.getStarring());
        String show = null;
        String showNow=null;
        for (int i = 0; i < 7; i++) {
            show = null;
            showNow=null;
            for (int j = 0; j < 12; j++) {
                show = show + plat.getSeatBySeatId(i, j);
                show=showNow+seat[i][j];
            }
            show = i + show;
            changeData("<platTime:" + plat.getTime() + ">" + "platShow:" + show,"<platTime:" + plat.getTime() + ">" + "platShow:" + showNow);
        }
        for (int i = 0; i < list.size(); i++) {
            Plat information = list.get(i);
            if (information.getTime().equals(plat.getTime())) {
                list.get(i).setFilm(film);
                list.get(i).setPrice(price);
                list.get(i).setScreeningHall(screeningHall);
                list.get(i).setTime(time);
                list.get(i).setSeat(seat);
                break;
            }
        }
    }
        public void updatePlat (Plat plat){
            for (int i = 0; i < list.size(); i++) {
                Plat information = list.get(i);
                if (information.getTime().equals(plat.getTime())) {
                    list.get(i).setFilm(plat.getFilm());
                    list.get(i).setPrice(plat.getPrice());
                    list.get(i).setScreeningHall(plat.getScreeningHall());
                    list.get(i).setTime(plat.getTime());
                    list.get(i).setSeat(plat.getSeat());
                    break;
                }
            }
        }

        public void deletePlat (String time){
            Iterator<Plat> iterator = list.iterator();
            while (iterator.hasNext()) {
                Plat plat = iterator.next();
                if (plat.getTime().equals(time)) {
                    deleteData("<platTime:" + plat.getTime() + ">" + "platScreeningHall:" + plat.getScreeningHall());
                    deleteData("<platTime:" + plat.getTime() + ">" + "platPrice:" + plat.getPrice());
                    deleteData("<platTime:" + plat.getTime() + ">" + "platTime:" + plat.getTime());
                    deleteData("<platTime:" + plat.getTime() + ">" + "platFilmTitle:" + plat.getFilm().getTitle());
                    deleteData("<platTime:" + plat.getTime() + ">" + "platFilmSynopsis:" + plat.getFilm().getSynopsis());
                    deleteData("<platTime:" + plat.getTime() + ">" + "platFilmDirector:" + plat.getFilm().getDirector());
                    deleteData("<platTime:" + plat.getTime() + ">" + "platFilmDuration:" + plat.getFilm().getDuration());
                    deleteData("<platTime:" + plat.getTime() + ">" + "platFilmStarring:" + plat.getFilm().getStarring());
                /*
                deleteData("<platTime:"+plat.getTime()+">"+"platAvailableSeat:"+plat.getAvailableSeat());
                deleteData("<platTime:"+plat.getTime()+">"+"platTotalNumberSeat:"+plat.getTotalNumberSeat());
                deleteData("<platTime:"+plat.getTime()+">"+"platShow:"+" 1 2 3 4 5 6 7 8 9 10 11 12");
                */
                    String show = null;
                    for (int i = 0; i < 7; i++) {
                        show = null;
                        for (int j = 0; j < 12; j++) {
                            show = show + plat.getSeatBySeatId(i, j);
                        }
                        show = i + show;
                        deleteData("<platTime:" + plat.getTime() + ">" + "platShow:" + show);
                    }
                    iterator.remove();
                }
            }
        }

        public Plat getPlatBYTimeTitle (String time, String title){
            for (Plat plat : list) {
                if (plat.getTime().equals(time) && plat.getFilm().getTitle().equals(title))
                    return plat;
            }
            return null;
        }
        public Plat getPlatByTicketId (String ticketId){
            for (Plat plat : list) {
                for (int i = 0; i < 7; i++) {
                    for (int j = 0; j < 12; j++) {
                        if (plat.getTicketID(i, j).equals(ticketId))
                            return plat;
                    }
                }
            }
            return null;
        }
        public List<Plat> getAllPlats () {
            return list;
        }
        private void deleteData (String data){
            try {
                // 读取文件内容
                FileReader fileReader = new FileReader("E:\\homework\\code\\j\\FilmHubManage\\platData.txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                // 创建一个临时文件来保存修改后的内容
                FileWriter tempFileWriter = new FileWriter("E:\\homework\\code\\j\\FilmHubManage\\tempPlatData.txt");
                BufferedWriter tempBufferedWriter = new BufferedWriter(tempFileWriter);
                String line = bufferedReader.readLine();
                String textToRemove = data; // 要删除的文本
                while (line != null) {
                    // 如果行不包含要删除的文本，则写入临时文件
                    if (!line.equals(textToRemove)) {

                        tempBufferedWriter.write(line);
                        tempBufferedWriter.newLine();
                    }
                    line = bufferedReader.readLine();
                }
                // 关闭所有的流
                bufferedReader.close();
                tempBufferedWriter.close();
                // 删除原文件
                File originalFile = new File("E:\\homework\\code\\j\\FilmHubManage\\platData.txt");
                if (originalFile.delete()) {
                    // 重命名临时文件为原文件名
                    File tempFile = new File("E:\\homework\\code\\j\\FilmHubManage\\tempPlatData.txt");
                    tempFile.renameTo(originalFile);
                } else {
                    System.out.println("无法删除原文件");
                }
            } catch (IOException e) {
                System.out.println("文件读取错误: " + e.getMessage());
            }
        }
        private void changeData (String data, String changeToData){
            try {
                // 读取文件内容
                FileReader fileReader = new FileReader("E:\\homework\\code\\j\\FilmHubManage\\platData.txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                // 创建一个临时文件来保存修改后的内容
                FileWriter tempFileWriter = new FileWriter("E:\\homework\\code\\j\\FilmHubManage\\tempPlatData.txt");
                BufferedWriter tempBufferedWriter = new BufferedWriter(tempFileWriter);
                String line = bufferedReader.readLine();
                String textToRemove = data; // 要删除的文本
                while (line != null) {
                    // 如果行不包含要删除的文本，则写入临时文件
                    if (!line.equals(textToRemove)) {
                        tempBufferedWriter.write(line);
                        tempBufferedWriter.newLine();
                    } else {
                        tempBufferedWriter.write(changeToData);
                        tempBufferedWriter.newLine();
                    }
                    line = bufferedReader.readLine();
                }
                // 关闭所有的流
                bufferedReader.close();
                tempBufferedWriter.close();
                // 删除原文件
                File originalFile = new File("E:\\homework\\code\\j\\FilmHubManage\\platData.txt");
                if (originalFile.delete()) {
                    // 重命名临时文件为原文件名
                    File tempFile = new File("E:\\homework\\code\\j\\FilmHubManage\\tempPlatData.txt");
                    tempFile.renameTo(originalFile);
                } else {
                    System.out.println("无法删除原文件");
                }
                fileReader.close();
                tempFileWriter.close();
            } catch (IOException e) {
                System.out.println("文件读取错误: " + e.getMessage());
            }
        }
    }