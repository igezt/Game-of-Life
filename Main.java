import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;
import java.util.Date;
import java.lang.Math;
import java.awt.event.*;
import javax.swing.*;
public class Main{

	public static void createBoard(int height, int width){
		Board b = new Board(height, width);
			
				

		
		b.firstGeneration();	
		
	/*		
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width ; j++){
				if(Math.random() < 0.5){
					b.cellAt(i,j).alive();
				}
			}
		}
	*/	
				

			
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width ; j++){
				if(i == j || i == width - j || i == height/2){
					b.cellAt(i, j).alive();
				}
			}
		}
		
		


		for(int i = 0; i < 100000000; i++){	
			if(b.getPlay()){
				try{
					Thread.sleep(50);
				} catch (InterruptedException e){
					System.out.println("Thread is interrupted");
				}
				b.nextGeneration();
				//System.out.println("next gen");
			}else{
				try{
				Thread.sleep(500);
				} catch (InterruptedException e){
				System.out.println("Thread is interrupted");
				}
				//System.out.println("waiting");
			}
		}	
		

	}




	public static void main(String[] args){


		Scanner sc = new Scanner(System.in);

		System.out.println("What is the height and width of the board?");

		int h = sc.nextInt();
		int w = sc.nextInt();

		//System.out.println("How many generations do you want to observe?");

		//int g = sc.nextInt();
		
		createBoard(h, w);
	}
}










