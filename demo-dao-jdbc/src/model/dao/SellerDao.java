package model.dao;

import java.util.List;
import model.entitites.Department;
import model.entitites.Seller;

public interface SellerDao {
    
    void insert (Seller obj);
    void update (Seller obj);
    void deletebyid (Integer id);
    Seller findbyid (Integer id);
    List<Seller> findAll();;
    List<Seller> findbyDepartment(Department department);
    
}
