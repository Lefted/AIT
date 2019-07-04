package my.dzeik.life;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameOfLiveSpielfläche extends JPanel {

   private boolean[][] data;
   private int cellsX;
   private int cellsY;
   @SuppressWarnings("unused")
private final int FIELD_WIDTH = 8;


   public GameOfLiveSpielfläche(int x, int y) {
      this.data = new boolean[x][y];
      this.cellsX = x;
      this.cellsY = y;
      this.setSize(x * 9 + 1, y * 9 + 1);
   }

   public void setData(boolean[][] dataNew) {
      if(dataNew.length != this.data.length || dataNew[0].length != this.data[0].length) {
         System.err.println("Fehler bei der Übergabe der neuen Daten!");
      }

      for(int i = 0; i < dataNew.length; ++i) {
         for(int j = 0; j < dataNew[i].length; ++j) {
            this.data[i][j] = dataNew[i][j];
         }
      }

   }

   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.setColor(Color.LIGHT_GRAY);
      g.fillRect(0, 0, this.cellsX * 9 + 1, this.cellsY * 9 + 1);
      g.setColor(Color.LIGHT_GRAY);

      int x;
      for(x = 0; x <= this.cellsX; ++x) {
         g.drawLine(x * 9, 0, x * 9, this.cellsX * 9);
      }

      for(x = 0; x <= this.cellsY; ++x) {
         g.drawLine(0, x * 9, this.cellsY * 9, x * 9);
      }

      
      int random = (int) (Math.random() * 100 + 0);
      Color gray = new Color(random, random, random);
      g.setColor(gray);

      for(x = 0; x < this.data.length; ++x) {
         for(int y = 0; y < this.data[x].length; ++y) {
            if(this.data[x][y]) {
               g.fillRect(x * 9 + 1, y * 9 + 1, 8, 8);
            }
         }
      }

   }

   public int getCellsX() {
      return this.cellsX;
   }

   public int getCellsY() {
      return this.cellsY;
   }
}
