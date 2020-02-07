package com.fb.fbservice.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fb.fbservice.connectors.RestClientIntegration;

@RestController
@RequestMapping("/match")
public class FootballController {
	@Autowired
	private Environment environment;

	@Autowired
	RestClientIntegration restClient;

	@RequestMapping(value = "/getStanding", method = RequestMethod.GET)
	public ResponseEntity<String> getStandings(@RequestParam(value = "league", defaultValue = "148") String league)
			throws IOException {
		String result = restClient.getResponse(StandingUrl() + league + getFootballApiKey());
		return ResponseEntity.ok(result);
	}

	@RequestMapping(value = "/getbyCountries", method = RequestMethod.GET)
	public ResponseEntity<String> getCountries() throws IOException {
		String result = restClient.getResponse(CountryUrl() + getFootballApiKey());
		return ResponseEntity.ok(result);
	}

	@RequestMapping(value = "/getbyLeagues", method = RequestMethod.GET)
	public ResponseEntity<String> getLeagues(@RequestParam(value = "country", defaultValue = "41") String country)
			throws IOException {
		String result = restClient.getResponse(LeagueUrl() + country + getFootballApiKey());
		return ResponseEntity.ok(result);
	}

	public String getFootballApiKey() {
		return environment.getProperty("football.api.key");
	}

	public String StandingUrl() {
		return environment.getProperty("standings.endpoint.url");
	}

	public String CountryUrl() {
		return environment.getProperty("country.endpoint.url");
	}

	public String LeagueUrl() {
		return environment.getProperty("league.endpoint.url");
	}
}
