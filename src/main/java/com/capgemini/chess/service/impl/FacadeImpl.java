package com.capgemini.chess.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.exception.UserValidationException;
import com.capgemini.chess.service.Facade;
import com.capgemini.chess.service.to.RankingTO;

@Service
public class FacadeImpl implements Facade {
	
	private RankingServiceImpl rankingService;

	@Autowired
	public FacadeImpl(RankingServiceImpl rankingService) {
		this.rankingService = rankingService;
	}

	@Override
	public RankingTO getRanking(Long id) throws UserValidationException {
		return rankingService.getRanking(id);
	}

}
