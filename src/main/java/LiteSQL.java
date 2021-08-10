import java.io.File;
import java.io.IOException;
import java.sql.*;

public class LiteSQL {
    private static Connection connection;
    private static Statement statement;
    public  static void openConnection(){
        connection = null;
        statement = null;
        try{
            File file = new File("database.db");
            if(!file.exists()){
                file.createNewFile();
            }
            String link = "jdbc:sqlite:"+file.getPath();
            connection = DriverManager.getConnection(link);
            statement = connection.createStatement();
            System.out.println("Connected to the database");
        }catch (SQLException | IOException exception){
            exception.printStackTrace();
        }
    }

    public static void disconnect(){
        if(connection!=null){
            try{
                connection.close();
            }catch (SQLException exception){
                exception.printStackTrace();
            }
        }
    }

    public static void onUpdate(String sql){
        if(statement !=null){
            try {
                statement.execute(sql);
            }catch (SQLException exception){
                exception.printStackTrace();
            }
        }
    }

    public static ResultSet onQuery(String sql){
        if(statement != null){
            try {
                return statement.executeQuery(sql);
            }catch (SQLException exception){
                exception.printStackTrace();
            }
        }
        return null;
    }


}
