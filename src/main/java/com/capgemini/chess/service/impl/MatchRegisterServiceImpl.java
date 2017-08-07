package com.capgemini.chess.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.exception.MatchValidationException;
import com.capgemini.chess.exception.UserValidationException;
import com.capgemini.chess.service.MatchRegisterService;
import com.capgemini.chess.service.MatchSaveService;
import com.capgemini.chess.service.MatchValidationService;
import com.capgemini.chess.service.StatisticsUpdateService;
import com.capgemini.chess.service.UserValidationService;
import com.capgemini.chess.service.to.MatchTO;

@Service
public class MatchRegisterServiceImpl implements MatchRegisterService{
	
	private StatisticsUpdateService statisticsUpdate;
	private MatchSaveService matchSave;
	private UserValidationService userValidation;
	private MatchValidationService matchValidation;
	
	@Autowired
	public MatchRegisterServiceImpl(StatisticsUpdateService statisticsUpdate, MatchSaveService matchSave,
			UserValidationService userValidation, MatchValidationService matchValidation) {
		this.statisticsUpdate = statisticsUpdate;
		this.matchSave = matchSave;
		this.userValidation = userValidation;
		this.matchValidation = matchValidation;
	}


	@Override
	public MatchTO register(MatchTO matchTO) throws UserValidationException, MatchValidationException{
		userValidation.validate(matchTO.getFirstPlayerId());
		userValidation.validate(matchTO.getSecondPlayerId());
		matchValidation.validate(matchTO.getMatchId());
		statisticsUpdate.update(matchTO);
		return matchSave.save(matchTO);
	}

}
