package com.work.service;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.work.dto.Cosmetic;
import com.work.dto.Ingredient;
import com.work.dto.Review;
import com.work.model.CosmeticDao;

@Service
public class CosmeticService {
	@Autowired
	private CosmeticDao cosmeticDao;

	public Cosmetic selectCosmetic(String cosmeticName) {
		return 	cosmeticDao.selectCosmetic(cosmeticName);
	}

	public ArrayList<Review> selectReviewList(String cosmeticName) {
		return cosmeticDao.selectReviewList(cosmeticName);
	}

	public boolean reviewAdd(Map<String, String> map) {
		return cosmeticDao.reviewAdd(map);
	}

	public boolean updateScore(Map<String, String> map) {
		return cosmeticDao.updateScore(map);
	}

	public ArrayList<Ingredient> selectIngreList(String cosmeticName) {
		return cosmeticDao.selectIngreList(cosmeticName);
	}

	public ArrayList<Cosmetic> cosmeticSelectRank() {
		return cosmeticDao.cosmeticSelectRank();
	}
	
	
}
