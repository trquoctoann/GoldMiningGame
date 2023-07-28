package goldmining.game;

import goldmining.model.MapObject;

import javax.swing.JOptionPane;

public class GameUtil {
    
    public static boolean checkInputDataValid(int numRows, int numCols, int numResources, int numTraps, int requiredMoney) {
        if (requiredMoney > numResources) {
            JOptionPane.showMessageDialog(null, "Required money cannot be greater than the number of resources", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if ((numResources + numTraps) > (numCols * numRows - 1)) {
            JOptionPane.showMessageDialog(null, "Number of objects cannot be greater than map area", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (numRows <= 0 || numCols <= 0 || requiredMoney < 0 || numResources < 0 || numTraps < 0) {
            JOptionPane.showMessageDialog(null, "Value must be positive", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (numRows > 6) {
            JOptionPane.showMessageDialog(null, "Max value of rows is 6", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (numCols > 12) {
            JOptionPane.showMessageDialog(null, "Max value of cols is 12", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public static boolean isGold(MapObject pointedObject) {
        if (pointedObject.getInfo() == "Gold") {
            return true;
        }
        return false;
    }

    public static boolean isTrap(MapObject pointedObject) {
        if (pointedObject.getInfo() == "Trap") {
            return true;
        }
        return false;
    }

    public static boolean isPlayer(MapObject pointedObject) {
        if (pointedObject.getInfo() == "Player") {
            return true;
        }
        return false;
    }
}
