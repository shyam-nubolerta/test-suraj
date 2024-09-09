package org.userinputs;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@SpringBootApplication
@EnableEncryptableProperties
@EnableWebSecurity
@EnableFeignClients(basePackages = "org.userinputs.external")
public class UserInputsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserInputsApplication.class, args);
	}

}
