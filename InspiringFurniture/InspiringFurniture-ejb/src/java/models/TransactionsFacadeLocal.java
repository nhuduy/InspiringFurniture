/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.Transactions;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author DuyAnh
 */
@Local
public interface TransactionsFacadeLocal {

    void create(Transactions transactions);

    void edit(Transactions transactions);

    void remove(Transactions transactions);

    Transactions find(Object id);

    List<Transactions> findAll();

    List<Transactions> findRange(int[] range);

    int count();
    
    List<Transactions> findByUser(Object userId);
    
}
