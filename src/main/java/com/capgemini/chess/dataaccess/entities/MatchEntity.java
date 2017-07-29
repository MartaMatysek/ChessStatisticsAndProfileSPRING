package com.capgemini.chess.dataaccess.entities;

import com.capgemini.chess.dataaccess.enums.MatchResult;

public class MatchEntity {

	private Long matchId;
	private Long FirstPlayerId;
	private Long SecondPlayerId;
	private MatchResult matchResult;
	
	public Long getMatchId() {
		return matchId;
	}
	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}
	public Long getFirstPlayerId() {
		return FirstPlayerId;
	}
	public void setFirstPlayerId(Long firstPlayerId) {
		FirstPlayerId = firstPlayerId;
	}
	public Long getSecondPlayerId() {
		return SecondPlayerId;
	}
	public void setSecondPlayerId(Long secondPlayerId) {
		SecondPlayerId = secondPlayerId;
	}
	public MatchResult getMatchResult() {
		return matchResult;
	}
	public void setMatchResult(MatchResult matchResult) {
		this.matchResult = matchResult;
	}
}
