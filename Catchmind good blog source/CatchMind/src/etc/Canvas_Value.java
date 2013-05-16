package etc;

import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;

// 캔버스에 그릴 정보를 저장하는 클래스 ( 전송하기 위한.. )
public class Canvas_Value implements Serializable{
	Point point;
	Color color;
	int g_Size;
	
	public Canvas_Value(Point point, Color color, int g_Size) {

		this.point = point;
		this.color = color;
		this.g_Size = g_Size;
	}

	public Point getPoint() {
		return point;
	}

	public Color getColor() {
		return color;
	}

	public int getG_Size() {
		return g_Size;
	}
}