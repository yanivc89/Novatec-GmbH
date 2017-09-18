package com.novatec.info.microprofilerest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainClass {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
        try {

            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
        
        Connection connection = null;

        try {

            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres", "postgres", "admin");
            
            Statement stat = connection.createStatement();
            
            ResultSet rs = stat.executeQuery("SELECT COUNT(*) AS COUNT FROM EMPLOYEE");
            
            while(rs.next()) {
            	System.out.println(" THE DBUSER COUNT :: " + rs.getInt("COUNT"));
            }            

            
        } catch (SQLException e) {
            e.printStackTrace();
            return;

        }
	}

}
