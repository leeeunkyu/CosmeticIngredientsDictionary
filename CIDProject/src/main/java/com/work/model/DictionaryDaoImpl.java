package com.work.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.work.dto.Ingredient;

@Repository
public class DictionaryDaoImpl implements DictionatyDao {

	private Factory factory;
	@Autowired
	public void setFactoryDao(Factory factory) {
		this.factory = factory;
	}
	
	
	/**
	 * 브랜드명 검색
	 */
	public ArrayList<String> searchBrand(String cosmeticBrand) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select cosmetic_brand from cosmetic where cosmetic_brand like ? group by cosmetic_brand order by cosmetic_brand";
		ArrayList<String> list = new ArrayList<>();
		
		try {
			con = factory.getConnection();
			pstmt = con.prepareStatement(sql);
			if(cosmeticBrand.trim().length()<1) {
				pstmt.setString(1, "%"+cosmeticBrand.trim()+"%");
			}else {
				pstmt.setString(1, cosmeticBrand.trim()+"%");
			}
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(rs.getString("cosmetic_brand"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				factory.close(con, pstmt, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/**
	 * 성분명 검색
	 */
	public ArrayList<Ingredient> searchIngredient(String ingredient) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		String sql = "select * from ingredient where ingredient_name like ? order by ingredient_name";
		ArrayList<Ingredient> list = new ArrayList<Ingredient>();
		
		try {
			con=factory.getConnection();
			pstmt=con.prepareStatement(sql);
			
			if(ingredient.trim().length()<1) {
				pstmt.setString(1, "%"+ingredient.trim()+"%");
			}else {
				pstmt.setString(1, ingredient.trim()+"%");
			}
			
			rs=pstmt.executeQuery();
			String ingredientName = null;
			String ingredientRisk = null;
			
			while(rs.next()) {
				ingredientName = rs.getString("ingredient_name");
				ingredientRisk = rs.getString("ingredient_risk");
				
				list.add(new Ingredient(ingredientName,ingredientRisk));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				factory.close(con, pstmt, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
}
