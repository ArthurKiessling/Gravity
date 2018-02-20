package at.spengergasse.game;
import javafx.scene.shape.Rectangle;
public class Blocks {
	 public Rectangle newBlock(int x, int y) {
	 Rectangle rectangle = new Rectangle();
     rectangle.setX(600);
     rectangle.setY(770);
     rectangle.setWidth(x);
     rectangle.setHeight(y);
	return rectangle;
	 }
}
