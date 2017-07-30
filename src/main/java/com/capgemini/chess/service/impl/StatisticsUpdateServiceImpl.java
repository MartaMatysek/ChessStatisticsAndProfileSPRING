package com.capgemini.chess.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.dataaccess.enums.MatchResult;
import com.capgemini.chess.service.ReadService;
import com.capgemini.chess.service.StatisticsSaveService;
import com.capgemini.chess.service.StatisticsUpdateService;
import com.capgemini.chess.service.to.MatchTO;
import com.capgemini.chess.service.to.UserStatisticsTO;

@Service
public class StatisticsUpdateServiceImpl implements StatisticsUpdateService{

	private ReadService readMatch;
	private StatisticsSaveService saveStatistics;
	
	@Autowired
	public StatisticsUpdateServiceImpl(ReadService readMatch, StatisticsSaveService saveStatistics) {
		this.readMatch = readMatch;
		this.saveStatistics = saveStatistics;
	}

	@Override
	public void update(MatchTO matchTO) {
		MatchResult result = matchTO.getMatchResult();
		
		if(result == MatchResult.WON){
			 wonFirstPlayerUpdateStatistics(matchTO);
		}
		
		else if(result == MatchResult.LOST){
			lostFirstPlayserUpdateStatistics(matchTO);
		}		
		else{
			drawUpdateStatistics(matchTO);
		}
	}
	
	private void wonFirstPlayerUpdateStatistics(MatchTO matchTO){
		Long idFirstPlayer = matchTO.getFirstPlayerId();
		Long idSecondPlayer = matchTO.getSecondPlayerId();
		updateStatisticsPlayer(matchTO, 5, 1, 0, 0, idFirstPlayer);
		updateStatisticsPlayer(matchTO, -5, 0, 1, 0, idSecondPlayer);
	}
	
	private void lostFirstPlayserUpdateStatistics(MatchTO matchTO){
		Long idFirstPlayer = matchTO.getFirstPlayerId();
		Long idSecondPlayer = matchTO.getSecondPlayerId();
		updateStatisticsPlayer(matchTO, -5, 0, 1, 0, idFirstPlayer);
		updateStatisticsPlayer(matchTO, 5, 1, 0, 0, idSecondPlayer);
	}
	
	private void drawUpdateStatistics(MatchTO matchTO){
		Long idFirstPlayer = matchTO.getFirstPlayerId();
		Long idSecondPlayer = matchTO.getSecondPlayerId();
		updateStatisticsPlayer(matchTO, 0, 0, 0, 1, idFirstPlayer);
		updateStatisticsPlayer(matchTO, 0, 0, 0, 1, idSecondPlayer);
	}
	
	private void updateStatisticsPlayer(MatchTO matchResultTO, int pointsToAdd, int won, int lost, int draw, Long playerId){
		UserStatisticsTO playerStatistics = readMatch.readUserStatistics(playerId);
		playerStatistics.setNumberOfWonMatches(playerStatistics.getNumberOfWonMatches()+ won);
		playerStatistics.setNumberOfLostMatches(playerStatistics.getNumberOfLostMatches()+lost);
		playerStatistics.setNumberOfDrawMatches(playerStatistics.getNumberOfDrawMatches()+draw);
		playerStatistics.setPoints(playerStatistics.getPoints() + pointsToAdd);
		playerStatistics.setLevel(getPlayerLevel(playerStatistics.getPoints()));
		saveStatistics.save(playerStatistics);
	}
	
	private int getPlayerLevel(int points){
		if(points<10){
			return 1;
		}
		else if(points < 50){
			return 2;
		}
		else if(points < 100){
			return 3;
		}
		else{
			return 4;
		}
	}
	
}
