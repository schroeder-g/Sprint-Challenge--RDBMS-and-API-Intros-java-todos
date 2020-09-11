package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todo;


public interface TodosService
{
    void markComplete(long todoid, boolean completed);

    Todo findTodoById(long todoid);

    Todo save(Todo todo);

    Todo update(Long todoid, Todo todo);
}
