package br.com.techinfo2.view.telas;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import br.com.techinfo2.core.entity.ExternoEntity;
import br.com.techinfo2.core.service.ExternoService;
import br.com.techinfo2.util.NegocioException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;

public class TelaListaExterno extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private List<ExternoEntity> externos;
	private JButton btnExcluir;
	private JButton btnAdc;
	private JLabel lblNewLabel;
	private JTextField fieldCodigo;
	private JButton btnPesquisar;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListaExterno frame = new TelaListaExterno();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaListaExterno() {
		setTitle("TECHINFO - EXTERNO");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		btnExcluir = new JButton("EXCLUIR");
		btnExcluir.setForeground(Color.BLACK);
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExcluir.setBackground(Color.RED);
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ExternoEntity externoSelecionado = externos.get(table.getSelectedRow());
				if(JOptionPane.showConfirmDialog(null, "DESEJA EXCLUIR O EXTERNO: " + externoSelecionado.getCodigo()) == JOptionPane.OK_OPTION) {
					
					try {
						new ExternoService().excluirExterno(externoSelecionado.getCodigo());
						popularTabela();
					} catch (NegocioException e1) {
						JOptionPane.showMessageDialog(null, e1.getMensagemDeErro());
					}
				}														
			}
		});
				
		JButton btnEditar = new JButton("EDITAR");
		btnEditar.setForeground(Color.BLACK);
		btnEditar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEditar.setBackground(Color.YELLOW);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				ExternoEntity externoSelecionado = externos.get(table.getSelectedRow());
				TelaCadastroExterno tcex = new TelaCadastroExterno();
				tcex.buscarPorId(externoSelecionado.getCodigo());
				tcex.setVisible(true);		
				dispose();
			}
		});
		btnEditar.setEnabled(false);
		
		btnAdc = new JButton("ADICIONAR");
		btnAdc.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAdc.setForeground(Color.BLACK);
		btnAdc.setBackground(Color.GREEN);
		btnAdc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroExterno tce = new TelaCadastroExterno();
				tce.setVisible(true);
				dispose();		
				}
		});
		
		lblNewLabel = new JLabel("CÓDIGO");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		fieldCodigo = new JTextField();
		fieldCodigo.setFont(new Font("Tahoma", Font.BOLD, 11));
		fieldCodigo.setColumns(10);
		
		btnPesquisar = new JButton("PESQUISAR");
		btnPesquisar.setIcon(new ImageIcon(TelaListaExterno.class.getResource("/resourse/lupa-de-pesquisa.png")));
		btnPesquisar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ExternoEntity externoFiltro = new ExternoEntity();
				
				try {
					if(!fieldCodigo.getText().equals("")) {
				Long codigo = Long.parseLong(fieldCodigo.getText());
				externoFiltro.setCodigo(codigo);
		        }
				}catch(Exception e2) {
					JOptionPane.showMessageDialog(null, "O VALOR DO CAMPO CÓDIGO PRECISA SER NUMÉRICO");
				}
				popularTabelaFiltrada(externoFiltro);
			}
		});
		
		lblNewLabel_1 = new JLabel("PESQUISA:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 556, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnAdc)
							.addPreferredGap(ComponentPlacement.RELATED, 315, Short.MAX_VALUE)
							.addComponent(btnEditar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnExcluir))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(fieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnPesquisar))
						.addComponent(lblNewLabel_1))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnExcluir)
								.addComponent(btnEditar)
								.addComponent(btnAdc))
							.addGap(17)
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel)
								.addComponent(fieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(btnPesquisar))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ExternoEntity externo = externos.get(table.getSelectedRow());
				JOptionPane.showMessageDialog(null, "CÓDIGO: " + externo.getCodigo() + "\n O ITEN SELECIONADO FOI: " + externo.getVisitas());
				
				btnExcluir.setEnabled(true);
				btnEditar.setEnabled(true);
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00D3DIGO:", "VISITAS/AGENDAMENTOS"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(1).setPreferredWidth(370);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		popularTabela();
	}

	private void popularTabela() {
		try {
			externos = new ExternoService().listarExterno();
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			model.getDataVector().removeAllElements();
			for (ExternoEntity externoEntity : externos) {
				model.addRow(new Object[] {externoEntity.getCodigo(),
						externoEntity.getVisitas()});
			}
			
		} catch (NegocioException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERRO AO LISTAS EXTERNO" + e.getMensagemDeErro());
		}				
    }
	private void popularTabelaFiltrada(ExternoEntity externoFiltro) {
		try {
			externos = new ExternoService().buscarFiltrado(externoFiltro);
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			model.getDataVector().removeAllElements();
			for (ExternoEntity externoEntity : externos) {
				model.addRow(new Object[] {externoEntity.getCodigo(),
						externoEntity.getVisitas()});
			}
			
		} catch (NegocioException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERRO AO LISTAS EXTERNO" + e.getMensagemDeErro());
		}				
}
}
