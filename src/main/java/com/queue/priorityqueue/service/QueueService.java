package com.queue.priorityqueue.service;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import org.springframework.stereotype.Service;

import com.queue.priorityqueue.dto.IndexResponse;
import com.queue.priorityqueue.dto.MeanResponse;
import com.queue.priorityqueue.dto.WorkOrder;
import com.queue.priorityqueue.exceptions.ResourceAlreadyExistException;
import com.queue.priorityqueue.exceptions.ResourceNotFoundException;
import com.queue.priorityqueue.util.Utility;

@Service
public class QueueService {


	Queue<WorkOrder> pQueue = new LinkedList<>();

	public void setData(WorkOrder request) {
		long requestId = request.getRequestId();
		if (requestId>0) {
			Date date = new Date();
			long timeInMillis = date.getTime();
			request.setTimeInMillis(timeInMillis);
			Iterator<WorkOrder> it = pQueue.iterator();
			while (it.hasNext()) {
				WorkOrder data = it.next();
				long id = data.getRequestId();
				if (id == requestId) {
					throw new ResourceAlreadyExistException("ID : " + requestId + " already exist");
				}
			}
			pQueue.add(request);
		}
		else {
			throw new RuntimeException("Ivalid Request ID");
		}
	
	}

	public Queue<WorkOrder> getWorkOrder() {
		pQueue=Utility.sortQueue(pQueue);
		return pQueue;
	}

	public void deleteTop() {
		if (!pQueue.isEmpty()) {
			pQueue=Utility.sortQueue(pQueue);
			WorkOrder topData = pQueue.peek();
			pQueue.remove(topData);
		}
		else {
			throw new RuntimeException("Queue is Empty");
		}
	}

	public void deleteID(long requestID) {
		boolean flag=false;
		Iterator<WorkOrder> it = pQueue.iterator();
		WorkOrder data;
		while(it.hasNext()) {
			data=it.next();
			long id = data.getRequestId();
			if (id == requestID) {
				flag=true;
				pQueue.remove(data);
				break;
			}
		}
		if (!flag) {
			throw new ResourceNotFoundException("ID : " + requestID + " doesn't exist");
		}
	}
	
	
	public IndexResponse getWorkOrderIndex(long requestId) {
		pQueue=Utility.sortQueue(pQueue);
		Iterator<WorkOrder> it=pQueue.iterator();
		long count=0;
		boolean flag=false;
		while(it.hasNext()) {
			WorkOrder data=it.next();
			long id=data.getRequestId();
			if (id == requestId) {
				flag=true;
				break;
			}
			else {
				count++;
			}
		}
		if (flag) {
			IndexResponse response=new IndexResponse();
			response.setRequestId(requestId);
			response.setIndexNumber(count);
			return response;
		}else {
			throw new ResourceNotFoundException("ID : " + requestId + " doesn't exist");
		}
	}

	public Queue<MeanResponse> getWaitTime(){
		Queue<MeanResponse> list=new LinkedList<>();
		Date date = new Date();
		long currentTime = date.getTime();
		if (!pQueue.isEmpty()) {
			pQueue=Utility.sortQueue(pQueue);
			Iterator<WorkOrder> it=pQueue.iterator();
			while(it.hasNext()) {
				WorkOrder data=it.next();
				long registeredTime=data.getTimeInMillis();
				MeanResponse response=new MeanResponse();
				response.setRequestId(data.getRequestId());
				long timeDiff=(currentTime-registeredTime)/1000;
				response.setTimeInSec(timeDiff);
				list.add(response);
			}
		}
		else {
			throw new RuntimeException("Queue is Empty");
		}
		
		return list;
	}
}
