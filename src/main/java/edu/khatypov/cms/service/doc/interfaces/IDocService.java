package edu.khatypov.cms.service.doc.interfaces;

import edu.khatypov.cms.model.Doc;

import java.util.List;

public interface IDocService {
    Doc create(Doc doc);
    Doc get(String id);
    Doc update(Doc doc);
    Doc delete(String id);
    List<Doc> getAll();
}
