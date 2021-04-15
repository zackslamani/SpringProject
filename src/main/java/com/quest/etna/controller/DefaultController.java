package com.quest.etna.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8090", maxAge = 3600)
@RestController
public class DefaultController {

	@RequestMapping({ "/testSuccess" })
	@ResponseStatus(HttpStatus.OK)
	public String testSuccess() {
		return "success";
	}
	
	@RequestMapping({ "/testNotFound" })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String testNotFound() {
		return "not found";
	}
	
	@RequestMapping({ "/testError" })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String testError() {
		return "error";
	}
}