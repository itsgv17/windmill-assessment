package com.windmill.manager.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "windmill_metric")
public class WindMillMetric {

	@EmbeddedId
	private WindMillMetricId windMillMetricId;

	public WindMillMetricId getWindMillMetricId() {
		return windMillMetricId;
	}

	public void setWindMillMetricId(WindMillMetricId windMillMetricId) {
		this.windMillMetricId = windMillMetricId;
	}

	private float power;

	public float getPower() {
		return power;
	}

	public void setPower(float power) {
		this.power = power;
	}

}
