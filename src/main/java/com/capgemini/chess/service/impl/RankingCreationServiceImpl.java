package com.capgemini.chess.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.service.RankingCreationService;
import com.capgemini.chess.service.ReadService;
import com.capgemini.chess.service.to.RankingTO;
import com.capgemini.chess.service.to.UserStatisticsTO;

@Service
public class RankingCreationServiceImpl implements RankingCreationService{
	
	@Autowired
	private ReadService readService;	

	@Override
	public RankingTO create(Long id){
		RankingTO ranking = new RankingTO();
		List<UserStatisticsTO> listOfUsersStatistics = readService.readRanking();
		int index = getUserPossition(listOfUsersStatistics, id);
		ranking.setUserRankingPosition(index+1);
		ranking.setUserLevel(listOfUsersStatistics.get(index).getLevel());
		ranking.setListOfUsersStatistics(listOfUsersStatistics);
		
		return ranking;
	}
	
	private int getUserPossition(List<UserStatisticsTO> listOfUsersStatistics, Long id){
		for(UserStatisticsTO userRankingPosition: listOfUsersStatistics){
			if(userRankingPosition.getId().equals(id)){
				return listOfUsersStatistics.indexOf(userRankingPosition);
			}
		}
		
		return 0;
	}
}
