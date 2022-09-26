
package com.store.hulk.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Configuration
@Async
public class AsyncConfig {

    @Bean("asyncExecutor")
    public CompletableFuture<Executor> asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(6);
        executor.setMaxPoolSize(8);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("concurrentProcess-");
        executor.initialize();
        return CompletableFuture.completedFuture(executor);

    }
}
