package com.tutorialspoint;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import no.webbydebby.HovedVindu;

public class Starter {

	public static void main(String[] args) {

		new AnnotationConfigApplicationContext(TegneprogramConfig.class)
		
		.getBean(HovedVindu.class).startUI();

	}

}
