package goldmining.game;

import goldmining.ui.GameUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetupGame {
    private JTextField numRowsField = new JTextField();
    private JTextField numColsField = new JTextField();
    private JTextField requiredMoneyField = new JTextField();
    private JTextField numResourcesField = new JTextField();
    private JTextField numTrapsField = new JTextField();

    public SetupGame() {
        JFrame inputFrame = createInputFrame();
        inputFrame.pack();
        inputFrame.setVisible(true);
    }

    private JFrame createInputFrame() {
        JFrame inputFrame = new JFrame("Gold Mine Game Setup");
        inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inputFrame.setLayout(new GridLayout(6, 1));

        inputFrame.add(createInputPanel("Number of Rows for the game map:", numRowsField));
        inputFrame.add(createInputPanel("Number of Columns for the game map:: ", numColsField));
        inputFrame.add(createInputPanel("Number of Resources: ", numResourcesField));
        inputFrame.add(createInputPanel("Number of Traps: ", numTrapsField));
        inputFrame.add(createInputPanel("Amount of Money to win: ", requiredMoneyField));

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (startGame()) {
                    inputFrame.setVisible(false);
                }
            }
        });

        inputFrame.add(startButton);
        return inputFrame;
    }

    private JPanel createInputPanel(String labelSentence, JTextField textField) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(labelSentence);
        panel.add(label, BorderLayout.NORTH);
        panel.add(textField, BorderLayout.SOUTH);
        return panel;
    }

    private boolean startGame() {
        try {
            int numRows = Integer.parseInt(numRowsField.getText());
            int numCols = Integer.parseInt(numColsField.getText());
            int numResources = Integer.parseInt(numResourcesField.getText());
            int numTraps = Integer.parseInt(numTrapsField.getText());
            int requiredMoney = Integer.parseInt(requiredMoneyField.getText());

            if (GameUtil.checkInputDataValid(numRows, numCols, numResources, numTraps, requiredMoney)) {
                Game game = new Game(numRows, numCols, numResources, numTraps, requiredMoney);
                GameUI gameUI = new GameUI(game);
                gameUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gameUI.pack();
                gameUI.setVisible(true);
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Value must be an integer", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
