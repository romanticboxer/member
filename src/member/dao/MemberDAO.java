package member.dao;

import java.util.List;

import member.dto.MemberDTO;

public interface MemberDAO {
	public List<MemberDTO> listMember();
	public int insertMember(MemberDTO dto);
	public int deleteMember(int num);
	public int updateMember(MemberDTO dto);
	public List<MemberDTO> findMember(String search,String searchString);
	public int checkMember(String ssn1,String ssn2);
	public MemberDTO getMember(int no);
	public int checkLogin(String id,String passwd);
	public String getId(String name,String ssn1,String ssn2);
	public String getPw(String id,String name,String ssn1,String ssn2);
}
