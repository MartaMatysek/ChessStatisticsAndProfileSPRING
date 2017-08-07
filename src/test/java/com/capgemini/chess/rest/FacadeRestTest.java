package com.capgemini.chess.rest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
import com.capgemini.chess.service.to.UpdateProfileTO;
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

		// when
		when(facade.getRanking(1L)).thenReturn(ranking);

		// then
		ResultActions response = this.mockMvc.perform(get("/readRanking/1").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content("1"));

		response.andExpect(status().isOk())
				.andExpect(jsonPath("userRankingPosition").value(ranking.getUserRankingPosition()))
				.andExpect(jsonPath("userLevel").value(ranking.getUserLevel()));

		verify(facade).getRanking(1L);
	}

	@Test(expected = Exception.class)
	public void shouldThrowExceptionDuringGetUserRanking() throws Exception {
		// given when
		when(facade.getRanking(1L)).thenThrow(new Exception("User with given ID does not exsist!"));

		// then
		ResultActions response = this.mockMvc.perform(get("/readRanking/1").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content("1"));

		assertEquals(response, new Exception());
		verify(facade).getRanking(1L);
	}

	@Test
	public void shouldSaveMatch() throws Exception {
		// given
		MatchTO match = new MatchTO();
		match.setMatchId(1L);
		match.setFirstPlayerId(1L);
		match.setSecondPlayerId(2L);
		match.setMatchResult(MatchResult.LOST);
		String matchJson = new ObjectMapper().writeValueAsString(match);
		
		// when
		when(facade.registerMatch(match)).thenReturn(match);

		//then
		ResultActions response = this.mockMvc.perform(post("/saveMatch").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(matchJson.getBytes()));

		response.andExpect(status().isOk()).andExpect(jsonPath("matchId").value(match.getMatchId().intValue()));
		verify(facade).registerMatch(match);
	}

	@Test(expected = Exception.class)
	public void shouldThrowExceptionDuringSaveMatch() throws Exception {
		// given
		MatchTO match = new MatchTO();
		String matchJson = new ObjectMapper().writeValueAsString(match);
		
		// when
		when(facade.registerMatch(match)).thenReturn(match);

		//then
		ResultActions response = this.mockMvc.perform(post("/saveMatch").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(matchJson.getBytes()));

		assertEquals(response, new Exception());
		verify(facade).registerMatch(match);
	}

	@Test
	public void shouldUpdateProfile() throws Exception {
		// given
		UserProfileTO userAfterUpdate = new UserProfileTO();
		userAfterUpdate.setId(1L);
		userAfterUpdate.setName("Janek");
		userAfterUpdate.setAboutMe("I'm from Poland.");

		UpdateProfileTO userBeforeUpdate = new UpdateProfileTO();
		userBeforeUpdate.setId(1L);
		userBeforeUpdate.setName("Jan");
		userBeforeUpdate.setAboutMe("I'm Janek.");

		String userJson = new ObjectMapper().writeValueAsString(userBeforeUpdate);

		// when
		when(facade.updateProfile(userBeforeUpdate)).thenReturn(userAfterUpdate);

		// then
		ResultActions response = this.mockMvc.perform(post("/updateProfile").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(userJson.getBytes()));

		response.andExpect(status().isOk()).andExpect(jsonPath("id").value(userAfterUpdate.getId().intValue()))
				.andExpect(jsonPath("name").value(userAfterUpdate.getName()))
				.andExpect(jsonPath("aboutMe").value(userAfterUpdate.getAboutMe()));
		verify(facade).updateProfile(userBeforeUpdate);
	}

	@Test(expected = AssertionError.class)
	public void shouldThrowExceptionDuringUpdateProfile() throws Exception {
		// given
		UpdateProfileTO user = new UpdateProfileTO();
		String userJson = new ObjectMapper().writeValueAsString(user);

		// when
		when(facade.updateProfile(user)).thenReturn(null);

		// then
		ResultActions response = this.mockMvc.perform(post("/updateProfile").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(userJson.getBytes()));

		assertEquals(response, new Exception());
		verify(facade).updateProfile(user);
	}

}
