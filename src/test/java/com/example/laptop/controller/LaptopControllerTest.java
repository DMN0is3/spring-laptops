package com.example.laptop.controller;

import com.example.laptop.LaptopApplication;
import com.example.laptop.entity.Laptop;
import com.example.laptop.repository.LaptopRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    String json = """
                {
                  "marca": "DELL",
                  "modelo": "Inspiron",
                  "pulgadas": 15,
                  "procesador": "I3",
                  "ram": 8,
                  "hdd": 256,
                  "peso": 1.85,
                  "precio": 499,
                  "disponible": true
              }
                """;
    String json2 = """
                {
                  "marca": "DELL",
                  "modelo": "Inspiron",
                  "pulgadas": 17,
                  "procesador": "I7",
                  "ram": 16,
                  "hdd": 500,
                  "peso": 2.85,
                  "precio": 699,
                  "disponible": true
              }
                """;
    String json3 = """
                {
                  "id": 2,
                  "marca": "DELL",
                  "modelo": "Inspiron",
                  "pulgadas": 17,
                  "procesador": "I7",
                  "ram": 16,
                  "hdd": 500,
                  "peso": 2.85,
                  "precio": 699,
                  "disponible": true
              }
                """;
    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @DisplayName("METODO findAll()")
    @Test
    void findAll() {
        ResponseEntity<Laptop[]> response  = testRestTemplate.getForEntity("/api/laptops", Laptop[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());

        List<Laptop> laptops = Arrays.asList(response.getBody());
        System.out.println(laptops.size());
    }

    @DisplayName("METODO findOneById(1)")
    @Test
    void findOneById() {

        ResponseEntity<Laptop> response  = testRestTemplate.getForEntity("/api/laptops/1", Laptop.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @DisplayName("METODO create()")
    @Test
    void create() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));


        HttpEntity<String> request = new HttpEntity<>(json,headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops", HttpMethod.POST, request, Laptop.class);

        Laptop result = response.getBody();

        assertEquals(1L, result.getId());

    }

    @DisplayName("METODO update()")
    @Test
    void update() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> request = new HttpEntity<>(json,headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops/", HttpMethod.POST, request, Laptop.class);
        HttpEntity<String> request2 = new HttpEntity<>(json3,headers);
        ResponseEntity<Laptop> response2 = testRestTemplate.exchange("/api/laptops/", HttpMethod.PUT, request2, Laptop.class);

        HttpStatus result = response.getStatusCode();
        assertEquals(HttpStatus.OK,result);

        HttpStatus result2 = response2.getStatusCode();
        assertEquals(HttpStatus.OK,result2);

    }

    @DisplayName("METODO delete()")
    @Test
    void delete() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> request = new HttpEntity<>(json,headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops", HttpMethod.POST, request, Laptop.class);

        HttpStatus result = response.getStatusCode();
        assertEquals(HttpStatus.OK,result);

        ResponseEntity<Void> response2 = testRestTemplate.exchange("/api/laptops/1", HttpMethod.DELETE, null, Void.class);

        HttpStatus result2 = response2.getStatusCode();
        assertEquals(HttpStatus.NO_CONTENT,result2);
    }

    @DisplayName("METODO deleteAll()")
    @Test
    void deleteAll() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> request1 = new HttpEntity<>(json,headers);
        ResponseEntity<Laptop> response1 = testRestTemplate.exchange("/api/laptops", HttpMethod.POST, request1, Laptop.class);

        HttpStatus result1 = response1.getStatusCode();
        assertEquals(HttpStatus.OK,result1);

        HttpEntity<String> request2 = new HttpEntity<>(json2,headers);
        ResponseEntity<Laptop> response2 = testRestTemplate.exchange("/api/laptops", HttpMethod.POST, request2, Laptop.class);

        HttpStatus result2 = response2.getStatusCode();
        assertEquals(HttpStatus.OK,result2);

        ResponseEntity<Void> response3 = testRestTemplate.exchange("/api/laptops/", HttpMethod.DELETE, null, Void.class);

        HttpStatus result3 = response3.getStatusCode();
        assertEquals(HttpStatus.NO_CONTENT,result3);
    }
}