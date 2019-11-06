/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCP;

import Model.FileInfo;
import Utils.AnalysisClass;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author trung
 */
public class ClientTCP {
    
    private Socket socket;
    private  String SERVER_IP;
    private  int SERVER_PORT;
    private JOptionPane jOptionPane;
    private AnalysisClass ac;
    private String  result;
    

    public ClientTCP(String SERVER_IP, int SERVER_PORT) {
        this.SERVER_IP = SERVER_IP;
        this.SERVER_PORT = SERVER_PORT;
        this.jOptionPane = jOptionPane;
        
    }
    
    // conect to server
    
    public void conectServer()
    {
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            
            
        } catch (IOException ex) {
            Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendFile(String sourceFilePath, String destinationDir, String Ex)
    {
//        System.out.println("TCP.ClientTCP.sendFile()");
        
        
        try {
            // send title
            DataOutputStream dataOutputStream = new DataOutputStream(this.socket.getOutputStream());
            dataOutputStream.writeUTF("Sendding file about : ");
            
            // send content
            
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(this.socket.getOutputStream());
            
            FileInfo fileInfo = coverToFileInfo(sourceFilePath, destinationDir, Ex);
//            System.out.println("Done covert file to byte");
            
//            System.out.println("Prepare send file to server\n" + fileInfo );
            
            objectOutputStream.writeObject(fileInfo);
            
            // give res
            
            ObjectInputStream objectInputStream = new ObjectInputStream(this.socket.getInputStream());
            FileInfo fileInfo1 = (FileInfo) objectInputStream.readObject();
            
            if(fileInfo1 != null && fileInfo1.isStatus() ==  true )
                System.out.println("Thành công");
            
            
            // Nhận về một object kết quả
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            this.ac = (AnalysisClass) objectInputStream.readObject();
            
            // nhận kết quả của run file về
            
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            this.result = dis.readUTF();
            
            
            dataOutputStream.close();
            objectOutputStream.close();
            objectInputStream.close();
            dis.close();
            
//            textAreaLog.append("Done!" + "\n");
            
        } catch (IOException ex) {
            Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(socket != null)
                try {
                    socket.close();
            } catch (IOException ex) {
                Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
    }
    public AnalysisClass getAnalys()
    {
        return this.ac;
    }
    public String getRequest()
    {
        return this.result;
    }
    private FileInfo coverToFileInfo(String sourceFilePath, String destinationDir, String Ex) {
        
        FileInfo fileInfo = null;
        BufferedInputStream bufferedInputStream = null;
        try {
            File sourceFile = new File(sourceFilePath);
            bufferedInputStream = new BufferedInputStream(new FileInputStream(sourceFile));
            fileInfo = new FileInfo();
            byte[] fileBytes = new byte[(int) sourceFile.length()];
            // get file info
            bufferedInputStream.read(fileBytes, 0, fileBytes.length);
            fileInfo.setFilename(sourceFile.getName());
            fileInfo.setDataBytes(fileBytes);
            fileInfo.setEx(Ex);
            fileInfo.setDestinationDirectory(destinationDir);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                bufferedInputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return fileInfo;
        
        
    }
    
    
}
