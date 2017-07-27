package com.capgemini.chess.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.exception.UserValidationException;
import com.capgemini.chess.service.CreationRankingService;
import com.capgemini.chess.service.ReadService;
import com.capgemini.chess.service.to.RankingTO;
import com.capgemini.chess.service.to.UserStatisticsTO;

@Service
public class CreationRankingServiceImpl implements CreationRankingService{
	
	private ReadService readService;	
	
	@Autowired
	public CreationRankingServiceImpl(ReadService readService) {
		this.readService = readService;
	}

	public RankingTO create(Long id) throws UserValidationException{
		RankingTO ranking = new RankingTO();
		List<UserStatisticsTO> listOfUserStatistics = readService.readRanking();
		ranking.setListOfUserStatistics(sort(listOfUserStatistics));
		int index = getUserPossition(listOfUserStatistics, id);
		ranking.setUserRankingPosition(index+1);
		ranking.setUserLevel(listOfUserStatistics.get(index).getLevel());
		
		return ranking;
	}
	
	private int getUserPossition(List<UserStatisticsTO> listOfUserStatistics, Long id) throws UserValidationException{
		for(UserStatisticsTO userRankingPosition: listOfUserStatistics){
			if(userRankingPosition.getId().equals(id)){
				return listOfUserStatistics.indexOf(userRankingPosition);
			}
		}
		
		throw new UserValidationException("User does not exist on the list!");
	}
	
	private List<UserStatisticsTO> sort(List<UserStatisticsTO> listOfUserStatistics){
		Collections.sort(listOfUserStatistics, Comparator.comparingInt(UserStatisticsTO::getPoints).reversed());
		return listOfUserStatistics;
	}

}
