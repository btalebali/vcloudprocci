package vcclient;

public class FirewallRule {
	// To discuss port 's attribute
    private String name;       // ping 
	private String protocol;   // icmp
	private String srcIp;      //0.0.0.0/0
	private String dstIp;      //0.0.0.0/0
	private String Fromport;   //-1
    private String Toport;     //-1
    private String portRange;  // unkhnown
	private String range;      //0.0.0.0/0
    private String Direction;  //inout
    
    public FirewallRule(
    		String name, String protocol, String srcIp, String dstIp,String Fromport,
    		String Toport, String PortRange,String Range,String direction) {
        //super();
        this.name = name;
        this.protocol = protocol;
        this.srcIp = srcIp;
        this.dstIp = dstIp;
        this.Fromport=Fromport;
        this.Toport=Toport;
        this.portRange = PortRange;
        this.range=Range;
        this.Direction=direction;
    }

    public String getName() {
        return name;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getSrcIp() {
        return srcIp;
    }

    public String getDstIp() {
        return dstIp;
    }

    public String getPortRange() {
        return portRange;
    }

	public String getFromport() {
		return Fromport;
	}

	public void setFromport(String fromport) {
		Fromport = fromport;
	}

	public String getToport() {
		return Toport;
	}

	public void setToport(String toport) {
		Toport = toport;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public String getDirection() {
		return Direction;
	}

	public void setDirection(String direction) {
		Direction = direction;
	}
    public void setName(String name) {
		this.name = name;
	}
    public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
    public void setPortRange(String portRange) {
 		this.portRange = portRange;
 	}
    public void setDstIp(String dstIp) {
		this.dstIp = dstIp;
	}
    public void setSrcIp(String srcIp) {
 		this.srcIp = srcIp;
 	}
}
