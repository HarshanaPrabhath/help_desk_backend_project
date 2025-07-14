package com.helpdesk.Repository;

import com.helpdesk.Model.department.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department,Long> {
}
