package com.company;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args){
        DataBaseConnector db = new DataBaseConnector();
        System.out.println("id      Fullname         Salary");
        db.getTrainers();
        System.out.print("\nTotal Salary = " + db.getTrainersTotalSalary());
    }

}
