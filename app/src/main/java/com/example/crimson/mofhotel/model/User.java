package com.example.crimson.mofhotel.model;

public class User {
    private  String Name;
    private  String Password;
    private  String Surname;
    private  String Birth;

    public User(){

    }

    public User(String name,String password,String surname,String birth){
        Name = name;
        Password = password;
        Surname = surname;
        Birth = birth;
    }
    public String getName(){
        return Name;
    }
    public void setName(String name){
        Name = name;
    }
    public String getPassword(){
        return  Password;
    }
    public void setPassword(String password){
        Password = password;
    }
    public String getSurname(){
        return Surname;
    }
    public void setSurname(String surname){
        Surname = surname;
    }
    public String getBirth(){
        return Birth;
    }
    public void setBirth(String birth){
        Birth = birth;
    }
}
