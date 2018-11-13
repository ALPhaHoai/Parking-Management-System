package model;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import static model.Lib.checkDouble;

/**
 *
 * @author Long
 */
public class Manager {
     public String id;
     public String name;
     public String type;
     public String parking_id;
     public String parking_name;
     public String parking_address;
     DB db = new DB();
    
    public Manager(){}
    public Manager(String id) {
        this.id = id;
        if(db.selectQuick("account.name, account.type,account.parking_id", "account", "account.id = " + id)){
        this.name = db.kq.get(0).get(0);
        this.type = db.kq.get(0).get(1);
        this.parking_id = db.kq.get(0).get(2);
        }
            System.out.println(this.type);
            if(!this.type.equalsIgnoreCase("admin") && !this.type.equalsIgnoreCase("manager")) {
                System.err.println("Tài khoản này không phải admin và cũng không phải quản lí");
            }
        if(this.type.equalsIgnoreCase("manager") && db.selectQuick("parking_info.id,parking_info.name,parking_info.address", 
                "parking_info", 
                "parking_info.id = " + this.parking_id)) {
            this.parking_id = db.kq.get(0).get(0);
            this.parking_name = db.kq.get(0).get(1);
            this.parking_address = db.kq.get(0).get(2);
            
        } else {
            System.err.println("Đã có lỗi xảy ra. Không thể tìm thấy thông tin của người quản lí này");
        }
    }
    
    public String getCondition(String id, String colName){
    if(this.type != null && this.type.equalsIgnoreCase("manager")) return colName + "  =  "+id;
    else {
        if(checkDouble(id)) return colName + "  =  "+id;
        else return " 1 ";
    }
    }
    
    //Trả về danh dách các loại xe mà bãi xe có thể chứa
    public ArrayList<String> getTypeVehicle(){
        return Guess.getTypeVehicle(this.parking_id);
    }
    //Trả về danh sách các xe vào  từ bảng vehicle
    public ArrayList<ArrayList<String>> getVehicleIn() {
        if(db.selectQuick("vehicle_in_out.parking_id,vehicle_in_out.id, typeofvehicle.name,vehicle_in_out.bks, "
                + "vehicle_in_out.time_in, vehicle_in_out.status, vehicle_in_out.thanh_tien, vehicle_in_out.time_out", 
                "vehicle_in_out, typeofvehicle", 
                 getCondition(this.parking_id, "vehicle_in_out.parking_id") + " AND vehicle_in_out.typeofvehicle = typeofvehicle.id")){
        if(db.kq == null || db.kq.size() < 1) return null;
        else {for (int i = 0; i < db.kq.size(); i++) {
            db.kq.get(i).add(0,String.valueOf(i + 1));
        }
        return db.kq;
        }
    } else return null;
    }
    //Trả về id của loại xe có tên là name
   public static String getTypeVehicleofId(String typeVehicleName){
       try {
           DB db = new DB();
           if(db.selectQuick("id", "typeofvehicle", "name = '" + typeVehicleName + "'")){
           return db.kq.get(0).get(0);
           }
       } catch (Exception e) {
       }
         return null;
   }
   //Trả về tên của loại xe có id là id
   public static String getTypeVehicleofName(String typeVehicleId){
       try {
           DB db = new DB();
           if(db.selectQuick("name", "typeofvehicle", "id = '" + typeVehicleId + "'")){
           return db.kq.get(0).get(0);
           }
       } catch (Exception e) {
       }
         return null;
   }

    public boolean choXeVao(String typeVehicle, String bks) {
        try {
            return db.insertIngorNull("vehicle_in_out", 
                    new String[]{"typeofvehicle","bks","parking_id"}, 
                    new String[]{getTypeVehicleofId(typeVehicle), bks, this.parking_id});
        } catch (SQLException ex) {
            System.err.println("Không thể thêm loại xe " + typeVehicle + " có bks là " + bks + " vào bãi đỗ đc");
            return false;
        }
    }
    public boolean choXeRa(String vehicle_id) {
        if(!checkDouble(this.parking_id)) return false;
        try {
            if(db.execute("UPDATE vehicle_in_out SET status = \"Đã ra\" ,  time_out = CURRENT_TIMESTAMP WHERE id = \"" + vehicle_id + "\" AND parking_id = \"" + this.parking_id + "\""))
            return true;
        } catch (SQLException ex) {
            System.err.println("Không thể cho xe " + vehicle_id + " ra được");
        }
        return false;
    }
    public String tinhTien(String vehicle_id){
        try {
            System.out.println("Đang tính tiền cho bãi xe " + this.parking_name);
            db.selectQuick("time_in, typeofvehicle", "vehicle_in_out", "id = \"" + vehicle_id + "\"");
            String timeIn = db.kq.get(0).get(0);
            String typeVehicle = db.kq.get(0).get(1);
            if(db.selectQuick("fares.fares_values", "fares, typeofvehicle", "fares.last_update <= '" + timeIn + "'AND fares.parking_id = " +this.parking_id+ " AND fares.typeofvehicle = '" + typeVehicle + "' order by fares.last_update desc limit 1")){
            String faresValues = db.kq.get(0).get(0);
            
            if (checkDouble(faresValues)) {
                 //Kiểm tra xem có gửi qua đêm không
            if(db.selectQuick("over_night", "parking_info", "over_night is not null AND parking_id = " + this.parking_id)){
                if(checkDouble(db.kq.get(0).get(0))){
                int overNightFare = Integer.valueOf(db.kq.get(0).get(0));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dayOut = new Date();
            Date dayIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeIn);
            //nếu hai ngày khác nhau thì có nghĩa là xe đã gửi qua đêm
            if( sdf.format(dayIn) != sdf.format(dayOut)){
            long diffInMillies = dayOut.getTime() - dayIn.getTime();
            long dayDriff = TimeUnit.DAYS.convert(diffInMillies,TimeUnit.MILLISECONDS);
            faresValues = String.valueOf(Integer.valueOf(faresValues) + dayDriff*overNightFare);
            }
            }}
                db.execute("UPDATE vehicle_in_out SET thanh_tien = \"" + faresValues + "\" WHERE id = \"" + vehicle_id + "\"" );
                return faresValues;
            }
            } else System.err.println("Không thể tìm thấy giá tiền cho loại xe " + typeVehicle + " được");
        } catch (Exception e) {
            System.err.println("Không thể tính tiền cho xe " + vehicle_id + " được");
        }
        return null;
    }
    public boolean deleteVehicle(String vehicle_id) {
        try {
            if(db.execute("DELETE FROM vehicle_in_out WHERE id = \"" + vehicle_id + "\""))
            return true;
        } catch (SQLException ex) {
            System.err.println("Không thể xóa xe " + vehicle_id + " được");
        }
        return false;
    }
    
     //Trả về lịch sử giá vé từ bảng fares
    public ArrayList<ArrayList<String>> getFareHistory() {
        
         if(db.selectQuick("fares.parking_id,fares.id, fares.fares_values,typeofvehicle.name, fares.last_update", "fares, typeofvehicle", getCondition(this.parking_id, "fares.parking_id") + " AND fares.typeofvehicle = typeofvehicle.id ORDER BY last_update DESC")){
        if(db.kq == null || db.kq.size() < 1) return null;
        else {
            for (int i = 0; i < db.kq.size(); i++) {
            db.kq.get(i).add(0,String.valueOf(i + 1));
            }
        return db.kq;
        }
    } else return null;
    }
     //Trả về thông tin bãi đỗ từ bảng paking info
    public ArrayList<ArrayList<String>> getParkingInfo() {
        String select = "parking_info.id, parking_info.name, parking_info.address,parking_info.capacity, "
            + "GROUP_CONCAT(typeofvehicle.name ) as typeofvehicle,parking_info.over_night ";
        String table = "parking_info, typeofvehicle , parking_vehicle";
        String condition = "parking_info.id = parking_vehicle.parking_id  \n" +
"AND parking_vehicle.typeofvehicle = typeofvehicle.id  \n" +
"AND parking_info.id = " + this.parking_id;
          if(db.selectQuick(select, table, condition )){
        if(db.kq == null || db.kq.size() < 1 ) return null;
        else {
            ArrayList<ArrayList<String>> getParkingInfo =  db.kq;
            for (int i = 0; i < getParkingInfo.size(); i++) {
                getParkingInfo.get(i).add(0,String.valueOf(i+1));
                getParkingInfo.get(i).add(4,availableSlot(getParkingInfo.get(i).get(0)));
            }
            return getParkingInfo;
            
        }}
        return null;
    }
    public boolean updateFares(String typeVehicle,String giaVe ){
        try {
            if(db.insertIngorNull("fares", 
                    new String[]{
                "parking_id","fares_values","typeofvehicle"}, 
                    new String[]{this.parking_id, giaVe, getTypeVehicleofId(typeVehicle)})) return true;
        } catch (SQLException ex) {
            System.err.println("Không thể cập nhật giá vé được");
        }
        return false;
    }
    public boolean deleteFares(String fares_id) {
        try {
            if(db.execute("DELETE FROM fares WHERE fares_id = \"" + fares_id + "\" AND parking_id = \"" + this.parking_id  + "\""))
            return true;
        } catch (SQLException ex) {
            System.err.println("Không thể xóa vé xe " + fares_id + " được");
        }
        return false;
    }

    //Cập nhật lại thông tin bãi đỗ
     public boolean updateParkingInfo(String parking_id, String parking_name, String parking_address, String parking_capacity, ArrayList<String> type_vehicle, String over_night){
         if(!checkDouble(over_night)) over_night = null;
         if(type_vehicle != null && type_vehicle.size() > 0){
             setLoaiXe(type_vehicle, this.parking_id);
         }
         return db.updateIngorNull("parking_info", 
                 new String[]{"name","address","capacity","over_night"}, 
                 new String[]{parking_name,  parking_address,  parking_capacity,  over_night}, 
                 " id = " + parking_id) ;
         }
     //Set danh sách các loại xe của bãi đỗ parking_id
     public static boolean setLoaiXe(ArrayList<String> arrLoaiXe, String parking_id){
         if(arrLoaiXe == null || arrLoaiXe.size() < 1) return true;
         DB db = new DB();
            try {
                db.execute("DELETE FROM parking_vehicle WHERE parking_id = " + parking_id);   
                for (int i = 0; i < arrLoaiXe.size(); i++) {
                    String loaiXeID = arrLoaiXe.get(i);
                    if(!Lib.checkNumeric(loaiXeID)) loaiXeID = getTypeVehicleofId(loaiXeID);
                     db.insertIngorNull("parking_vehicle", 
                     new String[]{"parking_id","typeofvehicle"}, 
                     new String[]{parking_id,loaiXeID});
                 }
                return true;
            } catch (Exception e) {
                return false;
            }
     }
     //Trả về số chỗ còn trống của bãi đỗ
     public static String availableSlot(String parking_id){
         try {
             DB db = new DB();
             if(db.selectQuick("capacity", "parking_info", "id =" + parking_id)){
     String parking_capacity = db.kq.get(0).get(0);
     if (db.selectQuick("count(*)", "vehicle_in_out", "parking_id =" + parking_id + " AND status = \"Đang trong bãi\"")){
     String parking_capacity_in = db.kq.get(0).get(0);
     int availableSlot = Integer.valueOf(parking_capacity) - Integer.valueOf(parking_capacity_in) ;
     if(availableSlot >= 0) return String.valueOf(availableSlot);
     }
     } 
         } catch (Exception e) {
             System.err.println(e);
         }
             return null;
     }
     

    public ArrayList<ArrayList<String>> getThongKe(String timeIn, String timeOut, String loaiXe, String trangThai, String ThoiGianVaoRa, String loaiThongKe) {
        try {
            String time = "time_in";
            if(ThoiGianVaoRa.equalsIgnoreCase("Thời Gian Ra")) time = "time_out";
            String typeDay = "DATE(" + time+")";
            String typeDay1 = "DATE(" + time+")";
            if(loaiThongKe.equalsIgnoreCase("Tuần")) {
                typeDay = "concat(weekofyear("+time+"), \"-\", year(time_in))";
                typeDay1 = "YEAR("+time+"),weekofyear("+time+")";
            }
            else if(loaiThongKe.equalsIgnoreCase("Tháng")) {
                typeDay = "concat(month("+time+"), \"-\", year(time_in))";
                typeDay1 = "YEAR("+time+"),month("+time+")";
            }
            else if(loaiThongKe.equalsIgnoreCase("Năm")) {typeDay = "YEAR(" + time+")";typeDay1 = typeDay;}
            String condition = " 1 ";
            
            String select = "count(id),sum(thanh_tien),"+typeDay;
            if(timeIn != null && timeIn.length() > 0) condition += " DATE("+time + ") >= '" + timeIn + "'";
            if(timeOut != null && timeOut.length() > 0 )  condition += " AND DATE(" + time + ") <= '" + timeOut + "'";
            
            if(loaiXe != null && loaiXe.length() > 0 && !loaiXe.equalsIgnoreCase("tất cả"))  condition += " AND type_vehicle = '" + getTypeVehicleofId(loaiXe) + "'";
            if(trangThai != null && trangThai.length() > 0 && !trangThai.equalsIgnoreCase("tất cả"))  condition += " AND status = '" + trangThai + "'";
            condition = condition.replaceAll("\\s+", " ").trim().toLowerCase();
            condition = condition.replace("AND AND", "AND");
            if(condition.length() >= 3 && condition.substring(0,3).equalsIgnoreCase("AND")) condition = condition.substring(3).trim();
            if(condition.length() >= 3 &&condition.substring(condition.length() - 3,condition.length()).equalsIgnoreCase("AND")) condition = condition.substring(0,condition.length() - 3).trim();
            if(!this.parking_id.equalsIgnoreCase("all")) condition += " AND parking_id =  "+ this.parking_id;
            else {
                if(!this.parking_id.equalsIgnoreCase("all")) condition = " parking_id =  "+ this.parking_id + " AND " + condition;
                }
            condition += " GROUP BY " + typeDay1;
//            System.out.println(condition);
            db.kq = null;
            db.selectQuickNew(select,3, "vehicle_in_out", condition );
        if(db.kq == null || db.kq.size() < 1) return null;
        else return db.kq;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }
    
}
