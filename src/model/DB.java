package model;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB {

    private final String DATABASE_MANAGEMENT_SYSTEM; // Hệ quản trị csdl : mysql, sqlServer
    private final String DATABASE; // Database name
    private final String HOST; //host
    private final String PORT; //cổng
    private final String USER; // username
    private final String PASSWORD; //Mật khẩu
    private final String DB_URL;

    public Properties properties;//getConfigFile từ desktop:databaseConf.properties
    public Statement state = null; //Statement ~ một câu lệnh SQL
    public Connection conn = null; //Kết nối
    public CallableStatement call = null;
    public PreparedStatement prep = null;
    public ResultSet result = null; //ResultSet đại diện cho tập hợp các bản ghi lấy do thực hiện truy vấn.
    public int count = 0; //Số lượng các bản ghi lấy được
    public ArrayList<ArrayList<String>> kq = null; //Mảng trả về các bản ghi

    public DB(Properties properties) { //Hàm khởi tạo
        this.properties = properties;

        DATABASE_MANAGEMENT_SYSTEM = properties.getProperty("DATABASE_MANAGEMENT_SYSTEM");
        HOST = properties.getProperty("HOST");
        PORT = properties.getProperty("PORT");
        USER = properties.getProperty("USER");
        PASSWORD = properties.getProperty("PASSWORD");
        // driver register
        DATABASE = "parking_management_system";
        DB_URL = "jdbc:" + DATABASE_MANAGEMENT_SYSTEM + "://" + HOST + ":" + PORT + "/" + DATABASE + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true&useSSL=false"; //Địa chỉ DataBase
          this.getConnection();
    }
    public DB() { //Hàm khởi tạo
        this.properties = this.getConfigFile();

        DATABASE_MANAGEMENT_SYSTEM = properties.getProperty("DATABASE_MANAGEMENT_SYSTEM");
        HOST = properties.getProperty("HOST");
        PORT = properties.getProperty("PORT");
        USER = properties.getProperty("USER");
        PASSWORD = properties.getProperty("PASSWORD");
        // driver register
        DATABASE = "parking_management_system";
        DB_URL = "jdbc:" + DATABASE_MANAGEMENT_SYSTEM + "://" + HOST + ":" + PORT + "/" + DATABASE + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true&useSSL=false"; //Địa chỉ DataBase
          this.getConnection();
    }

  

    public boolean getConnection() { //Tạo 1 kết nối
        try {
            Class.forName("com." + DATABASE_MANAGEMENT_SYSTEM + ".jdbc.Driver"); ////Đăng kí drive
            //            DriverManager.registerDriver(new com.mysql.jdbc.Driver());//Đăng kí drive cách 2
            this.conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            //            this.state = this.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE,ResultSet.TYPE_FORWARD_ONLY);
            this.state = this.conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.TYPE_SCROLL_SENSITIVE); //Khởi tạo state
            return true;
        } catch (Exception e) {
            System.err.println("Không thể kết nối tới " + DATABASE + ": " + e);
        }
        return false;
    }

    public void freeConnection() { //Giải phóng kết nối, không có cũng đc, Java tự làm
        try {
            this.state.close();
            this.conn.close();
        } catch (SQLException e) {
            System.err.println("Không thể đóng kết nối của " + DATABASE + ": " + e);
        }
    }

    public boolean executeQuery(String query) { //Thực thi 1 câu truy vẫn có trả về dữ liệu
        //Nếu câu truy vấn không trả về dữ liệu thì nên sử dụng state.execute(sql);, bằng không sẽ báo lỗi
        try {System.out.println(query);
            this.result = this.state.executeQuery(query);
            return true;
        } catch (SQLException ex) {
            System.err.println("Đã có lỗi xảy ra khi thực thi câu lệnh:\n" + query + "\n ở hàm excuteQuery(String query)");
        }
        return false;

    }

    public boolean execute(String query) throws SQLException { //Thực thi 1 câu truy vẫn không trả về dữ liệu(update,delete,insert...)
        try {
            System.out.println(query);
            this.state.execute(query);
            return true;
        } catch (SQLException ex) {
            System.err.println("Đã có lỗi xảy ra khi thực thi câu lệnh:\n" + query + "\n ở hàm excute(String query)");
        }
        return false;

    }
    public boolean contains(String column, String table, String condition) {
        try {
            String sql = "SELECT " + column + " FROM " + table + " WHERE " + condition + " ;"; //Câu lệnh sql
            if (!this.executeQuery(sql)) {
                return false;//Thực thi câu lệnh truy vấn
            }
            if (this.result == null) {
                return false;
            }
            count = 0;
            count = getRowCount(this.result);
            return (count > 0);
        } catch (Exception e) {
        }
        return false;
    }
    
    
    
    
    
//////////////////////Các truy vấn select: selectNormal , selectDetail, selectQuick 

//Truy vấn select (phần chính), không hỗ trợ select *
    public boolean selectQuick(String column, String table, String condition) {
        try {
            //    column: cột cần select (không thể select * hay distinct)
//    table: bảng đang được select
//    condition: điều kiện select
String sql = "SELECT " + column + " FROM " + table + " WHERE " + condition + " ;"; //Câu lệnh sql

String[] col = column.split(",");//Lựa chọn các cột cần lấy
int totalColumn = col.length;
if (!this.executeQuery(sql)) {
    return false;//Thực thi câu lệnh truy vấn
}
if(this.result == null) return false;
kq = new ArrayList<ArrayList<String>>();
this.result.beforeFirst();
while (this.result.next()) {//Vòng lặp
    ArrayList<String> temKq = new ArrayList<String>();//Biến lưu kết quả tạm thời
    for (int j = 0; j < totalColumn; j++) {
        String thisKQ = this.result.getString(j + 1);
        temKq.add(thisKQ);
    }
    if(temKq != null && temKq.size() > 0) kq.add(temKq);
}
if(kq != null && kq.size() > 0) return true;
return false;
        } catch (SQLException ex) {
            return false;
        }
    }
    public boolean selectQuickNew(String column, int totalColumn, String table, String condition) {
        try {
            //    column: cột cần select (không thể select * hay distinct)
//    table: bảng đang được select
//    condition: điều kiện select
String sql = "SELECT " + column + " FROM " + table + " WHERE " + condition + " ;"; //Câu lệnh sql

if (!this.executeQuery(sql)) {
    return false;//Thực thi câu lệnh truy vấn
}
if(this.result == null) return false;
kq = new ArrayList<ArrayList<String>>();
this.result.beforeFirst();
while (this.result.next()) {//Vòng lặp
    ArrayList<String> temKq = new ArrayList<String>();//Biến lưu kết quả tạm thời
    for (int j = 0; j < totalColumn; j++) {
        String thisKQ = this.result.getString(j + 1);
        temKq.add(thisKQ);
    }
    if(temKq != null && temKq.size() > 0) kq.add(temKq);
}
if(kq != null && kq.size() > 0) return true;
return false;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean insertIngorNull(String table, String[] col, String[] colValue) throws SQLException { //Truy vấn insert
        int totalColumn = colValue.length;
        if(col.length != totalColumn){
                System.err.println("Số lượng các cột không trùng nhau");
                return false;
            }
            
            
        String column = "";
        for (int i = 0; i < totalColumn; i++) {
            if(colValue[i] != null){
            if(i < totalColumn - 1) column += col[i].trim() + ", ";
            else column += col[i].trim();
            }
        }
            String strColValue = "";
        for (int i = 0; i < totalColumn; i++) {
            String tempColValue = colValue[i];
            if(tempColValue != null){
            strColValue += "\"" + tempColValue.replace("\"", "\\\"") +  "\"" ;
            if (i < totalColumn - 1)  strColValue += ", ";
            }
        }
            
            column = column.trim();
            strColValue = strColValue.trim();
            if(column.substring(column.length()-1).equals(",")) column = column.substring(0,column.length()-1);
            if(strColValue.substring(strColValue.length()-1).equals(",")) strColValue = strColValue.substring(0,strColValue.length()-1);
            if(column.substring(0,1).equals(",")) column = column.substring(1);
            if(strColValue.substring(0,1).equals(",")) strColValue = strColValue.substring(1);
            String sql = "INSERT INTO " + table + "(" + column + ")" + " VALUES " + "(" + strColValue + ");";
            return this.execute(sql);//Thực thi câu lệnh insert
    }

  

    public boolean isExists(String table) {// Kiểm tra sự tồn tại của bảng table trong DATABASE
        String sql = "SHOW TABLES WHERE Tables_in_" + DATABASE + " = '" + table + "';";
        DB tempDB = new DB(this.properties);
        if(!tempDB.executeQuery(sql)) return false;
        int rowCount = getRowCount(tempDB.result);
        tempDB.freeConnection();
        return rowCount > 0;
    }

    public ArrayList<String> getColumnName(String table) throws SQLException {// Lấy danh sách tên các cột của bảng table
        ArrayList<String> tempKQ = new ArrayList<String>();
        DB tempDB = new DB(this.properties);
        String sql = "SELECT DISTINCT(COLUMN_NAME) FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = \"" + table + "\"";
        if(!tempDB.executeQuery(sql)) return null; //Thực thi câu lệnh truy vấn
        int tempCount = getRowCount(tempDB.result);
        if (tempCount > 0) {
            tempDB.result.beforeFirst();
            while (tempDB.result.next()) {//Vòng lặp
                tempKQ.add(tempDB.result.getString("COLUMN_NAME"));
            }
            if (tempKQ != null && tempKQ.size() > 0) {
                tempDB.freeConnection();
                return tempKQ;
            }
        }
        tempDB.freeConnection();
        System.err.println("Bảng " + table + " không có cột nào cả");
        return null;
    }

    public int getRowCount(ResultSet tableResult) {//Đếm số hàng (bản ghi) của kết quả trả về ResultSet
        if(tableResult == null) return 0;
        try {
            tableResult.last();
            int rowCount = tableResult.getRow();
            tableResult.beforeFirst();
            return rowCount;
        } catch (Exception ex) {
            System.err.println("không thể lấy số kq của ResultSet được " + ex);
        }
        return 0;
    }

    public void printQuick() {// in kết quả các bản ghi trong mảng kq
        int totalRecords = 0;
        if (kq != null) {
            totalRecords = kq.size();
            for (int i = 0; i < totalRecords; i++) {
                System.out.println(String.valueOf((100 * (i + 1)) / totalRecords) + "% (" + String.valueOf(i + 1) + "/" + totalRecords + ") - " + kq.get(i));
            }
        } else {
            System.out.println("Không có dữ liệu để in");
        }
    }
    public void print() {// in kết quả các bản ghi trong mảng kq
        int totalRecords = 0;
        if (kq != null) {
            totalRecords = kq.size();
            for (int i = 0; i < totalRecords; i++) {
                int percent = (100 * (i + 1)) / totalRecords;
                if (percent < 10) {
                    System.out.println(String.valueOf(percent) + "% (" + String.valueOf(i + 1) + "/" + totalRecords + ") - " + kq.get(i));
                } else {
                    System.out.println(String.valueOf(percent) + "%(" + String.valueOf(i + 1) + "/" + totalRecords + ") - " + kq.get(i));
                }
            }
        } else {
            System.out.println("Không có dữ liệu để in");
        }
    }
    public static Properties getConfigFile() {//Nếu không có đối số truyền vào thì sẽ get config file từ desktop: databaseConf.properties
        String config_file = javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory().toString() + "\\databaseConf.properties";
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(new File(config_file))); // lấy danh sách property từ file vào
            System.out.println("Load config file: " + config_file);
            return properties;
        } catch (IOException ex) {
            System.err.println("Không thể load file config: " + config_file);
        }
        return null;
    }
    public boolean updateIngorNull(String table, String[] col, String[] colValue, String condition)  { //Truy vấn update, các giá trị null thì không update
        int totalColumn = colValue.length;
        if(col.length != totalColumn){
                System.err.println("Số lượng các cột không trùng nhau");
                return false;
            }
            
        String set = "";  
        for (int i = 0; i < totalColumn; i++) {
            String tempColName = col[i];
            String tempColValue = colValue[i];
            
            if(tempColValue != null && tempColName != null){
            set +=  tempColName + " = \"" + tempColValue.replace("\"", "\\\"") +  "\"" ;
            if (i < totalColumn - 1)  set += ",";
            }
        }
        //Loại bỏ các dấu , thừa trong set
        set = set.trim();
        if(set.substring(set.length()-1).equals(",")) set = set.substring(0,set.length()-1);
        if(set.substring(0,1).equals(",")) set = set.substring(1);

            String sql = "UPDATE " + table + " SET " + set + " WHERE " + condition;
        try {
            return this.execute(sql);//Thực thi câu lệnh insert
        } catch (SQLException ex) {
            System.err.println(ex);
            return false;
        }
    }


}
