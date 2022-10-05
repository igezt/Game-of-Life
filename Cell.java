import java.util.Arrays;

public class Cell{
	
	public int state = 0;
	public int nextState;
	public final int x;
	public final int y;
	public Cell[] neighbours = new Cell[8];
	public int neighbourCounter;
	public int overrideState = -1;	

	public Cell(int x, int y){
		this.x = x;
		this.y = y;
		this.neighbourCounter = 0;
	}

	public void addNeighbour(Cell a){	
		this.neighbours[this.neighbourCounter] = a;
		this.neighbourCounter++;
	}

	public String neighbourNames(){
		return Arrays.toString(this.neighbours);
	}

	
	public void future(int l){
		this.nextState = l;
	}
	
	public int status(){
		return this.state;
	}

	public void checkNeighbours(){
		//System.out.println(this.x + "," + this. y + ":" + this.neighboursCounter);

		//System.out.println("x: " + cellXPos() + " y: " + cellYPos());
		int liveNeighbours = 0;
		for(int i = 0; i < this.neighbourCounter; i++){
			if(this.neighbours[i].state == 1){
				liveNeighbours++;
			}
		}
		//System.out.println(this.x + "," + this. y + ":" + liveNeighbours);
		if(this.state == 0 && liveNeighbours == 3){
			this.nextState = 1;
		}else if(this.state == 0){
			this.nextState = 0;
		}else if(this.state == 1 && (liveNeighbours < 2 || liveNeighbours > 3)){
			this.nextState = 0;
		}else{
			this.nextState = 1;
		}
		//System.out.println(this.x + "," + this. y + ":" + this.nextState);
	}
	
	public void alive(){
		this.state = 1;
	}

	public void dead(){
		this.state = 0;
	}

	public void futureState(){
		//System.out.println("x: " + cellXPos() + " y: " + cellYPos() + " nextState: " + this.nextState);
		if(this.overrideState == -1){
			if(this.nextState == 1){
				alive();
			}else if(this.nextState == 0){
				dead();
			}
		}else if(this.overrideState == 0){
			dead();
			overrideReset();
		}else if(this.overrideState == 1){
			alive();
			overrideReset();
		}
	}

	public int cellXPos(){
		return this.x;
	}

	public int cellYPos(){
		return this.y;
	}
	
	public void overrideReset(){
		this.overrideState = -1;
	}

	public void overrideLive(){
		this.overrideState = 1;
	}
	
	public void overrideDead(){
		this.overrideState = 0;
	}
}
