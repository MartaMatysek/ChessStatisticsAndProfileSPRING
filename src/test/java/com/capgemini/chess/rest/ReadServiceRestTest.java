package com.capgemini.chess.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

import com.capgemini.chess.service.ReadService;
import com.capgemini.chess.service.to.UserProfileTO;
import com.capgemini.chess.service.to.UserStatisticsTO;

@RunWith(MockitoJUnitRunner.class)
public class ReadServiceRestTest {

	@Mock
	ReadService readService;

	@InjectMocks
	private ReadServiceRest readServiceRest;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(readServiceRest).build();
	}

	@Test
	public void shouldGetUsersByLevel() throws Exception {
		// given
		UserProfileTO user = usersForTest().get(0);

		// when
		when(readService.readUsersByLevelOrWonMatches(2, 0)).thenReturn(Arrays.asList(user));

		// then
		ResultActions response = this.mockMvc.perform(get("/readUsers/2/0").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content("1"));

		response.andExpect(jsonPath("[0].name").value(user.getName()))
				.andExpect(jsonPath("[0].userStatistics.level").value(user.getUserStatistics().getLevel()));

		verify(readService).readUsersByLevelOrWonMatches(2, 0);
	}

	@Test
	public void shouldGetUsersByWonMatches() throws Exception {
		// given
		UserProfileTO user = usersForTest().get(2);

		// when
		when(readService.readUsersByLevelOrWonMatches(0, 20)).thenReturn(Arrays.asList(user));

		// then
		ResultActions response = this.mockMvc.perform(get("/readUsers/0/20").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content("1"));

		response.andExpect(jsonPath("[0].name").value(user.getName()))
				.andExpect(jsonPath("[0].userStatistics.numberOfWonMatches")
						.value(user.getUserStatistics().getNumberOfWonMatches()));

		verify(readService).readUsersByLevelOrWonMatches(0, 20);
	}

	@Test
	public void shouldGetUsersByLevelOrWonMatches() throws Exception {
		// given
		UserProfileTO firstUser = usersForTest().get(0);
		UserProfileTO secondUser = usersForTest().get(1);

		// when
		when(readService.readUsersByLevelOrWonMatches(2, 3)).thenReturn(usersForTest().subList(0, 2));

		// then
		ResultActions response = this.mockMvc.perform(get("/readUsers/2/3").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content("1"));

		response.andExpect(jsonPath("[0].name").value(firstUser.getName()))
				.andExpect(jsonPath("[0].userStatistics.level").value(firstUser.getUserStatistics().getLevel()))
				.andExpect(jsonPath("[1].name").value(secondUser.getName()))
				.andExpect(jsonPath("[1].userStatistics.numberOfWonMatches")
						.value(secondUser.getUserStatistics().getNumberOfWonMatches()));

		verify(readService).readUsersByLevelOrWonMatches(2, 3);
	}

	@Test
	public void shouldGetUsersByName() throws Exception {
		// given
		UserProfileTO user = usersForTest().get(0);

		// when
		when(readService.readUsersByName("Jan")).thenReturn(Arrays.asList(user));

		// then
		ResultActions response = this.mockMvc.perform(get("/readUsers/Jan").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content("1"));

		response.andExpect(status().isOk()).andExpect(jsonPath("[0].id").value(user.getId().intValue()))
				.andExpect(jsonPath("[0].name").value(user.getName()));

		verify(readService).readUsersByName("Jan");
	}

	@Test
	public void shouldNullDuringGetUsersByName() throws Exception {
		// given when
		when(readService.readUsersByName("Jan")).thenReturn(null);

		// then
		ResultActions response = this.mockMvc.perform(get("/readUsers/Jan").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content("1"));

		response.andExpect(status().isOk());
		verify(readService).readUsersByName("Jan");
	}

	private List<UserProfileTO> usersForTest() {
		List<UserProfileTO> userProfiles = new ArrayList<>();

		UserStatisticsTO firstStatUser = new UserStatisticsTO();
		firstStatUser.setId(1L);
		firstStatUser.setPoints(20);
		firstStatUser.setLevel(2);
		firstStatUser.setNumberOfWonMatches(4);
		firstStatUser.setNumberOfLostMatches(1);
		firstStatUser.setNumberOfDrawMatches(2);

		UserProfileTO firstUser = new UserProfileTO();
		firstUser.setId(1L);
		firstUser.setLogin("janeczek");
		firstUser.setPassword("jan123");
		firstUser.setName("Jan");
		firstUser.setSurname("Kowalski");
		firstUser.setEmail("j@wp.pl");
		firstUser.setAboutMe("I am who I am.");
		firstUser.setLifeMotto("Carpie Diem");
		firstUser.setUserStatistics(firstStatUser);

		UserStatisticsTO secondUserStat = new UserStatisticsTO();
		secondUserStat.setId(2L);
		secondUserStat.setPoints(5);
		secondUserStat.setLevel(1);
		secondUserStat.setNumberOfWonMatches(3);
		secondUserStat.setNumberOfLostMatches(5);
		secondUserStat.setNumberOfDrawMatches(7);

		UserProfileTO secondUser = new UserProfileTO();
		secondUser.setId(2L);
		secondUser.setLogin("marecki");
		secondUser.setPassword("ma10nie11");
		secondUser.setName("Marek");
		secondUser.setSurname("Abacki");
		secondUser.setAboutMe("I like dog.");
		secondUser.setLifeMotto("Life if easy.");
		secondUser.setUserStatistics(secondUserStat);

		UserStatisticsTO thirdUserStat = new UserStatisticsTO();
		thirdUserStat.setId(3L);
		thirdUserStat.setPoints(55);
		thirdUserStat.setLevel(3);
		thirdUserStat.setNumberOfWonMatches(20);
		thirdUserStat.setNumberOfLostMatches(9);
		thirdUserStat.setNumberOfDrawMatches(1);

		UserProfileTO thirdUser = new UserProfileTO();
		thirdUser.setId(3L);
		thirdUser.setLogin("mis");
		thirdUser.setPassword("haslo123");
		thirdUser.setName("Michał");
		thirdUser.setSurname("Michals");
		thirdUser.setAboutMe("Smile.");
		thirdUser.setLifeMotto("Chess");
		thirdUser.setUserStatistics(thirdUserStat);

		userProfiles.add(firstUser);
		userProfiles.add(secondUser);
		userProfiles.add(thirdUser);
		return userProfiles;
	}

}
