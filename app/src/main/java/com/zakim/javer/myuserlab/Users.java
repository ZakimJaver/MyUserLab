package com.zakim.javer.myuserlab;

public class Users {

    private String uName;
    private String pass;
    private int hScore;
    private int id;

    public Users(String uName, int hScore){
        this.uName = uName;
        //default values
        this.hScore = hScore;
        this.pass = "";
    }
    public Users(String uName, String pass){
        this.uName = uName;
        //default values
        this.hScore = 0;
        this.pass = pass;
    }

    public Users(String uName, String pass, int hScore, int id){
        this.uName = uName;
        this.pass = pass;
        this.hScore = hScore;
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int gethScore() {
        return hScore;
    }

    public void sethScore(int hScore) {
        this.hScore = hScore;
    }

}
