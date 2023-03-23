package com.company.project.sql;

import java.sql.*;
import java.util.Scanner;

//import com.company.project.resilience4j.delays.IDelay;
//import com.company.project.resilience4j.delays.NoDelay;
import com.google.inject.Inject;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor_ = @__(@Inject))
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

@Path("/query")
public class jdbcMain extends jdbcConfig {

    public Connection setupConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = super.url;
        String usn = super.username;
        String pw = super.password;
        Connection connection = DriverManager.getConnection(url, usn, pw);

        return connection;
    }

    @GET
    public String get() throws SQLException, ClassNotFoundException {
        jdbcRun();
        return "Query executed.";
    }


    public void jdbcRun() throws SQLException, ClassNotFoundException {
        System.out.println("Enter Query Below:");
        Scanner scanner = new Scanner(System.in);
        Connection connection =setupConnection();;
        String query = scanner.nextLine();
        Statement statement =connection.createStatement();
        try{
            statement.executeUpdate(query);
            return;
        }catch (Exception e){}
        try{
            ResultSet rs=statement.executeQuery(query);
            while(rs.next()){
                int val= rs.getInt("num");
                System.out.println(val);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        connection.close();
        statement.close();
    }

}

