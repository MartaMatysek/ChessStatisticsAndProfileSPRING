package com.capgemini.chess.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.service.MatchSaveService;
import com.capgemini.chess.service.access.dao.MatchDao;
import com.capgemini.chess.service.to.MatchTO;

@Service
public class MatchSaveServiceImpl implements MatchSaveService{
	
	@Autowired
	private MatchDao matchDao;

	@Override
	public void save(MatchTO matchTO) {
		matchDao.saveMatch(matchTO);
	}
}
