/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
 

/**
 *
 * @author trung
 */
public class AnalysisClass {
    
    private String nameclass;

    public AnalysisClass(String nameclass) {
        this.nameclass = nameclass;
    }
    
    public String getName() throws ClassNotFoundException
    {
        return Class.forName(this.nameclass).getSimpleName();
    }
    
    public String getSuperClass() throws ClassNotFoundException
    {
        Class<?> aSuperClass = Class.forName(this.nameclass).getSuperclass();
        return aSuperClass.getSimpleName();
    }
    
    public Class<?>[] getInterface() throws ClassNotFoundException
    {
        Class<?>[] itfClasses = Class.forName(this.nameclass).getInterfaces();
        return  itfClasses;
    }
    
    public Class<?>[] getParam() throws ClassNotFoundException, NoSuchMethodException
    {
        Constructor<?> constructor = Class.forName(this.nameclass).getConstructor();
        Class<?>[] paramClasses = constructor.getParameterTypes();
        return paramClasses;
        
//        for (Class<?> paramClass : paramClasses) {
//          System.out.println("Param: " + paramClass.getSimpleName());
//        }
    }
    public Field[] getFields() throws ClassNotFoundException
    {
        Field[] fields = Class.forName(this.nameclass).getDeclaredFields();
        
        return fields;
    }
    public Method[] getMethods()
    {
       
        Method[] methods = null;
        try {
            methods = Class.forName(this.nameclass).getMethods();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AnalysisClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return methods;
    }
   

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        
        try {
            stringBuilder.append("{\nnameclass:" + this.getName() );
            if(this.getSuperClass() != null)
                stringBuilder.append("\nsuperclass:" + this.getSuperClass() );
            
            if (this.getInterface() != null)
            {
                stringBuilder.append("\ninterfaces:");
                for (Class<?> itfClass : this.getInterface()) {
                    stringBuilder.append(itfClass.getSimpleName() +", ");
                }
            }
            if(this.getParam() != null)
            {
                stringBuilder.append("\nparameters:");
                for (Class<?> parameter : this.getParam()){
                    stringBuilder.append(parameter.getSimpleName() +", ");
                }
            }
            
            if(this.getMethods() != null)
            {
                stringBuilder.append("\nmethods:");
                for (Method method : this.getMethods()){
                    stringBuilder.append(method.getName() +", ");
                }
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AnalysisClass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(AnalysisClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return stringBuilder.toString();
    }
    
    public void saveJsonFile() throws ClassNotFoundException
    {
        
        JSONObject obj = new JSONObject();
        // String s = 
        try {

            //-------------------------------------------
            JSONArray listM = new JSONArray();
            
            for (Method method : this.getMethods())
            {
                listM.add(method.getName());
            }
            obj.put("methods", listM);
            
            //-------------------------------------------
            JSONArray listP = new JSONArray();
            for (Field field : this.getFields()) 
            {
                listP.add(field.getName());
            }
            obj.put("fields", listP);
            //-------------------------------------------
            JSONArray listI = new JSONArray();
            for (Class<?> itfClass : this.getInterface()) 
            {
                listI.add(itfClass.getSimpleName());
            }
            obj.put("interfaces", listI);
            //-------------------------------------------
            obj.put("superclass", this.getSuperClass());
            //-------------------------------------------
            obj.put("nameclass", this.getName());
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AnalysisClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            FileWriter file = new FileWriter("C:\\Users\\trung\\Documents\\NetBeansProjects\\ProjectLTMang\\src\\json\\"
                    + this.getName() + ".json");
            file.write(obj.toJSONString());
            file.flush();
            file.close();
        } catch (IOException ex) {
            Logger.getLogger(AnalysisClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println(obj);
    }
    
}
