package vcclient;

import com.google.gson.Gson;

public class vcloud_params {

	
	    ////////access\\\\\\\\\\
		private String id;
	    private String user;
	    private String vcpassword;
	    private String organization;
	    private String vDC;
	    private String host;
	    private String version;
		////////service params\\\\\\\\\\
	    private String vApptemplate;
	    private String VMname;
	    private String VMid;
	    private String hostname;
	    private String password;
	    private String flavors;
	    private String label;
	    private String firewallrules;
	    private String profile;
	    private String access;
	    private  String private_address;
		private String  public_address;
		private String MACaddress;
		private String Status;
	    
		
	    public vcloud_params(  
	    String id,
	     String user,
	     String vcpassword,
	     String Organization,
	     String vDC,
	     String host,
	     String version,
		 String vApptemplate,
		 String VMname,
		 String VMid,
		 String hostname,
		 String password,
		 String flavors,
		 String label,
		 String firewallrules,
		 String profile,
		 String access,    
		 String Private_address,
		 String Public_address,
		 String MAC_address,
		 String STATUS
	     ) {
	        super();
	        this.setId(id);
	        this.setUser(user);
	        this.setVCPassword(vcpassword);
	        this.setOrganization(Organization);
	        this.setvDC(vDC);
	        this.setHost(host);
	        this.setVersion(version);
	        this.setvApptemplate(vApptemplate);
	        this.setVMName(VMname);
	        this.setVMId(VMid);
	        this.setHostname(hostname);
	        this.setpassword(password);
	        this.setFlavors(flavors);
	        this.setLabel(label);
	        this.setFirewallrules(firewallrules);
	        this.setProfile(profile);
	        this.setAccess(access);
	        this.setprivadd(Private_address);
	        this.setpublicadd(Public_address);
	        this.MACaddress=MAC_address;
	        this.Status=STATUS;
	
	        		
	    }
	    


		public String getUser() {
			return user;
		}


		public void setUser(String user) {
			this.user = user;
		}


		public String getVCPassword() {
			return vcpassword;
		}


		public void setVCPassword(String vcpassword) {
			this.vcpassword = vcpassword;
		}


		public String getOrganization() {
			return organization;
		}


		public void setOrganization(String Organization) {
			this.organization = Organization;
		}


		public String getvDC() {
			return vDC;
		}


		public void setvDC(String vDC) {
			this.vDC = vDC;
		}


		public String getHost() {
			return host;
		}


		public void setHost(String host) {
			this.host = host;
		}


		public String getVersion() {
			return version;
		}


		public void setVersion(String version) {
			this.version = version;
		}


		public String getvApptemplate() {
			return vApptemplate;
		}


		public void setvApptemplate(String vApptemplate) {
			this.vApptemplate = vApptemplate;
		}


		public String getVMId() {
			return VMid;
		}


		public void setVMId(String Id) {
			this.VMid = Id;
		}


		public String getHostname() {
			return hostname;
		}


		public void setHostname(String hostname) {
			this.hostname = hostname;
		}


		public String getpassword() {
			return password;
		}


		public void setpassword(String password) {
			this.password = password;
		}


		public String getFlavors() {
			return flavors;
		}


		public void setFlavors(String flavors) {
			this.flavors = flavors;
		}


		public String getLabel() {
			return label;
		}


		public void setLabel(String label) {
			this.label = label;
		}


		public String getFirewallrules() {
			return firewallrules;
		}


		public void setFirewallrules(String firewallrules) {
			this.firewallrules = firewallrules;
		}


		public String getProfile() {
			return profile;
		}


		public void setProfile(String profile) {
			this.profile = profile;
		}


		public String getAccess() {
			return access;
		}


		public void setAccess(String access) {
			this.access = access;
		}


		public String getVMname() {
			return VMname;
		}


		public void setVMName(String name) {
			this.VMname = name;
		}
		
		
		
		public String getprivadd() {
			return private_address;
		}


		public void setprivadd(String Private_address) {
			this.private_address = Private_address;
		}
		
		
		public String getpublicadd() {
			return public_address;
		}


		public void setpublicadd(String Public_address) {
			this.public_address = Public_address;
		}	
		
		
		
		public void printattributs()
		{
			System.out.println("///////////VCLOUD categories attributes////////////");
			System.out.println("id             = "+ this.id);
			System.out.println("user             = "+ this.user);
			System.out.println("vcpassword         = "+ this.vcpassword);
			System.out.println("organization     = "+ this.organization);
			System.out.println("vDC              = "+ this.vDC);
			System.out.println("host             = "+ this.host);
			System.out.println("version          = "+ this.version);
			
			System.out.println("vApptemplate     = "+ this.vApptemplate);
			System.out.println("VMname           = "+ this.VMname);
			System.out.println("id               = "+ this.VMid);
			System.out.println("hostname         = "+ this.hostname);
			System.out.println("password         = "+ this.password);
			System.out.println("flavors          = "+ this.flavors);
			System.out.println("label            = "+ this.label);
			System.out.println("firewallrules    = "+ this.firewallrules);
			System.out.println("profile          = "+ this.profile);
			System.out.println("access           = "+ this.access);			
			System.out.println("Private Address  = "+ this.private_address);
			System.out.println("Public Address   = "+ this.public_address);
				    
		}



		public String getMACaddress() {
			return MACaddress;
		}



		public void setMACaddress(String mACaddress) {
			MACaddress = mACaddress;
		}



		public String getStatus() {
			return Status;
		}



		public void setStatus(String status) {
			Status = status;
		}
		
		public String toString(){
			Gson g = new Gson();
			return g.toJson(this);
		}



		public String getId() {
			return id;
		}



		public void setId(String id) {
			this.id = id;
		}}




   

