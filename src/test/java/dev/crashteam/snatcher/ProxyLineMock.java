package dev.crashteam.snatcher;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.matching.StringValuePattern;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.Map;

import static java.nio.charset.Charset.defaultCharset;
import static org.springframework.util.StreamUtils.copyToString;

public class ProxyLineMock {

    @SneakyThrows
    public static void getProxy(WireMockServer mockService, Map<String, StringValuePattern> queryParams) {
        createGetMockStub(mockService, queryParams,"integration/get-proxy.json");
    }

    public static void createGetMockStub(WireMockServer mockService, Map<String, StringValuePattern> queryParams, String jsonPath) throws IOException {
        mockService.stubFor(WireMock.get(WireMock.urlPathEqualTo("/api/proxies"))
                .withQueryParams(queryParams)
                .willReturn(
                        WireMock.aResponse()
                                .withStatus(HttpStatus.OK.value())
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .withBody(
                                        copyToString(
                                                IntegrationTest.class
                                                        .getClassLoader()
                                                        .getResourceAsStream(jsonPath),
                                                defaultCharset())
                                )
                )
        );
    }
}
