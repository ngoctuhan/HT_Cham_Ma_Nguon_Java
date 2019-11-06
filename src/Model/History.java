/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author trung
 */
public class History {
    
    private String ip;
    private String port;
    private String nameFile;
    private String time;
    private String status;
    
    public History(String ip, String nameFile, String time) {
        this.ip = ip;
        
        this.nameFile = nameFile;
        this.time = time;
    }

    public History(String ip, String nameFile, String time, String status) {
        this.ip = ip;
        this.nameFile = nameFile;
        this.time = time;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public History() {
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "History{" + "ip=" + ip + ", port=" + port + ", nameFile=" + nameFile + ", time=" + time + '}';
    }
    
    
}
