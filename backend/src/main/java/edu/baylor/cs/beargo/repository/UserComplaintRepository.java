package edu.baylor.cs.beargo.repository;

import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.model.UserComplaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserComplaintRepository extends JpaRepository<UserComplaint, Long> {
    List<UserComplaint> findByComplainedUser(User user);
}
