package com.covidpersona.config;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PasswordGenerator {
	@Bean
	public String generatePassword() {
		String upperCaseLetters = RandomStringUtils.random(2, 65, 90, true, true);
		String lowerCaseLetters = RandomStringUtils.random(2, 97, 122, true, true);
		String numbers = RandomStringUtils.randomNumeric(2);
		String specialChar = RandomStringUtils.random(2, 33, 47, false, false);

		String combinedChars = upperCaseLetters.concat(lowerCaseLetters).concat(numbers).concat(specialChar);
		List<Character> pwChars = combinedChars.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
		Collections.shuffle(pwChars);

		return pwChars.stream().collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
	}
}
