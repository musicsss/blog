package com.xidian.blog.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author 米
 * @date 2020/4/4
 */
@Aspect
@Component
public class UpLoadAspect  {
    private final static Logger logger = LoggerFactory.getLogger(UpLoadAspect.class);
}
