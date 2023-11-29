package net.javaguides.departmentservice.service.Impl;

import net.javaguides.departmentservice.dto.DepartmentDto;
import net.javaguides.departmentservice.entity.Department;
import net.javaguides.departmentservice.repository.DepartmentRepository;
import net.javaguides.departmentservice.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }
    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {

        //convert department dto to deprtment jpa entity
        Department department = new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription(),
                departmentDto.getDepartmentCode()
        );
       Department savedDepartment= departmentRepository.save(department);

       DepartmentDto savedDepartmentDto =new DepartmentDto(
               savedDepartment.getId(),
               savedDepartment.getDepartmentName(),
               savedDepartment.getDepartmentDescription(),
               savedDepartment.getDepartmentCode()
       );
        return savedDepartmentDto;
    }

    public  DepartmentDto getDepartmentByCode(String departmentCode) throws RuntimeException{
        DepartmentDto returnedDepartmentDto = new DepartmentDto();
        Department department = departmentRepository.findByDepartmentCode(departmentCode);
         returnedDepartmentDto = new DepartmentDto(
                 department.getId(),
                 department.getDepartmentName(),
                 department.getDepartmentDescription(),
                 department.getDepartmentCode()
        );

         return returnedDepartmentDto;

    }
}
