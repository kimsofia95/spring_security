package app.web.controller;

import app.model.User;
import app.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
	private final UserDetailsService userDetailsService;
	private final AdminService adminService;

	@Autowired
	public UserController(UserDetailsService userDetailsService, AdminService adminService) {
		this.userDetailsService = userDetailsService;
		this.adminService = adminService;
	}

	@Transactional
	@GetMapping(value = "user")
	public String UserPageId(ModelMap modelMap, @AuthenticationPrincipal User user) {
		modelMap.addAttribute("user", user);
		return "userPage";
	}

}