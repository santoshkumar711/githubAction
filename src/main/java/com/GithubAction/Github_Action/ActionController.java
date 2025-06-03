package com.GithubAction.Github_Action;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActionController {
@RequestMapping("/test")
public String test()
{
	return " Hello Github Action";
}
}
