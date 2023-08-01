package goldmining.ui;

import goldmining.game.Game;
import goldmining.game.GameUtil;

import goldmining.model.MapObject;
import goldmining.model.character.Miner;

import javax.swing.*;
import java.awt.*;

public class SquareUI extends JPanel {
    private MapObject pointedObject;
    private Game game;
    private final int HEIGHT = 150;
    private final int WIDTH = 150;

    public SquareUI(MapObject pointedObject, Game game) {
        this.pointedObject = pointedObject;
        this.game = game;
        setPreferredSize(new Dimension(WIDTH, HEIGHT)); 
    }

    public SquareUI() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT)); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // if (pointedObject != null) {
        //     if (GameUtil.isGold(pointedObject)) {
        //         g.setColor(Color.BLACK);
        //         g.drawString("Gold", 60, 75);

        //     } else if (GameUtil.isSilver(pointedObject)) {
        //         g.setColor(Color.BLACK);
        //         g.drawString("Silver", 55, 75);

        //     } else if (GameUtil.isWood(pointedObject)) {
        //         g.setColor(Color.BLACK);
        //         g.drawString("Wood", 60, 75);

        //     } else if (GameUtil.isBearTrap(pointedObject)) {
        //         g.setColor(Color.BLACK);
        //         g.drawString("Bear Trap", 45, 75);

        //     } else if (GameUtil.isStoneTrap(pointedObject)) {
        //         g.setColor(Color.BLACK);
        //         g.drawString("Stone Trap", 45, 75);

        //     } else if (GameUtil.isThornTrap(pointedObject)) {
        //         g.setColor(Color.BLACK);
        //         g.drawString("Thorn Trap", 45, 75);

        //     } else if (GameUtil.isMiner(pointedObject)) {
        //         Miner miner = (Miner) pointedObject;
        //         String minerName = "Miner";
        //         String minerHP = "Heart: " + miner.getHP();
        //         String minerMoney = "Resource Value: " + miner.getMoney();
        //         g.setColor(Color.BLACK);
        //         g.drawString(minerName, 50, 50);
        //         g.drawString(minerHP, 40, 75);
        //         g.drawString(minerMoney, 25, 100);
        //     }
        // }

        if (pointedObject != null) {
            if (GameUtil.isMiner(pointedObject)) {
                Miner miner = (Miner) pointedObject;
                String minerName = "Miner";
                String minerHP = "Heart: " + miner.getHP();
                String minerMoney = "Resource Value: " + miner.getMoney();
                g.setColor(Color.BLACK);
                g.drawString(minerName, 50, 20);
                g.drawString(minerHP, 40, 40);
                g.drawString(minerMoney, 20, 60);

                int minerLatitude = miner.getLocation().getLatitude();
                int minerLongitude = miner.getLocation().getLongitude();

                if (game.isNearTrap(minerLatitude, minerLongitude)) {
                    g.drawString("Near Trap", 20, 80);
                }
                if (game.isNearResource(minerLatitude, minerLongitude)) {
                    g.drawString("Near Resource", 20, 100);
                }
            }
        }
    }
}
