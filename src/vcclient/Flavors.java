package vcclient;

import java.util.LinkedList;
import java.util.StringTokenizer;


public class Flavors {
	LinkedList<String> liste = new LinkedList<String>();
	private int vCPU;
	private float RAM;
	private int disk;
	private float speed;
	public Flavors(String flavors)
	
	{
		//System.out.println("flavors=");
		
		StringTokenizer ST1 = new StringTokenizer(flavors,";");

		while ( ST1.hasMoreTokens () ) 
		{
			this.liste.add(ST1.nextToken());
		}
		
		this.setvCPU((int) Integer.decode(liste.get(0)));
		this.setRAM((float) Float.parseFloat(liste.get(1)));
		this.setDisk(Integer.decode(liste.get(2)));
		this.setSpeed((float) Float.parseFloat(liste.get(3)));
		  
	}
	public int getvCPU() {
		return vCPU;
	}
	public void setvCPU(int vCPU) {
		this.vCPU = vCPU;
	}
	public float getRAM() {
		return RAM;
	}
	public void setRAM(float rAM) {
		RAM = rAM;
	}
	public int getDisk() {
		return disk;
	}
	public void setDisk(int disk) {
		this.disk = disk;
	}
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public void printattributs()
	{	
		System.out.println("///////////Flavors caracteristics////////////");
		System.out.println("vCPU  = "+ this.vCPU  + "cores");
		System.out.println("RAM   = "+ this.RAM   + "GHz");
		System.out.println("disk  = "+ this.disk  + "GHz");
		System.out.println("speed = "+ this.speed + "GHz");
	}
}
