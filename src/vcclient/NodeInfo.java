/* -------------------------------------------------------------------- */
/*  ACCORDS PLATFORM                            */
/*  (C) 2014 by Bechir TALEB ALI (Prologue SA) <btalebali@prologue.fr>  */
/* -------------------------------------------------------------------- */
/* Licensed under the Apache License, Version 2.0 (the "License");  */
/* you may not use this file except in compliance with the License. */
/* You may obtain a copy of the License at              */
/*                                  */
/*  http://www.apache.org/licenses/LICENSE-2.0              */
/*                                  */
/* Unless required by applicable law or agreed to in writing, software  */
/* distributed under the License is distributed on an "AS IS" BASIS,    */
/* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or  */
/* implied.                             */
/* See the License for the specific language governing permissions and  */
/* limitations under the License.                   */
/* -------------------------------------------------------------------- */


package vcclient;
import com.google.gson.Gson;
public class NodeInfo {
	private String id;
	private String adminPass;
	private String status;
	private String hostname;
	private String privateip;
	private String publicip;
	private String nodeurl;
	private String owner;
	

	public NodeInfo(
			String id, String adminPass, String status,
			String hostname, String privateip, String publicip, String nodeurl, String owner){
		this.id         = id;
		this.adminPass  = adminPass;
		this.status     = status;
		this.status     = status.toString();
		this.hostname   = hostname;
		this.privateip  = privateip;
		this.publicip   = publicip;
		this.nodeurl    = nodeurl;
		this.owner      = owner;
	}
	
	public String getHostname() {
		return hostname;
	}
	
	public String getstatus() {
		return status;
	}
	
	public String getPassword() {
		return adminPass;
	}
	
	public String getPrivateip() {
		return privateip;
	}
	
	public String getPublicip() {
		return publicip;
	}
	
	public String getStatus() {
		return status.toString();
	}
	
	public String getId() {
		return id;
	}
	
	public String getNodeurl() {
		return nodeurl;
	}	
	
	public String getOwner() {
		return owner;
	}	
	
	
	public void setId(String id) {
		this.id = id;
	}
	public String getAdminPass() {
		return adminPass;
	}

	public void setAdminPass(String adminPass) {
		this.adminPass = adminPass;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public void setPrivateip(String privateip) {
		this.privateip = privateip;
	}

	
	public void setPublicip(String publicip) {
		this.publicip = publicip;
	}
	
	
	public void setNodeurl(String nodeurl) {
		this.nodeurl = nodeurl;
	}

	
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	
	public String toString(){
		Gson g = new Gson();
		return g.toJson(this);
	}
}
