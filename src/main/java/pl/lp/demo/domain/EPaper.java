package pl.lp.demo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Builder
@Accessors
public class EPaper {
    @Id
    private final UUID id;

    private final String newspaperName;
    private final Long width;
    private final Long height;
    private final Long dpi;

    private final String fileName;
    private final ZonedDateTime createdAt;
}
