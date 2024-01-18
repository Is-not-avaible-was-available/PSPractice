package com.scaler.PSPractice;

import com.scaler.PSPractice.Repositories.CategoryRepository;
import com.scaler.PSPractice.models.Category;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PsPracticeApplication implements CommandLineRunner {
	private CategoryRepository categoryRepository;
	public PsPracticeApplication(CategoryRepository categoryRepository){
		this.categoryRepository = categoryRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(PsPracticeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category category = new Category("Electronics");
		categoryRepository.save(category);
		Category category1 = new Category("Fashion");
		categoryRepository.save(category1);
		Category category2 = new Category("Sports");
		categoryRepository.save(category2);
		Category category3 = new Category("Foot-wear");
		categoryRepository.save(category3);
		Category category4 = new Category("Underwear");
		categoryRepository.save(category4);
		Category category5 = new Category("Casual wear");
		categoryRepository.save(category5);
		Category category6 = new Category("PartyWear");
		categoryRepository.save(category6);
		Category category7 = new Category("Foods");
		categoryRepository.save(category7);
		Category category8 = new Category("Accessories");
		categoryRepository.save(category8);
		Category category9 = new Category("Office wear");
		categoryRepository.save(category9);

	}
}
