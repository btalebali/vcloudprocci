package vcclient;

public class vcloud_param {
	
	
	String  Id;
	String  vDC;
	String  catalogue;
	String  vApptemplate;
	String  vAppName  ;
	String   vAppId ;
	String   hostname ;
	String   rootpass ;
	String    cpu;
	String    ram;
	String   disc ;
	String    EdgeGateway;
	String    netlabel;
	String    FWrules;
	String    access;
	String    privateaddress;
	String    publicaddress ;
	String    Macaddress;
	String    vAppstatus;
	String    Agentstatus;
	public vcloud_param(String  id,String  vDC,String  catalogue ,String  vApptemplate
	,String  vAppName  
	,String   vAppId 
	,String   hostname 
	,String   rootpass 
	,String    cpu
	,String    ram
	,String   disc 
	,String    EdgeGateway
	,String    netlabel
	,String    FWrules
	,String    access
	,String    privateaddress
	,String    publicaddress 
	,String    Macaddress
	,String    vAppstatus
	,String    Agentstatus)
	{
		this.Id=id;
		this.vDC=vDC;
		this.catalogue=catalogue;
		this.vApptemplate=vApptemplate;
		this.vAppName= vAppName ;
		this.vAppId=vAppId ;
		this.hostname=hostname ;
		this.rootpass=rootpass;
		this.cpu=cpu;
		this.ram=ram;
		this.disc=disc;
		this.EdgeGateway=EdgeGateway;
		this.netlabel=netlabel;
		this.FWrules=FWrules;
		this.access=access;
		this.privateaddress=privateaddress;
		this.publicaddress=publicaddress;
		this.Macaddress=Macaddress;
		this.vAppstatus=vAppstatus;
		this.Agentstatus=Agentstatus;
		
		
	}

}
