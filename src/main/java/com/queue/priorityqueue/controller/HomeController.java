package com.queue.priorityqueue.controller;

import java.util.Queue;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.queue.priorityqueue.dto.IndexResponse;
import com.queue.priorityqueue.dto.MeanResponse;
import com.queue.priorityqueue.dto.WorkOrder;
import com.queue.priorityqueue.service.QueueService;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api")
public class HomeController {

	@Autowired
	QueueService qservice;

	@PostMapping("/workorder")
	public ResponseEntity<Void> createWorkOrder(@Valid @RequestBody WorkOrder request) {
		qservice.setData(request);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/workorder/delete")
	public ResponseEntity<WorkOrder> deleteTop() {
		return ResponseEntity.ok(qservice.deleteTop());
	}

	@GetMapping("/workorder")
	public ResponseEntity<Queue<WorkOrder>> getWorkOrder() {
		return ResponseEntity.ok(qservice.getWorkOrder());
	}

	@DeleteMapping("/workorder/{request_ID}/delete")
	public ResponseEntity<Void> deleteID(
			@ApiParam(value = "Request ID", required = true) @PathVariable(value = "request_ID") String requestID) {
		qservice.deleteID(Long.valueOf(requestID));
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/workorder/{request_ID}/index")
	public ResponseEntity<IndexResponse> getWorkOrderIndex(
			@ApiParam(value = "Request ID", required = true) @PathVariable(value = "request_ID") String requestID) {
		return ResponseEntity.ok(qservice.getWorkOrderIndex(Long.valueOf(requestID)));
	}

	@GetMapping("/workorder/waittime")
	public ResponseEntity<Queue<MeanResponse>> getWaitTime() {
		return ResponseEntity.ok(qservice.getWaitTime());
	}

}
