package com.prosmv.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prosmv.domain.Factory;
import com.prosmv.domain.Machine;
import com.prosmv.domain.StitchClass;
import com.prosmv.dto.MachineDTO;
import com.prosmv.dto.MachineListDTO;
import com.prosmv.form.MachineForm;
import com.prosmv.repositories.FactoryRepository;
import com.prosmv.repositories.MachineRepository;
import com.prosmv.repositories.StitchClassRepository;

@Service
public class MachineService {

	@Autowired
	private MachineRepository machineRepository;
	@Autowired
	private FactoryRepository factoryRepository;
	@Autowired
	private StitchClassRepository stitchClassRepository;

	public String createMachine(@Valid MachineForm machineForm) {
		String response = null;
		if (!isMachineExists(machineForm)) {
			Machine machine = getMachine(machineForm);
            if(machine != null) {
            	machineRepository.save(machine);
            	response = "machine created successfully";
            }else {
            	response = "machine not created";
            }
		}else {
			response = "machine already exists";
		}
		return response;
	}

	private Machine getMachine(@Valid MachineForm machineForm) {
		Machine machine = new Machine();
		machine.setName(machineForm.getMachineName());
		machine.setAllowance(machineForm.getAllowance());
		machine.setMachineColor(machineForm.getMachineColor());
		machine.setMachineType(machineForm.getMachineType());
		machine.setRpm(machineForm.getRpm());
		machine.setActive(true);
		Factory factory = factoryRepository.findByname(machineForm.getFactoryName());
		StitchClass stitchClass = stitchClassRepository.findByNameAndFactory(machineForm.getStitchClassName(), factory);
		if(factory==null || stitchClass==null) {
			return null;
		}
		machine.setStitchClass(stitchClass);
		return machine;
	}

	private boolean isMachineExists(@Valid MachineForm machineForm) {
		boolean machineExists = false;
		Factory factory = factoryRepository.findByname(machineForm.getFactoryName());
		Machine machine = machineRepository.findByNameAndFactory(machineForm.getMachineName(), factory);
		if (machine != null) {
			machineExists = true;
		}
		return machineExists;
	}

	public String updateMachine(@Valid MachineForm machineForm) {
		String response = null;
		Optional<Machine> oldMachines = machineRepository.findById(machineForm.getId());
		if(oldMachines.isPresent()) {
			Machine oldMachine = oldMachines.get();
			Machine machine = getMachine(machineForm);
			machine.setId(oldMachine.getId());
			response = "machine created successfully";
		}else {
			response = "machine not found";
		}
		return response;
	}

	public String deleteMachine(Long id) {
		String response = null;
       Optional<Machine> machines = machineRepository.findById(id);
       if(machines.isPresent()) {
    	   Machine machine = machines.get();
    	   if(machine != null) {
    		   machine.setDeleted(true);
    		   response = "machine deleted successfully";
    	   }else {
    		   response = "machine not found";
    	   }
       }
		return response;
	}

	public MachineListDTO getAllMachines(String factoryName) {
		Factory factory = factoryRepository.findByname(factoryName);
		List<Machine> machines = machineRepository.findByFactory(factory);
		List<MachineDTO> machineList = new ArrayList<>();
		for(Machine machine: machines) {
			if(!machine.isDeleted()) {
				MachineDTO machineDTO = new MachineDTO(machine);
				machineList.add(machineDTO);
			}
		}
		return new MachineListDTO(machineList);
	}

}
