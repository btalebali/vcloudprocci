package vcclient;

import java.util.LinkedList;
import java.util.StringTokenizer;


//Format EXL : LAN1:10.0.0.0/24:subnet1:10.0.0.0/28:8.8.8.8

public class vApp_Network 
{
    private LinkedList<String> liste = new LinkedList<String>();
    

	private String vAppNetName1;
	private String gateway1;
	private String Netmask1;
	private String DNS1;
    /////// default constructor
	public vApp_Network(String label) 
	{
		// define Network Type
		if (label.equalsIgnoreCase("ethernet")|| label.equalsIgnoreCase("account") || label.equalsIgnoreCase("service")|| label.equalsIgnoreCase("contract") )   
		{
			
			this.vAppNetName1 = label;
			this.gateway1     = "192.168.0.1";
			this.Netmask1     = "255.255.255.0";
			this.DNS1         = "8.8.8.8";
		
		}
		else
		{
		
		
		// Parsing LABEL
		
		StringTokenizer ST1 = new StringTokenizer(label,":");
		
		while ( ST1.hasMoreTokens()) {this.liste.add(ST1.nextToken());}
		this.setvAppNetName1(liste.get(0));
		String LAN_ADDRESS=liste.get(1);
		
		// parse LAN_ADDRESS to retrieve DNS and Gateway 
		parse(LAN_ADDRESS );
		// TODO   Create onother Private Network (Subnet)
		//EXL subnet1:10.0.0.0/28
		liste.get(2);   // not yet used
		liste.get(3);   // not yet used		
		this.DNS1=liste.get(4);				
		}
	}
	private void parse(String lAN_ADDRESS) {
		// TODO Auto-generated method stub
		StringTokenizer ST2 = new StringTokenizer(lAN_ADDRESS,"/");
		LinkedList<String> liste2 = new LinkedList<String>();
		while ( ST2.hasMoreTokens () ) 
		{
			liste2.add(ST2.nextToken());
		}
		//parse Network address, 
		StringTokenizer ST3 = new StringTokenizer(liste2.get(0),".");
		LinkedList<String> liste3 = new LinkedList<String>();
		while(ST3.hasMoreTokens()) 
		{
			liste3.add(ST3.nextToken());
		}
		liste3.set(3,String.valueOf((1+Integer.parseInt(liste3.get(3)))) );
		this.gateway1=liste3.get(0)+"."+liste3.get(1)+"."+liste3.get(2)+"."+liste3.get(3);

		//convert CIDR to Classical Mask
		this.Netmask1= convertCIDRtoClassikMask(liste2.get(1));
	}
	private String convertCIDRtoClassikMask(String CIDR) {
		// TODO Auto-generated method stub
		switch (Integer.parseInt(CIDR))
		{
		case 15: return("255.254.0.0");
		case 16: return("255.255.0.0");
		case 17: return("255.255.128.0");
		case 18: return("255.255.192.0");
		case 19: return("255.255.224.0");
		case 20: return("255.255.240.0");
		case 21: return("255.255.248.0");
		case 22: return("255.255.252.0");
		case 23: return("255.255.254.0");
		case 24: return("255.255.255.0");
		case 25: return("255.255.255.128");
		case 26: return("255.255.255.192");
		case 27: return("255.255.255.224");
		case 28: return("255.255.255.240");
		case 29: return("255.255.255.248");
		case 30: return("255.255.255.252");
		case 31: return("255.255.255.254");
		case 32: return("255.255.255.255");
		default: return("255.255.255.0");
		}		
	}
	
	public String getvAppNetName1() {
		return vAppNetName1;
	}
	public void setvAppNetName1(String vAppNetName) {
		this.vAppNetName1 = vAppNetName;
	}
	public String getgateway1() {
		return gateway1;	
	}
	public void setgateway1(String Gateway1) {
		gateway1 = Gateway1;
	}
	public String getNetmask1() {
		return Netmask1;
	}
	public void setNetmask1(String netmask1) {
		Netmask1 = netmask1;
	}	
	public String getDNS() {
		return DNS1;
	}
	public void setDNS(String dNS) {
		DNS1 = dNS;
	}
	public void printattributs()
	{
		System.out.println("///////////Network caracteristics////////////");
		System.out.println("vAppNetwork Name = "+ this.vAppNetName1);
		System.out.println("gateway1         = "+ this.gateway1);
		System.out.println("Netmask1         = "+ this.Netmask1);
		System.out.println("DNS1             = "+ this.DNS1);
	}
	public String getStartaddress() {
		// TODO Auto-generated method stub
		String GW1= this.getgateway1();
		// add 1 to GW1
		
		//parse Network address, 
		StringTokenizer ST3 = new StringTokenizer(GW1,".");
		LinkedList<String> liste3 = new LinkedList<String>();
		while(ST3.hasMoreTokens()) 
		{
			liste3.add(ST3.nextToken());
		}
		liste3.set(3,String.valueOf((1+Integer.parseInt(liste3.get(3)))) );
		String results=liste3.get(0)+"."+liste3.get(1)+"."+liste3.get(2)+"."+liste3.get(3);
		
		return results;

	}
	public String getEndaddress(int i) {
		// TODO Auto-generated method stub
		String GW1= this.getgateway1();
		// add i+1 to GW1
		StringTokenizer ST3 = new StringTokenizer(GW1,".");
		LinkedList<String> liste3 = new LinkedList<String>();
		while(ST3.hasMoreTokens()) 
		{
			liste3.add(ST3.nextToken());
		}
		liste3.set(3,String.valueOf((i+1+Integer.parseInt(liste3.get(3)))) );
		String results=liste3.get(0)+"."+liste3.get(1)+"."+liste3.get(2)+"."+liste3.get(3);

		return results;

	}
	

}
