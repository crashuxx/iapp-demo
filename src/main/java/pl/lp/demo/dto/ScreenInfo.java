package pl.lp.demo.dto;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@Getter
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
public class ScreenInfo {
    @XmlAttribute
    private Long width;
    @XmlAttribute
    private Long height;
    @XmlAttribute
    private Long dpi;
}
