package com.windmill.manager.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@JsonIgnoreProperties(ignoreUnknown = true)
public class WindMillMetricDTO {

	@Size(max = 16, min = 16, message = "SerialNumber must be 16 characters")
	@NotBlank(message = "SerialNumber must not be empty")
	private String serialNumber;
	@NotNull(message = "DateTime must not be empty")
	private LocalDateTime dateTime;
	private float power;

	public WindMillMetricDTO() {
		super();
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public float getPower() {
		return power;
	}

	public void setPower(float power) {
		this.power = power;
	}

}
