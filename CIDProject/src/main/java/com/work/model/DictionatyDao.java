package com.work.model;

import java.util.ArrayList;

import com.work.dto.Ingredient;

public interface DictionatyDao  {

	public ArrayList<String> searchBrand(String cosmeticBrand);
	public ArrayList<Ingredient> searchIngredient(String ingredient);
}
