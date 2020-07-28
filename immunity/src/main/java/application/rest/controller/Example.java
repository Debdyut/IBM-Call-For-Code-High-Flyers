package application.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import application.rest.bins.Hook;
import application.rest.bins.HookResponse;
import application.rest.bins.Immunity;
import application.rest.bins.ImmunityCheck;
import application.rest.helper.CfcHelper;

@RestController
@RequestMapping("/")
public class Example {

	@Autowired
	CfcHelper cfcHelper;

	@GetMapping(path = "/health")
	public @ResponseBody ResponseEntity<String> example() {
		String response = "Congratulations, your application is up and running";
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@PostMapping(path = "/getScore", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	private <T> ResponseEntity<T> getImmunityScore(@RequestBody List<Immunity> questions) {

		Integer score = cfcHelper.getScore(questions);
		if (score != null) {
			return (ResponseEntity<T>) new ResponseEntity<>(score, HttpStatus.OK);
		}
		return (ResponseEntity<T>) new ResponseEntity<>("Opps, Failed to get Score", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping(path = "/checkWebhook", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	private <T> ResponseEntity<T> checkWebhook(@RequestBody ImmunityCheck questions) {
		Integer score = cfcHelper.getScoreNew(questions);
		HookResponse hookResponse = new HookResponse();
		if (score != null && score > 0) {
			hookResponse.setScore(String.valueOf(score));
			if (score >= 90) {
				hookResponse.setStatus(" <p style='color:#228B22'> You have Very Good Immunity</p>\n");
			} else if (score >= 80) {
				hookResponse.setStatus(" <p style='color:#33cc33'>You have  Good Immunity</p>\n");
			} else if (score >= 65) {
				hookResponse.setStatus(" <p style='color:#ffff80'>You have  modarate Immunity</p>\n");
			} else if (score >= 55) {
				hookResponse.setStatus(" <p style='color:#ff3385'> You have  poor Immunity</p>\n");
			} else if (score < 55) {
				hookResponse.setStatus(" <p style='color:#ff0000'>You have very poor Immunity</p>\n");
			}
			hookResponse.setSuggetion(getSuggetions());

		} else {
			hookResponse.setScore("Oops failed to generate score");
		}
		if (score != null) {
			return (ResponseEntity<T>) new ResponseEntity<>(hookResponse, HttpStatus.OK);
		}
		return (ResponseEntity<T>) new ResponseEntity<>("Opps, Failed to get Score", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping(path = "/getImmunityScore", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	private <T> ResponseEntity<T> getImmunityScore(@RequestBody ImmunityCheck questions) {

		Integer score = cfcHelper.getScoreNew(questions);
		HookResponse hookResponse = new HookResponse();
		if (score != null && score > 0) {
			hookResponse.setScore(String.valueOf(score));
			if (score >= 90) {
				hookResponse.setStatus(" You have Very Good Immunity\n");
			} else if (score >= 80) {
				hookResponse.setStatus(" You have  Good Immunity\n");
			} else if (score >= 65) {
				hookResponse.setStatus(" You have  modarate Immunity\n");
			} else if (score >= 55) {
				hookResponse.setStatus(" You have  poor Immunity\n");
			} else if (score < 55) {
				hookResponse.setStatus(" You have very poor Immunity\n");
			}
			hookResponse.setSuggetion(getSuggetion());

		} else {
			hookResponse.setScore("Oops failed to generate score");
		}
		if (score != null) {
			return (ResponseEntity<T>) new ResponseEntity<>(hookResponse, HttpStatus.OK);
		}
		return (ResponseEntity<T>) new ResponseEntity<>("Opps, Failed to get Score", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public String getSuggetion() {
		String suggetion = "\nBoost your immunity by taking natural ayurvedic herbs mentioned below which are immunomodulators, antioxidant scavenging free radicals and remove toxins in the body.\n"
				+ "Strengthen your immune system by adding food rich in Vitamin - A, B, C, E, D, Iron, Zinc like Nuts, seeds, whole grains, legumes, leafy vegetables, yellow and orange vegetables like pumpkin and carrots, cereals, milk, oranges, lemons, limes, berries, kiwi, broccoli, tomatoes, capsicum, etc,.\n"
				+ "Focus on eating a variety of foods within each of the basic food groups to boost your intake of vitamins and minerals\n"
				+ "Eat seasonal fruits and veggies which are available in your locality\n"
				+ "Avoid processed and re-heated food\n"
				+ "Avoid over-the-counter drugs like painkiller, antacid, paracetamol, antibiotics, etc.\n"
				+ "Perform moderate intensity exercise like brisk walking, yoga asanas\n"
				+ "Pranayama and meditation to improve your mind and lung's health\n"
				+ "Get enough sleep: to maintain immune system homeostasis\n"
				+ "Ditch bad habits of smoking/drinking too much alcohol\n" + "Maintain good personal hygiene";
		return suggetion;
	}
	
	public String getSuggetions() {
		String suggetion = "<li>Boost your immunity by taking natural ayurvedic herbs mentioned below which are immunomodulators, antioxidant scavenging free radicals and remove toxins in the body.\n"
				+ "<li>Strengthen your immune system by adding food rich in Vitamin - A, B, C, E, D, Iron, Zinc like Nuts, seeds, whole grains, legumes, leafy vegetables, yellow and orange vegetables like pumpkin and carrots, cereals, milk, oranges, lemons, limes, berries, kiwi, broccoli, tomatoes, capsicum, etc,.\n"
				+ "<li>Focus on eating a variety of foods within each of the basic food groups to boost your intake of vitamins and minerals\n"
				+ "<li>Eat seasonal fruits and veggies which are available in your locality\n"
				+ "<li>Avoid processed and re-heated food\n"
				+ "<li>Avoid over-the-counter drugs like painkiller, antacid, paracetamol, antibiotics, etc.\n"
				+ "<li>Perform moderate intensity exercise like brisk walking, yoga asanas\n"
				+ "<li>Pranayama and meditation to improve your mind and lung's health\n"
				+ "<li>Get enough sleep: to maintain immune system homeostasis\n"
				+ "<li>Ditch bad habits of smoking/drinking too much alcohol\n" 
				+ "<li>Maintain good personal hygiene";
		return suggetion;
	}

}
