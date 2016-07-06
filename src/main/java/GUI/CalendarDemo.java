/**
 * 
 */
package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * @author Meng Guo 3/24/2016
 */
public class CalendarDemo extends JFrame implements ActionListener, Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8143339178998789831L;

	JPanel pTable, pDate;
	TextField textField;
	JComboBox<String> jYearBox, jMonthBox;
	JButton jButton;
	CalendarModel model = new CalendarModel();
	JTable monthTable;
	String[] days = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
	String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
	String[] years = new String[50];
	int byear = 1980;
	protected Thread clockThread = null;
	Font font = new Font("Microsoft YaHei", Font.BOLD, 13);
	int year, month, day, hour, min, sec, day_week;

	public CalendarDemo() {
		// TODO Auto-generated constructor stub

		for (int i = 0; i < years.length; i++) {
			years[i] = String.valueOf(byear + i);
		}
		
		jMonthBox = new JComboBox<>(months);
		jYearBox = new JComboBox<>(years);
		jButton = new JButton("Today");
		textField = new TextField(14);
		textField.setEditable(false);

		// calculate the time now
		calDate();

		monthTable = new JTable(model);
		System.out.println(months[month] + " " + day + "," + year + " " + hour + ":" + min + ":" + sec);

		pTable = new JPanel();
		pDate = new JPanel();
		pTable.setLayout(new BorderLayout());
		pDate.setLayout(new FlowLayout());

		pDate.add(jMonthBox);
		pDate.add(jYearBox);
		pDate.add(jButton);

		setLayout(new BorderLayout());

		jMonthBox.setSelectedIndex(month);
		jYearBox.setSelectedIndex(year - byear);

		model.setMonth(year, month);
		monthTable.repaint();
		monthTable.getColumnModel().getColumn(day_week - 1).setCellRenderer(new StatusColumnCellRenderer());

		jMonthBox.addItemListener(new MyItemListener());
		jYearBox.addItemListener(new MyItemListener());

		jButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				jMonthBox.setSelectedIndex(month);
				jYearBox.setSelectedIndex(year - byear);
			}
		});

		pTable.add(monthTable, BorderLayout.CENTER);

		add(pDate, BorderLayout.NORTH);
		add(pTable, BorderLayout.CENTER);
		add(textField, BorderLayout.SOUTH);
		// add(textField, BorderLayout.SOUTH);

		setSize(300, 220);
		setVisible(true);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
	}

	public void calDate() {
		Calendar calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH);
		day = calendar.get(Calendar.DAY_OF_MONTH);
		hour = calendar.get(Calendar.HOUR_OF_DAY);
		min = calendar.get(Calendar.MINUTE);
		sec = calendar.get(Calendar.SECOND);
		day_week = calendar.get(Calendar.DAY_OF_WEEK); // Sun 1 - Sat 7
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CalendarDemo calendarDemo = new CalendarDemo();
		calendarDemo.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (Thread.currentThread() == clockThread) {
			calDate();
			textField.setText("Date:  " + months[month] + " " + day + ", " + year + "  " + days[day_week - 1] + "  "
					+ hour + ":" + min + ":" + sec);
			textField.setFont(font);
			textField.setBackground(new Color(153, 153, 255));
			try {
				Thread.currentThread();
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
	}

	public void start() {
		if (clockThread == null) {
			clockThread = new Thread(this);
			clockThread.start();
		}
	}

	public void stop() {
		clockThread = null;
	}

	public class StatusColumnCellRenderer extends DefaultTableCellRenderer {
		/**
		 * 
		 */
		private static final long serialVersionUID = -6380815093711858079L;

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int col) {

			// Cells are by default rendered as a JLabel.
			JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

			// Get the status for the current row.
			CalendarModel tableModel = (CalendarModel) table.getModel();
			if (tableModel.getStatus(row, col) == CalendarModel.APPROVED) {
				l.setBackground(new Color(153, 255, 255));
			} else {
				l.setBackground(new Color(153, 255, 255));
			}

			// Return the JLabel which renders the cell.
			return l;
		}
	}

	class MyItemListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			model.setMonth(jYearBox.getSelectedIndex() + byear, jMonthBox.getSelectedIndex());
			monthTable.repaint();
		}

	}

	class CalendarModel extends AbstractTableModel {
		/**
		 * 
		 */
		private static final long serialVersionUID = -2748174121926527036L;

		String[] days = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };

		int[] numDays = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		String[][] calendar = new String[7][7];

		static final int APPROVED = 0;

		public CalendarModel() {
			for (int i = 0; i < days.length; ++i)
				calendar[0][i] = days[i];
			for (int i = 1; i < 7; ++i)
				for (int j = 0; j < 7; ++j)
					calendar[i][j] = " ";
		}

		public int getStatus(int row, int col) {
			// TODO Auto-generated method stub
			return 0;
		}

		public int getRowCount() {
			return 7;
		}

		public int getColumnCount() {
			return 7;
		}

		public Object getValueAt(int row, int column) {
			return calendar[row][column];
		}

		public void setValueAt(Object value, int row, int column) {
			calendar[row][column] = (String) value;
		}

		public void setMonth(int year, int month) {
			for (int i = 1; i < 7; ++i)
				for (int j = 0; j < 7; ++j)
					calendar[i][j] = " ";
			GregorianCalendar cal = new GregorianCalendar();
			cal.set(year, month, 1);
			int offset = cal.get(GregorianCalendar.DAY_OF_WEEK) - 1;
			offset += 7;
			int num = daysInMonth(year, month);
			for (int i = 0; i < num; ++i) {
				calendar[offset / 7][offset % 7] = Integer.toString(i + 1);
				++offset;
			}
		}

		public boolean isLeapYear(int year) {
			if (year % 4 == 0)
				return true;
			return false;
		}

		public int daysInMonth(int year, int month) {
			int days = numDays[month];
			if (month == 1 && isLeapYear(year))
				++days;
			return days;
		}
	}

}
