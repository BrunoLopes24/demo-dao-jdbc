package Main;

import model.dao.Dao_Factory;
import model.dao.SellerDao;
import model.entitites.Seller;

public class Program {

    public static void main(String[] args) {
        SellerDao sellerDao = Dao_Factory.createSellerDao();
        
        System.out.println("=== TEST 1: Seller findById ===");
        Seller seller = sellerDao.findbyid(7);
        System.out.println(seller);
        
        
    }
    
}
