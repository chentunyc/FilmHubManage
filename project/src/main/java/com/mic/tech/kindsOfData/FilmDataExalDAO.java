package com.mic.tech.kindsOfData;
import com.mic.tech.Film;
import com.mic.tech.FilmDao;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FilmDataExalDAO implements FilmDao {
    public FilmDataExalDAO(){
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                initializeExcelFile();
            }
        }
    private static final String FILE_PATH = "E:\\homework\\code\\j\\FilmHubManage\\filmData.xlsx";
    public void addFilm(Film film) {
        try {
            FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet("filmData");
            boolean filmExists = false;
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String title = row.getCell(0).getStringCellValue();;
                if (title.equals(film.getTitle())){
                    filmExists = true;
                    break;
                }
            }
            if (!filmExists) {
                int lastRowNum = sheet.getLastRowNum();
                Row newRow = sheet.createRow(lastRowNum + 1);
                newRow.createCell(0).setCellValue(film.getTitle());
                newRow.createCell(1).setCellValue(film.getDirector());
                newRow.createCell(2).setCellValue(film.getStarring());
                newRow.createCell(3).setCellValue(film.getSynopsis());
                newRow.createCell(4).setCellValue(film.getDuration());
                FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH);
                workbook.write(fileOutputStream);
                fileInputStream.close();
                fileOutputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void updateFilm(Film film,String title) {
        try {
            FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet("filmData");
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String titleNow = row.getCell(0).getStringCellValue();
                if (titleNow.equals(title)) {
                    row.getCell(0).setCellValue(film.getTitle());
                    row.getCell(1).setCellValue(film.getDirector());
                    row.getCell(2).setCellValue(film.getStarring());
                    row.getCell(3).setCellValue(film.getSynopsis());
                    row.getCell(4).setCellValue(film.getDuration());
                }
            }

            FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH);
            workbook.write(fileOutputStream);
            fileInputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteFilm(String title) {
        try {
            FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet("filmData");
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String titleNow = row.getCell(0).getStringCellValue();
                if (title.equals(titleNow)) {
                    rowIterator.remove();
                }
            }
            FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH);
            workbook.write(fileOutputStream);
            fileInputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Film getFilmByFilmTitle(String title) {
        try {
            FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet("filmData");
            Iterator<Row> rowIterator = sheet.iterator();
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String titleN=row.getCell(0).getStringCellValue();
                if (titleN.equals(title)) {
                    String director=row.getCell(1).getStringCellValue();
                    String starring=row.getCell(2).getStringCellValue();
                    String synopsis=row.getCell(3).getStringCellValue();
                    String duration=row.getCell(4).getStringCellValue();
                    Film film=new Film();
                    film.setTitle(title);
                    film.setDirector(director);
                    film.setStarring(starring);
                    film.setSynopsis(synopsis);
                    film.setDuration(duration);
                    return film;
                }
            }
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Film getFilmByFilmDirector(String director) {
        try {
            FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet("filmData");
            Iterator<Row> rowIterator = sheet.iterator();
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String directorN=row.getCell(1).getStringCellValue();
                if (directorN.equals(director)) {
                    String title=row.getCell(0).getStringCellValue();
                    String starring=row.getCell(2).getStringCellValue();
                    String synopsis=row.getCell(3).getStringCellValue();
                    String duration=row.getCell(4).getStringCellValue();
                    Film film=new Film();
                    film.setTitle(title);
                    film.setDirector(director);
                    film.setStarring(starring);
                    film.setSynopsis(synopsis);
                    film.setDuration(duration);
                    return film;
                }
            }
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Film getFilmByFilmStarring(String starring) {
        try {
            FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet("filmData");
            Iterator<Row> rowIterator = sheet.iterator();
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String starringN=row.getCell(2).getStringCellValue();
                if (starringN.equals(starring)) {
                    String title=row.getCell(0).getStringCellValue();
                    String director=row.getCell(1).getStringCellValue();
                    String synopsis=row.getCell(3).getStringCellValue();
                    String duration=row.getCell(4).getStringCellValue();
                    Film film=new Film();
                    film.setTitle(title);
                    film.setDirector(director);
                    film.setStarring(starring);
                    film.setSynopsis(synopsis);
                    film.setDuration(duration);
                    return film;
                }
            }
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Film> getAllFilms() {
        List<Film> list = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet("filmData");
            Iterator<Row> rowIterator = sheet.iterator();
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String title=row.getCell(0).getStringCellValue();
                String director=row.getCell(1).getStringCellValue();
                String starring=row.getCell(2).getStringCellValue();
                String synopsis=row.getCell(3).getStringCellValue();
                String duration=row.getCell(4).getStringCellValue();
                Film film=new Film();
                film.setTitle(title);
                film.setDirector(director);
                film.setStarring(starring);
                film.setSynopsis(synopsis);
                film.setDuration(duration);
                list.add(film);
            }
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    private void initializeExcelFile() {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("filmData");
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("片名");
            headerRow.createCell(1).setCellValue("导演");
            headerRow.createCell(2).setCellValue("主演");
            headerRow.createCell(3).setCellValue("剧情简介");
            headerRow.createCell(4).setCellValue("时长");
            FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
