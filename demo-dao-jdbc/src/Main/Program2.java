package Main;

import java.util.List;
import java.util.Scanner;
import model.dao.Dao_Factory;
import model.dao.DepartmentDAO;
import model.entitites.Department;

public class Program2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DepartmentDAO departmentDAO = Dao_Factory.createDepartmentDAO();

        System.out.println("=== TEST 1: Department findAll ===");
        List<Department> list = departmentDAO.findAll();
        
        for (Department department : list) {
            System.out.println(department);
        }
        
        System.out.println("");
        System.out.println("=== TEST 2: Department findbyId ===");
        Department dep = departmentDAO.findbyid(1);
        System.out.println(dep);
        
        System.out.println("");
        System.out.println("=== TEST 3: Department insert ===");
        Department newdep = new Department(null, "Music");
        departmentDAO.insert(newdep);
        
        System.out.println("Inserted! New ID: " + newdep.getId());
        
        System.out.println("");
        System.out.println("=== TEST 4: Department update ===");
        dep = departmentDAO.findbyid(9);
        dep.setName("Crypto");
        departmentDAO.update(dep);
        System.out.println("Update Completed!");
        
        System.out.println("");
        System.out.println("=== TEST 5: Department delete ===");
        System.out.print("Enter id for delete test: ");
        int id = sc.nextInt();
        
        departmentDAO.deletebyid(id);
        System.out.println("ID: " + id + " DELETED WITH SUCCESS!");
        
        sc.close();
    }
}
