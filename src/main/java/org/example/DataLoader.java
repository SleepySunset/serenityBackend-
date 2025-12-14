package org.example;

import org.example.model.Product;
import org.example.model.User;
import org.example.repository.ProductRepository;
import org.example.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.nio.file.Files;

@Component
public class DataLoader {

    @Bean
    CommandLineRunner loadData(ProductRepository productRepo, UserRepository userRepo) {
        return args -> {

            User user1 = new User("admin@serenity.com", "admin123", "Administrador");
            User user2 = new User("cliente@serenity.com", "cliente123", "Cliente Principal");
            User user3 = new User("test@example.com", "test123", "Usuario de Prueba");

            userRepo.save(user1);
            userRepo.save(user2);
            userRepo.save(user3);

            Product p1 = new Product("Brocha angular para cejas", "Brocha precisa para definir y rellenar cejas. Cerdas sintéticas de alta calidad.", 22.900);
            p1.setImage(Files.readAllBytes(new ClassPathResource("static/brochas.jpg").getFile().toPath()));

            Product p2 = new Product("Rímel Volumen Clásico", "Mascara negra intensa con fórmula que da volumen sin grumos.", 34.500);
            p2.setImage(Files.readAllBytes(new ClassPathResource("static/rimel.jpg").getFile().toPath()));

            Product p3 = new Product("Paleta de Sombras Soft Glam", "Tonos neutros y satinados para looks suaves o dramáticos.", 54.900);
            p3.setImage(Files.readAllBytes(new ClassPathResource("static/paleta.jpg").getFile().toPath()));

            Product p4 = new Product("Rubor Velvet Pink", "Rubor rosado en polvo con acabado aterciopelado y larga duración.", 29.900);
            p4.setImage(Files.readAllBytes(new ClassPathResource("static/rubor.jpg").getFile().toPath()));

            Product p5 = new Product("Lip Oil Rose", "Aceite labial hidratante con brillo suave y aroma a rosas.", 24.500);
            p5.setImage(Files.readAllBytes(new ClassPathResource("static/aceite-labial.jpg").getFile().toPath()));

            productRepo.save(p1);
            productRepo.save(p2);
            productRepo.save(p3);
            productRepo.save(p4);
            productRepo.save(p5);
        };
    }
}
