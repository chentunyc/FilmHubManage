package com.mic.tech.kindsOfData;
import com.mic.tech.AbstractAuthenticatedAction.Role;

import java.util.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import java.io.*;
public class UserDataExalDAO implements UserDAO {
    public UserDataExalDAO(){
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            initializeExcelFile();
        }
    }
    private static final String FILE_PATH = "E:\\homework\\code\\j\\FilmHubManage\\userData.xlsx";
    public void addUser(User user) {
        try {
            FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet("userData");
            boolean userExists = false;
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String username = row.getCell(0).getStringCellValue();;
                if (username.equals(user.getUsername())) {
                    userExists = true;
                    break;
                }
            }
            if (!userExists) {
                int lastRowNum = sheet.getLastRowNum();
                Row newRow = sheet.createRow(lastRowNum + 1);
                newRow.createCell(0).setCellValue(user.getUsername());
                newRow.createCell(1).setCellValue(user.getPassword());
                newRow.createCell(2).setCellValue(user.getRole().toString());
                newRow.createCell(3).setCellValue(String.valueOf(user.getId()));
                newRow.createCell(4).setCellValue(user.getEmail());
                newRow.createCell(5).setCellValue(user.getTelephoneNumber());
                newRow.createCell(6).setCellValue(String.valueOf(user.getRegistrationTime()));
                newRow.createCell(7).setCellValue(String.valueOf(user.getPurchaseAmount()));
                newRow.createCell(8).setCellValue(String.valueOf(user.getPurchaseNumber()));
                newRow.createCell(9).setCellValue(user.getLock());
                newRow.createCell(10).setCellValue(String.join(",",user.getBuyRecord()));
                newRow.createCell(11).setCellValue(String.join(",",user.getBuyTimeRecord()));
                FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH);
                workbook.write(fileOutputStream);
                fileInputStream.close();
                fileOutputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void updateUser(User user){
        try {
            FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet("userData");
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String username = row.getCell(0).getStringCellValue();
                if (username.equals(user.getUsername())) {
                    row.getCell(1).setCellValue(user.getPassword());
                    row.getCell(2).setCellValue(user.getRole().toString());
                    row.getCell(4).setCellValue(user.getEmail());
                    row.getCell(5).setCellValue(user.getTelephoneNumber());
                    row.getCell(7).setCellValue(String.valueOf(user.getPurchaseAmount()));
                    row.getCell(8).setCellValue(String.valueOf(user.getPurchaseNumber()));
                    row.getCell(9).setCellValue(user.getLock());
                    row.getCell(10).setCellValue(String.join(",",user.getBuyRecord()));
                    row.getCell(11).setCellValue(String.join(",",user.getBuyTimeRecord()));
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
    public void deleteUser(String userName) {
        try {
            FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet("userData");
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                int rowIndex = row.getRowNum();
                String username = row.getCell(0).getStringCellValue();
                if (username.equals(userName)) {
                    sheet.removeRow(row); //删除行对象
                    if(rowIndex+1<sheet.getLastRowNum())
                        sheet.shiftRows(rowIndex+1, sheet.getLastRowNum(), -1); //移动后续行，以填补删除行的空缺
                }
            }
            FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User getUserByUserName(String userName) {
        try {
            FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet("userData");
            Iterator<Row> rowIterator = sheet.iterator();
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String username = row.getCell(0).getStringCellValue();
                if (username.equals(userName)) {
                    String password = row.getCell(1).getStringCellValue();
                    String roleStr = row.getCell(2).getStringCellValue();
                    Role role = Role.valueOf(roleStr);
                    String idStr=row.getCell(3).getStringCellValue();
                    int id=Integer.parseInt(idStr);
                    String email=row.getCell(4).getStringCellValue();
                    String telephoneNumber=row.getCell(5).getStringCellValue();
                    String registrationTimeStr=row.getCell(6).getStringCellValue();
                    int registrationTime=Integer.parseInt(registrationTimeStr);
                    String purchaseAmountStr= row.getCell(7).getStringCellValue();
                    Double purchaseAmount=Double.parseDouble(purchaseAmountStr);
                    String purchaseNumberStr= row.getCell(8).getStringCellValue();
                    String lock=row.getCell(9).getStringCellValue();
                    int purchaseNumber=Integer.parseInt(purchaseNumberStr);
                    User user = new User();
                    user.setUsername(userName);
                    user.setPasswordIndex(password);
                    user.setRole(role);
                    user.setId(id);
                    user.setEmail(email);
                    user.setTelephoneNumber(telephoneNumber);
                    user.setregistrationTime(registrationTime);
                    user.setPurchaseAmount(purchaseAmount);
                    user.setPurchaseNumber(purchaseNumber);
                    user.setLock(lock);
                    String buyRecordStr= row.getCell(10).getStringCellValue();
                    if(buyRecordStr!=null) {
                        String[] buyRecordArray = buyRecordStr.split(",");
                        List<String> buyRecord = new ArrayList<>(Arrays.asList(buyRecordArray));
                        user.setBuyRecord(buyRecord);
                    }
                    String buyTimeRecordStr= row.getCell(11).getStringCellValue();
                    if(buyTimeRecordStr!=null) {
                        String[] buyTimeRecordArray = buyTimeRecordStr.split(",");
                        List<String> buyTimeRecord = new ArrayList<>(Arrays.asList(buyTimeRecordArray));
                        user.setBuyTimeRecord(buyTimeRecord);
                    }
                    return user;
                }
            }
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public User getUserByUserTelephoneNumber(String telephoneNumber){
        try {
            FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet("userData");
            Iterator<Row> rowIterator = sheet.iterator();
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String telephonenumber=row.getCell(5).getStringCellValue();
                if (telephonenumber.equals(telephoneNumber)) {
                    String userName=row.getCell(0).getStringCellValue();
                    String password = row.getCell(1).getStringCellValue();
                    String roleStr = row.getCell(2).getStringCellValue();
                    Role role = Enum.valueOf(Role.class,roleStr);
                    String idStr=row.getCell(3).getStringCellValue();
                    int id=Integer.parseInt(idStr);
                    String email=row.getCell(4).getStringCellValue();
                    String registrationTimeStr=row.getCell(6).getStringCellValue();
                    int registrationTime=Integer.parseInt(registrationTimeStr);
                    String purchaseAmountStr= row.getCell(7).getStringCellValue();
                    Double purchaseAmount=Double.parseDouble(purchaseAmountStr);
                    String purchaseNumberStr= row.getCell(8).getStringCellValue();
                    int purchaseNumber=Integer.parseInt(purchaseNumberStr);
                    User user = new User();
                    user.setUsername(userName);
                    user.setPasswordIndex(password);
                    user.setRole(role);
                    user.setId(id);
                    user.setEmail(email);
                    user.setTelephoneNumber(telephoneNumber);
                    user.setregistrationTime(registrationTime);
                    user.setPurchaseAmount(purchaseAmount);
                    user.setPurchaseNumber(purchaseNumber);
                    String lock=row.getCell(9).getStringCellValue();
                    user.setLock(lock);
                    String buyRecordStr= row.getCell(10).getStringCellValue();
                    if(buyRecordStr!=null) {
                        String[] buyRecordArray = buyRecordStr.split(",");
                        List<String> buyRecord = new ArrayList<>(Arrays.asList(buyRecordArray));
                        user.setBuyRecord(buyRecord);
                    }
                    String buyTimeRecordStr= row.getCell(11).getStringCellValue();
                    if(buyTimeRecordStr!=null) {
                        String[] buyTimeRecordArray = buyTimeRecordStr.split(",");
                        List<String> buyTimeRecord = new ArrayList<>(Arrays.asList(buyTimeRecordArray));
                        user.setBuyTimeRecord(buyTimeRecord);
                    }
                    return user;
                }
            }
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public User getUserByUserId(int id){
        try {
            FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet("userData");
            Iterator<Row> rowIterator = sheet.iterator();
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String idStr=row.getCell(3).getStringCellValue();
                int Id=Integer.parseInt(idStr);
                if (Id==id) {
                    String userName = row.getCell(0).getStringCellValue();
                    String password = row.getCell(1).getStringCellValue();
                    String roleStr = row.getCell(2).getStringCellValue();
                    Role role = Enum.valueOf(Role.class,roleStr);
                    String email=row.getCell(4).getStringCellValue();
                    String telephoneNumber=row.getCell(5).getStringCellValue();
                    String registrationTimeStr=row.getCell(6).getStringCellValue();
                    int registrationTime=Integer.parseInt(registrationTimeStr);
                    String purchaseAmountStr= row.getCell(7).getStringCellValue();
                    Double purchaseAmount=Double.parseDouble(purchaseAmountStr);
                    String purchaseNumberStr= row.getCell(8).getStringCellValue();
                    int purchaseNumber=Integer.parseInt(purchaseNumberStr);
                    User user = new User();
                    user.setUsername(userName);
                    user.setPasswordIndex(password);
                    user.setRole(role);
                    user.setId(id);
                    user.setEmail(email);
                    user.setTelephoneNumber(telephoneNumber);
                    user.setregistrationTime(registrationTime);
                    user.setPurchaseAmount(purchaseAmount);
                    user.setPurchaseNumber(purchaseNumber);
                    String lock=row.getCell(9).getStringCellValue();
                    user.setLock(lock);
                    String buyRecordStr= row.getCell(10).getStringCellValue();
                    if(buyRecordStr!=null) {
                        String[] buyRecordArray = buyRecordStr.split(",");
                        List<String> buyRecord = new ArrayList<>(Arrays.asList(buyRecordArray));
                        user.setBuyRecord(buyRecord);
                    }
                    String buyTimeRecordStr= row.getCell(11).getStringCellValue();
                    if(buyTimeRecordStr!=null) {
                        String[] buyTimeRecordArray = buyTimeRecordStr.split(",");
                        List<String> buyTimeRecord = new ArrayList<>(Arrays.asList(buyTimeRecordArray));
                        user.setBuyTimeRecord(buyTimeRecord);
                    }
                    return user;
                }
            }
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet("userData");
            Iterator<Row> rowIterator = sheet.iterator();
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String userName = row.getCell(0).getStringCellValue();
                String password = row.getCell(1).getStringCellValue();
                String roleStr = row.getCell(2).getStringCellValue();
                Role role = Enum.valueOf(Role.class,roleStr);
                String idStr=row.getCell(3).getStringCellValue();
                int id=Integer.parseInt(idStr);
                String email=row.getCell(4).getStringCellValue();
                String telephoneNumber=row.getCell(5).getStringCellValue();
                String registrationTimeStr=row.getCell(6).getStringCellValue();
                int registrationTime=Integer.parseInt(registrationTimeStr);
                String purchaseAmountStr= row.getCell(7).getStringCellValue();
                Double purchaseAmount=Double.parseDouble(purchaseAmountStr);
                String purchaseNumberStr= row.getCell(8).getStringCellValue();
                int purchaseNumber=Integer.parseInt(purchaseNumberStr);
                User user = new User();
                user.setUsername(userName);
                user.setPasswordIndex(password);
                user.setRole(role);
                user.setId(id);
                user.setEmail(email);
                user.setTelephoneNumber(telephoneNumber);
                user.setregistrationTime(registrationTime);
                user.setPurchaseAmount(purchaseAmount);
                user.setPurchaseNumber(purchaseNumber);
                String lock=row.getCell(9).getStringCellValue();
                user.setLock(lock);
                String buyRecordStr= row.getCell(10).getStringCellValue();
                if(buyRecordStr!=null) {
                    String[] buyRecordArray = buyRecordStr.split(",");
                    List<String> buyRecord = new ArrayList<>(Arrays.asList(buyRecordArray));
                    user.setBuyRecord(buyRecord);
                }
                String buyTimeRecordStr= row.getCell(11).getStringCellValue();
                if(buyTimeRecordStr!=null) {
                    String[] buyTimeRecordArray = buyTimeRecordStr.split(",");
                    List<String> buyTimeRecord = new ArrayList<>(Arrays.asList(buyTimeRecordArray));
                    user.setBuyTimeRecord(buyTimeRecord);
                }
                list.add(user);
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
            Sheet sheet = workbook.createSheet("userData");
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("名称");
            headerRow.createCell(1).setCellValue("密码");
            headerRow.createCell(2).setCellValue("角色");
            headerRow.createCell(3).setCellValue("id");
            headerRow.createCell(4).setCellValue("邮箱");
            headerRow.createCell(5).setCellValue("电话号码");
            headerRow.createCell(6).setCellValue("注册时间");
            headerRow.createCell(7).setCellValue("购买金额");
            headerRow.createCell(8).setCellValue("购买次数 ");
            headerRow.createCell(9).setCellValue("登录锁定");
            headerRow.createCell(10).setCellValue("购买记录 ");
            headerRow.createCell(11).setCellValue("购买时间");
            FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}