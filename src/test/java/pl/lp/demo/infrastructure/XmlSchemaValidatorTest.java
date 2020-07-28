package pl.lp.demo.infrastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.core.io.ClassPathResource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import pl.lp.demo.domain.XmlSchemaValidator;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class XmlSchemaValidatorTest {
    private XmlSchemaValidator xmlSchemaValidator;

    @BeforeEach
    void setUp() throws IOException, SAXException {
        xmlSchemaValidator = new XmlSchemaValidatorImpl(new ClassPathResource("schema.xsd"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"example1.xml", "example2.xml"})
    void validXmlSchema(String fileName) throws IOException, SAXException {
        ClassPathResource testXml = new ClassPathResource(fileName);

        xmlSchemaValidator.validate(testXml);
    }

    @ParameterizedTest
    @ValueSource(strings = {"example1-n.xml", "example2-n.xml"})
    void invalidXmlSchema(String fileName) {
        ClassPathResource testXml = new ClassPathResource(fileName);

        assertThrows(SAXParseException.class, () -> xmlSchemaValidator.validate(testXml));
    }
}