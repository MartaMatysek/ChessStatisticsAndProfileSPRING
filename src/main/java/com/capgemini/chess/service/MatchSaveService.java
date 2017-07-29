package com.capgemini.chess.service;

import com.capgemini.chess.service.to.MatchTO;

public interface MatchSaveService {

	void save(MatchTO matchTO);
}
