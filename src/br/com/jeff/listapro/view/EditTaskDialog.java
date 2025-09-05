package br.com.jeff.listapro.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.jeff.listapro.model.Priority;
import br.com.jeff.listapro.model.Task;
import br.com.jeff.listapro.util.UIUtils;

public class EditTaskDialog extends JDialog {
    private JTextField txtDescricao;
    private JComboBox<Priority> cbPrioridade;
    private boolean saved = false;

    public EditTaskDialog(JFrame parent, Task task) {
        super(parent, "Editar Tarefa", true);

        setLayout(new BorderLayout(15, 15));
        getContentPane().setBackground(new Color(250, 250, 250));
        setSize(400, 220);
        setResizable(false);
        setLocationRelativeTo(parent);

        // Painel de formulário
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(250, 250, 250));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblDescricao = new JLabel("Descrição:");
        lblDescricao.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(lblDescricao, gbc);

        txtDescricao = new JTextField(task.getDescription(), 20);
        txtDescricao.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 1; gbc.gridy = 0;
        formPanel.add(txtDescricao, gbc);

        JLabel lblPrioridade = new JLabel("Prioridade:");
        lblPrioridade.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(lblPrioridade, gbc);

        cbPrioridade = new JComboBox<>(Priority.values());
        cbPrioridade.setSelectedItem(task.getPriority());
        cbPrioridade.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 1; gbc.gridy = 1;
        formPanel.add(cbPrioridade, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Painel de botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        buttonPanel.setBackground(new Color(245, 247, 250));
        
        JButton btnSalvar = new JButton("Salvar", UIUtils.loadIcon("save.png"));
        UIUtils.styleButton(btnSalvar, new Color(76, 175, 80));
        
        btnSalvar.addActionListener(e -> {
            task.setDescription(txtDescricao.getText().trim());
            task.setPriority((Priority) cbPrioridade.getSelectedItem());
            saved = true;
            dispose();
        });
        
        
        JButton btnCancelar = new JButton("Cancelar", UIUtils.loadIcon("close.png"));
        UIUtils.styleButton(btnCancelar, new Color(244, 67, 54));
        btnCancelar.addActionListener(e -> dispose());

        buttonPanel.add(btnSalvar);
        buttonPanel.add(btnCancelar);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public boolean isSaved() {
        return saved;
    }
}