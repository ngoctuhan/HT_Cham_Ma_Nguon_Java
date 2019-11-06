/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.History;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author trung
 */
public class HistoryDAO implements ICommon<History>{
    
    private Connection connection;

    public HistoryDAO() {
        DbUtils dbUtils = new DbUtils();
        this.connection = dbUtils.getConnection();
    }
    
    public void closeCnn() throws SQLException
    {
        this.connection.close();
    }
    @Override
    public List<History> getAll() 
    {
        
        List<History> list = new ArrayList<History>();
        String command = "SELECT * FROM History";
        ResultSet resultSet = DbUtils.executeQuery(this.connection, command);
        History history = new History();
        System.out.println("Add to list ");
        try {
            while(resultSet.next())
            {
                history = new History(resultSet.getString("IPS"), 
                        resultSet.getString("FileName"), resultSet.getString("Time"),resultSet.getString("Status") );
                System.out.println(history);
                list.add(history);
            }
                
            resultSet.close();
//            DbUtils.closeConnection(this.connection);
            
        } catch (SQLException ex) {
            System.out.println("Không thể lấy giá trị");
            Logger.getLogger(HistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }

    @Override
    public Optional<History> get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(History t) {
        
        try {
            Statement statement = this.connection.createStatement();
            String sql = "INSERT INTO History(IPS, FileName, Time, Status) VALUES ( '" 
                + t.getIp() + "' , '" + t.getNameFile() + "' , '" + t.getTime() + "' , '" + t.getStatus() +"');";
            System.out.println(sql);
            
            int rs = statement.executeUpdate(sql);
            System.out.println(rs);
        } catch (SQLException ex) {
            Logger.getLogger(HistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void update(History t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(History t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void main(String[] args) {
        HistoryDAO hdao = new HistoryDAO();
//        History h = new History("127.0.0.1", "input.txt", "null" , "Yes");
//        hdao.save(h);
        List<History> list = hdao.getAll();
        System.out.println(list.get(0));
        
    }
}
