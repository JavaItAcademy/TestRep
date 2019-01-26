package com.company;

class Cities extends BaseClass {
    Countries country;
    Human human;

    public Cities(int id, String name, Countries country, Human human) {
        super(id, name);
        this.country = country;
        this.human = human;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", human=" + human +
                ", country=" + country +
                '}';
    }
}