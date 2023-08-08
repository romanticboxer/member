package member.mybatis;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.web.bind.annotation.RequestParam;

import member.dto.MemberDTO;

public class MemberMapper {
	private static SqlSessionFactory sqlSessionFactory;
	static {
		try {
			String resource = "Configuration.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			throw new RuntimeException("DB연동오류 발생!"+e.toString());
		}
	}
	public static List<MemberDTO> listMember(){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			List<MemberDTO> list = sqlSession.selectList("listMember");
			return list;
		}finally {
			sqlSession.close();
		}
	}
	public static int insertMember(MemberDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int res = sqlSession.insert("insertMember",dto);
			sqlSession.commit();
			return res;
		}finally {
			sqlSession.close();
		}
	}
	public static int deleteMember(int no) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int res =  sqlSession.delete("deleteMember",no);
			sqlSession.commit();
			return res;
		}finally {
			sqlSession.close();
		}
	}
	public static int updateMember(MemberDTO dto) {
		SqlSession sqlSession  = sqlSessionFactory.openSession();
		try {
			int res = sqlSession.update("updateMember",dto);
			sqlSession.commit();
			return res;
		}finally {
			sqlSession.close();
		}
	}
	public static List<MemberDTO> findMember(@RequestParam(required = false) Map<String,String> param){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			List<MemberDTO> list = sqlSession.selectList("findMember", param);
			return list;
		}finally {
			sqlSession.close();
		}
	}
	public static MemberDTO getMember(int no) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectOne("getMember",no);
		}finally {
			sqlSession.close();
		}
	}
	public static int checkLogin(String id,String passwd) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			MemberDTO dto = sqlSession.selectOne("checkLogin",id);
			if(dto == null) {
				return -1;
			}
			if(dto.getPasswd().equals(passwd)) {
				return 1;
			}
			else {
				return -2;
			}
		}finally {
			sqlSession.close();
		}
	}
	public static int checkMember(@RequestParam(required = false)Map<String,String> param) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			String res = sqlSession.selectOne("checkMember",param);
			if(res ==null) {
				return 0;
			}
			return 1;
		}finally {
			sqlSession.close();
		}
	}
	public static String getId(@RequestParam (required = false) Map<String,String> param) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			String id = sqlSession.selectOne("getId",param);
			return id;
		}finally {
			sqlSession.close();
		}
	}
	public static String getPw(@RequestParam (required = false) Map<String,String> param) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			String pw = sqlSession.selectOne("getPw",param);
			return pw;
		}
		finally {
			sqlSession.close();
		}
	}
}
