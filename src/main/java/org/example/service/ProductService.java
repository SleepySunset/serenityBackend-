package org.example.service;

import org.example.model.Product;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductService {

    private static final Map<Long, Product> products = new ConcurrentHashMap<>();
    private static final AtomicLong idCounter = new AtomicLong(1L);

    static {
        products.put(1L, new Product(1L, "Brocha angular para cejas", "Brocha precisa para definir y rellenar cejas. Cerdas sintéticas de alta calidad.", 22.900, "brochas.jpg"));
        products.put(2L, new Product(2L, "Rímel Volumen Clásico", "Mascara negra intensa con fórmula que da volumen sin grumos.", 34.500, "rimel.jpg"));
        products.put(3L, new Product(3L, "Paleta de Sombras Soft Glam", "Tonos neutros y satinados para looks suaves o dramáticos.", 54.900, "paleta.jpg"));
        products.put(4L, new Product(4L, "Rubor Velvet Pink", "Rubor rosado en polvo con acabado aterciopelado y larga duración.", 29.900, "rubor.jpg"));
        products.put(5L, new Product(5L, "Lip Oil Rose", "Aceite labial hidratante con brillo suave y aroma a rosas.", 24.500, "aceite-labial.jpg"));
        idCounter.set(6L);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public Optional<Product> getProductById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    public Product createProduct(Product product) {
        Long newId = idCounter.getAndIncrement();
        product.setId(newId);
        products.put(newId, product);
        return product;
    }

    public Optional<Product> updateProduct(Long id, Product details) {
        return Optional.ofNullable(products.computeIfPresent(id, (k, v) -> {
            v.setTitle(details.getTitle());
            v.setDescription(details.getDescription());
            v.setPrice(details.getPrice());
            if (details.getImageFileName() != null) {
                v.setImageFileName(details.getImageFileName());
            }
            return v;
        }));
    }

    public void deleteProduct(Long id) {
        products.remove(id);
    }

    public Optional<Product> updateProductImage(Long id, String imageFileName) {
        return Optional.ofNullable(products.computeIfPresent(id, (k, v) -> {
            v.setImageFileName(imageFileName);
            return v;
        }));
    }
}

