package model.dao.implementation;

import db.Db;
import db.DbException;
import java.util.List;
import model.dao.DepartmentDAO;
import model.entitites.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DepartmentDaoJDBC implements DepartmentDAO{
    private Connection conn;
    
    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }
    
    
    @Override
    public void insert(Department obj) {
        PreparedStatement st = null;
        
        try {
            st = conn.prepareStatement("INSERT INTO Coursejdbc.department "
                    + "(Name) "
                    + "VALUES"
                    + "(?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            
            st.setString(1, obj.getName());
            
            int rowsAffected = st.executeUpdate();
            
            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    
                    obj.setId(id);
                }
                Db.closeResultSet(rs);
            }else{
                throw new DbException("Error! No rows affected");
            }
            
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            Db.closeStatement(st);
        }
    }
    
    @Override
    public void update(Department obj) {
        PreparedStatement st = null;
        
        try {
            st = conn.prepareStatement("UPDATE Coursejdbc.department "
                    + "SET Name = ?"
                    + "WHERE Id = ?"
            );
            
            st.setString(1, obj.getName());            
            st.executeUpdate();
            
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            Db.closeStatement(st);
        }
    }
    
    @Override
    public void deletebyid(Integer id) {
        PreparedStatement st = null;
        
        try {
            st = conn.prepareStatement("DELETE FROM Coursejdbc.department "
                    + "WHERE Id = ?"
            );
            st.setInt(1, id);
            
            st.executeUpdate();
            
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            Db.closeStatement(st);
        }
    }
    
    @Override
    public Department findbyid(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            st = conn.prepareStatement("SELECT department.*,department.Name "
                    + "AS DepName "
                    + "FROM Coursejdbc.department "
                    + "WHERE department.Id = ?"
            );
            
            st.setInt(1, id);
            
            rs = st.executeQuery();
            
            if (rs.next()) { // Testa se vem resultado a seguir
                Department dep = instatiateDepartment(rs);
                return dep;
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
    public List<Department> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            st = conn.prepareStatement("SELECT * FROM Coursejdbc.department;");
            
            rs = st.executeQuery();
            
            List<Department> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();
            
            while (rs.next()) {
                Department dep = map.get(rs.getString("Name"));
                
                if (dep == null) {
                    dep = instatiateDepartment(rs);
                    map.put(rs.getInt("Id"), dep);
                }
                
                list.add(dep);
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
        dep.setId(rs.getInt("Id"));
        dep.setName(rs.getString("Name"));
        return dep;
    }
}
