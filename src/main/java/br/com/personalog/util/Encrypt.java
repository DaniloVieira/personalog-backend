package br.com.personalog.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encrypt {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("B4naninha!"));
	}
}
