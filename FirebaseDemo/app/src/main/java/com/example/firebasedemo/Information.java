package com.example.firebasedemo;

public class Information {
    String Email, Name, Phone;

    public Information() {
    }

    public Information(String email, String name, String phone) {
        this.Email = email;
        this.Name = name;
        this.Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        this.Phone = phone;
    }
}
