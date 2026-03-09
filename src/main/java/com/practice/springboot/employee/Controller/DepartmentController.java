package com.practice.springboot.employee.Controller;

import com.practice.springboot.employee.Entity.Department;
import com.practice.springboot.employee.Service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> allDepartments = departmentService.getAllDepartments();
        return new ResponseEntity<>(allDepartments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        Department departmentById = departmentService.getDepartmentById(id);
        return new ResponseEntity<>(departmentById,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        Department departmentCreated = departmentService.createDepartment(department);
        return new ResponseEntity<>(departmentCreated,HttpStatus.CREATED);
    }

    @PutMapping("/departments/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id,
                                       @RequestBody Map<String,Object> updates) throws IllegalAccessException {
        Department departmentUpdated = departmentService.updateDepartment(id, updates);
        return new ResponseEntity<>(departmentUpdated,HttpStatus.OK);
    }

    @DeleteMapping("/departments/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id){
        departmentService.deleteDepartment(id);
        return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
    }
}
