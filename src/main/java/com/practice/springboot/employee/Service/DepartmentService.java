package com.practice.springboot.employee.Service;

import com.practice.springboot.employee.Entity.Department;
import com.practice.springboot.employee.Payload.DepartmentDTO;
import com.practice.springboot.employee.Repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository repository;
    private final ModelMapper modelMapper;

    public List<DepartmentDTO> getAllDepartments(Boolean isActive, String sortBy) {

        List<Department> departments;

        // Apply sorting if sortBy is provided
        if (sortBy != null) {
            departments = repository.findAll(Sort.by(sortBy));
        } else {
            departments = repository.findAll();
        }

        // Apply filtering if isActive is provided
        if (isActive != null) {
            departments = departments.stream()
                    .filter(dept -> dept.getIsActive().equals(isActive))
                    .toList();
        }

        return departments.stream()
                .map(dept -> modelMapper.map(dept, DepartmentDTO.class))
                .toList();
    }

//    GET /api/v1/departments
//    GET /api/v1/departments?isActive=true
//    GET /api/v1/departments?sortBy=title
//    GET /api/v1/departments?isActive=true&sortBy=title

    public DepartmentDTO getDepartmentById(Long id) {
        Department department = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        return modelMapper.map(department, DepartmentDTO.class);
    }

    public DepartmentDTO createNewDepartment(DepartmentDTO departDto) {
        Department department = modelMapper.map(departDto, Department.class);
        department.setCreatedAt(LocalDateTime.now());
        Department savedDepartment = repository.save(department);
        return modelMapper.map(savedDepartment, DepartmentDTO.class);
    }

    public DepartmentDTO updateDepartmentById(Long departmentId,DepartmentDTO departmentDTO) {
        isExistsByDepartmentId(departmentId);
        Department departmentEntity = modelMapper.map(departmentDTO, Department.class);
        departmentEntity.setId(departmentId);
        Department savedDepartmentEntity = repository.save(departmentEntity);
        return modelMapper.map(savedDepartmentEntity, DepartmentDTO.class);
    }

    public void isExistsByDepartmentId(Long departmentId) {
        if (!repository.existsById(departmentId)) {
            throw new RuntimeException("Department not found with id: " + departmentId);
        }
    }

    public boolean deleteDepartmentById(Long employeeId) {
        isExistsByDepartmentId(employeeId);
        repository.deleteById(employeeId);
        return true;
    }

    public DepartmentDTO updatePartialDepartmentById(Long departmentId,
                                                     Map<String, Object> updates) {

        isExistsByDepartmentId(departmentId);
        Department departmentEntity = repository.findById(departmentId).get();
        updates.forEach((field, value) -> {

            Field fieldToBeUpdated =
                    ReflectionUtils.findField(Department.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, departmentEntity, value);
        });
        Department savedDepartment = repository.save(departmentEntity);
        return modelMapper.map(savedDepartment, DepartmentDTO.class);
    }
}
