package pl.lp.demo.domain;

import com.github.rutledgepaulv.qbuilders.builders.GeneralQueryBuilder;
import com.github.rutledgepaulv.qbuilders.conditions.Condition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface EPaperRepository extends MongoRepository<EPaper, UUID> {
    Page<EPaper> findAll(Condition<GeneralQueryBuilder> condition, Pageable pageable);
}
