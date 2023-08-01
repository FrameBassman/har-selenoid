package tech.romashov;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class SelenoidWebDriverProvider implements WebDriverProvider {
//    @Nonnull
    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        try {
            ChromeOptions options = new ChromeOptions();
            options.setCapability("selenoid:options", new HashMap<String, Object>() {{
                /* How to add test badge */
                put("name", "Test badge...");

                /* How to set session timeout */
                put("sessionTimeout", "15m");

                /* How to set timezone */
                put("env", new ArrayList<String>() {{
                    add("TZ=UTC");
                }});

                /* How to add "trash" button */
                put("labels", new HashMap<String, Object>() {{
                    put("manual", "true");
                }});

                /* How to enable video recording */
//                put("enableVideo", true);
            }});
            capabilities.merge(options);
            return new RemoteWebDriver(new URL("http://0.0.0.0:4444/wd/hub"), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
