package com.capgemini.chess.service.access.dao;

import com.capgemini.chess.service.to.MatchTO;

public interface MatchDao {
	
	void saveMatch(MatchTO matchTO);
}
