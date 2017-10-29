package com.work.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.work.dto.Cosmetic;
import com.work.dto.Ingredient;
import com.work.dto.Review;
import com.work.service.CosmeticService;

@Repository
public class CosmeticDao {
	
	@Resource(name="factory")
	private Factory factory;
	
	public Cosmetic selectCosmetic(String cosmeticName) {
		try {
			Connection conn = factory.getConnection();
			ResultSet rs = null;
			String sql = "select * from cosmetic where cosmetic_name = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cosmeticName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return new Cosmetic(
						rs.getString("COSMETIC_NAME"), rs.getString("COSMETIC_BRAND"), 
						rs.getString("COSMETIC_TYPE"), rs.getString("COSMETIC_PRICE"),
						rs.getString("COSMETIC_SCORE")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}

	public ArrayList<Review> selectReviewList(String cosmeticName) {
		try {
			ArrayList<Review> reviewList = new ArrayList<Review>();
			Connection conn = factory.getConnection();
			ResultSet rs = null;
			String sql = "select * from review where cosmetic_name = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cosmeticName);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				reviewList.add(new Review(rs.getInt("review_index"), rs.getString("user_id"),
						rs.getString("review_content"),	
						rs.getString("cosmetic_name"), rs.getInt("reivew_score")));
			}
			return reviewList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int reviewIndex() {
		try {
			Connection conn = factory.getConnection();
			ResultSet rs = null;
			String sql = "select review_index from review order by review_index desc";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("review_index");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public boolean reviewAdd(Map<String, String> map) {
	
		try {
			int indexNum = reviewIndex();
			Connection conn = factory.getConnection();
			String sql = "INSERT INTO REVIEW VALUES(?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, indexNum + 1);
			pstmt.setString(2, map.get("userName"));
			pstmt.setString(3, map.get("reviewContent"));
			pstmt.setString(4, map.get("cosmeticName"));
			pstmt.setString(5, map.get("reviewScore"));
			if(	pstmt.executeUpdate() != 0) {
				return true;
			}else {
			}
		} catch (SQLException e) {
			System.out.println("이미 등록된 id가 있습니다.2");
		}
		return false;
	}

	private boolean checkReview(Map<String, String> map) {
		try {
			Connection conn = factory.getConnection();
			ResultSet rs = null;
			String sql = "select user_id from review where cosmetic_name = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,map.get("cosmeticName"));
			rs = pstmt.executeQuery();
			while(rs.next()) {
				if(map.get("userId").equals(rs.getString("user_id")))
					return true;
			}
		} catch (SQLException e) {
		}
		return false;
	}
	public boolean updateScore(Map<String, String> map) {
		if(checkReview(map)) {
			return false;
		}
		try {
			Connection conn = factory.getConnection();
			String sql = "update cosmetic set cosmetic_score = \r\n" + 
					"((((select cosmetic_score from "
					+ "cosmetic where cosmetic_name = ?) \r\n" + 
					"* (select count(review_index) from review "
					+ "where cosmetic_name = ?)) + ?) \r\n" + 
					"/ ((select count(review_index) from review "
					+ "where cosmetic_name = ? )+1))\r\n" + 
					"where cosmetic_name = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//1.이름 2.이름 3.받은점수 4.이름 5.이름
			pstmt.setString(1, map.get("cosmeticName"));
			pstmt.setString(2, map.get("cosmeticName"));
			pstmt.setString(3, map.get("reviewScore"));
			pstmt.setString(4, map.get("cosmeticName"));
			pstmt.setString(5, map.get("cosmeticName"));
			if(	pstmt.executeUpdate() != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public  ArrayList<Ingredient> selectIngreList(String cosmeticName) {
		try {
			ArrayList<Ingredient> arryIngredientList = new ArrayList<Ingredient>();
			Connection conn = factory.getConnection();
			ResultSet rs = null;
			String sql = "select * from ingredient \r\n" + 
					"where ingredient_name in\r\n" + 
					"(select ingredient_name from cosingre \r\n" + 
					"where cosmetic_name = ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cosmeticName);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				arryIngredientList.add(new Ingredient(rs.getString("INGREDIENT_NAME")
						,rs.getString("INGREDIENT_RISK")));
			}
			return arryIngredientList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Cosmetic> cosmeticSelectRank() {
		try {
			ArrayList<Cosmetic> cosmeticRankList = new ArrayList<Cosmetic>();
			Connection conn = factory.getConnection();
			ResultSet rs = null;
			String sql = "select * from cosmetic order by cosmetic_score desc";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int count = 0;
			while(rs.next() && count<5) {
				cosmeticRankList.add(new Cosmetic(rs.getString("COSMETIC_NAME"),
						rs.getString("COSMETIC_BRAND"),rs.getString("cosmetic_type"),
						rs.getString("cosmetic_price"), rs.getString("cosmetic_score")));
				count ++;
			}
			count = 0;
			return cosmeticRankList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
