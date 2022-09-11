package com.example.foodapp.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;

public class DataBase {

    private String url;
    private String dbName;
    private String uname;
    private String password;

    private Connection connection =null;
    private static DataBase instance;


    private DataBase() {
        try {
            Properties prop = new Properties();
            String path = "src/main/resources/com/example/foodapp/config.properties";
            InputStream input = new FileInputStream(path);
            prop.load(input);
            this.url=prop.getProperty("dbUrl");
            this.dbName=prop.getProperty("dbName");
            this.uname=prop.getProperty("dbUName");
            this.password=prop.getProperty("dbPassword");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void connect(){
        try {
            connection = DriverManager.getConnection(url+dbName, uname, password);
            System.out.println("connection succeseful");
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public static DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;

    }

    public void insertIntoMeals(String name, String imageUrl, ArrayList<String> ingredients, String calories, String urlRecipe) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from meals where name like '"+name+"'");
        if (!resultSet.next()){
            String query = "INSERT INTO meals(name,imageUrl,ingredients,calories, urlRecipe)"+
                    "VALUES ('"+name+"'," +
                    "'"+imageUrl+"'," +
                    "'"+ingredients+"'," +
                    "'"+calories+ "'," +
                    "'"+urlRecipe+
                    "');";
            statement.executeUpdate(query);
        }
    }

    public void delete(String table, String name) throws SQLException {

        Statement statement = connection.createStatement();
        String query=("DELETE FROM "+ table +" where name like '"+name+"'");
        statement.executeUpdate(query);
    }

    public ResultSet getAll(String tableName) throws SQLException {
        String query = "select * from "+tableName;
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery(query);
        return resultSet;
    }

    public void init() throws SQLException, IOException {
        try {
            boolean exists=false;
            Connection con = DriverManager.getConnection(url,uname,password);
            Statement statement = con.createStatement();

            ResultSet db = con.getMetaData().getCatalogs();
            while(db.next()){
                if(Objects.equals(db.getString(1), dbName)){
                    exists=true;
                }
            }
            if(!exists){
                System.out.println("Creating database");
                statement.executeUpdate("CREATE DATABASE "+dbName);
                con.close();
                con =DriverManager.getConnection(url+dbName,uname,password);
                statement = con.createStatement();
            } else {
                con.close();
                con =DriverManager.getConnection(url+dbName,uname,password);
                statement = con.createStatement();
            }
            DatabaseMetaData dbm = con.getMetaData();
            ResultSet tables = dbm.getTables(dbName,null,"meals",new String[] {"TABLE"});
            if (!tables.next()){
                // no database
                System.out.println("Creating tables");
                String createMeals = "CREATE TABLE `meals`("+
                        "`id` INT NOT NULL AUTO_INCREMENT,"+
                        "`name` VARCHAR(100) NOT NULL,"+
                        "`imageUrl` VARCHAR(5000) NOT NULL,"+
                        "`ingredients` VARCHAR(2000) NOT NULL,"+
                        "`calories` VARCHAR(50) NOT NULL,"+
                        "`urlRecipe` VARCHAR(5000) NOT NULL,"+
                        "PRIMARY KEY (`id`)"+
                        ");";

//                String createPeople = "CREATE TABLE `people`("+
//                        "`id` INT NOT NULL AUTO_INCREMENT,"+
//                        "`name` VARCHAR(100) NOT NULL,"+
//                        "`height` VARCHAR(50) NOT NULL,"+
//                        "`hair_color` VARCHAR(50) NOT NULL,"+
//                        "`eye_color` VARCHAR(50) NOT NULL,"+
//                        "`birth_year` VARCHAR(50) NOT NULL,"+
//                        "`gender` VARCHAR(50) NOT NULL,"+
//                        "`homeworld` VARCHAR(50) NOT NULL,"+
//                        "`films` TEXT NOT NULL,"+
//                        "`starships` TEXT NOT NULL,"+
//                        "`vehicles` TEXT NOT NULL,"+
//                        "PRIMARY KEY (`id`)"+
//                        ");";
//
//                String createPlanets = "CREATE TABLE `planets`("+
//                        "`id` INT NOT NULL AUTO_INCREMENT,"+
//                        "`name` VARCHAR(100) NOT NULL,"+
//                        "`rotation_period` VARCHAR(100) NOT NULL,"+
//                        "`orbital_period` VARCHAR(100) NOT NULL,"+
//                        "`diameter` VARCHAR(100) NOT NULL,"+
//                        "`climate` VARCHAR(100) NOT NULL,"+
//                        "`gravity` VARCHAR(100) NOT NULL,"+
//                        "`terrain` VARCHAR(100) NOT NULL,"+
//                        "`population` VARCHAR(100) NOT NULL,"+
//                        "`films` TEXT NOT NULL,"+
//                        "`residents` TEXT NOT NULL,"+
//                        "PRIMARY KEY (`id`)"+
//                        ");";
//
//                String createSpecies = "CREATE TABLE `species`("+
//                        "`id` INT NOT NULL AUTO_INCREMENT,"+
//                        "`name` VARCHAR(100) NOT NULL,"+
//                        "`classification` VARCHAR(100) NOT NULL,"+
//                        "`designation` VARCHAR(100) NOT NULL,"+
//                        "`average_height` VARCHAR(100) NOT NULL,"+
//                        "`skin_colors` VARCHAR(100) NOT NULL,"+
//                        "`hair_colors` VARCHAR(100) NOT NULL,"+
//                        "`average_lifespan` VARCHAR(100) NOT NULL,"+
//                        "`language` VARCHAR(100) NOT NULL,"+
//                        "`homeworld` VARCHAR(100) NOT NULL,"+
//                        "`films` TEXT NOT NULL,"+
//                        "`people` TEXT NOT NULL,"+
//                        "PRIMARY KEY (`id`)"+
//                        ");";

                statement.executeUpdate(createMeals);
//                statement.executeUpdate(createPeople);
//                statement.executeUpdate(createPlanets);
//                statement.executeUpdate(createSpecies);
//                statement.executeUpdate(createStarships);
//                statement.executeUpdate(createVehicles);
                con.close();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Couldn't get connection with database! App closed!");
            System.exit(-1);
        }
    }
}

