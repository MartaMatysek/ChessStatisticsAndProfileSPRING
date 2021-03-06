package com.capgemini.chess.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.service.MatchSaveService;
import com.capgemini.chess.service.access.dao.MatchDao;
import com.capgemini.chess.service.to.MatchTO;

@Service
public class MatchSaveServiceImpl implements MatchSaveService{
	
	private MatchDao matchDao;

	@Autowired
	public MatchSaveServiceImpl(MatchDao matchDao) {
		this.matchDao = matchDao;
	}

	@Override
	public MatchTO save(MatchTO matchTO) {
		return matchDao.saveMatch(matchTO);
	}
}
