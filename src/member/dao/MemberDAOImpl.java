package member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import member.dto.MemberDTO;

public class MemberDAOImpl implements MemberDAO{
	private JdbcTemplate jdbcTemplate;
	class MyRowMapper2 implements RowMapper<String>{

		@Override
		public String mapRow(ResultSet rs, int co) throws SQLException {
			// TODO Auto-generated method stub
			String result = rs.getString(1);
			return result;
		}
	}
	class MyRowMapper implements RowMapper<MemberDTO>{

		@Override
		public MemberDTO mapRow(ResultSet rs, int co) throws SQLException {
			// TODO Auto-generated method stub
			MemberDTO dto = new MemberDTO();
			dto.setNo(rs.getInt("no"));
			dto.setName(rs.getString("name"));
			dto.setId(rs.getString("id"));
			dto.setPasswd(rs.getString("passwd"));
			dto.setSsn1(rs.getString("ssn1"));
			dto.setSsn2(rs.getString("ssn2"));
			dto.setEmail(rs.getString("email"));
			dto.setHp1(rs.getString("hp1"));
			dto.setHp2(rs.getString("hp2"));
			dto.setHp3(rs.getString("hp3"));
			dto.setJoindate(rs.getString("joindate"));
			return dto;
		}
	}
	private MyRowMapper mapper = new MyRowMapper();
	private MyRowMapper2 mapper2 = new MyRowMapper2();
	@Override
	public List<MemberDTO> listMember() {
		// TODO Auto-generated method stub
		String sql = "select * from jsp_member";
		List<MemberDTO> list = jdbcTemplate.query(sql, mapper);
		return list;
	}

	@Override
	public int insertMember(MemberDTO dto) {
		// TODO Auto-generated method stub
		String sql = "insert into jsp_member values(member_seq.nextval,?,?,?,?,?,?,?,?,?,sysdate)";
		Object[] values = new Object[] {
				dto.getName(),dto.getId(),dto.getPasswd(),dto.getSsn1(),dto.getSsn2(),dto.getEmail(),dto.getHp1(),dto.getHp2(),dto.getHp3()
		};
		int res = jdbcTemplate.update(sql,values);
		return res;
	}

	@Override
	public int deleteMember(int num) {
		// TODO Auto-generated method stub
		String sql = "delete from jsp_member where no = ?";
		int res = jdbcTemplate.update(sql,num);
		return res;
	}

	@Override
	public int updateMember(MemberDTO dto) {
		// TODO Auto-generated method stub
		String sql = "update jsp_member set id = ?, passwd = ?,email = ?, hp1 = ?, hp2 = ?, hp3 = ? where no = ? ";
		Object[] values =new  Object[] {
				dto.getId(),dto.getPasswd(),dto.getEmail(),dto.getHp1(),dto.getHp2(),dto.getHp3(),dto.getNo()
		};
		int res = jdbcTemplate.update(sql,values);
		return res;
	}

	@Override
	public List<MemberDTO> findMember(String search,String searchString) {
		// TODO Auto-generated method stub
		String sql = "select * from jsp_member where "+search+" = ?";
		List<MemberDTO> list = jdbcTemplate.query(sql, mapper,searchString);
		return list;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int checkMember(String ssn1, String ssn2) {
		// TODO Auto-generated method stub
		String sql = "select name from jsp_member where ssn1 =? and ssn2 =?";
		int res = jdbcTemplate.update(sql,ssn1,ssn2);
		return res;
	}

	@Override
	public MemberDTO getMember(int no) {
		// TODO Auto-generated method stub
		String sql = "select * from jsp_member where no = ?";
		List<MemberDTO> list = jdbcTemplate.query(sql,mapper, no);
		return list.get(0);
	}

	@Override
	public int checkLogin(String id,String passwd) {
		// TODO Auto-generated method stub
		String sql = "select * from jsp_member where id = ?";
		try {
			MemberDTO dto = jdbcTemplate.queryForObject(sql,mapper,id);
			if(dto.getPasswd().equals(passwd)) {
				return 1;
			}
			else {
				return -2;
			}
		}
		catch(EmptyResultDataAccessException e) {
			return -1;
		}
	}

	@Override
	public String getId(String name, String ssn1, String ssn2) {
		// TODO Auto-generated method stub
		String sql = "select id from jsp_member where name = ? and ssn1 =? and ssn2 = ?";
		try {
			return jdbcTemplate.queryForObject(sql, mapper2,name,ssn1,ssn2);
		}catch(EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public String getPw(String id,String name,String ssn1, String ssn2) {
		// TODO Auto-generated method stub
		String sql = "select passwd from jsp_member where id = ? and name = ? and ssn1 = ? and ssn2 = ?";
		try {
			return jdbcTemplate.queryForObject(sql,mapper2,id,name,ssn1,ssn2);
		}catch(EmptyResultDataAccessException e) {
			return null;
		}
	}

}
