package vcclient;
import java.io.IOException;
import java.lang.Object;
import java.lang.reflect.Array;
import java.util.Arrays;
public class TestVC_CLIENT {
	
	public static void main(String[] xrgs) throws IOException, Exception {
		// TODO Auto-generated method stub
	
		
		
		Object[] argsvCconfig= new Object[5];
		argsvCconfig[0]  = "https://172.17.117.108/";   // URL
		argsvCconfig[1]  = "uicbm";                     // user
		argsvCconfig[2]  = "prologue";	                // organisation 
		argsvCconfig[3]  = "u15i21cb21m0";              //password
		argsvCconfig[4]  = "5.1"; //1.5 // 5.5 //allowed parametres

		Object[] argsvCloud= new Object[20];		
		// user,password,organization,vDC,host,version,image,VMname,VMid,hostname,password,flavor,label,firewallrules,
		// profile,access,private_address,public_address, MAC address, VMstatus		
		
		argsvCloud[0]  = "instanceid";   				// VM id 
		argsvCloud[1]  = "vDC_prologue";  							//vDC,
		argsvCloud[2]  = "Linux";  									//catalogue
		argsvCloud[3]  ="Ubuntu12.04 x86_64";         				//vApptemplate
		argsvCloud[4]  = "VM1";   	              				//vApp name
		argsvCloud[5]  = "urn:vcloud:vm:7c1a5933-f718-4e9c-be95-7f7e815d7426";	//vAppId of the VM in vCloud platform,						                        
		argsvCloud[6]  = "uicbm";					                            //hostname,
		argsvCloud[7] = "prologue";					                        //rootpass,
		argsvCloud[8] = "1";				 	                        //cpu(N° vcpu)	
		argsvCloud[9] = "2";				 	                        //ram (GHz)
		argsvCloud[10] = "40";				 	                        //disc(Go)
		argsvCloud[11] = "EdgeGw";				 	                        //EdgeGateway
		argsvCloud[12] = "net1:10.10.10.0/16:subnet1:10.0.0.0/24:8.8.8.8"; //label for network
		
		argsvCloud[13] = "cosacs:8286:8286:tcp:0.0.0.0/0:inout*"
    				+ "http:80:80:tcp:0.0.0.0/0:inout*"
    				+ "proxy:8080:8080:tcp:0.0.0.0/0:inout*"
				    + "https:434:434:tcp:0.0.0.0/0:inout*"
			        + "ping:-1:-1:icmp:0.0.0.0/0:inout*"
			        + "ssh:22:22:tcp:0.0.0.0/0:inout*"
			        + "ACCORDS:8000:8005:tcp:0.0.0.0/0:inout";  // security group for this VM
		
		argsvCloud[14] = "private";              //access 		
		argsvCloud[15] = "notset";               //private address
		argsvCloud[16] = "notset";               //public address
		argsvCloud[17] = "notset";               //MAC address
		argsvCloud[18] = "notset";               //VM status
		argsvCloud[19] = "notset";               //Agent status
		

		
		
		
		
		


		
		//String[] catalogues = (String[]) ManageIaaS.get_catalogues(argsvCconfig);
		//System.out.println(catalogues.length);
		
		//String[] vDC = (String[]) ManageIaaS.get_vDCs(argsvCconfig);
		//System.out.println(vDC.length);	
		
		Object[] aux = new Object[6];;		
		aux=argsvCconfig;
		aux = append(aux, argsvCloud);
		
		//String[] vApptemplate = (String[]) ManageIaaS.get_vApptemplatepervDC(aux);
		
		
		//String[] EDGWs = (String[]) ManageIaaS.get_EDGEpervDC(aux);
		//System.out.println(EDGWs[0]);	
		
		
		//String[] vDCORGs = (String[]) ManageIaaS.get_vDCORGpervDC(aux);
		//System.out.println(vDCORGs[0]);	
		

		//String[] vApps = (String[]) ManageIaaS.get_vAppspervDC(aux);
		//System.out.println(vApps.length);	
	
		Object[] aux2 = new Object[5];		
		aux=argsvCconfig;
		aux = append(aux, argsvCloud);	
		



/*
 *  FAILED_CREATION(-1)
 *  UNRESOLVED(0)
 *  RESOLVED(1) 
 *  DEPLOYED(2)
 *  SUSPENDED(3)
 *  POWERED_ON(4)
 *  UNKNOWN(6) - Entity state could not be retrieved from the inventory, e.g., VM power state is null. 
 *  UNRECOGNIZED(7) - Entity state was retrieved from the inventory but could not be mapped to an internal state. 
 *  POWERED_OFF(8) - All VMs of the vApp are powered off. 
 *  INCONSISTENT_STATE(9) Apply to VM status, if a vm is POWERED_ON, or WAITING_FOR_INPUT, 
 *  but is undeployed, it is in inconsistent state. MIXED(10) - 
 *  vApp status is set to MIXED when the VMs in the vApp are in different power states.
 * */


// state for ACCORDS
 // (1)    power on
 // (4)    deployment error
 // (8)    En cours d'exécution des actions
 // (2)    suspended
 // (0)    stopped
		
				
		
		ManageIaaS.create_server(aux2);
		//Procci.stop_server(args); 

		//Procci.restart_server(args); // hardreboot
				
		//Procci.delete_server(args);		
        
	    //Procci.suspend_server(args);
        
	    //Procci.resume_server(args);
         
        ///// reseting Virtual Machine /////
        //As with physical computers, you may need to reset a guest operating system that has become unresponsive. 
	    //This is generally not recommended: If you reset a virtual machine while the virtual disk is being written to, data may be lost or corrupted.
		//Procci.reset_server(args);		

}
	
	static <T> T[] append(T[] arr, T element) {
	    final int N = arr.length;
	    arr = Arrays.copyOf(arr, N + 1);
	    arr[N] = element;
	    return arr;
	}	
	
}
