package uz.sushi.component;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.sushi.entity.enums.PermissionEnum;
import uz.sushi.entity.Role;
import uz.sushi.entity.User;
import uz.sushi.repository.RoleRepository;
import uz.sushi.repository.UserRepository;
import uz.sushi.util.Beans;

import java.util.Objects;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlMode;

    @Value("${app.admin.username}")
    private String adminUsername;

    @Value("${app.admin.password}")
    private String adminPassword;

    String SUPER_ROLE_ADMIN = "SUPER_ROLE_ADMIN";
    String ROLE_USER = "ROLE_USER";

    @Override
    public void run(String... args) throws Exception {
        if (Objects.equals(ddlMode, "create")) {

            Role superAdminRole = new Role();
            superAdminRole.setName(SUPER_ROLE_ADMIN);
            superAdminRole.setDescription("Project owner");
            superAdminRole.setPermissions(Set.of(PermissionEnum.values()));
            roleRepository.save(superAdminRole);

            Role roleUser = Beans.userRole;
            roleUser.setName(ROLE_USER);
            roleUser.setDescription("Foydalanuvchi");
            roleUser.setPermissions(Set.of(PermissionEnum.ORDER));
            roleRepository.save(roleUser);

            User admin = new User(
                    adminUsername,
                    passwordEncoder.encode(adminPassword),
                    "Botir");
            admin.setRole(superAdminRole);
            admin.setEnabled(true);

            userRepository.save(admin);
        } else {
            Role superAdminRole = userRepository.findByEmail(adminUsername).getRole();
            superAdminRole.getPermissions().addAll(Set.of(PermissionEnum.values()));
            roleRepository.save(superAdminRole);
        }
        if (Beans.userRole.getId() == null)
            Beans.userRole.setAll(roleRepository.findRoleByName(ROLE_USER));

    }

}
