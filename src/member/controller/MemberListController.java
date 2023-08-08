package member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import member.dao.MemberDAO;
import member.dto.MemberDTO;

public class MemberListController implements Controller {
	private MemberDAO memberDAO;

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		String mode = req.getParameter("mode");
		ModelAndView mav = new ModelAndView();
		if (mode.equals("All")) {
			List<MemberDTO> list = memberDAO.listMember();
			mav.addObject("list", list);
			mav.addObject("mode", mode);
			mav.setViewName("/memberAll");
		}
		else {
			String search = req.getParameter("search");
			String name = req.getParameter("searchString");
			List<MemberDTO> list  = memberDAO.findMember(search,name);
			mav.addObject("list",list);
			mav.addObject("mode", mode);
			mav.setViewName("/memberAll");
		}
		return mav;
	}

	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
}
