package goldmining.ui;

import goldmining.constant.Direction;
import goldmining.constant.MapObjectType;
import goldmining.game.Game;
import goldmining.model.MapObject;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameUI extends JFrame implements KeyListener {

    private final Game game;

    public GameUI(Game game) {
        this.game = game;
        setTitle("Gold Mining");
        setLayout(new GridBagLayout());
        setResizable(true);
        addKeyListener(this);
        setFocusable(true);
        drawMap();
    }

    private void drawMap() {
        getContentPane().removeAll();
        repaint();
        if (game.checkGameStatus() == ' '){
            GridBagConstraints gbc = new GridBagConstraints();
            for (int col = 0; col < game.getNumCols(); col++) {
                for (int row = 0; row < game.getNumRows(); row++) {
                    gbc.gridx = col;
                    gbc.gridy = row;

                    MapObject resourceObject = game.getMapObject(row, col, MapObjectType.RESOURCES);
                    MapObject trapObject = game.getMapObject(row, col, MapObjectType.TRAP);
                    MapObject minerObject = game.getMapObject(row, col, MapObjectType.MINER);

                    SquareUI square;
                    if (resourceObject != null && minerObject == null) {
                        square = new SquareUI(resourceObject, game);
                    } else if (trapObject != null && minerObject == null) {
                        square = new SquareUI(trapObject, game);
                    } else if (minerObject != null) {
                        square = new SquareUI(minerObject, game);
                    } else {
                        square = new SquareUI();
                    }
                    Border border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
                    square.setBorder(border);
                    add(square, gbc);
                }
            }
        } else {
            add(gameOverScreen());
        }
        validate();
    }

    private JLabel gameOverScreen() {
        JLabel gameOverLabel = new JLabel("");
        if (game.checkGameStatus() == 'W') {
            gameOverLabel.setText("You Win");;
        } else if (game.checkGameStatus() == 'L') {
            gameOverLabel.setText("You Lose");;
        }
        gameOverLabel.setFont(new Font("SansSerif", Font.BOLD, 48));
        gameOverLabel.setForeground(Color.RED);
        gameOverLabel.setHorizontalAlignment(JLabel.CENTER);
        gameOverLabel.setVerticalAlignment(JLabel.CENTER);
    
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int labelWidth = 400;
        int labelHeight = 100;
        int x = (screenSize.width - labelWidth) / 2;
        int y = (screenSize.height - labelHeight) / 2;
        gameOverLabel.setBounds(x, y, labelWidth, labelHeight);
        return gameOverLabel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 37) {
            game.move(1, Direction.LEFT);
        }
        if (e.getKeyCode() == 38) {
            game.move(1, Direction.UP);
        }
        if (e.getKeyCode() == 39) {
            game.move(1, Direction.RIGHT);
        }
        if (e.getKeyCode() == 40) {
            game.move(1, Direction.DOWN);
        }
        drawMap();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}