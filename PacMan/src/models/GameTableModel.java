package models;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;

public class GameTableModel extends AbstractTableModel {
    private GameState gameState;

    public GameTableModel(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public int getRowCount() {
        return gameState.getBoardSize();
    }

    @Override
    public int getColumnCount() {
        return gameState.getBoardSize();
    }

    @Override
    public Object getValueAt(int row, int column) {
        GameObject result = gameState.findByPosition(row, column);
        if(result == null) {
            return null;
        }
        return result.getImage();
    }

    @Override
    public Class<?> getColumnClass(int column) {
        return ImageIcon.class;
    }
}
