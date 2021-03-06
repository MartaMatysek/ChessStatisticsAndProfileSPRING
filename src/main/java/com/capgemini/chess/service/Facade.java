package com.capgemini.chess.service;

import com.capgemini.chess.exception.MatchValidationException;
import com.capgemini.chess.exception.UserValidationException;
import com.capgemini.chess.service.to.MatchTO;
import com.capgemini.chess.service.to.RankingTO;
import com.capgemini.chess.service.to.UpdateProfileTO;
import com.capgemini.chess.service.to.UserProfileTO;

public interface Facade {
	
	RankingTO getRanking(Long id) throws UserValidationException;
	
	MatchTO registerMatch(MatchTO matchTO) throws UserValidationException, MatchValidationException;
	
	UserProfileTO updateProfile(UpdateProfileTO updateProfileTO) throws UserValidationException;
}
