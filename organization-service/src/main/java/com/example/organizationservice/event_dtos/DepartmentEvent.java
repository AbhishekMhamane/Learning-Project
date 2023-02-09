package com.example.organizationservice.event_dtos;

import com.example.organizationservice.model.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DepartmentEvent {
    
    Department department;

    boolean isDelete = false;
}
