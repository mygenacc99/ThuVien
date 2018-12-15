package application.DAO;

import com.sun.rowset.CachedRowSetImpl;

import javax.sql.rowset.CachedRowSet;
import java.sql.*;

public class DBUtil {
    private static  String DB_URL = "jdbc:mysql://localhost:3306/" +"ThuVien" + "?useUnicode=yes&characterEncoding=UTF-8"  ;
    private static  String DRIVER = "com.mysql.jdbc.Driver";
    private static  String USER = "admin";
    private static  String PASS = "123456";
    private static Connection connection = null;
    public static void dbConnect() throws SQLException, ClassNotFoundException{
        try {
            Class.forName(DRIVER);
        }catch (ClassNotFoundException e){
            System.out.println("Where is your MySQL JDBC Driver?\n");
            e.printStackTrace();
            throw e;
        }

        System.out.println("JDBC Driver has been registerd!\n");

        try{
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            if(connection != null){
                System.out.println("Connected to Database!\n");
            }
        }
        catch (SQLException e){
            System.out.println("Connection failed! Check output console "+ e);
            throw  e;
        }

    }

    public static void dbDisconnect() throws SQLException{
        try{
            if(connection !=null && !connection.isClosed()){
                connection.close();
            }
        }
        catch (Exception e){
            throw e;
        }
    }

    public static ResultSet dbExcuteQuery(String sqlStmt) throws SQLException, ClassNotFoundException{
        Statement stmt = null;
        ResultSet rs = null;
        CachedRowSet crs = null;
        try{
            dbConnect();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sqlStmt);
            crs = new CachedRowSetImpl();
            crs.populate(rs);
        }
        catch (SQLException e){
            System.out.println("Problem occured at dbExcuteQuery operation "+e);
            throw e;
        }
        finally {
            if (stmt!=null){
                stmt.close();
            }
            dbDisconnect();
        }
        return crs;
    }

    public static int dbExcuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException{
        Statement stmt = null;
        int row = 0;
        try{
            dbConnect();
            stmt = connection.createStatement();
            row = stmt.executeUpdate(sqlStmt);
        }
        catch (SQLException e){
            System.out.println("Error occured at dbExcuteUpdate operation "+e);
            throw e;
        }
        finally {
            if (stmt!=null){
                stmt.close();
            }
            dbDisconnect();
        }
        return row;
    }

}
