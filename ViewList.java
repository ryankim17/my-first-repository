import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.util.*;

public class ViewList extends JFrame {

	private ArrayList<Student> studentList;
	private ArrayList<Member> memberList;
	private JTable table;
	/**
	 * Launch the application.
	 *
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewList window = new ViewList(studentList);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public ViewList(ArrayList<Student> slist, ArrayList<Member> mlist) {
		initialize(slist, mlist);
	}

	/**
	 * Initialize the contents of the 
	 */
	private void initialize(ArrayList<Student> students, ArrayList<Member> members) {
		studentList = students;
		memberList = members;
		setBounds(100, 100, 534, 431);
		setResizable(false);
		setLocation((int)((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2), (int)((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblSelect = new JLabel("Select a profile to view:");
		lblSelect.setBounds(26, 28, 229, 20);
		getContentPane().add(lblSelect);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//table.getSelectedCell().   //////////////////////////////////////////select a cell and get the student based on the name
				int row = table.getSelectedRow();
				int column = table.getSelectedColumn();
				String tempName = (String)table.getValueAt(row, column);
				Student temp = new Student(null, 0, null, null, null);
				for(int i = 0; i < studentList.size(); i++) {
					if(studentList.get(i).getName().equals(tempName)) {
						temp = studentList.get(i);
						break;
					}
				}
				StudentView studentView = new StudentView(temp);
				studentView.setVisible(true);
				Member temp1 = new Member(null, 0, null, null, 0, null, null);
				for(int i = 0; i < memberList.size(); i++) {
					if(memberList.get(i).getName().equals(tempName)) {
						temp1 = memberList.get(i);
						break;
					}
				}
				MemberView memberView = new MemberView(temp1);
				memberView.setVisible(true);
				dispose();
			}
		});
		btnConfirm.setBounds(94, 337, 117, 34);
		getContentPane().add(btnConfirm);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(305, 337, 117, 34);
		getContentPane().add(btnCancel);
		
		String col[] = {"Name", "Title"};
		int size = studentList.size();
		DefaultTableModel model = new DefaultTableModel(col, size);
		Object rowData[] = new Object[2];
		for(int i = 0; i < size; i++) {
			rowData[0] = studentList.get(i).getName();
			rowData[1] = "Student";
			model.addRow(rowData);
		}
		int size1 = memberList.size();
		DefaultTableModel model1 = new DefaultTableModel(col, size1);
		Object rowData1[] = new Object[2];
		for(int i = 0; i < size1; i++) {
			rowData1[0] = memberList.get(i).getName();
			rowData1[1] = "Member";
			model1.addRow(rowData1);
		}
		table = new JTable(model);
		table.setBounds(36, 59, 447, 257);
		getContentPane().add(table);
	}
}
