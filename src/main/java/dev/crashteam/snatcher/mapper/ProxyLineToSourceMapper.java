package dev.crashteam.snatcher.mapper;

import dev.crashteam.snatcher.model.proxy.ProxyLineResponse;
import dev.crashteam.snatcher.model.redis.ProxySource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProxyLineToSourceMapper {

    public ProxySource dtoToDomain(ProxyLineResponse.ProxyLineResult proxyLineResult) {
        ProxySource proxySource =new ProxySource();
        proxySource.setHost(proxyLineResult.getIp());
        proxySource.setPort(Integer.valueOf(proxyLineResult.getPortHttp()));
        proxySource.setLogin(proxyLineResult.getUser());
        proxySource.setPassword(proxyLineResult.getPassword());
        proxySource.setSocksPort(proxyLineResult.getPortSocks5());
        return proxySource;
    }

    public List<ProxySource> dtoToDomain(List<ProxyLineResponse.ProxyLineResult> proxyLineResults) {
        return proxyLineResults.stream()
                .map(this::dtoToDomain)
                .collect(Collectors.toList());
    }
}
