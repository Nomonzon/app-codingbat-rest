package com.example.appcodingbatrest.repository;

import com.example.appcodingbatrest.entities.Example;
import org.apache.el.parser.JJTELParserState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface ExampleRepository extends JpaRepository<Example, Long> {
}
