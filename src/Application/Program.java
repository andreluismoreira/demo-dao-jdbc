package Application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
	SellerDao sellerDao = DaoFactory.createSellerDao();
	
	
	System.out.println("================TESTE seller find by ID================");
	Seller seller = sellerDao.findById(3);
	System.out.println(seller);
	
	System.out.println("================TESTE-2 seller find by DEPARTMENT================");
	Department department = new Department(2, null);
	List<Seller> depList = sellerDao.findByDepartment(department);
	
	for (Seller obj : depList) {
		System.out.println(obj);
	}
 }

}
