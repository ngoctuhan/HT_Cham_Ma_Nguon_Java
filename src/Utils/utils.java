/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Model.FileInfo;
import TCP.ServerTCP;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author trung
 */
public class utils {
    
    private static boolean creatFile(FileInfo fileInfo) {
        
        BufferedOutputStream bufferedOutputStream = null;
         
        try {
            if (fileInfo != null) {
                File fileReceive = new File(fileInfo.getDestinationDirectory() 
                        + fileInfo.getFilename());
                bufferedOutputStream = new BufferedOutputStream(
                        new FileOutputStream(fileReceive));
                // write file content
                bufferedOutputStream.write(fileInfo.getDataBytes());
                bufferedOutputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                bufferedOutputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(ServerTCP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }
    
    public static boolean checkFileJava(String filePath)
    {
        // cắt lấy phần đuôi để so sánh
        String format = "";
        
        for(int i = filePath.length()-1; i>=0; i--)
        {
            if(filePath.charAt(i) == '.') break;
            String tmp = ""+filePath.charAt(i);
            format = tmp + format;
        }
        return format.equalsIgnoreCase("java");
    }
    
    public static int checkOutput(String filename1, String filename2) throws IOException
    {
        try {
            String content1 = new String(Files.readAllBytes(Paths.get(filename1)),
            StandardCharsets.UTF_8);//đưa về chuẩn utf-8
            content1 = content1.trim();
            // System.out.println(content1 + "..");
            String content2 = new String(Files.readAllBytes(Paths.get(filename2)),
            StandardCharsets.UTF_8);//đưa về chuẩn utf-8	

            content1 = content2.trim();
            // System.out.println(content2+"..");
            if(content1.equals(content2)) return 1;
            return 0;
        } catch (Exception e) {
            return 2;
        }
	
    }
    public static void copyFile(String sou, String des, String Ex) throws FileNotFoundException, IOException
    {
        //sou = "C:\\Users\\trung\\Desktop\\Solution\\" + Ex + "\\" + sou;
        des = "C:\\Users\\trung\\Desktop\\Solution\\" + Ex + "\\" + des;
        FileInputStream fis = new FileInputStream(new File(sou)); 
  
        /* assuming that the file exists and need not to be 
           checked */
        FileOutputStream fos = new FileOutputStream(new File(des)); 
  
        int b; 
        while  ((b=fis.read()) != -1) 
            fos.write(b); 
  
        /* read() will readonly next int so we used while 
           loop here in order to read upto end of file and 
           keep writing the read int into dest file */
        fis.close(); 
        fos.close();
    }
    public static void main(String[] args) throws IOException {
        
        String path1 = "C:\\Users\\trung\\Desktop\\Solution\\A\\output.txt";
        String path2 = "C:\\Users\\trung\\Desktop\\Solution\\A\\outputc.txt";
        System.out.println( checkOutput(path1, path2) );
        
    }
   
}
