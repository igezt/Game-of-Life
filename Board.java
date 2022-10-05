import java.util.Random;
import java.awt.*;
import javax.swing.*;
import java.util.Arrays;
import java.awt.event.*;


public class Board extends JFrame{
	private int height;
	private int width;
	private Cell cellMap[][];
	private JButton cells[][];
	public boolean play = false;

	public Board(int height, int width){
		this.height = height;
		this.width = width;

		setSize(1800, 1000);
		setVisible(true);
		setLayout(new GridLayout(height + 1, width));
		this.cells = new JButton[height][width];	
		
		for(int i = 0; i < width - 3; i++){
			add(new JPanel());
		}
		JButton clear = new JButton("Clear");
		JButton pause = new JButton("Pause");
		JButton go = new JButton("Go");
		go.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				play = true;
			}
		});
		
		pause.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				play = false;
			}
		});

		clear.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				killAll();
			}
		});

		add(clear);
		add(pause);
		add(go);

	


		Cell tempCellMap[][] = new Cell[height][width];
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){

				

				tempCellMap[i][j] = new Cell(i, j);
				JButton temp = new JButton();


				temp.setBackground(Color.WHITE);
				temp.setPreferredSize( new Dimension(40, 40));
				temp.putClientProperty("row", i);
				temp.putClientProperty("column", j);
				temp.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e){
						JButton btn = (JButton) e.getSource();
						int y = (int) btn.getClientProperty("column");
						int x =	(int) btn.getClientProperty("row");
						boolean alive = cellAt(x, y).status() == 1;	
						
						if(play){
							if(alive){
								cellAt(x, y).overrideDead();
								btn.setBackground(Color.WHITE);
							}else if(!alive){
								cellAt(x, y).overrideLive();
								btn.setBackground(Color.GREEN);
							}
						}else if(!play){
							if(alive){
								cellAt(x,y).dead();
								btn.setBackground(Color.WHITE);
							}else if(!alive){
								cellAt(x,y).alive();
								btn.setBackground(Color.GREEN);
							}
						}

					}
				});
				
				

				add(temp);
				this.cells[i][j] = temp;
			}
		}
		this.cellMap = tempCellMap;
		/*MouseListener mouseListener = new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				System.out.println("x: " + e.getX() + " y: " + e.getY());
			}
		};
		*/
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	} 

	

	/*
	@Override
	public void mouseClicked(MouseEvent e){
		System.out.println("x: " + e.getX() + "y: " + e.getY());
	}

	@Override
	public void mouseEntered(MouseEvent e){}
	
	@Override
	public void mouseExited(MouseEvent e){}

	@Override
	public void mousePressed(MouseEvent e){}

	@Override
	public void mouseReleased(MouseEvent e){}

	*/

	public void killAll(){
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				cellAt(i, j).dead();
				buttonAt(i, j).setBackground(Color.WHITE);
			}
		}
	}
	
	public void whoNeighbours(){
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				Cell currentCell = this.cellMap[i][j];
				for(int k = -1; k <= 1; k++){
					for(int l = -1; l <= 1; l++){
						if(i + k >= 0 && i + k < height){
							if(j + l >= 0 && j + l < width){
								if(k == 0 && l == 0){
									//do nothing
								}else{	
									currentCell.addNeighbour(this.cellMap[i + k][j + l]);
								}
							}	
						}
					}
				}
			}
		}
	}
	
	public Cell cellAt(int x, int y){
		return this.cellMap[x][y];
	}

	public JButton buttonAt(int x, int y){
		return this.cells[x][y];
	}

	public void nextGeneration(){
		for(int i = 0; i < this.height; i++){
			for(int j = 0; j < this.width; j++){
				cellAt(i, j).checkNeighbours();	
			}
		}		

		for(int i = 0; i < this.height; i++){
			for(int j = 0; j < this.width; j++){
				cellAt(i,j).futureState();
				//System.out.println("x: " + cellAt(i, j).cellXPos() + " y: " + cellAt(i,j).cellYPos() + " State: " + cellAt(i, j).status());
			}
		}

		for(int i = 0; i < this.height; i++){
			for(int j = 0; j < this.width; j++){
				if(cellAt(i,j).status() == 1){
					buttonAt(i, j).setBackground(Color.GREEN);
				}else if(cellAt(i, j).status() == 0){
					buttonAt(i, j).setBackground(Color.WHITE);
				}					
			}
		}
	}

	public void firstGeneration(){
		whoNeighbours();
		for(int i = 0; i < this.height; i++){
			for(int j = 0; j < this.width; j++){
				if(cellAt(i,j).status() == 1){
					buttonAt(i, j).setBackground(Color.GREEN);
				}else if(cellAt(i, j).status() == 0){
					buttonAt(i, j).setBackground(Color.WHITE);
				}					
			}
		}
	}

	public void printCellMap(){
		for(int i = 0; i < height; i++){
			System.out.println(Arrays.toString(this.cellMap));
		}
	}

	
	public boolean getPlay(){
		return this.play;
	}


}
