package login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class SearchFormController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		String mode = ServletRequestUtils.getStringParameter(req, "mode");
		ModelAndView mav = new ModelAndView();
		mav.addObject("mode",mode);
		mav.setViewName("/search");
		return mav;
	}

}
