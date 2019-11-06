/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import static Controler.ExcuteFileJava.RunFile;
import DAO.HistoryDAO;
import Model.FileInfo;
import Model.History;
import TCP.ServerTCP;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utils.utils;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author trung
 */
public class WorkSendingThread extends Thread{
    
    private Socket socket;

    public WorkSendingThread(Socket socket) {
        this.socket = socket;
    }
    
    public void run()
    {
        System.out.println("Processing: " + socket);
        
        // read title 
        DataInputStream dataInputStream;
        HistoryDAO hdao = new HistoryDAO();
            
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
            String title = dataInputStream.readUTF();

            boolean check = false;
                
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            FileInfo fileInfo = (FileInfo) objectInputStream.readObject();

            if(fileInfo != null) check = creatFile(fileInfo);
            // send corfirm
                
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            fileInfo.setStatus(check);
            fileInfo.setDataBytes(null);
            objectOutputStream.writeObject(fileInfo);
            //
            // create history
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now();  
            System.out.println(dtf.format(now));  
            //nameClass 
            String nameFile = fileInfo.getFilename();
            History h = new History(socket.toString(),nameFile, dtf.format(now));
            
            // anaylys file java and sendeing tree structure java for client
            Class c = TestAnalys.getClass(nameFile);
            AnalysisClass ac = new AnalysisClass(c);
            
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(ac);
            
            //run file to check result 
            nameFile = nameFile.trim();
            String Ex = fileInfo.getEx();
            String pathOutput = "C:\\Users\\trung\\Desktop\\Solution\\"+ Ex +"\\outputc.txt";
            String pathInput = "C:\\Users\\trung\\Desktop\\Solution\\" + Ex + "\\input.txt";
            boolean ktRun = RunFile(nameFile, pathInput, pathOutput);
            
//            System.out.println("Utils.WorkSendingThread.run()");
            // check 2 file ouput and send về cho client
            String s = "";
            if(ktRun){
                String pathOutTrue = "C:\\Users\\trung\\Desktop\\Solution\\"+ Ex + "\\output.txt";
                int kt = utils.checkOutput( pathOutput,pathOutTrue);
                // gửi kết quả cho client

                if(kt == 1) s+= "Yes";
                else if(kt == 0) s += "No";
                else s += "Runtime Error";
                h.setStatus(s);

            }
            else {
                s +="Runtime Error";
                h.setStatus(s);
            }
            hdao.save(h);
            System.out.println("Kết quả : " + s);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF(s);
            
            
            dataInputStream.close();
            objectInputStream.close();
            objectOutputStream.close();
            dos.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(WorkSendingThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(WorkSendingThread.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }

    private boolean creatFile(FileInfo fileInfo) {
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
    
}
