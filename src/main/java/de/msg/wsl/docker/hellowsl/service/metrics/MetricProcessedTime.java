package de.msg.wsl.docker.hellowsl.service.metrics;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
@RequiredArgsConstructor
public class MetricProcessedTime {

  private final MetricsService metricsService;

  @Around("publicAndAnnotatedMethods()")
  public Object stopAndSendMetric(ProceedingJoinPoint joinPoint) throws Throwable {
    long startTime = System.currentTimeMillis();
    Object result = joinPoint.proceed();
    long processedTime = System.currentTimeMillis() - startTime;
    final Class<?> observedClass = joinPoint.getTarget().getClass();
    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
    metricsService.timer(Metric.PROCESSING_MESSAGE, processedTime, observedClass.getSimpleName(), methodSignature.getName());
    return result;
  }

  @Pointcut("execution(@de.msg.wsl.docker.hellowsl.service.metrics.MetricProcessedTimeInterceptable * *.*(..))")
  public void annotatedWithMetricProcessedTimeInterceptable() {}

  @Pointcut("execution(public * *(..))")
  public void publicMethods() {}

  @Pointcut("publicMethods() && annotatedWithMetricProcessedTimeInterceptable()")
  public void publicAndAnnotatedMethods() {}
}
