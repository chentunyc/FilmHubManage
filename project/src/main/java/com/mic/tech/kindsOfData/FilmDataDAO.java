package com.mic.tech.kindsOfData;
import com.mic.tech.Film;
import com.mic.tech.FilmDao;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FilmDataDAO implements FilmDao {
    public FilmDataDAO(){
        try{
            FileWriter fileWriter = new FileWriter("E:\\homework\\code\\j\\FilmHubManage\\filmData.txt",false);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private List <Film> list=new ArrayList<>();
    public void addFilm(Film film) {
        list.add(film);
        try {
            FileWriter fileWriter = new FileWriter("E:\\homework\\code\\j\\FilmHubManage\\filmData.txt",true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.newLine();
            bufferedWriter.write("<filmTitle:"+film.getTitle()+">"+"FilmTitle:"+film.getTitle());
            bufferedWriter.newLine();
            bufferedWriter.write("<filmTitle:"+film.getTitle()+">"+"FilmSynopsis:"+film.getSynopsis());
            bufferedWriter.newLine();
            bufferedWriter.write("<filmTitle:"+film.getTitle()+">"+"FilmDirector:"+film.getDirector());
            bufferedWriter.newLine();
            bufferedWriter.write("<filmTitle:"+film.getTitle()+">"+"FilmDuration:"+film.getDuration());
            bufferedWriter.newLine();
            bufferedWriter.write("<filmTitle:"+film.getTitle()+">"+"FilmStarring:"+film.getStarring());
            bufferedWriter.flush();
            fileWriter.close();
            bufferedWriter.close();
            System.out.println("文件写入成功");
        }catch (IOException e){
            if (e instanceof FileNotFoundException) {
                System.out.println("文件不存在: " + e.getMessage());
            } else {
                System.out.println("发生文件操作错误: " + e.getMessage());
            }
        }
    }

    public void updateFilm(Film film) {
        for(int i=0;i<list.size();i++){
            Film information=list.get(i);
            if (information.getTitle().equals(film.getTitle())||information.getSynopsis().equals(film.getSynopsis())){
                list.get(i).setTitle(film.getTitle());
                list.get(i).setDirector(film.getDirector());
                list.get(i).setStarring(film.getStarring());
                list.get(i).setSynopsis(film.getSynopsis());
                list.get(i).setDuration(film.getDuration());
                break;
            }
        }
    }

    public void deleteFilm(String title) {
        Iterator<Film> iterator = list.iterator();
        while (iterator.hasNext()) {
            Film film = iterator.next();
            if (film.getTitle().equals(title)) {
                deleteData("<filmTitle:"+film.getTitle()+">"+"FilmTitle:"+film.getTitle());
                deleteData("<filmTitle:"+film.getTitle()+">"+"FilmSynopsis:"+film.getSynopsis());
                deleteData("<filmTitle:"+film.getTitle()+">"+"FilmDirector:"+film.getDirector());
                deleteData("<filmTitle:"+film.getTitle()+">"+"FilmDuration:"+film.getDuration());
                deleteData("<filmTitle:"+film.getTitle()+">"+"FilmStarring:"+film.getStarring());
                iterator.remove();
            }
        }
    }

    public Film getFilmByFilmTitle(String title) {
        for (Film film:list){
            if(film.getTitle().equals(title))
                return film;
        }
        return null;
    }

    public Film getFilmByFilmDirector(String director) {
        for (Film film:list){
            if(film.getDirector().equals(director))
                return film;
        }
        return null;
    }

    public Film getFilmByFilmStarring(String starring) {
        for (Film film:list){
            if(film.getStarring().equals(starring))
                return film;
        }
        return null;
    }

    public List<Film> getAllFilms() {
        return list;
    }
    public void deleteData(String data){
        try {
            // 读取文件内容
            FileReader fileReader = new FileReader("E:\\homework\\code\\j\\FilmHubManage\\filmData.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            // 创建一个临时文件来保存修改后的内容
            FileWriter tempFileWriter = new FileWriter("E:\\homework\\code\\j\\FilmHubManage\\tempFilmData.txt");
            BufferedWriter tempBufferedWriter = new BufferedWriter(tempFileWriter);
            String line= bufferedReader.readLine();
            String textToRemove =data; // 要删除的文本
            while (line  != null) {
                // 如果行不包含要删除的文本，则写入临时文件
                if (!line.equals(textToRemove)) {

                    tempBufferedWriter.write(line);
                    tempBufferedWriter.newLine();
                }
                line= bufferedReader.readLine();
            }
            // 关闭所有的流
            bufferedReader.close();
            tempBufferedWriter.close();
            // 删除原文件
            File originalFile = new File("E:\\homework\\code\\j\\FilmHubManage\\filmData.txt");
            if (originalFile.delete()) {
                // 重命名临时文件为原文件名
                File tempFile = new File("E:\\homework\\code\\j\\FilmHubManage\\tempFilmData.txt");
                tempFile.renameTo(originalFile);
            } else {
                System.out.println("无法删除原文件");
            }
        } catch (IOException e) {
            System.out.println("文件读取错误: " + e.getMessage());
        }
    }
    public void changeData(String data,String changeToData) {
        try {
            // 读取文件内容
            FileReader fileReader = new FileReader("E:\\homework\\code\\j\\FilmHubManage\\filmData.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            // 创建一个临时文件来保存修改后的内容
            FileWriter tempFileWriter = new FileWriter("E:\\homework\\code\\j\\FilmHubManage\\tempFilmData.txt");
            BufferedWriter tempBufferedWriter = new BufferedWriter(tempFileWriter);
            String line= bufferedReader.readLine();
            String textToRemove =data; // 要删除的文本
            while (line  != null) {
                // 如果行不包含要删除的文本，则写入临时文件
                if (!line.equals(textToRemove)) {
                    tempBufferedWriter.write(line);
                    tempBufferedWriter.newLine();
                }
                else {
                    tempBufferedWriter.write(changeToData);
                    tempBufferedWriter.newLine();
                }
                line= bufferedReader.readLine();
            }
            // 关闭所有的流
            bufferedReader.close();
            tempBufferedWriter.close();
            // 删除原文件
            File originalFile = new File("E:\\homework\\code\\j\\FilmHubManage\\filmData.txt");
            if (originalFile.delete()) {
                // 重命名临时文件为原文件名
                File tempFile = new File("E:\\homework\\code\\j\\FilmHubManage\\tempFilmData.txt");
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
