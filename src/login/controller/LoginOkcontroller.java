package login.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import member.dao.MemberDAO;

public class LoginOkcontroller implements Controller{
	private MemberDAO memberDAO;
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		String id = ServletRequestUtils.getStringParameter(req,"id");
		String passwd = ServletRequestUtils.getStringParameter(req,"passwd");
		String saveId = ServletRequestUtils.getStringParameter(req, "saveId");
		ModelAndView mav = new ModelAndView("forward:message.jsp");
		HttpSession session = req.getSession();
		int res = memberDAO.checkLogin(id, passwd);
		if (res >0) {
			Cookie ck = new Cookie("saveId",id);
			if(saveId != null) {
				ck.setMaxAge(24*60*60);
			}
			else {
				ck.setMaxAge(0);
			}
			session.setAttribute("mbId", id);
			mav.addObject("msg",id+"님 로그인되었습니다.");
			mav.addObject("url","index_member.do");
		}
		else if (res == -1) {
			mav.addObject("msg","id가 맞지않습니다.");
			mav.addObject("url","index_member.do");
		}
		else if (res == -2) {
			mav.addObject("msg","비밀번호가 맞지않습니다.");
			mav.addObject("url","index_member.do");
		}
		return mav;
	}

	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

}
