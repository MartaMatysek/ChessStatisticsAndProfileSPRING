package com.capgemini.chess.rest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.capgemini.chess.dataaccess.enums.MatchResult;
import com.capgemini.chess.service.Facade;
import com.capgemini.chess.service.to.MatchTO;
import com.capgemini.chess.service.to.RankingTO;
import com.capgemini.chess.service.to.UserProfileTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class FacadeRestTest {

	@Mock
	Facade facade;

	@InjectMocks
	private FacadeRest chessRestService;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(chessRestService).build();
	}

	@Test
	public void shouldGetUserRanking() throws Exception {
		// given
		RankingTO ranking = new RankingTO();
		ranking.setUserLevel(2);
		ranking.setUserRankingPosition(2);
		ranking.setListOfUsersStatistics(null);

		// when
		when(facade.getRanking(1L)).thenReturn(ranking);

		// then
		ResultActions response = this.mockMvc.perform(get("/readRanking?id=1").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content("1"));

		response.andExpect(status().isOk());
		response.andExpect(jsonPath("userRankingPosition").value(ranking.getUserRankingPosition()));
		response.andExpect(jsonPath("userLevel").value(ranking.getUserLevel()));

	}

	@Test(expected = Exception.class)
	public void shouldThrowExceptionDuringGetUserRanking() throws Exception {
		// given
		RankingTO ranking = new RankingTO();
		ranking.setUserLevel(2);
		ranking.setUserRankingPosition(2);
		ranking.setListOfUsersStatistics(null);

		// when
		when(facade.getRanking(1L)).thenThrow(new Exception("User with given ID does not exsist!"));

		// then
		ResultActions response = this.mockMvc.perform(get("/readRanking?id=1").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content("1"));

		assertEquals(response, new Exception());
	}

	@Test
	public void shouldSaveMatch() throws Exception {
		// given
		MatchTO match = new MatchTO();
		match.setMatchId(1);
		match.setFirstPlayerId(1);
		match.setSecondPlayerId(2);
		match.setMatchResult(MatchResult.LOST);
		String matchJson = "{\"matchId\": 1, \"firstPlayerId\": 1, \"secondPlayerId\": 2, \"matchResult\": \"LOST\"}";

		// when
		when(facade.registerMatch(match)).thenReturn(match);

		// given
		ResultActions response = this.mockMvc.perform(post("/saveMatch").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(matchJson.getBytes()));

		response.andExpect(status().isOk());
		response.andExpect(jsonPath("matchId").value((int) match.getMatchId()));
	}

	@Test(expected = AssertionError.class)
	public void shouldThrowExceptionDuringSaveMatch() throws Exception {
		// given
		MatchTO match = new MatchTO();
		String matchJson = "{\"matchId\": 1, \"firstPlayerId\": 1, \"secondPlayerId\": 2, \"matchResult\": \"LOST\"}";

		// when
		when(facade.registerMatch(match)).thenReturn(match);

		// given
		ResultActions response = this.mockMvc.perform(post("/saveMatch").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(matchJson.getBytes()));

		assertEquals(response, new AssertionError());
	}

	@Test
	public void shouldUpdateProfile() throws Exception {
		// given		
		UserProfileTO userAfterUpdate = new UserProfileTO();
		userAfterUpdate.setId(1);
		userAfterUpdate.setName("Janek");
		userAfterUpdate.setAboutMe("I'm from Poland.");

		UserProfileTO userBeforeUpdate = new UserProfileTO();
		userBeforeUpdate.setId(1);
		userBeforeUpdate.setName("Jan");
		userBeforeUpdate.setAboutMe("I'm Janek.");
		
		String userJson = new ObjectMapper().writeValueAsString(userBeforeUpdate);

		// when
		when(facade.updateProfile(Mockito.any())).thenReturn(userAfterUpdate);

		// then
		ResultActions response = this.mockMvc.perform(post("/updateProfile").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(userJson.getBytes()));

		response.andExpect(status().isOk());
		response.andExpect(jsonPath("id").value((int)userAfterUpdate.getId()));
		response.andExpect(jsonPath("name").value(userAfterUpdate.getName()));
		response.andExpect(jsonPath("aboutMe").value(userAfterUpdate.getAboutMe()));
	}
	
	@Test(expected = AssertionError.class)
	public void shouldThrowExceptionDuringUpdateProfile() throws Exception{
		//given 
		UserProfileTO user = new UserProfileTO();
		String userJson = new ObjectMapper().writeValueAsString(user);
		
		//when
		when(facade.updateProfile(user)).thenReturn(null);
		
		//then
		ResultActions response = this.mockMvc.perform(post("/updateProfile").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(userJson.getBytes()));

		assertEquals(response, new Exception());
	}

}
