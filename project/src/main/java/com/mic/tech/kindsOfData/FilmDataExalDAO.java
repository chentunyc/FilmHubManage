package com.mic.tech.kindsOfData;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

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
                int rowIndex = row.getRowNum();
                String titleNow = row.getCell(0).getStringCellValue();
                if (title.equals(titleNow)) {
                    sheet.removeRow(row); //删除行对象
                    if(rowIndex+1<sheet.getLastRowNum())
                        sheet.shiftRows(rowIndex+1, sheet.getLastRowNum(), -1); //移动后续行，以填补删除行的空缺
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

    public List<Film> getFilmByFilmInformation(String title, String director, String starring) {
        try {
            List<Film>list=null;
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
                String directorN=row.getCell(1).getStringCellValue();
                String starringN=row.getCell(2).getStringCellValue();
                if (titleN.equals(title)||directorN.equals(director)||starringN.equals(starringN)) {
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
            }
            fileInputStream.close();
            return list;
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
