package com.queue.priorityqueue.service;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import org.springframework.scheduling.annotation.Scheduled;
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

	@Scheduled(fixedRate = 1000)
	public void scheduleTaskWithFixedRate() {
		pQueue = Utility.sortQueue(pQueue);

	}

	public WorkOrder setData(WorkOrder request) {
		long requestId = request.getRequestId();
		if (requestId > 0) {
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
		} else {
			throw new RuntimeException("Invalid Request ID");
		}
		return request;

	}

	public Queue<WorkOrder> getWorkOrder() {
		return pQueue;
	}

	public WorkOrder deleteTop() {
		if (!pQueue.isEmpty()) {
			WorkOrder workOrder = new WorkOrder();
			WorkOrder topData = pQueue.peek();
			workOrder.setRequestId(topData.getRequestId());
			workOrder.setDate(topData.getDate());
			pQueue.remove(topData);
			return workOrder;
		} else {
			throw new RuntimeException("Queue is Empty");
		}
	}

	public boolean deleteID(long requestID) {
		boolean flag = false;
		Iterator<WorkOrder> it = pQueue.iterator();
		WorkOrder data;
		while (it.hasNext()) {
			data = it.next();
			long id = data.getRequestId();
			if (id == requestID) {
				flag = true;
				pQueue.remove(data);
				break;
			}
		}
		if (!flag) {
			throw new ResourceNotFoundException("ID : " + requestID + " doesn't exist");
		}
		return flag;
	}

	public IndexResponse getWorkOrderIndex(long requestId) {
		Iterator<WorkOrder> it = pQueue.iterator();
		long count = 0;
		boolean flag = false;
		while (it.hasNext()) {
			WorkOrder data = it.next();
			long id = data.getRequestId();
			if (id == requestId) {
				flag = true;
				break;
			} else {
				count++;
			}
		}
		if (flag) {
			IndexResponse response = new IndexResponse();
			response.setRequestId(requestId);
			response.setIndexNumber(count);
			return response;
		} else {
			throw new ResourceNotFoundException("ID : " + requestId + " doesn't exist");
		}
	}

	public Queue<MeanResponse> getWaitTime() {
		Queue<MeanResponse> list = new LinkedList<>();
		Date date = new Date();
		long currentTime = date.getTime();
		long registeredTime;
		long timeDiff;
		long avergaeTime;
		if (!pQueue.isEmpty()) {
			int size = pQueue.size();
			Iterator<WorkOrder> it = pQueue.iterator();
			while (it.hasNext()) {
				WorkOrder data = it.next();
				registeredTime = data.getTimeInMillis();
				MeanResponse response = new MeanResponse();
				response.setRequestId(data.getRequestId());
				timeDiff = (currentTime - registeredTime) / 1000;
				avergaeTime = timeDiff / size;
				response.setTimeInSec(avergaeTime);
				list.add(response);
			}
		} else {
			throw new RuntimeException("Queue is Empty");
		}

		return list;
	}
}
