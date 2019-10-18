package com.windmill.manager.service;

import java.util.List;

import com.windmill.manager.model.PowerAggregate;
import com.windmill.manager.model.WindMillDTO;
import com.windmill.manager.model.WindMillMetricDTO;

public interface WindMillService {

	public boolean registerWindMill(WindMillDTO windMillDto);

	public boolean windMillMetric(WindMillMetricDTO windMillMetricDTO);

	public List<PowerAggregate> getAggregatedPowerInfo();

}
