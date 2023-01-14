import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Firework extends JFrame implements ActionListener {
    private JSlider speed, angle, time;
    private JComboBox comboboxcolour, comboboxexplosion;
    private JButton buttonlaunch;
    private JLabel labelSpeed, labelAngle, labelTime;

    public static String color;
    public static String firework;
    public static int s;
    public static int a;
    public static int t;
    public static int launch;
    Canvas canvas;

    //getters and setters for color, firework, speed, time, angle and launch
    public void setColor(String color) {
        this.color = color;
    }
    
    public static String getColor() {
        return color;
    }

    public void setExplosion(String firework) {
        this.firework = firework;
    }
    
    public static String getFirework() {
        return firework;
    }

    public void setSpeed(int speed) {
        this.s = speed;
    }
    
    public static int getSpeed() {
        return s;
    } 

    public void setAngle(int angle) {
        this.a = angle;
    }
    
    public static int getAngle() {
        return a;
    }

    public void setTime(int time) {
        this.t = time;
    }

    public static int getTime() {
        return t;
    }

    public static void setLaunch(int launch) {
        Firework.launch = launch;
    }

    public static int getLaunch() {
        return launch;
    }

    //constructor of firework
    public Firework() {
    	//create a JPanel
        JPanel j = new JPanel();
        
        //SPEED
        labelSpeed = new JLabel("Speed: ");//speed has the label to indicate the current speed
        j.add(labelSpeed);
        speed = initSlider(); 
        //adding listener to the slider using the anonymous class, which is taking the entire class into argument
        speed.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
            	labelSpeed.setText("Speed: " + speed.getValue());
            }
        });
        j.add(speed);//add the slider to the JPanel
        
        //ANGLE
        labelAngle = new JLabel("Angle: "); //GUI will show the the value of angle
        j.add(labelAngle);
        angle = angleSlider(); //angle has benchmarks
        //adding listener to the slider using the anonymous class
        angle.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
            	labelAngle.setText("Angle: " + angle.getValue());
            }
        });
        j.add(angle);
        
        //TIME
        labelTime = new JLabel("Time: "); //time has a label
        j.add(labelTime); //add the label
        time = timeSlider(); //slider has benchmarks
        time.addChangeListener(new ChangeListener() { //adding Change listener, to display the current time
            @Override
            public void stateChanged(ChangeEvent e) {
            	labelTime.setText("Time: " + time.getValue());
            }
        });
        j.add(time); //adding slider of time

        //COLOR
        JLabel labelColor = new JLabel("Choose Color:"); //to shown in GUI
        j.add(labelColor); //add to the JPanel
        String[] colourarray = {"Black", "Red", "Blue", "Yellow", "Green"}; //putting color in an array
        comboboxcolour = new JComboBox(colourarray); //ComboBox will show all the color in the array
        j.add(comboboxcolour); //add to the JPanel
        comboboxcolour.addActionListener(this); //adding ActionListerner

        //EXPLOSION TYPE
        JLabel labelExplosion = new JLabel("Choose Type:"); 
        j.add(labelExplosion); //adding to the GUI
        String[] typeArray = {"Firework!", "Concentric Circles", "Lines", "Flower", "Spiral Ring", "Children"};
        comboboxexplosion = new JComboBox(typeArray);
        j.add(comboboxexplosion); 
        comboboxexplosion.addActionListener(this); // the same as the ComboBox Color

        //LAUNCH BUTTON
        buttonlaunch = new JButton("Launch");
        buttonlaunch.addActionListener(this); //it has change listener
        j.add(buttonlaunch);

        launch = 0; //initialize it to 0

        add(j, BorderLayout.NORTH); //adding JPenal to the top of the GUI
        canvas = new Canvas();
        add(canvas, BorderLayout.CENTER); //adding canvas to the center of the GUI
        
        //creating a bunch of panel, adding them to the right, left, bottom, respectively
        JPanel emptyeast = new JPanel();
        JPanel emptywest = new JPanel();
        JPanel emptysouth = new JPanel();
        add(emptyeast, BorderLayout.EAST);
        add(emptywest, BorderLayout.WEST);
        add(emptysouth, BorderLayout.SOUTH);
        pack();
        //setting the basics
        setSize(2000, 1000);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    
    //Speed Slider method to make it has ticks
    private JSlider initSlider() {
        JSlider slider = new JSlider(0, 200, 10);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
		slider.setOrientation(SwingConstants.HORIZONTAL);
        Hashtable<Integer, JComponent> hashtable = new Hashtable<Integer, JComponent>();
        hashtable.put(0, new JLabel("0")); 
        hashtable.put(200, new JLabel("200"));     
        slider.setLabelTable(hashtable);
        return slider;
    }
    
  //Angle Slider method to make it has ticks
    private JSlider angleSlider() {
    	JSlider slider = new JSlider(0, 90, 0);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
		slider.setOrientation(SwingConstants.HORIZONTAL);
        Hashtable<Integer, JComponent> hashtable = new Hashtable<Integer, JComponent>();
        hashtable.put(0, new JLabel("0")); 
        hashtable.put(90, new JLabel("90"));  
        slider.setLabelTable(hashtable);
        return slider;
    }
    
  //Time Slider method to make it has ticks
    private JSlider timeSlider() {
    	JSlider slider = new JSlider(0,5,0);
    	slider.setMajorTickSpacing(1);
    	slider.setMinorTickSpacing(1);
    	slider.setPaintTicks(true);
    	slider.setPaintLabels(true);
    	slider.setOrientation(SwingConstants.HORIZONTAL);
    	Hashtable<Integer, JComponent> hashtable = new Hashtable<Integer, JComponent>();
    	hashtable.put(0, new JLabel("0")); 
    	hashtable.put(5, new JLabel("5"));
    	slider.setLabelTable(hashtable);
    	return slider;
    }

    //main method
    public static void main(String[] args) {
        new Firework().setVisible(true);
    }
    
    //ActionPerfomed, when buttonlaunch is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(buttonlaunch)) {
            launch++; //the launch counter will add 1
            canvas.setS(speed.getValue()); //use the setters to display the right value
            canvas.setA(angle.getValue());
            canvas.setT(time.getValue());
            canvas.setExp((String) comboboxexplosion.getSelectedItem());
            canvas.setCol((String) comboboxcolour.getSelectedItem());
            System.out.println((String) comboboxexplosion.getSelectedItem());
            //repaint when the launch is cliecked again
            repaint();
        }

    }


}