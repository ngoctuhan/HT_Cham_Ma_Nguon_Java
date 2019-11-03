/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Object.FileInfo;
import TCP.ServerTCP;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
}
