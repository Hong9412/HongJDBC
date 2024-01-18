package day03.member.model.vo;

import java.sql.Date;

public class Member {
	private String memberId;
	private String memberPw;
	private String memberName;
	private char gender;
	private int age;
	private String address;
	private String email;
	private String phone;
	private String hobby;
	private Date enrollDate;

	public Member() {
	}

	public Member(String memberId, String memberPw) {
		super();
		this.memberId = memberId;
		this.memberPw = memberPw;
	}

	public Member(String memberPw, String address, String email, String phone, String hobby) {
		super();
		this.memberPw = memberPw;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.hobby = hobby;
	}

	public Member(String memberId, String memberPw, String memberName, char gender, int age, String address,
			String email, String phone, String hobby) {
		super();
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.gender = gender;
		this.age = age;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.hobby = hobby;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Member [아이디=" + memberId + ", memberPw=" + memberPw + ", memberName=" + memberName + ", gender="
				+ gender + ", age=" + age + ", email=" + email + ", phone=" + phone + ", hobby=" + hobby
				+ ", enrollDate=" + enrollDate + "]";
	}

}
