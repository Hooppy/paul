package com.paul.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paul.dao.PaulDao;
import com.paul.model.PaulModel;

@Controller
public class PaulController {
	
	@Autowired
	PaulDao paulDao;
	
	@Autowired
	PaulModel paulModel;
	
	@RequestMapping("index")
	public String index(ModelMap model) {
		
		model.addAttribute("index", "index");
		
		return "index";
	}
	
	@RequestMapping("new")
	public String newMember(ModelMap model) {
		
		model.addAttribute("new", "new");
		
		return "join";
	}
	
	@RequestMapping("join")
	public String Join(ModelMap model,
		@RequestParam(value = "username") String username,
		@RequestParam(value = "password") String password) {
		
		paulModel.setUsername(username);
		paulModel.setPassword(password);
		
		try {
			paulDao.Join(paulModel);
		} catch (Exception e) {
			model.addAttribute("fail", "회원가입 실패");
			e.printStackTrace();
		}
		
		model.addAttribute("join", "join");
		
		return "index";
	}
	
	@RequestMapping("login")
	public String login(ModelMap model,
			HttpSession session) {
			
		Object failureCode = session.getAttribute("failureCode");
		
		model.addAttribute("failureCode", failureCode);
		
		return "index";
	}
	
	@RequestMapping("login_success")
	public String login_success(ModelMap model,
			HttpSession session) {
			
		Object failureCode = session.getAttribute("failureCode");
		
		model.addAttribute("failureCode", failureCode);
		
		return "login_success";
	}
}
