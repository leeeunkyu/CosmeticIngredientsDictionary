package com.work.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.work.dto.Cosmetic;

@Repository
public class SearchFourOptionDao {

	@Resource(name="factory")
	private Factory factory;
	@Autowired
	private SearchThreeOptionDao searchThreeOptionDao;
	@Autowired
	private SearchTwoOptionDao searchTwoOptionDao;
	@Autowired
	private SearchOneOptionDao searchOneOptionDao;
	
	private boolean cosmeticType = false;
	private boolean cosmeticBrand = false;
	private boolean ingreAdd = false;
	private boolean ingerDelete = false;
	
	public ArrayList<String> seperator(String ingre) {
		ArrayList<String> arr = new ArrayList<String>();
		String[] ingreList = ingre.split(","); 
		for (int i = 0; i < ingreList.length; i++) {
			arr.add(ingreList[i]);
		}
		return arr;
	}
	
	
	
	//op1 타입 op2 브랜드 op3 넣을성분 op4 뺄성분
	public ArrayList<Cosmetic> selectFourOption(Map<String, String> map) {
		ArrayList<String>arr = sortCosmeticItem(map);
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
						"where cosmetic_name in \r\n" + 
						"(select cosmetic_name from cosmetic "
						+ "where cosmetic_type like ?)\r\n" + 
						"and (ingredient_name like ?"+tempIteratorSql2+")\r\n" + 
						"and cosmetic_name NOT IN "
						+ "(select cosmetic_name from cosingre where  "
						+ "(ingredient_name like ?"+tempIteratorSql1+"))\r\n" + 
						"and cosmetic_name in "
						+ "(select cosmetic_name from cosmetic "
						+ "where cosmetic_brand like ?)\r\n" + 
						"GROUP BY COSMETIC_NAME\r\n" + 
						")";
						
			}else {
				sql = "select * from cosmetic where cosmetic_name in (\r\n" + 
						"select COSMETIC_NAME from cosingre \r\n" + 
						"where cosmetic_name in \r\n" + 
						"(select cosmetic_name from cosmetic "
						+ "where cosmetic_type like ?)\r\n" + 
						"and ingredient_name like ?\r\n" + 
						"and cosmetic_name NOT IN "
						+ "(select cosmetic_name from cosingre "
						+ "where  ingredient_name like ?)\r\n" + 
						"and cosmetic_name in "
						+ "(select cosmetic_name from cosmetic"
						+ " where cosmetic_brand like ?)\r\n" + 
						"GROUP BY COSMETIC_NAME\r\n" + 
						")";
			}
			Connection conn = factory.getConnection();
			ResultSet rs = null;
			//타입 넣을성분 뺄성분 브랜드
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+arr.get(1)+"%");
			
			for(int i = 0; i < arrsplit2.size(); i++) {
				pstmt.setString(i+2, "%"+arrsplit2.get(i)+"%");
				System.out.println("%"+arrsplit2.get(i)+"%");
			}
			for(int i = 0; i < arrsplit1.size(); i++) {
				pstmt.setString(i+2+arrsplit2.size(), "%"+arrsplit1.get(i)+"%");
				System.out.println("%"+arrsplit1.get(i)+"%");
			}
			/*pstmt.setString(2, "%"+arr.get(5)+"%");
			pstmt.setString(3, "%"+arr.get(7)+"%");*/
			
			pstmt.setString(arrsplit1.size()+arrsplit2.size()+2, "%"+arr.get(3)+"%");
			
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

	private ArrayList<String> sortCosmeticItem(Map<String, String> map) {
		ArrayList<String>temp = new ArrayList<String>();
		ArrayList<String>arr = new ArrayList<String>();
		Iterator<String> iterator =  map.keySet().iterator();
		while(iterator.hasNext()) {
			String key = iterator.next();
			temp.add(map.get(key));
			arr.add(map.get(key));
		}
		for (int i = 0; i < temp.size(); i++) {
			if(temp.get(i) != null) {
				switch (temp.get(i)) {
				case "cosmeticType":
					if(this.cosmeticType == false) {
						if(temp.get(i+1) != null) {
						arr.set(0, "cosmeticType");
						arr.set(1, temp.get(i+1));
						this.cosmeticType = true;
						}
					}
					break;
				case "cosmeticBrand":
					arr.set(2, "cosmeticBrand");
					arr.set(3, temp.get(i+1));
					this.cosmeticBrand = true;
					break;
				case "ingreAdd":
					arr.set(4, "ingreAdd");
					arr.set(5, temp.get(i+1));
					this.ingreAdd = true;
					break;
				case "ingerDelete":
					arr.set(6, "ingerDelete");
					arr.set(7, temp.get(i+1));
					this.ingerDelete = true;
					break;
				default:
					break;
				}
			}else {
				
			}
		}
		return arr;
	}
	public ArrayList<Cosmetic> selectThreeOption(Map<String, String> map) {
		ArrayList<String>arr = sortCosmeticItem(map);
		ArrayList<Cosmetic>cosmeticList = new ArrayList<Cosmetic>();
		if(this.cosmeticType == false) {
			cosmeticList = searchThreeOptionDao.notCosmeticType(arr);
		}else if(this.cosmeticBrand == false) {
			cosmeticList = searchThreeOptionDao.notCosmeticBrand(arr);
		}else if(this.ingreAdd == false) {
			cosmeticList = searchThreeOptionDao.noIngreAdd(arr);
		}else if(this.ingerDelete == false) {
			cosmeticList = searchThreeOptionDao.noIngreDelete(arr);
		}
		this.cosmeticType = false;
		this.cosmeticBrand = false;
		this.ingreAdd = false;
		this.ingerDelete = false;
		
		return cosmeticList;
	}

	public ArrayList<Cosmetic> selectTwoOption(Map<String, String> map) {
		ArrayList<String>arr = sortCosmeticItem(map);
		for (int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i));
		}
		ArrayList<Cosmetic>cosmeticList = new ArrayList<Cosmetic>();
		if(this.cosmeticType == true && this.ingreAdd == true) {
			cosmeticList = searchTwoOptionDao.includeTypeAndIngreAdd(arr);
		}else if(this.cosmeticType == true && this.ingerDelete == true) {
			cosmeticList = searchTwoOptionDao.includeTypeAndIngreDelete(arr);
		}else if(this.cosmeticType == true && this.cosmeticBrand == true) {
			cosmeticList = searchTwoOptionDao.includeTypeAndBrand(arr);
		}else if(this.ingreAdd == true && this.ingerDelete == true) {
			cosmeticList = searchTwoOptionDao.includeIngreAddAndIngreDelete(arr);
		}else if(this.cosmeticBrand == true && this.ingreAdd == true) {
			cosmeticList = searchTwoOptionDao.includeBrandAndIngreAdd(arr);
		}else if(this.cosmeticBrand == true && this.ingerDelete == true) {
			cosmeticList = searchTwoOptionDao.includeBrandAndIngreDelete(arr);
		}
		
		this.cosmeticType = false;
		this.cosmeticBrand = false;
		this.ingreAdd = false;
		this.ingerDelete = false;

		return cosmeticList;

	}

	public ArrayList<Cosmetic> selectOneOption(Map<String, String> map) {
		ArrayList<String>arr = sortCosmeticItem(map);
		ArrayList<Cosmetic>cosmeticList = new ArrayList<Cosmetic>();
		if(this.cosmeticType == true) {
			cosmeticList = searchOneOptionDao.selectCosmeticType(arr);
		}else if(this.cosmeticBrand == true) {
			cosmeticList = searchOneOptionDao.selectCosmeticBrand(arr);
		}else if(this.ingreAdd == true) {
			cosmeticList = searchOneOptionDao.selectIngreAdd(arr);
		}else if(this.ingerDelete == true) {
			cosmeticList = searchOneOptionDao.selectIngreDelete(arr);
		}
		
		this.cosmeticType = false;
		this.cosmeticBrand = false;
		this.ingreAdd = false;
		this.ingerDelete = false;

		return cosmeticList;
	}
}
