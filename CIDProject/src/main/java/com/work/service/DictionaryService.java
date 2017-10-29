package com.work.service;

import java.util.ArrayList;

import com.work.dto.Cosmetic;
import com.work.dto.Ingredient;

public interface DictionaryService {

	public ArrayList<String> searchBrand(String cosmeticBrand);
	public ArrayList<Ingredient> searchIngredient(String ingredientName);
}
