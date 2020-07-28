package pl.lp.demo.infrastructure;

import com.github.rutledgepaulv.qbuilders.builders.GeneralQueryBuilder;
import com.github.rutledgepaulv.qbuilders.conditions.Condition;
import com.github.rutledgepaulv.qbuilders.visitors.MongoVisitor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import pl.lp.demo.domain.EPaper;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EPaperRepositoryImpl {
    private final MongoTemplate mongoTemplate;

    public Page<EPaper> findAll(Condition<GeneralQueryBuilder> condition, Pageable pageable) {
        Query query = new Query();

        Optional.ofNullable(condition)
                .map(c -> c.query(new MongoVisitor()))
                .ifPresent(query::addCriteria);

        long total = mongoTemplate.count(query, EPaper.class);

        query.with(pageable);

        return new PageImpl<>(mongoTemplate.find(query, EPaper.class), pageable, total);
    }
}
