package com.company;

public class City {
    int id;
    String title;
    int country_id;
    int mayor_id;

    public City(int id, String title, int country_id, int mayor_id) {
        this.id = id;
        this.title = title;
        this.country_id = country_id;
        this.mayor_id = mayor_id;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", country_id=" + country_id +
                ", mayor_id=" + mayor_id +
                '}';
    }
}
