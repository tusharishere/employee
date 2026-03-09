package com.practice.springboot.employee.Service;

import com.practice.springboot.employee.Entity.Department;
import com.practice.springboot.employee.Repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository repository;

    public List<Department> getAllDepartments() {
        return repository.findAll();
    }

    public Department getDepartmentById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    public Department createDepartment(Department department) {
        department.setCreatedAt(LocalDateTime.now());
        return repository.save(department);
    }

    public Department updateDepartment(Long id, Map<String, Object> updates) throws IllegalAccessException {

        Department department = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        for (Map.Entry<String, Object> entry : updates.entrySet()) {

            String fieldName = entry.getKey();
            Object fieldValue = entry.getValue();

            Field field = ReflectionUtils.findField(Department.class, fieldName);

            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, department, fieldValue);
            }
        }
        department.setUpdatedAt(LocalDateTime.now());
        return repository.save(department);
    }

    public void deleteDepartment(Long id) {
        repository.deleteById(id);
    }
}
