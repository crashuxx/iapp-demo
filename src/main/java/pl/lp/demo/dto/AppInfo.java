package pl.lp.demo.dto;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Getter
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
public class AppInfo {
    @XmlElement
    private String newspaperName;
    @XmlElement
    private String version;
}
