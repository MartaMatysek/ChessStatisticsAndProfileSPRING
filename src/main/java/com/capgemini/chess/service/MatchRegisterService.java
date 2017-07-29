package com.capgemini.chess.service;

import com.capgemini.chess.service.to.MatchTO;

public interface MatchRegisterService {

	void register(MatchTO matchTO);
}