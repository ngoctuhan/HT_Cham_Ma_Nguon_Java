/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.NoSuchFileException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author trung
 */
public class ExcuteFileJava {
    
    private static void printLines(String cmd, InputStream ins) throws Exception {
        String line = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(ins));
        while ((line = in.readLine()) != null) {
            System.out.println(cmd + " " + line);
        }
    }
    private static void writeLines(OutputStream os) throws Exception {
        String line = null;
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(os));
        
        
    }
    private static void runProcess(String command) throws Exception {
       
        Process pro = Runtime.getRuntime().exec(command);
        
        printLines(command + " stdout:", pro.getInputStream());
        
        writeLines(pro.getOutputStream());
      
        printLines(command + " stderr:", pro.getErrorStream());
       
        pro.waitFor();
        System.out.println(command + " exitValue() " + pro.exitValue());
    }
    public static boolean RunFile(String filename, String input, String output) {
        
        String cmdClass = "javac -cp src src/Receive/"+filename;
        try {
            runProcess(cmdClass);
            
        } catch (Exception e) {
            return false;
        }
        String [] tmp = filename.split(".java");
        String cmdExcute = "java -cp src; Receive." + tmp[0] + "< " + input + "> " +output;
        ProcessBuilder processBuilder = new ProcessBuilder();
        // Windows
        processBuilder.command("cmd.exe", "/c",cmdExcute);

        try {

            Process process = processBuilder.start();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("\nExited with error code : " + exitCode);

        } catch (IOException e) {
            return false;
        } catch (InterruptedException e) {
            return false;
        }catch (Exception e){
            return false;
        }
       
        
        return true;
    }
//    public static void main(String[] args) {
//        RunFile("testRun.java", "C:\\Users\\trung\\Desktop\\input.txt", "C:\\Users\\trung\\Desktop\\output.txt");
//    }
    
}
