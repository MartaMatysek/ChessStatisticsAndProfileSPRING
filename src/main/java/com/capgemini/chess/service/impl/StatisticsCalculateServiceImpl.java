package com.capgemini.chess.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.service.ReadService;
import com.capgemini.chess.service.StatisticsCalculateService;
import com.capgemini.chess.service.StatisticsUpdateSaveService;
import com.capgemini.chess.service.to.MatchTO;
import com.capgemini.chess.service.to.UserStatisticsTO;

@Service
public class StatisticsCalculateServiceImpl implements StatisticsCalculateService{
	
	@Autowired
	private ReadService readMatch;
	@Autowired
	private StatisticsUpdateSaveService statisticsUpdateSave;
	
	@Override
	public void wonFirstPlayerUpdateStatistics(MatchTO matchTO){
		Long idFirstPlayer = matchTO.getFirstPlayerId();
		Long idSecondPlayer = matchTO.getSecondPlayerId();
		updateStatisticsPlayer(matchTO, 5, 1, 0, 0, idFirstPlayer);
		updateStatisticsPlayer(matchTO, -5, 0, 1, 0, idSecondPlayer);
	}
	
	@Override
	public void lostFirstPlayserUpdateStatistics(MatchTO matchTO){
		Long idFirstPlayer = matchTO.getFirstPlayerId();
		Long idSecondPlayer = matchTO.getSecondPlayerId();
		updateStatisticsPlayer(matchTO, -5, 0, 1, 0, idFirstPlayer);
		updateStatisticsPlayer(matchTO, 5, 1, 0, 0, idSecondPlayer);
	}
	
	@Override
	public void drawUpdateStatistics(MatchTO matchTO){
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
		playerStatistics.setLevel(setPlayerLevel(playerStatistics.getPoints()));
		statisticsUpdateSave.save(playerStatistics);
	}
	
	private int setPlayerLevel(int points){
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
