package model.dao;

import java.util.List;
import model.entitites.Department;

public interface DepartmentDAO {
    
    void insert (Department obj);
    void update (Department obj);
    void deletebyid (Integer id);
    Department findbyid (Integer id);
    List<Department> findAll();
    
}
