package tfg.eespunes;

import jdk.internal.dynalink.support.NameCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TFGApplication implements CommandLineRunner {
    @Autowired
    private PasswordEncoder passwordEncoder;
    public static void main(String[] args) {
        SpringApplication.run(TFGApplication.class, args);
    }
    @Override
    public void run(String... strings) throws Exception {
        System.out.println("Admin: "+passwordEncoder.encode("admin"));
    }
}
