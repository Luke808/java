package com.accenture.masterdata.organization.service;


/**
 * @author haibo.liu
 * @version 1.0
 * @created 05-Nov-2018 2:44:31 PM
 */
public class OrganizationService {

	public OrganizationService(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param params
	 */
	public int addOrganizationHierarchy(OrganizationHierarchyIn params){
		return 0;
	}

	/**
	 * 
	 * @param id
	 */
	public int deleteOrganizationHierarchy(int id){
		return 0;
	}

	/**
	 * 
	 * @param params
	 */
	public int saveOrganizationHierarchy(OrganizationHierarchyIn params){
		return 0;
	}

	/**
	 * 
	 * @param id
	 */
	public OrganizationHierarchyOut selectOrganizationHierarchy(int id){
		return null;
	}

	/**
	 * 
	 * @param params
	 */
	public List<OrganizationHierarchyRes> selectOrganizationHierarchys(OrganizationHierarchyIn params){
		return null;
	}

	/**
	 * 
	 * @param params
	 */
	public int selectOrganizationHierCount(OrganizationHierarchyIn params){
		return 0;
	}

	/**
	 * 
	 * @param params
	 */
	public int addOrganization(OrganizationIn params){
		return 0;
	}

	/**
	 * 
	 * @param params
	 */
	public int saveOrganization(OrganizationIn params){
		return 0;
	}

	/**
	 * 
	 * @param id
	 */
	public int deleteOrganization(int id){
		return 0;
	}

	/**
	 * 
	 * @param params
	 */
	public List<OrganizationRes> selectOrganizations(OrganizationIn params){
		return null;
	}

	/**
	 * 
	 * @param id
	 */
	public OrganizationOut selectOrganization(int id){
		return null;
	}

	/**
	 * 
	 * @param params
	 */
	public int selectOrganizationCount(OrganizationIn params){
		return 0;
	}

	/**
	 * 
	 * @param params
	 */
	private inputInfoCheck(OrganizationIn params){

	}

	/**
	 * 
	 * @param params
	 */
	private duplicationCheck(OrganizationIn params){

	}

	/**
	 * 
	 * @param params
	 */
	private hierInputCheck(OrganizationHierarchyIn params){

	}

	/**
	 * 
	 * @param params
	 */
	private hireDuplicationCheck(OrganizationHierarchyIn params){

	}

	/**
	 * 
	 * @param id
	 */
	public List<HierarchyPropertiesOut> selectHierarchyProperties(int id){
		return null;
	}

}