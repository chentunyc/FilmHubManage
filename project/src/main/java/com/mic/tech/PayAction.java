package com.mic.tech;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.UUID;

public class PayAction extends AbstractAuthenticatedAction{
    int seatI,seatJ;
    Double priceNumber;
    User user;
    Scanner scanner;
    Plat plat;
    Role role;
    UserService userService=null;
    public String getDescription() {
        return "付款 only customers";
    }
    public String getActionName() {
        return "PAY";
    }
    PayAction(int seatI,int seatJ,Plat plat,Double priceNumber,User user,Scanner scanner,UserService userService){
        this.seatI=seatI;
        this.seatJ=seatJ;
        this.plat=plat;
        this.priceNumber=priceNumber;
        this.user=user;
        this.scanner=scanner;
        this.userService=userService;
    }
    void perform() {
        this.role=user.getRole();
        if(role==Role.BRONZE_CUSTOMER||role==Role.GOLD_CUSTOMER||role==Role.SILVER_CUSTOMER){
            super.print("请输入ALIPAY或WECHAT或BANKCARD来选择查询方式:");
            String selection = scanner.nextLine();
            if (selection.equals("ALIPAY")) {
                pay();
            }
            if (selection.equals("WECHAT")) {
                pay();
            }
            if (selection.equals("BANKCARD")) {
                pay();
            }

        }
        else {
            super.println("你不是用户");
        }
    }
    void pay(){
        if (role == Role.BRONZE_CUSTOMER) {
            priceNumber=priceNumber*1;
        } else if (role == Role.SILVER_CUSTOMER) {
            priceNumber=priceNumber*0.95;
        } else if (role == Role.GOLD_CUSTOMER) {
            priceNumber=priceNumber*0.88;
        }
        UUID uuid=UUID.randomUUID();
        String string=uuid.toString();
        plat.setTicketIDBySeatId(seatI,seatJ,string);//号码
        plat.setIsPutoutBySeat(seatI,seatJ,"hasBuied");//说明购买
        plat.setSeatBySeatId(seatI, seatJ, "X");//占座
        plat.setAvailableSeat(plat.getAvailableSeat() - 1);//可用座位-1
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTimeString = currentDateTime.format(formatter);//当前时间
        user.addBuyRecord(plat.getTicketIDBySeat(seatI, seatJ));
        user.addBuyTimeRecord(dateTimeString);//购买记录
        user.setPurchaseAmount(user.getPurchaseAmount()+priceNumber);
        user.setPurchaseNumber(user.getPurchaseNumber()+1);
        userService.updateUser(user);
        super.println("购买完成");
    }
}
