package member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import member.dao.MemberDAO;
import member.dto.MemberDTO;

public class MemberEditController extends AbstractCommandController {
	private MemberDAO memberDAO;
	@Override
	protected ModelAndView handle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, BindException arg3)
			throws Exception {
		// TODO Auto-generated method stub
		MemberDTO dto = (MemberDTO)arg2;
		ModelAndView mav = new ModelAndView("forward:message.jsp");
		int res = memberDAO.updateMember(dto);
		if (res>0) {
			mav.addObject("msg","회원수정 성공 ! 목록으로 이동합니다.");
			mav.addObject("url","memberAll.do?mode=All");
		}else {
			mav.addObject("msg","회원수정 실패, 최초페이지로 이동합니다.");
			mav.addObject("url","index_member.do");
		}
		return mav;
	}
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

}
