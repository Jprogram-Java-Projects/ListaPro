package br.com.jeff.listapro.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.jeff.listapro.controller.TaskController;
import br.com.jeff.listapro.model.Priority;
import br.com.jeff.listapro.util.UIUtils;


public class TaskFormPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private final JTextField txtDescricao;
    private final JComboBox<Priority> cbPrioridade;
    private final JButton btnAdicionar;

    public TaskFormPanel(TaskController controller, Runnable onTaskAdded) {
        setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));
        setBackground(new Color(245, 247, 250));

        txtDescricao = new JTextField(25);
        cbPrioridade = new JComboBox<>(Priority.values());

        
        btnAdicionar = new JButton("Adicionar", UIUtils.loadIcon("add.png"));
        UIUtils.styleButton(btnAdicionar, new Color(33, 150, 243));
        btnAdicionar.setPreferredSize(new Dimension(130, 35));

        btnAdicionar.addActionListener(e -> {
            String descricao = txtDescricao.getText().trim();
            Priority prioridade = (Priority) cbPrioridade.getSelectedItem();
            if (!descricao.isEmpty() && prioridade != null) {
                controller.addTask(descricao, prioridade);
                onTaskAdded.run(); // chama callback (ex: recarregar tabela)
                txtDescricao.setText("");
            }
        });

        add(new JLabel("Descrição:"));
        add(txtDescricao);
        add(new JLabel("Prioridade:"));
        add(cbPrioridade);
        add(btnAdicionar);
    }
}
