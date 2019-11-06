package Receive;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author trung
 */
public class Student {
    
    private int id;
    private  String fullname;
    private long telphone;
    private float avg;

    public Student(int id, String fullname, long telphone, float avg) {
        this.id = id;
        this.fullname = fullname;
        this.telphone = telphone;
        this.avg = avg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public long getTelphone() {
        return telphone;
    }

    public void setTelphone(long telphone) {
        this.telphone = telphone;
    }

    public float getAvg() {
        return avg;
    }

    public void setAvg(float avg) {
        this.avg = avg;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", fullname=" + fullname + ", telphone=" + telphone + ", avg=" + avg + '}';
    }
    
    
    
}
