package login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import member.dao.MemberDAO;
import member.dto.MemberDTO;

public class CheckController implements Controller {
	private MemberDAO memberDAO;
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		String id = ServletRequestUtils.getStringParameter(req,"id");
		String	ssn1 = ServletRequestUtils.getStringParameter(req,"ssn1");
		String ssn2 = ServletRequestUtils.getStringParameter(req,"ssn2");
		String name = ServletRequestUtils.getStringParameter(req,"name");
		String mode = ServletRequestUtils.getStringParameter(req, "mode");
		ModelAndView mav = new ModelAndView("forward:message.jsp");
		if(mode.equals("id")) {
			String res = memberDAO.getId(name,ssn1,ssn2);
			if(res==null) {
				mav.addObject("msg","입력하신정보가 맞지 않습니다. 다시 입력해주세요.");
				mav.addObject("url","search.do?mode=id");
			}
			else {
				mav.addObject("msg","회원님의 id 는 "+res+"입니다.");
				mav.addObject("url","close.do");
			}
		}
		else if (mode.equals("pw")) {
			String res = memberDAO.getPw(id,name,ssn1,ssn2);
			if(res == null) {
				mav.addObject("msg","입력하신정보가 맞지 않습니다. 다시 입력해주세요.");
				mav.addObject("url","search.do?mode=pw");
			}
			else {
				mav.addObject("msg","회원님의 비밀번호 는 "+res+"입니다.");
				mav.addObject("url","close.do");
			}
		}
		return mav;
	}

}
