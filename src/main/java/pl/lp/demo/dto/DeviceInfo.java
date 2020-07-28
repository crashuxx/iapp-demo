package pl.lp.demo.dto;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@Getter
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceInfo {
    @XmlAttribute
    private String id;
    @XmlAttribute
    private String name;

    @XmlElement
    private ScreenInfo screenInfo;
    @XmlElement
    private OSInfo osInfo;
    @XmlElement
    private AppInfo appInfo;
}
