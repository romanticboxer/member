package member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import member.dao.MemberDAO;
import member.dto.MemberDTO;

public class JoinMemberController extends AbstractCommandController {
	private MemberDAO memberDAO;
	@Override
	protected ModelAndView handle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, BindException arg3)
			throws Exception {
		// TODO Auto-generated method stub
		MemberDTO dto = (MemberDTO) arg2;
		HttpSession session = arg0.getSession();
		int res = memberDAO.insertMember(dto);
		ModelAndView mav = new ModelAndView("forward:message.jsp");
		if(res>0){
			mav.addObject("msg","회원가입 성공 ! 최초페이지 이동");
			mav.addObject("url","index_member.do");
			session.invalidate();
		}
		else {
			mav.addObject("msg","회원가입 실패 ! 최초페이지 이동");
			mav.addObject("url","index_member.do");
			session.invalidate();
		}
		return mav;
	}
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

}
