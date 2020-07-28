package pl.lp.demo.infrastructure;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import pl.lp.demo.domain.EPaperXmlDeserializer;
import pl.lp.demo.dto.EPaperRequest;

import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class EPaperXmlDeserializerImpl implements EPaperXmlDeserializer {
    private final XmlMapper mapper;

    public EPaperXmlDeserializerImpl() {
        this(new XmlMapper());
        mapper.registerModule(new JavaTimeModule());
    }

    @Override
    public EPaperRequest deserialize(Resource resource) throws IOException {
        try (InputStream inputStream = resource.getInputStream()) {
            return mapper.readValue(inputStream, EPaperRequest.class);
        }
    }
}
