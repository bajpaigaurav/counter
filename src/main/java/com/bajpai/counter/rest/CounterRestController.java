package com.bajpai.counter.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bajpai.counter.objects.request.CreateCounterRequest;
import com.bajpai.counter.objects.response.CreateCounterResponse;
import com.bajpai.counter.objects.response.GetCounterResponse;
import com.bajpai.counter.objects.response.GetCounterStatusResponse;
import com.bajpai.counter.service.CalculateCounterService;
import com.bajpai.counter.util.TimeOperations;

@RestController
public class CounterRestController {

	@Autowired
	CalculateCounterService counterService;
	
	@GetMapping(path = "/hello")
	public void helloWorld() {
		System.out.println("Hello!!");
	}
	
	@PostMapping(path = "api/generate")
	@ResponseStatus(HttpStatus.CREATED)
	public CreateCounterResponse createCounter(@RequestBody CreateCounterRequest request) {
		
		CreateCounterResponse response = counterService.createCounter(request);
		
		return response;
	}
	
	@GetMapping(path = "/api/tasks/{uuid}")
	public GetCounterResponse getCounterValue(@PathVariable int uuid, @RequestParam(name="action") String action) {
		
		System.out.println("Action triggered :"+action);
		
		GetCounterResponse response = counterService.getCounter(uuid);
		
		return response;
	}
	
	@GetMapping(path="/api/tasks/{uuid}/status")
	public GetCounterStatusResponse getCounterStatus(@PathVariable int uuid) {
		return counterService.getStatus(uuid);
	}
}
