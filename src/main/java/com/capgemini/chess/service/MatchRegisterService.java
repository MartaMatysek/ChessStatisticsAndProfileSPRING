package com.capgemini.chess.service;

import com.capgemini.chess.exception.MatchValidationException;
import com.capgemini.chess.exception.UserValidationException;
import com.capgemini.chess.service.to.MatchTO;

public interface MatchRegisterService {

	MatchTO register(MatchTO matchTO) throws UserValidationException, MatchValidationException;
}