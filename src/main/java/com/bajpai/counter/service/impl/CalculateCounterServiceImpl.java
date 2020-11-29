package com.bajpai.counter.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bajpai.counter.constants.StatusResponseEnum;
import com.bajpai.counter.objects.request.CreateCounterRequest;
import com.bajpai.counter.objects.response.CreateCounterResponse;
import com.bajpai.counter.objects.response.GetCounterResponse;
import com.bajpai.counter.objects.response.GetCounterStatusResponse;
import com.bajpai.counter.service.CalculateCounterService;
import com.bajpai.counter.util.TimeOperations;


@Service
public class CalculateCounterServiceImpl implements CalculateCounterService {

	Map<Integer,CreateCounterRequest> counters = new HashMap<Integer,CreateCounterRequest>();
	int uuid = 100;
	private List<Integer> counts;
	
	//@Autowired
	//TimeCustomUtil time;
	
	@Autowired
	TimeOperations timeOperations;
	
	
	@Override
	public CreateCounterResponse createCounter(CreateCounterRequest request) {
		
		// to figure out the creation time and later we can simulate difference between creation and query time.
		request.setTimeStamp(timeOperations.getCurrentTime());
		
		counters.put(uuid, request);
		
		CreateCounterResponse response = new CreateCounterResponse();
		
		response.setTask(uuid);
		
		uuid++;
		
		return response;
	}

	@Override
	public GetCounterResponse getCounter(int uuid) {
		
		GetCounterResponse response = new GetCounterResponse();
		counts = new ArrayList<Integer>();
		if(counters.get(uuid) != null) {
			
			System.out.println("Value:"+counters.get(uuid));
			
			for(int i = counters.get(uuid).getGoal() ; i >= 0;
					i = i - counters.get(uuid).getStep()) {
				counts.add(i);	
			}
		}
		
		String countsAsString = counts.toString()
									  .replace("[", "")
									  .replace("]", "")
									  .replace(", ", ",");
		response.setResult(countsAsString);
		
		return response;
	}

	@Override
	public GetCounterStatusResponse getStatus(int uuid) {
		GetCounterStatusResponse response = new GetCounterStatusResponse();
		boolean isReady = false;
		if(counters.get(uuid) != null){
			isReady = timeOperations.isCounterReady(counters.get(uuid).getTimeStamp(), 10);
			
			if(isReady) {
				response.setResponse(StatusResponseEnum.SUCCESS.toString());
			} else {
				response.setResponse(StatusResponseEnum.IN_PROGRESS.toString());
			}
		} else {
			 response.setResponse(StatusResponseEnum.ERROR.toString());
			 return response;
		}
		
		return response;
	}

}
