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
		firstMatch.setSecondPlayerId(2L);
		firstMatch.setMatchResult(MatchResult.DRAW);
		
		MatchEntity secondMatch = new MatchEntity();
		secondMatch.setMatchId(2L);
		secondMatch.setFirstPlayerId(3L);
		secondMatch.setSecondPlayerId(4l);
		secondMatch.setMatchResult(MatchResult.LOST);
		
		matches.add(firstMatch);
		matches.add(secondMatch);
		
	}
	
	@Override
	public MatchTO findById(Long id){
		MatchEntity matchEntity = matches.stream().filter(u -> u.getMatchId().equals(id)).findFirst().orElse(null);
		return MatchMapper.map(matchEntity);
	}

	@Override
	public MatchTO saveMatch(MatchTO matchTO) {
		MatchEntity match = MatchMapper.map(matchTO);
		matches.add(match);
		return matchTO;
	}

}
