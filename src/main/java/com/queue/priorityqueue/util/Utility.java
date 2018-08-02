package com.queue.priorityqueue.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.queue.priorityqueue.dto.WorkOrder;

public class Utility {

	private Utility() {

	}

	public static Queue<WorkOrder> sortQueue(Queue<WorkOrder> pQueue) {
		Queue<WorkOrder> sortedQueue = new LinkedList<>();
		List<WorkOrder> manQueue = new LinkedList<>();
		List<WorkOrder> restQueue = new LinkedList<>();
		List<WorkOrder> sortedRest;
		Iterator<WorkOrder> it = pQueue.iterator();
		while (it.hasNext()) {
			WorkOrder data = it.next();
			long requestID = data.getRequestId();
			if (requestID % 3 == 0 && requestID % 5 == 0) {
				manQueue.add(data);
			} else {
				restQueue.add(data);
			}
		}
		sortedRest = restSort(restQueue);
		sortedQueue.addAll(manQueue);
		sortedQueue.addAll(sortedRest);
		return sortedQueue;

	}

	protected static List<WorkOrder> restSort(List<WorkOrder> restQueue) {
		Date date = new Date();
		double time = date.getTime();
		Iterator<WorkOrder> it = restQueue.iterator();
		while (it.hasNext()) {
			WorkOrder data = it.next();
			long requestID = data.getRequestId();
			double registeredtime = data.getTimeInMillis();
			double diff = (time - registeredtime) / 1000;

			if (requestID % 3 == 0) {
				double timeInQueue = Math.max(3, diff * Math.log(diff));
				data.setTimeInQueue(timeInQueue);
			} else if (requestID % 5 == 0) {
				double timeInQueue = Math.max(4, 2 * (diff * Math.log(diff)));
				data.setTimeInQueue(timeInQueue);
			} else {
				data.setTimeInQueue(diff);
			}
		}

		Collections.sort(restQueue, new Comparator<WorkOrder>() {
			@Override
			public int compare(WorkOrder o1, WorkOrder o2) {
				return (int) (o2.getTimeInQueue() - o1.getTimeInQueue());
			}
		});

		return restQueue;
	}

}
