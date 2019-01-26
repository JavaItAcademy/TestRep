package com.company;

abstract class BaseClass {
    int id;
    String name;

    public BaseClass(int id, String name) {
        this.id = id;
        this.name = name;
    }
    @Override
    public String toString() {
        return "BaseClass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
