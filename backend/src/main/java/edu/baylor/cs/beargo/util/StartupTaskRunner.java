package edu.baylor.cs.beargo.util;

import edu.baylor.cs.beargo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * This class will be invoked during application startup.
 * Register an admin user during startup.
 */
@Component
public class StartupTaskRunner implements CommandLineRunner {
    @Value("${beargo.admin.email}")
    private String adminEmail;

    @Value("${beargo.admin.password}")
    private String adminPassword;

    @Value("${beargo.admin.name}")
    private String adminName;

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        userService.registerAdmin(adminEmail, adminPassword, adminName);
    }
}
