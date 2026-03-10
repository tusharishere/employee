package com.practice.springboot.employee.Controller;

import com.practice.springboot.employee.Entity.Department;
import com.practice.springboot.employee.Payload.DepartmentDTO;
import com.practice.springboot.employee.Service.DepartmentService;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments(
            @RequestParam(required = false) Boolean isActive,
            @RequestParam(required = false) String sortBy) {

        List<DepartmentDTO> allDepartments =
                departmentService.getAllDepartments(isActive, sortBy);

        return new ResponseEntity<>(allDepartments, HttpStatus.OK);
    }

    @GetMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id) {
        DepartmentDTO departmentById = departmentService.getDepartmentById(id);
        return new ResponseEntity<>(departmentById,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@Valid @RequestBody DepartmentDTO departmentDTO) {
        DepartmentDTO departmentCreated = departmentService.createNewDepartment(departmentDTO);
        return new ResponseEntity<>(departmentCreated,HttpStatus.CREATED);
    }

    @PutMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDTO> updateDepartmentById(@Valid @RequestBody DepartmentDTO departmentDTO,@PathVariable Long departmentId ){
        DepartmentDTO departmentDTO1 = departmentService.updateDepartmentById(departmentId, departmentDTO);
        return new ResponseEntity<>(departmentDTO1,HttpStatus.OK);
    }

    @DeleteMapping(path = "/{departmentId}")
    public ResponseEntity<Boolean> deleteDepartmentById(@PathVariable Long departmentId){
        boolean gotDeleted = departmentService.deleteDepartmentById(departmentId);
        return gotDeleted ? ResponseEntity.ok(true) : ResponseEntity.notFound().build();
    }

    @PatchMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDTO> updatePartialDepartmentById(
            @RequestBody Map<String, Object> updates,
            @PathVariable Long departmentId) {

        DepartmentDTO departmentDTO =
                departmentService.updatePartialDepartmentById(departmentId, updates);

        if (departmentDTO == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(departmentDTO);
    }

}
