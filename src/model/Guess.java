/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Arrays;
import static model.Lib.loadTable;
import static model.Manager.availableSlot;


public class Guess {
    DB db = new DB();
    public ArrayList<ArrayList<String>> loadTableParkingInfo(){
    String select = "parking_info.id, parking_info.name, parking_info.address,parking_info.capacity, "
            + "GROUP_CONCAT(typeofvehicle.name ) as typeofvehicle,parking_info.over_night ";
        String table = "parking_info, typeofvehicle , parking_vehicle";
        String condition = "parking_info.id = parking_vehicle.parking_id  \n" +
"AND parking_vehicle.typeofvehicle = typeofvehicle.id  \n" +
"GROUP BY parking_info.id";
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
    
    public ArrayList<ArrayList<String>> timKiemBaiDo(String name, String address, boolean overnight, String typeVehicle){
        typeVehicle = typeVehicle.replaceAll("\\s+", " ");
        typeVehicle = typeVehicle.replace(", ", ",");
        
        String select = "parking_info.id, parking_info.name, parking_info.address,parking_info.capacity, "
            + "GROUP_CONCAT(DISTINCT typeofvehicle.name ) as typeofvehicle,parking_info.over_night ";
        String table = "parking_info, typeofvehicle , parking_vehicle";
        String condition = "parking_info.id = parking_vehicle.parking_id  \n" +
"AND parking_vehicle.typeofvehicle = typeofvehicle.id  " ;
        if(name != null && name.length() > 0) condition += " AND parking_info.name like '%" + name + "%' ";
        if(address != null && address.length() > 0) condition += " AND parking_info.address like '%" + address + "%' ";
        if(overnight) condition += " AND parking_info.over_night is not null ";
        if(typeVehicle != null && typeVehicle.length() > 0) {
        condition += " AND typeofvehicle.name like '" + typeVehicle + "' ";
        }
        condition = condition.replaceAll("\\s+", " ").trim().toLowerCase();
            condition = condition.replace("AND AND", "AND");
            if(condition.length() >= 3 && condition.substring(0,3).equalsIgnoreCase("AND")) condition = condition.substring(3).trim();
            if(condition.length() >= 3 &&condition.substring(condition.length() - 3,condition.length()).equalsIgnoreCase("AND")) condition = condition.substring(0,condition.length() - 3).trim();
            condition += " GROUP BY parking_info.id ";
           System.out.println(condition);
           
           DB db = new DB();
          if(db.selectQuick(select, table, condition )){
        if(db.kq == null || db.kq.size() < 1) {
        return null; }
        else {
            ArrayList<ArrayList<String>> getParkingInfo =  db.kq;
            for (int i = 0; i < getParkingInfo.size(); i++) {
                getParkingInfo.get(i).add(0,String.valueOf(i+1));
                String parking_id = getParkingInfo.get(i).get(1);
                String strTypeVehicle = "";
                System.out.println(parking_id);
                if(Lib.checkNumeric(parking_id)){
                ArrayList<String> arrTypeVehicle = getTypeVehicle(parking_id);
                if(arrTypeVehicle != null && arrTypeVehicle.size() > 0){
                    for (int j = 0; j < arrTypeVehicle.size(); j++) {
                        strTypeVehicle += arrTypeVehicle.get(j);
                        if(j < arrTypeVehicle.size() - 1) strTypeVehicle += ",";
                    }
                }
                }
                strTypeVehicle = strTypeVehicle.trim();
                if(strTypeVehicle.length() > 0) getParkingInfo.get(i).set(5, strTypeVehicle);
                getParkingInfo.get(i).add(4,new Admin().availableSlot(getParkingInfo.get(i).get(0)));
            }
            return getParkingInfo;
            
        }} else return null;
    }

    //Trả về danh dách các loại xe mà bãi xe có thể chứa
    public static ArrayList<String> getTypeVehicle(String parking_id){
        DB db = new DB();
        String condition = " ";
        if(!Lib.checkNumeric(parking_id)) condition = " 1  ORDER BY LENGTH(typeofvehicle.name) DESC";
        else  condition = " parking_info.id = " + parking_id + 
                " AND parking_info.id = parking_vehicle.parking_id AND parking_vehicle.typeofvehicle = typeofvehicle.id  ORDER BY LENGTH(typeofvehicle.name) DESC ";
       if(db.selectQuick("distinct(typeofvehicle.name)", "typeofvehicle, parking_info,parking_vehicle",  condition )){
        if(db.kq == null || db.kq.size() < 1) return null;
        
        ArrayList<String> kkq = new ArrayList<String>();
           for (int i = 0; i < db.kq.size(); i++) {
               kkq.add(db.kq.get(i).get(0));
           }
        return kkq;
    } else return null;
    }
}
