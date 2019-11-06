/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Acount;
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
public class AcountDAO implements ICommon<Acount>{
    
    
    private Connection connection;

    public AcountDAO() {
        DbUtils dbUtils = new DbUtils();
        this.connection = dbUtils.getConnection();
    }
    
    public void closeCnn() throws SQLException
    {
        this.connection.close();
    }
    @Override
    public List<Acount> getAll() {
        List<Acount> list = new ArrayList<Acount>();
        String command = "SELECT * FROM Acount";
        ResultSet resultSet = DbUtils.executeQuery(this.connection, command);
        Acount acount = new Acount();
        System.out.println("Add to list ");
        try {
            while(resultSet.next())
            {
                acount = new Acount(
                        resultSet.getString("username"), resultSet.getString("password") );
                System.out.println(acount);
                list.add(acount);
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
    public Optional<Acount> get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(Acount t) {
        try {
            Statement statement = this.connection.createStatement();
            String sql = "INSERT INTO Acount(username, password) VALUES ( '" 
                + t.getUsername() + "' ,'" + t.getPassword() + "');";
            System.out.println(sql);
            
            int rs = statement.executeUpdate(sql);
            System.out.println(rs);
        } catch (SQLException ex) {
            Logger.getLogger(HistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Acount t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Acount t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//    public static void main(String[] args) {
//        
//        Acount a = new Acount("admin", "admin");
//        AcountDAO adao = new AcountDAO();
//        adao.save(a);
//        
//    }
//    
//    
    
}
