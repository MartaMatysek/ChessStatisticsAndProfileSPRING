package com.capgemini.chess.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.service.RankingCreationService;
import com.capgemini.chess.service.ReadService;
import com.capgemini.chess.service.to.RankingTO;
import com.capgemini.chess.service.to.UserStatisticsTO;

@Service
public class RankingCreationServiceImpl implements RankingCreationService{
	
	private ReadService readService;	

	@Autowired
	public RankingCreationServiceImpl(ReadService readService){
		this.readService = readService;
	}
	
	@Override
	public RankingTO create(long id){
		RankingTO ranking = new RankingTO();
		List<UserStatisticsTO> listOfUsersStatistics = readService.readRanking();
		List<UserStatisticsTO> listOfUsersStatisticsResult = sort(listOfUsersStatistics);
		int index = getUserPossition(listOfUsersStatisticsResult, id);
		ranking.setUserRankingPosition(index+1);
		ranking.setUserLevel(listOfUsersStatisticsResult.get(index).getLevel());
		ranking.setListOfUsersStatistics(listOfUsersStatisticsResult);
		
		return ranking;
	}

	private int getUserPossition(List<UserStatisticsTO> listOfUsersStatistics, long id){
		for(UserStatisticsTO userRankingPosition: listOfUsersStatistics){
			if(userRankingPosition.getId() == id){
				return listOfUsersStatistics.indexOf(userRankingPosition);
			}
		}
		
		return 0;
	}
	
	private List<UserStatisticsTO> sort(List<UserStatisticsTO> listOfUsersStatistics){
		Collections.sort(listOfUsersStatistics, Comparator.comparingInt(UserStatisticsTO::getPoints).reversed());
		return listOfUsersStatistics;
	}
}
