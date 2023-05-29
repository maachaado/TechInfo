package br.com.techinfo2.view.telas;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.techinfo2.core.entity.ClienteEntity;
import br.com.techinfo2.core.service.ClienteService;
import br.com.techinfo2.util.NegocioException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class TelaCadastroCliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fieldCodigo;
	private JTextField fieldCpf;
	private JTextField fieldEndereco;
	private JTextField fieldNome;
	private JTextField fieldTelefone;
	private JLabel labelTitulo;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroCliente frame = new TelaCadastroCliente();
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
	public TelaCadastroCliente() {
		setResizable(false);
		setTitle("TECHINFO - CLIENTE");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 468, 310);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		labelTitulo = new JLabel("CADASTRO DE CLIENTES ");
		labelTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblNewLabel_1 = new JLabel("CÓDIGO:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JButton btnSalvar = new JButton("SALVAR");
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSalvar.setForeground(new Color(0, 0, 0));
		btnSalvar.setBackground(Color.GREEN);
		btnSalvar.setIcon(new ImageIcon(TelaCadastroCliente.class.getResource("/resourse/salvar-silhueta-de-icone.png")));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteEntity cli = new ClienteEntity();
				cli.setNome(fieldNome.getText());
				cli.setCpf(fieldCpf.getText());
				cli.setEndereco(fieldEndereco.getText());
				cli.setTelefone(fieldTelefone.getText());
				
				String msg = null;
						
				try {
					
				if(fieldCodigo.getText().equals("")) {
				msg = new ClienteService().salvarCliente(cli);
				}else {
					cli.setCodigo(Long.parseLong(fieldCodigo.getText()));
					msg = new ClienteService().alterarCliente(cli);
				}							
				limparCampo();
				JOptionPane.showMessageDialog(btnSalvar, msg);
				
				TelaListaCliente tlc = new TelaListaCliente();
				tlc.setVisible(true);
				dispose();
				
				} catch (NegocioException e1) {
					JOptionPane.showMessageDialog(null, e1.getMensagemDeErro() , "ERRO", JOptionPane.ERROR_MESSAGE);
				}
			}	
			});
		
		JLabel lblNewLabel_2 = new JLabel("NOME: ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblNewLabel_3 = new JLabel("CPF:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblNewLabel_4 = new JLabel("ENDEREÇO:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblNewLabel_5 = new JLabel("TELEFONE:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		fieldCodigo = new JTextField();
		fieldCodigo.setFont(new Font("Tahoma", Font.BOLD, 11));
		fieldCodigo.setBackground(SystemColor.inactiveCaptionBorder);
		fieldCodigo.setEnabled(false);
		fieldCodigo.setEditable(false);
		fieldCodigo.setColumns(10);
		
		fieldCpf = new JTextField();
		fieldCpf.setFont(new Font("Tahoma", Font.BOLD, 11));
		fieldCpf.setBackground(SystemColor.inactiveCaptionBorder);
		fieldCpf.setColumns(10);
		
		fieldEndereco = new JTextField();
		fieldEndereco.setFont(new Font("Tahoma", Font.BOLD, 11));
		fieldEndereco.setBackground(SystemColor.inactiveCaptionBorder);
		fieldEndereco.setColumns(10);
		
		fieldNome = new JTextField();
		fieldNome.setFont(new Font("Tahoma", Font.BOLD, 11));
		fieldNome.setBackground(SystemColor.inactiveCaptionBorder);
		fieldNome.setColumns(10);
		
		fieldTelefone = new JTextField();
		fieldTelefone.setFont(new Font("Tahoma", Font.BOLD, 11));
		fieldTelefone.setBackground(SystemColor.inactiveCaptionBorder);
		fieldTelefone.setColumns(10);
		
		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnVoltar.setForeground(new Color(0, 0, 0));
		btnVoltar.setBackground(Color.CYAN);
		btnVoltar.setIcon(new ImageIcon(TelaCadastroCliente.class.getResource("/resourse/volte.png")));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaListaCliente tlc = new TelaListaCliente();
				tlc.setVisible(true);
				dispose();
			}
		});
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TelaCadastroCliente.class.getResource("/resourse/cliente 2.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_1)
										.addComponent(lblNewLabel_4)
										.addComponent(lblNewLabel_3)
										.addComponent(lblNewLabel_2))
									.addGap(14)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
											.addComponent(fieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(fieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(fieldCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addComponent(fieldEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_5)
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(30)
											.addComponent(btnVoltar)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(btnSalvar))
										.addComponent(fieldTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
							.addPreferredGap(ComponentPlacement.RELATED, 4, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(119)
							.addComponent(labelTitulo, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(58))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addComponent(labelTitulo)
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1)
								.addComponent(fieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(fieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(fieldCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_3))
							.addPreferredGap(ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_4)
								.addComponent(fieldEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(11)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_5)
								.addComponent(fieldTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(21)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnVoltar)
								.addComponent(btnSalvar))))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void limparCampo() {
		fieldCodigo.setText("");
		fieldNome.setText("");
		fieldCpf.setText("");
		fieldEndereco.setText("");
		fieldTelefone.setText("");
	}
	
	public void buscarPorId(Long codigoCliente) {
	try {
		ClienteEntity clienteEncontrado = new ClienteService().buscarPorId(codigoCliente);
		if(clienteEncontrado == null) {
			JOptionPane.showMessageDialog(null, "USUÁRIO NÃO LOCALIZADO", "erro", JOptionPane.ERROR_MESSAGE);
		}else {
			fieldCodigo.setText("" + clienteEncontrado.getCodigo());
			fieldNome.setText(clienteEncontrado.getNome());
			fieldCpf.setText(clienteEncontrado.getCpf());
			fieldEndereco.setText(clienteEncontrado.getEndereco());
			fieldTelefone.setText(clienteEncontrado.getTelefone());
			
		}
		
		labelTitulo.setText("ALTERAÇÃO DE CLIENTE");
		
	} catch (NegocioException e) {
		e.printStackTrace();
		JOptionPane.showMessageDialog(null, e.getMensagemDeErro(), "erro", JOptionPane.ERROR_MESSAGE);
	}
	
}}
