package com.cms.admin.aspect;

import com.cms.admin.entity.CmsLogEntity;
import com.cms.admin.entity.ResponseEntity;
import com.cms.admin.mapper.CmsLogMapper;
import com.cms.admin.service.CmsLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

@Slf4j
@Aspect
@Component
public class SystemLogAspect {

  @Resource private CmsLogService logService;

  /** 切入点 */
  @Pointcut(
      "execution(public * com.cms.admin.controller..*.*(..))&&@annotation(com.cms.admin.aspect.SystemLog)")
  public void SystemLog() {}

  /**
   * 环绕操作 只作用在controller上面
   *
   * @param point 切入点
   * @return 原方法返回值
   * @throws Throwable 异常信息
   */
  @Around("SystemLog()")
  public Object aroundLog(ProceedingJoinPoint point) throws Throwable {

    // 开始打印请求日志
    ServletRequestAttributes attributes =
        (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
    HttpServletResponse response = Objects.requireNonNull(attributes).getResponse();

    // 获取注解上的值
    MethodSignature signature = (MethodSignature) point.getSignature();
    Method method = signature.getMethod();
    SystemLog annotation = method.getAnnotation(SystemLog.class);

    // 获取响应信息
    long startTime = System.currentTimeMillis();
    ResponseEntity result = (ResponseEntity) point.proceed();

    // 构造日志
    CmsLogEntity logEntity =
        CmsLogEntity.builder()
            .requestIp(getIp(request))
            .requestUrl(request.getRequestURL().toString())
            .operationType(annotation.type())
            .httpMethod(request.getMethod())
            .operationTime(new Date(startTime).toInstant().toString())
            .requestParams(getParamsAndValue(point).toString())
            .operationContent(annotation.operation())
            .timeCost(System.currentTimeMillis() - startTime)
            .classMethod(
                point.getSignature().getDeclaringTypeName() + point.getSignature().getName())
            .resultMsg(result.getMsg())
            .resultCode(response.getStatus())
            .build();

    logService.insert(logEntity);
    return result;
  }

  /**
   * 获取方法参数名和参数值
   *
   * @param joinPoint
   * @return
   */
  private Map<String, Object> getParamsAndValue(ProceedingJoinPoint joinPoint) {

    final Signature signature = joinPoint.getSignature();
    MethodSignature methodSignature = (MethodSignature) signature;
    final String[] names = methodSignature.getParameterNames();
    final Object[] args = joinPoint.getArgs();

    if (names.length == 0 || args.length == 0) {
      return Collections.emptyMap();
    }
    if (names.length != args.length) {
      log.warn("{}方法参数名和参数值数量不一致", methodSignature.getName());
      return Collections.emptyMap();
    }
    Map<String, Object> map = new HashMap<>();
    for (int i = 0; i < names.length; i++) {
      map.put(names[i], args[i]);
    }
    return map;
  }

  private static final String UNKNOWN = "unknown";

  /** 获取ip地址 */
  public static String getIp(HttpServletRequest request) {
    String ip = request.getHeader("x-forwarded-for");
    if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
      ip = request.getHeader("Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
      ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
      ip = request.getRemoteAddr();
    }
    String comma = ",";
    String localhost = "127.0.0.1";
    if (ip.contains(comma)) {
      ip = ip.split(",")[0];
    }
    if (localhost.equals(ip)) {
      // 获取本机真正的ip地址
      try {
        ip = InetAddress.getLocalHost().getHostAddress();
      } catch (UnknownHostException e) {
        log.error(e.getMessage(), e);
      }
    }
    return ip;
  }
}
