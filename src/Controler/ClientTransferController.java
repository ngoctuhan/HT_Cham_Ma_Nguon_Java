package Controler;


import TCP.ClientTCP;
import View.ClientTransferView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author trung
 */
public class ClientTransferController {
    
    private ClientTransferView view;

    public static final String destinationDir = "C:\\Users\\trung\\Documents\\ReceiveSourceCode\\";

    public ClientTransferController(ClientTransferView clientTransferView) {
        this.view = clientTransferView;
    }

    public void sendingFile()
    {
        String SERVER_IP = view.getHost();
        int SERVER_PORT = view.getPort();
        String sourceFilePath = view.getChooser();
        ClientTCP clientTCP = new ClientTCP(SERVER_IP, SERVER_PORT);
        clientTCP.conectServer();
        System.out.println("connected !");
        clientTCP.sendFile(sourceFilePath, destinationDir);
        
        
        
        
    }
    
    
    
    
}
