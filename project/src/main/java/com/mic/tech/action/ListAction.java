package com.mic.tech.action;

import com.mic.tech.AbstractAction;

import java.util.List;

public class ListAction extends AbstractAction {
    private List <AbstractAction> list=null;
    public ListAction(List <AbstractAction> list){
        this.list=list;
    }
    public String getActionName(){
        return "LIST";
    }
    public String getDescription() {
        return "列出可执行的操作 everyone";
    }
    public void run() {
        for(AbstractAction lst:this.list){
            String name= lst.getActionName();
            String description=lst.getDescription();
            super.println(name+"-"+description);
        }
    }
}
