package day03.employee.controller;

import java.util.List;

import day03.employee.model.dao.EmployeeDAO;
import day03.employee.model.vo.Employee;

public class EmployeeController {
	private EmployeeDAO eDao;

	public EmployeeController() {
		eDao = new EmployeeDAO();
	}

	public void registerEmployee(Employee employee) {
		eDao.insertEmployee(employee);
	}

	public List<Employee> printAllEmployees() {
		List<Employee> eList = eDao.selectAllemployees();
		return eList;
	}

	public int removeEmployee(String empId) {
		int result = eDao.deleteEmployee(empId);
		return result;
	}

	public int modifyEmp(Employee employee) {
		int result = eDao.updateEmployee(employee);
		return result;
	}
}
