package app.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class ApiCallCounterAspect {

    @Around("@annotation(app.aop.CountApiCall)")
    public Object countApiCall(ProceedingJoinPoint joinPoint) throws Throwable {
        final String apiKey = extractApiKey();
        log.info("API 인증 횟수를 증가 : {}", apiKey);
        return joinPoint.proceed();
    }

    private String extractApiKey() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        final String apiKey = request.getHeader("X-API-KEY");
        if (apiKey.isBlank())
            throw new IllegalArgumentException("API key is missing");
        return apiKey;
    }
}
