package com.mic.tech;
import  com.mic.tech.AbstractAuthenticatedAction.Role;
import java.util.ArrayList;
import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class User {
    public User(String username,String password,Role role,int id,String telephoneNumber,int registrationTime,String email,int purchaseNumber,int purchaseAmount,String lock){
        this.username=username;
        this.password=password;
        this.role=role;
        this.id=id;
        this.telephoneNumber=telephoneNumber;
        this.registrationTime=registrationTime;
        this.email=email;
        this.purchaseAmount=purchaseAmount;
        this.purchaseNumber=purchaseNumber;
        this.lock=lock;
    }
    public User(){}
    private String username = null;
    private String password = null;
    private Role role = null;
    private int id=0;
    private String telephoneNumber=null;
    private int registrationTime=0;
    private String email=null;
    private int purchaseNumber=0;
    private double purchaseAmount=0;
    private String lock=null;
    private List <String> buyRecord=new ArrayList<>();
    private List <String> buyTimeRecord=new ArrayList<>();
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password=hashPassword(password);
    }
    public void setPasswordIndex(String password){
        this.password=password;
    }
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getTelephoneNumber(){
        return telephoneNumber;
    }
    public void setTelephoneNumber(String telephoneNumber){
        this.telephoneNumber=telephoneNumber;
    }
    public int getRegistrationTime(){
        return registrationTime;
    }
    public void setregistrationTime(int registrationTime){this.registrationTime=registrationTime;}
    public String getEmail(){
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setPurchaseNumber(int purchaseNumber){
        this.purchaseNumber=purchaseNumber;
    }

    public int getPurchaseNumber() {
        return purchaseNumber;
    }
    public void setPurchaseAmount(double purchaseAmount){
        this.purchaseAmount=purchaseAmount;
    }
    public double getPurchaseAmount(){
        return purchaseAmount;
    }
    public List getBuyRecord(){
        return buyRecord;
    }
    public void addBuyRecord(String record){
        buyRecord.add(record);
    }
    public List getBuyTimeRecord(){
        return buyTimeRecord;
    }
    public void addBuyTimeRecord(String record){
        buyTimeRecord.add(record);
    }
    public void setLock(String lock){
        this.lock=lock;
    }

    public String getLock() {
        return lock;
    }

    public static String hashPassword(String password) {
        try {
            // 创建SHA-256散列对象
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // 将密码转换为字节数组
            byte[] passwordBytes = password.getBytes();

            // 计算散列值
            byte[] hashBytes = digest.digest(passwordBytes);

            // 将散列值转换为十六进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
