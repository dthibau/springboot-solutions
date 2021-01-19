package org.formation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Profile({"prod","integration"})
public class HelloController {
	@Autowired
	HelloProperties props;

	@Value("${app.random}")
	String uuid;

	@RequestMapping("/hello")
	public String hello(@RequestParam String name) {
		switch (props.getCaseType()) {
		case LOWER:
			name = name.toLowerCase();
			break;
		case UPPER:
			name = name.toUpperCase();
			break;
		default:
			break;
		}
		if (props.getPosition() == 0)
			return props.getGreeting() + name;
		else
			return name + props.getGreeting();
	}

	@RequestMapping("/uuid")
	public String uuid() {
		return uuid;
	}
}
