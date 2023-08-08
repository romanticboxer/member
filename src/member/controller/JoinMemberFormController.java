package member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JoinMemberFormController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		String name = (String) session.getAttribute("name");
		String ssn1 = (String) session.getAttribute("ssn1");
		String ssn2 = (String) session.getAttribute("ssn2");
		ModelAndView mav = new ModelAndView();
		mav.addObject("name",name);
		mav.addObject("ssn1",ssn1);
		mav.addObject("ssn2",ssn2);
		mav.setViewName("/member");
		return mav;
	}
}
