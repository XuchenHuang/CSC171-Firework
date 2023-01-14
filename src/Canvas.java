import java.awt.*;
import java.util.*;
import javax.swing.*;

public class Canvas extends JComponent {
//declaring variable 
	public static double v; //velocity
	public static double a; //angle
	public static int t; //time
	public static String type = ""; //type of firework
	public static String col = ""; //color
	
	//getters and setters for string and col
	public static String getType() {
		return type;
	}

	public static void setExp(String type) {
		Canvas.type = type;
	}

	public static double getS() {
		return v;
	}

	public void setS(double v1) {
		this.v = v1;
	}

	public static double getA() {
		return a;
	}

	public void setA(double a1) {
		this.a = a1;
	}

	public static int getT() {
		return t;
	}

	public void setT(int t1) {
		this.t = t1;
	}

	public static String getCol() {
		return col;
	}

	public static void setCol(String col) {
		Canvas.col = col;
	}


//paintcomponent
	public void paintComponent(Graphics g) {
		//initialize the color to black
		g.setColor(Color.black);
		//setting the bound
		g.drawRect(0, 0, getWidth(), getHeight());
		//y1 is height of the window
		int y1 = getHeight();
		//declaring Random
		Random random = new Random();
		double xstart = 0;
		//ystart is the height of the window
		double ystart = getHeight();
		//the formula
		double xendx = v * Math.cos(Math.toRadians(a)) * t;
		//the formula
		double yendy = (y1 - (v * Math.sin(Math.toRadians(a)) * t - 0.5 * 9.81 * t * t));


		//draw lines based on the color that user selected
		for (double dis = 0; dis < t; dis = dis + 0.01) {
			if (col.equals("Red")) {
				Color D = Color.RED;
				//set the color red
				g.setColor(D);
			}

			else if (col.equals("Blue")) {
				Color E = Color.BLUE;
				g.setColor(E);
			}

			else if (col.equals("Yellow")) {
				Color F = Color.YELLOW;
				g.setColor(F);

			}

			else if (col.equals("Green")) {
				Color G = Color.GREEN;
				g.setColor(G);

			}

			else if (col.equals("Black")) {
				Color H = Color.BLACK;
				g.setColor(H);

			}
			
			double xend = v * Math.cos(Math.toRadians(a)) * dis;
			double yend = (y1 - (v * Math.sin(Math.toRadians(a)) * dis - 0.5 * 9.81 * dis * dis));
			g.drawLine((int) xstart, (int) ystart, (int) xend, (int) yend);
			//drawing line segment, by assigning the value of end to start, and repeat by the for loop
			xstart = xend;
			ystart = yend;
		}

		// EXPLOSION TYPES

		//String Firework!
		if (type.equals("Firework!")) {
			g.setFont(new Font("Helvetica", Font.BOLD, 40));
			String string = "\"Firework!\"";
			//drawing out the Firework! 
			g.drawString(string, (int) (xendx - 70), (int) (yendy + 15));
		}

		//concentric circles
		else if (type.equals("Concentric Circles")) {
			//random number of circles
			for (int displace = 5; displace < random.nextInt(1000); displace = displace + 5) {
				//make the concentric circles
				g.drawOval((int) xendx - (displace / 2), (int) yendy - (displace / 2), (int) displace, (int) displace);
			}
		}

		//lines
		else if (type.equals("Lines")) {
			//drawing lines around the center 
			for (int i = 0; i <= 360; i = i + 4) {
				double hor = xendx - 100 * Math.cos(i * (Math.PI) / 180);
				double ver = yendy - 100 * Math.sin(i * (Math.PI) / 180);
				g.drawLine((int) xendx, (int) yendy, (int) hor, (int) ver);
			}
		}

		//flower
		else if (type.equals("Flower")) {
			//drawing six circles to make a flower
			g.fillOval((int) xendx - 20, (int) yendy - 66, 45, 45);
			g.fillOval((int) xendx + 23, (int) yendy - 20, 45, 45);
			g.fillOval((int) xendx + 10, (int) yendy + 27, 45, 45);
			g.fillOval((int) xendx - 42, (int) yendy + 27, 45, 45);
			g.fillOval((int) xendx - 68, (int) yendy - 20, 45, 45);
			g.fillOval((int) xendx - 18, (int) yendy - 18, 36, 36);

		}

		//spiral ring
		else if (type.equals("Spiral Ring")) {
			int hor = (int) xendx;
			int ver = (int) yendy;
			//drawing lines and arc to make a big flower,
			for (double i = 0; i <= 6.28; i = i + 0.07) {
				hor = (int) (xendx + 100 * Math.cos(i));
				ver = (int) (yendy - 100 * Math.sin(i));
				g.drawArc((int) hor, (int) ver, 50, 70, 0, 330);
				g.drawLine((int) xendx, (int) yendy, hor + 12, ver + 12);

			}
		}

		// Children for extra credit
		else if (type.equals("Children")) {
			for (int k = 0; k <= 8; k++) {
				int startx = (int) xendx;
				int starty = (int) yendy;
				//drawing the first "children"
				if (k == 0) {
					//drawing the trajectory of the explosion  
					for (double dis = 0; dis < 6; dis = dis + 0.1) {
						double ec1x = xendx + 20 * Math.cos(45 * (Math.PI / 180)) * dis;
						double ec1y = yendy - (20 * Math.sin(45 * (Math.PI / 180)) * dis - 0.5 * 9.81 * dis * dis);
						g.drawLine(startx, starty, (int) ec1x, (int) ec1y);
						startx = (int) ec1x;
						starty = (int) ec1y;
					}
					//draw lines around the end point of trajectory to make a children firework
					for (int i = 0; i <= 360; i = i + 10) {
						double hor = startx - 15 * Math.cos(i * (Math.PI) / 180);
						double ver = starty - 15 * Math.sin(i * (Math.PI) / 180);
						g.drawLine((int) startx, (int) starty, (int) hor, (int) ver);
					}
				//drawing the second "children"	
				} else if (k == 1) {
					//drawing the trajectory of the explosion 
					for (double dis = 0; dis < 6; dis = dis + 0.1) {
						double ec2x = xendx - 20 * Math.cos(45 * (Math.PI / 180)) * dis;
						double ec2y = yendy - (20 * Math.sin(45 * (Math.PI / 180)) * dis - 0.5 * 9.81 * dis * dis);
						g.drawLine((int) startx, (int) starty, (int) ec2x, (int) ec2y);
						startx = (int) ec2x;
						starty = (int) ec2y;
					}
					//draw lines around the end point of trajectory to make a children firework
					for (int i = 0; i <= 360; i = i + 10) {
						double hor = startx - 15 * Math.cos(i * (Math.PI) / 180);
						double ver = starty - 15 * Math.sin(i * (Math.PI) / 180);
						g.drawLine((int) startx, (int) starty, (int) hor, (int) ver);
					}
					
				//drawing the third "children"	
				} else if (k == 2) {
					//drawing the trajectory of the explosion 
					for (double dis = 0; dis < 6; dis = dis + 0.1) {
						double ec2x = xendx + 25 * Math.cos(65 * (Math.PI / 180)) * dis;
						double ec2y = yendy - (30 * Math.sin(65 * (Math.PI / 180)) * dis - 0.5 * 9.81 * dis * dis);
						g.drawLine((int) startx, (int) starty, (int) ec2x, (int) ec2y);
						startx = (int) ec2x;
						starty = (int) ec2y;
					}
					//draw lines around the end point of trajectory to make a children firework
					for (int i = 0; i <= 360; i = i + 10) {
						double hor = startx - 15 * Math.cos(i * (Math.PI) / 180);
						double ver = starty - 15 * Math.sin(i * (Math.PI) / 180);
						g.drawLine((int) startx, (int) starty, (int) hor, (int) ver);
					}
				}
				//drawing the fourth "children"	
				else if (k == 3) {
					//drawing the trajectory of the explosion 
					for (double dis = 0; dis < 6; dis = dis + 0.1) {
						double ec2x = xendx - 15 * Math.cos(70 * (Math.PI / 180)) * dis;
						double ec2y = yendy - (15 * Math.sin(70 * (Math.PI / 180)) * dis - 0.5 * 9.81 * dis * dis);
						g.drawLine((int) startx, (int) starty, (int) ec2x, (int) ec2y);
						startx = (int) ec2x;
						starty = (int) ec2y;
					}
					//draw lines around the end point of trajectory to make a children firework
					for (int i = 0; i <= 360; i = i + 10) {
						double hor = startx - 15 * Math.cos(i * (Math.PI) / 180);
						double ver = starty - 15 * Math.sin(i * (Math.PI) / 180);
						g.drawLine((int) startx, (int) starty, (int) hor, (int) ver);
					}
				//drawing the fifth "children"		
				} else if (k == 4) {
					//drawing the trajectory of the explosion 
					for (double dis = 0; dis < 5; dis = dis + 0.1) {
						double ec2x = xendx - 40 * Math.cos(75 * (Math.PI / 180)) * dis;
						double ec2y = yendy - (40 * Math.sin(75 * (Math.PI / 180)) * dis - 0.5 * 9.81 * dis * dis);
						g.drawLine((int) startx, (int) starty, (int) ec2x, (int) ec2y);
						startx = (int) ec2x;
						starty = (int) ec2y;
					}
					//draw lines around the end point of trajectory to make a children firework
					for (int i = 0; i <= 360; i = i + 10) {
						double hor = startx - 15 * Math.cos(i * (Math.PI) / 180);
						double ver = starty - 15 * Math.sin(i * (Math.PI) / 180);
						g.drawLine((int) startx, (int) starty, (int) hor, (int) ver);
					}
				//drawing the sixth "children"						
				} else if (k == 5) {
					//drawing the trajectory of the explosion 
					for (double dis = 0; dis < 5; dis = dis + 0.1) {
						double ec2x = xendx + 40 * Math.cos(80 * (Math.PI / 180)) * dis;
						double ec2y = yendy - (45 * Math.sin(80 * (Math.PI / 180)) * dis - 0.5 * 9.81 * dis * dis);
						g.drawLine((int) startx, (int) starty, (int) ec2x, (int) ec2y);
						startx = (int) ec2x;
						starty = (int) ec2y;
					}
					//draw lines around the end point of trajectory to make a children firework
					for (int i = 0; i <= 360; i = i + 10) {
						double hor = startx - 15 * Math.cos(i * (Math.PI) / 180);
						double ver = starty - 15 * Math.sin(i * (Math.PI) / 180);
						g.drawLine((int) startx, (int) starty, (int) hor, (int) ver);
					}
				//drawing the seventh "children"		
				} else if (k == 6) {
					//drawing the trajectory of the explosion 
					for (double dis = 0; dis < 6; dis = dis + 0.1) {
						double ec2x = xendx + 12 * Math.cos(40 * (Math.PI / 180)) * dis;
						double ec2y = yendy - (12 * Math.sin(40 * (Math.PI / 180)) * dis - 0.5 * 9.81 * dis * dis);
						g.drawLine((int) startx, (int) starty, (int) ec2x, (int) ec2y);
						startx = (int) ec2x;
						starty = (int) ec2y;
					}
					//draw lines around the end point of trajectory to make a children firework
					for (int i = 0; i <= 360; i = i + 10) {
						double hor = startx - 15 * Math.cos(i * (Math.PI) / 180);
						double ver = starty - 15 * Math.sin(i * (Math.PI) / 180);
						g.drawLine((int) startx, (int) starty, (int) hor, (int) ver);
					}
				//drawing the eighth "children"		
				} else if (k == 7) {
					//drawing the trajectory of the explosion 
					for (double dis = 0; dis < 6; dis = dis + 0.1) {
						double ec2x = xendx - 30 * Math.cos(55 * (Math.PI / 180)) * dis;
						double ec2y = yendy - (30 * Math.sin(55 * (Math.PI / 180)) * dis - 0.5 * 9.81 * dis * dis);
						g.drawLine((int) startx, (int) starty, (int) ec2x, (int) ec2y);
						startx = (int) ec2x;
						starty = (int) ec2y;
					}
					//draw lines around the end point of trajectory to make a children firework
					for (int i = 0; i <= 360; i = i + 10) {
						double hor = startx - 15 * Math.cos(i * (Math.PI) / 180);
						double ver = starty - 15 * Math.sin(i * (Math.PI) / 180);
						g.drawLine((int) startx, (int) starty, (int) hor, (int) ver);
					}
				}

			}
		}

	} }
