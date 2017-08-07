package com.capgemini.chess.service.impl;
import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.chess.exception.MatchValidationException;
import com.capgemini.chess.service.MatchValidationService;
import com.capgemini.chess.service.access.dao.MatchDao;
import com.capgemini.chess.service.to.MatchTO;

public class MatchValidationServiceImpl implements MatchValidationService{
	
	private MatchDao matchDao;
	
	@Autowired
	public MatchValidationServiceImpl(MatchDao matchDao) {
		this.matchDao = matchDao;
	}

	@Override
	public void validate(Long id) throws MatchValidationException{
		findMatchById(id);
	}
	
	private void findMatchById(Long id) throws MatchValidationException{
		MatchTO findMatch = matchDao.findById(id);
		if(findMatch == null){
			throw new MatchValidationException("Match with given ID doesn't exist!");
		}
	}

}
