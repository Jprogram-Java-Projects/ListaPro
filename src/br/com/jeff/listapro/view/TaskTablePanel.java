package br.com.jeff.listapro.view;

import br.com.jeff.listapro.model.Task;
import br.com.jeff.listapro.util.DateTimeUtils;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.List;

public class TaskTablePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private final JTable tabela;
    private final DefaultTableModel modeloTabela;

    @SuppressWarnings("serial")
	public TaskTablePanel() {
        setLayout(new BorderLayout());

        modeloTabela = new DefaultTableModel(
                new Object[]{"ID", "Descrição", "Prioridade", "Criada em", "Conclusão", "Status"}, 0
        );

        tabela = new JTable(modeloTabela) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabela.setRowHeight(28);
        tabela.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabela.getTableHeader().setBackground(new Color(63, 81, 181));
        tabela.getTableHeader().setForeground(Color.WHITE);

        // Status colorido
        DefaultTableCellRenderer statusRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setFont(new Font("Segoe UI", Font.BOLD, 14));
                
                if ("Concluído".equals(value)) c.setForeground(new Color(0, 128, 0));
                else if ("Em andamento".equals(value)) c.setForeground(new Color(200, 0, 0));
                else c.setForeground(Color.BLACK);
                
                return c;
            }
        };
        tabela.getColumnModel().getColumn(5).setCellRenderer(statusRenderer);

        add(new JScrollPane(tabela), BorderLayout.CENTER);
    }

    public void atualizarTabela(List<Task> tasks) {
        modeloTabela.setRowCount(0);
        for (Task t : tasks) {
            modeloTabela.addRow(new Object[]{
                    t.getId(),
                    t.getDescription(),
                    t.getPriority(),
                    DateTimeUtils.format(t.getDateCreation()),
                    t.getDateConclusion() != null ? DateTimeUtils.format(t.getDateConclusion()) : "",
                    t.isDone() ? "Concluído" : "Em andamento"
            });
        }
    }

    public JTable getTabela() {
        return tabela;
    }

    public DefaultTableModel getModeloTabela() {
        return modeloTabela;
    }
}
