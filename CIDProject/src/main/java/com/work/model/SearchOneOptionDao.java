package com.work.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.work.dto.Cosmetic;

@Repository
public class SearchOneOptionDao {
	@Resource(name="factory")
	private Factory factory;

	public  ArrayList<Cosmetic> selectCosmeticType(ArrayList<String> arr) {
		ArrayList<Cosmetic>cosmeticList = new ArrayList<Cosmetic>();
		//arr 0.타입 1.타입이름 2.브랜드 3.브랜드명 4.넣을성분 5.성분명 6.뺄성분 7.뺄성분명
		try {
			Connection conn = factory.getConnection();
			ResultSet rs = null;
			//타입 넣을성분 뺄성분 브랜드
			String sql = "select * from cosmetic where cosmetic_name in (\r\n" + 
					"select COSMETIC_NAME from cosingre \r\n" + 
					"where cosmetic_name in \r\n" + 
					"(select cosmetic_name from cosmetic \r\n" + 
					"where cosmetic_type like ?) \r\n" + 
					"GROUP BY COSMETIC_NAME)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+arr.get(1)+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				cosmeticList.add(new Cosmetic(
						rs.getString("COSMETIC_NAME"), rs.getString("COSMETIC_BRAND"), 
						rs.getString("COSMETIC_TYPE"), rs.getString("COSMETIC_PRICE"),
						rs.getString("COSMETIC_SCORE")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cosmeticList;
	}

	public ArrayList<Cosmetic> selectCosmeticBrand(ArrayList<String> arr) {
		ArrayList<Cosmetic>cosmeticList = new ArrayList<Cosmetic>();
		//arr 0.타입 1.타입이름 2.브랜드 3.브랜드명 4.넣을성분 5.성분명 6.뺄성분 7.뺄성분명
		try {
			Connection conn = factory.getConnection();
			ResultSet rs = null;
			//타입 넣을성분 뺄성분 브랜드
			String sql = "select * from cosmetic where cosmetic_name in (\r\n" + 
					"select COSMETIC_NAME from cosingre \r\n" + 
					"where  cosmetic_name in \r\n" + 
					"(select cosmetic_name from cosmetic \r\n" + 
					"where cosmetic_brand like ?)  \r\n" + 
					"GROUP BY COSMETIC_NAME)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+arr.get(3)+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				cosmeticList.add(new Cosmetic(
						rs.getString("COSMETIC_NAME"), rs.getString("COSMETIC_BRAND"), 
						rs.getString("COSMETIC_TYPE"), rs.getString("COSMETIC_PRICE"),
						rs.getString("COSMETIC_SCORE")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cosmeticList;
	}

	public ArrayList<Cosmetic> selectIngreAdd(ArrayList<String> arr) {
		ArrayList<Cosmetic>cosmeticList = new ArrayList<Cosmetic>();
		//arr 0.타입 1.타입이름 2.브랜드 3.브랜드명 4.넣을성분 5.성분명 6.뺄성분 7.뺄성분명
		try {
			ArrayList<String> arrsplit= seperator(arr.get(5));
			String iteratorSql = " or ingredient_name like ? ";
			String  tempIteratorSql = "";
			String sql = "";
			for (int i = 0; i < arrsplit.size()-1; i++) {
				tempIteratorSql += iteratorSql;
			}
			if(arrsplit.size() > 1) {
				sql = "select * from cosmetic where cosmetic_name in (\r\n" + 
						"select COSMETIC_NAME from cosingre \r\n" + 
						"where  (ingredient_name like ?"+tempIteratorSql
						+ ")GROUP BY COSMETIC_NAME)";
						
			}else {
				sql = "select * from cosmetic where "
						+ "cosmetic_name in (\r\n" + 
						"select COSMETIC_NAME from cosingre \r\n" + 
						"where  ingredient_name like ? "
						+ "GROUP BY COSMETIC_NAME)";
			}
			Connection conn = factory.getConnection();
			ResultSet rs = null;
			//타입 넣을성분 뺄성분 브랜드
			PreparedStatement pstmt = conn.prepareStatement(sql);
			for(int i = 0; i < arrsplit.size(); i++) {
				pstmt.setString(i+1, "%"+arrsplit.get(i)+"%");
				System.out.println(arrsplit.get(i));
			}
			System.out.println(arr.get(5)+"@@@@@@@@@@@@@@@@@@@@@@");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				cosmeticList.add(new Cosmetic(
						rs.getString("COSMETIC_NAME"), rs.getString("COSMETIC_BRAND"), 
						rs.getString("COSMETIC_TYPE"), rs.getString("COSMETIC_PRICE"),
						rs.getString("COSMETIC_SCORE")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cosmeticList;
	}

	public ArrayList<String> seperator(String ingre) {
		ArrayList<String> arr = new ArrayList<String>();
		String[] ingreList = ingre.split(","); 
		for (int i = 0; i < ingreList.length; i++) {
			arr.add(ingreList[i]);
		}
		System.out.println("================================");
		for (int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i));
		}
		System.out.println(arr.size());
		System.out.println("================================");
		return arr;
	}

	public ArrayList<Cosmetic> selectIngreDelete(ArrayList<String> arr) {
		ArrayList<Cosmetic>cosmeticList = new ArrayList<Cosmetic>();
		//arr 0.타입 1.타입이름 2.브랜드 3.브랜드명 4.넣을성분 5.성분명 6.뺄성분 7.뺄성분명
		try {
			ArrayList<String> arrsplit= seperator(arr.get(7));
			String iteratorSql = " or ingredient_name like ? ";
			String  tempIteratorSql = "";
			String sql = "";
			for (int i = 0; i < arrsplit.size()-1; i++) {
				tempIteratorSql += iteratorSql;
			}
			if(arrsplit.size() > 1) {
				sql = "select * from cosmetic where cosmetic_name in (\r\n" + 
						"select COSMETIC_NAME from cosingre \r\n" + 
						"where cosmetic_name NOT IN \r\n" + 
						"(select cosmetic_name from cosingre where  \r\n" + 
						"(ingredient_name like ?"+tempIteratorSql+
						"))\r\n" + 
						"GROUP BY COSMETIC_NAME)\r\n" + 
						"";
						
			}else {
				sql = "select * from cosmetic where cosmetic_name in (\r\n" + 
						"select COSMETIC_NAME from cosingre \r\n" + 
						"where cosmetic_name NOT IN \r\n" + 
						"(select cosmetic_name from cosingre where  \r\n" + 
						"ingredient_name like ?)  \r\n" + 
						"GROUP BY COSMETIC_NAME)\r\n" + 
						"";
			}
			Connection conn = factory.getConnection();
			ResultSet rs = null;
			//타입 넣을성분 뺄성분 브랜드
			PreparedStatement pstmt = conn.prepareStatement(sql);
			for(int i = 0; i < arrsplit.size(); i++) {
				pstmt.setString(i+1, "%"+arrsplit.get(i)+"%");
				System.out.println(arrsplit.get(i));
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				cosmeticList.add(new Cosmetic(
						rs.getString("COSMETIC_NAME"), rs.getString("COSMETIC_BRAND"), 
						rs.getString("COSMETIC_TYPE"), rs.getString("COSMETIC_PRICE"),
						rs.getString("COSMETIC_SCORE")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cosmeticList;
	}
}
