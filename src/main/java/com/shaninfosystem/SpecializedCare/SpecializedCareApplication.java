package com.shaninfosystem.SpecializedCare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(
        setDates = true,
        auditorAwareRef = "AuditorAwareImpl"
)
@EnableCaching
public class SpecializedCareApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpecializedCareApplication.class, args);
	}

}
