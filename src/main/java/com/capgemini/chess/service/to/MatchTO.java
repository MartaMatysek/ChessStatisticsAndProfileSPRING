package com.capgemini.chess.service.to;

import com.capgemini.chess.dataaccess.enums.MatchResult;

public class MatchTO {

	private long matchId;
	private long firstPlayerId;
	private long secondPlayerId;
	private MatchResult matchResult;

	public long getMatchId() {
		return matchId;
	}

	public void setMatchId(long matchId) {
		this.matchId = matchId;
	}

	public long getFirstPlayerId() {
		return firstPlayerId;
	}

	public void setFirstPlayerId(long firstPlayerId) {
		this.firstPlayerId = firstPlayerId;
	}

	public long getSecondPlayerId() {
		return secondPlayerId;
	}

	public void setSecondPlayerId(long secondPlayerId) {
		this.secondPlayerId = secondPlayerId;
	}

	public MatchResult getMatchResult() {
		return matchResult;
	}

	public void setMatchResult(MatchResult matchResult) {
		this.matchResult = matchResult;
	}
}
