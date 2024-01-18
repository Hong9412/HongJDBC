package day03.employee.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import day03.employee.model.vo.Employee;

public class EmployeeDAO {
	final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	final String USERNAME = "STUDENT";
	final String PASSWORD = "STUDENT";
	// 1. 드라이버 등럭
	// 2. DBMS 연결 생성
	// 3. Statement 생성
	// 4. 쿼리문 실행 및
	// 5. 결과받기
	// 6. 자원해제

	public void insertEmployee(Employee employee) {
		String query = "INSERT INTO EMPLOYEE VALUES('" + employee.getEmpId() + "', '" + employee.getEmpName() + "', '"
				+ employee.getEmpNo() + "', '" + employee.getEmail() + "'," + " '" + employee.getPhone() + "', '"
				+ employee.getDeptCode() + "', '" + employee.getJobCode() + "', '" + employee.getSalLevel() + "', "
				+ employee.getSalary() + ", " + employee.getBonus() + ", '" + employee.getManagerId()
				+ "',SYSDATE,null,'N')";
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = conn.createStatement();
			int result = stmt.executeUpdate(query); // 인서트에는 인트랑 업데이트(쿼리x!) 시험포인트
			if (result > 0) {
				// 성공 - commit
			} else {
				// 실패 - rollBack
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Employee> selectAllemployees() {
		String query = "SELECT * FROM EMPLOYEE";
		List<Employee> eList = null;
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(query); // 셀렉트일 때 리절트셋이랑 쿼리문!
			eList = new ArrayList<Employee>();
			// 후처리
			while (rset.next()) {
				Employee employee = new Employee();
				employee.setEmpId(rset.getString("EMP_ID"));
				employee.setEmpName(rset.getString("EMP_NAME"));
				employee.setEmpNo(rset.getString("EMP_NO"));
				employee.setEmail(rset.getString("EMAIL"));
				employee.setPhone(rset.getString("PHONE"));
				employee.setDeptCode(rset.getString("DEPT_CODE"));
				employee.setJobCode(rset.getString("JOB_CODE"));
				employee.setSalLevel(rset.getString("SAL_LEVEL"));
				employee.setSalary(rset.getInt("SALARY"));
				employee.setBonus(rset.getDouble("BONUS"));
				employee.setManagerId(rset.getString("MANAGER_ID"));
				employee.setHireDate(rset.getDate("HIRE_DATE"));
				employee.setEntDate(rset.getDate("ENT_DATE"));
				employee.setEntYn(rset.getString("ENT_YN"));
				eList.add(employee);
//				System.out.println("직원명 : " + rset.getString("EMP_NAME"));
			}
			rset.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return eList;
	}

	public int deleteEmployee(String empId) {
		String query = "DELETE FROM EMPLOYEE WHERE EMP_ID = '" + empId + "'";
		int result = -1;
		try {
			Connection conn = this.getConnection();
			Statement stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		Class.forName(DRIVER_NAME);
		conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		return conn;
	}

	public int updateEmployee(Employee employee) {
		String query = "UPDATE EMPLOYEE SET EMAIL = '" + employee.getEmail() + "', PHONE = '" + employee.getPhone()
				+ "' WHERE EMP_ID = '" + employee.getEmpId() + "'";
		int result = -1;
		Connection conn;
		try {
			conn = this.getConnection();
			Statement stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result; // 누락주위!!!!!
	}
}
