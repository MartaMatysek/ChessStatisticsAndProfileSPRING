package com.capgemini.chess.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.exception.UserValidationException;
import com.capgemini.chess.service.RankingCreationService;
import com.capgemini.chess.service.RankingService;
import com.capgemini.chess.service.UserValidationService;
import com.capgemini.chess.service.to.RankingTO;

@Service
public class RankingServiceImpl implements RankingService{
	
	private UserValidationService userIdValidation;
	private RankingCreationService rankingCreation;
	
	@Autowired
	public RankingServiceImpl(UserValidationService userIdValidation, RankingCreationService rankingCreation) {
		this.userIdValidation = userIdValidation;
		this.rankingCreation = rankingCreation;
	}

	@Override
	public RankingTO getRanking(Long id) throws UserValidationException{
		userIdValidation.validate(id);
		return rankingCreation.create(id);
	}

}
