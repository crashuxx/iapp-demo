package pl.lp.demo.domain;

import org.springframework.core.io.Resource;
import pl.lp.demo.dto.EPaperRequest;

import java.io.IOException;

public interface EPaperXmlDeserializer {
    EPaperRequest deserialize(Resource resource) throws IOException;
}
