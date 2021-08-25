package model.dao;

import DB.DB;
import model.dao.impl.DepartmentDaoJdbc;
import model.dao.impl.SellerDaoJdbc;

public class DaoFactory {

	public static SellerDao createSellerDao() {
		return new SellerDaoJdbc(DB.getConnection());
	}
	
	public static DepartmentDao createDepartmentDao() {
		return new DepartmentDaoJdbc(DB.getConnection());
	}
	
}
