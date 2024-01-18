package day04.pstmt.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import day04.pstmt.member.common.JDBCTemplate;
import day04.pstmt.member.model.vo.Member;

public class MemberDAO {
	private JDBCTemplate jdbcTemplate;

	public MemberDAO() {
		jdbcTemplate = JDBCTemplate.getInstance();
	}
	// JDBC를 이용하여
	// Oracle DB에 접속하는 클래스
	// JDBC 코딩 절차

	/*
	 * 1. 드라이버 등록 2. DB 연결 생성 3. 쿼리문 실행 준비 4. 쿼리문 실행 및 5. 결과 받기 6. 자원해제(close())
	 */

	public void insertMember(Member member) {
		String query = "INSERT INTO MEMBER_TBL VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate)";
		try {
			Connection conn = jdbcTemplate.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
			pstmt.setInt(5, member.getAge());
			pstmt.setString(4, String.valueOf(member.getGender())); // 문자를 문자열로 변환!!!!
			pstmt.setString(6, member.getEmail());
			pstmt.setString(7, member.getPhone());
			pstmt.setString(8, member.getAddress());
			pstmt.setString(9, member.getHobby()); // 실행하기 전 위치홀더(?) 값 셋팅
			// ResultSet rset = stmt.executeQuery(query);
			int result = pstmt.executeUpdate(); // pstmt는 실행할 때 전달값이 필요 없음!!! (*****)
			if (result > 0) { // AutoCommit이 되기 때문에 안 해도 됨
				// insert 성공!
			} else {
				// insert 실패
			}
			conn.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// JDBC를 이용하여
	// Oracle DB에 접속하는 클래스
	// JDBC 코딩 절차

	/*
	 * 1. 드라이버 등록 2. DB 연결 생성 3. 쿼리문 실행 준비 4. 쿼리문 실행 및 5. 결과 받기 6. 자원해제(close())
	 */

	public void updateMember(Member member) {
		String query = "UPDATE MEMBER_TBL SET MEMBER_PWD = ?" + ", EMAIL = ?"
				+ ", PHONE = ?, ADDRESS = ?, HOBBY = ? WHERE MEMBER_ID = ?";
		try {
			Connection conn = jdbcTemplate.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
//			int result = pstmt.executeUpdate();
			pstmt.setString(1, member.getMemberPw());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getAddress());
			pstmt.setString(5, member.getHobby());
			pstmt.setString(6, member.getMemberId()); // 실행하기 전에 위치홀더에 값 셋팅!!!!
			int result = pstmt.executeUpdate(); // 실행할 땐 전달값 필요없음
			if (result > 0) {
				// success
			} else {
				// fail
			}
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteMember(String memberId) {
		String query = "DELETE FROM MEMBER_TBL WHERE MEMBER_ID = ?";
		try {

			Connection conn = jdbcTemplate.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			int result = pstmt.executeUpdate(); // DML의 경우 int로 받음
			if (result > 0) {
				// 성공 후 커밋
			} else {
				// 실패면 롤백
			}
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// JDBC를 이용하여
	// Oracle DB에 접속하는 클래스
	// JDBC 코딩 절차

	/*
	 * 1. 드라이버 등록 2. DB 연결 생성 3. 쿼리문 실행 준비 4. 쿼리문 실행 및 5. 결과 받기 6. 자원해제(close())
	 */

	// 클래스 바로 밑에 코드 못 씀
	// 메소드로 감싸줘야 함
	// 메소드 안에 코드를 씀
	// 내가 필요할 때 호출해서 씀

	public List<Member> selectAll() {

		String query = "SELECT * FROM MEMBER_TBL";
		List<Member> mList = null;
		Connection conn;
		try {
			conn = jdbcTemplate.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(query); // 초록색 재생버튼 누름
			// rset 전부 다 담겨있기 때문에 한 행씩 꺼내서 출력해줘야 함
			mList = new ArrayList<Member>(); // 누락 주의! 꼭 쓰세욤!
			while (rset.next()) {
				Member member = this.rsetToMember(rset);
				mList.add(member); // 누락주의! 하나의 행 데이터를 List에 반복적으로 저장
				// 후처리 : SELECT한 결과값 자바영역인 List에다가 옮기는 작업
			}
			rset.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mList;
	}

	public Member selectOneById(String memberId) {
		String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ?";
		Member member = null;
		try {
			Connection conn = jdbcTemplate.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			ResultSet rset = pstmt.executeQuery();

			if (rset.next()) { // 여러개면 while 한 개면 if!!
				member = this.rsetToMember(rset);
			}
			rset.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}

	// 클래스 바로 밑에 코드 못 씀
	// 메소드로 감싸줘야 함
	// 메소드 안에 코드를 씀
	// 내가 필요할 때 호출해서 씀

	public Member loginInfo(Member mOne) {
		String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ? AND MEMBER_PWD = ?";
		Member member = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			conn = jdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(query); // 쿼리문을 미리 컴파일 함.
			pstmt.setString(1, mOne.getMemberId()); // ?(위치홀더)에 값을 넣는 곳,
			pstmt.setString(2, mOne.getMemberPw()); // 시작은 1로 하고 마지막 수는 물음 rset = pstmt.executeQuery(); // 쿼리문이 안
													// 들어감!!!! (***시험유력***)
			// 쿼리문을 미리 컴파일하고 위치홀더 값을 셋팅하고 쿼리문 실행 및 결과 받기
			if (rset.next()) {
				member = this.rsetToMember(rset);
			}
			rset.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}

	private Member rsetToMember(ResultSet rset) throws SQLException {
		Member member = new Member();
		member.setMemberId(rset.getString("MEMBER_ID"));
		member.setMemberPw(rset.getString("MEMBER_PWD"));
		member.setMemberName(rset.getString("MEMBER_NAME"));
		member.setGender(rset.getString("GENDER").charAt(0)); // gender는 한글자라서 문자 처리!!
		member.setAge(rset.getInt("AGE"));
		member.setEmail(rset.getString("EMAIL"));
		member.setPhone(rset.getString("PHONE"));
		member.setAddress(rset.getString("ADDRESS"));
		member.setHobby(rset.getString("HOBBY"));
		member.setEnrollDate(rset.getDate("ENROLL_DATE")); // getDate 사용
		return member;
	}
}
