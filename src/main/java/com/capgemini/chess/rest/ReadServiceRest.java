package com.capgemini.chess.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.capgemini.chess.exception.UserValidationException;
import com.capgemini.chess.service.ReadService;
import com.capgemini.chess.service.to.UserProfileTO;

@Controller
@ResponseBody
public class ReadServiceRest {

	@Autowired
	ReadService readService;
	
	@RequestMapping(value = "/readUsers/{level}/{wonMatches}", method= RequestMethod.GET)
	public List<UserProfileTO> readUsersByLevelOrWonMatches(@PathVariable("level") int level,
			@PathVariable("wonMatches") int wonMatches) throws UserValidationException {
		return readService.readUsersByLevelOrWonMatches(level, wonMatches);
	}

	@RequestMapping(value = "/readUsers/{name}", method= RequestMethod.GET)
	public List<UserProfileTO> readUsersByName(@PathVariable("name") String name) throws UserValidationException {
		return readService.readUsersByName(name);
	}
}
