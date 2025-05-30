import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBClass {
    Connection myConn = null;
    Statement myStmt = null;
    String error = "";
    ResultSet results;
    public DBClass(String name, String user, String password) {
        try {
            String dbName = "jdbc:mysql://localhost:3306/" + name;
            myConn = DriverManager.getConnection(dbName, user, password);
            myStmt = myConn.createStatement();
            error = "DB is OK";
        } catch (Exception ex) {
            error = ex.toString();
            System.out.println("Failed to open DataBase");
        }
    }

    public void closeDB() {
        try {
            if (myStmt != null) {
                myStmt.close();
            }

            if (myConn != null) {
                myConn.close();
            }

        } catch (Exception ex) {
            System.out.println("Failed to close DataBase");
        }
    }

    public String execSQL(){
        try{
            String tables = "";

            ResultSet results = myStmt.executeQuery("select * from brend");

            while(results.next()){
                tables+=results.getString(2)+" ";
            }
            return tables;
        }catch(Exception e){
            return "failed to read from DataBase";
        }
    }
}
