package com.queue.priorityqueue.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.queue.priorityqueue.dto.IndexResponse;
import com.queue.priorityqueue.dto.MeanResponse;
import com.queue.priorityqueue.dto.WorkOrder;
import com.queue.priorityqueue.service.QueueService;

@RunWith(SpringRunner.class)
public class HomeControllerTest {

	@Mock
	private QueueService service;

	@InjectMocks
	private HomeController controller;

	@Test
	public void createWorkOrder() {

		WorkOrder order = new WorkOrder();
		order.setRequestId(1);
		order.setDate(LocalDate.now());
		Date date = new Date();
		long timeInMillis = date.getTime();
		order.setTimeInMillis(timeInMillis);
		Queue<WorkOrder> pQueue = new LinkedList<>();
		pQueue.add(order);

		when(service.setData(order)).thenReturn(order);

		ResponseEntity<Void> responseEntity = controller.createWorkOrder(order);
		System.out.println(responseEntity.getBody() + "----------------" + responseEntity.getStatusCode());
		assertEquals(200, responseEntity.getStatusCode().value());
	}

	@Test
	public void deleteTop() {

		WorkOrder order = new WorkOrder();
		order.setRequestId(1);
		order.setDate(LocalDate.now());

		when(service.deleteTop()).thenReturn(order);

		ResponseEntity<WorkOrder> responseEntity = controller.deleteTop();
		System.out.println(responseEntity.getBody() + "----------------" + responseEntity.getStatusCode());
		assertEquals(200, responseEntity.getStatusCode().value());
	}

	@Test
	public void getAll() {
		WorkOrder order = new WorkOrder();
		order.setRequestId(1);
		order.setDate(LocalDate.now());
		Date date = new Date();
		long timeInMillis = date.getTime();
		order.setTimeInMillis(timeInMillis);
		Queue<WorkOrder> pQueue = new LinkedList<>();
		pQueue.add(order);
		when(service.getWorkOrder()).thenReturn(pQueue);

		ResponseEntity<Queue<WorkOrder>> responseEntity = controller.getWorkOrder();
		System.out.println(responseEntity.getBody() + "----------------" + responseEntity.getStatusCode());
		assertEquals(200, responseEntity.getStatusCode().value());
	}

	@Test
	public void deleteID() {

		String id = "1";

		when(service.deleteID(Long.valueOf(id))).thenReturn(true);

		ResponseEntity<Void> responseEntity = controller.deleteID(id);
		System.out.println(responseEntity.getBody() + "----------------" + responseEntity.getStatusCode());
		assertEquals(200, responseEntity.getStatusCode().value());
	}

	@Test
	public void getWorkOrderIndex() {

		IndexResponse response = new IndexResponse();
		response.setRequestId(1);
		response.setIndexNumber(0);

		String id = "1";

		when(service.getWorkOrderIndex(Long.valueOf(id))).thenReturn(response);

		ResponseEntity<IndexResponse> responseEntity = controller.getWorkOrderIndex(id);
		System.out.println(responseEntity.getBody() + "----------------" + responseEntity.getStatusCode());
		assertEquals(200, responseEntity.getStatusCode().value());
	}
	
	@Test
	public void getWaitTime() {

		MeanResponse response = new MeanResponse();
		response.setRequestId(1);
		response.setTimeInSec(10);
		
		Queue<MeanResponse> queue = new LinkedList<>();
		queue.add(response);

		when(service.getWaitTime()).thenReturn(queue);

		ResponseEntity<Queue<MeanResponse>> responseEntity = controller.getWaitTime();
		System.out.println(responseEntity.getBody() + "----------------" + responseEntity.getStatusCode());
		assertEquals(200, responseEntity.getStatusCode().value());
	}
	
}
