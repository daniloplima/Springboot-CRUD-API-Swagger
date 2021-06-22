package com.example.api.h2.jpa.repositories;

import com.example.api.h2.jpa.model.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository  extends CrudRepository<Todo, Long> {

}
