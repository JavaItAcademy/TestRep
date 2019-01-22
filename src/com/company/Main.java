package com.company;

import com.company.Group3.Aigerim;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
        Aigerim aigerim = new Aigerim();
        aigerim.connect();
        aigerim.getTrainersCount();
        aigerim.getStudents();
    }
}