package com.helpdesk.Repository;

import com.helpdesk.Model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchRepo extends JpaRepository<Batch, Long> {
}
