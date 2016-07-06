/**
 * 
 */
package GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.text.*;

/**
 * @author Meng Guo 3/24/2016
 */
public class FileContentDisplay extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2344241662178499800L;

	BufferedReader br;
	JButton chooseFile; // Button for choose file
	JFileChooser jChooser = new JFileChooser("e:/"); // initialization path
	JPanel jPanel1, jPanel2, jPanel3; // North, Center, South Panel
	TextField jTextField; // for search
	JTextArea jTextArea; // for display contents
	JLabel j1, j2, j3; // label for button, textarea, search field
	Font font;
	Highlighter highlighter = null;

	public FileContentDisplay() {
		// TODO Auto-generated constructor stub
		jTextArea = new JTextArea();
		jTextField = new TextField(14);
		jTextArea.setLineWrap(true);
		chooseFile = new JButton("Choose File");
		font = new Font("Microsoft YaHei", Font.BOLD, 12);
		chooseFile.setFont(font);
		j1 = new JLabel("Please choose File for display: ");
		j2 = new JLabel("Display Area: ");
		j3 = new JLabel("Search: ");
		j1.setFont(font);
		j2.setFont(font);
		j3.setFont(font);

		jPanel1 = new JPanel();
		jPanel2 = new JPanel();
		jPanel3 = new JPanel();

		jPanel1.setBackground(new Color(204, 204, 255));
		jPanel2.setBackground(Color.white);
		jPanel3.setBackground(new Color(204, 255, 204));

		jPanel1.setLayout(new FlowLayout());
		jPanel2.setLayout(new BorderLayout());
		jPanel3.setLayout(new FlowLayout());

		jPanel1.add(j1);
		jPanel1.add(chooseFile);
		jPanel2.add(j2, BorderLayout.NORTH);
		jPanel2.add(new JScrollPane(jTextArea), BorderLayout.CENTER);
		jPanel3.add(j3);
		jPanel3.add(jTextField);

		setLayout(new BorderLayout());
		add(jPanel1, BorderLayout.NORTH);
		add(jPanel2, BorderLayout.CENTER);
		add(jPanel3, BorderLayout.SOUTH);

		setSize(800, 600);
		setVisible(true);

		chooseFile.addActionListener(this);
		jTextField.addTextListener(new alistener());

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
	}

	class alistener implements TextListener {

		@Override
		public void textValueChanged(TextEvent e) {
			// TODO Auto-generated method stub
			String search = jTextField.getText();
			String text = jTextArea.getText();
			highlighter.removeAllHighlights();
			try {
				jTextArea.setSelectionColor(new Color(51, 255, 51));
				int length = search.length();
				int index = 0;
				int fromIndex = 0;
				while ((index = text.indexOf(search, fromIndex)) > 0) {
					highlighter.addHighlight(index, index + length, DefaultHighlighter.DefaultPainter);
					fromIndex = index + length;
				}
			} catch (BadLocationException ble) {
				System.err.println(ble);
			}
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FileContentDisplay();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if ((JButton) ae.getSource() == chooseFile) {
			int select = jChooser.showOpenDialog(this);
			if (select == JFileChooser.APPROVE_OPTION) {
				File file = jChooser.getSelectedFile();
				try {
					String line = "";
					jTextArea.setText("");
					br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
					while ((line = br.readLine()) != null) {
						jTextArea.append(line + "\r\n");
					}

					highlighter = jTextArea.getHighlighter();
					highlighter.removeAllHighlights();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (select == JFileChooser.CANCEL_OPTION) {
				System.out.println("Log: Cancel the chooser dialog");
			}
		}
	}
}
