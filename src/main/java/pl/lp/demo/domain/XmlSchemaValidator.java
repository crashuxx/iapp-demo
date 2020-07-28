package pl.lp.demo.domain;

import org.springframework.core.io.Resource;
import org.xml.sax.SAXException;

import java.io.IOException;

public interface XmlSchemaValidator {
    void validate(Resource xmlResource) throws IOException, SAXException;
}
