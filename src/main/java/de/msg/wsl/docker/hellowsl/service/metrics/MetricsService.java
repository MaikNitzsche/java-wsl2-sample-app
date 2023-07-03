package de.msg.wsl.docker.hellowsl.service.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.springframework.util.StringUtils.arrayToCommaDelimitedString;

@Service
@RequiredArgsConstructor
@Slf4j
public class MetricsService {

  private final MeterRegistry meterRegistry;

  public void count(Metric metric, String... tagValues) {
    if (areTagValuesValid(metric, tagValues)) {
      meterRegistry.counter(metric.name, tags(metric, tagValues)).increment();
    }
  }

  public void timer(Metric metric, long duration, String... tagValues) {
    if (areTagValuesValid(metric, tagValues)) {
      meterRegistry.timer(metric.name, tags(metric, tagValues)).record(duration, TimeUnit.MILLISECONDS);
    }
  }

  private List<Tag> tags(Metric metric, String... tagValues) {
    List<Tag> tags = new ArrayList<>();
    for (int i = 0; i < tagValues.length; i++) {
      tags.add(Tag.of(metric.tags[i], tagValues[i]));
    }
    return tags;
  }

  private boolean areTagValuesValid(Metric metric, String... tagValues) {
    if (metric != null && tagValues != null && metric.tags.length == tagValues.length) {
      return true;
    } else {
      log.error("IllegalState in MetricService: Expected tag count doesn't match with received values. Metric: {} expected tags: {} received values: {}"
          , metric, metric == null ? "metric is null" : arrayToCommaDelimitedString(metric.tags), arrayToCommaDelimitedString(tagValues));
      return false;
    }
  }

}
