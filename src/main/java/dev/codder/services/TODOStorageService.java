package dev.codder.services;

import dev.codder.models.TODOModel;

import java.util.List;

/**
 * Created by Daniil on 2/25/2016.
 */
public interface TODOStorageService {

    void delete(Long id);

    int update(TODOModel todo);

    TODOModel save(TODOModel todo);

    List<TODOModel> getAll();

    TODOModel getById(Long id);

    void deleteCompleted();

}
