package com.example.backend;

import com.example.backend.enums.RoleEnum;
import com.example.backend.models.PermissionEntity;
import com.example.backend.models.RoleEntity;
import com.example.backend.models.UserEntity;
import com.example.backend.repositories.UserEntityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.List;
import java.util.Set;


@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
	Dotenv dotenv = Dotenv.load();

	@Bean
	CommandLineRunner init(UserEntityRepository userEntityRepository) {
		String superuser = dotenv.get("superuser");
		String superuserPassword = dotenv.get("superuser_password");
		String superuserEmail = dotenv.get("superuser_email");

		return args -> {
			/* CREATE PERMISSIONS */
			PermissionEntity createPermission = PermissionEntity.builder()
					.permission("CREATE")
					.build();

			PermissionEntity readPermission = PermissionEntity.builder()
					.permission("READ")
					.build();

			PermissionEntity updatePermission = PermissionEntity.builder()
					.permission("UPDATE")
					.build();

			PermissionEntity deletePermission = PermissionEntity.builder()
					.permission("DELETE")
					.build();

			/* CREATE ROLES */
			RoleEntity roleAdmin = RoleEntity.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();

			/* CREATE USERS */
			UserEntity superUser = UserEntity.builder()
					.username(superuser)
					.password(superuserPassword)
					.email(superuserEmail)
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleAdmin))
					.build();

			userEntityRepository.saveAll(List.of(superUser));
		};
	}
}
