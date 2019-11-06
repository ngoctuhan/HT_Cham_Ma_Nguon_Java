/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCP;

import Model.FileInfo;
import Utils.WorkSendingThread;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author trung
 */
public class ServerTCP {
    
    public final static int SERVER_PORT = 3333;
    public static final int NUM_OF_THREAD = 5;
    
    public static void main(String[] args) {
        
        ServerSocket serverSocket = null;
        ExecutorService executor = Executors.newFixedThreadPool(NUM_OF_THREAD);
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Waitting client connect!");
            
            while (true) {                
                
                Socket socket = serverSocket.accept();
                System.out.println("Accepted :  "+  socket);
                
                WorkSendingThread handler = new WorkSendingThread(socket);
                executor.execute(handler);  
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                serverSocket.close();
            } catch (IOException ex) {
                Logger.getLogger(ServerTCP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }  
}
