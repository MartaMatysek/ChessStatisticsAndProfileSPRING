package com.capgemini.chess.service;

import com.capgemini.chess.exception.UserValidationException;
import com.capgemini.chess.service.to.RankingTO;

public interface Facade {
	
	RankingTO getRanking(Long id) throws UserValidationException;
}
