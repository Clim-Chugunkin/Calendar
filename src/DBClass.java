import java.sql.*;
import java.util.HashMap;
import java.util.Map;

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

    public HashMap<Key, HashMap<String,String>> getData(int month){
        String query = "select * from day where month in(" + (month - 1) + ","
                + month + "," + (month + 1) + ")";
        HashMap<Key, HashMap<String,String>> data = new HashMap<>();
        try {
            results = myStmt.executeQuery(query);
            //variables temporary
            HashMap<String, String> row;
            Key key ;
            int day = 0;
            int monthNumber = 0;
            String resultStr;

            while (results.next()) {
                row = new HashMap<>();
                for(int i = 1;i<results.getMetaData().getColumnCount()+1;i++){
                    resultStr = results.getString(i);
                    switch(results.getMetaData().getColumnName(i)){
                        case MyCalendar.MONTH -> monthNumber = Integer.parseInt(resultStr);

                        case MyCalendar.DAY -> day = Integer.parseInt(resultStr);

                        default -> row.put(results.getMetaData().getColumnName(i),
                                    resultStr);

                    }
                }
                key = new Key(monthNumber,day);
                data.put(key,row);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return data;
    }
    public void setData(Key key, HashMap<String, String> data) {
        //check if key exists
        String query = "SELECT count(*) size FROM day where day = "+ key.getDay()+ " AND month = " + key.getMonth();
        try{
            results = myStmt.executeQuery(query);
            results.next();
            if (Integer.parseInt(results.getString(1)) != 0 ){
                query = "UPDATE day SET ";
                for (Map.Entry<String,String> entry : data.entrySet()){
                    query += (entry.getKey() + " = " + "'" +entry.getValue() +  "',");
                }
                query =  query.substring(0,query.length()-1)
                         + " where day = " + key.getDay() + " AND "
                            + "month = " + key.getMonth();
            }else{
                String names = "(day,month";
                String values = "(" + key.getDay() + "," + key.getMonth();
                for (Map.Entry<String,String> entry : data.entrySet()){
                    names+=("," + entry.getKey());
                    values+=("," + entry.getValue());
                }
                names+=")";
                values+=")";
                query = "INSERT INTO day " + names + " VALUES" + values;
            }
            myStmt.executeUpdate(query);

        }catch(Exception ex){
            System.out.println(ex);;
        }
    }
}
