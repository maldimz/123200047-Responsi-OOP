package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class MovieModel implements Calculation{    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/movie_db";
    static final String USER = "root";
    static final String PASS = "";
    
    Connection connection;
    Statement statement;
    
    public MovieModel() {
        try{
            Class.forName(JDBC_DRIVER);
            connection = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connection Success");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
            System.out.println("Connection Failed");
        }
    }

    @Override
    public double average(double plot, double character, double acting) {
        return (plot+character+acting)/3;
    }
    
    public int getMovieData(){
        try {
            int totalData = 0;
            String query = "SELECT * FROM `movie`";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                totalData++;
            }
            statement.close();
            return totalData;
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return 0;
        }
    }
    
    public void insertMovide(String title, String plot, String character, String acting){
        try {
            double plt, chr, act, score;
            plt = Double.parseDouble(plot);
            chr = Double.parseDouble(character);
            act = Double.parseDouble(acting);
            
            System.out.println(chr);
            if(plt < 0.0 || plt > 5.0){
                throw new Exception("out");
            }
            if(chr < 0.0 || chr > 5.0){
                throw new Exception("out");
            }
            if(act < 0.0 || act > 5.0){
                throw new Exception("out");
            }
            score = average(plt, chr, act);
            
            String query = "INSERT INTO `movie` (`title`, `plot`, `character`, `acting`, `score`) " + 
                        "VALUES ('" + title + "'," + plt + "," + chr + "," + act + "," + score + ")";
            
            statement = connection.createStatement();
            statement.executeUpdate(query);
            
            JOptionPane.showMessageDialog(null, "Register Success");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if(e.getMessage().equals("out")){
                JOptionPane.showMessageDialog(null, "Out of Bound");
            }else{
                JOptionPane.showMessageDialog(null, "Register Failed");
            }     
        }
    }
    
    public String[][] readMovie(){
        try{
            int totalData = 0;
            
            String data[][] = new String[getMovieData()][5];
            
            String query = "SELECT * FROM `movie`"; 
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                data[totalData][0] = resultSet.getString("title");
                data[totalData][1] = resultSet.getString("plot");                
                data[totalData][2] = resultSet.getString("character");
                data[totalData][3] = resultSet.getString("acting");
                data[totalData][4] = resultSet.getString("score");
                totalData++;
            }
            statement.close();
            return data;
               
        }catch(SQLException e){
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }
    
    public void updateMovie(String title, String plot, String character, String acting){
        try {
            double plt, chr, act, score;
            plt = Double.parseDouble(plot);
            chr = Double.parseDouble(character);
            act = Double.parseDouble(acting);
            
            System.out.println(chr);
            if(plt < 0.0 || plt > 5.0){
                throw new Exception("out");
            }
            if(chr < 0.0 || chr > 5.0){
                throw new Exception("out");
            }
            if(act < 0.0 || act > 5.0){
                throw new Exception("out");
            }
            score = average(plt, chr, act);
            
            String query = "UPDATE `movie` "
                    + "SET "
                    + "`plot`=" + plot + ","
                    + "`character`=" + chr + ","
                    + "`acting`=" + act + ","
                    + "`score`=" + score
                    + " WHERE title='" + title + "'";
            
            statement = connection.createStatement();
            statement.executeUpdate(query);
            
            JOptionPane.showMessageDialog(null, "Update Success");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if(e.getMessage().equals("out")){
                JOptionPane.showMessageDialog(null, "Out of Bound");
            }else{
                JOptionPane.showMessageDialog(null, "Update Failed");
            }     
        }
    }
    
    public void deleteMovie (String title) {
        try{
            String query = "DELETE FROM movie WHERE title = '"+title+"'";
            statement = connection.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Delete Success");
            
        }catch(SQLException e) {
            System.out.println("Error : " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Delete Failed");
        }
    }
}
