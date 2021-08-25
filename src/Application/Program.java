package Application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
	Scanner sc = new Scanner (System.in);
	SellerDao sellerDao = DaoFactory.createSellerDao();
	
	
	System.out.println("================TESTE seller find by ID================");
	Seller seller = sellerDao.findById(3);
	System.out.println(seller);
	
	System.out.println("\n");
	
	System.out.println("================TESTE-2 seller find by DEPARTMENT================");
	Department department = new Department(2, null);
	List<Seller> depList = sellerDao.findByDepartment(department);
	
	for (Seller obj : depList) {
		System.out.println(obj);
	}
	
	System.out.println("\n");
	
	System.out.println("================TESTE-3 seller find All================");
	depList = sellerDao.findAll();
	for (Seller obj : depList) {
		System.out.println(obj);
	}
	
	System.out.println("\n");
	
	System.out.println("================TESTE-4 seller INSERT================");
	Seller seller2 = new Seller(null, "Joao", "joao@gamail", new Date(), 2000.00, department);
	sellerDao.insert(seller2);
	System.out.println("inserted, new Id: " + seller2.getId());
	
	System.out.println("\n");
	
	System.out.println("================TESTE-5 seller UPDATE================");
	seller = sellerDao.findById(9);
	seller.setName("Guiguiu");
	sellerDao.update(seller);
	System.out.println("Update complete: new seller = " + seller);
	
	System.out.println("\n");
	
	System.out.println("================TESTE-5 seller DELETE================");
	System.out.println("insira um Id para deleção: ");
	int id = sc.nextInt();
	sellerDao.deleteById(id);
	System.out.println("Delete complete");
	
	sc.close();
	}
	
 }


