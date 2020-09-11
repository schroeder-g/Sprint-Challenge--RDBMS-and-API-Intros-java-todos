package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todo;
import com.lambdaschool.todos.repository.TodosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "TodosService")
public  class TodosServiceImpl  implements TodosService{

    @Autowired
    TodosRepository todosRepos;

    @Transactional
    @Override
    public void markComplete(long todoid, boolean completed) {
        Todo todo = todosRepos.findById(todoid)
                .orElseThrow(() -> new EntityNotFoundException("Can't complete something that doesn't exist"));

        todo.setCompleted(!completed);
        update(todoid, todo);
    }

    @Override
    public Todo findTodoById(long todoid) throws
            EntityNotFoundException
    {
        return todosRepos.findById(todoid)
                .orElseThrow(() -> new EntityNotFoundException("Todo not found."));
    }

    @Override
    public Todo save(Todo todo) {
        Todo newTodo = new Todo();

        if (todo.getTodoid() != 0)
        {
            findTodoById(todo.getTodoid());
            newTodo.setTodoid(todo.getTodoid());
        }

        newTodo.setDescription(todo.getDescription());
        newTodo.setCompleted(todo.getCompleted());
        newTodo.setUser(todo.getUser());

        return todosRepos.save(newTodo);
    }

    @Override
    public Todo update(Long id, Todo todo) {
        Todo patchTodo = new Todo();
        patchTodo.setTodoid(id);

        if (todo.getDescription() != null)
        {
            patchTodo.setDescription(todo.getDescription());
        }

        if (todo.getCompleted() != null)
        {
            patchTodo.setCompleted(todo.getCompleted());
        }

        if (todo.getUser() != null)
            patchTodo.setUser(todo.getUser());


        return todosRepos.save(patchTodo);
    }


}
