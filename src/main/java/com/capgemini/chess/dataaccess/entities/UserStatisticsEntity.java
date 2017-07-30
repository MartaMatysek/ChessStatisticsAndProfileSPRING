package com.capgemini.chess.dataaccess.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserStatisticsEntity {

	@Id
	private Long id;
	private int points;
	private int level;
	private int numberOfWonMatches;
	private int numberOfLostMatches;
	private int numberOfDrawMatches;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getNumberOfWonMatches() {
		return numberOfWonMatches;
	}

	public void setNumberOfWonMatches(int numberOfWonMatches) {
		this.numberOfWonMatches = numberOfWonMatches;
	}

	public int getNumberOfLostMatches() {
		return numberOfLostMatches;
	}

	public void setNumberOfLostMatches(int numberOfLostMatches) {
		this.numberOfLostMatches = numberOfLostMatches;
	}

	public int getNumberOfDrawMatches() {
		return numberOfDrawMatches;
	}

	public void setNumberOfDrawMatches(int numberOfDrawMatches) {
		this.numberOfDrawMatches = numberOfDrawMatches;
	}
}
