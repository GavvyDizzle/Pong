import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Pong2 extends JComponent implements ActionListener, MouseMotionListener
{
	private static Rectangle[][] arr = new Rectangle[4][5];
	private static int rectHeight = 25;
	private int ballX = (int) (Math.random() * 770);
	private int ballY = arr.length * rectHeight;
	private int paddleX = 0;
	private int paddleY = 510;
	private double ballYSpeed = 5;
	private double ballXSpeed = (int) (Math.random() * 3 + 3);
	private int count = 0;
	
	
	public static void main(String[] args)
	{
		JFrame window = new JFrame("Pong Game");
		Pong2 game = new Pong2();
		window.add(game);
		window.pack();
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		createArr();
		
		Timer t = new Timer(10, game);
		t.start();
		
		window.addMouseMotionListener(game);
	}
	
	public Dimension getPreferredSize()
	{
		return new Dimension(800, 600);
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		g.setColor(new Color(178, 223, 224)); //Sky
		g.fillRect(0, 0, 800, 600);
		
		g.setColor(new Color(110, 65, 13)); //Paddle
		g.fillRect(paddleX, paddleY, 150, 15);
		
		for (int i = 0; i < arr.length; i++)
		{
			for (int j = 0; j < arr[0].length; j++)
			{
				g.setColor(Color.BLACK);
				g.fillRect((int) arr[i][j].getX(),(int) arr[i][j].getY(),(int) arr[i][j].getWidth(),(int) arr[i][j].getHeight());
					
				g.setColor(new Color(178, 223, 224));
				g.fillRect(j * 160 - 1, i * rectHeight, 1, rectHeight);
			}
			if (i > 0)
			{
				g.setColor(new Color(178, 223, 224)); //horizontal lines
				g.fillRect(0, i * rectHeight, 800, 1);
			}
		}
		
		g.setColor(new Color(155, 93, 169)); //Ball
		g.fillOval(ballX, ballY, 30, 30);
		
		if (count == arr.length * arr[0].length)
		{
			ballXSpeed = 0;
			ballYSpeed = 0;
			g.setColor(new Color( (int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255) ));
			g.setFont(new Font("sansserif", Font.BOLD, 42));
			g.drawString("YOU WIN!",  300,  300);
		}
	}
	
	private static void createArr()
	{
		for (int i = 0; i < arr.length; i++)
		{
			for (int j = 0; j < arr[0].length; j++)
			{
				Rectangle r = new Rectangle(j * 160, i * rectHeight, 160, rectHeight);
				arr[i][j] = r;
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		ballX += ballXSpeed;
		ballY += ballYSpeed;
		
		if (ballX + 30 >= paddleX && ballX <= paddleX + 150 && ballY + 30 >= paddleY && ballY <= paddleY + 15)
		{
			padBallCollision();
			ballYSpeed *= -1;
		}
		if (ballY <= 0)
			ballYSpeed *= -1;
		if (ballX >= 770)
			ballXSpeed *= -1;
		if (ballX <= 0)
			ballXSpeed *= -1;
		
		for (int i = 0; i < arr.length; i++) //test for collision
		{
			for (int j = 0; j < arr[0].length; j++)
			{
				if (ballX + 30 > arr[i][j].getX() && ballX < arr[i][j].getX() + arr[i][j].getWidth() &&
					ballY + 30 > arr[i][j].getY() && ballY < arr[i][j].getY() + arr[i][j].getHeight())
				{
					if (ballYSpeed < 0)
						ballYSpeed *= -1;
					arr[i][j].setX(1000);
					arr[i][j].setY(1000);
					arr[i][j].setHeight(0);
					arr[i][j].setWidth(0);
					//ballXSpeed = (int) (Math.random() * 3 + 3); //random x speed when hit rectangle
					count++;
				}
			}
		}
		
		repaint();
	}
	
	private void padBallCollision() {
		double collidePos = (ballX + 15) - (paddleX + 75);
		ballXSpeed = collidePos / 10;
		
	}

	@Override
	public void mouseDragged(MouseEvent e) 
	{
		ballX = e.getX();
		ballY = e.getY();
	}
	
	@Override
	public void mouseMoved(MouseEvent e)
	{
		paddleX  = e.getX() - 75;
		repaint();
	}
}
