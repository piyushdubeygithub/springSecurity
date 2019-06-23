package com.prosmv.util;

public  class URLMapping {

	public static final String BASE_URL = "/api/v1";

    //Company
	public static final String REGISTER_COMPANY = BASE_URL + "/register/company";
	public static final String UPDATE_COMPANY = BASE_URL + "/update/company";
	public static final String COMPANY_LIST = BASE_URL + "/company/list";
	public static final String DELETE_COMPANY = BASE_URL + "/delete/company";
	public static final String DEACTIVATE_COMPANY = BASE_URL + "/deactivate/company";
	public static final String ACTIVATE_COMPANY = BASE_URL + "/activate/company";
	//Login
	public static final String USER_LOGIN = BASE_URL + "/user/login";
	public static final String USER_LOGOUT = BASE_URL + "/user/logout";
	// Factory
	public static final String REGISTER_FACTORY = BASE_URL + "/register/factory";
	public static final String UPDATE_FACTORY = BASE_URL + "/update/factory";
	public static final String FACTORY_LIST = BASE_URL + "/factory/list";
	public static final String DEACTIVATE_FACTORY = BASE_URL + "/deactivate/factory";
	public static final String ACTIVATE_FACTORY = BASE_URL + "/activate/factory";
	// USER
	public static final String REGISTER_USER = BASE_URL + "/register/user";
	public static final String UPDATE_USER = BASE_URL + "/update/user";
	public static final String USER_LIST = BASE_URL + "/user/list";
	public static final String DEACTIVATE_USER = BASE_URL + "/deactivate/user";
	public static final String ACTIVATE_USER = BASE_URL + "/activate/user";
	//SubModule Permission
	public static final String SAVE_ROLE_PERMISSIONS = BASE_URL + "/save/rolePermissions";
	public static final String UPDATE_ROLE_PERMISSIONS = BASE_URL + "/update/rolePermissions";
	public static final String DELETE_ROLE_PERMISSIONS = BASE_URL + "/delete/rolePermissions";

	//Customer
	public static final String REGISTER_CUSTOMER = BASE_URL + "/register/customer";
	public static final String UPDATE_CUSTOMER = BASE_URL + "/update/customer";
	public static final String ACTIVATE_CUSTOMER = BASE_URL + "/activate/customer";
	public static final String DEACTIVATE_CUSTOMER = BASE_URL + "/deactivate/customer";
	public static final String ADD_BRAND = BASE_URL + "/add/brand";
	public static final String GET_ALL_BRANDS = BASE_URL + "/get/allBrands";
	public static final String DELETE_BRAND = BASE_URL + "/delete/brand";
    
	//Designation
	public static final String CREATE_DESIGNATION = BASE_URL + "/create/designation";
	public static final String UPDATE_DESIGNATION = BASE_URL + "/update/designation";
	public static final String DELETE_DESIGNATION = BASE_URL + "/delete/designation";
	public static final String GET_ALL_DESIGNATIONS = BASE_URL + "/get/allDesignation";
	
    // Stitch Class
	public static final String CREATE_STITCHCLASS = BASE_URL + "/create/stitchClass";
	public static final String UPDATE_STITCHCLASS = BASE_URL + "/update/stitchClass";
	public static final String GET_ALL_STITCHCLASSES = BASE_URL + "/get/allStitchClasses";
	public static final String DELETE_STITCHCLASS = BASE_URL + "/delete/stitchClass";
    
	//Machine
	public static final String CREATE_MACHINE = BASE_URL + "/create/machine";
	public static final String UPDATE_MACHINE = BASE_URL + "/update/machine";
	public static final String DELETE_MACHINE = BASE_URL + "/delete/machine";
	public static final String GET_ALL_MACHINES = BASE_URL + "/get/allMachines";

}
