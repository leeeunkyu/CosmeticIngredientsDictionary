package com.work.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.work.dto.Cosmetic;
import com.work.model.DictionatyDao;
import com.work.dto.Ingredient;

@Repository
public class DictionaryServiceImpl implements DictionaryService {

	
	private DictionatyDao dao;
	@Autowired
	public void setDictionaryDao(DictionatyDao dao) {
		this.dao = dao;
	}
	/**
	 * 브랜드명으로 화장품 검색
	 * @param cosmeticBrand
	 * @return
	 */
	public ArrayList<String> searchBrand(String cosmeticBrand) {
		return dao.searchBrand(cosmeticBrand);
		
	}
	
	public ArrayList<Ingredient> searchIngredient(String ingredientName){
		return dao.searchIngredient(ingredientName);
	}
}
