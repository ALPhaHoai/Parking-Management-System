package admin;

import com.itextpdf.text.PageSize;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import manager.LoginForm;
import model.DB;
import static model.Lib.loadTable;
import model.Admin;
import static model.Lib.checkNumeric;
import static model.Lib.getCellVal;
import static model.Lib.getTodayNgayThangNam;
import static model.Lib.xuatFileExcel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author hctha_000
 */
public class QuanLiXeVaoRa extends javax.swing.JFrame {

    static DB db = new DB();
    static Admin admin;
    ArrayList<String> typeVehicle ;
    
    public QuanLiXeVaoRa(Admin admin) {
            initComponents();
            this.setExtendedState(MAXIMIZED_BOTH);
            this.setVisible(true);
            this.admin = admin;
            setTitle("Quản lí xe vào ra - " + admin.name);

            //Set Các loại xe mà bãi xe có thể chứa
            jComboBox_LoaiXe.removeAllItems();
            this.typeVehicle = this.admin.getTypeVehicle();
            if(typeVehicle != null && typeVehicle.size() > 0) for (String thisType : typeVehicle) {
                    jComboBox_LoaiXe.addItem(thisType);
                }
            //Load bảng xe vào từ DB
            loadDanhSachBaiXe();
            this.loadTableXe();
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField_BienKiemSoat = new javax.swing.JTextField();
        jComboBox_LoaiXe = new javax.swing.JComboBox<>();
        buttonThemXeVao = new javax.swing.JButton();
        buttonXoa = new javax.swing.JButton();
        buttonRefresh = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        buttonLogout = new javax.swing.JButton();
        buttonTurnBack = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableDanhSachXe = new javax.swing.JTable();
        buttonChoXeRa = new javax.swing.JButton();
        buttonTimKiem = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1366, 768));
        getContentPane().setLayout(null);

        jPanel1.setMaximumSize(new java.awt.Dimension(1366, 768));
        jPanel1.setMinimumSize(new java.awt.Dimension(1366, 768));
        jPanel1.setLayout(null);

        jLabel1.setText("Loại Xe");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(60, 290, 70, 30);

        jLabel2.setText("Biển Kiểm Soát");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(350, 290, 100, 30);
        jPanel1.add(jTextField_BienKiemSoat);
        jTextField_BienKiemSoat.setBounds(460, 290, 120, 30);

        jComboBox_LoaiXe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_LoaiXeActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBox_LoaiXe);
        jComboBox_LoaiXe.setBounds(140, 290, 120, 30);

        buttonThemXeVao.setText("Cho xe vào");
        buttonThemXeVao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonThemXeVaoActionPerformed(evt);
            }
        });
        jPanel1.add(buttonThemXeVao);
        buttonThemXeVao.setBounds(110, 360, 120, 30);

        buttonXoa.setText("Xóa");
        buttonXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonXoaActionPerformed(evt);
            }
        });
        jPanel1.add(buttonXoa);
        buttonXoa.setBounds(970, 290, 120, 30);

        buttonRefresh.setText("Xuất file Excel");
        buttonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRefreshActionPerformed(evt);
            }
        });
        jPanel1.add(buttonRefresh);
        buttonRefresh.setBounds(970, 340, 120, 30);

        jButton2.setText("Xuất file PDF");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(810, 340, 120, 30);

        buttonLogout.setText("Đăng xuất");
        buttonLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLogoutActionPerformed(evt);
            }
        });
        jPanel1.add(buttonLogout);
        buttonLogout.setBounds(820, 390, 120, 30);

        buttonTurnBack.setText("Back");
        buttonTurnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTurnBackActionPerformed(evt);
            }
        });
        jPanel1.add(buttonTurnBack);
        buttonTurnBack.setBounds(970, 390, 120, 30);
        jPanel1.add(jLabel11);
        jLabel11.setBounds(1140, 290, 200, 30);

        TableDanhSachXe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Bãi Xe", "Mã Xe Vào Ra", "Loại Xe", "Biển Kiểm Soát", "Thời Gian Vào", "Trạng Thái", "Thành Tiền", "Thời Gian Ra"
            }
        ));
        TableDanhSachXe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TableDanhSachXeMouseReleased(evt);
            }
        });
        TableDanhSachXe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TableDanhSachXeKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(TableDanhSachXe);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(50, 50, 890, 220);

        buttonChoXeRa.setText("Cho xe ra");
        buttonChoXeRa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonChoXeRaActionPerformed(evt);
            }
        });
        jPanel1.add(buttonChoXeRa);
        buttonChoXeRa.setBounds(290, 360, 120, 30);

        buttonTimKiem.setText("Tìm Kiếm");
        buttonTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTimKiemActionPerformed(evt);
            }
        });
        jPanel1.add(buttonTimKiem);
        buttonTimKiem.setBounds(450, 360, 120, 30);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jPanel1.add(jComboBox1);
        jComboBox1.setBounds(1010, 80, 140, 20);

        jLabel3.setText("Danh sách các bãi xe");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(1010, 50, 140, 14);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 10, 1366, 768);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonThemXeVaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonThemXeVaoActionPerformed
         String typeVehicle = (String) jComboBox_LoaiXe.getSelectedItem();
        String bks = jTextField_BienKiemSoat.getText();
        if(this.typeVehicle == null || !this.typeVehicle.contains(typeVehicle)) JOptionPane.showMessageDialog(null, "Loại xe không được phép vào bãi đỗ");
        else {
        boolean thanhCong = this.admin.choXeVao(typeVehicle, bks);
        String message = null;
        if(thanhCong) message = "Cho xe vào thành công";
        else message = "Cho xe vào thất bại";
        JOptionPane.showMessageDialog(null, message);
        }
        //Load bảng xe vào từ DB
        this.loadTableXe();
    }//GEN-LAST:event_buttonThemXeVaoActionPerformed

    private void buttonXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonXoaActionPerformed
 this.xoaXe();    }//GEN-LAST:event_buttonXoaActionPerformed

    private void buttonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRefreshActionPerformed
      writeToExcel(TableDanhSachXe,"DanhSachXeVaoRa");
    }//GEN-LAST:event_buttonRefreshActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         writeToPDF(TableDanhSachXe,"DanhSachXeVaoRa");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void buttonLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLogoutActionPerformed
        this.dispose();
        LoginForm newlogin = new LoginForm();
        newlogin.showWindows();
    }//GEN-LAST:event_buttonLogoutActionPerformed

    private void buttonTurnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTurnBackActionPerformed
        this.dispose();
        AdminForm newAdminForm = new AdminForm(admin.id);
        newAdminForm.showWindows();
    }//GEN-LAST:event_buttonTurnBackActionPerformed

    private void TableDanhSachXeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableDanhSachXeMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_TableDanhSachXeMouseReleased

    private void TableDanhSachXeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TableDanhSachXeKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_TableDanhSachXeKeyReleased

    private void jComboBox_LoaiXeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_LoaiXeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_LoaiXeActionPerformed

    private void buttonChoXeRaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChoXeRaActionPerformed
        choXeRa();
    }//GEN-LAST:event_buttonChoXeRaActionPerformed

    private void buttonTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTimKiemActionPerformed
        timKiem();
    }//GEN-LAST:event_buttonTimKiemActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        this.loadTableXe();
        //Set Các loại xe mà bãi xe có thể chứa
        jComboBox_LoaiXe.removeAllItems();
        this.typeVehicle = this.admin.getTypeVehicle();
        if(typeVehicle != null && typeVehicle.size() > 0) for (String thisType : typeVehicle) {
                jComboBox_LoaiXe.addItem(thisType);
            }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QuanLiXeVaoRa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLiXeVaoRa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLiXeVaoRa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLiXeVaoRa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLiXeVaoRa(admin).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableDanhSachXe;
    private javax.swing.JButton buttonChoXeRa;
    private javax.swing.JButton buttonLogout;
    private javax.swing.JButton buttonRefresh;
    private javax.swing.JButton buttonThemXeVao;
    private javax.swing.JButton buttonTimKiem;
    private javax.swing.JButton buttonTurnBack;
    private javax.swing.JButton buttonXoa;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox_LoaiXe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField_BienKiemSoat;
    // End of variables declaration//GEN-END:variables



    void showWindows() {
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

//    private void xuatHoaDon() {
//        String[] tenCot = {"Mã Độc Giả","Tên Độc Giả","MSSV","Lớp","Khóa","Giới Tính","Ngày Sinh","SĐT","Địa Chỉ","Ngày Tạo"};;
//        String title = "                                                                          Tìm Kiếm Độc Giả";
//        String fileName = "TimKiemDocGia";
//        String bf = "                             Họ và tên         : Lê Ngọc Long\n"
//                + "                              MSSV              : 20142659\n"
//                + "                              Đề tài               : Xây dựng chương trình quản lý thư viện";
//        bf += "\n\n                              Trường Đại Học Bách Khoa Hà Nội                                                                                                           Cộng Hòa - Xã Hội - Chủ Nghĩa - Việt Nam\n"
//                + "                                    Thư viện Tạ Quang Bửu                                                                                                                                Độc Lập - Tự Do - Hạnh Phúc" ;
//        bf += "\n\n                                                           " +title.toUpperCase();
//        
//        
//        String af = "                              Hà Nội, Việt Nam Ngày "+ Model.ThuVien.getTodayNgayThangNam()+"\n" +
//                     "                              Người tạo bảng : Lê Ngọc Long";
//        
//        
//        Model.ThuVien.xuatFilePDF2(fileName, bf,tenCot,af,TableXeVao, title,PageSize.A2);
//    }
    public void loadDanhSachBaiXe(){
    //Load bảng xe vào từ DB
        if(db.selectQuick("id", "parking_info", "1")){
        ArrayList<ArrayList<String>> arr = db.kq;
            for (ArrayList<String> arrayList : arr) {
                jComboBox1.addItem(arrayList.get(0));
            }
        }
    }
    private void choXeRa() {
          int[] xeSelectedRows = TableDanhSachXe.getSelectedRows();
          int soXeDuocChon = xeSelectedRows.length;
        if (soXeDuocChon <= 0 ) {
            JOptionPane.showMessageDialog(null, "Bạn phải chọn ít nhất 1 xe");
        } else {
                TableModel model = (TableModel) TableDanhSachXe.getModel();
                for(int i = 0; i < soXeDuocChon; i++){
                    String id = model.getValueAt(xeSelectedRows[i], 2).toString();
                    String type = model.getValueAt(xeSelectedRows[i], 3).toString();
                    String bks = model.getValueAt(xeSelectedRows[i], 4).toString();
                    String timein = model.getValueAt(xeSelectedRows[i], 5).toString();
                    String status = model.getValueAt(xeSelectedRows[i], 6).toString();
                    if(status.equalsIgnoreCase("Đang trong bãi"))
                    {
                    String alert = "Bạn có muốn cho xe " + type;
                    if(bks != null && bks.length() > 1) alert += " có biển kiểm soát " + bks;
                    alert += " vào bến lúc " + timein + " ra không?";
                    if (JOptionPane.showConfirmDialog(null, alert) == JOptionPane.YES_OPTION) {
                    if(this.admin.choXeRa(id)) 
                    {
                        String giaTien = this.admin.tinhTien(id);
                        if(giaTien != null) JOptionPane.showMessageDialog(null, "Cho xe ra thành công\nSố tiền phải trả là : " + giaTien);
                        else JOptionPane.showMessageDialog(null, "Cho xe ra thành công\nKhông thể thính tiền cho xe này được");
                    
                    }
                    }                
                    } else JOptionPane.showMessageDialog(null, "Không thể cho xe này ra được");
                }
                loadTableXe();
                
            }
    }
     private void xoaXe() {
          int[] xeSelectedRows = TableDanhSachXe.getSelectedRows();
          int soXeDuocChon = xeSelectedRows.length;
        if (soXeDuocChon <= 0 ) {
            JOptionPane.showMessageDialog(null, "Bạn phải chọn ít nhất 1 xe");
        } else {
                TableModel model = (TableModel) TableDanhSachXe.getModel();
                for(int i = 0; i < soXeDuocChon; i++){
                    String id = model.getValueAt(xeSelectedRows[i], 2).toString();
                    String type = model.getValueAt(xeSelectedRows[i], 3).toString();
                    String bks = model.getValueAt(xeSelectedRows[i], 4).toString();
                    String timein = model.getValueAt(xeSelectedRows[i], 5).toString();
                    String status = model.getValueAt(xeSelectedRows[i], 6).toString();
                    String alert = "Bạn có muốn xóa xe " + type;
                    if(bks != null && bks.length() > 1) alert += " có biển kiểm soát " + bks;
                    alert += " vào bến lúc " + timein + " không?";
                    if (JOptionPane.showConfirmDialog(null, alert) == JOptionPane.YES_OPTION) {
                        if (this.admin.deleteVehicle(id)) {
                            JOptionPane.showMessageDialog(null, "Xóa xe ra thành công");
                        } else {
                            JOptionPane.showMessageDialog(null, "Xóa xe ra thất bại");
                        }
                    }                
                }
                
                loadTableXe();
                
            
            }
    }
     public void loadTableXe(){
     String selectedBaiXe = jComboBox1.getSelectedItem().toString();
     if(selectedBaiXe.equalsIgnoreCase("tất cả")) {
     admin.parking_id = "all";
     admin.parking_address = "no_address";
     admin.parking_name = "no_name";
     } else {
        admin.parking_id = selectedBaiXe;
        if(db.selectQuick("name,address", "parking_info", "id = " + selectedBaiXe)){
        admin.parking_name = db.kq.get(0).get(0);
        admin.parking_address = db.kq.get(0).get(1);
        }
        }
        loadTable(TableDanhSachXe, this.admin.getVehicleIn());
     }
     
private void writeToPDF(JTable table, String fileName) {
        String url = fileName;
        String[] tenCot = {"STT","Bãi Xe","Mã Xe Vào Ra","Loại Xe","Biển Kiểm Soát","Thời Gian Vào","Trạng Thái","Thành Tiền","Thời Gian Ra"};
        String title = "Quản Lí Xe Vào Ra";
        String loaiThongKe = "";
        
        String bf = "Họ và tên         : Lê Ngọc Long\n"
                + "MSSV              : 20142659\n"
                + "Phần mềm quản lí bãi giữ xe";
        bf += "\n\nTrường Đại Học Bách Khoa Hà Nội                                                                   Cộng Hòa - Xã Hội - Chủ Nghĩa - Việt Nam\n"
                + " Bãi giữ xe "+admin.parking_name +"                                                                            Độc Lập - Tự Do - Hạnh Phúc" ;
        bf += "\n\n                                                                                         " +title.toUpperCase() +"\n";
        bf+= loaiThongKe.toUpperCase();
        
        
        String af = "Hà Nội, Việt Nam Ngày "+ getTodayNgayThangNam()+"\n" +
                     "Người tạo bảng : " + admin.name;
        
        
        //Model.ThuVien.xuatFilePDF(url,table,title,PageSize.A3);
        model.Lib.xuatFilePDF2(url, bf,tenCot,af,table, title,PageSize.A3);
    }



public void writeToExcel(JTable table, String fileName){
//    try{
   DefaultTableModel dm = (DefaultTableModel) table.getModel();
   XSSFWorkbook wb = new XSSFWorkbook();
   XSSFSheet ws = wb.createSheet();
   
   //Load data to Treemap
   TreeMap<String,Object[]> data = new TreeMap();
   data.put("0", new Object[]{dm.getColumnName(0),dm.getColumnName(1),dm.getColumnName(2),dm.getColumnName(3),dm.getColumnName(4),dm.getColumnName(5),dm.getColumnName(6),dm.getColumnName(7),dm.getColumnName(8)});
        for (int i = 0; i < dm.getRowCount(); i++) {
            data.put(String.valueOf(i+1), new Object[]{
                getCellVal(dm,i,0),
                getCellVal(dm,i,1),
                getCellVal(dm,i,2),
                getCellVal(dm,i,3),
                getCellVal(dm,i,4),
                getCellVal(dm,i,5),
                getCellVal(dm,i,6),
                getCellVal(dm,i,7),
                getCellVal(dm,i,8)
                });
        }
   //Write to sheet
   Set<String> ids = data.keySet();
   XSSFRow row ;
   int rowID = 0;
   
   for(String key:ids){
   row = ws.createRow(rowID++);
   //get data as per key
   Object[] values = data.get(key);
   int cellID = 0;
   for(Object o : values){
   XSSFCell cell = row.createCell(cellID++);
   if(o != null) cell.setCellValue(String.valueOf(o));
   else cell.setCellValue("");
   }
   }
   
   //Write excell to file system
   xuatFileExcel(fileName,wb);
   
//} catch(Exception ex){
//            System.err.println(ex);
//}
}

    private void timKiem() {
        String loaiXe = jComboBox_LoaiXe.getSelectedItem().toString();
        if(loaiXe != null && loaiXe.length() > 0) loaiXe = " type_vehicle like '%" + loaiXe + "%' ";
        else loaiXe = null;
        String bks = jTextField_BienKiemSoat.getText();
        if(bks != null && bks.length() > 0) bks = " bks like '%" + bks + "%' ";
        else bks = null;
        String baiXe = jComboBox1.getSelectedItem().toString();
        if(!checkNumeric(baiXe)) baiXe = null;
        else baiXe = " parking_id = " + baiXe;
        String select = "parking_id,vehicle_id, type_vehicle,bks, time_in, status, thanh_tien, time_out";
        String table  = "vehicle";
        String condition = "";
        if(loaiXe != null) condition = loaiXe;
        if(bks != null) condition += " AND " + bks;
        if(baiXe != null) condition += " AND " + baiXe;
        condition = condition.trim().replaceAll("\\s+", " ");
        condition = condition.replace("AND AND", "AND");
        if(condition.length() >= 3 && condition.substring(0,3).equalsIgnoreCase("AND")) condition = condition.substring(3).trim();
        if(condition.length() >= 3 &&condition.substring(condition.length() - 3,condition.length()).equalsIgnoreCase("AND")) condition = condition.substring(0,condition.length() - 3).trim();
        if(condition.length() < 2) condition = "1";
        DB db = new DB();
          if(db.selectQuick(select, table, condition )){
        if(db.kq == null || db.kq.size() < 1) {
        JOptionPane.showMessageDialog(null, "Không tìm thấy xe nào phù hợp");
        return; }
        else {
            
            ArrayList<ArrayList<String>> getParkingInfo =  db.kq;
            for (int i = 0; i < getParkingInfo.size(); i++) {
                getParkingInfo.get(i).add(0,String.valueOf(i+1));
            }
            loadTable(TableDanhSachXe,getParkingInfo);
            
        }} else JOptionPane.showMessageDialog(null, "Không tìm thấy xe nào phù hợp");
    }
}
