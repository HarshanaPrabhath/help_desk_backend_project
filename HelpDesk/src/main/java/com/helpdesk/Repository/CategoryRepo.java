package com.helpdesk.Repository;

import com.helpdesk.Model.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Long> {
}
