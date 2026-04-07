package com.ai.SpringAiChat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class GenAIController {
	private final ChatService chatService;
	private final ImageService imageService;
	private final RecipeService recipeService;

	public GenAIController(ChatService chatService, ImageService imageService, RecipeService recipeService) {
		this.chatService = chatService;
		this.imageService = imageService;
		this.recipeService = recipeService;
	}

	@GetMapping("ask-ai")
	public String getResponse(@RequestParam String prompt) {
		return chatService.getResponse(prompt);
	}

	@GetMapping("ask-ai-options")
	public String getResponseOptions(@RequestParam String prompt) {
		return chatService.getResponseOptions(prompt);
	}

	@SuppressWarnings("finally")
	@GetMapping("generate-multiple-images")
	public List<String> generateMultipleImages(HttpServletResponse response, @RequestParam String prompt,
			@RequestParam(defaultValue = "1") int numberOfImages) {
		List<String> imageUrls = new ArrayList<String>();
		try {
			imageUrls = imageService.generateImage(prompt, numberOfImages).getResults().stream()
					.map(result -> result.getOutput().getUrl()).toList();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return imageUrls;
		}
	}

	@GetMapping("recipe-creator")
	public String generateRecipe(@RequestParam String ingredients, @RequestParam(defaultValue = "any") String cuisine,
			@RequestParam(defaultValue = "") String dietaryRestrictions) {
		return recipeService.createRecipe(ingredients, cuisine, dietaryRestrictions);
	}

}