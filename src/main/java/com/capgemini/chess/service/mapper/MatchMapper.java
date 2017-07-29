package com.capgemini.chess.service.mapper;

import com.capgemini.chess.dataaccess.entities.MatchEntity;
import com.capgemini.chess.service.to.MatchTO;

public class MatchMapper {

	public static MatchTO map(MatchEntity matchResultEntity){
		if(matchResultEntity != null){
			MatchTO matchResultTO = new MatchTO();
			matchResultTO.setMatchId(matchResultEntity.getMatchId());
			matchResultTO.setFirstPlayerId(matchResultEntity.getFirstPlayerId());
			matchResultTO.setSecondPlayerId(matchResultEntity.getSecondPlayerId());
			matchResultTO.setMatchResult(matchResultEntity.getMatchResult());
			return matchResultTO;
		}
		
		return null;
	}
	
	public static MatchEntity map(MatchTO matchResultTO){
		if(matchResultTO != null){
			MatchEntity matchResultEntity = new MatchEntity();
			matchResultEntity.setMatchId(matchResultTO.getMatchId());
			matchResultEntity.setFirstPlayerId(matchResultTO.getFirstPlayerId());
			matchResultEntity.setSecondPlayerId(matchResultTO.getSecondPlayerId());
			matchResultEntity.setMatchResult(matchResultTO.getMatchResult());
			return matchResultEntity;
		}
		
		return null;
	}
}
