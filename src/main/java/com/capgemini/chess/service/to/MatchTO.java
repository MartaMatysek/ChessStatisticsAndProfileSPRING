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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (firstPlayerId ^ (firstPlayerId >>> 32));
		result = prime * result + (int) (matchId ^ (matchId >>> 32));
		result = prime * result + ((matchResult == null) ? 0 : matchResult.hashCode());
		result = prime * result + (int) (secondPlayerId ^ (secondPlayerId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MatchTO other = (MatchTO) obj;
		if (firstPlayerId != other.firstPlayerId)
			return false;
		if (matchId != other.matchId)
			return false;
		if (matchResult != other.matchResult)
			return false;
		if (secondPlayerId != other.secondPlayerId)
			return false;
		return true;
	}

	
}
