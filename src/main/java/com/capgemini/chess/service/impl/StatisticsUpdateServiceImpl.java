package com.capgemini.chess.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.dataaccess.enums.MatchResult;
import com.capgemini.chess.service.StatisticsCalculateService;
import com.capgemini.chess.service.StatisticsUpdateService;
import com.capgemini.chess.service.to.MatchTO;

@Service
public class StatisticsUpdateServiceImpl implements StatisticsUpdateService{

	@Autowired
	private MatchResult result;
	@Autowired
	private StatisticsCalculateService statisticsCalculate;
	
	@Override
	public void update(MatchTO matchTO) {
		result = matchTO.getMatchResult();
		
		if(result == MatchResult.WON){
			 statisticsCalculate.wonFirstPlayerUpdateStatistics(matchTO);
		}
		
		else if(result == MatchResult.LOST){
			statisticsCalculate.lostFirstPlayserUpdateStatistics(matchTO);
		}		
		else{
			statisticsCalculate.drawUpdateStatistics(matchTO);
		}
	}
	
	
}
