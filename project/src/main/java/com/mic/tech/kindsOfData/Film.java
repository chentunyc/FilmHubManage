package com.mic.tech.kindsOfData;
public class Film{
    public Film(){}
    private String title=null;
    private String director=null;
    private String starring=null;
    private String synopsis=null;
    private String duration=null;
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public String getDirector(){
        return director;
    }
    public void setDirector(String director){
        this.director=director;
    }
    public String getStarring(){
        return starring;
    }
    public void setStarring(String starring){
        this.starring=starring;
    }
    public String getSynopsis(){
        return synopsis;
    }
    public void  setSynopsis(String synopsis){
        this.synopsis=synopsis;
    }
    public String getDuration(){
        return  duration;
    }
    public void setDuration(String duration){
        this.duration=duration;
    }
}
