package manager;

import com.itextpdf.text.PageSize;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.DB;
import model.Guess;
import static model.Lib.checkDouble;
import static model.Lib.getTodayNgayThangNam;
import model.Manager;

public class ParkingInfoForm extends javax.swing.JFrame {

    
    static DB db = new DB();
    static Manager manager;
    public ParkingInfoForm(Manager manager) {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setVisible(true);
        this.manager = manager;
        setTitle("Quản lí thông tin bãi đỗ - " + manager.name);
        
        //Load bảng thông tin bãi đỗ từ DB
        this.loadTableParkingInfo();
        this.loadDanhSachLoaiXe();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableParkingInfo = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel_name = new javax.swing.JLabel();
        jTextField_Name = new javax.swing.JTextField();
        buttonUpdateFares = new javax.swing.JButton();
        jLabel_address = new javax.swing.JLabel();
        jTextField_Address = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField_Capacity = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField_Overnight = new javax.swing.JTextField();
        jLabel_name1 = new javax.swing.JLabel();
        jTextField_ID = new javax.swing.JTextField();
        buttonTurnBack = new javax.swing.JButton();
        buttonLogout = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        buttonRefresh = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList_TypeVehicle = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TableParkingInfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "ID", "Tên", "Địa chỉ", "Tổng số xe có thể chứa", "Số chỗ còn trống", "Loại Xe", "Qua đêm"
            }
        ));
        TableParkingInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TableParkingInfoMouseReleased(evt);
            }
        });
        TableParkingInfo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TableParkingInfoKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(TableParkingInfo);

        jLabel1.setText("Loại Xe");

        jLabel_name.setText("Tên");

        buttonUpdateFares.setText("Cập nhật");
        buttonUpdateFares.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateFaresActionPerformed(evt);
            }
        });

        jLabel_address.setText("Địa chỉ");

        jLabel4.setText("Tổng số xe có thể chứa");

        jLabel5.setText("Qua đêm");

        jLabel_name1.setText("ID");

        jTextField_ID.setEditable(false);

        buttonTurnBack.setText("Back");
        buttonTurnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTurnBackActionPerformed(evt);
            }
        });

        buttonLogout.setText("Đăng xuất");
        buttonLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLogoutActionPerformed(evt);
            }
        });

        jButton2.setText("Xuất File");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        buttonRefresh.setText("Refresh");
        buttonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRefreshActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(jList_TypeVehicle);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(213, 213, 213)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel_name, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(jTextField_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel_address, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(jTextField_Address, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel_name1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jTextField_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(62, 62, 62)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jTextField_Capacity, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField_Overnight, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGap(284, 284, 284)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(buttonRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(buttonLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(buttonTurnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 890, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(417, 417, 417)
                                .addComponent(buttonUpdateFares, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_Capacity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_name1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_address, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Address, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addComponent(buttonUpdateFares, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonTurnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Overnight, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TableParkingInfoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableParkingInfoMouseReleased
        getNoiDungtuParkingInfoTable();
    }//GEN-LAST:event_TableParkingInfoMouseReleased

    private void TableParkingInfoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TableParkingInfoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN) {
            getNoiDungtuParkingInfoTable();
        }
    }//GEN-LAST:event_TableParkingInfoKeyReleased

    private void buttonUpdateFaresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateFaresActionPerformed
        capNhatBaiDo();
    }//GEN-LAST:event_buttonUpdateFaresActionPerformed

    private void buttonTurnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTurnBackActionPerformed
        this.dispose();
        ManagerForm newManagerForm = new ManagerForm(manager.id);
        newManagerForm.showWindows();
    }//GEN-LAST:event_buttonTurnBackActionPerformed

    private void buttonLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLogoutActionPerformed
        this.dispose();
        LoginForm newlogin = new LoginForm();
        newlogin.showWindows();
    }//GEN-LAST:event_buttonLogoutActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
                xuatFile(TableParkingInfo,"ThongTinBaiDo");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void buttonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRefreshActionPerformed
        this.loadTableParkingInfo();
    }//GEN-LAST:event_buttonRefreshActionPerformed

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
            java.util.logging.Logger.getLogger(ParkingInfoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ParkingInfoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ParkingInfoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ParkingInfoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ParkingInfoForm(manager).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableParkingInfo;
    private javax.swing.JButton buttonLogout;
    private javax.swing.JButton buttonRefresh;
    private javax.swing.JButton buttonTurnBack;
    private javax.swing.JButton buttonUpdateFares;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel_address;
    private javax.swing.JLabel jLabel_name;
    private javax.swing.JLabel jLabel_name1;
    private javax.swing.JList<String> jList_TypeVehicle;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField_Address;
    private javax.swing.JTextField jTextField_Capacity;
    private javax.swing.JTextField jTextField_ID;
    private javax.swing.JTextField jTextField_Name;
    private javax.swing.JTextField jTextField_Overnight;
    // End of variables declaration//GEN-END:variables

    void showWindows() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public void loadTableParkingInfo(){
     // Reset lại bảng
     DefaultTableModel dm = (DefaultTableModel) TableParkingInfo.getModel();
        int rowCount = dm.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            dm.removeRow(i);
        }
        
    //Load bảng parking info vào từ DB
        ArrayList<ArrayList<String>> parkingInfo = this.manager.getParkingInfo();
        if(parkingInfo != null && parkingInfo.size() > 0) {
        for (ArrayList<String> thisParkingInfo : parkingInfo) {
            String[] rowData = thisParkingInfo.toArray(new String[thisParkingInfo.size()]);
               dm.addRow(rowData);
            }
        }
    }
    private void getNoiDungtuParkingInfoTable() {
        int i = TableParkingInfo.getSelectedRow();
        TableModel model = (TableModel) TableParkingInfo.getModel();
         if(model.getValueAt(i, 1) != null)jTextField_ID.setText(String.valueOf(model.getValueAt(i, 1)));
         else jTextField_ID.setText("");
         if(model.getValueAt(i, 2) != null)jTextField_Name.setText(String.valueOf(model.getValueAt(i, 2)));
         else jTextField_Name.setText("");
         if(model.getValueAt(i, 3) != null)jTextField_Address.setText(String.valueOf(model.getValueAt(i, 3)));
         else jTextField_Address.setText("");
         if(model.getValueAt(i, 5) != null)jTextField_Capacity.setText(String.valueOf(model.getValueAt(i, 5)));
         else jTextField_Capacity.setText("");
         if(model.getValueAt(i, 7) != null)jTextField_Overnight.setText(String.valueOf(model.getValueAt(i, 7)));
         else jTextField_Overnight.setText("");
         if(model.getValueAt(i, 6) != null){
         String[] arr_TypeVehicle = String.valueOf(model.getValueAt(i, 6)).split(",");
             if(arr_TypeVehicle != null && arr_TypeVehicle.length > 0){
         ArrayList<String> myList = new ArrayList<String>(Arrays.asList(arr_TypeVehicle));
             setSelectedLoaiXe(myList);
             }
         }
         else jList_TypeVehicle.removeAll();
        
    }
   public void capNhatBaiDo(){
        String parking_id = jTextField_ID.getText();
        String parking_name = jTextField_Name.getText().trim();
        String parking_address = jTextField_Address.getText().trim();
        String parking_capacity = jTextField_Capacity.getText().trim();
        ArrayList<String> type_vehicle = new ArrayList<String>(jList_TypeVehicle.getSelectedValuesList());
        System.out.println(type_vehicle);
        String over_night = jTextField_Overnight.getText().trim();
        System.out.println(over_night.length());
        if(parking_id == null || parking_id.length() < 1  || !checkDouble(parking_id)) JOptionPane.showMessageDialog(null, "Kiểm tra lại id bãi đỗ");
        else if(parking_name == null || parking_name.length() < 1) JOptionPane.showMessageDialog(null, "Kiểm tra lại tên bãi đỗ");
        else if( !checkDouble(parking_capacity)) JOptionPane.showMessageDialog(null, "Kiểm tra lại tổng số xe có thể chứa của bãi đỗ");
        else if( over_night != null && over_night.length() > 0 && !checkDouble(over_night)) JOptionPane.showMessageDialog(null, "Kiểm tra lại giá vé qua đêm");
        else  {
        if(this.manager.updateParkingInfo(parking_id, parking_name,parking_address,parking_capacity,type_vehicle,over_night )) {JOptionPane.showMessageDialog(null, "Cập nhật thông tin bãi đỗ thành công");this.loadTableParkingInfo();}
        else JOptionPane.showMessageDialog(null, "Cập nhật thông tin bãi đỗ thất bại");
        }
   }
   private void xuatFile(JTable table, String fileName) {
        String url = fileName;
        String[] tenCot = {"Mã số","Tên Bãi Đỗ","Địa Chỉ","Tổng Số Xe Có Thể Chứa","Số Chỗ Còn Trống","Loại Xe","Qua Đêm"};
        String title = "  Thông Tin Bãi Đỗ";
        
        String bf = "Họ và tên         : Lê Ngọc Long\n"
                + "MSSV              : 20142659\n"
                + "Phần mềm quản lí bãi giữ xe";
        bf += "\n\nTrường Đại Học Bách Khoa Hà Nội                                                                   Cộng Hòa - Xã Hội - Chủ Nghĩa - Việt Nam\n"
                + " Bãi giữ xe "+manager.parking_name +"                                                                            Độc Lập - Tự Do - Hạnh Phúc" ;
        bf += "\n\n                                                                                         " +title.toUpperCase() +"\n";
        
        
        String af = "Hà Nội, Việt Nam Ngày "+ getTodayNgayThangNam()+"\n" +
                     "Người tạo bảng : " + manager.name;
        
        
        //Model.ThuVien.xuatFilePDF(url,table,title,PageSize.A3);
        model.Lib.xuatFilePDF2(url, bf,tenCot,af,table, title,PageSize.A3);
    }
   public void loadDanhSachLoaiXe(){
       jList_TypeVehicle.removeAll();
   ArrayList<String> arrTypeVehicle = Guess.getTypeVehicle(null);
   if(arrTypeVehicle == null || arrTypeVehicle.size() < 1) return;
   String[] arr = arrTypeVehicle.toArray(new String[arrTypeVehicle.size()]);
   jList_TypeVehicle.setListData(arr);
//   setSelectedLoaiXe(manager.getTypeVehicle());
   }
   public void setSelectedLoaiXe(ArrayList<String> arrTypeVehicle1){
   ArrayList<String> arrTypeVehicle = Guess.getTypeVehicle(null);
   if(arrTypeVehicle == null || arrTypeVehicle.size() < 1) return;
   if(arrTypeVehicle1 == null || arrTypeVehicle1.size() < 1) return;
   ArrayList<String> deli = new ArrayList<String>() ;
       for (int i = 0; i < arrTypeVehicle.size(); i++) {
           if(arrTypeVehicle1.contains(arrTypeVehicle.get(i))){
               deli.add(String.valueOf(i));
           }
       }
       String[] arrString = deli.toArray(new String[deli.size()]);
       int[] arrInt = new int[arrString.length];
       for (int i = 0; i < arrString.length; i++) {
           arrInt[i] = Integer.valueOf(arrString[i]);
       }
       jList_TypeVehicle.setSelectedIndices(arrInt);
   }

}
