package day04.pstmt.employee.view;

import java.util.List;
import java.util.Scanner;

import day04.pstmt.employee.controller.EmployeeController;
import day04.pstmt.employee.model.vo.Employee;

public class EmployeeView {
	private EmployeeController eController;

	public EmployeeView() {
		eController = new EmployeeController();
	}

	public void statProgram() {
		Employee employee = null;
		int result = -1;
		end: while (true) {
			int choice = this.printMainMenu();
			switch (choice) {
			case 1: // 전체 조회
				List<Employee> eList = eController.printAllEmployees();
				this.displayAllEmployees(eList);
				break;
			case 2: // 1명 조회
				break;
			case 3: // 등록
				employee = this.inputEmployee();
				eController.registerEmployee(employee);
				System.out.println("등록 성공");
				break;
			case 4: // 삭제
				String empId = this.inputEmpId();
				result = eController.removeEmployee(empId);
				if (result > 0) {
					this.displayMessaget("삭제 성공");
				} else {
					this.displayMessaget("삭제 실패");
				}
				break;
			case 5: // 수정
				employee = this.modifyEmployee();
				result = eController.modifyEmp(employee);
				if (result > 0) {
					this.displayMessaget("수정 성공");
				} else {
					this.displayMessaget("수정 실패");
				}
				break;
			case -1:
				break end;
			}
		}

	}

	private Employee modifyEmployee() {
		Scanner sc = new Scanner(System.in);
		System.out.println("=== === 사원 정보 수정 === ===");
		System.out.print("사번 : ");
		String empId = sc.next();
		System.out.print("이메일 : ");
		String email = sc.next();
		System.out.print("폰번호 : ");
		String phone = sc.next();
		Employee employee = new Employee(empId, email, phone);
		return employee;
	}

	private String inputEmpId() {
		Scanner sc = new Scanner(System.in);
		System.out.println("사원번호를 입력해주세요 : ");
		return sc.next();
	}

	private void displayMessaget(String msg) {
		System.out.println(msg);
	}

	private int printMainMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("=== === 직원 관리 프로그램 === ===");
		System.out.println("1. 직원 전체 조회");
		System.out.println("2. 직원 아이디로 조회");
		System.out.println("3. 직원 정보 등록");
		System.out.println("4. 직원 정보 삭제");
		System.out.println("5. 직원 정보 수정");
		System.out.println("-1. 프로그램 종료");
		System.out.print("메뉴 선택 : ");
		int input = sc.nextInt();
		return input;
	}

	private Employee inputEmployee() {
		// View에서 입력받고 Controller 넘겨 준 후 DAO에서 저장하도록 하기 위한 Start!!
		// -> 입력받은 값을로 저장하기 위함.
		Scanner sc = new Scanner(System.in);
		System.out.println("======== 사원 정보 입력 ========");
		System.out.print("사번 : ");
		String empId = sc.next();
		System.out.print("사원명 : ");
		String empName = sc.next();
		System.out.print("주민번호 : ");
		String empNo = sc.next();
		System.out.print("이메일 : ");
		String email = sc.next();
		System.out.print("전화번호 : ");
		String phone = sc.next();
		System.out.print("부서코드 : ");
		String deptCode = sc.next();
		System.out.print("직급코드 : ");
		String jobCode = sc.next();
		System.out.println("급여등급 : ");
		String salLevel = sc.next();
		System.out.print("급여 : ");
		int salay = sc.nextInt();
		System.out.print("보너스 : ");
		double bonus = sc.nextDouble();
		System.out.print("매니저 사번 : ");
		String managerId = sc.next();
//		Employee employee = new Employee();	 // 텅빈 객체
		Employee employee = new Employee(empId, empName, empNo, email, phone, deptCode, jobCode, salLevel, salay, bonus,
				managerId); // 채워진 객체
		return employee;
	}

	public void printOneById(Employee emp) {
		System.out.println("=== === 회원 정보 출력(아이디로 검색) === ===");
		System.out.println("직원명 : " + emp.getEmpName() + ", 사번 : " + emp.getEmpId() + ", 이메일 : " + emp.getEmail()
				+ ", 전화번호 : " + emp.getPhone() + ", 입사일 : " + emp.getHireDate());
	}

	private void displayAllEmployees(List<Employee> eList) {
		// TODO Auto-generated method stub
		System.out.println(
				"======================================== 사원 정보 전체 출력 ========================================");
		for (Employee emp : eList) {
			System.out.println("직원명 : " + emp.getEmpName() + ", 사번 : " + emp.getEmpId() + ", 이메일 : " + emp.getEmail()
					+ ", 전화번호 : " + emp.getPhone() + ", 입사일 : " + emp.getHireDate());
		}
	}
}
