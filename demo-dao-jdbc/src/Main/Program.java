package Main;

import java.util.List;
import model.dao.Dao_Factory;
import model.dao.SellerDao;
import model.entitites.Department;
import model.entitites.Seller;

public class Program {

    public static void main(String[] args) {
        SellerDao sellerDao = Dao_Factory.createSellerDao();
        
        System.out.println("=== TEST 1: Seller findById ===");
        Seller seller = sellerDao.findbyid(7);
        System.out.println(seller);
        
        System.out.println("");
        System.out.println("=== TEST 2: Seller findByDepartment ===");
        Department department = new Department(2, null);
        List<Seller> list = sellerDao.findbyDepartment(department);
        for (Seller obj : list) {
            System.out.println(obj);
        }
        
        System.out.println("");
        System.out.println("=== TEST 3: Seller findAll ===");

        list = sellerDao.findAll();
        for (Seller obj : list) {
            System.out.println(obj);
        }
    }
    
}
