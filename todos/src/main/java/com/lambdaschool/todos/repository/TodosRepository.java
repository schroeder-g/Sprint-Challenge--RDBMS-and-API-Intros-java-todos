package com.lambdaschool.todos.repository;

import com.lambdaschool.todos.models.Todo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface TodosRepository extends CrudRepository<Todo, Long>
{
    @Transactional
    @Modifying
    @Query(value = "UPDATE todos SET description = :description, completed = :completed, last_modified_by = :uname, last_modified_date = CURRENT_TIMESTAMP WHERE todoid = :todoid",
            nativeQuery = true)
    void updateCompleted(
            String uname,
            long todoid,
            boolean completed);

}
