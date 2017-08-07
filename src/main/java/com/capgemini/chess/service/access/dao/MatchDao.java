package com.capgemini.chess.service.access.dao;

import com.capgemini.chess.service.to.MatchTO;

public interface MatchDao {
	
	MatchTO findById(Long id);
	
	MatchTO saveMatch(MatchTO matchTO);
}
