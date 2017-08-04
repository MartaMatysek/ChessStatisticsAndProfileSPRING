package com.capgemini.chess.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.chess.dataaccess.dao.impl.MapUserDaoImpl;
import com.capgemini.chess.service.access.dao.UserDao;
import com.capgemini.chess.service.impl.FacadeImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class FacadeTest {

	@Autowired
	Facade serviceFacade;

	@Autowired
	UserDao userDao;

	@Configuration
		static class RankServiceTestContextConfiguration {
			@Bean
			public Facade userService() {
				return new FacadeImpl();
			}
			
			@Bean
			public UserDao userDao() {
				return new MapUserDaoImpl();
			}
			
	/*		@Bean
			public UserUpdateService userUpdateService() {
				return new UserUpdateServiceImpl();
			}
			
			@Bean
			public UserReadService userReadService() {
				return new UserReadServiceImpl();
			}
			@Bean
			public UserPasswordService userDataValidator() {
				return new UserPasswordServiceImpl();
			}
		}
*/
	/*	@Test
		public void shouldReadUser() throws Exception {

			// when
			UserTO userTO = serviceFacade.readUser(1L);
			
			// than
			assertNotNull(userTO);
		}*/
		
}
}
