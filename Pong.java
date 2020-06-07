import java.awt.*;
import java.awt.geom.*;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import java.util.Random;
import java.util.Timer;

public class Pong extends Canvas
{
	Point delta;
	Ellipse2D.Double ball;
    Rectangle paddle1;
    Rectangle paddle2;
    int score1;
	int score2;
	Color orange = new Color(255, 140, 0); 
	
	public static void main( String[] args )
	{
		
		JFrame win = new JFrame("Pong");
		win.setSize(1010,735);
		win.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		win.add( new Pong() );
		win.setVisible(true);
	}

	

	public Pong()
	{
		enableEvents(java.awt.AWTEvent.KEY_EVENT_MASK);
		requestFocus();
		
		ball = new Ellipse2D.Double(500,350,20,20);
		delta = new Point(-5,5);
        paddle1 = new Rectangle(50,250,20,200);
        paddle2 = new Rectangle(900,250,20,200);
		
		Timer t = new Timer(true);
		t.schedule( new java.util.TimerTask()
		{
			public void run()
			{
				doStuff();
				repaint();
			}
		}, 10, 10);

	}

	public void paint( Graphics g )
	{
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(Color.black);
		g2.fill(ball);
		
		g2.setColor(Color.blue);
		g2.fill(paddle1);
		
		
        
		g2.setColor(Color.orange);
		g2.fill(paddle2);

		g2.setColor(Color.black);
		Font font = new Font("San Serif", 1, 25);
		g.setFont(font);
		g2.drawString("Player1: " + score1, 200, 55);
		g2.drawString("Player2: " + score2, 700, 55);

		if (score1 >= 20 ){
			g2.drawString("GAME OVER: Winner - Player 1 ", 300, 350);
			ball.setFrame(1001, 0, 0, 0);
			
		} 
		if (score2 >= 20 ){
			g2.drawString("GAME OVER: Winner - Player 2 ", 300, 350);
			ball.setFrame(0, 0, 0, 0);
			
		} 
		
		
	}

	public void processKeyEvent(KeyEvent e)
	{
		if ( e.getID() == KeyEvent.KEY_PRESSED )
		{
			if ( e.getKeyCode() == KeyEvent.VK_W )
			{
				paddle1.y -= 60; 
			}
			if ( e.getKeyCode() == KeyEvent.VK_S )
			{
				paddle1.y += 60;
            }
            

            if ( e.getKeyCode() == KeyEvent.VK_UP )
			{
				paddle2.y -= 60; 
			}
			if ( e.getKeyCode() == KeyEvent.VK_DOWN )
			{
				paddle2.y += 60;
			}

		}
	}
	
	public void doStuff()
	{

		
		ball.x += delta.x;
        ball.y += delta.y;
        
        

		// and bounce if we hit a wall
		if ( ball.y < 0 || ball.y+20 > 700 )
			delta.y = -delta.y;
		if ( ball.x < 0 )
			delta.x = -delta.x;
			
		// check if the ball is hitting the paddle
		if ( ball.intersects(paddle1) )
            delta.x = -delta.x;
            
        if ( ball.intersects(paddle2) )
			delta.x = -delta.x;
			
		// check for scoring
		if ( ball.x < 1 )
		{
			ball.x = 500;
			ball.y = 350;
			delta = new Point(-5,5);
			if(score2 >= 20){
				score2 = 20;
			}else {
				score2++;
			}
		}

		if ( ball.x > 1000 )
		{
			ball.x = 500;
			ball.y = 350;
			delta = new Point(-5,5);
			if(score1 >= 20){
				score1 = 20;
			
			}else {
				score1++;
			}
		}
			
		
		
	}
	
	public boolean isFocusable() { return true;	}
}