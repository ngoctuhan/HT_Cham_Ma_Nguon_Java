/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import DAO.AcountDAO;
import Model.Acount;
import java.util.List;

/**
 *
 * @author trung
 */
public class LoginControler {
    
    public static boolean controlerLogin(String username, String password)
    {
        Acount a = new Acount(username, password);
        
        AcountDAO adao = new AcountDAO();
        
        List<Acount> list = adao.getAll();
        
        for(int i=0; i< list.size() ; i++)
        {
            if(a.equals(list.get(i))) return true;
        }
        return false;
    }
}
