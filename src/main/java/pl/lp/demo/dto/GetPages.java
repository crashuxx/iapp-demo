package pl.lp.demo.dto;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.time.LocalDate;

@Getter
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
public class GetPages {
    @XmlAttribute
    private Long editionDefId;
    @XmlAttribute
    private LocalDate publicationDate;
}
