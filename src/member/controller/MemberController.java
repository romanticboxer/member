package member.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import member.dao.MemberDAO;
import member.dto.MemberDTO;
import member.mybatis.MemberMapper;

@Controller
public class MemberController {
	@Autowired
	private MemberDAO memberDAO;

	@RequestMapping("/index_member.do")
	public String index_member() {
		return "/index_member";
	}

	@RequestMapping("/memberSsn.do")
	public String memberSSn() {
		return "/memberSsn";
	}

	@RequestMapping("/checkMember.do")
	public ModelAndView checkMember(@RequestParam Map<String, String> param, HttpServletRequest req) {
//		int res = memberDAO.checkMember(param.get("ssn1"), param.get("ssn2"));
		int res = MemberMapper.checkMember(param);
		ModelAndView mav = new ModelAndView("forward:message.jsp");
		if (res > 0) {
			mav.addObject("msg", "이미 존재하는 회원입니다 로그인을 해주세요 !");
			mav.addObject("url", "index_member.do");
		} else {
			mav.addObject("msg", "없는 회원입니다. 회원가입창으로 이동합니다.");
			mav.addObject("url", "member.do");
			HttpSession session = req.getSession();
			session.setAttribute("name", param.get("name"));
			session.setAttribute("ssn1", param.get("ssn1"));
			session.setAttribute("ssn2", param.get("ssn2"));
		}
		return mav;
	}

	@RequestMapping("memberAll.do")
	public String list_member(HttpServletRequest req, @RequestParam(required = false) Map<String, String> param) {
		if (param.get("mode").equals("All")) {
			List<MemberDTO> list = MemberMapper.listMember();
			req.setAttribute("list", list);
			req.setAttribute("mode", param.get("mode"));
		} 
		else if (param.get("search") == null) {
			List<MemberDTO> list = null;
			req.setAttribute("list", list);
			req.setAttribute("mode", param.get("mode"));
		}
		else {
//			List<MemberDTO> list = memberDAO.findMember(param.get("search"), param.get("searchString"));
			List<MemberDTO> list = MemberMapper.findMember(param);
			req.setAttribute("list", list);
			req.setAttribute("mode", param.get("mode"));
		}
		return "/memberAll";
	}

	@RequestMapping("member.do")
	public String member_joinForm() {
		return "/member";
	}

	@RequestMapping("join_member.do")
	public String join_member(HttpServletRequest req, @ModelAttribute MemberDTO dto, BindingResult result) {
		if (result.hasErrors()) {
			dto.setNo(0);
		}
//		int res = memberDAO.insertMember(dto);
		int res = MemberMapper.insertMember(dto);
		if (res > 0) {
			req.setAttribute("msg", "회원가입 성공! 최초 페이지로 이동합니다.");
			req.setAttribute("url", "index_member.do");
		} else {
			req.setAttribute("msg", "회원가입 실패! 최초 페이지로 이동합니다.");
			req.setAttribute("url", "index_member.do");
		}
		return "forward:message.jsp";
	}

	@RequestMapping(value = "/member_edit.do", method = RequestMethod.GET)
	public String update_form(HttpServletRequest req, int no) {
//		MemberDTO dto = memberDAO.getMember(no);
		MemberDTO dto = MemberMapper.getMember(no);
		req.setAttribute("dto", dto);
		return "/edit_member";
	}

	@RequestMapping(value = "/member_edit.do", method = RequestMethod.POST)
	public String update(HttpServletRequest req, @ModelAttribute MemberDTO dto) {
		int res = MemberMapper.updateMember(dto);
		if (res > 0) {
			req.setAttribute("msg", "회원수정 성공,목록으로 이동합니다.");
			req.setAttribute("url", "memberAll.do?mode=All");
		} else {
			req.setAttribute("msg", "회원수정실패");
			req.setAttribute("url", "memberAll.do?mode=All");
		}
		return "forward:message.jsp";
	}

	@RequestMapping(value = "/member_delete.do", method = RequestMethod.GET)
	public ModelAndView delete(int no) {
		int res = MemberMapper.deleteMember(no);
		ModelAndView mav = new ModelAndView("forward:message.jsp");
		if (res > 0) {
			mav.addObject("msg", "삭제성공 ! 목록으로 이동합니다.");
			mav.addObject("url", "memberAll.do?mode=All");
		} else {
			mav.addObject("msg", "삭제실패! 목록으로 이동합니다.");
			mav.addObject("url", "memberAll.do?mode=All");
		}
		return mav;
	}

	@RequestMapping(value = "login.do", method = RequestMethod.GET)
	public String login() {
		return "/login";
	}

	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	public ModelAndView loginOk(HttpServletRequest req, @RequestParam Map<String, String> param) {
		ModelAndView mav = new ModelAndView("forward:message.jsp");
		HttpSession session = req.getSession();
		int res = MemberMapper.checkLogin(param.get("id"), param.get("passwd"));
		Cookie ck = new Cookie("saveId", param.get("id"));
		if (param.get("saveId") != null) {
			ck.setMaxAge(86400);
		} else {
			ck.setMaxAge(0);
		}
		if (res > 0) {
			session.setAttribute("mbId", param.get("id"));
			mav.addObject("msg", param.get("id") + "님 로그인 되었습니다.");
			mav.addObject("url", "index_member.do");
		} else if (res == -1) {
			mav.addObject("msg", "id가 맞지않습니다");
			mav.addObject("url", "login.do");
		} else if (res == -2) {
			mav.addObject("msg", "비밀번호가 맞지않습니다.");
			mav.addObject("url", "login.do");
		}
		return mav;
	}

	@RequestMapping("search.do")
	public String search(HttpServletRequest req, String mode) {
		req.setAttribute("mode", mode);
		return "/search";
	}

	@RequestMapping("check.do")
	public ModelAndView check(@RequestParam Map<String, String> param) {
		ModelAndView mav = new ModelAndView("forward:message.jsp");
		if (param.get("mode").equals("id")) {
//			String id = memberDAO.getId(param.get("name"), param.get("ssn1"), param.get("ssn2"));
			String id = MemberMapper.getId(param);
			mav.addObject("msg", "회원님의 id 는 " + id + "입니다.");
			mav.addObject("url", "close.do");
		} else {
//			String pw = memberDAO.getPw(param.get("id"), param.get("name"), param.get("ssn1"), param.get("ssn2"));
			String pw = MemberMapper.getPw(param);
			mav.addObject("msg", "회원님의 비밀번호는 " + pw + "입니다.");
			mav.addObject("url", "close.do");
		}
		return mav;
	}

	@RequestMapping("close.do")
	public String close() {
		return "/closeWindow";
	}

	@RequestMapping("logout.do")
	public String logout() {
		return "/logout";
	}
}
