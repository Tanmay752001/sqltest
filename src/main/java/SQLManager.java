import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLManager {
    public static void createTables(){
        LiteSQL.onUpdate("CREATE TABLE IF NOT EXISTS guilds(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, welcome STRING, channel STRING, reply_channel STRING)");
    }
    public static void insertValues(){
        LiteSQL.onUpdate("INSERT INTO guilds(welcome,channel,reply_channel) VALUES(2,3,'dad')");
    }
    public static String getValues(){
        ResultSet resultSet = LiteSQL.onQuery("SELECT * FROM guilds");
        try {
            return resultSet.getString(4);
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return null;
    }
}
