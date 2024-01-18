package day04.pstmt.employee.common;

import java.sql.Connection;
import java.sql.DriverManager;

class Singletone {
	private static Singletone instance;

	private Singletone() {
	}

	public static Singletone getInstance() {
		if (instance == null) {
			instance = new Singletone();
		}
		return instance;
	}
}

public class JDBCTemplate {
	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USERNAME = "STUDENT";
	private final String PASSWORD = "STUDENT";

	private static JDBCTemplate instance;
	private static Connection conn;

	private JDBCTemplate() {
	}

	public static JDBCTemplate getInstance() {
		if (instance == null) {
			instance = new JDBCTemplate();
		}
		return instance;
	}

	public Connection getConnection() throws Exception {
		if (conn == null || conn.isClosed()) {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}
		return conn;
	}
}
