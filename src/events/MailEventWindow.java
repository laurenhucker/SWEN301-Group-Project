package events;

import java.awt.Dialog.ModalityType;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import core.Controller;
import core.Popup;

public class MailEventWindow {

	private JDialog dialog;
	private Controller controller;
	private ArrayList<JTextField> fields;

	private JTextField dayField;
	private JTextField toField;
	private JTextField fromField;
	private JTextField weightField;
	private JTextField volumeField;
	private JTextField priorityField;

	/**
	 * Create the application.
	 * @param controller
	 */
	public MailEventWindow(Controller controller) {
		this.controller = controller;
		initialize();
	}

	private void submitEvent(){
		boolean empty = false;
		for(JTextField j: fields){
			if(j.getText().trim().equals("")){
				empty = true;
			}
		}
		if(empty){
			Popup.infoBox(dialog, "A field is empty!", "Error!");
		}else{
			MailEvent m = null;
			try{
				m = new MailEvent(dayField.getText().trim(),
				toField.getText().trim(),
				fromField.getText().trim(),
				Integer.parseInt(weightField.getText().trim()),
				Integer.parseInt(volumeField.getText().trim()),
				priorityField.getText().trim());
			}catch(NumberFormatException e){
				Popup.infoBox(dialog, "One of the fields contains incorrect characters!", "Error!");
			}
			if(m != null){
				controller.addEvent(m);
				dialog.dispose();
			}
		}
	}

	private void initialize() {
		dialog = new JDialog();
		dialog.setTitle("Add a new mail event");
		dialog.setBounds(100, 100, 750, 750);
		dialog.setModal(true);
		dialog.setAlwaysOnTop(true);
		dialog.setModalityType(ModalityType.APPLICATION_MODAL);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		JLabel day = new JLabel("Day:");
		day.setBounds(10, 10, 100, 15);
		dialog.getContentPane().add(day);

		dayField = new JTextField();
		dayField.setBounds(110, 10, 150, 15);
		dialog.getContentPane().add(dayField);
		dayField.setColumns(20);

		JLabel to = new JLabel("To:");
		to.setBounds(10, 40, 100, 15);
		dialog.getContentPane().add(to);

		toField = new JTextField();
		toField.setBounds(110, 40, 150, 15);
		dialog.getContentPane().add(toField);
		toField.setColumns(20);

		JLabel from = new JLabel("From:");
		from.setBounds(10, 70, 100, 15);
		dialog.getContentPane().add(from);

		fromField = new JTextField();
		fromField.setBounds(110, 70, 150, 15);
		dialog.getContentPane().add(fromField);
		fromField.setColumns(20);

		JLabel weight = new JLabel("Weight:");
		weight.setBounds(10, 100, 100, 15);
		dialog.getContentPane().add(weight);

		weightField = new JTextField();
		weightField.setBounds(110, 100, 150, 15);
		dialog.getContentPane().add(weightField);
		weightField.setColumns(20);

		JLabel volume = new JLabel("Volume:");
		volume.setBounds(10, 130, 100, 15);
		dialog.getContentPane().add(volume);

		volumeField = new JTextField();
		volumeField.setBounds(110, 130, 150, 15);
		dialog.getContentPane().add(volumeField);
		volumeField.setColumns(20);

		JLabel priority = new JLabel("Priority:");
		priority.setBounds(10, 160, 100, 15);
		dialog.getContentPane().add(priority);

		priorityField = new JTextField();
		priorityField.setBounds(110, 160, 150, 15);
		dialog.getContentPane().add(priorityField);
		priorityField.setColumns(20);

		JButton confirmButton = new JButton("Confirm");
		confirmButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				submitEvent();
			}
		});
		confirmButton.setBounds(10, 190, 100, 25);
		dialog.getContentPane().add(confirmButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dialog.dispose();
			}
		});
		cancelButton.setBounds(160, 190, 100, 25);
		dialog.getContentPane().add(cancelButton);

		//The last label in swing seems to stick to the middle and move
		//around when the frame is getting resized. A weird interaction
		//worked around by adding and empty label as the last label.
		JLabel dummy = new JLabel("");
		dummy.setBounds(10, 370, 100, 15);
		dialog.getContentPane().add(dummy);
		groupFields();

		dialog.setVisible(true);
	}

	private void groupFields() {
		fields = new ArrayList<JTextField>();
		fields.add(dayField);
		fields.add(toField);
		fields.add(fromField);
		fields.add(weightField);
		fields.add(volumeField);
	}
}
