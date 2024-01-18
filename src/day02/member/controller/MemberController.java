package day02.member.controller;

import java.util.List;

import day02.member.model.dao.MemberDAO;
import day02.member.model.vo.Member;

public class MemberController {
//	List<Member> memberList;
	MemberDAO mDao;

	public MemberController() {
		mDao = new MemberDAO();
	}

//	mDao.selecAll();	// 이렇게 하면 안 됨!!
	public List<Member> printAll() {
		List<Member> mList = mDao.selectAll();
		return mList;
	}

	public Member printOneById(String memberId) {
		Member member = mDao.selectOneById(memberId);
		return member;
	}

	public void registerMember(Member member) {
		mDao.insertMember(member);
	}

	public void removeMember(String memberId) {
		mDao.deleteMember(memberId);
	}

	public void modifyMember(Member member) {
		mDao.updateMember(member);
	}

	public Member memberLogin(Member member) {
		// TODO Auto-generated method stub
		return null;
	}
}
