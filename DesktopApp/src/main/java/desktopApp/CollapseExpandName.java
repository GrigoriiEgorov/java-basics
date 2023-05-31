package desktopApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CollapseExpandName {
    private JPanel mainPanel;
    private JPanel collapsePanel;
    private JPanel expandPanel;
    private JButton collapseButton;
    private JButton expandButton;
    private JTextField name;
    private JTextField secondName;
    private JTextField surname;
    private JTextField collapsedName;

    public CollapseExpandName(){
        expandPanel.setVisible(false);
        collapseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(name.getText().isBlank() && surname.getText().isBlank()){
                    JOptionPane.showMessageDialog(
                            collapsePanel,
                            "Не правильно введены Имя и Фамилия",
                            "error",
                            JOptionPane.PLAIN_MESSAGE
                    );
                    return;
                }

                collapsedName.setText(
                        surname.getText().replace(" ","") + " " +
                        name.getText().replace(" ","") + " " +
                        secondName.getText().replace(" ", "")
                );
                name.setText("");
                surname.setText("");
                secondName.setText("");

                collapsePanel.setVisible(false);
                expandPanel.setVisible(true);
            }
        });


        expandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String[] s = collapsedName.getText().split(" ");

                surname.setText(s[0]);
                name.setText(s[1]);

                if(s.length == 3){
                    secondName.setText(s[2]);
                }

                expandPanel.setVisible(false);
                collapsePanel.setVisible(true);
            }
        });

    }


    public JPanel getMainPanel(){
        return mainPanel;
    }



}
