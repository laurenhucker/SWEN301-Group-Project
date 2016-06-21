package routes;

public class Edge {

	private Node from;
	private Node to;
	private double price;
	private double duration;
	
	public Edge(Node f, Node t, double p, double d){
		this.from = f;
		this.to= t;
		this.price = p;
		this.duration = d;
	}
	
	public String toString(){
		return "From: "+from.getName()+" To: "+to.getName()+" Price: "+price+" Duration: "+duration;
	}
	
	public Node getFrom(){
		return from;
	}
	
	public Node getTo(){
		return to;
	}
	
	public int getPrice(){
		return (int) price;
	}
	
	public int getDuration(){
		return (int) duration;
	}
}