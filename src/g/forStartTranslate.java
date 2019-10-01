package g;

import java.awt.Button;
import java.awt.Choice;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Translate.PapagoNMT;

public class forStartTranslate extends JFrame {

	private JPanel contentPane;
	private JTextField tfFrom;
	private JTextField tfTo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					forStartTranslate frame = new forStartTranslate();
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
	public forStartTranslate() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfFrom = new JTextField();
		tfFrom.setBounds(12, 182, 410, 87);
		contentPane.add(tfFrom);
		tfFrom.setColumns(10);
		
		tfTo = new JTextField();
		tfTo.setColumns(10);
		tfTo.setBounds(12, 302, 410, 87);
		contentPane.add(tfTo);
		
		Choice dbFrom = new Choice();
		dbFrom.setBounds(12, 155, 92, 21);
		dbFrom.add("ko");
		dbFrom.add("en");
		contentPane.add(dbFrom);
		
		Choice dbTo = new Choice();
		dbTo.setBounds(12, 275, 92, 21);
		
		dbTo.add("ko");
		dbTo.add("en");
		contentPane.add(dbTo);
		
		Button doTl = new Button("New button");
		doTl.setBounds(346, 395, 76, 23);
		contentPane.add(doTl);
		doTl.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String OriginalText = tfFrom.getText();
				String source = dbFrom.getSelectedItem();
				String target =dbTo.getSelectedItem();
				
				PapagoNMT nmt = new PapagoNMT( OriginalText, source,  target);
				String str = nmt.str;
				System.out.println(str);
				tfTo.setText(str);

				
			}
		});
	}
}
