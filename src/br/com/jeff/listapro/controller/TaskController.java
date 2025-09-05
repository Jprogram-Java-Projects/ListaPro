package br.com.jeff.listapro.controller;

import br.com.jeff.listapro.dao.TaskFileDAO;
import br.com.jeff.listapro.model.Priority;
import br.com.jeff.listapro.model.Task;

import java.util.List;
import java.util.Optional;

public class TaskController {

    private final TaskFileDAO taskFile;

    public TaskController(TaskFileDAO taskFile) {
        this.taskFile = taskFile;
    }

    public Task addTask(String description, Priority priority) {
        Task task = new Task(0, description, priority); // id é gerado no DAO
        return taskFile.save(task);
    }


    public List<Task> getAllTasks() {
        return taskFile.listAll();
    }

    public Optional<Task> markTaskAsDone(int id) {
        Optional<Task> optTask = taskFile.listAll()
                .stream()
                .filter(t -> t.getId() == id)
                .findFirst();

        if (optTask.isPresent()) {
            Task task = optTask.get();
            task.markAsDone();
            return taskFile.update(task);
        }
        return Optional.empty();
    }

    public Optional<Task> updateTask(Task task) {
        return taskFile.update(task);
    }

    public Optional<Task> getTaskById(int id) {
        return taskFile.listAll().stream()
                .filter(t -> t.getId() == id)
                .findFirst();
    }


    public boolean deleteTask(int id) {
        return taskFile.delete(id);
    }
}
