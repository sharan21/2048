
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;




public class MainGame extends JFrame implements ActionListener,KeyListener {

    JFrame frame = new JFrame("2048");
    JButton reset = new JButton("reset");
    JButton DOWN = new JButton("DOWN");
    JButton RIGHT = new JButton("RIGHT");
    JButton LEFT = new JButton("LEFT");

    JButton[][] buttons = new JButton[5][5];
    int[][] count = new int[5][5];
    Container grid = new Container();


    public MainGame(){

        //initialize the frame

        frame.setSize(500,500);
        frame.setLayout(new BorderLayout());

        frame.add(reset, BorderLayout.NORTH);
        frame.add(DOWN, BorderLayout.SOUTH);
        frame.add(RIGHT, BorderLayout.EAST);
        frame.add(LEFT, BorderLayout.WEST);



        reset.addActionListener(this);
        DOWN.addActionListener(this);
        RIGHT.addActionListener(this);
        LEFT.addActionListener(this);



        grid.setLayout(new GridLayout(5,5));

        //initialize the buttons
        for (int i = 0; i < count.length; i++) {
            for (int j = 0; j < count[0].length; j++) {
                buttons[i][j] = new JButton();
                //initialize all buttons with 0
                count[i][j] = 0;
                grid.add(buttons[i][j]);
            }

        }
        frame.add(grid,BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.addKeyListener(this);
        frame.setFocusable(true);
        AddRandom();

    }
    public void AddRandom(){


        //count = new int[5][5];

        int choice_1 = (int) (Math.random() * 5);
        int choice_2 = (int) (Math.random() * 5);
        int choice_3 = (int) (Math.random() * 5);
        int choice_4 = (int) (Math.random() * 5);


        System.out.println("the random address is" + choice_1 + ", " + choice_2);

        //get random value as factor of 2
        count[choice_1][choice_2]=2;
        count[choice_3][choice_4]=2;

        update();

    }
    public void AddOneRandom(){ //add a random number in an unoccupied cell
        int choice_1 = (int) (Math.random()*5);
        int choice_2 = (int) (Math.random()*5);
        while(count[choice_1][choice_2]!=0) {
                choice_1 = (int) (Math.random() * 5);
                choice_2 = (int) (Math.random() * 5);
        }
        System.out.println("the new random address is" + choice_1 + ", " + choice_2);
        count[choice_1][choice_2]=2;
        update();

    }

    public static void main(String args[]) {
        new MainGame();
    }


    public void actionPerformed(ActionEvent event){
        if(event.getSource().equals(reset)){
            System.out.print("reset was pressed");
            for (int i = 0; i < count.length; i++) {
                for (int j = 0; j < count[0].length; j++) {
                    count[i][j]=0;
                    buttons[i][j].setText("");
                }
            }
            AddRandom();
        }
        if(event.getSource().equals(DOWN))
        {
            System.out.println("computing down sum");
            compute_DOWN();
        }
        if(event.getSource().equals(RIGHT))
        {
            System.out.println("computing right sum");
            compute_RIGHT();
        }
        if(event.getSource().equals(LEFT))
        {
            System.out.println("computing left sum");
            compute_LEFT();
        }
    }
    public void compute_DOWN(){

        for (int i = 0; i < count.length-1; i++) {
            for (int j = 0; j < count.length; j++) {
                if(count[i+1][j]==0&&count[i][j]!=0){// zero condition
                    count[i+1][j]=count[i][j];
                    count[i][j]=0;
                }
                if(count[i+1][j]==count[i][j]&&count[i][j]!=0){
                    count[i+1][j]+=count[i][j];
                    count[i][j]=0;
                }
            }
        }
        update();
        AddOneRandom();
    }
    public void compute_RIGHT(){

        for (int j = 0; j < count.length-1; j++) {// moving top to bottom
            for (int i = 0; i < count.length; i++) {
                if (count[i][j + 1] == 0 && count[i][j] != 0) {
                    count[i][j + 1] = count[i][j];
                    count[i][j] = 0;

                }
                if (count[i][j + 1] == count[i][j] && count[i][j] != 0) {// matching condition
                    count[i][j + 1] += count[i][j];
                    count[i][j] = 0;
                }
            }
        }
        update();
        AddOneRandom();
    }
    public void compute_LEFT(){

        for (int j = count.length-1; j > 0 ; j--) {// moving top to bottom
            for (int i = 0; i < count.length; i++) {
                if (count[i][j - 1] == 0 && count[i][j] != 0) {// zero condition
                    count[i][j - 1] = count[i][j];
                    count[i][j] = 0;
                }
                if (count[i][j - 1] == count[i][j] && count[i][j] != 0) {
                    count[i][j - 1] += count[i][j];
                    count[i][j] = 0;
                }
            }
        }
        update();
        AddOneRandom();
    }
    public void compute_UP(){

        for (int i = count.length-1; i > 0 ; i--) {// moving top to bottom
            for (int j = 0; j < count.length; j++) {
                if (count[i-1][j] == 0 && count[i][j] != 0) {// zero condition
                    count[i-1][j] = count[i][j];
                    count[i][j] = 0;
                }
                if (count[i-1][j] == count[i][j] && count[i][j] != 0) {
                    count[i-1][j] += count[i][j];
                    count[i][j] = 0;
                }
            }
        }
        update();
        AddOneRandom();
    }


    public void update() // TO BE FIXED
    {
        for (int i = 0; i < count.length ;i++) {
            for (int j = 0; j < count.length; j++) {
                if(count[i][j]==2){
                    buttons[i][j].setForeground(Color.RED);
                    buttons[i][j].setText(count[i][j]+"");
                }
                else if(count[i][j]==4){
                    buttons[i][j].setForeground(Color.ORANGE);
                    buttons[i][j].setText(count[i][j]+"");
                }
                else if(count[i][j]==8){
                    buttons[i][j].setForeground(Color.CYAN);
                    buttons[i][j].setText(count[i][j]+"");
                }
                else if(count[i][j]==16){
                    buttons[i][j].setForeground(Color.GREEN);
                    buttons[i][j].setText(count[i][j]+"");
                }
                else if(count[i][j]==32){
                    buttons[i][j].setForeground(Color.BLUE);
                    buttons[i][j].setText(count[i][j]+"");
                }
                else if(count[i][j]==64){
                    buttons[i][j].setForeground(Color.BLACK);
                    buttons[i][j].setText(count[i][j]+"");
                }
                else {
                    buttons[i][j].setText("");
                }
            }
        }
    }

    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_RIGHT ) {
            System.out.println("right was pressed");
            compute_RIGHT();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
            System.out.println("left was pressed");
            compute_LEFT();
        }
        if (e.getKeyCode() == KeyEvent.VK_UP ) {
            System.out.println("computing top sum");
            compute_UP();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
            System.out.println("down was pressed");
            compute_DOWN();
        }

    }

    public void keyTyped(KeyEvent e){
        // do nothing

    }

    public void keyReleased(KeyEvent e){
        //do nothing


    }
}
