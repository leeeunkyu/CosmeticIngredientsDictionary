package com.work.cosmetic;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.work.dto.Cosmetic;
import com.work.dto.User;
import com.work.service.CosmeticService;
import com.work.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class UserController {
	
	private UserService service;
	@Autowired
	public void setUserService(UserService service) {
		this.service = service;
	}
	
	private String token;
	private User user;
	@Autowired
	CosmeticService cosmeticService;
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping("loginView.do")
	public String loginView() {
		return "user/login";
	}
	
	@RequestMapping("joinView.do")
	public String joinView() {
		return "user/join";
	}
	
	
	@RequestMapping("login.do")
	public ModelAndView login(HttpSession session, String userId,String userPw) {
		ModelAndView mv = new ModelAndView();
		String userName = service.login(userId,userPw);
		if(userName!=null) {
			ArrayList<Cosmetic> cosmeticRankList = cosmeticService.cosmeticSelectRank();
			session.setAttribute("userId", userId);
			 session.setAttribute("userName", userName);
	         mv.addObject("userId", userId);
	 		mv.addObject("cosmeticRankList", cosmeticRankList);
	         mv.addObject("successMessage", "사용자 인증 완료");
	         mv.setViewName("home");
			
		}else {
			 mv.addObject("errorMessage", "회원정보를 다시 확인하시기 바랍니다.");
	         mv.setViewName("user/login");
		}
		return mv;
	}
	
	@RequestMapping("logout.do")
	public ModelAndView logout(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if(session.getAttribute("userId") != null) {
			ArrayList<Cosmetic> cosmeticRankList = cosmeticService.cosmeticSelectRank();
	 		mv.addObject("cosmeticRankList", cosmeticRankList);
	         mv.setViewName("home");
			session.removeAttribute("userId");
			session.setMaxInactiveInterval(0);
			session.invalidate();
		}
		return mv;
	}
	
	@RequestMapping("join.do")
	public String join(User user,HttpSession session,HttpServletRequest request) {
		String ip = null;
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		String tempUserId = user.getUserId()+"@"+request.getParameter("email2");
		user.setUserId(tempUserId);
		this.user = user;
		//session.setAttribute("user", user);
		String contextPath = request.getContextPath();
		int port = request.getServerPort();
		sendEmail(tempUserId,contextPath,ip,port);
		return "user/permissionWait";
	}
	
	private void sendEmail(String email,String contextPath,String ip, int port) {
		  String host     = "smtp.naver.com";
		  final String adminUser   = "";
		  final String password  = "!";
		  String to     = email;
		  System.out.println(email);
		  Properties props = new Properties();
		  props.put("mail.smtp.host", host);
		  props.put("mail.smtp.auth", "true");
		  Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		   protected PasswordAuthentication getPasswordAuthentication() {
		    return new PasswordAuthentication(adminUser, password);
		   }
		  });
		  Random random = new Random();
		  String token = "";
		  for (int i = 0; i < 13; i++) {
			char src = (char)(random.nextInt(26)+65);
			token += src;
		  }
		  this.token = token;
		  try {
			 
		   MimeMessage message = new MimeMessage(session);
		   message.setFrom(new InternetAddress(adminUser));
		   message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		   message.setSubject("[Subject] FarmPAM Token");
		   message.setText("http://"+ip+":"+port+contextPath+"/emailPermission.do?token="+token);
		   Transport.send(message);
		  } catch (MessagingException e) {
		   e.printStackTrace();
		  }
	}
	
	@RequestMapping(value="emailPermission.do")
	public ModelAndView emailPermission(HttpSession session,String token) {
		int rows = 0;
		ModelAndView mv = new ModelAndView();
		if(this.token.equals(token)) {
			rows = service.join(this.user);
		}
		if(rows != 0) {
			StringBuilder message = new StringBuilder();
			message.append(this.user.getUserName()).append("(");
			message.append(this.user.getUserId()).append(")");
			message.append("님 회원가입 완료되었습니다. 로그인후 서비스를 이용하시기 바랍니다.");
			mv.addObject("successMessage",""+message);
			mv.setViewName("user/login"); // 로그인 페이지 응답 이동
		}else {
			mv.addObject("errorMessage", "회원가입에 실패하였습니다. 정보를 다시 확인하시기 바랍니다.");
			mv.setViewName("user/login");
		}
		return mv;
	}
}
