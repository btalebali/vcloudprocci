/*
 * *******************************************************
 * Copyright VMware, Inc. 2010-2013.  All Rights Reserved.
 * *******************************************************
 *
 * DISCLAIMER. THIS PROGRAM IS PROVIDED TO YOU "AS IS" WITHOUT
 * WARRANTIES OR CONDITIONS # OF ANY KIND, WHETHER ORAL OR WRITTEN,
 * EXPRESS OR IMPLIED. THE AUTHOR SPECIFICALLY # DISCLAIMS ANY IMPLIED
 * WARRANTIES OR CONDITIONS OF MERCHANTABILITY, SATISFACTORY # QUALITY,
 * NON-INFRINGEMENT AND FITNESS FOR A PARTICULAR PURPOSE.
 */
package examples;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.logging.Level;

import com.vmware.vcloud.api.rest.schema.QueryResultAdminVMRecordType;
import com.vmware.vcloud.sdk.QueryParams;
import com.vmware.vcloud.sdk.RecordResult;
import com.vmware.vcloud.sdk.VCloudException;
import com.vmware.vcloud.sdk.VcloudClient;
import com.vmware.vcloud.sdk.admin.extensions.ExtensionQueryService;
import com.vmware.vcloud.sdk.admin.extensions.VcloudAdminExtension;
import com.vmware.vcloud.sdk.constants.Version;
import com.vmware.vcloud.sdk.constants.query.QueryAdminVMField;
import com.vmware.vcloud.sdk.constants.query.QueryRecordType;

/**
 * 
 * Query Service Sample.
 * 
 * This samples queries all the vm's and its information from the vCloud. This
 * sample can be run only by a system admin.
 * 
 * @author Ecosystem Engineering.
 */

public class QueryAllVms {

	private static VcloudClient vcloudClient;
	private static VcloudAdminExtension adminExtension;
	private static ExtensionQueryService queryService;
	private static int MAX_PAGE_SIZE = 128;
	private static int noOfVms;

	/**
	 * Query all the vms.
	 * 
	 * @throws VCloudException
	 */
	public static void QueryAllVMs() throws VCloudException {

		System.out.println("Query All VM's");
		System.out.println("--------------");

		QueryParams<QueryAdminVMField> vmQueryParams = new QueryParams<QueryAdminVMField>();
		vmQueryParams.setPageSize(MAX_PAGE_SIZE);

		RecordResult<QueryResultAdminVMRecordType> vmRecordResult = queryService
				.queryRecords(QueryRecordType.ADMINVM, vmQueryParams);

		while (vmRecordResult.getRecords().size() > 0) {
			displayVms(vmRecordResult);
			try {
				vmRecordResult = vmRecordResult.getNextPage();
			} catch (VCloudException e) {
				break;
			}
		}
		System.out.println("\nTotal No of VM's - " + noOfVms);

	}

	/**
	 * Display some of the VM's information.
	 * 
	 * @param vmRecordResult
	 */
	public static void displayVms(
			RecordResult<QueryResultAdminVMRecordType> vmRecordResult) {
		for (QueryResultAdminVMRecordType vmRecord : vmRecordResult
				.getRecords()) {
			noOfVms++;
			System.out.println("VMName : " + vmRecord.getName() + " VMMoref : "
					+ vmRecord.getMoref());
		}
	}

	/**
	 * Sample Usage
	 */
	public static void usage() {
		System.out
				.println("java QueryAllVMs vCloudURL user@organization password CertificateKeyStorePath[optional] CertificateKeyStorePassword[optional]");
		System.out
				.println("java QueryAllVMs https://vcloud user@System password");
		System.out
				.println("java QueryAllVMs https://vcloud user@System password certificatekeystorepath certificatekeystorepassword");
		System.exit(0);
	}

	/**
	 * Start
	 * 
	 * @param args
	 * @throws KeyManagementException
	 * @throws UnrecoverableKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 * @throws VCloudException
	 * @throws IOException
	 * @throws CertificateException
	 */
	public static void main(String args[]) throws KeyManagementException,
			UnrecoverableKeyException, NoSuchAlgorithmException,
			KeyStoreException, VCloudException, CertificateException,
			IOException {

		if(args.length < 3)
			usage();

		// Client login
		VcloudClient.setLogLevel(Level.OFF);
		vcloudClient = new VcloudClient(args[0], Version.V5_5);

		// Performing Certificate Validation
		if (args.length == 5) {
			System.setProperty("javax.net.ssl.trustStore", args[3]);
			System.setProperty("javax.net.ssl.trustStorePassword", args[4]);

			vcloudClient.registerScheme("https", 443, CustomSSLSocketFactory
					.getInstance());
		} else if (args.length == 3) {
			System.err
					.println("Ignoring the Certificate Validation using FakeSSLSocketFactory.java - DO NOT DO THIS IN PRODUCTION");
			vcloudClient.registerScheme("https", 443, FakeSSLSocketFactory
					.getInstance());
		} else {
			usage();
		}

		vcloudClient.login(args[1], args[2]);

		// Getting the VcloudAdminExtension
		adminExtension = vcloudClient.getVcloudAdminExtension();

		// Getting the Admin Extension Query Service.
		queryService = adminExtension.getExtensionQueryService();

		// Query All the vms and its information.
		QueryAllVMs();
	}
}
