package com.example.vms.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import com.example.vms.model.Employee;
import com.example.vms.model.Visitor;
import com.example.vms.service.VisitorService;

public class Receiver {

	@Autowired
	VisitorService visitorService;

	@KafkaListener(topics = "MacProducer")
	public void receive(Visitor vistor) {
		visitorService.createVisitor(vistor);
	}
}
