package com.capgemini.chess.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.service.MatchRegisterService;
import com.capgemini.chess.service.MatchSaveService;
import com.capgemini.chess.service.StatisticsUpdateService;
import com.capgemini.chess.service.to.MatchTO;

@Service
public class MatchRegisterServiceImpl implements MatchRegisterService{
	
	private StatisticsUpdateService statisticsUpdate;
	private MatchSaveService matchSave;
	
	@Autowired
	public MatchRegisterServiceImpl(StatisticsUpdateService statisticsUpdate, MatchSaveService matchSave) {
		this.statisticsUpdate = statisticsUpdate;
		this.matchSave = matchSave;
	}

	@Override
	public void register(MatchTO matchTO){
		statisticsUpdate.update(matchTO);
		matchSave.save(matchTO);
	}

}
