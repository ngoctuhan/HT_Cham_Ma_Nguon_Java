/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author trung
 */
public class TestAnalys {
    
    public static Class getClass(String nameFile) throws ClassNotFoundException, MalformedURLException {
       
        while(true)
        {
            File tempFile = new File("C:\\Users\\trung\\Documents\\NetBeansProjects\\HT_Cham_Ma_Nguon_Java\\src\\Receive\\"+nameFile);
            boolean exists = tempFile.exists();
            
            if(exists == true) break;  
            
        }
        try {
            Thread.sleep(8000);
        } catch (InterruptedException ex) {
            Logger.getLogger(TestAnalys.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] nameClass = nameFile.split(".java");
        System.out.println("File Name : " + nameFile);
//            Thread.sleep(3000);
        String classFull = "Receive." + nameClass[0].trim();
        System.out.println(classFull);
            
        Class c = Class.forName(classFull);
        return c;
    }
    public static void main(String[] args) throws ClassNotFoundException, MalformedURLException {
        
        getClass("testRun.java");
        
    }
    
}
