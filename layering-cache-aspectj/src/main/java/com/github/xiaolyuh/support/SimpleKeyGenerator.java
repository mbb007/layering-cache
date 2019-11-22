/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.xiaolyuh.support;


import java.lang.reflect.Method;

/**
 * Simple key generator. Returns the parameter itself if a single non-null value
 * is given, otherwise returns a {@link SimpleKey} of the parameters.
 * <p>Unlike {@link DefaultKeyGenerator}, no collisions will occur with the keys
 * generated by this class. The returned {@link SimpleKey} object can be safely
 * used with a {@link org.springframework.cache.concurrent.ConcurrentMapCache},
 * however, might not be suitable for all {@link org.springframework.cache.Cache}
 * implementations.
 *
 * @author Phillip Webb
 * @author Juergen Hoeller
 * @see SimpleKey
 * @see org.springframework.cache.annotation.CachingConfigurer
 * @since 4.0
 */
public class SimpleKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        return generateKey(params);
    }

    /**
     * Generate a key based on the specified parameters.
     *
     * @param params params
     * @return Object
     */
    public static Object generateKey(Object... params) {
        if (params.length == 0) {
            return SimpleKey.EMPTY;
        }
        if (params.length == 1) {
            Object param = params[0];
            if (param != null && !param.getClass().isArray()) {
                return param;
            }
        }
        return new SimpleKey(params);
    }

}
