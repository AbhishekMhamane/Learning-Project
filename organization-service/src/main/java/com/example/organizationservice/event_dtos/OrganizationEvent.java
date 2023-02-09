package com.example.organizationservice.event_dtos;

import com.example.organizationservice.model.Organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrganizationEvent {
    
    Organization organization;
    
    boolean isDelete = false;
}
