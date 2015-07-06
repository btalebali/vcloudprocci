package vcclient;

import com.google.gson.Gson;

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
	String    message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
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
	,String    Agentstatus
	,String message)
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
		this.message=message;
		
		
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getvDC() {
		return vDC;
	}
	public void setvDC(String vDC) {
		this.vDC = vDC;
	}
	public String getCatalogue() {
		return catalogue;
	}
	public void setCatalogue(String catalogue) {
		this.catalogue = catalogue;
	}
	public String getvApptemplate() {
		return vApptemplate;
	}
	public void setvApptemplate(String vApptemplate) {
		this.vApptemplate = vApptemplate;
	}
	public String getvAppName() {
		return vAppName;
	}
	public void setvAppName(String vAppName) {
		this.vAppName = vAppName;
	}
	public String getvAppId() {
		return vAppId;
	}
	public void setvAppId(String vAppId) {
		this.vAppId = vAppId;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public String getRootpass() {
		return rootpass;
	}
	public void setRootpass(String rootpass) {
		this.rootpass = rootpass;
	}
	public String getCpu() {
		return cpu;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	public String getRam() {
		return ram;
	}
	public void setRam(String ram) {
		this.ram = ram;
	}
	public String getDisc() {
		return disc;
	}
	public void setDisc(String disc) {
		this.disc = disc;
	}
	public String getEdgeGateway() {
		return EdgeGateway;
	}
	public void setEdgeGateway(String edgeGateway) {
		EdgeGateway = edgeGateway;
	}
	public String getNetlabel() {
		return netlabel;
	}
	public void setNetlabel(String netlabel) {
		this.netlabel = netlabel;
	}
	public String getFWrules() {
		return FWrules;
	}
	public void setFWrules(String fWrules) {
		FWrules = fWrules;
	}
	public String getAccess() {
		return access;
	}
	public void setAccess(String access) {
		this.access = access;
	}
	public String getPrivateaddress() {
		return privateaddress;
	}
	public void setPrivateaddress(String privateaddress) {
		this.privateaddress = privateaddress;
	}
	public String getPublicaddress() {
		return publicaddress;
	}
	public void setPublicaddress(String publicaddress) {
		this.publicaddress = publicaddress;
	}
	public String getMacaddress() {
		return Macaddress;
	}
	public void setMacaddress(String macaddress) {
		Macaddress = macaddress;
	}
	public String getvAppstatus() {
		return vAppstatus;
	}
	public void setvAppstatus(String vAppstatus) {
		this.vAppstatus = vAppstatus;
	}
	public String getAgentstatus() {
		return Agentstatus;
	}
	public void setAgentstatus(String agentstatus) {
		Agentstatus = agentstatus;
	}
	public String toString(){
		Gson g = new Gson();
		return g.toJson(this);
	}

}
