package com.tw.microservice.product.service;

import com.tw.microservice.product.entity.Product;
import com.tw.microservice.product.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @Before
    public void setUp() {
        productService = new ProductService(productRepository);
    }

    @Test
    public void should_return_all_product_when_call_getAll() {
        //given
        List<Product> products = new ArrayList<>(3);
        Product product = new Product();
        product.setName("test");
        products.add(product);
        products.add(product);
        products.add(product);
        given(productRepository.findAll()).willReturn(products);
        //when
        List<Product> allProduct = productService.getAll();
        //then
        assertThat(allProduct.size()).isEqualTo(3);
    }

    @Test
    public void should_get_product_when_call_get(){
        //given
        long id = 1L;
        Product product = new Product();
        product.setId(id);
        product.setName("test");
        given(productRepository.findById(id)).willReturn(java.util.Optional.ofNullable(product));
        //when
        Product selectedProduct = productService.get(id);
        //then
        assertThat(selectedProduct.getId()).isEqualTo(id);
        assertThat(selectedProduct.getName()).isEqualTo("test");
    }

    @Test
    public void should_correctly_add_product_when_call_add() {
        //given
        Product product = new Product();
        product.setName("test");
        given(productRepository.save(product)).willReturn(product);
        //when
        Product addedProduct = productService.add(product);
        //then
        assertThat(addedProduct).isEqualTo(product);
    }

    @Test
    public void should_correctly_update_product_when_call_update() {
        //given
        long id = 1L;
        Product productOld = new Product();
        productOld.setName("test1");
        productOld.setId(id);
        Product productNew = new Product();
        productNew.setName("test2");
        productNew.setId(id);

        given(productRepository.findById(id)).willReturn(java.util.Optional.of(productOld));
        given(productRepository.save(productNew)).willReturn(productNew);
        //when
        Product updatedProduct = productService.update(id, productNew);
        //then
        assertThat(updatedProduct.getId()).isEqualTo(1L);
        assertThat(updatedProduct.getName()).isEqualTo("test2");
    }

    @Test
    public void should_delete_product_when_given_product_id(){
        //given
        long id = 1L;
        Product product = new Product();
        product.setName("test1");
        product.setId(id);
        given(productRepository.findById(id)).willReturn(java.util.Optional.ofNullable(product));
        //when
        productService.remove(id);
        //then
        verify(productRepository,times(1)).deleteById(id);
    }

}