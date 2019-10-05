package edu.khatypov.cms.repository;

import edu.khatypov.cms.model.Doc;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocRepository extends MongoRepository<Doc, String> {
}