package member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import member.dao.MemberDAO;

public class MemberDeleteController implements Controller {
	private MemberDAO memberDAO;
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		int num = ServletRequestUtils.getIntParameter(arg0, "no"); 
		ModelAndView mav = new ModelAndView("forward:message.jsp");
		int res = memberDAO.deleteMember(num);
		if (res>0) {
			mav.addObject("msg", "회원삭제 성공, 목록으로 이동합니다.");
			mav.addObject("url","memberAll.do?mode=All");
		}
		else {
			mav.addObject("msg", "회원삭제 실패, 목록으로 이동합니다.");
			mav.addObject("url","memberAll.do?mode=All");
		}
		return mav;
	}

	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

}
