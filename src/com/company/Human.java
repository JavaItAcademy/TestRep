package com.company;

public class Human {
    int id;
    String fullname;

    public Human(int id, String fullname) {
        this.id = id;
        this.fullname = fullname;
    }

    @Override
    public String toString() {
        return "Human{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                '}';
    }
}
