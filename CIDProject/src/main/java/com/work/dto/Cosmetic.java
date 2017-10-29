package com.work.dto;

public class Cosmetic {
	
	private String cosmeticName;
	private String cosmeticBrand;
	private String cosmeticType;
	private String cosmeticPrice;
	private String cosmeticScore;
		
	public Cosmetic() {
	}

	public Cosmetic(String cosmeticName, String cosmeticBrand, String cosmeticType, String cosmeticPrice,
			String cosmeticScore) {
		this.cosmeticName = cosmeticName;
		this.cosmeticBrand = cosmeticBrand;
		this.cosmeticType = cosmeticType;
		this.cosmeticPrice = cosmeticPrice;
		this.cosmeticScore = cosmeticScore;
	}
	
	public String getCosmeticName() {
		return cosmeticName;
	}
	public void setCosmeticName(String cosmeticName) {
		this.cosmeticName = cosmeticName;
	}
	public String getCosmeticBrand() {
		return cosmeticBrand;
	}
	public void setCosmeticBrand(String cosmeticBrand) {
		this.cosmeticBrand = cosmeticBrand;
	}
	public String getCosmeticType() {
		return cosmeticType;
	}
	public void setCosmeticType(String cosmeticType) {
		this.cosmeticType = cosmeticType;
	}
	public String getCosmeticPrice() {
		return cosmeticPrice;
	}
	public void setCosmeticPrice(String cosmeticPrice) {
		this.cosmeticPrice = cosmeticPrice;
	}
	public String getCosmeticScore() {
		return cosmeticScore;
	}
	public void setCosmeticScore(String cosmeticScore) {
		this.cosmeticScore = cosmeticScore;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(cosmeticName);
		builder.append(", ");
		builder.append(cosmeticBrand);
		builder.append(", ");
		builder.append(cosmeticType);
		builder.append(", ");
		builder.append(cosmeticPrice);
		builder.append(", ");
		builder.append(cosmeticScore);
		return builder.toString();
	}
	
	
}
