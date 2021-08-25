package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import DB.DB;
import DB.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJdbc implements DepartmentDao {

	private Connection conn;

	public DepartmentDaoJdbc(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department obj) {

		PreparedStatement st = null;

		try {
			st = conn.prepareStatement("INSERT INTO department (DepName) values (?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getName());

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);

			} else {
				throw new DbException("erro inesperado, nenhuma linha afetada !");
			}
		} catch (SQLException e) {

			throw new DbException(e.getMessage());

		} finally {

			DB.closeStatement(st);

		}
	}

	@Override
	public void update(Department obj) {
		
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("UPDATE department "
					+ "SET department.DepName = ? "
					+ "WHERE Id = ?");
			
			st.setString(1, obj.getName());
			st.setInt(2, obj.getId());
			
			st.executeUpdate();
			
		} catch (SQLException e) {

			throw new DbException(e.getMessage());

		} finally {

			DB.closeStatement(st);

		}

	}

	@Override
	public void deleteById(Integer id) {

		PreparedStatement st = null;

		try {
			st = conn.prepareStatement("DELETE from department WHERE " + "Id = ?");

			st.setInt(1, id);
			st.executeUpdate();

		} catch (SQLException e) {

			throw new DbException(e.getMessage());

		} finally {

			DB.closeStatement(st);

		}

	}

	@Override
	public Department findById(Integer id) {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("select * from department where department.Id = ?");

			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {
				Department dep = instantiateDepartment(rs);

				return dep;
			}

			return null;

		} catch (SQLException e) {

			throw new DbException(e.getMessage());

		} finally {

			DB.closeStatement(st);
			DB.closeResultSet(rs);

		}
	}

	@Override
	public List<Department> findAll() {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("select * from department ORDER BY DepName");

			rs = st.executeQuery();

			List<Department> list = new ArrayList<>();

			while (rs.next()) {
				Department dep = instantiateDepartment(rs);
				list.add(dep);
			}

			return list;

		} catch (SQLException e) {

			throw new DbException(e.getMessage());

		} finally {

			DB.closeStatement(st);
			DB.closeResultSet(rs);

		}
	}

	public Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department department = new Department();
		department.setId(rs.getInt("department.Id"));
		department.setName(rs.getString("DepName"));

		return department;
	}
}
