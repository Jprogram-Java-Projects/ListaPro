package br.com.jeff.listapro;

import javax.swing.SwingUtilities;

import br.com.jeff.listapro.controller.TaskController;
import br.com.jeff.listapro.dao.TaskFileDAO;
import br.com.jeff.listapro.view.TaskView;

public class Main {
    public static void main(String[] args) {
    		TaskFileDAO dao = new TaskFileDAO("tasks.txt");
        TaskController controller = new TaskController(dao);
        SwingUtilities.invokeLater(() -> {
            TaskView view = new TaskView(controller);
            view.setVisible(true);
        });
    }
}
