package com.capgemini.chess.dataaccess.entities;


import com.capgemini.chess.dataaccess.enums.MatchResult;

public class MatchEntity {

	private Long matchId;
	private Long firstPlayerId;
	private Long secondPlayerId;
	private MatchResult matchResult;

	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}

	public Long getFirstPlayerId() {
		return firstPlayerId;
	}

	public void setFirstPlayerId(Long firstPlayerId) {
		this.firstPlayerId = firstPlayerId;
	}

	public Long getSecondPlayerId() {
		return secondPlayerId;
	}

	public void setSecondPlayerId(Long secondPlayerId) {
		this.secondPlayerId = secondPlayerId;
	}

	public MatchResult getMatchResult() {
		return matchResult;
	}

	public void setMatchResult(MatchResult matchResult) {
		this.matchResult = matchResult;
	}
}
