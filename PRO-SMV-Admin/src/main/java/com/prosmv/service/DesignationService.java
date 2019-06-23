package com.prosmv.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prosmv.domain.Company;
import com.prosmv.domain.Designation;
import com.prosmv.domain.User;
import com.prosmv.dto.DesignationDTO;
import com.prosmv.dto.ResponseDTO;
import com.prosmv.form.DesignationForm;
import com.prosmv.repositories.CompanyRepository;
import com.prosmv.repositories.DesignationRepository;
import com.prosmv.repositories.UserRepository;
import com.prosmv.util.Constant;

@Service
public class DesignationService {

	@Autowired
	CompanyRepository companyRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	DesignationRepository designationRepository;

	public String createDesignation(DesignationForm designationForm) {
		String response = null;
		if (!isExistsDesignation(designationForm)) {
			Designation designation = getDesignation(designationForm);
			designationRepository.save(designation);
			response = "designation created successfully";
		} else {
			response = "this designation already exists";
		}
		return response;
	}

	private Designation getDesignation(DesignationForm designationForm) {
		Designation designation = new Designation();
		designation.setDesignationname(designationForm.getDesignationname());
		designation.setContigencyAllowance(designationForm.getContigencyAllowance());
		designation.setPersonelAllowance(designationForm.getPersonelAllowance());
		Company company = companyRepository.findByname(designationForm.getDesignationname());
		designation.setCompany(company);
		if (designationForm.getLoginUserName() != null) {
			User user = userRepository.findByUsername(designationForm.getLoginUserName());
			designation.setCreatedBy(user);
		}
		return designation;
	}

	private boolean isExistsDesignation(DesignationForm designationForm) {
		boolean isDesignationExists = false;
		Designation designation = designationRepository.findByDesignationname(designationForm.getDesignationname());
		if (designation != null) {
			isDesignationExists = true;
		}
		return isDesignationExists;
	}

	public String updateDesignation( DesignationForm designationForm) {
		String response = null;
		Designation oldDesignation = designationRepository.findByDesignationname(designationForm.getDesignationname());
		if (oldDesignation != null) {
			Designation designation = getDesignation(designationForm);
			designation.setId(oldDesignation.getId());
			response = "designation updated successfully";
		} else {
			response = "designation not found";
		}
		return response;
	}

	public String deleteDesignation(Long designationId) {
		Optional<Designation> designations = designationRepository.findById(designationId);
		if(designations.isPresent()) {
			Designation designation = designations.get();
			designation.setDeleted(true);
			designationRepository.save(designation);
		}
		return "designation is deleted";
	}

	public ResponseDTO getAllDesignations() {
        List<Designation> designationList = designationRepository.findAll();
        List<DesignationDTO> designations = new ArrayList<>();
        for(Designation designation: designationList) {
        	if(!designation.isDeleted()) {
        		DesignationDTO designationDTO = new DesignationDTO(designation);
        		designations.add(designationDTO);
        	}
        }
		return new ResponseDTO(Constant.SUCCESS, "designations list", designations);
	}

}
