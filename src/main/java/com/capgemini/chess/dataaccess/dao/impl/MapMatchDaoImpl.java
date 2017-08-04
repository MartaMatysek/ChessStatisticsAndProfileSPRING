package com.capgemini.chess.dataaccess.dao.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.capgemini.chess.dataaccess.entities.MatchEntity;
import com.capgemini.chess.dataaccess.enums.MatchResult;
import com.capgemini.chess.service.access.dao.MatchDao;
import com.capgemini.chess.service.mapper.MatchMapper;
import com.capgemini.chess.service.to.MatchTO;

@Repository
public class MapMatchDaoImpl implements MatchDao{
	
	private List<MatchEntity> matches = new ArrayList<>();
	
	{
		MatchEntity firstMatch = new MatchEntity();
		firstMatch.setMatchId(1L);
		firstMatch.setFirstPlayerId(1L);
		firstMatch.setSecondPlayerId(2l);
		firstMatch.setMatchResult(MatchResult.DRAW);
		
		MatchEntity secondMatch = new MatchEntity();
		secondMatch.setMatchId(1L);
		secondMatch.setFirstPlayerId(1L);
		secondMatch.setSecondPlayerId(2l);
		secondMatch.setMatchResult(MatchResult.DRAW);
		
		matches.add(firstMatch);
		matches.add(secondMatch);
		
	}

	@Override
	public MatchTO saveMatch(MatchTO matchTO) {
		MatchEntity match = MatchMapper.map(matchTO);
		matches.add(match);
		return matchTO;
	}

}
