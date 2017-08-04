package com.capgemini.chess.service;

import com.capgemini.chess.service.to.MatchTO;

public interface MatchSaveService {

	MatchTO save(MatchTO matchTO);
}
