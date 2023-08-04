package dev.crashteam.snatcher.component;

import dev.crashteam.snatcher.model.redis.ProxySource;
import dev.crashteam.snatcher.util.RandomUserAgent;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Collections;
import java.util.function.Predicate;

@Component
public class BrowserDriverManager {

    public ChromeDriver newChromeDriver(ProxySource proxySource){
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        // Fixing 255 Error crashes
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        // Options to trick bot detection
        // Removing webdriver property
        options.addArguments("start-maximized");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("window-size=1920,1080");

        // Changing the user agent / browser fingerprint
        options.addArguments(RandomUserAgent.getRandomUserAgent());

        // Other
        options.addArguments("disable-infobars");

        Proxy proxy = new Proxy();
        proxy.setAutodetect(false);
        proxy.setProxyType(Proxy.ProxyType.MANUAL);
        proxy.setHttpProxy("%s:%s".formatted(proxySource.getHost(), proxySource.getPort()));
        proxy.setSslProxy("%s:%s".formatted(proxySource.getHost(), proxySource.getPort()));

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(CapabilityType.PROXY, proxy);
        ChromeDriver chromeDriver = new ChromeDriver(options.merge(desiredCapabilities));

        Predicate<URI> uriPredicate = uri -> uri.getHost().contains(proxySource.getHost());

        ((HasAuthentication) chromeDriver)
                .register(uriPredicate, UsernameAndPassword.of(proxySource.getLogin(), proxySource.getPassword()));

        return chromeDriver;
    }

}
