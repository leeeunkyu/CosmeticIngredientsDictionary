package com.work.dto;

import org.springframework.stereotype.Repository;

@Repository
public class Review {
	
	private int reviewIndex;
	private String userId;
	private String reviewContent;
	private String cosmeticName;
	private int reivewScore;
	
	public Review(int reviewIndex, String userId, String reviewContent, String cosmeticName,
			int reivewScore) {
		this.reviewIndex = reviewIndex;
		this.userId = userId;
		this.reviewContent = reviewContent;
		this.cosmeticName = cosmeticName;
		this.reivewScore = reivewScore;
	}
	
	public Review() {
	}

	public int getReviewIndex() {
		return reviewIndex;
	}
	public void setReviewIndex(int reviewIndex) {
		this.reviewIndex = reviewIndex;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	public String getCosmeticName() {
		return cosmeticName;
	}
	public void setCosmeticName(String cosmeticName) {
		this.cosmeticName = cosmeticName;
	}
	public int getReivewScore() {
		return reivewScore;
	}
	public void setReivewScore(int reivewScore) {
		this.reivewScore = reivewScore;
	}



}
