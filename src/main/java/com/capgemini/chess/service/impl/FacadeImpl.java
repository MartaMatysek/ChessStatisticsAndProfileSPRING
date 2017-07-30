package com.capgemini.chess.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.exception.UserValidationException;
import com.capgemini.chess.service.Facade;
import com.capgemini.chess.service.MatchRegisterService;
import com.capgemini.chess.service.RankingService;
import com.capgemini.chess.service.UserUpdateProfileService;
import com.capgemini.chess.service.to.MatchTO;
import com.capgemini.chess.service.to.RankingTO;
import com.capgemini.chess.service.to.UserProfileTO;

@Service
public class FacadeImpl implements Facade {
	
	private RankingService ranking;
	private MatchRegisterService matchRegister;
	private UserUpdateProfileService userUpdateProfile;

	@Autowired
	public FacadeImpl(RankingService ranking, MatchRegisterService matchRegister,
			UserUpdateProfileService userUpdateProfile) {
		this.ranking = ranking;
		this.matchRegister = matchRegister;
		this.userUpdateProfile = userUpdateProfile;
	}

	@Override
	public RankingTO getRanking(Long id) throws UserValidationException {
		return ranking.getRanking(id);
	}
	
	@Override
	public void registerMatch(MatchTO matchTO){
		matchRegister.register(matchTO);
	}
	
	@Override
	public UserProfileTO updateProfile(UserProfileTO userProfileTO) throws UserValidationException {
		return userUpdateProfile.update(userProfileTO);
	}

}
