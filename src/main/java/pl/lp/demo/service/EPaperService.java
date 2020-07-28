package pl.lp.demo.service;

import com.github.rutledgepaulv.qbuilders.builders.GeneralQueryBuilder;
import com.github.rutledgepaulv.qbuilders.conditions.Condition;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import pl.lp.demo.domain.EPaper;
import pl.lp.demo.domain.EPaperRepository;
import pl.lp.demo.domain.EPaperXmlDeserializer;
import pl.lp.demo.domain.XmlSchemaValidator;
import pl.lp.demo.dto.EPaperRequest;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EPaperService {
    private final XmlSchemaValidator xmlSchemaValidatorImpl;
    private final EPaperXmlDeserializer ePaperXmlDeserializerImpl;
    private final EPaperRepository ePaperRepository;

    public UUID add(String fileName, Resource resource) throws IOException, SAXException {
        xmlSchemaValidatorImpl.validate(resource);
        EPaperRequest paperRequest = ePaperXmlDeserializerImpl.deserialize(resource);

        EPaper ePaper = EPaper.builder()
                .id(UUID.randomUUID())
                .fileName(fileName)
                .createdAt(ZonedDateTime.now())
                .newspaperName(paperRequest.getDeviceInfo().getAppInfo().getNewspaperName())
                .width(paperRequest.getDeviceInfo().getScreenInfo().getWidth())
                .height(paperRequest.getDeviceInfo().getScreenInfo().getHeight())
                .dpi(paperRequest.getDeviceInfo().getScreenInfo().getDpi())
                .build();

        ePaperRepository.save(ePaper);

        return ePaper.getId();
    }

    public Page<EPaper> findAll(Condition<GeneralQueryBuilder> condition, Pageable pageable) {
        return ePaperRepository.findAll(condition, pageable);
    }
}
