package day05.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import day05.member.model.vo.Member;

public class MemberDAO {

	public int insertMember(Connection conn, Member member) {
		String query = "INSERT INTO MEMBER_TBL VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?,SYSDATE)";
//		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = -1;

		try {
//			conn = jdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
			// pstmt.setString(4, member.getGender() + "");
			pstmt.setString(4, String.valueOf(member.getGender()));
			pstmt.setInt(5, member.getAge());
			pstmt.setString(6, member.getEmail());
			pstmt.setString(7, member.getPhone());
			pstmt.setString(8, member.getAddress());
			pstmt.setString(9, member.getHobby());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int deleteMember(Connection conn, String memberId) {
		String query = "DELETE INTO MEMBER_TBL WHERE MEMBER_ID(?, ?, ?, ?, ?, ?, ?, ?, ?,SYSDATE)";
//		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = -1;
		try {
//			conn = jdbcTemplate.getConnection();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			pstmt = conn.prepareStatement(query);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public Member loginInfo(Connection conn, Member mOne) {
		String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ? AND MEMBER_PWD = ?";
		Member member = null;
//		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
//			conn = jdbcTemplate.getConnection();
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
//			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}

	public List<Member> selectAllMember(Connection conn) {
		String query = "SELECT * FROM MEMBER_TBL";
		Statement stmt = null;
		ResultSet rset = null;
		List<Member> mList = null;
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			mList = new ArrayList<Member>();
			while (rset.next()) {
				Member member = this.rsetToMember(rset);
				mList.add(member);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return mList;
	}

	private Member rsetToMember(ResultSet rset) throws SQLException {
		Member member = new Member();
		member.setMemberId(rset.getString("MEMBER_ID"));
		member.setMemberPw(rset.getString("MEMBER_PWD"));
		member.setMemberName(rset.getString("MEMBER_NAME"));
		member.setGender(rset.getString("GENDER").charAt(0));
		member.setAge(rset.getInt("AGE"));
		member.setEmail(rset.getString("EMAIL"));
		member.setPhone(rset.getString("PHONE"));
		member.setAddress(rset.getString("ADDRESS"));
		member.setHobby(rset.getString("HOBBY"));
		member.setEnrollDate(rset.getDate("ENROLL_DATE"));
		return member;
	}

	public int updateMember(Connection conn, Member member) {
		int result = -1;
		String query = "UPDATE MEMBER_TBL SET MEMBER_PWD = ?" + ", EMAIL = ?"
				+ ", PHONE = ?, ADDRESS = ?, HOBBY = ? WHERE MEMBER_ID = ?";
		try {
//			Connection conn = jdbcTemplate.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberPw());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getAddress());
			pstmt.setString(5, member.getHobby());
			pstmt.setString(6, member.getMemberId()); // 실행하기 전에 위치홀더에 값 셋팅!!!!
			result = pstmt.executeUpdate(); // 실행할 땐 전달값 필요없음
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
