package com.capgemini.chess.dataaccess.dao.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.capgemini.chess.dataaccess.entities.MatchEntity;
import com.capgemini.chess.service.access.dao.MatchDao;
import com.capgemini.chess.service.mapper.MatchMapper;
import com.capgemini.chess.service.to.MatchTO;

@Repository
public class MapMatchDaoImpl implements MatchDao{
	
	private List<MatchEntity> matches = new ArrayList<>();

	@Override
	public void saveMatch(MatchTO matchTO) {
		MatchEntity match = MatchMapper.map(matchTO);
		matches.add(match);
	}

}
