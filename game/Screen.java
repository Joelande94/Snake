package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class Screen extends JPanel implements Runnable{

private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 800, HEIGHT = 800;
	private Thread thread;
	private boolean running = false;
	
	private BodyPart b;
	private ArrayList<BodyPart> snake;
	
	private Apple apple;
	private ArrayList<Apple> apples;
	
	private Random r;
	
	private int xCoor = 0, yCoor = 0;
	private int size = 5;
	
	private boolean right = true, left = false, up = false, down = false;
	
	private int ticks = 0;
	
	private double time;
	
	private Key key;
	
	public Screen(){
		setFocusable(true);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		snake = new ArrayList<BodyPart>();
		apples = new ArrayList<Apple>();
		
		r = new Random();
		
		key = new Key();
		addKeyListener(key);
		
		start();
	}
	

	
	void tick(){
		
		if(snake.size() == 0){
			b = new BodyPart(xCoor, yCoor, 40);
			snake.add(b);
		}
		
		if(apples.size() == 0){
			boolean notVisible = true;
			int xCoor = r.nextInt(19);
			int yCoor = r.nextInt(19);
			
//			while(notVisible){
//				xCoor = r.nextInt(19);
//				yCoor = r.nextInt(19);
//				for(int i=0; i<snake.size(); i++){
//					if((snake.get(i).getxCoor() == xCoor && snake.get(i).getyCoor() == yCoor)){
//						notVisible = true;
//					}
//				}
//				if(notVisible == false){
//					apple = new Apple(xCoor, yCoor, 40);
//					apples.add(apple);
//				}
//			}
			apple = new Apple(xCoor, yCoor, 40);
			apples.add(apple);
		}


		for(int i=0; i<apples.size(); i++){
			if(apples.get(i).getxCoor() == xCoor && apples.get(i).getyCoor() == yCoor){
				size++;
				apples.remove(i);
				i--;
			}
		}
		
		if(xCoor > 19 || xCoor < 0 || yCoor > 19 || yCoor < 0){
			stop();
		}

		for(int i=0; i<snake.size(); i++){
			if(snake.get(i).getxCoor() == xCoor && snake.get(i).getyCoor() == yCoor){
				if(i != snake.size() - 1) {
					System.out.println("stop");
					stop();
				}
			}
		}

		ticks++;
		if(System.nanoTime() - time > 100000000){
			time = System.nanoTime();
			if(right) xCoor++;
			if(left) xCoor--;
			if(up) yCoor--;
			if(down) yCoor++;
			
			ticks = 0;
			
			b = new BodyPart(xCoor, yCoor, 40);
			snake.add(b);
			
			if(snake.size() > size){
				snake.remove(0);
			}
		}
		
	}
	
	public void paint(Graphics g){
		//Clear previous screen
		g.clearRect(0, 0, WIDTH, HEIGHT);
		
		//Draw background
		g.setColor(new Color(10, 50, 0));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		//Draw apples
		for(int i=0; i<apples.size(); i++){
			apples.get(i).draw(g);
		}
			
		//Draw snake
		for(int i=0; i<snake.size(); i++){
			snake.get(i).draw(g);
		}
		
		//Draw score
		g.setColor(Color.BLACK);
		g.drawString(size-4 + "", WIDTH/2 - 5, 50);
	}
	
	@Override
	public void run() {
		while(running){
			tick();
			repaint();
		}
	}
	void start(){
		time = System.nanoTime();
		running = true;
		thread = new Thread(this, "Game loop");
		thread.start();
	}
	void stop(){
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public class Key implements KeyListener{
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_RIGHT && !left){
				right = true;
				up = false;
				down = false;
			}
			if(e.getKeyCode() == KeyEvent.VK_LEFT && !right){
				left = true;
				up = false;
				down = false;
			}
			if(e.getKeyCode() == KeyEvent.VK_UP && !down){
				up = true;
				right = false;
				left = false;
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN && !up){
				down = true;
				right = false;
				left = false;
			}
		}
		
		@Override
		public void keyReleased(KeyEvent arg0) {
		}
		@Override
		public void keyTyped(KeyEvent arg0) {
		}
	}
}
