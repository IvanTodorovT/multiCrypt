package tes3;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.CardLayout;

import javax.swing.AbstractButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.ShutdownChannelGroupException;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class multi {

	private JFrame frame;
	private JPanel panelCaesar;
	private JPanel panelMenu;
	private JPanel panelVigenere;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	int offset = 3;
	private JTextField textField_6;

	public String encrypt(String s) throws IOException {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char t = s.charAt(i);
			if (t >= 'A' && t <= 'Z') {
				int t1 = t - 'A' + offset;
				t1 = t1 % 26;
				sb.append((char) (t1 + 'A'));
			} else if (t >= 'a' && t <= 'z') {
				int t1 = t - 'a' + offset;
				t1 = t1 % 26;
				sb.append((char) (t1 + 'a'));
			} else if (t >= 'ภ' && t <= '฿') {
				int t1 = t - 'ภ' + offset;
				t1 = t1 % 30;
				sb.append((char) (t1 + 'ภ'));
			} else if (t >= 'เ' && t <= 'ÿ') {
				int t1 = t - 'เ' + offset;
				t1 = t1 % 30;
				sb.append((char) (t1 + 'เ'));
			}

			else if (t >= '0' && t <= '9') {
				int t1 = t - '0' + offset;
				t1 = t1 % 10;
				sb.append((char) (t1 + '0'));
			}

			else if (t == ' ') {
				int t1 = 0;
				sb.append((char) (t1 + ':'));
			}

			else if (t == '.') {
				int t1 = 0;
				sb.append((char) (t1 + '/'));
			}

			else if (t == ',') {
				int t1 = 0;
				sb.append((char) (t1 + ' '));
			}

			else if (t == ':') {
				int t1 = 0;
				sb.append((char) (t1 + '.'));
			}

			else if (t == '/') {
				int t1 = 0;
				sb.append((char) (t1 + ','));
			} else {
				JOptionPane.showMessageDialog(null, "Wrong symbol");
			}

		}
		return sb.toString();
	}

	public String decrypt(String s) throws IOException {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char t = s.charAt(i);
			if (t >= 'A' && t <= 'Z') {
				int t1 = t - 'A' - offset;
				if (t1 < 0)
					t1 = 26 + t1;
				sb.append((char) (t1 + 'A'));
			} else if (t >= 'a' && t <= 'z') {
				int t1 = t - 'a' - offset;
				if (t1 < 0)
					t1 = 26 + t1;
				sb.append((char) (t1 + 'a'));
			}

			else if (t >= 'ภ' && t <= '฿') {
				int t1 = t - 'ภ' - offset;
				if (t1 < 0)
					t1 = 30 + t1;
				sb.append((char) (t1 + 'ภ'));
			} else if (t >= 'เ' && t <= 'ÿ') {
				int t1 = t - 'เ' - offset;
				if (t1 < 0)
					t1 = 30 + t1;
				sb.append((char) (t1 + 'เ'));
			}

			else if (t >= '0' && t <= '9') {
				int t1 = t - '0' - offset;
				if (t1 < 0)
					t1 = 10 + t1;
				sb.append((char) (t1 + '0'));
			}

			else if (t == '.') {
				int t1 = 0;
				sb.append((char) (t1 + ':'));
			}

			else if (t == ',') {
				int t1 = 0;
				sb.append((char) (t1 + '/'));
			}

			else if (t == ':') {
				int t1 = 0;
				sb.append((char) (t1 + ' '));
			}

			else if (t == '/') {
				int t1 = 0;
				sb.append((char) (t1 + '.'));
			}

			else {
				JOptionPane.showMessageDialog(null, "Wrong symbol");
			}

		}

		return sb.toString();
	}

	static String encrypt2(String text, final String key) {
		String res = "";
		// text = text.toUpperCase();
		// text=text.toLowerCase();

		for (int i = 0, j = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (c < 'ภ' || c > '฿')
				continue;
			res += (char) ((c + key.charAt(j) - 2 * 'ภ') % 30 + 'ภ');
			j = ++j % key.length();
		}
		
		for (int i = 0, j = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (c < 'เ' || c > 'ÿ')
				continue;
			res += (char) ((c + key.charAt(j) - 2 * 'เ') % 30 + 'เ');
			j = ++j % key.length();
		}

		for (int i = 0, j = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (c < 'A' || c > 'Z')
				continue;
			res += (char) ((c + key.charAt(j) - 2 * 'A') % 26 + 'A');
			j = ++j % key.length();
		}

		for (int i = 0, j = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (c < 'a' || c > 'z')
				continue;
			res += (char) ((c + key.charAt(j) - 2 * 'a') % 26 + 'a');
			j = ++j % key.length();

		}

		return res;
	}

	static String decrypt2(String text, final String key) {
		String res = "";
		// text = text.toUpperCase();
		for (int i = 0, j = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (c < 'ภ' || c > '฿')
				continue;
			res += (char) ((c - key.charAt(j) + 30) % 30 + 'ภ');
			j = ++j % key.length();
		}

		for (int i = 0, j = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (c < 'เ' || c > 'ÿ')
				continue;
			res += (char) ((c - key.charAt(j) + 30) % 30 + 'เ');
			j = ++j % key.length();
		}

		for (int i = 0, j = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (c < 'A' || c > 'Z')
				continue;
			res += (char) ((c - key.charAt(j) + 26) % 26 + 'A');
			j = ++j % key.length();
		}

		for (int i = 0, j = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (c < 'a' || c > 'z')
				continue;
			res += (char) ((c - key.charAt(j) + 26) % 26 + 'a');
			j = ++j % key.length();
		}

		return res;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					multi window = new multi();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public multi() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		JPanel panelMenu = new JPanel();
		frame.getContentPane().add(panelMenu, "name_8480549562663");
		panelMenu.setLayout(null);

		JPanel panelCaesar = new JPanel();
		frame.getContentPane().add(panelCaesar, "name_8485458085143");
		panelCaesar.setLayout(null);
		panelCaesar.setVisible(false);

		JPanel panelVigenere = new JPanel();
		frame.getContentPane().add(panelVigenere, "name_8490665583484");
		panelVigenere.setLayout(null);
		panelVigenere.setVisible(false);

		textField_3 = new JTextField();
		textField_3.setBounds(63, 70, 86, 20);
		panelVigenere.add(textField_3);
		textField_3.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("KEY");
		lblNewLabel_2.setBounds(63, 45, 46, 14);
		panelVigenere.add(lblNewLabel_2);

		JButton btnNewButton_5 = new JButton("cript");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String str = textField_3.getText();
				String str2 = textField_6.getText();

				// System.out.println("ehoooo"+str);
				// System.out.println("qhuuuuuuuuuuu"+str2);

				textField_4.setText(encrypt2(str2, str));

				System.out.println("มห฿฿฿ " + encrypt2(str2, str));

			}

		});
		btnNewButton_5.setBounds(44, 170, 89, 23);
		panelVigenere.add(btnNewButton_5);

		JButton btnNewButton_6 = new JButton("decript");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				textField_5.setText(decrypt2(textField_6.getText(),
						textField_3.getText()));

			}
		});
		btnNewButton_6.setBounds(288, 170, 89, 23);
		panelVigenere.add(btnNewButton_6);

		textField_4 = new JTextField();
		textField_4.setBounds(10, 204, 217, 47);
		panelVigenere.add(textField_4);
		textField_4.setColumns(10);

		textField_5 = new JTextField();
		textField_5.setBounds(237, 204, 176, 20);
		panelVigenere.add(textField_5);
		textField_5.setColumns(10);

		JButton btnNewButton_7 = new JButton("back");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelVigenere.setVisible(false);
				panelMenu.setVisible(true);
				panelCaesar.setVisible(false);
			}
		});
		btnNewButton_7.setBounds(10, 11, 89, 23);
		panelVigenere.add(btnNewButton_7);

		textField_6 = new JTextField();
		textField_6.setBounds(237, 70, 176, 20);
		panelVigenere.add(textField_6);
		textField_6.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("MSSG");
		lblNewLabel_3.setBounds(237, 45, 46, 14);
		panelVigenere.add(lblNewLabel_3);

		JLabel lblNewLabel = new JLabel(
				"\u0418\u0437\u0431\u0435\u0440\u0435\u0442\u0435 \u043A\u0440\u0438\u043F\u0442\u043E\u0433\u0440\u0430\u0444\u0441\u043A\u0438 \u043C\u0435\u0442\u043E\u0434!");
		lblNewLabel.setBounds(154, 54, 237, 52);
		panelMenu.add(lblNewLabel);

		JButton btnNewButton = new JButton("\u0426\u0435\u0437\u0430\u0440");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelCaesar.setVisible(true);
				panelMenu.setVisible(false);
				panelVigenere.setVisible(false);
			}
		});
		btnNewButton.setBounds(82, 141, 89, 23);
		panelMenu.add(btnNewButton);

		JButton btnNewButton_1 = new JButton(
				"\u0412\u0438\u0436\u0435\u043D\u0435\u0440");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelCaesar.setVisible(false);
				panelMenu.setVisible(false);
				panelVigenere.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(267, 141, 89, 23);
		panelMenu.add(btnNewButton_1);

		textField = new JTextField();

		textField.setBounds(145, 65, 173, 20);
		panelCaesar.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Vuvedete");
		lblNewLabel_1.setBounds(161, 40, 46, 14);
		panelCaesar.add(lblNewLabel_1);

		JButton btnNewButton_2 = new JButton("Cript");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String str = textField.getText();
				try {
					textField_1.setText(encrypt(str));
					// textField_2.setText(str);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnNewButton_2.setBounds(59, 149, 89, 23);
		panelCaesar.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Decript");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String str2 = textField.getText();
				try {
					textField_2.setText(decrypt(str2));
					// textField_2.setText(str2);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnNewButton_3.setBounds(264, 149, 89, 23);
		panelCaesar.add(btnNewButton_3);

		textField_1 = new JTextField();
		textField_1.setBounds(62, 213, 86, 20);
		panelCaesar.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(267, 213, 86, 20);
		panelCaesar.add(textField_2);
		textField_2.setColumns(10);

		JButton btnNewButton_4 = new JButton("back");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCaesar.setVisible(false);
				panelMenu.setVisible(true);
				panelVigenere.setVisible(false);
			}
		});
		btnNewButton_4.setBounds(0, 11, 89, 23);
		panelCaesar.add(btnNewButton_4);

	}
}
