package edu.baylor.cs.beargo.repository;

import edu.baylor.cs.beargo.model.UserComplaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserComplaintRepository extends JpaRepository<UserComplaint, Long> {

}