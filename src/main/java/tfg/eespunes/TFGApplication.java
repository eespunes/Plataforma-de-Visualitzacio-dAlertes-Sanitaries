package tfg.eespunes;

import jdk.internal.dynalink.support.NameCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TFGApplication {
    public static void main(String[] args) {
        SpringApplication.run(TFGApplication.class, args);
    }
}
