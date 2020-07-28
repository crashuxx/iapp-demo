package pl.lp.demo.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.xml.sax.SAXException;
import pl.lp.demo.domain.XmlSchemaValidator;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.io.InputStream;

@RequiredArgsConstructor
public class XmlSchemaValidatorImpl implements XmlSchemaValidator {
    private final Schema schema;

    public XmlSchemaValidatorImpl(Resource schemaResource) throws SAXException, IOException {
        this(createFromResource(schemaResource));
    }

    @Override
    public void validate(Resource xmlResource) throws IOException, SAXException {
        Validator validator = schema.newValidator();

        try (InputStream xmlInputStream = xmlResource.getInputStream()) {
            validator.validate(new StreamSource(xmlInputStream));
        }
    }

    static Schema createFromResource(Resource schemaResource) throws SAXException, IOException {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        return schemaFactory.newSchema(schemaResource.getURL());
    }
}
