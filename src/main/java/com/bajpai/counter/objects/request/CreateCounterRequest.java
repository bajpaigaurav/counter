package com.bajpai.counter.objects.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CreateCounterRequest {

	int step;
	int goal;
	String timeStamp;
}
