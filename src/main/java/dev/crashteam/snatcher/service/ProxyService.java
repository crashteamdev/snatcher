package dev.crashteam.snatcher.service;

import dev.crashteam.snatcher.exception.ProxyNotObtainedException;
import dev.crashteam.snatcher.integration.ProxyLineIntegration;
import dev.crashteam.snatcher.mapper.ProxyLineToSourceMapper;
import dev.crashteam.snatcher.model.proxy.ProxyLineResponse;
import dev.crashteam.snatcher.model.redis.ProxySource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class ProxyService {

    private final ProxyLineIntegration proxyLineIntegration;
    private final ProxyLineToSourceMapper lineToSourceMapper;

    @Value("${app.proxy.proxy-line.api-key}")
    private String apiKey;

    public ProxySource getRandomProxy() {
        List<ProxySource> proxies = getProxies();
        int randomIndex = ThreadLocalRandom.current().nextInt(proxies.size()) % proxies.size();
        return proxies.get(randomIndex);
    }

    @Cacheable(value = "proxies")
    public List<ProxySource> getProxies() {
        List<ProxyLineResponse.ProxyLineResult> activeProxies = Optional
                .ofNullable(proxyLineIntegration.getProxy(apiKey, "active"))
                .map(ProxyLineResponse::getResults)
                .orElseThrow(ProxyNotObtainedException::new);
        return lineToSourceMapper.dtoToDomain(activeProxies);
    }
}
