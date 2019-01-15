package cn.com.simpleuse.web.aspect;

import cn.com.simpleuse.sys.exception.CsrfTokenException;
import cn.com.simpleuse.web.annotation.CsrfToken;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Aspect
//@Component
public class CsrfTokenAspectObject {

    @Autowired
    @Qualifier("csrfTokenCacheManager")
    private CacheManager cacheManager;

    private static final Logger logger = LoggerFactory.getLogger(CsrfTokenAspectObject.class);

    @Before("execution(* cn.com.simpleuse..controller.*.*(..)) && @annotation(cn.com.simpleuse.web.annotation.CsrfToken)")
    public void processCsrfToken(JoinPoint joinPoint) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Cache cache = cacheManager.getCache("csrf_token");
        CsrfToken csrfToken = signature.getMethod().getAnnotation(CsrfToken.class);

        if (csrfToken.verify()) {

            String requestCsrfToken = request.getParameter("_csrf_token");
            String cacheCsrfToken = cache.get("_csrf_token",String.class);
            if (requestCsrfToken == null || cacheCsrfToken == null) {
                throw new CsrfTokenException("missing csrf_token paramater");
            }

            if (requestCsrfToken.equals(cacheCsrfToken)) {
                 cache.evict(requestCsrfToken);
            } else {
                throw new CsrfTokenException("verify csrf_token failed");
            }

//            Object[] args = joinPoint.getArgs();
//            Class<?> cls = args[1].getClass();
//            Method method = cls.getMethod("reject", String.class, String.class);
//            method.invoke(args[1], "csrf_token_verify_failed", "CSRF Token Verify Failed");

//            Method method = cls.getMethod("getCsrfToken", null);
//            Object key = method.invoke(args[0], null);
//
//            Object cacheToken = cache.get(key).get();
//            cache.evict(key);
//            if (!key.equals(cacheToken)) {
//                logger.error("verify csrf token error");
//            }
        } else {
            String uuid = cache.get("_csrf_token",String.class);
            if (uuid == null) {
                uuid = UUID.randomUUID().toString();
                cache.put("_csrf_token", uuid);
            }
            request.setAttribute("_csrf_token", uuid);
        }
    }
}
