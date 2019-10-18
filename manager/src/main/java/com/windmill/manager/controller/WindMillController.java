package com.windmill.manager.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.windmill.manager.model.PowerAggregate;
import com.windmill.manager.model.WindMillDTO;
import com.windmill.manager.model.WindMillMetricDTO;
import com.windmill.manager.model.WindMillResp;
import com.windmill.manager.service.WindMillService;

@RestController
public class WindMillController {

	@Autowired
	private WindMillService windMillService;

	@PostMapping(value = "/register", produces = { "application/json" }, consumes = "application/json")
	public WindMillResp registerWindMill(@Valid @RequestBody WindMillDTO windMillDto) {

		this.windMillService.registerWindMill(windMillDto);

		return new WindMillResp("OK", LocalDateTime.now(), ZoneId.systemDefault().toString());

	}

	@PostMapping(value = "/windmill-metric", produces = { "application/json" }, consumes = "application/json")
	public WindMillResp windMillMetric(@Valid @RequestBody WindMillMetricDTO windMillMetricDTO) {

		this.windMillService.windMillMetric(windMillMetricDTO);

		return new WindMillResp("OK", LocalDateTime.now(), ZoneId.systemDefault().toString());
	}

	@GetMapping(value = "/power-aggregate", produces = { "application/json" })
	public List<PowerAggregate> getAggregatedPowerInfo() {

		return this.windMillService.getAggregatedPowerInfo();
	}
}
