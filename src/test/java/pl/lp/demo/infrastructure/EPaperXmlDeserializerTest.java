package pl.lp.demo.infrastructure;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.core.io.ClassPathResource;
import pl.lp.demo.domain.EPaperXmlDeserializer;
import pl.lp.demo.dto.DeviceInfo;
import pl.lp.demo.dto.EPaperRequest;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EPaperXmlDeserializerTest {
    EPaperXmlDeserializer deserializer = new EPaperXmlDeserializerImpl();

    @ParameterizedTest
    @ValueSource(strings = {"example1.xml", "example2.xml"})
    void shouldDeserializeDocument(String fileName) throws IOException {
        ClassPathResource resource = new ClassPathResource(fileName);

        EPaperRequest ePaperRequest = deserializer.deserialize(resource);

        assertNotNull(ePaperRequest);
        DeviceInfo deviceInfo = ePaperRequest.getDeviceInfo();
        assertNotNull(deviceInfo);
        assertNotEquals("", deviceInfo.getAppInfo().getNewspaperName());
        assertThat("Width", deviceInfo.getScreenInfo().getWidth(), greaterThan(0L));
        assertThat("Height", deviceInfo.getScreenInfo().getHeight(), greaterThan(0L));
        assertThat("Dpi", deviceInfo.getScreenInfo().getDpi(), greaterThan(0L));
    }
}