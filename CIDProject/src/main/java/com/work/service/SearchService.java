package com.work.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.work.dto.Cosmetic;
import com.work.model.SearchFourOptionDao;

@Service
public class SearchService {
	@Autowired
	SearchFourOptionDao searchDao;

	public ArrayList<Cosmetic> selectFourOption(Map<String, String> map) {
		return searchDao.selectFourOption(map);		
	}

	public ArrayList<Cosmetic> selectThreeOption(Map<String, String> map) {
		
		return searchDao.selectThreeOption(map);
	}

	public ArrayList<Cosmetic> selectTwoOption(Map<String, String> map) {
		return searchDao.selectTwoOption(map);
	}

	public ArrayList<Cosmetic> selectOneOption(Map<String, String> map) {
		return searchDao.selectOneOption(map);
	}
	
}
