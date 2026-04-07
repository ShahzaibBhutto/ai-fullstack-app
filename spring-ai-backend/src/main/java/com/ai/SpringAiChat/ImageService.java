package com.ai.SpringAiChat;

import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
	private final OpenAiImageModel openaiImageModel;

	public ImageService(OpenAiImageModel openaiImageModel) {
		this.openaiImageModel = openaiImageModel;
	}

	public ImageResponse generateImage(String prompt, int numberOfImages) {

		ImageResponse response = openaiImageModel.call(new ImagePrompt(prompt,
				OpenAiImageOptions.builder().model("dall-e-2").N(numberOfImages).height(1024).width(1024).build())

		);

		return response;
	}

}
