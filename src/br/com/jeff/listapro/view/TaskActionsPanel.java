package br.com.jeff.listapro.view;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import br.com.jeff.listapro.controller.TaskController;
import br.com.jeff.listapro.util.UIUtils;

public class TaskActionsPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public TaskActionsPanel(TaskController controller, TaskTablePanel tablePanel, Runnable onAction) {
        setLayout(new FlowLayout(FlowLayout.CENTER, 30, 15));
        setBackground(new Color(245, 247, 250));
        

        JButton btnConcluir = new JButton("Concluir", UIUtils.loadIcon("check.png"));
        UIUtils.styleButton(btnConcluir, new Color(76, 175, 80));
        
        btnConcluir.addActionListener(e -> {
            int row = tablePanel.getTabela().getSelectedRow();
            if (row >= 0) {
                int id = (int) tablePanel.getModeloTabela().getValueAt(row, 0);
                controller.markTaskAsDone(id);
                onAction.run();
            }
        });
        
        JButton btnEditar = new JButton("Editar", UIUtils.loadIcon("edit.png"));
        UIUtils.styleButton(btnEditar, new Color(255, 193, 7));
        
        
        tablePanel.getTabela().getSelectionModel().addListSelectionListener(e -> {
            boolean linhaSelecionada = tablePanel.getTabela().getSelectedRow() >= 0;
            btnEditar.setEnabled(linhaSelecionada);
        });
        btnEditar.setEnabled(false); // começa desativado
        
        btnEditar.addActionListener(e -> {
            int row = tablePanel.getTabela().getSelectedRow();
            if (row >= 0) {
                int id = (int) tablePanel.getModeloTabela().getValueAt(row, 0);
                controller.getTaskById(id).ifPresent(task -> {
                    EditTaskDialog dialog = new EditTaskDialog(null, task); // null como parent, ou passe a janela principal se quiser modal
                    dialog.setVisible(true);

                    if (dialog.isSaved()) {
                        controller.updateTask(task); // atualiza via controller
                        onAction.run();             // recarrega a tabela
                    }
                });
            }
        });
        
        
        JButton btnExcluir = new JButton("Excluir", UIUtils.loadIcon("delete.png"));
        UIUtils.styleButton(btnExcluir, new Color(244, 67, 54));
        
        btnExcluir.addActionListener(e -> {
            int row = tablePanel.getTabela().getSelectedRow();
            if (row >= 0) {
                int id = (int) tablePanel.getModeloTabela().getValueAt(row, 0);
                controller.deleteTask(id);
                onAction.run();
            }
        });

        add(btnConcluir);
        add(btnEditar);
        add(btnExcluir);
    }
}
