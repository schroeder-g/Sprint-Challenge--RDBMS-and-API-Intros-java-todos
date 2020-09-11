package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todos;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface TodosService
{
    void markComplete(long todoid);

    Todos getTodoById(long todoid);

    Todos save(Todos todos);
}
