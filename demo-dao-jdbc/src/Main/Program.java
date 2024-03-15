package Main;

import java.util.Date;
import model.entitites.Department;
import model.entitites.Seller;

public class Program {

    public static void main(String[] args) {
        Department obj = new Department(1, "Books");
        
        Seller seller = new Seller(21, "Bob", "bob@gmail.com", new Date(), 3000.00, obj);
                
        System.out.println(seller);
    }
    
}
