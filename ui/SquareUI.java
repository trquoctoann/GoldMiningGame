package goldmining.ui;

import goldmining.game.GameUtil;
import goldmining.model.MapObject;
import goldmining.model.character.Player;

import javax.swing.*;
import java.awt.*;

public class SquareUI extends JPanel {
    private MapObject pointedObject;
    private final int HEIGHT = 150;
    private final int WIDTH = 150;

    public SquareUI(MapObject pointedObject) {
        this.pointedObject = pointedObject;
        setPreferredSize(new Dimension(WIDTH, HEIGHT)); 
    }

    public SquareUI() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT)); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (pointedObject != null) {
            if (GameUtil.isGold(pointedObject)) {
                g.setColor(Color.BLACK);
                g.drawString("Gold", 60, 75);

            } else if (GameUtil.isTrap(pointedObject)) {
                g.setColor(Color.BLACK);
                g.drawString("Trap", 60, 75);

            } else if (GameUtil.isPlayer(pointedObject)) {
                Player player = (Player) pointedObject;
                String playerName = "Player";
                String playerHP = "Heart: " + player.getHP();
                String playerMoney = "Resource Value: " + player.getMoney();
                g.setColor(Color.BLACK);
                g.drawString(playerName, 50, 50);
                g.drawString(playerHP, 40, 75);
                g.drawString(playerMoney, 25, 100);
            }
        }
    }
}
