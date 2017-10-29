package com.work.cosmetic;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.work.dto.Cosmetic;
import com.work.dto.Ingredient;
import com.work.dto.Review;
import com.work.service.CosmeticService;

@Controller
public class CosmeticController {
	@Autowired
	CosmeticService cosmeticService;
		
	@RequestMapping(value = "iteminfo.do")
	public ModelAndView iteminfo(String cosmeticName) {
		ArrayList<Ingredient> arryIngredientList = cosmeticService.selectIngreList(cosmeticName);
		Cosmetic cosmetic = cosmeticService.selectCosmetic(cosmeticName);
		ArrayList<Review> reviewList = cosmeticService.selectReviewList(cosmeticName);
		ModelAndView mv = new ModelAndView();
		mv.addObject("cosmetic",cosmetic);
		mv.addObject("reviewList",reviewList);
		mv.addObject("arryIngredientList",arryIngredientList);
		mv.setViewName("cosmetic/iteminfo");
		return mv;
	}
	
	
	@RequestMapping(value = "reviewAdd.do")
	public ModelAndView reviewAdd(@RequestParam Map<String,String> map,HttpSession session) {
		map.put("userId",(String) session.getAttribute("userId"));
		map.put("userName",(String) session.getAttribute("userName"));
		ModelAndView mv = new ModelAndView();
		if(cosmeticService.updateScore(map)) {
			if(cosmeticService.reviewAdd(map)) {
			ArrayList<Ingredient> arryIngredientList = cosmeticService.selectIngreList(map.get("cosmeticName"));
			Cosmetic cosmetic = cosmeticService.selectCosmetic(map.get("cosmeticName"));
			ArrayList<Review> reviewList = cosmeticService.selectReviewList(map.get("cosmeticName"));
			mv.addObject("cosmetic",cosmetic);
			mv.addObject("reviewList",reviewList);
			mv.addObject("arryIngredientList",arryIngredientList);
			mv.setViewName("cosmetic/iteminfo");
			}else {
				mv.addObject("cosmeticName",map.get("cosmeticName"));
				mv.setViewName("error/errorReviewAdd");	
			}
		}else {
			mv.addObject("cosmeticName",map.get("cosmeticName"));
			mv.setViewName("error/errorReviewAdd");	
		}
		return mv;
	}
	

}
