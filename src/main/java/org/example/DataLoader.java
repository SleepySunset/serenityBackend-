package org.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.nio.file.Files;

@Component
public class DataLoader {

    @Bean
    CommandLineRunner loadData(ProductRepository repo) {
        return args -> {

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


            repo.save(p1);
            repo.save(p2);
            repo.save(p3);
            repo.save(p4);
            repo.save(p5);
        };
    }
}
