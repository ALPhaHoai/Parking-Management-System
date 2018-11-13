package model;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.*;
import java.sql.SQLException;
import java.text.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;
import javax.swing.*;
import javax.swing.table.*;
import org.apache.poi.xssf.usermodel.*;

/**
 *
 * @author Long
 */
public class Lib {
   public static boolean checkDouble(String textField){
       if(textField == null || textField.length() < 1) return false;
    try {
            double in = Double.parseDouble(textField);
            return true; //Đây là 1 số
          }
         catch (NumberFormatException e) {
            return false; // Đây không phải là số
        }
    }
   public static boolean checkNumeric(String textField){
       if(textField == null || textField.length() < 1) return false;
    try {
            int in = Integer.parseInt(textField);
            return true; //Đây là 1 số
          }
         catch (NumberFormatException e) {
            return false; // Đây không phải là số
        }
    }
   public static void openPDFfile(String url) {
        
        try {
        //Process p = Runtime.getRuntime().exec ("\"" +url + "\""); cach nay cham hon
        if (Desktop.isDesktopSupported()) {       
        File myFile = new File(url);
        Desktop.getDesktop().open(myFile);
        }  
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Đã có lỗi xảy ra : openPDFfile " + ex, "ERROR", 2);
        }
    
    }
   public static void xuatFilePDF2(String fileName ,String textBefore,String[] columnName, String textAfter, JTable table, String title, Rectangle PageSize) {
        try{
        BaseFont bf = BaseFont.createFont("io\\font\\vuTimes.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(bf,15);
        
            
            com.itextpdf.text.Document document = new com.itextpdf.text.Document(PageSize);
        
//        String urlFile = System.getProperty("user.dir") +"/io/" +fileName + ".pdf";
        String urlFile = "io\\" +fileName + ".pdf";
        PdfWriter writer;
        File file = new File(urlFile);
        file.getParentFile().mkdirs();//Tạo thư mục cha cho file
        writer = PdfWriter.getInstance(document,new FileOutputStream(urlFile));

        // writer = PdfWriter.getInstance(document, new
        // FileOutputStream("my_jtable_fonts.pdf"));
        document.open();
        PdfContentByte cb = writer.getDirectContent();

        PdfTemplate tp = cb.createTemplate(table.getWidth(), table.getHeight());
        
        
        
        document.addTitle(title.trim());
        
        int hang = table.getRowCount();
        int cot = table.getColumnCount();
        PdfPTable pdftable = new PdfPTable(cot);
        for(int i = 0; i < cot; i++){
            
        //pdftable.addCell(columnName[i]);
        pdftable.addCell(new PdfPCell(new Phrase(columnName[i],font)));
        }
        //DefaultTableModel dm = new (DefaultTableModel) table.getModel();
        TableModel model = (TableModel) table.getModel();
        String gt = "";
        for(int j= 0; j < hang; j++){
            for(int i = 0; i < cot; i++){
                gt = "";
                if(model.getValueAt(j,i) != null) gt = model.getValueAt(j,i).toString();
                if(gt.trim().equals("") || gt == null){ pdftable.addCell(" ");}
            else {
                    //pdftable.addCell(gt,font);
                pdftable.addCell(new PdfPCell(new Phrase(gt,font)));
                }   
        }}
        document.add(new Paragraph(textBefore + "\n\n",font));
        document.add(pdftable);
        document.add(new Paragraph("\n\n" +textAfter,font));
         
        
           
        
        
        
        // step 5: we close the document
        
        document.close();
        if (JOptionPane.showConfirmDialog(null, "Xuất file " +title.trim() +" thành công!\n" +"Bạn có muốn mở không ?") == JOptionPane.YES_OPTION) {
        openPDFfile(urlFile);
        }
        //JOptionPane.showMessageDialog(null, "Xuất file " +title +" thành công!\n" + url);
        }
        catch(DocumentException ex){
        JOptionPane.showMessageDialog(null, "Đã có lỗi xảy ra :" + ex, "ERROR", 2);
        }
        catch(IOException ex){
        JOptionPane.showMessageDialog(null, "Đã có lỗi xảy ra :" + ex, "ERROR", 2);
        }
        
         
    }
   public static void xuatFileExcel(String fileName ,XSSFWorkbook wb) {
        try{
        
//        String urlFile = System.getProperty("user.dir") +"/io/" +fileName + ".pdf";
        String urlFile = "io\\" +fileName + ".xlsx";
        File file = new File(urlFile);
        file.getParentFile().mkdirs();//Tạo thư mục cha cho file
        //Write excell to file system
        FileOutputStream fos = new FileOutputStream(file);
        wb.write(fos);
        fos.close();

        // writer = PdfWriter.getInstance(document, new
        // FileOutputStream("my_jtable_fonts.pdf"));
       
        if (JOptionPane.showConfirmDialog(null, "Xuất file " +fileName.trim() +" thành công!\n" +"Bạn có muốn mở không ?") == JOptionPane.YES_OPTION) {
        openPDFfile(urlFile);
        }
        //JOptionPane.showMessageDialog(null, "Xuất file " +title +" thành công!\n" + url);
        }
        catch(IOException ex){
        JOptionPane.showMessageDialog(null, "Đã có lỗi xảy ra :" + ex, "ERROR", 2);
        }
        
         
    }
   public static String getTodayNgayThangNam(){
    Date today = new Date();
    String ngay = new SimpleDateFormat("dd").format(today);
    String thang = new SimpleDateFormat("MM").format(today);
    String nam = new SimpleDateFormat("YYYY").format(today);
    return (ngay + "/" + thang + "/" + nam);
    
    }
   public static String getCellVal(DefaultTableModel dm, int x, int y){
   if(dm.getValueAt(x,y) != null) return dm.getValueAt(x,y).toString();
   else return null;
   }
   public static void loadTable(JTable table, ArrayList<ArrayList<String>> vehicleIn){
   
     // Reset lại bảng
     DefaultTableModel dm = (DefaultTableModel) table.getModel();
        int rowCount = dm.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            dm.removeRow(i);
        }
        
    //Load bảng xe vào từ DB
        if(vehicleIn != null && vehicleIn.size() > 0) {
        for (ArrayList<String> thisVehicleIn : vehicleIn) {
            String[] rowData = thisVehicleIn.toArray(new String[thisVehicleIn.size()]);
               dm.addRow(rowData);
            }
        }
   }
  
}
