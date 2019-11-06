package Controler;


import TCP.ClientTCP;
import Utils.AnalysisClass;
import Utils.utils;
import View.ClientTransferView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

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
    private AnalysisClass ac;
    private String respons;
    
    public static final String destinationDir = "C:\\Users\\trung\\Documents\\NetBeansProjects\\HT_Cham_Ma_Nguon_Java\\src\\Receive\\";

    public ClientTransferController(ClientTransferView clientTransferView) {
        this.view = clientTransferView;
    }

    public boolean sendingFile(String Ex)
    {
        String SERVER_IP = view.getHost();
        int SERVER_PORT = view.getPort();
        String sourceFilePath = view.getChooser();
        if(utils.checkFileJava(sourceFilePath))
        {
            ClientTCP clientTCP = new ClientTCP(SERVER_IP, SERVER_PORT);
            clientTCP.conectServer();
            System.out.println("connected !");
            clientTCP.sendFile(sourceFilePath, destinationDir,Ex);
            this.ac = clientTCP.getAnalys();
            this.respons = clientTCP.getRequest();
             JOptionPane.showMessageDialog(view, "Submit successful !");
             return true;
        }
        else JOptionPane.showMessageDialog(view, "Just acepted file .java!");
        return false;
        
    }
    public AnalysisClass getAnalys()
    {
        return this.ac;
    }
    public String getResult()
    {
        return this.respons;
    }
    
    
}
