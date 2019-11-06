/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author trung
 */
public interface ICommon<T> {
    
    List<T> getAll();
 
    Optional<T> get(int id);
 
    void save(T t);
 
    void update(T t);
 
    void delete(T t);
    
    
}
