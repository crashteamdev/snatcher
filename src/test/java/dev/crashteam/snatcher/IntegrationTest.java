package dev.crashteam.snatcher;

import com.github.tomakehurst.wiremock.WireMockServer;
import dev.crashteam.snatcher.configuration.WireMockConfig;
import dev.crashteam.snatcher.service.ProxyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;

@SpringBootTest
@ActiveProfiles({"test"})
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WireMockConfig.class})
public class IntegrationTest {

    @Autowired
    WireMockServer wireMockServer;

    @Autowired
    ProxyService proxyService;

    @Value("${app.proxy.proxy-line.api-key}")
    private String apiKey;
    @BeforeEach
    public void setup() {
        ProxyLineMock.getProxy(wireMockServer, Map.of("api_key", equalTo(apiKey),
                "status", equalTo("active")));
    }

    @Test
    public void testGetProxy() {
        Assertions.assertNotNull(proxyService.getProxies().get(0));
        Assertions.assertNotNull(proxyService.getProxies().get(0).getHost());
        Assertions.assertNotNull(proxyService.getProxies().get(0).getPort());
    }

}
