package com.capgemini.chess.dataaccess.dao.impl;


import java.util.HashMap;
import java.util.Map;

import com.capgemini.chess.dataaccess.entities.MatchEntity;
import com.capgemini.chess.service.access.dao.MatchDao;
import com.capgemini.chess.service.mapper.MatchMapper;
import com.capgemini.chess.service.to.MatchTO;

public class MapMatchDao implements MatchDao{
	
	private Map<Long, MatchEntity> matches = new HashMap<>();
	
	@Override
	public void saveMatch(MatchTO matchTO) {
		MatchEntity match = MatchMapper.map(matchTO);
		matches.put(match.getMatchId(), match);
	}

}
