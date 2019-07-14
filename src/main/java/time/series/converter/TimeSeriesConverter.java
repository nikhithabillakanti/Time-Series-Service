package time.series.converter;


import java.util.Calendar;
import java.util.Objects;

import org.springframework.stereotype.Component;

import time.series.dto.TimeSeriesDTO;
import time.series.model.TimeSeries;

@Component
public class TimeSeriesConverter {
	
	public TimeSeries convert(TimeSeriesDTO timeSeriesDTO) {
		TimeSeries timeSeries = new TimeSeries();
		timeSeries.setCustomerId(timeSeriesDTO.getCustomerId());
		timeSeries.setMeterId(timeSeriesDTO.getMeterId());
		timeSeries.setFrom(timeSeriesDTO.getFrom());
		timeSeries.setTo(timeSeriesDTO.getTo());
		timeSeries.setResolution(timeSeriesDTO.getResolution());
		timeSeries.setValues(timeSeriesDTO.getValues());
		timeSeries.setTotal(timeSeriesDTO.getValues().values().stream().filter(Objects::nonNull).mapToDouble(Double::doubleValue).sum());
		
		
		Calendar calendar= Calendar.getInstance();
		calendar.setTimeInMillis(timeSeriesDTO.getFrom().getTime());
		
		timeSeries.setWeek(calendar.get(Calendar.WEEK_OF_YEAR));
		return timeSeries;
	}
	
	public TimeSeriesDTO convert(TimeSeries timeSeries) {
		TimeSeriesDTO timeSeriesDTO = new TimeSeriesDTO();
		timeSeriesDTO.setCustomerId(timeSeries.getCustomerId());
		timeSeriesDTO.setMeterId(timeSeries.getMeterId());
		timeSeriesDTO.setFrom(timeSeries.getFrom());
		timeSeriesDTO.setTo(timeSeries.getTo());
		timeSeriesDTO.setResolution(timeSeries.getResolution());
		timeSeriesDTO.setValues(timeSeries.getValues());
		
		return timeSeriesDTO;
	}
}
