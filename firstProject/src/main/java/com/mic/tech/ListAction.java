package com.mic.tech;

import java.util.List;

public class ListAction extends AbstractAction{
    private List <AbstractAction> list=null;
    ListAction(List <AbstractAction> list){
        this.list=list;
    }
    String getActionName(){
        return "LIST";
    }
    String getDescription() {
        return "列出可执行的操作";
    }
    void run() {
        for(AbstractAction lst:this.list){
            String name= lst.getActionName();
            String description=lst.getDescription();
            super.println(name+"-"+description);
        }
    }
}
