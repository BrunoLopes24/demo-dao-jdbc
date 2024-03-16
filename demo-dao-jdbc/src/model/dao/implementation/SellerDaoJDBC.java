package model.dao.implementation;

import db.Db;
import db.DbException;
import java.util.List;
import model.dao.SellerDao;
import model.entitites.Seller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.entitites.Department;

public class SellerDaoJDBC implements SellerDao {
    
    private Connection conn;
    
    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public void insert(Seller obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void update(Seller obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void deletebyid(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public Seller findbyid(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            st = conn.prepareStatement("SELECT seller.*,department.Name "
                    + "AS DepName "
                    + "FROM Coursejdbc.seller INNER JOIN Coursejdbc.department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE seller.Id = ?"
            );
            
            st.setInt(1, id);
            
            rs = st.executeQuery();
            
            if (rs.next()) { // Testa se vem resultado a seguir
                Department dep = instatiateDepartment(rs);    
                Seller obj = instatiateSeller(rs, dep);
                return obj;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            Db.closeStatement(st);
            Db.closeResultSet(rs);
        }
        
        
    }
    
    @Override
    public List<Seller> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public List<Seller> findbyDepartment(Department department) {
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            st = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
                    + "FROM Coursejdbc.seller INNER JOIN Coursejdbc.department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE DepartmentId = ? "
                    + "ORDER BY Name"
            );
            
            st.setInt(1, department.getId());
            
            rs = st.executeQuery();
            
            
            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();
            
            while (rs.next()) { // Testa se vem resultado a seguir
                Department dep = map.get(rs.getInt("DepartmentId"));
                
                if (dep == null) {
                    dep = instatiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }
                   
                Seller obj = instatiateSeller(rs, dep);
                list.add(obj);
            }
            
            return list;
            
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            Db.closeStatement(st);
            Db.closeResultSet(rs);
        }
    }
    
    
    private Department instatiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));
        return dep;
    }
    
    private Seller instatiateSeller(ResultSet rs, Department dep) throws SQLException {
        Seller obj = new Seller();
        obj.setId(rs.getInt("Id"));
        obj.setName(rs.getString("Name"));
        obj.setEmail(rs.getString("Email"));
        obj.setBaseSalary(rs.getDouble("BaseSalary"));
        obj.setBithDate(rs.getDate("BirthDate"));
        obj.setDepartment(dep);
        return obj;
    }

}
