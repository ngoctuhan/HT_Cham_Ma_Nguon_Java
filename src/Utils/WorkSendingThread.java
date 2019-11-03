/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Object.FileInfo;
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
import java.io.File;
import java.io.FileOutputStream;

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
                
            //nameClass 
            String nameFile = fileInfo.getFilename();
            
            
            
            
            
            dataInputStream.close();
            objectInputStream.close();
            objectOutputStream.close();
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
