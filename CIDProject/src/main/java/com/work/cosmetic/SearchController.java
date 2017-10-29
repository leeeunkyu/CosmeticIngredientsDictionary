package com.work.cosmetic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.work.dto.Cosmetic;
import com.work.service.CosmeticService;
import com.work.service.SearchService;

@Controller
public class SearchController {
	@Autowired
	SearchService searchService;
	@Autowired
	CosmeticService cosmeticService;

	@RequestMapping(value = "testajax.do")
	public void testajax(@RequestParam Map<String, String> map) {
		Iterator<String> iterator =  map.keySet().iterator();
		while(iterator.hasNext()) {
			String key = iterator.next();
			if(map.get(key).trim().length() == 0) {
				map.put(key, null);
			}
		}
	}
	@RequestMapping(value = "home.do")
	public ModelAndView home(HttpSession session,HttpServletRequest req) {
		ArrayList<Cosmetic> cosmeticRankList = cosmeticService.cosmeticSelectRank();
		ModelAndView mv = new ModelAndView();
		mv.addObject("cosmeticRankList", cosmeticRankList);
		mv.setViewName("home");
		return mv;
	}
	
	@RequestMapping(value = "searchBar.do")
	public ModelAndView searchBar(@RequestParam Map<String, String> map) {
		ArrayList<Cosmetic> cosmeticList;
		ModelAndView mv = new ModelAndView();
		ArrayList<String> keyArry = new ArrayList<String>();
		Iterator<String> iterator =  map.keySet().iterator();
		while(iterator.hasNext()) {
			String key = iterator.next();
			if(map.get(key).trim().length() == 0) {
				keyArry.add(key);
				map.put(key, null);
			}
		}
		cosmeticList = selectService(keyArry,map);
		for (int i = 0; i < cosmeticList.size(); i++) {
			System.out.println(cosmeticList.get(i).
					getCosmeticName() +"|"+ cosmeticList.get(i).getCosmeticPrice() +"|"+
					cosmeticList.get(i).getCosmeticBrand() +"|"+cosmeticList.get(i).getCosmeticScore());
			
		}
		mv.addObject("cosmeticList",cosmeticList);
		mv.setViewName("cosmetic/itemlist");
		return mv;
	}

	private ArrayList<Cosmetic> selectService(ArrayList<String> keyArry, Map<String, String> map) {
		System.out.println("keyArraysize: "+keyArry.size());
		ArrayList<Cosmetic> cosmeticList = null;
		/*
		keyArry.size()
		0 op1, op2, op3, op4
		1 op1, op2, op3
		2 op1, op2, 
		3 op1
		*/
		switch (keyArry.size()) {
		case 0:
			cosmeticList = searchService.selectFourOption(map);
			break;
		case 1:
			cosmeticList = searchService.selectThreeOption(map);
			break;
		case 2:
			cosmeticList = searchService.selectTwoOption(map);
			break;
		case 3:
			cosmeticList = searchService.selectOneOption(map);
			break;
		default:
			break;
		}
		return cosmeticList;
	}
}
