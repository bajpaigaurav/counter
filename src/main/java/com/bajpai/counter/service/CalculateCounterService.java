package com.bajpai.counter.service;

import com.bajpai.counter.objects.request.CreateCounterRequest;
import com.bajpai.counter.objects.response.CreateCounterResponse;
import com.bajpai.counter.objects.response.GetCounterResponse;
import com.bajpai.counter.objects.response.GetCounterStatusResponse;

public interface CalculateCounterService {

	public CreateCounterResponse createCounter(CreateCounterRequest request);
	
	public GetCounterResponse getCounter(int uuid);
	
	public GetCounterStatusResponse getStatus(int uuid);
	
}
