package model.dao;

import model.dao.implementation.SellerDaoJDBC;

public class Dao_Factory {
    
    public static SellerDao createSellerDao(){
        return new SellerDaoJDBC();
    }
}
