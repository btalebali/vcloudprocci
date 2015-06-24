package vcclient;
import java.io.IOException;
public class TestVC_CLIENT {
	
	public static void main(String[] xrgs) throws IOException, Exception {
		// TODO Auto-generated method stub
		
		Object[] argsvCconfig= new Object[5];
		
		argsvCconfig[0]  = "https://172.17.117.108/";   // URL
		argsvCconfig[1]  = "uicbm";    // user
		argsvCconfig[2]  = "prologue";	      // organisation 
		argsvCconfig[3]  = "u15i21cb21m0";    //password
		argsvCconfig[4]  = "5.5"; //1.5 // 5.5 //allowed parametres
		
		ManageIaaS.get_catalogues(argsvCconfig);
		
//		ManageIaaS.get_vApptemplate(argsvCconfig);
				
		//		ManageIaaS.vDC(argsvCconfig);
		
		//		ManageIaaS.EDGW(argsvCconfig);
		
		//	ManageIaaS.vApps(argsvCconfig);
		
		//	ManageIaaS.vDCorg(argsvCconfig);
		
		
		
		Object[] argsvCloud= new Object[21];
		
		// user,password,organization,vDC,host,version,image,VMname,VMid,hostname,password,flavor,label,firewallrules,
		// profile,access,private_address,public_address, MAC address, VMstatus		
		argsvCloud[0]  = "id for accords platform";   // VM id    									//id
		argsvCloud[1]  = "uicbm";          			//user
		argsvCloud[2]  = "u15i21cb21m0";   			//password,
		argsvCloud[3]  = "prologue";       			//organization,
		argsvCloud[4]  = "vDC_prologue";  			//vDC,
		argsvCloud[5]  = "https://172.17.117.108/"; 	//host,
		argsvCloud[6]  = "v5.5";      				//version,
		argsvCloud[7]  ="Ubuntu12.04 x86_64";         //"rhel-server-5.7-i386";
		argsvCloud[8]  = "VM1 net2";   	
		argsvCloud[9]  = "urn:vcloud:vm:7c1a5933-f718-4e9c-be95-7f7e815d7426";	//unique Id of the VM in vCloud platform,						                        
		argsvCloud[10]  = "uicbm";					//hostname,
		argsvCloud[11] = "prologue";					//rootpass,
		argsvCloud[12] = "1;1;16;1";					//flavors,
		argsvCloud[13] = "net1:10.10.10.0/16:subnet1:10.0.0.0/24:8.8.8.8"; //label,		
		argsvCloud[14] = "cosacs:8286:8286:tcp:0.0.0.0/0:inout*"
    				+ "http:80:80:tcp:0.0.0.0/0:inout*"
    				+ "proxy:8080:8080:tcp:0.0.0.0/0:inout*"
				    + "https:434:434:tcp:0.0.0.0/0:inout*"
			        + "ping:-1:-1:icmp:0.0.0.0/0:inout*"
			        + "ssh:22:22:tcp:0.0.0.0/0:inout*"
			        + "ACCORDS:8000:8005:tcp:0.0.0.0/0:inout";
		
		argsvCloud[15] = "TEST-FW-UPDATESC05";   //profile,
		argsvCloud[16] = "private";              //access 		
		argsvCloud[17] = "notset";               //private address
		argsvCloud[18] = "notset";               //public address
		argsvCloud[19] = "notset";               //MAC address
		argsvCloud[20] = "notset";               //VM status

/*   
     	args[8]  = "VM01";
		Procci.start_server(args);			
		args[8]  = "VM02";
		Procci.start_server(args);
		args[8]  = "VM03";
		Procci.start_server(args);
		args[8]  = "VM04";
		Procci.start_server(args);
		args[8]  = "VM05";
		Procci.start_server(args);
		args[8]  = "VM06";
		Procci.start_server(args);
		args[8]  = "VM07";
		Procci.start_server(args);
		args[8]  = "VM08";
		Procci.start_server(args);
		args[8]  = "VM09";
		Procci.start_server(args);		
*/

		//Setting VMs

		
		//VM1 net1			//urn:vcloud:vm:96a39b0f-4f36-47a5-a6fe-1ad03bbbd04e
		//VM2 net1         	//urn:vcloud:vm:4910e16e-38f7-463a-82e3-1a66f84d0995
		
// Virtual Machine Status

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
		
				
		
		//ManageIaaS.start_server(args);
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
}
