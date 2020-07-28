package pl.lp.demo.controller;

import com.github.rutledgepaulv.qbuilders.builders.GeneralQueryBuilder;
import com.github.rutledgepaulv.qbuilders.conditions.Condition;
import com.github.rutledgepaulv.rqe.pipes.QueryConversionPipeline;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.xml.sax.SAXException;
import pl.lp.demo.domain.EPaper;
import pl.lp.demo.service.EPaperService;

import java.io.IOException;
import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@RestController
@RequestMapping("epaper")
@RequiredArgsConstructor
public class EPaperController {
    private final EPaperService ePaperService;
    private final QueryConversionPipeline pipeline;

    @GetMapping
    public Page<EPaper> get(
            @RequestParam(required = false) String query,
            Pageable pageable
    ) {

        Condition<GeneralQueryBuilder> condition = null;
        try {
            if (isNotBlank(query)) {
                condition = pipeline.apply(query, EPaper.class);
            }
        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid RSQL query");
        }

        return ePaperService.findAll(condition, pageable);
    }

    @PutMapping
    public UUID put(@RequestParam("file") MultipartFile file) throws IOException, SAXException {
        ByteArrayResource xmlResource = new ByteArrayResource(file.getBytes());

        return ePaperService.add(file.getOriginalFilename(), xmlResource);
    }
}
