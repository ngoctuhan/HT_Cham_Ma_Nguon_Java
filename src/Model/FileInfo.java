package Model;


import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author trung
 */
public class FileInfo implements Serializable{
    private static final long serialVersionUID = 1L;
 
    private String destinationDirectory;
    private String sourceDirectory;
    private String filename;
    private long fileSize;
    private int piecesOfFile;
    private int lastByteLength;
    private byte[] dataBytes;
    private boolean status;
    private String Ex;

    public FileInfo() {
    }
    
    
    public FileInfo(String destinationDirectory, String sourceDirectory, String filename, long fileSize, int piecesOfFile, int lastByteLength, byte[] dataBytes, boolean status) {
        this.destinationDirectory = destinationDirectory;
        this.sourceDirectory = sourceDirectory;
        this.filename = filename;
        this.fileSize = fileSize;
        this.piecesOfFile = piecesOfFile;
        this.lastByteLength = lastByteLength;
        this.dataBytes = dataBytes;
        this.status = status;
    }
    
    public String getDestinationDirectory() {
        return destinationDirectory;
    }

    public void setDestinationDirectory(String destinationDirectory) {
        this.destinationDirectory = destinationDirectory;
    }

    public String getSourceDirectory() {
        return sourceDirectory;
    }

    public void setSourceDirectory(String sourceDirectory) {
        this.sourceDirectory = sourceDirectory;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public int getPiecesOfFile() {
        return piecesOfFile;
    }

    public void setPiecesOfFile(int piecesOfFile) {
        this.piecesOfFile = piecesOfFile;
    }

    public int getLastByteLength() {
        return lastByteLength;
    }

    public void setLastByteLength(int lastByteLength) {
        this.lastByteLength = lastByteLength;
    }

    public byte[] getDataBytes() {
        return dataBytes;
    }

    public void setDataBytes(byte[] dataBytes) {
        this.dataBytes = dataBytes;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getEx() {
        return Ex;
    }

    public void setEx(String Ex) {
        this.Ex = Ex;
    }
    
    
    @Override
    public String toString() {
        return "FileInfo{" + "destinationDirectory=" + destinationDirectory + ", sourceDirectory=" + sourceDirectory + ", filename=" + filename + ", fileSize=" + fileSize + ", piecesOfFile=" + piecesOfFile + ", lastByteLength=" + lastByteLength + ", dataBytes=" + dataBytes + ", status=" + status + '}';
    }
    
    
    
    
}
