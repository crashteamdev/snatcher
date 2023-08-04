package dev.crashteam.snatcher.integration;

import dev.crashteam.snatcher.model.proxy.ProxyLineResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "proxyLineIntegration", url = "${app.proxy.proxy-line.url}")
public interface ProxyLineIntegration {

    @GetMapping("/proxies")
    ProxyLineResponse getProxy(@RequestParam(name = "api_key") String apiKey,
                                     @RequestParam(name = "status", defaultValue = "active") String status);
}
