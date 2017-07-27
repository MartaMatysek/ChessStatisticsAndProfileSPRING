package com.capgemini.chess.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.exception.UserValidationException;
import com.capgemini.chess.service.CreationRankingService;
import com.capgemini.chess.service.RankingService;
import com.capgemini.chess.service.ValidationUserService;
import com.capgemini.chess.service.to.RankingTO;

@Service
public class RankingServiceImpl implements RankingService{
	
	@Autowired
	private CreationRankingService createRankingService;
	@Autowired
	private ValidationUserService rankingValidateService;
	
	@Override
	public RankingTO getRanking(Long id) throws UserValidationException{
		rankingValidateService.validate(id);
		return createRankingService.create(id);
	}

}
