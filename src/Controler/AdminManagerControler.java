/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import DAO.HistoryDAO;
import Model.History;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author trung
 */
public class AdminManagerControler {
    
    
    public static List<History> getHistory() throws SQLException
    {
        HistoryDAO hdao = new HistoryDAO();
        List<History> list = hdao.getAll();
        hdao.closeCnn();
        return list;
    }
    
}
