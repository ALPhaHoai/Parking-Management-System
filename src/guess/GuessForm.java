package guess;

import com.itextpdf.text.PageSize;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import manager.LoginForm;
import model.Admin;
import model.DB;
import model.Guess;
import static model.Lib.checkNumeric;
import static model.Lib.getTodayNgayThangNam;
import static model.Lib.loadTable;
import model.Manager;

/**
 *
 * @author Long
 */
public class GuessForm extends javax.swing.JFrame {

    /**
     * Creates new form GuessForm
     */
    Guess guess = new Guess();
    public GuessForm() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
            this.setVisible(true);
        //Load bảng thông tin bãi đỗ từ DB
        this.loadTableParkingInfo();
        this.loadDanhSachLoaiXe();
        setTitle("Tìm kiếm bãi đỗ xe");
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
        jScrollPane2 = new javax.swing.JScrollPane();
        TableParkingInfo = new javax.swing.JTable();
        jLabel_name = new javax.swing.JLabel();
        jTextField_Name = new javax.swing.JTextField();
        jLabel_address = new javax.swing.JLabel();
        jTextField_Address = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        buttonUpdateFares = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        buttonLogout = new javax.swing.JButton();
        jCheckBox_Ovrnight = new javax.swing.JCheckBox();
        buttonUpdateFares1 = new javax.swing.JButton();
        jComboBox_LoaiXe = new javax.swing.JComboBox<>();

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

        jLabel_name.setText("Tên");

        jLabel_address.setText("Địa chỉ");

        jTextField_Address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_AddressActionPerformed(evt);
            }
        });

        jLabel1.setText("Loại Xe");

        jLabel5.setText("Qua đêm");

        buttonUpdateFares.setText("Xóa Chữ");
        buttonUpdateFares.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateFaresActionPerformed(evt);
            }
        });

        jButton2.setText("Xuất File");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        buttonLogout.setText("Đăng xuất");
        buttonLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLogoutActionPerformed(evt);
            }
        });

        jCheckBox_Ovrnight.setText("Cho Gửi Qua Đêm");
        jCheckBox_Ovrnight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_OvrnightActionPerformed(evt);
            }
        });

        buttonUpdateFares1.setText("Tìm Kiếm");
        buttonUpdateFares1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateFares1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel_name, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jTextField_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel_address, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jTextField_Address, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(62, 62, 62)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jComboBox_LoaiXe, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 280, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buttonLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jCheckBox_Ovrnight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(347, 347, 347)
                        .addComponent(buttonUpdateFares1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonUpdateFares, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 890, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(216, 216, 216))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(108, 108, 108)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_address, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Address, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(20, 20, 20)
                            .addComponent(buttonLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jCheckBox_Ovrnight, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(55, 55, 55)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox_LoaiXe, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonUpdateFares, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonUpdateFares1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(146, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TableParkingInfoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableParkingInfoMouseReleased
        getNoiDungtuParkingInfoTable();
    }//GEN-LAST:event_TableParkingInfoMouseReleased

    private void TableParkingInfoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TableParkingInfoKeyReleased
//        if (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN) {
//            getNoiDungtuParkingInfoTable();
//        }
    }//GEN-LAST:event_TableParkingInfoKeyReleased

    private void buttonUpdateFaresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateFaresActionPerformed
        jTextField_Name.setText("");
        jTextField_Address.setText("");
        this.loadDanhSachLoaiXe();
        jCheckBox_Ovrnight.setSelected(false);
    }//GEN-LAST:event_buttonUpdateFaresActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
          writeToPDF(TableParkingInfo,"TimKiemBaiDo");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void buttonLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLogoutActionPerformed
        this.dispose();
        LoginForm newlogin = new LoginForm();
        newlogin.showWindows();
    }//GEN-LAST:event_buttonLogoutActionPerformed

    private void jTextField_AddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_AddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_AddressActionPerformed

    private void jCheckBox_OvrnightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_OvrnightActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox_OvrnightActionPerformed

    private void buttonUpdateFares1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateFares1ActionPerformed
        timKiemBaiDo();
    }//GEN-LAST:event_buttonUpdateFares1ActionPerformed

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
            java.util.logging.Logger.getLogger(GuessForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GuessForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GuessForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GuessForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GuessForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableParkingInfo;
    private javax.swing.JButton buttonLogout;
    private javax.swing.JButton buttonUpdateFares;
    private javax.swing.JButton buttonUpdateFares1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox_Ovrnight;
    private javax.swing.JComboBox<String> jComboBox_LoaiXe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel_address;
    private javax.swing.JLabel jLabel_name;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField_Address;
    private javax.swing.JTextField jTextField_Name;
    // End of variables declaration//GEN-END:variables

    public void loadTableParkingInfo(){
        loadTable(TableParkingInfo,guess.loadTableParkingInfo());
    }

    private void getNoiDungtuParkingInfoTable() {
        int i = TableParkingInfo.getSelectedRow();
        TableModel model = (TableModel) TableParkingInfo.getModel();
         if(model.getValueAt(i, 2) != null)jTextField_Name.setText(String.valueOf(model.getValueAt(i, 2)));
         else jTextField_Name.setText("");
         if(model.getValueAt(i, 3) != null)jTextField_Address.setText(String.valueOf(model.getValueAt(i, 3)));
         else jTextField_Address.setText("");
         if(model.getValueAt(i, 7) != null && model.getValueAt(i, 7).toString().length() > 0 && checkNumeric(model.getValueAt(i, 7).toString()))jCheckBox_Ovrnight.setSelected(true);
         else jCheckBox_Ovrnight.setSelected(false);
         if(model.getValueAt(i, 6) != null) {
         String jTextField_TypeVehicle = String.valueOf(model.getValueAt(i, 6));
         String[] arr_TypeVehicle = jTextField_TypeVehicle.split(",");
         if(arr_TypeVehicle != null && arr_TypeVehicle.length > 0){
             jComboBox_LoaiXe.removeAllItems();
             for (int j = 0; j < arr_TypeVehicle.length; j++) {
                 if(arr_TypeVehicle[j] != null && arr_TypeVehicle[j].length() > 2) jComboBox_LoaiXe.addItem(arr_TypeVehicle[j]);
             }
         }
         }
         else jComboBox_LoaiXe.removeAllItems();
        
    }

    private void timKiemBaiDo() {
        String name = jTextField_Name.getText().trim();
        String address = jTextField_Address.getText().trim();
        boolean overnight = jCheckBox_Ovrnight.isSelected();
        String typeVehicle = jComboBox_LoaiXe.getSelectedItem().toString().trim();
        ArrayList<ArrayList<String>> getParkingInfo = guess.timKiemBaiDo(name, address, overnight, typeVehicle);
        if(getParkingInfo == null || getParkingInfo.size() < 1) JOptionPane.showMessageDialog(null, "Không tìm thấy bản ghi bào phù hợp");
        else loadTable(TableParkingInfo,getParkingInfo);
    }
public void showWindows() {
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
private void writeToPDF(JTable table, String fileName) {
        String url = fileName;
        String[] tenCot = {"STT","ID Bãi Xe","Tên","Địa Chỉ","Tổng Số Xe Có Thể Chứa","Số Chỗ Còn Trống","Loại Xe","Tiền Gửi Qua Đêm"};
        String title = "  Tìm Kiếm Bãi Xe";
            String moreInfo = "";
            if(jTextField_Name.getText() != null && jTextField_Name.getText().length() > 0) moreInfo += " Tên: " + jTextField_Name.getText();
            if(jTextField_Address.getText() != null && jTextField_Address.getText().length() > 0) moreInfo += ", Địa chỉ: " + jTextField_Address.getText();
        if(jCheckBox_Ovrnight.isSelected()) moreInfo += ", Gửi qua đêm : Có";
        if(jComboBox_LoaiXe.getSelectedItem() != null 
                && jComboBox_LoaiXe.getSelectedItem().toString().trim().length() > 0) moreInfo += ", Loại Xe: " + jComboBox_LoaiXe.getSelectedItem().toString().trim();
        moreInfo = moreInfo.replaceAll("\\s+", " ").trim();
            moreInfo = moreInfo.replace(", ,", ",");
            moreInfo = moreInfo.replace(",,", ",");
            if(moreInfo.length() >= 1 && moreInfo.substring(0,1).equalsIgnoreCase(",")) moreInfo = moreInfo.substring(1).trim();
            if(moreInfo.length() >= 1 &&moreInfo.substring(moreInfo.length() - 1,moreInfo.length()).equalsIgnoreCase(",")) moreInfo = moreInfo.substring(0,moreInfo.length() - 1).trim();
        String bf = "Họ và tên         : Lê Ngọc Long\n"
                + "MSSV              : 20142659\n"
                + "Phần mềm quản lí bãi giữ xe";
        bf += "\n\nTrường Đại Học Bách Khoa Hà Nội                                                                   Cộng Hòa - Xã Hội - Chủ Nghĩa - Việt Nam\n"
        + "                                                                                                                                      Độc Lập - Tự Do - Hạnh Phúc" ;
        bf += "\n\n                                                                                         " +title.toUpperCase() +"\n";
        bf+= moreInfo;
        
        
        String af = "Hà Nội, Việt Nam Ngày "+ getTodayNgayThangNam()+"\n" +
                     "Người tạo bảng : Khách";
        
        
        //Model.ThuVien.xuatFilePDF(url,table,title,PageSize.A3);
        model.Lib.xuatFilePDF2(url, bf,tenCot,af,table, title,PageSize.A3);
    }
public void loadDanhSachLoaiXe(){
//Set Các loại xe mà bãi xe có thể chứa
        jComboBox_LoaiXe.removeAllItems();
        jComboBox_LoaiXe.addItem("");
        ArrayList<String> typeVehicle = guess.getTypeVehicle(null);
        if(typeVehicle != null && typeVehicle.size() > 0) for (String thisType : typeVehicle) {
                jComboBox_LoaiXe.addItem(thisType);
            }    
    }

   
}
