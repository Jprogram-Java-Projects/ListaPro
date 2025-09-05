package br.com.jeff.listapro.dao;

import br.com.jeff.listapro.model.Priority;
import br.com.jeff.listapro.model.Task;

import br.com.jeff.listapro.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskFileDAO implements TaskDAO {

    private final File file;

    public TaskFileDAO(String filePath) {
        this.file = new File(filePath);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao criar arquivo de tarefas", e);
        }
    }

    @Override
    public Task save(Task t) {
        List<Task> tasks = listAll();

        // Gera ID incremental
        int newId = tasks.stream()
                .mapToInt(Task::getId)
                .max()
                .orElse(0) + 1;

        t.setId(newId);
        tasks.add(t);

        persist(tasks);
        return t;
    }

    @Override
    public boolean delete(int id) {
        List<Task> tasks = listAll();
        boolean removed = tasks.removeIf(task -> task.getId() == id);
        if (removed) {
            persist(tasks);
        }
        return removed;
    }

    @Override
    public Optional<Task> update(Task t) {
        List<Task> tasks = listAll();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == t.getId()) {
                tasks.set(i, t);
                persist(tasks);
                return Optional.of(t);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Task> listAll() {
        List<String> lines = FileUtils.readLines(file);
        List<Task> tasks = new ArrayList<>();
        for (String line : lines) {
            tasks.add(parseTask(line));
        }
        return tasks;
    }

    // --- Métodos privados específicos de Task ---
    private void persist(List<Task> tasks) {
        List<String> lines = new ArrayList<>();
        for (Task t : tasks) {
            lines.add(formatTask(t));
        }
        FileUtils.writeLines(file, lines);
    }

    private String formatTask(Task t) {
        return t.getId() + ";" +
               t.getDescription() + ";" +
               t.getDateCreation() + ";" +
               (t.getDateConclusion() != null ? t.getDateConclusion() : "") + ";" +
               t.getPriority().name() + ";" +
               t.isDone();
    }


    private Task parseTask(String line) {
        String[] parts = line.split(";");
        
        int id = Integer.parseInt(parts[0]);
        String description = parts[1];

        // Converte datas gravadas em texto para LocalDateTime
        LocalDateTime dateCreation = LocalDateTime.parse(parts[2]);
        LocalDateTime dateConclusion = parts[3].isEmpty() ? null : LocalDateTime.parse(parts[3]);

        // Converte enum Priority (assumindo que você salvou como nome do enum)
        Priority priority = Priority.valueOf(parts[4]);

        // Converte boolean
        boolean done = Boolean.parseBoolean(parts[5]);
        
        Task t = new Task();
        
        t.setId(id);
        t.setDescription(description);
        t.setDateCreation(dateCreation);
        t.setDateConclusion(dateConclusion);
        t.setPriority(priority);
        t.setDone(done);

        return t;
    }

}
