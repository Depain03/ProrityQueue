package com.queue.priorityqueue.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class WorkOrder{
	
	private long requestId;

	private LocalDate date;
	
	@JsonIgnore
	private long timeInMillis;
	
	@JsonIgnore
	private double timeInQueue;
	
	
	
	public double getTimeInQueue() {
		return timeInQueue;
	}
	public void setTimeInQueue(double timeInQueue) {
		this.timeInQueue = timeInQueue;
	}
	public long getRequestId() {
		return requestId;
	}
	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}

	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public long getTimeInMillis() {
		return timeInMillis;
	}
	public void setTimeInMillis(long timeInMillis) {
		this.timeInMillis = timeInMillis;
	}

}
