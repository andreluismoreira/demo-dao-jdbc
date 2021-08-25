package Application;

import java.util.List;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class ProgramDep {

	public static void main(String[] args) {
		
		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		System.out.println("================TESTE 1 seller find by ID ================");
		Department dep = departmentDao.findById(5);
		System.out.println(dep);
		
		System.out.println("\n");
		
		System.out.println("================TESTE 2 seller find All ================");
		List<Department> list = departmentDao.findAll();
		for (Department obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n");

		System.out.println("================TESTE 3 seller Delete By Id ================");
		departmentDao.deleteById(0);
		System.out.println("Deletado com sucesso!");
		
		System.out.println("\n");
		
		System.out.println("================TESTE 4 seller Insert OBJ ================");
		Department depNew = new Department(null, "Handcrafted");
		departmentDao.insert(depNew);
		System.out.println("Adicionado com sucesso!");
		
		System.out.println("\n");
		
		System.out.println("================TESTE 4 seller Update OBJ ================");
		Department dep2New = departmentDao.findById(11);
		dep2New.setName("Cds");
		departmentDao.update(dep2New);
		System.out.println("Update complete: " + dep2New);
		
		System.out.println("\n");
		
	}

}
