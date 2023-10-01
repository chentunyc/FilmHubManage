package com.mic.tech.kindsOfData;
import com.mic.tech.User;
import com.mic.tech.UserDAO;
import com.mic.tech.AbstractAuthenticatedAction.Role;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.io.*;
public class UserDataDAO implements UserDAO {
    public UserDataDAO(){
        try{
            FileWriter fileWriter = new FileWriter("E:\\homework\\code\\j\\FilmHubManage\\userData.txt",false);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private List <User> list=new ArrayList<>();
    public void addUser(User user) {
        list.add(user);
        try {
            FileWriter fileWriter = new FileWriter("E:\\homework\\code\\j\\FilmHubManage\\userData.txt",true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.newLine();
            bufferedWriter.write("<userId:"+user.getId()+">"+"userId:"+user.getId());
            bufferedWriter.newLine();
            bufferedWriter.write("<userId:"+user.getId()+">"+"userName:"+user.getUsername());
            bufferedWriter.newLine();
            bufferedWriter.write("<userId:"+user.getId()+">"+"userPassword:"+user.getPassword());
            bufferedWriter.newLine();
            bufferedWriter.write("<userId:"+user.getId()+">"+"userRole:"+user.getRole());
            bufferedWriter.newLine();
            bufferedWriter.write("<userId:"+user.getId()+">"+"userEmail:"+user.getEmail());
            bufferedWriter.newLine();
            bufferedWriter.write("<userId:"+user.getId()+">"+"userTelephoneNumber:"+user.getTelephoneNumber());
            bufferedWriter.newLine();
            bufferedWriter.write("<userId:"+user.getId()+">"+"userRegistrationTime:"+user.getRegistrationTime());
            bufferedWriter.newLine();
            bufferedWriter.write("<userId:"+user.getId()+">"+"userPurchaseAmount:"+user.getPurchaseAmount());
            bufferedWriter.newLine();
            bufferedWriter.write("<userId:"+user.getId()+">"+"userPurchaseNumber:"+user.getPurchaseNumber());
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

    public void updateUser(User user,String password,Role role,String email,String telephoneNumber,int purchaseNumber,int purchaseAmount) {
        changeData("<userId:"+user.getId()+">"+"userPassword:"+list.get(user.getId()).getPassword(),"<userId:"+user.getId()+">"+"userPassword:"+password);
        changeData("<userId:"+user.getId()+">"+"userRole:"+list.get(user.getId()).getRole(),"<userId:"+user.getId()+">"+"userRole:"+role);
        changeData("<userId:"+user.getId()+">"+"userEmail:"+list.get(user.getId()).getEmail(),"<userId:"+user.getId()+">"+"userEmail:"+email);
        changeData("<userId:"+user.getId()+">"+"userTelephoneNumber:"+list.get(user.getId()).getTelephoneNumber(),"<userId:"+user.getId()+">"+"userTelephoneNumber:"+telephoneNumber);
        changeData("<userId:"+user.getId()+">"+"userPurchaseNumber:"+list.get(user.getId()).getPurchaseNumber(),"<userId:"+user.getId()+">"+"userPurchaseNumber:"+purchaseNumber);
        changeData("<userId:"+user.getId()+">"+"userPurchaseAmount:"+list.get(user.getId()).getPurchaseAmount(),"<userId:"+user.getId()+">"+"userPurchaseAmount:"+purchaseAmount);
        list.get(user.getId()).setPassword(password);
        list.get(user.getId()).setRole(role);
        list.get(user.getId()).setEmail(email);
        list.get(user.getId()).setTelephoneNumber(telephoneNumber);
        list.get(user.getId()).setPurchaseNumber(purchaseNumber);
        list.get(user.getId()).setPurchaseAmount(purchaseNumber);
    }

    public void deleteUser(String userName) {
        Iterator<User> iterator = list.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getUsername().equals(userName)) {
                deleteData("<userId:"+user.getId()+">"+"userName:"+user.getUsername());
                deleteData("<userId:"+user.getId()+">"+"userPassword:"+user.getPassword());
                deleteData("<userId:"+user.getId()+">"+"userId:"+user.getId());
                deleteData("<userId:"+user.getId()+">"+"userEmail:"+user.getEmail());
                deleteData("<userId:"+user.getId()+">"+"userTelephoneNumber:"+user.getTelephoneNumber());
                deleteData("<userId:"+user.getId()+">"+"userRegistrationTime:"+user.getRegistrationTime());
                deleteData("<userId:"+user.getId()+">"+"userRole:"+user.getRole());
                deleteData("<userId:"+user.getId()+">"+"userPurchaseAmount:"+user.getPurchaseAmount());
                deleteData("<userId:"+user.getId()+">"+"userPurchaseNumber:"+user.getPurchaseNumber());
                iterator.remove();
            }
        }
    }

    public User getUserByUserName(String userName) {
        for(User user:list){
            if(user.getUsername().equals(userName)) {
                return user;
            }
        }
        return null;
    }
    public User getUserByUserTelephoneNumber(String telephoneNumber){
        for(User user:list){
            if(user.getTelephoneNumber().equals(telephoneNumber)) {
                return user;
            }
        }
        return null;
    }
    public User getUserByUserId(int id){
        return list.get(id);
    }

    public List<User> getAllUsers() {
        return list;
    }
    public void deleteData(String data){
        try {
            // 读取文件内容
            FileReader fileReader = new FileReader("E:\\homework\\code\\j\\FilmHubManage\\userData.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            // 创建一个临时文件来保存修改后的内容
            FileWriter tempFileWriter = new FileWriter("E:\\homework\\code\\j\\FilmHubManage\\tempUserData.txt");
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
            File originalFile = new File("E:\\homework\\code\\j\\FilmHubManage\\userData.txt");
            if (originalFile.delete()) {
                // 重命名临时文件为原文件名
                File tempFile = new File("E:\\homework\\code\\j\\FilmHubManage\\tempUserData.txt");
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
    public void changeData(String data,String changeToData) {
        try {
            // 读取文件内容
            FileReader fileReader = new FileReader("E:\\homework\\code\\j\\FilmHubManage\\userData.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            // 创建一个临时文件来保存修改后的内容
            FileWriter tempFileWriter = new FileWriter("E:\\homework\\code\\j\\FilmHubManage\\tempUserData.txt");
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
            File originalFile = new File("E:\\homework\\code\\j\\FilmHubManage\\userData.txt");
            if (originalFile.delete()) {
                // 重命名临时文件为原文件名
                File tempFile = new File("E:\\homework\\code\\j\\FilmHubManage\\tempUserData.txt");
                tempFile.renameTo(originalFile);
            } else {
                System.out.println("无法删除原文件");
            }
        } catch (IOException e) {
            System.out.println("文件读取错误: " + e.getMessage());
        }
    }
}