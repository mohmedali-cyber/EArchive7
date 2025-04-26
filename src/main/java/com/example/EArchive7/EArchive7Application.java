package com.example.EArchive7;

import com.example.EArchive7.model.CompanyArchive;
import com.example.EArchive7.repository.CompanyArchiveRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EArchive7Application {

	public static void main(String[] args) {
		SpringApplication.run(EArchive7Application.class, args);
	}

	@Bean
	CommandLineRunner init(CompanyArchiveRepository repo) {
		return args -> {
			CompanyArchive archive = new CompanyArchive();
			archive.setCompanyName("شركة تجريبية");
			archive.setStatisticalNumber("123456");
			archive.setNationalId("987654321");
			archive.setCommissionername("مفوض التجربة");

			// لو مش عايز ترفع صور حاليًا، خليهم null أو [].
			// archive.setTaxFile(null);

			// حفظ البيانات في Neon
			repo.save(archive);

			System.out.println("تم حفظ شركة تجريبية في Neon ✅");
		};
	}
}
