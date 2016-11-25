package ru.itis.inform;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Window extends JFrame {

    public Window() {
        super("My Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        UsersDAO dao = new UsersDAO();

        String[] columnNames = {
                "id",
                "name",
                "surname",
                "group",
                "fronKazan"
        };

        String[][] data = dao.findAllToStr();

        JTable table = new JTable(data, columnNames);

        JScrollPane scrollPane = new JScrollPane(table);

        getContentPane().add(scrollPane);
        setPreferredSize(new Dimension(450, 200));
        pack();
        setLocationRelativeTo(null);

        JPanel panelRight = new JPanel();
        panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));




        JLabel name = new JLabel(("Name"), JLabel.LEFT);
        panelRight.add(name, this.newTextFieldConstraints());

        JTextField nameF = new JTextField();
        name.setLabelFor(nameF);
        panelRight.add(nameF, this.newTextFieldConstraints());


        JLabel surName = new JLabel(("Surname"), JLabel.LEFT);
        panelRight.add(surName, this.newTextFieldConstraints());

        JTextField surNameF = new JTextField();
        surName.setLabelFor(surNameF);
        panelRight.add(surNameF, this.newTextFieldConstraints());


        JLabel place = new JLabel(("From Kazan"), JLabel.LEFT);
        panelRight.add(place, this.newTextFieldConstraints());

        JCheckBox placeF = new JCheckBox();
        place.setLabelFor(placeF);
        panelRight.add(placeF, this.newTextFieldConstraints());


        JLabel age = new JLabel(("Group"), JLabel.LEFT);
        panelRight.add(age, this.newTextFieldConstraints());

        JTextField ageF = new JTextField();
        age.setLabelFor(ageF);
        panelRight.add(ageF, this.newTextFieldConstraints());


        getContentPane().add(panelRight, BorderLayout.EAST);

        Button btnRed = new Button("Save");
        btnRed.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
//
//        Button btnTime = new Button("Delete");
//        btnTime.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(null, new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
//            }
//        });
//
//
//        panelRight.add(btnRed);
//        panelRight.add(btnTime);

        getContentPane().add(panelRight, BorderLayout.EAST);

        setBounds(300, 300, 700, 700);

        setVisible(true);

    }

    private GridBagConstraints newConstraints() {
        GridBagConstraints c = new GridBagConstraints();
        // a little breathing room
        c.insets = new Insets(2, 2, 2, 2);
        return c;
    }

    private GridBagConstraints newTextFieldConstraints() {
        GridBagConstraints c = this.newConstraints();
        // grow text fields horizontally
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        // text fields end a row
        c.gridwidth = GridBagConstraints.REMAINDER;
        return c;
    }

}
