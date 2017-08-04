package com.capgemini.chess.service;

import com.capgemini.chess.service.to.MatchTO;

public interface MatchRegisterService {

	MatchTO register(MatchTO matchTO);
}