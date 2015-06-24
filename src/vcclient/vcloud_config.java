package vcclient;

public class vcloud_config {
	
	private String URL;
    private String user;
    private String organization;
    private String vcpassword;
    private String version;
    public vcloud_config(String url,String user, String org, String pass, String version) {
		// TODO Auto-generated constructor stub
		 super();
		 this.setURL(url);
		 this.setUser(user);
		 this.setOrganization(org);
		 this.setVcpassword(pass);
		 this.setVersion(version);
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getVcpassword() {
		return vcpassword;
	}
	public void setVcpassword(String vcpassword) {
		this.vcpassword = vcpassword;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}

}
