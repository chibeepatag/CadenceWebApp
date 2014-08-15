/**
 * 
 */
package au.edu.cmu.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import au.edu.cmu.model.User;
import au.edu.cmu.model.Rider;
import au.edu.cmu.service.LoginService;

/**
 * @author ChibeePatag
 * 
 */
@Controller
public class LoginController {

	@Autowired
	LoginService loginService;

	@ModelAttribute("coach")
	public User getCoach() {
		return new User();
	}
	
    @RequestMapping("/")
    public String root(Locale locale) {
        return "redirect:/index";
    }
    
    @RequestMapping("/index")
    public String index(Locale locale) {
        return "index";
    }

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping("/login-error.html")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model) {
		return "logout";
	}

	@RequestMapping("/error.html")
	public String error(HttpServletRequest request, Model model) {
		model.addAttribute("errorCode",
				request.getAttribute("javax.servlet.error.status_code"));
		Throwable throwable = (Throwable) request
				.getAttribute("javax.servlet.error.exception");
		String errorMessage = null;
		if (throwable != null) {
			errorMessage = throwable.getMessage();
		}
		model.addAttribute("errorMessage", errorMessage.toString());
		return "error.html";
	}

}
