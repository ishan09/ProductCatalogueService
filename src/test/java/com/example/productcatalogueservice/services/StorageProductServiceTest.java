package com.example.productcatalogueservice.services;

import com.example.productcatalogueservice.models.Product;
import com.example.productcatalogueservice.repo.ProductRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StorageProductServiceTest {

    @Mock
    private ProductRepo productRepo;

    @InjectMocks
    private StorageProductService storageProductService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetProductById_ProductExists() {
        // Arrange
        Long productId = 1L;
        Product mockProduct = new Product();
        mockProduct.setId(productId);
        mockProduct.setName("Test Product");
        mockProduct.setPrice(100.00);
        when(productRepo.findById(productId)).thenReturn(Optional.of(mockProduct));

        // Act
        Product result = storageProductService.getProductById(productId);

        // Assert
        assertNotNull(result);
        assertEquals("Test Product", result.getName());
        assertEquals(100.00, result.getPrice());
        verify(productRepo, times(1)).findById(productId);
    }

    @Test
    void testGetProductById_ProductDoesNotExist() {
        // Arrange
        Long productId = 1L;
        when(productRepo.findById(productId)).thenReturn(Optional.empty());

        // Act
        Product result = storageProductService.getProductById(productId);

        // Assert
        assertNull(result);
        verify(productRepo, times(1)).findById(productId);
    }

    @Test
    void testGetAllProducts() {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Product 1");
        product1.setPrice(50.00);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Product 2");
        product2.setPrice(75.00);

        // Arrange
        List<Product> mockProducts = Arrays.asList(product1,product2);
        when(productRepo.findAll()).thenReturn(mockProducts);

        // Act
        List<Product> result = storageProductService.getAllProducts();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Product 1", result.get(0).getName());
        verify(productRepo, times(1)).findAll();
    }

    @Test
    void testCreateProduct_NewProduct() {
        // Arrange
        Product newProduct = new Product();
        newProduct.setId(1L);
        newProduct.setName("New Product");
        newProduct.setPrice(120.00);
        when(productRepo.findById(1L)).thenReturn(Optional.empty());
        when(productRepo.save(newProduct)).thenReturn(newProduct);

        // Act
        Product result = storageProductService.createProduct(newProduct);

        // Assert
        assertNotNull(result);
        assertEquals("New Product", result.getName());
        assertEquals(120.00, result.getPrice());
        verify(productRepo, times(1)).save(newProduct);
    }

    @Test
    void testCreateProduct_ProductAlreadyExists() {
        // Arrange
        Product existingProduct = new Product();
        existingProduct.setId(1L);
        existingProduct.setName("Existing Product");
        existingProduct.setPrice(90.00);
        when(productRepo.findById(1L)).thenReturn(Optional.of(existingProduct));

        // Act
        Product result = storageProductService.createProduct(existingProduct);

        // Assert
        assertNotNull(result);
        assertEquals("Existing Product", result.getName());
        assertEquals(90.00, result.getPrice());
        verify(productRepo, never()).save(existingProduct);
    }

    @Test
    void testDeleteProduct_ProductExists() {
        // Arrange
        Long productId = 1L;
        Product existingProduct = new Product();
        existingProduct.setId(productId);
        existingProduct.setName("Existing Product");
        existingProduct.setPrice(90.00);
        when(productRepo.findById(productId)).thenReturn(Optional.of(existingProduct));
        doNothing().when(productRepo).deleteById(productId);

        // Act
        boolean result = storageProductService.deleteProduct(productId);

        // Assert
        assertTrue(result);
        verify(productRepo, times(1)).deleteById(productId);
    }

    @Test
    void testDeleteProduct_ProductDoesNotExist() {
        // Arrange
        Long productId = 1L;
        when(productRepo.findById(productId)).thenReturn(Optional.empty());

        // Act
        boolean result = storageProductService.deleteProduct(productId);

        // Assert
        assertFalse(result);
        verify(productRepo, never()).deleteById(productId);
    }

    @Test
    void testReplaceProduct() {
        // Arrange
        Product updatedProduct = new Product();
        updatedProduct.setId(1L);
        updatedProduct.setName("Updated Product");
        updatedProduct.setPrice(150.00);
        when(productRepo.save(updatedProduct)).thenReturn(updatedProduct);

        // Act
        Product result = storageProductService.replaceProduct(updatedProduct, 1L);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Product", result.getName());
        assertEquals(150.00, result.getPrice());
        verify(productRepo, times(1)).save(updatedProduct);
    }
}