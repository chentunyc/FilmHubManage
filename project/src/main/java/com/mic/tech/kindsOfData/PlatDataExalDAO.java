package com.mic.tech.kindsOfData;
import com.mic.tech.FilmDao;
import com.mic.tech.Plat;
import com.mic.tech.PlatDAO;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class PlatDataExalDAO implements PlatDAO {
    public PlatDataExalDAO(FilmDao filmDao){
        File file = new File(FILE_PATH);
        this.filmDao=filmDao;
        if (!file.exists()) {
            initializeExcelFile();
        }
    }
    private static final String FILE_PATH = "E:\\homework\\code\\j\\FilmHubManage\\platData.xlsx";
    private FilmDao filmDao=null;
    public void addPlat(Plat plat) {
        try {
            FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet("platData");
            boolean platExit = false;
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String time = row.getCell(2).getStringCellValue();
                String filmTitle = row.getCell(3).getStringCellValue();
                if (time.equals(plat.getTime()) && filmTitle.equals(plat.getFilm().getTitle())) {
                    platExit = true;
                    break;
                }
            }
            if (!platExit) {
                int lastRowNum = sheet.getLastRowNum();
                Row newRow = sheet.createRow(lastRowNum + 1);
                newRow.createCell(0).setCellValue(plat.getScreeningHall());
                newRow.createCell(1).setCellValue(String.valueOf(plat.getPrice()));
                newRow.createCell(2).setCellValue(plat.getTime());
                newRow.createCell(3).setCellValue(plat.getFilm().getTitle());
                newRow.createCell(4).setCellValue(String.valueOf(plat.getTotalNumberSeat()));
                newRow.createCell(5).setCellValue(String.valueOf(plat.getAvailableSeat()));
                newRow.createCell(6).setCellValue(convert2DArrayToString(plat.getSeat()));
                newRow.createCell(7).setCellValue(convert2DArrayToString(plat.getTicketID()));
                newRow.createCell(8).setCellValue(convert2DArrayToString(plat.getIsPutout()));
                FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH);
                workbook.write(fileOutputStream);
                fileInputStream.close();
                fileOutputStream.close();
            }
        } catch (IOException e) {
                e.printStackTrace();
        }
    }
    public void updatePlat(Plat plat,String time) {
        try {
            FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet("platData");
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String timeN = row.getCell(3).getStringCellValue();
                if (timeN.equals(time)) {
                    row.getCell(0).setCellValue(plat.getScreeningHall());
                    row.getCell(1).setCellValue(String.valueOf(plat.getPrice()));
                    row.getCell(2).setCellValue(plat.getTime());
                    row.getCell(3).setCellValue(plat.getFilm().getTitle());
                    row.getCell(4).setCellValue(String.valueOf(plat.getTotalNumberSeat()));
                    row.getCell(5).setCellValue(String.valueOf(plat.getAvailableSeat()));
                    row.getCell(6).setCellValue(convert2DArrayToString(plat.getSeat()));
                    row.getCell(7).setCellValue(convert2DArrayToString(plat.getTicketID()));
                    row.getCell(8).setCellValue(convert2DArrayToString(plat.getIsPutout()));
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

    public void deletePlat(String time) {
        try {
            FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet("platData");
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String timeNow = row.getCell(2).getStringCellValue();
                if (time.equals(timeNow)) {
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
    public Plat getPlatByTimeTitle(String time, String title) {
        try {
            FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet("platData");
            Iterator<Row> rowIterator = sheet.iterator();
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String titleN = row.getCell(3).getStringCellValue();
                String timeN = row.getCell(2).getStringCellValue();
                if (titleN.equals(title) && timeN.equals(time)) {
                    String screeningHall = row.getCell(0).getStringCellValue();
                    String priceStr = row.getCell(1).getStringCellValue();
                    int price = Integer.parseInt(priceStr);
                    String totalNumberStr = row.getCell(4).getStringCellValue();
                    int totalNumberSeat = Integer.parseInt(totalNumberStr);
                    String totalAvailableStr = row.getCell(5).getStringCellValue();
                    int availableSeat = Integer.parseInt(totalAvailableStr);
                    String seatStr=row.getCell(6).getStringCellValue();
                    String seat[][]=convertStringTo2DArray(seatStr);
                    String seatTicketIdStr=row.getCell(7).getStringCellValue();
                    String ticketId[][]=convertStringTo2DArray(seatTicketIdStr);
                    String isPutOutStr=row.getCell(8).getStringCellValue();
                    String isPutOut[][]=convertStringTo2DArray(isPutOutStr);
                    Plat plat=new Plat();
                    plat.setScreeningHall(screeningHall);
                    plat.setPrice(price);
                    plat.setTime(time);
                    plat.setFilm(filmDao.getFilmByFilmTitle(title));
                    plat.setTotalNumberSeat(totalNumberSeat);
                    plat.setAvailableSeat(availableSeat);
                    plat.setSeat(seat);
                    plat.setTicketID(ticketId);
                    plat.setIsPutout(isPutOut);
                    return plat;
                }
            }
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public Plat getPlatByTicketId(String ticketId) {
        try {
            FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet("platData");
            Iterator<Row> rowIterator = sheet.iterator();
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                    String seatTicketIdStr=row.getCell(7).getStringCellValue();
                    String ticketIdN[][]=convertStringTo2DArray(seatTicketIdStr);
                    for (int i=0;i<7;i++) {
                        for (int j=0;j<12;j++) {
                            if(ticketIdN[i][j].equals(ticketId)) {
                                String screeningHall = row.getCell(0).getStringCellValue();
                                String priceStr = row.getCell(1).getStringCellValue();
                                int price = Integer.parseInt(priceStr);
                                String time = row.getCell(2).getStringCellValue();
                                String title = row.getCell(3).getStringCellValue();

                                String totalNumberStr = row.getCell(4).getStringCellValue();
                                int totalNumberSeat = Integer.parseInt(totalNumberStr);
                                String totalAvailableStr = row.getCell(5).getStringCellValue();
                                int availableSeat = Integer.parseInt(totalAvailableStr);
                                String seatStr = row.getCell(6).getStringCellValue();
                                String seat[][] = convertStringTo2DArray(seatStr);

                                String isPutOutStr = row.getCell(8).getStringCellValue();
                                String isPutOut[][] = convertStringTo2DArray(isPutOutStr);
                                Plat plat = new Plat();
                                plat.setScreeningHall(screeningHall);
                                plat.setPrice(price);
                                plat.setTime(time);
                                plat.setFilm(filmDao.getFilmByFilmTitle(title));
                                plat.setTotalNumberSeat(totalNumberSeat);
                                plat.setAvailableSeat(availableSeat);
                                plat.setSeat(seat);
                                plat.setTicketID(ticketIdN);
                                plat.setIsPutout(isPutOut);
                                return plat;
                            }
                        }
                    }
                }
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Plat> getAllPlats() {
        List<Plat> list = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet("platData");
            Iterator<Row> rowIterator = sheet.iterator();
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String screeningHall = row.getCell(0).getStringCellValue();
                String priceStr = row.getCell(1).getStringCellValue();
                int price = Integer.parseInt(priceStr);
                String time = row.getCell(2).getStringCellValue();
                String title = row.getCell(3).getStringCellValue();

                String totalNumberStr = row.getCell(4).getStringCellValue();
                int totalNumberSeat = Integer.parseInt(totalNumberStr);
                String totalAvailableStr = row.getCell(5).getStringCellValue();
                int availableSeat = Integer.parseInt(totalAvailableStr);
                String seatStr = row.getCell(6).getStringCellValue();
                String seat[][] = convertStringTo2DArray(seatStr);
                String seatTicketIdStr=row.getCell(7).getStringCellValue();
                String ticketIdN[][]=convertStringTo2DArray(seatTicketIdStr);
                String isPutOutStr = row.getCell(8).getStringCellValue();
                String isPutOut[][] = convertStringTo2DArray(isPutOutStr);
                Plat plat = new Plat();
                plat.setScreeningHall(screeningHall);
                plat.setPrice(price);
                plat.setTime(time);
                plat.setFilm(filmDao.getFilmByFilmTitle(title));
                plat.setTotalNumberSeat(totalNumberSeat);
                plat.setAvailableSeat(availableSeat);
                plat.setSeat(seat);
                plat.setTicketID(ticketIdN);
                plat.setIsPutout(isPutOut);
                list.add(plat);
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
            Sheet sheet = workbook.createSheet("platData");
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("放映厅");
            headerRow.createCell(1).setCellValue("价格");
            headerRow.createCell(2).setCellValue("时段");
            headerRow.createCell(3).setCellValue("电影标题");
            headerRow.createCell(4).setCellValue("总座位");
            headerRow.createCell(5).setCellValue("可用座位");
            headerRow.createCell(6).setCellValue("座位情况");
            headerRow.createCell(7).setCellValue("座位票码");
            headerRow.createCell(8).setCellValue("取票情况");
            FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String convert2DArrayToString(String[][] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                sb.append(array[i][j]);
                if (j < array[i].length - 1) {
                    sb.append(",");
                }
            }
            if (i < array.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
    public static String[][] convertStringTo2DArray(String str) {
        String[] lines = str.split(" ");
        String[][] array = new String[lines.length][];
        for (int i = 0; i < lines.length; i++) {
            String[] values = lines[i].split(",");
            array[i] = values;
        }
        return array;
    }

}