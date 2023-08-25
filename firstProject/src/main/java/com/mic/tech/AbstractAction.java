package com.mic.tech;

abstract class AbstractAction {

    abstract String getDescription();
    abstract String getActionName();
    abstract void run();
    public void println(Object s) {
        System.out.println(this.getActionName().toUpperCase() + "> " + s);
    }

    public void print(Object s) {
        System.out.print(this.getActionName().toUpperCase() + "> " + s);
    }
}
