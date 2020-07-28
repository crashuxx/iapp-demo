package pl.lp.demo;

import com.github.rutledgepaulv.rqe.conversions.SpringConversionServiceConverter;
import com.github.rutledgepaulv.rqe.conversions.parsers.StringToInstantConverter;
import com.github.rutledgepaulv.rqe.conversions.parsers.StringToObjectBestEffortConverter;
import com.github.rutledgepaulv.rqe.pipes.DefaultArgumentConversionPipe;
import com.github.rutledgepaulv.rqe.pipes.QueryConversionPipeline;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;
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
        DefaultConversionService conversions = new DefaultConversionService();
        conversions.addConverter(new StringToInstantConverter());
        conversions.addConverter(new StringToObjectBestEffortConverter());
        conversions.addConverter(String.class, ZonedDateTime.class, ZonedDateTime::parse);

        DefaultArgumentConversionPipe argumentConversionPipe = DefaultArgumentConversionPipe.builder()
                .useNonDefaultStringToTypeConverter(new SpringConversionServiceConverter(conversions))
                .build();

        return QueryConversionPipeline.builder()
                .useNonDefaultArgumentConversionPipe(argumentConversionPipe).build();
    }

    @Bean
    public XmlSchemaValidator xmlSchemaValidator() throws IOException, SAXException {
        return new XmlSchemaValidatorImpl(new ClassPathResource("schema.xsd"));
    }
}
