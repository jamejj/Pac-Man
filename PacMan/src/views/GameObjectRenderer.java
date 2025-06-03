package views;

import models.GameObject;
import models.GameState;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class GameObjectRenderer extends JPanel implements TableCellRenderer {
    private ImageIcon image;

    public GameObjectRenderer() {
        setBackground(Color.BLACK);
    }

    @Override
    public Component getTableCellRendererComponent(JTable jTable, Object o, boolean b, boolean b1, int row, int col) {
        if(o instanceof ImageIcon ii) {
            image = ii;
        } else {
            image = null;
        }

        return this;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(image != null) {
            int width = getWidth();
            int height = getHeight();

            double imgWidth = image.getIconWidth();
            double imgHeight = image.getIconHeight();

            double ratio = Math.min(width / imgWidth,  height / imgHeight);
            int w = (int)(imgWidth * ratio);
            int h = (int)(imgHeight * ratio);

            int x = (int)((width - w) / 2);
            int y = (int)((height - h) / 2);

            g.drawImage(image.getImage(), x, y, w, h, null);
        }
    }
}
