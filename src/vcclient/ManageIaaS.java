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

// Setting Logger 
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import com.vmware.vcloud.api.rest.schema.ReferenceType;

import java.util.logging.Level;

import javax.xml.namespace.QName;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.vmware.vcloud.sdk.admin.EdgeGateway;
import com.vmware.vcloud.sdk.admin.ExternalNetwork;
import com.vmware.vcloud.sdk.constants.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.TimeoutException;

import javax.xml.bind.JAXBElement;

import com.vmware.vcloud.api.rest.schema.FirewallRuleProtocols;
import com.vmware.vcloud.api.rest.schema.FirewallRuleType;
import com.vmware.vcloud.api.rest.schema.FirewallServiceType;
import com.vmware.vcloud.api.rest.schema.GatewayFeaturesType;
import com.vmware.vcloud.api.rest.schema.GatewayInterfaceType;
import com.vmware.vcloud.api.rest.schema.GatewayNatRuleType;
import com.vmware.vcloud.api.rest.schema.GuestCustomizationSectionType;
import com.vmware.vcloud.api.rest.schema.InstantiateVAppTemplateParamsType;
import com.vmware.vcloud.api.rest.schema.InstantiationParamsType;
import com.vmware.vcloud.api.rest.schema.IpRangeType;
import com.vmware.vcloud.api.rest.schema.IpRangesType;
import com.vmware.vcloud.api.rest.schema.IpScopeType;
import com.vmware.vcloud.api.rest.schema.IpScopesType;
import com.vmware.vcloud.api.rest.schema.NatRuleType;
import com.vmware.vcloud.api.rest.schema.NatServiceType;
import com.vmware.vcloud.api.rest.schema.NetworkConfigSectionType;
import com.vmware.vcloud.api.rest.schema.NetworkConfigurationType;
import com.vmware.vcloud.api.rest.schema.NetworkConnectionSectionType;
import com.vmware.vcloud.api.rest.schema.NetworkConnectionType;
import com.vmware.vcloud.api.rest.schema.NetworkFeaturesType;
import com.vmware.vcloud.api.rest.schema.NetworkServiceType;
import com.vmware.vcloud.api.rest.schema.NetworksType;
import com.vmware.vcloud.api.rest.schema.ObjectFactory;
import com.vmware.vcloud.api.rest.schema.OrgVdcNetworkType;
import com.vmware.vcloud.api.rest.schema.RecomposeVAppParamsType;
import com.vmware.vcloud.api.rest.schema.ReferencesType;
import com.vmware.vcloud.api.rest.schema.SourcedCompositionItemParamType;
import com.vmware.vcloud.api.rest.schema.StaticRouteType;
import com.vmware.vcloud.api.rest.schema.StaticRoutingServiceType;
import com.vmware.vcloud.api.rest.schema.SubnetParticipationType;
import com.vmware.vcloud.api.rest.schema.VAppNetworkConfigurationType;
import com.vmware.vcloud.api.rest.schema.VmType;
import com.vmware.vcloud.api.rest.schema.ovf.CimString;
import com.vmware.vcloud.api.rest.schema.ovf.MsgType;
import com.vmware.vcloud.api.rest.schema.ovf.Network;
import com.vmware.vcloud.api.rest.schema.ovf.RASDType;
import com.vmware.vcloud.api.rest.schema.ovf.SectionType;
import com.vmware.vcloud.sdk.OrgVdcNetwork;
import com.vmware.vcloud.sdk.Organization;
import com.vmware.vcloud.sdk.ReferenceResult;
import com.vmware.vcloud.sdk.Task;
import com.vmware.vcloud.sdk.VCloudException;
import com.vmware.vcloud.sdk.VM;
import com.vmware.vcloud.sdk.Vapp;
import com.vmware.vcloud.sdk.VcloudClient;
import com.vmware.vcloud.sdk.Vdc;
import com.vmware.vcloud.sdk.constants.FenceModeValuesType;
import com.vmware.vcloud.sdk.constants.IpAddressAllocationModeType;
import com.vmware.vcloud.sdk.constants.UndeployPowerActionType;
import com.vmware.vcloud.sdk.constants.Version;
import com.vmware.vcloud.sdk.exception.MissingPropertyException;
import com.vmware.vcloud.sdk.VirtualCpu;
import com.vmware.vcloud.sdk.VirtualDisk;
import com.vmware.vcloud.sdk.VirtualMemory;
import com.google.common.net.InetAddresses;

import examples.NetworkPoolCRUD;


public class ManageIaaS {
	public static final long BOOT_DELAY = 80000; //  5 min
	public static final int  IP_STATIC_RAGE = 50; // Maximum number of VM per private Network
	public static final int  PASSWORD_LENGTH = 10; 
	private static final 	 ReferenceType NullPointerException = null;
	public static    		 VcloudClient vcloudClient;
	private static InetAddresses InetAddresses;
	static final     		 Logger logger = LogManager.getLogger(ManageIaaS.class.getName());
	private static final ObjectFactory OBJECT_FACTORY = null;
	/**
	 * Default constructor
	 */

	public ManageIaaS() { }      						




	public static Object start_server(Object[] args) throws Exception,IOException, VCloudException
	{
		// user,password,organization,vDC,host,version,image,name,id,hostname,password,flavor,label,firewallrules,
		// profile,access,private_address,public_address



		vcloud_params param =new vcloud_params(
				args[0].toString(), args[1].toString(), args[2].toString(), args[3].toString(), args[4].toString(),  
				args[5].toString(),  args[6].toString(),args[7].toString(), args[8].toString(),  args[9].toString(),  
				args[10].toString(), args[11].toString(), args[12].toString(),args[13].toString(), args[14].toString(), 
				args[15].toString(), args[16].toString(), args[17].toString(),args[18].toString(),args[19].toString(),args[20].toString());

		//set VM password
		param.setpassword(generate(PASSWORD_LENGTH));
		param.printattributs();
		Flavors Fl1 = new Flavors(param.getFlavors());              // setting flavors

		vApp_Network Network = new vApp_Network(param.getLabel());  // setting Network		

		LinkedList<FirewallRule> FirewallRulles =new LinkedList<>();
		FirewallRulles= parseFirewallRulles(param.getFirewallrules()); // setting firewall rules

		VcloudClient.setLogLevel(Level.OFF);

		//Login To VMware platform 
		VcloudClient vcloudClient = authenticateauxiliaire( param );

		// Retrieve Organization reference      
		ReferenceType OrgRef = vcloudClient.getOrgRefByName(param.getOrganization());

		// Retrieve vDC Reference  
		ReferenceType vdcLink = Organization.getOrganizationByReference(vcloudClient, OrgRef).getVdcRefByName(param.getvDC());

		// Retrieve Account's vApp Reference  
		ReferenceType vAppRef = Vdc.getVdcByReference(vcloudClient, vdcLink).getVappRefByName("ACCOUNT_"+param.getProfile()) ;


		if(vAppRef!=NullPointerException)    // A vApp exists 
		{
			// Retrieve vApp  
			vcloudClient = authenticateauxiliaire(param);
			Vapp vapp = Vapp.getVappByReference(vcloudClient, vAppRef);

			//1/ If Network exists
			if( vapp.getNetworkByName(Network.getvAppNetName1()) != null)
			{
				// if VM exists with same name and ID , we started it else created  
				VM VMstart2=search_VM_BY_ID(vapp,param);
				if(VMstart2 != null)
				{
					System.out.println("before VMstart2.getResource().getStatus()= " + VMstart2.getResource().getStatus());
					VMstart2.powerOn().waitForTask(1000000, 1000);
					//while (!(VMstart2.getVMStatus().toString().equals("POWERED_ON") ))
					Thread.sleep(BOOT_DELAY); 
					System.out.println("after VMstart2.getResource().getStatus()= " + VMstart2.getResource().getStatus());
					//param.setStatus(VMstart2.getResource().getStatus());
					param.setStatus("POWERED_ON");
				}
				else 
				{
					// add VM to this Network

					vapp.recomposeVapp(createRecomposeParams(vAppRef, param)).waitForTask(1000000, 10000);

					// refresh the vApp
					vapp = Vapp.getVappByReference(vcloudClient, vAppRef);

					//find a vDV            
					Vdc vdc =  ManageIaaS.findVdc(vcloudClient, param.getOrganization(), param.getvDC());

					configureVMsIPAddressingMode_staticmode(vcloudClient,vapp.getReference(), vdc,Network);

					//Retrieving VM

					vcloudClient = authenticateauxiliaire( param);
					vapp = Vapp.getVappByReference(vcloudClient, vapp.getReference());
					VM VM2 = getVMbyname(vapp,param.getvApptemplate()); //  

					//setting up VM Name
					VmType vmType = VM2.getResource();  
					vmType.setName(param.getVMname());  
					VM2.updateVM(vmType).waitForTask(1000000, 10000); 

					// Virtual Machine Customization        
					VMGuestCustomization(VM2,param,Fl1);
					VM2.getGuestCustomizationSection().setEnabled(true);

					// Power on VM
					VM2.powerOn().waitForTask(1000000, 10000);

					// waiting for OS to boot // Specific for  Alhambra Platform // until VM status = POWER ON 
					// waiting for OS to boot 
					//while (!(VM2.getVMStatus().toString().equals("POWERED_ON") ))
					Thread.sleep(BOOT_DELAY); 


					// Retrieve private and public IP
					vcloudClient = authenticateauxiliaire( param);
					VM2 = com.vmware.vcloud.sdk.VM.getVMById(vcloudClient,VM2.getReference().getId());
					NetworkConnectionType nic0= VM2.getNetworkConnections().iterator().next();

					if (nic0.getIpAddress() != null && nic0.getExternalIpAddress() != null)
					{
						param.setpublicadd(nic0.getExternalIpAddress());
						param.setprivadd(nic0.getIpAddress());
					}

					// retrieve MAC address
					param.setMACaddress(VM2.getNetworkConnections().iterator().next().getMACAddress());

					//TODO Release Public IP if we have a private access.


					// Retrieve VM status

					param.setStatus(VM2.getVMStatus().toString());
					System.out.println("VM2.getResource().getId()"+VM2.getResource().getId());
					param.setVMId(VM2.getResource().getId());

					// Update Network rules
					//updateNetworkRules(vapp,param,Network);
				}

			}
			else
			{
				//2/ if not create another network and attach this network to vApp 
				logger.fatal("Creating  " + Network.getvAppNetName1() + " then, attachning  VM to  " + Network.getvAppNetName1() + "  on  " +vapp.getResource().getName());

				vapp.recomposeVapp(createRecomposeParams_add_vappnetwork(vAppRef, param,Network,FirewallRulles)).waitForTask(1000000, 10000);

				// refresh the vApp
				vapp = Vapp.getVappByReference(vcloudClient, vAppRef);

				System.out.println("vApp Recomposed : "
						+ vapp.getReference().getName());

				//find a vDV            
				Vdc vdc =  ManageIaaS.findVdc(vcloudClient, param.getOrganization(), param.getvDC());

				configureVMsIPAddressingMode_staticmode(vcloudClient,vapp.getReference(), vdc,Network);

				//////////////////guest customization/////////////////////////////////////////////////////
				//////////////////Login To VMware platform ///////////////////////////////////////////////
				////////////////// refresh  //////////////////////////////////////////////////////////////
				vcloudClient = authenticateauxiliaire( param);


				vapp = Vapp.getVappByReference(vcloudClient, vapp.getReference());
				VM VM2 = vapp.getChildrenVms().get(1); // We suppose that we have one Virtual Machine on the vApp

				// Virtual Machine Customization ///         
				VMGuestCustomization(VM2,param,Fl1);
				VM2.getGuestCustomizationSection().setEnabled(true);

				// power on VM
				VM2.powerOn().waitForTask(1000000, 10000);

				// waiting for OS to boot 
				//	while (!(VM2.getVMStatus().toString().equals("POWERED_ON") ))
				Thread.sleep(BOOT_DELAY); 


				// Retrieve private and public IP
				//// refresh 
				vcloudClient = authenticateauxiliaire( param);
				VM2 = com.vmware.vcloud.sdk.VM.getVMById(vcloudClient,VM2.getReference().getId());
				NetworkConnectionType nic0= VM2.getNetworkConnections().iterator().next();

				if (nic0.getIpAddress() != null && nic0.getExternalIpAddress() != null)
				{
					param.setpublicadd(nic0.getExternalIpAddress());
					param.setprivadd(nic0.getIpAddress());
				}


				// retrieve MAC address
				param.setMACaddress(VM2.getNetworkConnections().iterator().next().getMACAddress());


				// Retrieve VM status
				System.out.println(" VM2.getVMStatus().toString() = "+VM2.getVMStatus().toString());
				param.setStatus(VM2.getVMStatus().toString());
				param.setVMId(VM2.getResource().getId());
				System.out.println(" VM2.getResource().getId() = "+VM2.getResource().getId());

				// TODO release PUBLIC IP if access=private						

				// Update Network rules
				//updateNetworkRules(vapp,param,Network);


			}

		}
		else
		{
			// Create the vApp for this CloudPort ACCOUNT

			vcloudClient = authenticateauxiliaire( param);
			Vdc vdc =  ManageIaaS.findVdc(vcloudClient, param.getOrganization(), param.getvDC());

			// finding the vAppTemplate Reference using the vAppTemplate
			ReferenceType vAppTemplatereference =findvAppTemplate(vcloudClient,vdc.getReference(),param.getvApptemplate());

			if (vAppTemplatereference  == NullPointerException) 
			{
				logger.error("vApp Template Not Found, please Verify vApp Template Name ... ");
				return "Template Not Found";
			}

			// TODO Creating vApp without private Network 
			//Vapp vapp = newvAppFromTemplate(vAppTemplatereference, vdc,"ACCOUNT_"+param.getProfile());  // Setting vApp name to ACCORDS 's Account Name 

			// With private Network
			Vapp vapp = newvAppFromTemplatewithvAppNetwork(vAppTemplatereference, vdc,Network,FirewallRulles, "ACCOUNT_"+param.getProfile());  // Setting vApp name to ACCORDS 's Account Name 

			//System.out.println(" --- newvAppFromTemplate  executed " );

			List<Task> tasks = vapp.getTasks();
			if (tasks.size() > 0) 
			{
				//  System.out.println("Creating .....");
				tasks.get(0).waitForTask(100000000,10000); //waiting for VM creation
			}


			//String aux=configureVMsIPAddressingMode(vcloudClient,vapp.getReference(), vdc);
			// Configure VM's IP addressing mode to static IP pool
			configureVMsIPAddressingMode_staticmode(vcloudClient,vapp.getReference(), vdc,Network);


			//String vappName = vapp.getReference().getName();
			vapp.getReference().setName("ACCOUNT_"+param.getProfile());

			//////////////////guest customization/////////////////////////////////////
			//////Login To VMware platform ////////////////////////////////////////////
			//// refresh
			vcloudClient = authenticateauxiliaire( param);


			vapp = Vapp.getVappByReference(vcloudClient, vapp.getReference());
			VM VM1 = vapp.getChildrenVms().get(0); // We suppose that we have one Virtual Machine on the vApp

			// Virtual Machine Customization ///         
			VMGuestCustomization(VM1,param,Fl1);
			// Deploying the Instantiated vApp

			vapp.deploy(false, 1000000, false).waitForTask(1000000, 10000);

			//setting up VM Name

			VmType vmType = VM1.getResource();  
			vmType.setName(param.getVMname());  
			VM1.updateVM(vmType).waitForTask(0); 

			// Activate VM customization 
			VM1.getGuestCustomizationSection().setEnabled(true);

			//Powering on the VM
			VM1.powerOn().waitForTask(1000000, 10000);

			// waiting for OS to boot 
			//while (!(VM1.getVMStatus().toString().equals("POWERED_ON") ))
			Thread.sleep(BOOT_DELAY); 


			// Retrieve private and public IP
			//// refresh 
			vcloudClient = authenticateauxiliaire( param);
			VM1 = com.vmware.vcloud.sdk.VM.getVMById(vcloudClient,VM1.getReference().getId());
			NetworkConnectionType nic0= VM1.getNetworkConnections().iterator().next();

			if (nic0.getIpAddress() != null && nic0.getExternalIpAddress() != null)
			{
				param.setpublicadd(nic0.getExternalIpAddress());
				param.setprivadd(nic0.getIpAddress());
			}

			// retrieve MAC address
			param.setMACaddress(VM1.getNetworkConnections().iterator().next().getMACAddress());

			// Retrieve VM status
			System.out.println("VM1.getResource().getId() = "+VM1.getResource().getId());
			param.setStatus(VM1.getVMStatus().toString());
			param.setVMId(VM1.getResource().getId());

			System.out.println("VM1.getResource().getId()"+VM1.getResource().getId());

			// release PUBLIC IP if access= private
			if (param.getAccess().equalsIgnoreCase("private"))
			{			
				System.out.println("nic0.getIpAddressAllocationMode()="+nic0.getIpAddressAllocationMode());
				nic0.setIpAddressAllocationMode(IpAddressAllocationModeType.MANUAL.value());
				nic0.setExternalIpAddress("0.0.0.0");

			}

			// Update Network rules
			//updateNetworkRules(vapp,param,Network);
		}

		// sending results to VCprocci ...
		System.out.println(param.toString());
		return param.toString();

	}


	/*   ------------------------------------   */
	/*      s t o p _ s e r v e r         */
	/*  ------------------------------------    */



	public static Object stop_server(Object[] args) throws Exception {
		// user,password,organization,vDC,host,version,image,name,id,hostname,password,flavor,label,firewallrules,
		// profile,access,private_address,public_address,           

		vcloud_params param =new vcloud_params(args[0].toString(), args[1].toString(), args[2].toString(), 
				args[3].toString(), args[4].toString(),  args[5].toString(),  args[6].toString(), 
				args[7].toString(), args[8].toString(),  args[9].toString(),  args[10].toString(), args[11].toString(), 
				args[12].toString(),args[13].toString(), args[14].toString(),  args[15].toString(), 
				args[16].toString(), args[17].toString(),args[18].toString(),args[19].toString(),args[20].toString());

		param.printattributs();
		////////////Login To VMware platform ////////////////////////////////////////////
		VcloudClient vcloudClient = authenticateauxiliaire( param);

		// Finding a vdc
		Vdc vdc =ManageIaaS.findVdc(vcloudClient, param.getOrganization(), param.getvDC());

		// reteiving vApp Ref
		ReferenceType AppRef=vdc.getVappRefByName("ACCOUNT_"+param.getProfile());

		Vapp vappstop=Vapp.getVappByReference(vcloudClient, AppRef);

		// Finding VM
		VM VMstop=search_VM_BY_ID(vappstop,param);
		System.out.println("VMdel.getResource().getStatus()= " + VMstop.getResource().getStatus());

		switch (VMstop.getResource().getStatus().toString()) {

		case "3" :   // VM status: SUSPENDED
			logger.warn("requested operation could not be executed since VM is SUSPENDED");
			//param.setStatus("SUSPENDED");
			param.setStatus(VMstop.getVMStatus().toString());
			break;
		case "4" :   // VM status: POWER_ON
			logger.warn("HARD REBOOTING Virtual Machine ");
			VMstop.undeploy(UndeployPowerActionType.POWEROFF).waitForTask(1000000, 1000);
			param.setStatus("POWERED_OFF");
			//VMdel.delete().waitForTask(1000000, 1000);
			break;

		case "8" :   // VM status POWERED_OFF
			logger.warn("VM is POWERED_OFF,Nothing to be done ");
			param.setStatus(VMstop.getVMStatus().toString());
			break;

		default: 
			//VMdel.shutdown().waitForTask(1000000, 1000);
			//VMdel.delete().waitForTask(1000000, 1000);
			logger.warn("VM on 'UNKNOWN STATUS': could not be stoped");
		}
		System.out.println(param.toString());
		return param.toString();

	}


	/*   ------------------------------------   */
	/*      r e s e t _ s e r v e r         	*/
	/*  ------------------------------------    */


	public static Object reset_server(Object[] args) throws Exception {
		// user,password,organization,vDC,host,version,image,name,id,hostname,password,flavor,label,firewallrules,
		// profile,access,private_address,public_address,           

		vcloud_params param =new vcloud_params(args[0].toString(), args[1].toString(), args[2].toString(), 
				args[3].toString(), args[4].toString(),  args[5].toString(),  args[6].toString(), 
				args[7].toString(), args[8].toString(),  args[9].toString(),  args[10].toString(), args[11].toString(), 
				args[12].toString(),args[13].toString(), args[14].toString(),  args[15].toString(), 
				args[16].toString(), args[17].toString(),args[18].toString(),args[19].toString(),args[20].toString());

		param.printattributs();
		////////////Login To VMware platform ////////////////////////////////////////////
		VcloudClient vcloudClient = authenticateauxiliaire( param);

		// Finding a vdc
		Vdc vdc =ManageIaaS.findVdc(vcloudClient, param.getOrganization(), param.getvDC());

		// reteiving vApp Ref
		ReferenceType AppRef=vdc.getVappRefByName("ACCOUNT_"+param.getProfile());

		Vapp vappsuspend=Vapp.getVappByReference(vcloudClient, AppRef);

		// Finding VM
		VM VMreset=search_VM_BY_ID(vappsuspend,param);
		System.out.println("VMdel.getResource().getStatus()= " + VMreset.getResource().getStatus());

		switch (VMreset.getResource().getStatus().toString()) {

		case "3" :   // VM status: SUSPENDED			 
			break;
		case "4" : // VM status: POWER_ON
			logger.warn(" ");
			VMreset.reset().waitForTask(1000000, 1000);
			param.setStatus(VMreset.getVMStatus().toString());
			//VMdel.delete().waitForTask(1000000, 1000);
			break;

		case "8" : // VM status POWERED_OFF	 
			break;
		default: 		 
		}
		System.out.println(param.toString());
		return param.toString();		
	}



	/*   ------------------------------------   */
	/*      s u s p e n d _ s e r v e r         */
	/*  ------------------------------------    */



	public static Object suspend_server(Object[] args) throws Exception {
		// user,password,organization,vDC,host,version,image,name,id,hostname,password,flavor,label,firewallrules,
		// profile,access,private_address,public_address,           

		vcloud_params param =new vcloud_params(args[0].toString(), args[1].toString(), args[2].toString(), 
				args[3].toString(), args[4].toString(),  args[5].toString(),  args[6].toString(), 
				args[7].toString(), args[8].toString(),  args[9].toString(),  args[10].toString(), args[11].toString(), 
				args[12].toString(),args[13].toString(), args[14].toString(),  args[15].toString(), 
				args[16].toString(), args[17].toString(),args[18].toString(),args[19].toString(),args[20].toString());

		param.printattributs();
		////////////Login To VMware platform ////////////////////////////////////////////
		VcloudClient vcloudClient = authenticateauxiliaire( param);

		// Finding a vdc
		Vdc vdc =ManageIaaS.findVdc(vcloudClient, param.getOrganization(), param.getvDC());

		// reteiving vApp Ref
		ReferenceType AppRef=vdc.getVappRefByName("ACCOUNT_"+param.getProfile());

		Vapp vappsuspend=Vapp.getVappByReference(vcloudClient, AppRef);

		// Finding VM
		VM VMsuspend=search_VM_BY_ID(vappsuspend,param);
		System.out.println("VMdel.getResource().getStatus()= " + VMsuspend.getResource().getStatus());

		switch (VMsuspend.getResource().getStatus().toString()) {

		case "3" :   // VM status: SUSPENDED
			logger.warn("VM is already suspended ");
			param.setStatus("SUSPENDED");

			break;
		case "4" : // VM status: POWER_ON
			logger.warn("SUSPENDING Virtual Machine ");
			VMsuspend.undeploy(UndeployPowerActionType.SUSPEND).waitForTask(1000000, 1000);
			param.setStatus(VMsuspend.getVMStatus().toString());
			//VMdel.delete().waitForTask(1000000, 1000);
			break;

		case "8" : // VM status POWERED_OFF
			logger.warn("VM is POWERED_OFF,Nothing to be done ");
			param.setStatus(VMsuspend.getVMStatus().toString());
			break;

		default: 
			//VMdel.shutdown().waitForTask(1000000, 1000);
			//VMdel.delete().waitForTask(1000000, 1000);
			logger.warn("VM on 'UNKNOWN STATUS': could not be stoped");
		}
		System.out.println(param.toString());
		return param.toString();		
	}




	/*   ------------------------------------   */
	/*       r e s u m e _ s e r v e r          */
	/*  ------------------------------------    */


	public static Object resume_server(Object[] args) throws Exception {
		// user,password,organization,vDC,host,version,image,name,id,hostname,password,flavor,label,firewallrules,
		// profile,access,private_address,public_address,           

		vcloud_params param =new vcloud_params(args[0].toString(), args[1].toString(), args[2].toString(), 
				args[3].toString(), args[4].toString(),  args[5].toString(),  args[6].toString(), 
				args[7].toString(), args[8].toString(),  args[9].toString(),  args[10].toString(), args[11].toString(), 
				args[12].toString(),args[13].toString(), args[14].toString(),  args[15].toString(), 
				args[16].toString(), args[17].toString(),args[18].toString(),args[19].toString(),args[20].toString());

		param.printattributs();
		////////////Login To VMware platform ////////////////////////////////////////////
		VcloudClient vcloudClient = authenticateauxiliaire( param);

		// Finding a vdc
		Vdc vdc =ManageIaaS.findVdc(vcloudClient, param.getOrganization(), param.getvDC());

		// reteiving vApp Ref
		ReferenceType AppRef=vdc.getVappRefByName("ACCOUNT_"+param.getProfile());

		Vapp vappstop=Vapp.getVappByReference(vcloudClient, AppRef);

		// Finding VM
		VM VMresume=search_VM_BY_ID(vappstop,param);
		System.out.println("VMdel.getResource().getStatus()= " + VMresume.getResource().getStatus());

		switch (VMresume.getResource().getStatus().toString()) {

		case "3" :   // VM status: SUSPENDED
			System.out.println("VMdel.getResource().getStatus()= " + VMresume.getResource().getStatus());
			VMresume.powerOn().waitForTask(1000000, 1000);
			//	while (!(VMresume.getVMStatus().toString().equals("POWERED_ON") ))
			Thread.sleep(BOOT_DELAY); 
			System.out.println("VMresume.getResource().getStatus()="+VMresume.getResource().getStatus());
			param.setStatus(VMresume.getVMStatus().toString());

			break;
		case "4" : // VM status: POWER_ON
			logger.warn("SUSPENDING Virtual Machine ");
			VMresume.undeploy(UndeployPowerActionType.SUSPEND).waitForTask(1000000, 1000);
			param.setStatus(VMresume.getVMStatus().toString());
			//VMdel.delete().waitForTask(1000000, 1000);
			break;

		case "8" : // VM status POWERED_OFF
			logger.warn("VM is POWERED_OFF,Nothing to be done ");
			param.setStatus(VMresume.getVMStatus().toString());
			break;

		default: 
			//VMdel.shutdown().waitForTask(1000000, 1000);
			//VMdel.delete().waitForTask(1000000, 1000);
			logger.warn("VM on 'UNKNOWN STATUS': could not be stoped");
			param.setStatus(VMresume.getVMStatus().toString());
		}
		System.out.println(param.toString());
		return param.toString();		
	}




	/*   ------------------------------------   */
	/*      r e s t a r t _ s e r v e r         */
	/*  ------------------------------------    */


	public static Object restart_server(Object[] args) throws Exception {     // hard reboot




		vcloud_params param =new vcloud_params(args[0].toString(), args[1].toString(), args[2].toString(), 
				args[3].toString(), args[4].toString(),  args[5].toString(),  args[6].toString(), 
				args[7].toString(), args[8].toString(),  args[9].toString(),  args[10].toString(), args[11].toString(), 
				args[12].toString(),args[13].toString(), args[14].toString(),  args[15].toString(), 
				args[16].toString(), args[17].toString(),args[18].toString(),args[19].toString(),args[20].toString());

		param.printattributs();

		//////////// Login To VMware platform ////////////////////////////////////////////
		VcloudClient vcloudClient = authenticateauxiliaire( param);

		// Finding a vdc
		param.printattributs();
		Vdc vdc =findVdc(vcloudClient,param.getOrganization(), param.getvDC());

		ReferenceType AppRef=vdc.getVappRefByName("ACCOUNT_"+param.getProfile());
		Vapp vapprestart=Vapp.getVappByReference(vcloudClient, AppRef);

		// Finding VM
		VM VMreb=search_VM_BY_ID(vapprestart,param);
		System.out.println("VMdel.getResource().getStatus()= " + VMreb.getResource().getStatus());

		switch (VMreb.getResource().getStatus().toString()) {

		case "3" :   // VM status: SUSPENDED
			logger.warn("requested operation could not be executed since VM is SUSPENDED");
			param.setStatus(VMreb.getVMStatus().toString());

			break;
		case "4" : // VM status: POWER_ON
			logger.warn("HARD REBOOTING Virtual Machine ");
			VMreb.undeploy(UndeployPowerActionType.POWEROFF).waitForTask(1000000, 1000);
			VMreb.powerOn().waitForTask(1000000, 1000);
			//	while (!(VMreb.getVMStatus().toString().equals("POWERED_ON") ))
			Thread.sleep(BOOT_DELAY); 
			param.setStatus(VMreb.getVMStatus().toString());
			//VMdel.delete().waitForTask(1000000, 1000);
			break;

		case "8" : // VM status POWERED_OFF
			logger.warn("VM is POWERED_OFF, It will be started ");
			VMreb.powerOn().waitForTask(1000000, 1000);
			//	while (!(VMreb.getVMStatus().toString().equals("POWERED_ON") ))
			Thread.sleep(BOOT_DELAY); 
			param.setStatus(VMreb.getVMStatus().toString());
			break;

		default: 
			//VMdel.shutdown().waitForTask(1000000, 1000);
			//VMdel.delete().waitForTask(1000000, 1000);
			logger.warn("VM on 'UNKHNOWN STATUS': could not be restarted");
			param.setStatus(VMreb.getVMStatus().toString());
		}
		System.out.println(param.toString());
		return param.toString();
	}


	/*  ------------------------------------    */
	/*      d e l e t e _ s e r v e r           */
	/*  ------------------------------------    */

	public static Object delete_server(Object[] args) throws Exception {

		//retrieve parameters

		vcloud_params param =new vcloud_params(
				args[0].toString(), args[1].toString(), args[2].toString(), 
				args[3].toString(), args[4].toString(),  args[5].toString(),  args[6].toString(), 
				args[7].toString(), args[8].toString(),  args[9].toString(),  args[10].toString(), args[11].toString(), 
				args[12].toString(),args[13].toString(), args[14].toString(),  args[15].toString(), args[16].toString(), 
				args[17].toString(),args[18].toString(),args[19].toString(),args[20].toString());

		/////
		param.printattributs();     

		//////////// Login To VMware platform ////////////////////////////////////////////
		VcloudClient vcloudClient = authenticateauxiliaire( param);

		// Finding a vdc

		Vdc vdc = ManageIaaS.findVdc(vcloudClient, param.getOrganization(), param.getvDC());
		ReferenceType AppRef=vdc.getVappRefByName("ACCOUNT_"+param.getProfile());
		Vapp vappd=Vapp.getVappByReference(vcloudClient, AppRef);
		VappStatus f = vappd.getVappStatus();
		//  System.out.println(" --- vApp Status "+f.toString());

		//looking for VM to delete
		VM VMdel=search_VM_BY_ID(vappd,param);
		if (VMdel  != null)
		{
			System.out.println("VMdel.getResource().getStatus()= " + VMdel.getResource().getStatus());

			switch (VMdel.getResource().getStatus().toString()) {

			case "3" :   // VM status: SUSPENDED
				//VMdel.undeploy(UndeployPowerActionType.POWEROFF).waitForTask(1000000, 1000);
				VMdel.delete().waitForTask(1000000, 1000);
				break;
			case "4" : // VM status: POWER_ON

				VMdel.undeploy(UndeployPowerActionType.POWEROFF).waitForTask(1000000, 1000);
				VMdel.delete().waitForTask(1000000, 1000);
				break;

			case "8" :
				//VMdel.undeploy(UndeployPowerActionType.POWEROFF).waitForTask(1000000, 1000);
				VMdel.delete().waitForTask(1000000, 1000);
				break;

				//default: 
				//VMdel.shutdown().waitForTask(1000000, 1000);
				//VMdel.delete().waitForTask(1000000, 1000);
			}

			//TODO if vApp donesn't include any VM then delete this vApp

			if(vappd.getChildrenVms().isEmpty())
			{       
				System.out.println("delete vApp with network");
				vappd.undeploy(UndeployPowerActionType.POWEROFF).waitForTask(1000000, 1000);
				vappd.delete().waitForTask(1000000, 1000);
			}
			param.setStatus("DELETED");
		}
		else 
		{
			System.out.println("VM does not exist");
		}

		System.out.println(param.toString());
		return param.toString();
	}



	private static VM search_VM_BY_ID(Vapp vappd, vcloud_params param) throws VCloudException
	{
		// TODO Auto-generated method stub
		List<VM> VMdel = vappd.getChildrenVms();
		VM aux = null;
		if (VMdel.isEmpty())  aux=null;
		Iterator<VM> t = VMdel.iterator();
		//aux=t.next();
		while(t.hasNext())
		{
			aux=t.next();
			System.out.println(aux.getResource().getId()+"   "+aux.getResource().getName());
			if(aux.getResource().getId().equals(param.getVMId()))
			{		
				System.out.println(aux.getResource().getId());
				return aux;

			}		
		}
		return null;// VM not found
	}

	private static VM getVMbyname(Vapp vapp, String VMname) throws VCloudException {
		// TODO Auto-generated method stub
		List<VM> ListVM = vapp.getChildrenVms();
		VM VM=null;
		for(int i=0;i<ListVM.size();i++)
		{
			System.out.println("ListVM.get(i).getResource().getName() "+ListVM.get(i).getResource().getName());
			if (ListVM.get(i).getResource().getName().equals(VMname))
				VM=ListVM.get(i);
		}

		return VM;
	}


	private static VM getVMbyVMID(Vapp vapp, String VMId) throws VCloudException {
		// TODO Auto-generated method stub
		List<VM> ListVM = vapp.getChildrenVms();
		VM VM=null;
		for(int i=0;i<ListVM.size();i++)
		{
			System.out.println("ListVM.get(i).getResource().getId() "+ListVM.get(i).getResource().getId());
			if (ListVM.get(i).getResource().getId().equals(VMId))
				VM=ListVM.get(i);
		}
		return VM;
	}


	private static RecomposeVAppParamsType createRecomposeParams_add_vappnetwork(
			ReferenceType vAppRef, vcloud_params param, vApp_Network network, LinkedList<FirewallRule> firewallRulles) throws KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, VCloudException, FileNotFoundException, InterruptedException {
		// TODO Auto-generated method stub

		//// refresh 
		vcloudClient = authenticateauxiliaire( param);
		// get the to be recomposed vapp reference.
		Vapp vapp = Vapp.getVappByReference(vcloudClient, vAppRef);
		String vappNetworkName = vapp.getNetworkConfigSection().getNetworkConfig()
				.get(0).getNetworkName();

		Vdc vdc =  findVdc(vcloudClient, param.getOrganization(),param.getvDC() );
		ReferenceType vAppTemplatereference =findvAppTemplate(vcloudClient, vdc.getReference(),param.getvApptemplate());

		SourcedCompositionItemParamType vmItem = new SourcedCompositionItemParamType();
		vmItem.setSource(vAppTemplatereference);

		NetworkConnectionSectionType networkConnectionSectionType = new NetworkConnectionSectionType();
		MsgType networkInfo = new MsgType();
		networkConnectionSectionType.setInfo(networkInfo);

		NetworkConnectionType networkConnectionType = new NetworkConnectionType();
		networkConnectionType.setNetwork(vappNetworkName);
		networkConnectionType
		.setIpAddressAllocationMode(IpAddressAllocationModeType.POOL
				.value());

		networkConnectionSectionType.getNetworkConnection().add(networkConnectionType);

		InstantiationParamsType vmInstantiationParamsType = new InstantiationParamsType();
		List<JAXBElement<? extends SectionType>> vmSections = vmInstantiationParamsType
				.getSection();
		vmSections
		.add(new ObjectFactory()
		.createNetworkConnectionSection(networkConnectionSectionType));


		// create the recompose vapp params type.
		RecomposeVAppParamsType recomposeVAppParamsType = new RecomposeVAppParamsType();
		recomposeVAppParamsType.setName("ACCOUNT_"+param.getProfile());


		// adding the vm item.
		List<SourcedCompositionItemParamType> newItems = recomposeVAppParamsType
				.getSourcedItem();
		newItems.add(vmItem);

		return recomposeVAppParamsType;	



	}

	private static RecomposeVAppParamsType createRecomposeParams(
			ReferenceType vAppRef, vcloud_params param) throws VCloudException, FileNotFoundException, InterruptedException, KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException {
		// TODO Auto-generated method stub
		//// refresh 
		vcloudClient = authenticateauxiliaire( param);
		// get the to be recomposed vapp reference.
		Vapp vapp = Vapp.getVappByReference(vcloudClient, vAppRef);
		String vappNetworkName = vapp.getNetworkConfigSection().getNetworkConfig()
				.get(0).getNetworkName();

		Vdc vdc =  findVdc(vcloudClient, param.getOrganization(),param.getvDC() );
		ReferenceType vAppTemplatereference =findvAppTemplate(vcloudClient,vdc.getReference(),param.getvApptemplate());

		SourcedCompositionItemParamType vmItem = new SourcedCompositionItemParamType();
		vmItem.setSource(vAppTemplatereference);

		NetworkConnectionSectionType networkConnectionSectionType = new NetworkConnectionSectionType();
		MsgType networkInfo = new MsgType();
		networkConnectionSectionType.setInfo(networkInfo);

		NetworkConnectionType networkConnectionType = new NetworkConnectionType();
		networkConnectionType.setNetwork(vappNetworkName);
		networkConnectionType
		.setIpAddressAllocationMode(IpAddressAllocationModeType.POOL
				.value());

		networkConnectionSectionType.getNetworkConnection().add(networkConnectionType);

		InstantiationParamsType vmInstantiationParamsType = new InstantiationParamsType();
		List<JAXBElement<? extends SectionType>> vmSections = vmInstantiationParamsType
				.getSection();
		vmSections
		.add(new ObjectFactory()
		.createNetworkConnectionSection(networkConnectionSectionType));


		// create the recompose vapp params type.
		RecomposeVAppParamsType recomposeVAppParamsType = new RecomposeVAppParamsType();
		recomposeVAppParamsType.setName("ACCOUNT_"+param.getProfile());


		// adding the vm item.
		List<SourcedCompositionItemParamType> newItems = recomposeVAppParamsType
				.getSourcedItem();
		newItems.add(vmItem);

		return recomposeVAppParamsType;
	}

	private static LinkedList<FirewallRule> parseFirewallRulles(
			String firewallrules) {
		LinkedList<FirewallRule> FirewallRullesaux =new LinkedList<>();
		// TODO Auto-generated method stub
		//Format of firewallrules: ping:-1:-1:icmp:0.0.0.0/0:inout*http:80:80:tcp:0.0.0.0/0:inout*cosacs:8286:8286:tcp:109.234.64.71/32:inout

		//parse to retrive rules
		StringTokenizer Fw1 = new StringTokenizer(firewallrules,"*");
		LinkedList<String> listerules = new LinkedList<String>();
		int NBrues=0;
		while ( Fw1.hasMoreTokens () ) 
		{


			listerules.add(Fw1.nextToken());
			//parse listerules.get(NBrues) to retrieve rule's definition and follow them on firewallRulles
			FirewallRule aux = new FirewallRule("", "", "", "", "", "", "", "", "");
			StringTokenizer Fwruledef = new StringTokenizer(listerules.get(NBrues),":");
			LinkedList<String> listerulesdef = new LinkedList<String>();
			while ( Fwruledef.hasMoreTokens () ) 
			{
				listerulesdef.add(Fwruledef.nextToken());
			}   
			NBrues++;
			aux.setName(listerulesdef.get(0));
			aux.setFromport(listerulesdef.get(1));
			aux.setToport(listerulesdef.get(2));
			aux.setProtocol(listerulesdef.get(3));
			aux.setRange(listerulesdef.get(4));
			aux.setDirection(listerulesdef.get(5));
			aux.setSrcIp("ANY");
			aux.setDstIp("ANY");
			aux.setPortRange("ANY");
			FirewallRullesaux.add(aux);

		}
		return FirewallRullesaux;       
	}

	/*   ------------------------------------   */
	/*      s t o p _ s e r v e r               */
	/*  ------------------------------------    */

	private static void VMGuestCustomization(VM vm1, vcloud_params param,
			Flavors Fl1) throws VCloudException, TimeoutException {
		// TODO Auto-generated method stub
		GuestCustomizationSectionType guestCustomizationSection = vm1
				.getGuestCustomizationSection();
		guestCustomizationSection.setEnabled(true);
		guestCustomizationSection.setAdminPasswordEnabled(true);
		guestCustomizationSection.setAdminPasswordAuto(false);
		guestCustomizationSection.setAdminPassword(param.getpassword());
		guestCustomizationSection.setComputerName(param.getHostname());
		guestCustomizationSection.setEnabled(true);
		vm1.updateSection(guestCustomizationSection).waitForTask(1000000, 1000);
		updatevmflavors(Fl1.getvCPU(), Fl1.getRAM(), Fl1.getDisk(), vm1);
	}

	/*  ------------------------------------    */
	/*       f i n d _ V d c        */
	/*  ------------------------------------    */
	public static  Vdc findVdc(VcloudClient vcloudClient2, String orgName, String vdcName)
			throws VCloudException {
		//  System.out.println(" orgName = "+orgName);
		ReferenceType orgRef = vcloudClient2.getOrgRefsByName().get(orgName);
		Organization org = Organization.getOrganizationByReference(
				vcloudClient2, orgRef);
		ReferenceType vdcRef = org.getVdcRefByName(vdcName);
		return Vdc.getVdcByReference(vcloudClient2, vdcRef);
	}


	//authenticate to VMware Platform//
	private static  VcloudClient authenticateauxiliaire(vcloud_params param) throws KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, VCloudException 
	{   
		// TODO Auto-generated method stub
		VcloudClient vcloudClient2 = new VcloudClient(param.getHost(), Version.V5_1);
		vcloudClient2.registerScheme("https", 443, FakeSSLSocketFactory.getInstance());
		vcloudClient2.login(param.getUser()+"@"+param.getOrganization(), param.getVCPassword()); 
		//	vcloudClient2.
		return vcloudClient2;
	}

	//authenticate to VMware Platform//
	private static  VcloudClient authenticate(vcloud_config var) throws KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, VCloudException 
	{   
		// TODO Auto-generated method stub
		Version a = null;

		switch (var.getVersion()) {
		case "1.5":  a=Version.V1_5;
		case "5.1":  a=Version.V5_1;
		case "5.5":  a=Version.V5_5;
		break;
		default: break;
		}

		VcloudClient vcloudClient2 = new VcloudClient(var.getURL(), a );
		vcloudClient2.registerScheme("https", 443, FakeSSLSocketFactory.getInstance());
		vcloudClient2.login(var.getUser()+"@"+var.getOrganization(), var.getVcpassword());
		return vcloudClient2;
	}


	/*  ----------------------------------------------------------------------------------  */
	/*       c o n f i g u r e _ V M s _ I P _ A d d r e s s i n g _ M o d e        */
	/*  ----------------------------------------------------------------------------------- */

	public static void configureVMsIPAddressingMode_staticmode(VcloudClient vcloudClient,ReferenceType vappRef,
			Vdc vdc, vApp_Network network) throws VCloudException, TimeoutException 
			{
		Vapp vapp = Vapp.getVappByReference(vcloudClient, vappRef);
		List<VM> childVms = vapp.getChildrenVms();
		for (VM childVm : childVms) {
			NetworkConnectionSectionType networkConnectionSectionType = childVm
					.getNetworkConnectionSection();

			List<NetworkConnectionType> networkConnections = networkConnectionSectionType
					.getNetworkConnection();
			for (NetworkConnectionType networkConnection : networkConnections) {
				networkConnection
				.setIpAddressAllocationMode(IpAddressAllocationModeType.POOL
						.value());
				networkConnection.setNetwork(network.getvAppNetName1());
				networkConnection.setIsConnected(true);


			}
			childVm.updateSection(networkConnectionSectionType).waitForTask(1000000, 1000);
		}
			}


	/*  ----------------------------------------------------------------------------------  */
	/*       c o n f i g u r e _ V M s _ I P _ A d d r e s s i n g _ M o d e        */
	/*  ----------------------------------------------------------------------------------- */

	public static String configureVMsIPAddressingMode(ReferenceType vappRef,
			Vdc vdc) throws VCloudException, TimeoutException {
		String IP="0.0.0.0";
		//System.out.println("------Configuring VM Ip Addressing Mode---");
		Vapp vapp = Vapp.getVappByReference(vcloudClient, vappRef);
		List<VM> childVms = vapp.getChildrenVms();
		for (VM childVm : childVms) {
			NetworkConnectionSectionType networkConnectionSectionType = childVm
					.getNetworkConnectionSection();
			List<NetworkConnectionType> networkConnections = networkConnectionSectionType
					.getNetworkConnection();
			for (NetworkConnectionType networkConnection : networkConnections) {
				networkConnection
				.setIpAddressAllocationMode(IpAddressAllocationModeType.POOL
						.value());
				networkConnection.setNetwork(vdc.getAvailableNetworkRefs()
						.iterator().next().getName());
				//  System.out.
				//  println("The available Network : "+vdc.getAvailableNetworkRefs().iterator()
				//  .next().getName());
			}


			childVm.updateSection(networkConnectionSectionType).waitForTask(1000000, 1000);

			for (String ip : VM
					.getVMByReference(vcloudClient, childVm.getReference())
					.getIpAddressesById().values()) {
				//System.out.println("      " + ip);
				IP=ip;
			}
		}
		return IP;
	}


	/*  -----------------------------------------------------------------   */
	/*       n e w _ v A p p _ F r o m _ T e m p l a t e            */
	/*  -----------------------------------------------------------------   */

	public static Vapp newvAppFromTemplatewithvAppNetwork(ReferenceType vAppTemplateReference,
			Vdc vdc, vApp_Network network, LinkedList<FirewallRule> firewallRulles, String vApp_name) throws VCloudException {

		//System.out.println("Instantiating the new vApp:   " + vAppTemplateReference.getName());

		NetworkConfigurationType networkConfigurationType = new NetworkConfigurationType();

		String newvAppNetwork = network.getvAppNetName1();

		// Verify Existing Organization Network 
		if (vdc.getAvailableNetworkRefs().size() == 0) 
			System.exit(0);

		// retain network information across deployment 
		networkConfigurationType.setRetainNetInfoAcrossDeployments(true);
		networkConfigurationType.setBackwardCompatibilityMode(true);

		// configure IP address for the vApp Network  

		IpScopeType ipScope = new IpScopeType();
		ipScope.setGateway(network.getgateway1());
		ipScope.setNetmask(network.getNetmask1());
		ipScope.setDns1(network.getDNS());
		ipScope.setIsEnabled(true);
		ipScope.setIsInherited(false);   //take over from parent configuration

		IpRangesType ipRangesType = new IpRangesType();
		IpRangeType  ipRangeType  = new IpRangeType();

		ipRangeType.setStartAddress(network.getStartaddress());    // if /24 from 2 to 253
		ipRangeType.setEndAddress  (network.getEndaddress(IP_STATIC_RAGE));
		ipRangesType.getIpRange().add(ipRangeType); //adding specific range
		ipScope.setIpRanges(ipRangesType);
		ipScope.setIsEnabled(true);
		ipScope.setIsInherited(false); 


		IpScopesType ipScopes = new IpScopesType();
		ipScopes.getIpScope().add(ipScope);

		networkConfigurationType.setIpScopes(ipScopes);  // setting all configurations of vApp Network 


		networkConfigurationType.setFenceMode(FenceModeValuesType.NATROUTED.value());                // We choose a fenced Mode (Nat: translation address+ Firewall rules ..)
		networkConfigurationType.setParentNetwork(vdc.getAvailableNetworkRefs().iterator().next());  // connecting this private Network to available vOrg Network

		//ADDING FIREWALL RULES + NAT RULES

		NetworkFeaturesType features = new NetworkFeaturesType();  // features is used to 
		List<JAXBElement<? extends NetworkServiceType>> networkService = features.getNetworkService();


		// Setup NAT Port-forwarding
		NatServiceType natServiceType = new NatServiceType();
		natServiceType.setIsEnabled(true);

		//natServiceType.setNatType(NatTypeType.PORTFORWARDING.value());   // using port forwarding service
		natServiceType.setPolicy(NatPolicyType.ALLOWTRAFFIC.value());   
		// addNatRule(natServiceType.getNatRule(), "SSH", "TCP", 22, 22);
		//addNatRule(natServiceType.getNatRule(), "RDP", "TCP", 3389, 3389);

		natServiceType.setNatType(NatTypeType.IPTRANSLATION.value());      //setting Nat to IP translation Mode

		JAXBElement<NetworkServiceType> networkServiceType = new ObjectFactory().createNetworkService(natServiceType);
		networkService.add(networkServiceType);


		//Setup DHCP : // DHCP is disable on ALhambra platform
		/*  DhcpServiceType dhcpServiceType= new DhcpServiceType();
        dhcpServiceType.setIsEnabled(true);
        IpRangeType IPrangeType1=new IpRangeType();

        IPrangeType1.setStartAddress("10.0.0.100");
        IPrangeType1.setEndAddress("10.0.0.199");


        dhcpServiceType.setIpRange(IPrangeType1);;


        JAXBElement<DhcpServiceType> networkServiceTypeDHCP = new ObjectFactory().createDhcpService(dhcpServiceType);
        networkService.add(networkServiceTypeDHCP);
		 */


		//If we work on VDC's PROLOGUE platform ("internal network provided doesn't provide access to 109 platform")
		if (vdc.getReference().getName().equals("vDC_prologue"))
		{
			StaticRoutingServiceType    staticroutingSerType=new StaticRoutingServiceType();
			staticroutingSerType.setIsEnabled(true);
			List<StaticRouteType> at = staticroutingSerType.getStaticRoute();
			StaticRouteType SRT=new StaticRouteType();
			SRT.setName("ADD DEPOT REPOSITORY");
			//SRT.setGatewayInterface(value);
			SRT.setNextHopIp("172.17.138.89");
			SRT.setNetwork("109.234.64.71/32");
			at.add(SRT);
			JAXBElement<StaticRoutingServiceType> staticRST = (new ObjectFactory())
					.createStaticRoutingService(staticroutingSerType);
			networkService.add(staticRST);
		}


		////////////////////////////Setup Firewall Rules////////////////////////////////

		FirewallServiceType firewallServiceType = new FirewallServiceType();
		firewallServiceType.setIsEnabled(true);
		firewallServiceType.setDefaultAction(FirewallPolicyType.DROP.value());
		firewallServiceType.setLogDefaultAction(false);

		// addinf firewall rules
		//  for (FirewallRule rule : firewallRules) {
		//    addFirewallRule(firewallServiceType.getFirewallRule(), rule);
		// }
		// adding firewall rules
		List<FirewallRuleType> fwRules = firewallServiceType.getFirewallRule();

		// Protecting Prologue 's Internal Network 
		if (vdc.getReference().getName().equals("vDC_prologue"))
		{
			addFirewallRule(fwRules, "Allow GW access", "ANY", "internal", "172.17.138.89", "Any",FirewallPolicyType.ALLOW.value());
			addFirewallRule(fwRules, "Allow pool IP", "ANY", "internal", "172.17.117.110-172.17.117.140", "Any",FirewallPolicyType.ALLOW.value());
			addFirewallRule(fwRules, "Protect Prologue's Internal Network", "ANY", "internal", "172.17.117.120", "Any",FirewallPolicyType.DROP.value());	    
			addFirewallRule(fwRules, "Protect Prologue's Internal Network", "ANY", "internal", "172.17.0.0/16", "Any",FirewallPolicyType.DROP.value());
			addFirewallRule(fwRules, "Protect Prologue's Internal Network", "ANY", "internal", "172.31.0.0/16", "Any",FirewallPolicyType.DROP.value());
			addFirewallRule(fwRules, "Protect Prologue's Internal Network", "ANY", "internal", "192.168.43.0/24", "Any",FirewallPolicyType.DROP.value());
			addFirewallRule(fwRules, "Protect Prologue's Internal Network", "ANY", "internal", "192.168.40.0/24", "Any",FirewallPolicyType.DROP.value());
		}

		Iterator<FirewallRule> aux = firewallRulles.iterator();
		while (aux.hasNext())
		{
			FirewallRule rule = aux.next();
			if(rule.getFromport() ==rule.getToport() )
			{
				System.out.println("rule.getProtocol()"+rule.getProtocol());
				addFirewallRule(fwRules,rule.getName().toUpperCase(),rule.getProtocol().toUpperCase(),
						"external", "internal",rule.getFromport(),FirewallPolicyType.ALLOW.value());
			}
			else
			{int i=0;
			while ((Integer.decode(rule.getFromport())+i) <= Integer.decode(rule.getToport()))
			{
				addFirewallRule(fwRules,rule.getName().toUpperCase(),rule.getProtocol().toUpperCase(),
						"external", "internal", Integer.toString(Integer.decode(rule.getFromport())+i),FirewallPolicyType.ALLOW.value());
				i++;
			}

			}
		}

		//addFirewallRule(fwRules, "DNS", "ANY", "internal", "external", "53");
		addFirewallRule(fwRules, "In-Out", "ANY", "internal", "external", "Any",FirewallPolicyType.ALLOW.value());
		//name,protocol,srcIp,dstIp,portRange)





		JAXBElement<FirewallServiceType> firewall = (new ObjectFactory())
				.createFirewallService(firewallServiceType);
		networkService.add(firewall);
		networkConfigurationType.setFeatures(features);                                                // setting feature to Network configurationType

		VAppNetworkConfigurationType vAppNetworkConfigurationType = new VAppNetworkConfigurationType();   //creating a vApp Network Configuration Type
		vAppNetworkConfigurationType.setConfiguration(networkConfigurationType);                          // setting the Network Configuration Type
		vAppNetworkConfigurationType.setNetworkName(newvAppNetwork);                                      // Set vApp Network Name




		/*  NetworkConfigSectionType networkConfigSectionType = new NetworkConfigSectionType();
            MsgType networkInfo = new MsgType();
            networkConfigSectionType.setInfo(networkInfo);
            List<VAppNetworkConfigurationType> vAppNetworkConfigs = networkConfigSectionType
                    .getNetworkConfig();
            vAppNetworkConfigs.add(vAppNetworkConfigurationType);



            InstantiationParamsType vappOrvAppTemplateInstantiationParamsType = new InstantiationParamsType();
            List<JAXBElement<? extends SectionType>> vappSections = vappOrvAppTemplateInstantiationParamsType
                    .getSection();
            vappSections.add(new ObjectFactory()
            .createNetworkConfigSection(networkConfigSectionType));*/


		//// previous code //

		//  networkConfigurationType.setParentNetwork(vdc.getAvailableNetworkRefs()
		//      .iterator().next());
		//networkConfigurationType.setFenceMode(FenceModeValuesType.BRIDGED
		//  .value());


		//VAppNetworkConfigurationType vAppNetworkConfigurationType = new VAppNetworkConfigurationType();
		//vAppNetworkConfigurationType.setConfiguration(networkConfigurationType);
		//vAppNetworkConfigurationType.setNetworkName(vdc
		//      .getAvailableNetworkRefs().iterator().next().getName());

		NetworkConfigSectionType networkConfigSectionType = new NetworkConfigSectionType();
		MsgType networkInfo = new MsgType();
		networkConfigSectionType.setInfo(networkInfo);

		List<VAppNetworkConfigurationType> vAppNetworkConfigs = networkConfigSectionType.getNetworkConfig();
		vAppNetworkConfigs.add(vAppNetworkConfigurationType);	

		//
		// fill in remaining InstantititonParams (name, Source)
		//

		InstantiationParamsType instantiationParamsType = new InstantiationParamsType();
		List<JAXBElement<? extends SectionType>> sections = instantiationParamsType.getSection();
		sections.add(new ObjectFactory().createNetworkConfigSection(networkConfigSectionType));

		// create the request body (InstantiateVAppTemplateParams)
		//
		InstantiateVAppTemplateParamsType instVappTemplParamsType = new InstantiateVAppTemplateParamsType();
		instVappTemplParamsType.setName(vApp_name);

		instVappTemplParamsType.setSource(vAppTemplateReference);
		instVappTemplParamsType.setInstantiationParams(instantiationParamsType);
		//
		// make the request, and get an href to the vApp in return
		//
		Vapp vapp = vdc.instantiateVappTemplate(instVappTemplParamsType);
		return vapp;
	}




	/*  -----------------------------------------------------------------   */
	/*       n e w _ v A p p _ F r o m _ T e m p l a t e            		*/
	/*  -----------------------------------------------------------------   */

	private static void addFirewallRule(List<FirewallRuleType> fwRules, String name, String protocol, String srcIp,
			String dstIp, String portRange,String policy) {
		// TODO Auto-generated method stub

		FirewallRuleType firewallRuleType = new FirewallRuleType();
		firewallRuleType.setDescription(name);
		firewallRuleType.setSourceIp(srcIp);
		firewallRuleType.setSourcePort(-1);

		firewallRuleType.setPolicy(policy);



		firewallRuleType.setDestinationIp(dstIp);
		FirewallRuleProtocols protocols = new FirewallRuleProtocols();
		if (protocol.equalsIgnoreCase("ICMP")) {
			protocols.setIcmp(true);
			firewallRuleType.setIcmpSubType("any");
		} else if (protocol.equalsIgnoreCase("TCP")) {
			protocols.setTcp(true);
			firewallRuleType.setDestinationPortRange(portRange);
		} else if (protocol.equalsIgnoreCase("ANY")) {
			protocols.setAny(true);
			firewallRuleType.setDestinationPortRange(portRange);
		}
		firewallRuleType.setProtocols(protocols);
		fwRules.add(firewallRuleType);
	}



	private static void updateNetworkRules(Vapp vapp, vcloud_params param, vApp_Network network) throws VCloudException 
	{
		// TODO Auto-generated Method Stub

		List<VAppNetworkConfigurationType> NetworkConfig = vapp.getNetworkConfigSection().getNetworkConfig();


		for(int i = 0; i < NetworkConfig.size() ; i++ )
		{	
			VAppNetworkConfigurationType vAppNetworkconfigurationtype = NetworkConfig.get(i);

			NetworkConfigurationType networkconfigurationtype = vAppNetworkconfigurationtype.getConfiguration();		
			System.out.println("Network Name   "+vAppNetworkconfigurationtype.getNetworkName());

			if(vAppNetworkconfigurationtype.getNetworkName().equals(network.getvAppNetName1()))
			{

				NetworkFeaturesType templateFeatures = networkconfigurationtype.getFeatures();
				List<JAXBElement<? extends NetworkServiceType>> networkservicetype = templateFeatures.getNetworkService();

				System.out.println("networkservicetype    "+networkservicetype.size());

				// for (JAXBElement<? extends NetworkServiceType> ns : networkservicetype)
				for( i = 0; i < networkservicetype.size() ; i++)
				{
					if(networkservicetype.get(i).getDeclaredType().getName().contains("FirewallServiceType"))
					{

						FirewallServiceType firewallService = (FirewallServiceType) networkservicetype.get(i).getValue();
						for(int j=0;j<firewallService.getFirewallRule().size();j++)
						{	  
							FirewallRuleType rule = firewallService.getFirewallRule().get(j);
							rule.setDestinationIp(param.getpublicadd());
							System.out.println("rule description"+rule.getDescription());
							System.out.println("rule DestinationIp"+rule.getDestinationIp());	 
						}
						networkservicetype.remove(firewallService);
						networkservicetype.add(new ObjectFactory().createFirewallService(firewallService));
						break;

					}
					networkconfigurationtype .setFeatures(templateFeatures);

					List<VAppNetworkConfigurationType> h = vapp.getNetworkConfigSection().getNetworkConfig();

					for ( int k = 0 ;k < 0 ; k++ )

					{ 
						if (h.get(k).getNetworkName().equals(network.getvAppNetName1()))
							h.get(0).setConfiguration(networkconfigurationtype);
					}

					vapp.updateSection(vapp.getNetworkConfigSection());
				}


			}

		}	
	}


	public static Vapp newvAppFromTemplate(ReferenceType vAppTemplateReference,
			Vdc vdc, String vApp_name) throws VCloudException {

		//  System.out.println("Instantiating the new vApp:   " + vAppTemplateReference.getName());

		NetworkConfigurationType networkConfigurationType = new NetworkConfigurationType();

		//if (vdc.getAvailableNetworkRefs().size() == 0) {
		//System.out.println("No Networks in vdc to instantiate the vapp");
		//	System.exit(0);
		//}

		//networkConfigurationType.setParentNetwork(vdc.getAvailableNetworkRefs()
		//		.iterator().next());
		//networkConfigurationType.setFenceMode(FenceModeValuesType.BRIDGED
		//		.value());
		//VAppNetworkConfigurationType vAppNetworkConfigurationType = new VAppNetworkConfigurationType();
		//vAppNetworkConfigurationType.setConfiguration(networkConfigurationType);
		//vAppNetworkConfigurationType.setNetworkName(vdc
		//		.getAvailableNetworkRefs().iterator().next().getName());
		NetworkConfigSectionType networkConfigSectionType = new NetworkConfigSectionType();
		MsgType networkInfo = new MsgType();
		networkConfigSectionType.setInfo(networkInfo);
		List<VAppNetworkConfigurationType> vAppNetworkConfigs = networkConfigSectionType
				.getNetworkConfig();
		//vAppNetworkConfigs.add(vAppNetworkConfigurationType);
		//
		// fill in remaining InstantititonParams (name, Source)
		//
		InstantiationParamsType instantiationParamsType = new InstantiationParamsType();
		List<JAXBElement<? extends SectionType>> sections = instantiationParamsType
				.getSection();
		sections.add(new ObjectFactory()
		.createNetworkConfigSection(networkConfigSectionType));
		//
		// create the request body (InstantiateVAppTemplateParams)
		//
		InstantiateVAppTemplateParamsType instVappTemplParamsType = new InstantiateVAppTemplateParamsType();
		instVappTemplParamsType.setName(vApp_name);
		instVappTemplParamsType.setSource(vAppTemplateReference);
		instVappTemplParamsType.setInstantiationParams(instantiationParamsType);
		//
		// make the request, and get an href to the vApp in return
		//
		Vapp vapp = vdc.instantiateVappTemplate(instVappTemplParamsType);
		return vapp;
	}

	/*  -----------------------------------------------------------------   */
	/*       f i n d _ v A p p _ T e m p l a t e                */
	/*  -----------------------------------------------------------------   */

	public static ReferenceType findvAppTemplate(VcloudClient vcloudClient, ReferenceType vDCRef,
			String vappTemplate)throws VCloudException, FileNotFoundException, InterruptedException
			{ 
		Vdc vdc= Vdc.getVdcByReference(vcloudClient, vDCRef);
		ReferenceType k=null;
		Collection<ReferenceType> t = vdc.getVappTemplateRefsByName(vappTemplate);
		while (t.iterator().hasNext())
		{
			k = t.iterator().next();
			if (k.getName().equals(vappTemplate))
			{
				return k;
			}}
		return k;}




	private static String generate(int length) {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"; 
		StringBuffer pass = new StringBuffer();
		for(int x=0;x<length;x++)   {
			int i = (int)Math.floor(Math.random() * (chars.length() -1));
			pass.append(chars.charAt(i));
		}
		return pass.toString();
	}
	/**
	 * 
	 ************************** UPDATE VM FLAVORS************************
	 * 
	 * 
	 */
	private static void updatevmflavors(int a, float rAM, int c, VM vm) throws VCloudException {
		// TODO Auto-generated method stub
		int bb;
		if  (rAM <= 0.25) 
			bb=0;
		else if (rAM <=0.5)
			bb=1;
		else if (rAM <=1)
			bb=2;
		else
			bb=3;

		int cc;
		if  (c <= 8) 
			cc=0;
		else if (c <=16)
			cc=1;
		else if (c <=32)
			cc=2;
		else if (c<=64)
			cc=3;
		else 
			cc=4;
		int[] tabc= new int[4];
		tabc[0]=1;
		tabc[1]=2;
		tabc[2]=3;
		tabc[3]=4;
		BigInteger[] tab= new BigInteger[5];
		tab[0]=new BigInteger("256");
		tab[1]=new BigInteger("512");
		tab[2]=new BigInteger("1024");
		tab[3]=new BigInteger("2048");

		BigInteger[] disksize =new BigInteger[5];
		disksize[0]=new BigInteger("8192");//8 ghz
		disksize[1]=new BigInteger("16384");//16 ghz
		disksize[2]=new BigInteger("32768");//32 ghz
		disksize[3]=new BigInteger("65536");//16 ghz
		disksize[4]=new BigInteger("131072");//16 ghz

		VirtualCpu vcpu=vm.getCpu();
		vcpu.setNoOfCpus(tabc[a-1]);
		try {
			try {
				vm.updateCpu(vcpu).waitForTask(1000000, 1000);
			} catch (TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		catch (VCloudException e) {
			//System.out.println("    Adding vcpu failed, verify your quota !!!");
			System.exit(0);
		}


		VirtualMemory vRAM=vm.getMemory();
		vRAM.setMemorySize(tab[bb]);
		try {
			try {
				vm.updateMemory(vRAM).waitForTask(1000000, 1000);
			} catch (TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		catch (VCloudException e) {
			//System.out.println("    Adding memory failed, verify your quota !!");
			System.exit(0);
		}

		List<VirtualDisk> disks = vm.getDisks();
		List<VirtualDisk> newDisks = new ArrayList<VirtualDisk>(disks.size());
		String diskname=disks.get(1).getItemResource().getElementName().getValue();

		try {
			for (VirtualDisk disk : disks) {
				if (disk.isHardDisk()) {
					RASDType d = new RASDType();
					d.setElementName(disk.getItemResource().getElementName());
					d.setResourceType(disk.getItemResource().getResourceType());
					d.setInstanceID(disk.getItemResource().getInstanceID());

					for (int i = 0; i < disk.getItemResource().getHostResource().size(); i++) {
						CimString resource = disk.getItemResource().getHostResource().get(i);
						d.getHostResource().add(resource);
						if (disk.getItemResource().getElementName().getValue().equals(diskname)) {
							if (disk.getHardDiskSize().compareTo(disksize[cc]) == 1) {
								throw new VCloudException("Failed to resize disk, shrinking disks is not supported");
							}
							for (QName key : resource.getOtherAttributes().keySet()) {
								if (key.getLocalPart().equals("capacity")) {
									resource.getOtherAttributes().put(key, disksize[cc].toString());
								}
							}
						}
					}
					newDisks.add(new VirtualDisk(d));
				}
			}


		} catch (VCloudException e) {
			//System.out.println("An error occured while resizing disks");
			e.printStackTrace();
			System.exit(1);
		}
		try {
			try {
				vm.updateDisks(newDisks).waitForTask(1000000, 1000);
			} catch (TimeoutException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		catch (VCloudException e) {
			//System.out.println("    Adding disk failed");
			System.exit(0);
		}
	}
	public static Object get_catalogues(Object[] argsvCconfig) throws Exception,IOException, VCloudException {
		// TODO Auto-generated method stub
		vcloud_config config =new vcloud_config(argsvCconfig[0].toString(), argsvCconfig[1].toString(), argsvCconfig[2].toString(), argsvCconfig[3].toString(), argsvCconfig[4].toString());

		VcloudClient vcloudClient = authenticate( config );	
		//for  (Entry<String, ReferenceType> e : example.entrySet()) 
		//{
		//	System.out.println(e.getKey()+"="+e.getValue());
		//}

		HashMap<String, ReferenceType> OrgRefs = vcloudClient.getOrgRefsByName();
		Organization organisation = Organization.getOrganizationByReference(vcloudClient, OrgRefs.get(config.getOrganization()));

		Collection<ReferenceType> catalogueRef = organisation.getCatalogRefs();
		String[] catalogues = new String[catalogueRef.size()];
		Iterator<ReferenceType> catalogueRefit = catalogueRef.iterator();
		for (int i=0;i<catalogueRef.size();i++) 
		{
			catalogues[i]=catalogueRefit.next().getName();
		}
		return catalogues;
	}

	public static String[] get_vDCs(Object[] argsvCconfig) throws Exception,IOException, VCloudException{

		// TODO Auto-generated method stub
		vcloud_config config =new vcloud_config(argsvCconfig[0].toString(), argsvCconfig[1].toString(), argsvCconfig[2].toString(), argsvCconfig[3].toString(), argsvCconfig[4].toString());
		VcloudClient vcloudClient = authenticate( config );	

		HashMap<String, ReferenceType> OrgRefs = vcloudClient.getOrgRefsByName();
		Organization organisation = Organization.getOrganizationByReference(vcloudClient, OrgRefs.get(config.getOrganization()));	


		Collection<ReferenceType> vDCRef = organisation.getVdcRefs();
		String[] vDCs = new String[vDCRef.size()];
		Iterator<ReferenceType> vDCsRefit = vDCRef.iterator();
		for (int i=0;i<vDCRef.size();i++) 
		{
			vDCs[i]=vDCsRefit.next().getName();

		}
		return vDCs;

	}
	public static String[] get_vApptemplatepervDC(Object[] argsvCconfig)throws Exception,IOException, VCloudException 
	{

		// TODO Auto-generated method stub
		vcloud_config config =new vcloud_config(argsvCconfig[0].toString(), argsvCconfig[1].toString(), argsvCconfig[2].toString(), argsvCconfig[3].toString(), argsvCconfig[4].toString());
		String vDCname=argsvCconfig[5].toString();
		VcloudClient vcloudClient = authenticate( config );	
		//for  (Entry<String, ReferenceType> e : example.entrySet()) 
		//{
		//	System.out.println(e.getKey()+"="+e.getValue());
		//}

		List<String> list = new ArrayList<String>();

		HashMap<String, ReferenceType> OrgRefs = vcloudClient.getOrgRefsByName();
		Organization organisation = Organization.getOrganizationByReference(vcloudClient, OrgRefs.get(config.getOrganization()));



		Collection<ReferenceType> vDCRef = organisation.getVdcRefs();
		ReferenceType vDCref = organisation.getVdcRefByName(vDCname);
		Vdc vdc = Vdc.getVdcByReference(vcloudClient, vDCref) ;
		Collection<ReferenceType> vapptemplateRef = vdc.getVappTemplateRefs();
		Iterator<ReferenceType> itvapptemplateRef = vapptemplateRef.iterator();

		for(int j=0;j<vapptemplateRef.size();j++)
		{
			String name=itvapptemplateRef.next().getName();
			//System.out.println(name);
			list.add(name);

		}


		String[] vapptemplates = new String[list.size()];
		vapptemplates = list.toArray(vapptemplates);

		return (vapptemplates);
	}

	public static String[] get_EDGEpervDC(Object[] argsvCconfig) throws Exception,IOException, VCloudException 
	{
		// TODO Auto-generated method stub
		vcloud_config config =new vcloud_config(argsvCconfig[0].toString(), argsvCconfig[1].toString(), argsvCconfig[2].toString(), argsvCconfig[3].toString(), argsvCconfig[4].toString());
		String vDCname=argsvCconfig[5].toString();

		VcloudClient vcloudClient = authenticate( config );	
		//for  (Entry<String, ReferenceType> e : example.entrySet()) 
		//{
		//	System.out.println(e.getKey()+"="+e.getValue());
		//}

		List<String> list = new ArrayList<String>();

		HashMap<String, ReferenceType> OrgRefs = vcloudClient.getOrgRefsByName();
		Organization organisation = Organization.getOrganizationByReference(vcloudClient, OrgRefs.get(config.getOrganization()));


		Collection<ReferenceType> vDCRef = organisation.getVdcRefs();
		ReferenceType vDCref = organisation.getVdcRefByName(vDCname);
		Vdc vdc = Vdc.getVdcByReference(vcloudClient, vDCref);

		ReferenceResult edgeGWref = vdc.getEdgeGatewayRefs();
		ReferencesType a = edgeGWref.getResource();
		List<JAXBElement<ReferenceType>> g = a.getReference();
		Iterator<JAXBElement<ReferenceType>> EdgeGWit = g.iterator();

		while (EdgeGWit.hasNext())
			list.add(EdgeGWit.next().getValue().getName());

		String[] EDGWs = new String[list.size()];
		EDGWs = list.toArray(EDGWs);

		return (EDGWs);

	}

	public static ReferenceType find_EDGE(vcloud_config config,vcloud_param param) throws Exception,IOException, VCloudException 
	{

		VcloudClient vcloudClient = authenticate( config );	
		List<String> list = new ArrayList<String>();

		HashMap<String, ReferenceType> OrgRefs = vcloudClient.getOrgRefsByName();
		Organization organisation = Organization.getOrganizationByReference(vcloudClient, OrgRefs.get(config.getOrganization()));


		Collection<ReferenceType> vDCRef = organisation.getVdcRefs();
		ReferenceType vDCref = organisation.getVdcRefByName(param.getvDC());
		Vdc vdc = Vdc.getVdcByReference(vcloudClient, vDCref);

		ReferenceResult edgeGWref = vdc.getEdgeGatewayRefs();
		ReferencesType a = edgeGWref.getResource();
		List<JAXBElement<ReferenceType>> g = a.getReference();
		Iterator<JAXBElement<ReferenceType>> EdgeGWit = g.iterator();
		ReferenceType Ref=null;
		while (EdgeGWit.hasNext())
		{
			Ref = EdgeGWit.next().getValue();
			list.add(Ref.getName());
			if(Ref.getName().compareTo(param.getEdgeGateway())==0)
				return Ref;	
		}
		return Ref;


	}





	public static String[] get_vDCORGpervDC(Object[] argsvCconfig)  throws Exception,IOException, VCloudException{
		// TODO Auto-generated method stub
		vcloud_config config =new vcloud_config(argsvCconfig[0].toString(), argsvCconfig[1].toString(), argsvCconfig[2].toString(), argsvCconfig[3].toString(), argsvCconfig[4].toString());
		String vDCname=argsvCconfig[5].toString();

		VcloudClient vcloudClient = authenticate( config );	
		//for  (Entry<String, ReferenceType> e : example.entrySet()) 
		//{
		//	System.out.println(e.getKey()+"="+e.getValue());
		//}

		List<String> list = new ArrayList<String>();

		HashMap<String, ReferenceType> OrgRefs = vcloudClient.getOrgRefsByName();
		Organization organisation = Organization.getOrganizationByReference(vcloudClient, OrgRefs.get(config.getOrganization()));


		Collection<ReferenceType> vDCRef = organisation.getVdcRefs();
		ReferenceType vDCref = organisation.getVdcRefByName(vDCname);
		Vdc vdc = Vdc.getVdcByReference(vcloudClient, vDCref);

		Collection<ReferenceType> NetRef = vdc.getAvailableNetworkRefs();

		Iterator<ReferenceType> NetRefit = NetRef.iterator();


		while (NetRefit.hasNext())
		{
			String name=NetRefit.next().getName();
			list.add(name);
			//System.out.println(name);
		}
		String[] vDCorg = new String[list.size()];
		vDCorg = list.toArray(vDCorg);

		return (vDCorg);
	}
	public static String[] get_vAppspervDC(Object[] argsvCconfig) throws Exception,IOException, VCloudException{
		// TODO Auto-generated method stub
		vcloud_config config =new vcloud_config(argsvCconfig[0].toString(), argsvCconfig[1].toString(), argsvCconfig[2].toString(), argsvCconfig[3].toString(), argsvCconfig[4].toString());
		String vDCname=argsvCconfig[5].toString();

		VcloudClient vcloudClient = authenticate( config );	
		//for  (Entry<String, ReferenceType> e : example.entrySet()) 
		//{
		//	System.out.println(e.getKey()+"="+e.getValue());
		//}

		List<String> list = new ArrayList<String>();

		HashMap<String, ReferenceType> OrgRefs = vcloudClient.getOrgRefsByName();
		Organization organisation = Organization.getOrganizationByReference(vcloudClient, OrgRefs.get(config.getOrganization()));


		Collection<ReferenceType> vDCRef = organisation.getVdcRefs();
		ReferenceType vDCref = organisation.getVdcRefByName(vDCname);
		Vdc vdc = Vdc.getVdcByReference(vcloudClient, vDCref);

		Collection<ReferenceType> vAppRef = vdc.getVappRefs();

		Iterator<ReferenceType> vAppRefit = vAppRef.iterator();


		while (vAppRefit.hasNext())
		{
			String name=vAppRefit.next().getName();
			list.add(name);
		}
		String[] vApps = new String[list.size()];
		vApps = list.toArray(vApps);

		return (vApps);	}




	public static Object create_server(Object[] args) throws Exception, IOException, VCloudException {
		// TODO Auto-generated method stub			
		vcloud_config config =new vcloud_config(args[0].toString(), args[1].toString(), args[2].toString(), args[3].toString(), args[4].toString());		
		vcloud_param  vcloud= new vcloud_param(args[5].toString(),args[6].toString(),args[7].toString(),args[8].toString(),args[9].toString(),args[10].toString(),args[11].toString(),args[12].toString(),args[13].toString(),
				args[14].toString(),args[15].toString(),args[16].toString(),args[17].toString(),args[18].toString(),args[19].toString(),args[20].toString(),args[21].toString(),args[22].toString(),args[23].toString(),args[24].toString(),args[25].toString());

		// verify if vApp with the same name exists in the specific vDC

		VcloudClient vcloudClient = authenticate( config );
		//get org
		HashMap<String, ReferenceType> OrgRefs = vcloudClient.getOrgRefsByName();
		Organization organisation = Organization.getOrganizationByReference(vcloudClient, OrgRefs.get(config.getOrganization()));
		//Collection<ReferenceType> vDCRef = organisation.getVdcRefs();
		ReferenceType vDCref = organisation.getVdcRefByName(vcloud.getvDC());
		Vdc vdc = Vdc.getVdcByReference(vcloudClient, vDCref);

		if(vdc.getVappRefByName(vcloud.getvAppName()) != null)
		{
			vcloud.setMessage("vApp with the same name exists, please change vApp name");
			System.out.println(vcloud.toString());
			return (vcloud.toString());

		}
		// Find vApp template
		// Finding the vAppTemplate Reference using the vAppTemplate in all vDCs
		Collection<ReferenceType> vDCRefs = organisation.getVdcRefs();
		Iterator<ReferenceType> vDCRefsit = vDCRefs.iterator();
		ReferenceType vAppTemplateRef=NullPointerException;
		while(vDCRefsit.hasNext())
		{
			vAppTemplateRef =findvAppTemplate(vcloudClient,vDCRefsit.next(),vcloud.getvApptemplate());
			if(vAppTemplateRef!=NullPointerException)
				break;
		}

		if(vAppTemplateRef==NullPointerException)
		{
			vcloud.setMessage("vApptemplate not found !");
			System.out.println(vcloud.toString());
			return (vcloud.toString());
		}	


		//verify if vDCorg exists  

		//create a routed vDCORG if not exists
		vApp_Network Net = new vApp_Network(vcloud.getNetlabel());  // setting Network
		//verify if EDGE gateway exists and that has interface capacity available
		ReferenceType EDGERef = find_EDGE(config, vcloud);
		EdgeGateway edgegw=EdgeGateway.getEdgeGatewayByReference(vcloudClient, EDGERef);	


		//orgvdcNettype
		create_or_get_vDCORGNET(vdc,Net,EDGERef);


		// Instantiate vApp from vApptemple and connect to vDC network
		vdc =  ManageIaaS.findVdc(vcloudClient, config.getOrganization(), vcloud.getvDC());

		//instantiate  vApp
		Vapp vapp= newvAppFromTemplatewithNetconfig(vAppTemplateRef,vdc, vcloud,Net);


		//Hardware customization of the VM 
		GuestCustomizationVM(vcloudClient,vcloud,vapp.getReference());		

		//connect VMs to vDC network
		configureVMsIPAddressingMode_staticmode(vcloudClient,vapp.getReference(), vdc,Net);


		//setting up Network configuration

		vcloudClient = authenticate( config);
		vapp=Vapp.getVappByReference(vcloudClient, vapp.getReference());
		List<VM> childVms = vapp.getChildrenVms();
		NetworkConnectionType nic0= childVms.get(0).getNetworkConnections().iterator().next();
		if (nic0.getIpAddress() != null)
		{
			vcloud.setPrivateaddress(nic0.getIpAddress());
			vcloud.setMacaddress(nic0.getMACAddress());
		}
		
		//setting up public IP if needed
		if(vcloud.getAccess().compareTo("public")==0)
		{
			//Pool d'IP publique sous-alloué dans la EDGE GW
			String[] AllPublicIPs=list_PublicIP_on_GW(edgegw);
			
			// get Public IP already in use
			String[] UsedPublicIPs=Get_AllocatedPublicIP_on_GW(edgegw);
			
			//Select a public IP
			Object[] pubAddress=SelectPubAddress(AllPublicIPs,UsedPublicIPs);
			if(pubAddress.length==0)
				{vcloud.setMessage("no available Free PubLic IP on the EDGE Gateway");
			    return (vcloud.toString());}

			vcloud.setPublicaddress((String) pubAddress[ 0 + (int)(Math.random()*pubAddress.length)]);

		
        

		//configure DHCP
		DeactivateDHCP(edgegw, vdc, vcloud, Net, vcloudClient);

		//configure NAT
		Add_NAT_Rules(edgegw, vdc, vcloud, Net, vcloudClient);	

		//Configure Firewall
		Add_Firewall_Rules(edgegw,vcloud); 
		}
		else if (vcloud.getAccess().compareTo("private")==0) {
			//configure DHCP
			DeactivateDHCP(edgegw, vdc, vcloud, Net, vcloudClient);
			//no NAT Firewall Rule  is configured
			// TODO s'assurer que le FIREWALL est désactivé par défaut
		}
		else {
			vcloud.setMessage("no specific access is defined , please verify access");
			return(vcloud.toString());
		}

		//Powering VM	
		vapp.deploy(false, 1000000, false).waitForTask(1000000, 10000);
		vapp.getChildrenVms().get(0).deploy(true, 1000000, true).waitForTask(1000000, 10000);

		//refreash vApp
		vapp=Vapp.getVappByReference(vcloudClient, vapp.getReference());
		vcloud.setvAppstatus(vapp.getChildrenVms().iterator().next().getVMStatus().toString());
		vcloud.setvAppId(vapp.getReference().getId());

		
		vcloud.setMessage("Deployment succeeded");
		return (vcloud.toString());				

	}


	private static Object[] SelectPubAddress(String[] allPublicIPs,
			String[] usedPublicIPs) {
				
		// TODO Auto-generated method stub
		Set<String> listALL = new HashSet<String>(Arrays.asList(allPublicIPs));
		Set<String> SetusedUsed = new HashSet<String>(Arrays.asList(usedPublicIPs));
		listALL.removeAll(SetusedUsed);
		Object[] result =listALL.toArray();	
		return result;
	}




	private static String[] Get_AllocatedPublicIP_on_GW(EdgeGateway edgegw) {
		// TODO Auto-generated method stub
		LinkedList<String> UsedAddress = new LinkedList<>();

		GatewayFeaturesType gatewayFeatures = edgegw.getResource().getConfiguration().getEdgeGatewayServiceConfiguration();
		NatServiceType NatSer=null;
		JAXBElement<? extends NetworkServiceType> NetserviceType=null;
		Iterator<JAXBElement<? extends NetworkServiceType>> GWNEtserit = gatewayFeatures.getNetworkService().iterator();
		while (GWNEtserit.hasNext())
		{		
			NetserviceType = GWNEtserit.next();
			switch (NetserviceType.getDeclaredType().getSimpleName()){
			case "NatServiceType":
				NatSer = (NatServiceType) NetserviceType.getValue();
				break;	
			}}

		for(int h=0;h<NatSer.getNatRule().size();h++){
			if(NatSer.getNatRule().get(h).getRuleType().compareTo("DNAT")==0)
				{System.out.println("UsedIP"+NatSer.getNatRule().get(h).getGatewayNatRule().getOriginalIp());
				UsedAddress.add(NatSer.getNatRule().get(h).getGatewayNatRule().getOriginalIp());}
		}

		String[] UsedAdresstab = new String[UsedAddress.size()];
		UsedAdresstab = UsedAddress.toArray(UsedAdresstab);				
		return UsedAdresstab;
	}




	private static void create_or_get_vDCORGNET(Vdc vdc, vApp_Network net, ReferenceType eDGERef) throws VCloudException, MissingPropertyException, TimeoutException {
		// TODO Auto-generated method stub

		@SuppressWarnings("deprecation")
		ReferenceType networkref = vdc.getAvailableNetworkRefByName(net.getvAppNetName1());
		if(networkref==NullPointerException)
		{
			OrgVdcNetworkType OrgvdcNettype=new OrgVdcNetworkType();
			OrgvdcNettype.setName(net.getvAppNetName1());
			OrgvdcNettype.setDescription("Net vDC network for UICB");			
			NetworkConfigurationType NetConfigType=new NetworkConfigurationType();				
			NetConfigType.setRetainNetInfoAcrossDeployments(true);
			NetConfigType.setBackwardCompatibilityMode(true);		
			IpScopeType ipScope = new IpScopeType();
			ipScope.setGateway(net.getgateway1());
			ipScope.setNetmask(net.getNetmask1());
			ipScope.setDns1(net.getDNS());
			ipScope.setIsEnabled(true);
			ipScope.setIsInherited(false);
			IpRangesType ipRangesType = new IpRangesType();
			IpRangeType  ipRangeType  = new IpRangeType();
			ipRangeType.setStartAddress(net.getStartaddress());     
			ipRangeType.setEndAddress  (net.getEndaddress(IP_STATIC_RAGE));
			ipRangesType.getIpRange().add(ipRangeType);  
			ipScope.setIpRanges(ipRangesType);
			ipScope.setIsEnabled(true);
			ipScope.setIsInherited(false); 
			IpScopesType ipScopes = new IpScopesType();
			ipScopes.getIpScope().add(ipScope);
			NetConfigType.setIpScopes(ipScopes);   
			NetConfigType.setFenceMode(FenceModeValuesType.NATROUTED.value());			
			OrgvdcNettype.setConfiguration(NetConfigType);
			OrgvdcNettype.setEdgeGateway(eDGERef);
			OrgVdcNetwork orgvdcNet= vdc.createOrgVdcNetwork(OrgvdcNettype);	        
			List<Task> tasks = orgvdcNet.getTasks();
			if (tasks.size() > 0) 
			{
				tasks.get(0).waitForTask(100000000,10000);
			}}}

	private static void GuestCustomizationVM(VcloudClient vcloudClient2,vcloud_param vcloud, ReferenceType vappRef) throws VCloudException, TimeoutException  {
		// TODO Auto-generated method stub
		if(vcloud.getRootpass().equals(""))
			vcloud.setRootpass(generate(PASSWORD_LENGTH));			
		String script="#/bin/sh \n"
				+ "wget http://109.234.64.71/accords-repository/Linux/install-cosacs-v1.sh \n"
				+ "sh install-cosacs-v1.sh \n"
				+ "exit 0";

		Vapp vapp = Vapp.getVappByReference(vcloudClient2, vappRef);
		VM vm1=VM.getVMByReference(vcloudClient2, vapp.getChildrenVms().get(0).getReference());

		GuestCustomizationSectionType guestCustomizationSection =
				vm1.getGuestCustomizationSection();
		guestCustomizationSection.setEnabled(true);
		guestCustomizationSection.setAdminPasswordEnabled(true);
		guestCustomizationSection.setAdminPasswordAuto(false);
		guestCustomizationSection.setAdminPassword(vcloud.getRootpass());
		guestCustomizationSection.setComputerName(vcloud.getHostname());
		guestCustomizationSection.setAdminAutoLogonEnabled(true);
		guestCustomizationSection.setCustomizationScript(script);
		vm1.updateSection(guestCustomizationSection).waitForTask(1000000, 1000);
		//Update VCPU 
		VirtualCpu vcpu=vm1.getCpu();
		vcpu.setNoOfCpus(Integer.parseInt(vcloud.getCpu()));			
		try {
			try {
				vm1.updateCpu(vcpu).waitForTask(1000000, 1000);
			} catch (TimeoutException e) {
				e.printStackTrace();
				vcloud.setMessage("Setting up CPU timed out, please verify quota!");
			}
		}catch (VCloudException e) {
			e.printStackTrace();
			vcloud.setMessage("Setting up CPU failed, please verify quota!");
		}
		//Update RAM
		VirtualMemory vRAM=vm1.getMemory();
		BigInteger MemorySize = new BigInteger(vcloud.getRam());
		vRAM.setMemorySize(MemorySize);
		try {try {
			vm1.updateMemory(vRAM).waitForTask(1000000, 1000);
		} catch (TimeoutException e) {
			e.printStackTrace();
			vcloud.setMessage("Setting up RAM timed out, please verify quota!");
		}
		}catch (VCloudException e) {
			e.printStackTrace();
			vcloud.setMessage("Setting up RAM failed, please verify quota!");
		}			

		//Update Disc
		BigInteger disksize;		
		if  (Integer.parseInt(vcloud.getDisc()) <= 8) 
			disksize=new BigInteger("8192");
		else if (Integer.parseInt(vcloud.getDisc()) <=16)
			disksize=new BigInteger("16384");
		else if (Integer.parseInt(vcloud.getDisc()) <=32)
			disksize=new BigInteger("32768");
		else if (Integer.parseInt(vcloud.getDisc())<=64)
			disksize=new BigInteger("65536");
		else 
			disksize=new BigInteger("131072");			

		List<VirtualDisk> disks = vm1.getDisks();
		List<VirtualDisk> newDisks = new ArrayList<VirtualDisk>(disks.size());
		String diskname=disks.get(1).getItemResource().getElementName().getValue();

		try {
			for (VirtualDisk disk : disks) {
				if (disk.isHardDisk()) {
					RASDType d = new RASDType();
					d.setElementName(disk.getItemResource().getElementName());
					d.setResourceType(disk.getItemResource().getResourceType());
					d.setInstanceID(disk.getItemResource().getInstanceID());

					for (int i = 0; i < disk.getItemResource().getHostResource().size(); i++) {
						CimString resource = disk.getItemResource().getHostResource().get(i);
						d.getHostResource().add(resource);
						if (disk.getItemResource().getElementName().getValue().equals(diskname)) {
							if (disk.getHardDiskSize().compareTo(disksize) == 1) {
								throw new VCloudException("Failed to resize disk, shrinking disks is not supported");
							}
							for (QName key : resource.getOtherAttributes().keySet()) {
								if (key.getLocalPart().equals("capacity")) {
									resource.getOtherAttributes().put(key, disksize.toString());
								}
							}
						}
					}
					newDisks.add(new VirtualDisk(d));
				}
			}


		} catch (VCloudException e) {
			vcloud.setMessage("An error occured while resizing disks");
			e.printStackTrace();
		}

		try {try {
			vm1.updateDisks(newDisks).waitForTask(1000000, 1000);
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			vcloud.setMessage("Resizing disks timed out");}
		} 
		catch (VCloudException e) {
			e.printStackTrace();
			vcloud.setMessage("Resizing disks failed, please verify quota!!!");
		}



	}


	private static String[] list_PublicIP_on_GW(EdgeGateway edgegw) throws UnknownHostException {
		// TODO Auto-generated method stub	
		LinkedList<String> PublicAddress = new LinkedList<>();

		for( GatewayInterfaceType  gatewayInterfacetype : edgegw.getResource().getConfiguration().getGatewayInterfaces().getGatewayInterface())
		{
			if(gatewayInterfacetype.getInterfaceType().compareTo("uplink")==0)
			{	
				
				for(SubnetParticipationType subnetparticipation:gatewayInterfacetype.getSubnetParticipation())						
				{

					if(subnetparticipation.getIpRanges() == null)
						System.out.println("no sub-allocated public IP for this interface");
					else
					{
						for(IpRangeType subnetpartyperange:subnetparticipation.getIpRanges().getIpRange())
						{
							InetAddress startaddress = InetAddress.getByName(subnetpartyperange.getStartAddress());
							InetAddress endaddress = InetAddress.getByName(subnetpartyperange.getEndAddress());
							
							InetAddress cpt = InetAddress.getByName(subnetpartyperange.getStartAddress());
							PublicAddress.add(startaddress.getHostName());				            
							System.out.println(cpt.getHostAddress());

							while(!cpt.equals(endaddress))
							{
								cpt=InetAddresses.increment(cpt);
								PublicAddress.add(cpt.getHostAddress());
								System.out.println(cpt.getHostAddress());
							}
						}
					}	
				}
			}
		}

		String[] PubAddresstab = new String[PublicAddress.size()];
		PubAddresstab = PublicAddress.toArray(PubAddresstab);
		
		/*System.out.println("PubAddresstab[0]= ");
		System.out.println(PubAddresstab[0]);
		System.exit(0);*/
		
		return PubAddresstab;
	}


	private static Vapp newvAppFromTemplatewithNetconfig(ReferenceType vAppTemplateReference,
			Vdc vdc, vcloud_param vcloud, vApp_Network net) throws VCloudException, TimeoutException {

		NetworkConfigurationType networkConfigurationType = new NetworkConfigurationType();	
		networkConfigurationType.setParentNetwork(vdc.getAvailableNetworkRefByName(net.getvAppNetName1()));
		networkConfigurationType.setFenceMode(FenceModeValuesType.BRIDGED.value());
		VAppNetworkConfigurationType vAppNetworkConfigurationType = new VAppNetworkConfigurationType();
		vAppNetworkConfigurationType.setConfiguration(networkConfigurationType);
		vAppNetworkConfigurationType.setNetworkName(vdc.getAvailableNetworkRefByName(net.getvAppNetName1()).getName());
		NetworkConfigSectionType networkConfigSectionType = new NetworkConfigSectionType();
		MsgType networkInfo = new MsgType();
		networkConfigSectionType.setInfo(networkInfo);
		List<VAppNetworkConfigurationType> vAppNetworkConfigs = networkConfigSectionType.getNetworkConfig();



		vAppNetworkConfigs.add(vAppNetworkConfigurationType);
		//
		// fill in remaining InstantiationParams (name, Source)
		//
		InstantiationParamsType instantiationParamsType = new InstantiationParamsType();
		List<JAXBElement<? extends SectionType>> sections = instantiationParamsType
				.getSection();
		sections.add(new ObjectFactory().createNetworkConfigSection(networkConfigSectionType));
		//
		// create the request body (InstantiateVAppTemplateParams)
		//
		InstantiateVAppTemplateParamsType instVappTemplParamsType = new InstantiateVAppTemplateParamsType();
		instVappTemplParamsType.setName(vcloud.getvAppName());
		instVappTemplParamsType.setSource(vAppTemplateReference);
		instVappTemplParamsType.setInstantiationParams(instantiationParamsType);
		//
		// make the request, and get an href to the vApp in return
		//
		Vapp vapp = vdc.instantiateVappTemplate(instVappTemplParamsType);
		//block task
		List<Task> tasksvapp = vapp.getTasks();
		if (tasksvapp.size() > 0) 
		{
			tasksvapp.get(0).waitForTask(100000000,10000);
		}
		return vapp;




	}
	public static String[] get_vDCORGNetworks(Object[] args) throws KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, VCloudException 
	{
		// TODO Auto-generated method stub
		vcloud_config config =new vcloud_config(args[0].toString(), args[1].toString(), args[2].toString(), args[3].toString(), args[4].toString());
		String vDCname=args[5].toString();	
		VcloudClient vcloudClient = authenticate( config );	
		HashMap<String, ReferenceType> OrgRefs = vcloudClient.getOrgRefsByName();
		Organization organisation = Organization.getOrganizationByReference(vcloudClient, OrgRefs.get(config.getOrganization()));
		Collection<ReferenceType> vDCRef = organisation.getVdcRefs();
		ReferenceType vDCref = organisation.getVdcRefByName(vDCname);
		Vdc vdc = Vdc.getVdcByReference(vcloudClient, vDCref);
		Collection<ReferenceType> exnetworks =vdc.getAvailableNetworkRefs();
		String[] aux = new String[exnetworks.size()];
		Iterator<ReferenceType> ExNetworksit = exnetworks.iterator();

		for (int i=0;i<exnetworks.size();i++) 
		{
			String name=ExNetworksit.next().getName();
			aux[i]=name;

		}			
		return aux;	
	}
















	private static void DeactivateDHCP(EdgeGateway edgegw,Vdc vdc, vcloud_param vcloud,vApp_Network net,VcloudClient vcloudClient
			) throws VCloudException, TimeoutException {	
		//Retrieve previous configuration
		GatewayFeaturesType gatewayFeatures = edgegw.getResource().getConfiguration().getEdgeGatewayServiceConfiguration();
		//GatewayFeaturesType gatewayFeatures = new GatewayFeaturesType();
		//ObjectFactory objectFactory = new ObjectFactory();
		//Configure DHCP (deactivateDHCP)
		//// retreive dhcpservice Type
		JAXBElement<? extends NetworkServiceType> dhcp = null;

		Iterator<JAXBElement<? extends NetworkServiceType>> GWNEtserit = gatewayFeatures.getNetworkService().iterator();
		while (GWNEtserit.hasNext())
		{
			JAXBElement<? extends NetworkServiceType> NetserviceType = GWNEtserit.next();
			//NetserviceType.getValue().
			switch (NetserviceType.getDeclaredType().getSimpleName()){
			case "GatewayDhcpServiceType":
				dhcp = NetserviceType;

				break;}
		}
		NetworkServiceType dhcpService = dhcp.getValue();
		//DhcpServiceType dhcpService = new DhcpServiceType();
		dhcpService.setIsEnabled(false);
		//JAXBElement<DhcpServiceType> dhcp = objectFactory.createDhcpService(dhcpService);
		gatewayFeatures.getNetworkService().add(dhcp); 
		edgegw.configureServices(gatewayFeatures).waitForTask(100000000, 10000);
	}



	private static void Add_NAT_Rules(EdgeGateway edgegw,Vdc vdc, vcloud_param vcloud,vApp_Network net,VcloudClient vcloudClient
			) throws VCloudException, TimeoutException, InterruptedException {	

		//Retrieve previous configuration
		GatewayFeaturesType gatewayFeatures = edgegw.getResource().getConfiguration().getEdgeGatewayServiceConfiguration();
		ObjectFactory objectFactory = new ObjectFactory();
		NatServiceType NatSer=null;
		JAXBElement<? extends NetworkServiceType> NetserviceType=null;
		Iterator<JAXBElement<? extends NetworkServiceType>> GWNEtserit = gatewayFeatures.getNetworkService().iterator();
		while (GWNEtserit.hasNext())
		{		
			NetserviceType = GWNEtserit.next();
			switch (NetserviceType.getDeclaredType().getSimpleName()){
			case "NatServiceType":
				NatSer = (NatServiceType) NetserviceType.getValue();
				break;	
			}}
		
		////////////Configure SNAT		
		NatSer.setIsEnabled(true);
		NatSer.setPolicy(NatPolicyType.ALLOWTRAFFIC.value());

		GatewayNatRuleType GWDNAtruletype = new GatewayNatRuleType();
		GWDNAtruletype.setOriginalIp(vcloud.getPrivateaddress());
		GWDNAtruletype.setOriginalPort("any");
		GWDNAtruletype.setProtocol("any");

		//if(vcloud.getAccess().compareTo("private")==0)
		//{
			String ExterneNetadd = edgegw.getResource().getConfiguration().getGatewayInterfaces().getGatewayInterface().iterator().next().getSubnetParticipation().iterator().next().getIpAddress();
			GWDNAtruletype.setTranslatedIp(ExterneNetadd);
		//}
		//else
		//{
			GWDNAtruletype.setTranslatedIp(vcloud.getPublicaddress());
		//}				
		GWDNAtruletype.setTranslatedPort("any");
		//We supoose that we have one public Network with suballocate pool connected to Edgethe used EDGE gateway
		ReferenceType NetRef = null;// = edgegw.getResource().getConfiguration().getGatewayInterfaces().getGatewayInterface().iterator().next().getNetwork();
		Iterator<GatewayInterfaceType> GWinterfaceit = edgegw.getResource().getConfiguration().getGatewayInterfaces().getGatewayInterface().iterator();
		while(GWinterfaceit.hasNext())
		{
			GatewayInterfaceType GWinterface = GWinterfaceit.next();
			if(GWinterface.getInterfaceType().compareTo("uplink")==0)
			{
				NetRef=GWinterface.getNetwork();
				
		//   NetworksType=new Network()
		//		System.out.println("external network informations \n"
	    //			+ "size: "+exNet.getResource().getConfiguration().getIpScope().getAllocatedIpAddresses().getIpAddress().size()+""
	    //				+ "addresse [0]: "+exNet.getResource().getConfiguration().getIpScope().getAllocatedIpAddresses().getIpAddress().get(0));
        //                 System.exit(0); 
				
			}
		}

		System.out.println("External Network="+NetRef.getName());
		System.out.println("public IP="+vcloud.getPublicaddress());
		System.out.println("Private IP IP="+vcloud.getPrivateaddress());

		//Thread.sleep(80000);

		GWDNAtruletype.setInterface(NetRef);			
		NatRuleType Snatrule = new NatRuleType();
		Snatrule.setIsEnabled(true);
		Snatrule.setRuleType(NatPolicyType.ALLOWTRAFFIC.value());
		Snatrule.setRuleType("SNAT");
		Snatrule.setGatewayNatRule(GWDNAtruletype);

		List<NatRuleType> Natrules = NatSer.getNatRule();
		Natrules.add(Snatrule);

		////////////Configure DNAT if we have public access
		if(vcloud.getAccess().compareTo("public")==0)			
		{
			GatewayNatRuleType GWSNAtruletype = new GatewayNatRuleType();
			GWSNAtruletype.setOriginalIp(vcloud.getPublicaddress());
			GWSNAtruletype.setOriginalPort("any");
			GWSNAtruletype.setProtocol("any");
			GWSNAtruletype.setTranslatedIp(vcloud.getPrivateaddress());
			GWSNAtruletype.setTranslatedPort("any");
			//ReferenceType NetRef2 = vdc.getAvailableNetworkRefByName(net.getvAppNetName1());
			GWSNAtruletype.setInterface(NetRef);
			NatRuleType Dnatrule = new NatRuleType();
			Dnatrule.setIsEnabled(true);
			Dnatrule.setRuleType(NatPolicyType.ALLOWTRAFFIC.value());
			Dnatrule.setRuleType("DNAT");
			Dnatrule.setGatewayNatRule(GWSNAtruletype);
			Natrules.add(Dnatrule);
		}

		JAXBElement<NetworkServiceType> serviceType = objectFactory.createNetworkService(NatSer);
		gatewayFeatures.getNetworkService().add(serviceType);
		edgegw.configureServices(gatewayFeatures).waitForTask(100000000, 10000);

	}




	private static void Add_Firewall_Rules(EdgeGateway edgegw, vcloud_param vcloud) throws VCloudException, TimeoutException {	

		//retreive previous configuration
		GatewayFeaturesType gatewayFeatures = edgegw.getResource().getConfiguration().getEdgeGatewayServiceConfiguration();
		ObjectFactory objectFactory = new ObjectFactory();

		FirewallServiceType firewallServiceType=null; 
		Iterator<JAXBElement<? extends NetworkServiceType>> GWNEtserit = gatewayFeatures.getNetworkService().iterator();
		while (GWNEtserit.hasNext())
		{	

			JAXBElement<? extends NetworkServiceType> NetserviceType = GWNEtserit.next();
			//NetserviceType.getValue().
			switch (NetserviceType.getDeclaredType().getSimpleName()){
			case "FirewallServiceType":
				firewallServiceType = (FirewallServiceType) NetserviceType.getValue();
				break;	
			}}

		//Configure Firewall
		//FirewallServiceType firewallServiceType = new FirewallServiceType();
		firewallServiceType.setIsEnabled(true);
		firewallServiceType.setDefaultAction(FirewallPolicyType.DROP.value());
		firewallServiceType.setLogDefaultAction(false);
		List<FirewallRuleType> fwRules = firewallServiceType.getFirewallRule();

		//load firewall rules
		LinkedList<FirewallRule> FirewallRulles =new LinkedList<>();
		FirewallRulles= parseFirewallRulles(vcloud.getFWrules()); // setting firewall rules

		Iterator<FirewallRule> aux = FirewallRulles.iterator();
		while (aux.hasNext())
		{
			FirewallRule rule = aux.next();
			if(rule.getRange().compareTo("0.0.0.0/0")==0)
				rule.setRange("external");

			if(rule.getFromport().compareTo(rule.getToport())==0 )
			{
				addFirewallRule(fwRules,rule.getName().toUpperCase(),rule.getProtocol().toUpperCase(),
						rule.getRange(), vcloud.getPublicaddress(),rule.getFromport(),FirewallPolicyType.ALLOW.value());
			}
			else
			{
				/*int i=0;
				while ((Integer.decode(rule.getFromport())+i) <= Integer.decode(rule.getToport()))
				{
					addFirewallRule(fwRules,rule.getName().toUpperCase(),rule.getProtocol().toUpperCase(),
							rule.getRange(), vcloud.getPublicaddress(), Integer.toString(Integer.decode(rule.getFromport())+i),FirewallPolicyType.ALLOW.value());
					i++;
				}*/
				
				String DestportRange=rule.getFromport().toString()+"-"+rule.getToport().toString();
				addFirewallRule(fwRules,rule.getName().toUpperCase(),rule.getProtocol().toUpperCase(),
						rule.getRange(), vcloud.getPublicaddress(),DestportRange,FirewallPolicyType.ALLOW.value());
			}
		}

		//Add Internal-external access
		addFirewallRule(fwRules, "In-Out", "ANY", vcloud.getPrivateaddress(), "external", "Any",FirewallPolicyType.ALLOW.value());
		JAXBElement<FirewallServiceType> firewall = objectFactory.createFirewallService(firewallServiceType);
		gatewayFeatures.getNetworkService().add(firewall);			
		edgegw.configureServices(gatewayFeatures).waitForTask(100000000, 10000);;			
	}	


}

