package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todos;
import com.lambdaschool.todos.repository.TodosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Transactional
@Service(value = "TodosService")
public class TodosServiceImpl  implements TodosService{

    @Autowired
    TodosRepository todosRepos;

    @Override
    public void markComplete(long todoid) {
//        Todos todos = todosRepos.findById(todoid);

//        todos.setCompleted(todos.getCompleted() == true? false : true);
    }

    @Transactional
    @Override
    public Todos save(Todos todos)
    {
        if (todos.getUser() == null)
        {
            throw new EntityExistsException("User todos are not updated through todos.");
        }

        return todosRepos.save(todos);
    }

    @Override
    public Todos getTodoById(long todoid) {
        return todosRepos.findById(todoid)
                .orElseThrow(() -> new EntityNotFoundException("Todo doesn't exist!"));
    }
}
