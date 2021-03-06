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
public class SearchThreeOptionDao {
	@Resource(name="factory")
	private Factory factory;

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
	
	public ArrayList<Cosmetic> notCosmeticType(ArrayList<String> arr) {
		ArrayList<Cosmetic>cosmeticList = new ArrayList<Cosmetic>();

		//arr 0.타입 1.타입이름 2.브랜드 3.브랜드명 4.넣을성분 5.성분명 6.뺄성분 7.뺄성분명
		try {
			ArrayList<String> arrsplit1= seperator(arr.get(7));
			ArrayList<String> arrsplit2= seperator(arr.get(5));
			String iteratorSql = " or ingredient_name like ? ";
			String  tempIteratorSql1 = "";
			String  tempIteratorSql2 = "";
			for (int i = 0; i < arrsplit1.size()-1; i++) {
				tempIteratorSql1 += iteratorSql;
			}
			for (int i = 0; i < arrsplit2.size()-1; i++) {
				tempIteratorSql2 += iteratorSql;
			}
			String sql = "";
			if(arrsplit1.size() > 1 || arrsplit2.size() > 1) {
				sql = "select * from cosmetic where cosmetic_name in (\r\n" + 
						"select COSMETIC_NAME from cosingre \r\n" + 
						"where cosmetic_name NOT IN (select cosmetic_name from cosingre where  (ingredient_name like ? "+tempIteratorSql1+")) \r\n" + 
						"and (ingredient_name like ? "+tempIteratorSql2+")\r\n" + 
						"and cosmetic_name in (select cosmetic_name from cosmetic where cosmetic_brand like ?)\r\n" + 
						"GROUP BY COSMETIC_NAME )";
						
			}else {
				sql = "select * from cosmetic where cosmetic_name in (\r\n" + 
						"select COSMETIC_NAME from cosingre where cosmetic_name NOT IN "
						+ "(select cosmetic_name from cosingre where  ingredient_name like ?) \r\n" + 
						"and ingredient_name like ?\r\n" + 
						"and cosmetic_name in "
						+ "(select cosmetic_name from cosmetic where cosmetic_brand like ?)\r\n" + 
						"GROUP BY COSMETIC_NAME )";
			}
			Connection conn = factory.getConnection();
			ResultSet rs = null;
			//타입 넣을성분 뺄성분 브랜드

			PreparedStatement pstmt = conn.prepareStatement(sql);
			for(int i = 0; i < arrsplit1.size(); i++) {
				pstmt.setString(i+1, "%"+arrsplit1.get(i)+"%");
				System.out.println("%"+arrsplit1.get(i)+"%");
			}
			for(int i = 0; i < arrsplit2.size(); i++) {
				pstmt.setString(i+1+arrsplit1.size(), "%"+arrsplit2.get(i)+"%");
				System.out.println("%"+arrsplit2.get(i)+"%");
			}
			pstmt.setString(arrsplit1.size()+arrsplit2.size()+1, "%"+arr.get(3)+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				cosmeticList.add(new Cosmetic(
						rs.getString("COSMETIC_NAME"), rs.getString("COSMETIC_BRAND"), 
						rs.getString("COSMETIC_TYPE"), rs.getString("COSMETIC_PRICE"),
						rs.getString("COSMETIC_SCORE")
						));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cosmeticList;
	}

	public ArrayList<Cosmetic> notCosmeticBrand(ArrayList<String> arr) {
		ArrayList<Cosmetic>cosmeticList = new ArrayList<Cosmetic>();

		//arr 0.타입 1.타입이름 2.브랜드 3.브랜드명 4.넣을성분 5.성분명 6.뺄성분 7.뺄성분명
		try {
			ArrayList<String> arrsplit1= seperator(arr.get(7));
			ArrayList<String> arrsplit2= seperator(arr.get(5));
			String iteratorSql = " or ingredient_name like ? ";
			String  tempIteratorSql1 = "";
			String  tempIteratorSql2 = "";
			for (int i = 0; i < arrsplit1.size()-1; i++) {
				tempIteratorSql1 += iteratorSql;
			}
			for (int i = 0; i < arrsplit2.size()-1; i++) {
				tempIteratorSql2 += iteratorSql;
			}
			String sql = "";
			if(arrsplit1.size() > 1 || arrsplit2.size() > 1) {
				sql = "select * from cosmetic where cosmetic_name in (\r\n" + 
						"select COSMETIC_NAME from cosingre \r\n" + 
						"where cosmetic_name in (select cosmetic_name from cosmetic where cosmetic_type like ?) \r\n" + 
						"and (ingredient_name like ? "+tempIteratorSql2+")\r\n" + 
						"and cosmetic_name NOT IN (select cosmetic_name from cosingre where  (ingredient_name like ? "+tempIteratorSql1+"))\r\n" + 
						"GROUP BY COSMETIC_NAME\r\n" + 
						")";
						
			}else {
				sql = "select * from cosmetic where cosmetic_name in (\r\n" + 
						"select COSMETIC_NAME from cosingre \r\n" + 
						"where cosmetic_name in "
						+ "(select cosmetic_name from cosmetic where cosmetic_type like ?) \r\n" + 
						"and ingredient_name like ? \r\n" + 
						"and cosmetic_name NOT IN "
						+ "(select cosmetic_name from cosingre where  ingredient_name like ?)\r\n" + 
						"GROUP BY COSMETIC_NAME\r\n" + 
						")";
			}
			Connection conn = factory.getConnection();
			ResultSet rs = null;
			//타입 넣을성분 뺄성분 브랜드
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,"%"+arr.get(1)+"%");
			for(int i = 0; i < arrsplit1.size(); i++) {
				pstmt.setString(i+2+arrsplit2.size(), "%"+arrsplit1.get(i)+"%");
				System.out.println("%"+arrsplit1.get(i)+"%");
			}
			for(int i = 0; i < arrsplit2.size(); i++) {
				pstmt.setString(i+2, "%"+arrsplit2.get(i)+"%");
				System.out.println("%"+arrsplit2.get(i)+"%");
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cosmeticList;
	}

	public ArrayList<Cosmetic> noIngreAdd(ArrayList<String> arr) {
		ArrayList<Cosmetic>cosmeticList = new ArrayList<Cosmetic>();

		//arr 0.타입 1.타입이름 2.브랜드 3.브랜드명 4.넣을성분 5.성분명 6.뺄성분 7.뺄성분명
		try {
			ArrayList<String> arrsplit= seperator(arr.get(7));
			String iteratorSql = " or ingredient_name like ? ";
			String  tempIteratorSql = "";
			for (int i = 0; i < arrsplit.size()-1; i++) {
				tempIteratorSql += iteratorSql;
			}
			String sql = "";
			if(arrsplit.size() > 1) {
				sql = "select * from cosmetic where cosmetic_name in (\r\n" + 
						"select COSMETIC_NAME from cosingre \r\n" + 
						"where cosmetic_name NOT IN \r\n" + 
						"(select cosmetic_name from cosingre where  (ingredient_name like ? "+tempIteratorSql+")) \r\n" + 
						"AND cosmetic_NAME IN \r\n" + 
						"(select cosmetic_name from cosmetic where cosmetic_type like ?) \r\n" + 
						"and cosmetic_name in \r\n" + 
						"(select cosmetic_name from cosmetic where cosmetic_brand like ?)\r\n" + 
						"GROUP BY COSMETIC_NAME\r\n" + 
						")";
						
			}else {
				sql =  "select * from cosmetic where cosmetic_name in (\r\n" + 
						"select COSMETIC_NAME from cosingre \r\n" + 
						"where cosmetic_name NOT IN "
						+ "(select cosmetic_name from cosingre where  ingredient_name like ?) \r\n" + 
						"AND cosmetic_NAME IN "
						+ "(select cosmetic_name from cosmetic where cosmetic_type like ?) \r\n" + 
						"and cosmetic_name in "
						+ "(select cosmetic_name from cosmetic where cosmetic_brand like ?)\r\n" + 
						"GROUP BY COSMETIC_NAME\r\n" + 
						")";
			}
			Connection conn = factory.getConnection();
			ResultSet rs = null;
			//타입 넣을성분 뺄성분 브랜드
			PreparedStatement pstmt = conn.prepareStatement(sql);
			for(int i = 0; i < arrsplit.size(); i++) {
				pstmt.setString(i+1, "%"+arrsplit.get(i)+"%");
			}			
			pstmt.setString(arrsplit.size()+1, "%"+arr.get(1)+"%");
			pstmt.setString(arrsplit.size()+2, "%"+arr.get(3)+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				cosmeticList.add(new Cosmetic(
						rs.getString("COSMETIC_NAME"), rs.getString("COSMETIC_BRAND"), 
						rs.getString("COSMETIC_TYPE"), rs.getString("COSMETIC_PRICE"),
						rs.getString("COSMETIC_SCORE")
						));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cosmeticList;
	}

	public ArrayList<Cosmetic> noIngreDelete(ArrayList<String> arr) {
		ArrayList<Cosmetic>cosmeticList = new ArrayList<Cosmetic>();

		//arr 0.타입 1.타입이름 2.브랜드 3.브랜드명 4.넣을성분 5.성분명 6.뺄성분 7.뺄성분명
		try {
			ArrayList<String> arrsplit= seperator(arr.get(5));
			String iteratorSql = " or ingredient_name like ? ";
			String  tempIteratorSql = "";
			for (int i = 0; i < arrsplit.size()-1; i++) {
				tempIteratorSql += iteratorSql;
			}
			String sql = "";
			if(arrsplit.size() > 1) {
				sql = "select * from cosmetic where cosmetic_name in (\r\n" + 
						"select COSMETIC_NAME from cosingre\r\n" + 
						"where cosmetic_name in (select cosmetic_name from cosmetic where cosmetic_type like ?) \r\n" + 
						"and (ingredient_name like ? "+tempIteratorSql+")\r\n" + 
						"and cosmetic_name in (select cosmetic_name from cosmetic where cosmetic_brand like ?)\r\n" + 
						"GROUP BY COSMETIC_NAME\r\n" + 
						")";
						
			}else {
				sql = "select * from cosmetic where cosmetic_name in (\r\n" + 
						"select COSMETIC_NAME from cosingre\r\n" + 
						"where cosmetic_name in "
						+ "(select cosmetic_name from cosmetic where cosmetic_type like ?) \r\n" + 
						"and ingredient_name like ? \r\n" + 
						"and cosmetic_name in "
						+ "(select cosmetic_name from cosmetic where cosmetic_brand like ?)\r\n" + 
						"GROUP BY COSMETIC_NAME\r\n" + 
						")\r\n";
			}
			Connection conn = factory.getConnection();
			ResultSet rs = null;
			//타입 넣을성분 뺄성분 브랜드
			PreparedStatement pstmt = conn.prepareStatement(sql);
			System.out.println("SQL 테스트 시작==========================================");
			pstmt.setString(1, "%"+arr.get(1)+"%");
			System.out.println("1"+"%"+arr.get(1)+"%");
			for(int i = 0; i < arrsplit.size(); i++) {
				pstmt.setString(i+2, "%"+arrsplit.get(i)+"%");
				System.out.println(i+2+" | "+"%"+arrsplit.get(i)+"%");

			}
			pstmt.setString(2+arrsplit.size(), "%"+arr.get(3)+"%");
			System.out.println(2+arrsplit.size()+" | "+"%"+arr.get(3)+"%");

			System.out.println(sql);
			System.out.println("SQL 테스트 끝==========================================");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				cosmeticList.add(new Cosmetic(
						rs.getString("COSMETIC_NAME"), rs.getString("COSMETIC_BRAND"), 
						rs.getString("COSMETIC_TYPE"), rs.getString("COSMETIC_PRICE"),
						rs.getString("COSMETIC_SCORE")
						));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cosmeticList;
	}
}
