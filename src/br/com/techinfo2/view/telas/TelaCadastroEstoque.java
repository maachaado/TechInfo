package br.com.techinfo2.view.telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.techinfo2.core.entity.EstoqueEntity;
import br.com.techinfo2.core.service.EstoqueService;
import br.com.techinfo2.util.NegocioException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;

public class TelaCadastroEstoque extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fieldPm;
	private JTextField fieldCpu;
	private JTextField fieldFonte;
	private JTextField fieldMem;
	private JTextField fieldPv;
	private JButton btnSalvar;
	private JTextField fieldCodigo;
	private JButton btnVoltar;
	JLabel labelTitulo;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroEstoque frame = new TelaCadastroEstoque();
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
	public TelaCadastroEstoque() {
		setResizable(false);
		setTitle("TECHINFO - ESTOQUE");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 440, 310);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		labelTitulo = new JLabel("CADASTRO DE ESTOQUE ");
		labelTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		btnSalvar = new JButton("SALVAR");
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSalvar.setBackground(Color.GREEN);
		btnSalvar.setForeground(new Color(0, 0, 0));
		btnSalvar.setIcon(new ImageIcon(TelaCadastroEstoque.class.getResource("/resourse/salvar-silhueta-de-icone.png")));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EstoqueEntity est = new EstoqueEntity();
				est.setPlacaMae(fieldPm.getText());
				est.setProcessador(fieldCpu.getText());
				est.setFonte(fieldFonte.getText());
				est.setMemoria(fieldMem.getText());
				est.setPlacaVideo(fieldPv.getText());
				
				String msg = null;
						
				try {
					if(fieldCodigo.getText().equals("")) {
					msg = new EstoqueService().salvarEstoque(est);
					}else {
					est.setCodigo(Long.parseLong(fieldCodigo.getText()));
					msg = new EstoqueService().alterarEstoque(est);
					}
				
				
				limparCampo();
				JOptionPane.showMessageDialog(btnSalvar, msg);
				
				TelaListaEstoque tle = new TelaListaEstoque();
				tle.setVisible(true);
				dispose();
				
				} catch (NegocioException e1) {
					JOptionPane.showMessageDialog(null, e1.getMensagemDeErro() , "ERRO", JOptionPane.ERROR_MESSAGE);
				}
			}	
			});										
		
		JLabel lblNewLabel_1 = new JLabel("PLACA DE VÍDEO:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblNewLabel_2 = new JLabel("MEMÓRIAS:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblNewLabel_3 = new JLabel("FONTE:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblNewLabel_4 = new JLabel("PROCESSADOR:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblNewLabel_5 = new JLabel("PLACA MÃE:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		fieldPm = new JTextField();
		fieldPm.setFont(new Font("Tahoma", Font.BOLD, 11));
		fieldPm.setBackground(SystemColor.inactiveCaptionBorder);
		fieldPm.setColumns(10);
		
		fieldCpu = new JTextField();
		fieldCpu.setFont(new Font("Tahoma", Font.BOLD, 11));
		fieldCpu.setBackground(SystemColor.inactiveCaptionBorder);
		fieldCpu.setColumns(10);
		
		fieldFonte = new JTextField();
		fieldFonte.setFont(new Font("Tahoma", Font.BOLD, 11));
		fieldFonte.setBackground(SystemColor.inactiveCaptionBorder);
		fieldFonte.setColumns(10);
		
		fieldMem = new JTextField();
		fieldMem.setFont(new Font("Tahoma", Font.BOLD, 11));
		fieldMem.setBackground(SystemColor.inactiveCaptionBorder);
		fieldMem.setColumns(10);
		
		fieldPv = new JTextField();
		fieldPv.setFont(new Font("Tahoma", Font.BOLD, 11));
		fieldPv.setBackground(SystemColor.inactiveCaptionBorder);
		fieldPv.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("CÓDIGO:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_6.setBackground(Color.BLACK);
		
		fieldCodigo = new JTextField();
		fieldCodigo.setFont(new Font("Tahoma", Font.BOLD, 11));
		fieldCodigo.setBackground(SystemColor.inactiveCaptionBorder);
		fieldCodigo.setEnabled(false);
		fieldCodigo.setColumns(10);
		
		btnVoltar = new JButton("VOLTAR");
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnVoltar.setForeground(new Color(0, 0, 0));
		btnVoltar.setBackground(Color.CYAN);
		btnVoltar.setIcon(new ImageIcon(TelaCadastroEstoque.class.getResource("/resourse/volte.png")));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaListaEstoque tle = new TelaListaEstoque();
				tle.setVisible(true);
				dispose();
				
			}
		});
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TelaCadastroEstoque.class.getResource("/resourse/estoque-pronto 2.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(114)
					.addComponent(btnVoltar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSalvar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(144))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(110)
					.addComponent(labelTitulo, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
					.addGap(127))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_4)
						.addComponent(lblNewLabel_3)
						.addComponent(lblNewLabel_2)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel_5)
						.addComponent(lblNewLabel_6))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(fieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(230, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(fieldPm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(fieldPv, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(230, Short.MAX_VALUE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(fieldMem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(230, Short.MAX_VALUE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(fieldCpu)
									.addComponent(fieldFonte))
								.addGap(92)
								.addComponent(lblNewLabel)
								.addContainerGap(92, Short.MAX_VALUE)))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addComponent(labelTitulo)
					.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_6)
						.addComponent(fieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5)
						.addComponent(fieldPm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_4)
								.addComponent(fieldCpu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_3)
								.addComponent(fieldFonte, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(11)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_2)
								.addComponent(fieldMem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1)
								.addComponent(fieldPv, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblNewLabel))
					.addGap(17)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSalvar)
						.addComponent(btnVoltar))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
	private void limparCampo() {
		fieldCodigo.setText("");
		fieldPm.setText("");
		fieldCpu.setText("");
		fieldFonte.setText("");
		fieldMem.setText("");
		fieldPv.setText("");
	}
	
	public void buscarPorId(Long codigoEstoque) {
		
		try {
			
			EstoqueEntity estoqueEncontrado = new EstoqueService().buscarPorId(codigoEstoque);
			
			if(estoqueEncontrado == null) {
				JOptionPane.showMessageDialog(null, "ERRO AO BUSCAR ESTOQUE", "Erro", JOptionPane.ERROR_MESSAGE);
				
			}else {
				fieldCodigo.setText("" + estoqueEncontrado.getCodigo());
				fieldPm.setText(estoqueEncontrado.getPlacaMae());
				fieldCpu.setText(estoqueEncontrado.getProcessador());
				fieldFonte.setText(estoqueEncontrado.getFonte());
				fieldMem.setText(estoqueEncontrado.getMemoria());
				fieldPv.setText(estoqueEncontrado.getPlacaVideo());
			}
			
			labelTitulo.setText("ALTERAÇÃO DE ESTOQUE");
		} catch (NegocioException e) {
			JOptionPane.showMessageDialog(null, e.getMensagemDeErro(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
