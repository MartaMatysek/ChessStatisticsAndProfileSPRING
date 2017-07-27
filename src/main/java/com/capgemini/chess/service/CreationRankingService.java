package com.capgemini.chess.service;

import com.capgemini.chess.exception.UserValidationException;
import com.capgemini.chess.service.to.RankingTO;

public interface CreationRankingService {

	public RankingTO create(Long id) throws UserValidationException;
}
