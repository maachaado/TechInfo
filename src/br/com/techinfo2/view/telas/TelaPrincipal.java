package br.com.techinfo2.view.telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import net.miginfocom.swing.MigLayout;

public class TelaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
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
	public TelaPrincipal() {
		setTitle("TECHINFO SYSTEM");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.LIGHT_GRAY);
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu(" CADASTROS");
		mnNewMenu.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/resourse/points.png")));
		mnNewMenu.setBackground(Color.LIGHT_GRAY);
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("CLIENTE");
		mntmNewMenuItem.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/resourse/cliente.png")));
		mntmNewMenuItem.setBackground(Color.LIGHT_GRAY);
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaListaCliente tlc = new TelaListaCliente();
				tlc.setVisible(true);
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("SERVIÇO");
		mntmNewMenuItem_1.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/resourse/servico.png")));
		mntmNewMenuItem_1.setBackground(Color.LIGHT_GRAY);
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaListaServico tls = new TelaListaServico();
				tls.setVisible(rootPaneCheckingEnabled);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("ESTOQUE");
		mntmNewMenuItem_2.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/resourse/estoque-pronto.png")));
		mntmNewMenuItem_2.setBackground(Color.LIGHT_GRAY);
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				TelaListaEstoque tle = new TelaListaEstoque();
				tle.setVisible(true);
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("EXTERNO");
		mntmNewMenuItem_3.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/resourse/link-externo.png")));
		mntmNewMenuItem_3.setBackground(Color.LIGHT_GRAY);
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaListaExterno tle = new TelaListaExterno();
				tle.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_3);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[263px][][][][][140px]", "[86px][26px][128px][16px][][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("TECHINFO");
		lblNewLabel.setFont(new Font("Impact", Font.PLAIN, 70));
		contentPane.add(lblNewLabel, "cell 1 0,alignx left,aligny top");
		
		JLabel lblNewLabel_2 = new JLabel("TECHNOLOGIC SOLUTIONS");
		lblNewLabel_2.setFont(new Font("Impact", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_2, "cell 1 1,alignx center,aligny top");
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/resourse/cpu (2).png")));
		contentPane.add(lblNewLabel_3, "cell 1 2,alignx center,aligny top");
		
		JLabel lblNewLabel_1 = new JLabel("DESIGNED BY JOÃO HENRIQUE ");
		lblNewLabel_1.setFont(new Font("Impact", Font.PLAIN, 12));
		contentPane.add(lblNewLabel_1, "cell 5 9,alignx left,aligny bottom");
	}

}
