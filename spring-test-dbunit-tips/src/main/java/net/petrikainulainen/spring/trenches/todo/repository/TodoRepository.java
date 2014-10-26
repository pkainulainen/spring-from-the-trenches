package net.petrikainulainen.spring.trenches.todo.repository;

import net.petrikainulainen.spring.trenches.todo.model.Todo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * This repository provides CRUD operations for {@link net.petrikainulainen.spring.trenches.todo.model.Todo} objects.
 * @author Petri Kainulainen
 */
public interface TodoRepository extends CrudRepository<Todo, Long> {

    List<Todo> findByDescription(String description);
}
