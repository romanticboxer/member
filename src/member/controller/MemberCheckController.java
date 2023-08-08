package member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import member.dao.MemberDAO;

public class MemberCheckController implements Controller{
	private MemberDAO memberDAO;
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		String name = ServletRequestUtils.getStringParameter(req,"name");
		String ssn1 = ServletRequestUtils.getStringParameter(req,"ssn1");
		String ssn2 = ServletRequestUtils.getStringParameter(req,"ssn2");
		ModelAndView mav = new ModelAndView("forward:message.jsp");
		HttpSession session = req.getSession();
		int res = memberDAO.checkMember(ssn1, ssn2);
		if(res>0) {
			mav.addObject("msg","이미 존재하는 회원입니다 로그인을 해주세요 !");
			mav.addObject("url","index_member.do");
		}
		else {
			mav.addObject("msg","없는 회원입니다. 회원가입창으로 이동합니다.");
			mav.addObject("url","member.do");
			session.setAttribute("name",name);
			session.setAttribute("ssn1",ssn1);
			session.setAttribute("ssn2",ssn2);
		}
		return mav;
	}
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

}
