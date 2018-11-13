package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Long
 */
public class Admin extends Manager{
    
    public Admin(String id) {
        super(id);
        this.parking_id = "all";
    }
    public Admin() {
        super();
        this.parking_id = "all";
    }
    public boolean deleteQuanLi(String id){
        try {
            if(db.execute("DELETE FROM account WHERE id = " + id)){
                return true;
            } else return false;
        } catch (SQLException ex) {
            return false;
        }
    }
    public ArrayList<ArrayList<String>> getQuanLiTable(){
    if(db.selectQuick("id, name, user, pass, birthday, sdt,parking_id", "account", "type = 'manager'")){
    if(db.kq != null && db.kq.size() > 0) {
        for (int i = 0; i < db.kq.size(); i++) {
            db.kq.get(i).add(0,String.valueOf(i + 1));
            
        }
    return db.kq;
    }
    else return null;
    } else return null;
    }
    
}
