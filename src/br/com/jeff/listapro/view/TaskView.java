package br.com.jeff.listapro.view;

import br.com.jeff.listapro.controller.TaskController;
import br.com.jeff.listapro.model.Task;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;

public class TaskView extends JFrame {
    private final TaskController controller;
    private final TaskTablePanel tablePanel;

    public TaskView(TaskController controller) {
        this.controller = controller;

        setTitle("ListaPro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(766, 575);
        setLocationRelativeTo(null);

        getContentPane().setLayout(new BorderLayout(10, 10));

        tablePanel = new TaskTablePanel();

        TaskFormPanel formPanel = new TaskFormPanel(controller, this::carregarTabela);
        TaskActionsPanel actionsPanel = new TaskActionsPanel(controller, tablePanel, this::carregarTabela);

        getContentPane().add(formPanel, BorderLayout.NORTH);
        getContentPane().add(tablePanel, BorderLayout.CENTER);
        getContentPane().add(actionsPanel, BorderLayout.SOUTH);

        carregarTabela();
    }

    private void carregarTabela() {
        List<Task> tasks = controller.getAllTasks();
        tablePanel.atualizarTabela(tasks);
    }
}
