package pl.lp.demo;

import com.github.rutledgepaulv.rqe.conversions.StringToTypeConverter;
import com.github.rutledgepaulv.rqe.pipes.DefaultArgumentConversionPipe;
import com.github.rutledgepaulv.rqe.pipes.QueryConversionPipeline;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.xml.sax.SAXException;
import pl.lp.demo.domain.XmlSchemaValidator;
import pl.lp.demo.infrastructure.XmlSchemaValidatorImpl;

import java.io.IOException;
import java.time.ZonedDateTime;

@Configuration
public class AppConfiguration {
    @Bean
    public QueryConversionPipeline queryConversionPipeline() {
        DefaultArgumentConversionPipe argumentConversionPipe = DefaultArgumentConversionPipe.builder()
                .useNonDefaultStringToTypeConverter(new StringToTypeConverter() {

                    @Override
                    public Object apply(String s, Class<?> aClass) {
                        return ZonedDateTime.parse(s);
                    }

                    @Override
                    public boolean supports(Class<?> clazz) {
                        return ZonedDateTime.class.isAssignableFrom(clazz);
                    }
                })
                .build();

        return QueryConversionPipeline.builder()
                .useNonDefaultArgumentConversionPipe(argumentConversionPipe).build();
    }

    @Bean
    public XmlSchemaValidator xmlSchemaValidator() throws IOException, SAXException {
        return new XmlSchemaValidatorImpl(new ClassPathResource("schema.xsd"));
    }
}
