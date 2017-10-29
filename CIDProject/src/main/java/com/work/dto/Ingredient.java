package com.work.dto;


public class Ingredient {
	
	private String ingredientName;
	private String ingredientRisk;
	
	public Ingredient() {
	}
	
	public Ingredient(String ingredientName, String ingredientRisk) {
		this.ingredientName = ingredientName;
		this.ingredientRisk = ingredientRisk;
	}
	public String getIngredientName() {
		return ingredientName;
	}
	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}
	public String getIngredientRisk() {
		return ingredientRisk;
	}
	public void setIngredientRisk(String ingredientRisk) {
		this.ingredientRisk = ingredientRisk;
	}	
}
