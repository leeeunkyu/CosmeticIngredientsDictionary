package com.work.cosmetic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.work.dto.Cosmetic;
import com.work.dto.Ingredient;
import com.work.service.DictionaryService;

@Controller
public class DictionaryController {

	private DictionaryService service;
	@Autowired
	public void setDictionaryService(DictionaryService service) {
		this.service = service;
	}
	
	@RequestMapping("brandList.do")
	public ModelAndView brandList(String cosmeticBrand) {
		ModelAndView mv = new ModelAndView();
		cosmeticBrand=" ";
		ArrayList<String> list = service.searchBrand(cosmeticBrand);
	
		mv.addObject("list", list);
		mv.setViewName("dictionary/brandList");
		
		return mv;
		}
	
	@RequestMapping("searchBrand.do")
	public ModelAndView searchBrand(String cosmeticBrand) {
		ModelAndView mv = new ModelAndView();
		ArrayList<String> list = service.searchBrand(cosmeticBrand);
		
		if(cosmeticBrand.trim().length() == 0 || cosmeticBrand == null) {
			mv.addObject("errormessage", "브랜드명을 입력하시기 바랍니다.");
			mv.setViewName("dictionary/brandList");
			
		}else if(list.size() > 0) {
			StringBuilder message = new StringBuilder();
			message.append("'").append(cosmeticBrand);
			message.append("'").append("에 대한 검색결과 ");
			message.append(list.size()).append("건이 있습니다.");
			mv.addObject("message", message);
			mv.addObject("list", list);
			mv.setViewName("dictionary/brandList");
			
		}else if(list.size()==0){
			StringBuilder message = new StringBuilder();
			message.append("'").append(cosmeticBrand);
			message.append("'").append("에 대한 검색결과가 없습니다.");
			mv.addObject("errormessage", message);
			mv.setViewName("dictionary/brandList");
		}
		
		return mv;
		
	}
	@RequestMapping("ingredientList.do")
	public ModelAndView ingredient(String ingredientName) {
		ingredientName = " ";
		ModelAndView mv = new ModelAndView();
		ArrayList<Ingredient> list = service.searchIngredient(ingredientName);
		mv.addObject("list", list);
		mv.setViewName("dictionary/ingredientList");
		
		return mv;
	}
	
	@RequestMapping("searchIngredient.do")
	public ModelAndView searchIngredient(String ingredientName) {
		ModelAndView mv = new ModelAndView();
		ArrayList<Ingredient> list = service.searchIngredient(ingredientName);
		
		if(ingredientName.trim().length()==0 || ingredientName==null) {
			mv.addObject("errormessage", "성분명을 입력하시기 바랍니다.");
			mv.setViewName("dictionary/ingredientList");
			
		}else if(list.size()>0) {
			StringBuilder message = new StringBuilder();
			message.append("'").append(ingredientName);
			message.append("'").append("에 대한 검색결과 ");
			message.append(list.size()).append("건이 있습니다.");
			mv.addObject("message", message);
			mv.addObject("list", list);
			mv.setViewName("dictionary/ingredientList");
			
		}else if(list.size()==0) {
			StringBuilder message = new StringBuilder();
			message.append("'").append(ingredientName);
			message.append("'").append("에 대한 검색결과가 없습니다.");
			mv.addObject("errormessage", message);
			mv.setViewName("dictionary/ingredientList");
		}
		return mv;
	}
	
}
