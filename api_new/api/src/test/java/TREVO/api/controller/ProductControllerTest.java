package TREVO.api.controller;

import TREVO.api.catalog.CatalogDTO;
import TREVO.api.image.ImageDTO;
import TREVO.api.product.ProductDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import java.net.HttpURLConnection;
import java.net.URL;
import TREVO.api.image.ImageRepository;
import TREVO.api.catalog.CatalogRepository;
import TREVO.api.product.ProductRepository;
import TREVO.api.catalog.Catalog;
import TREVO.api.image.Image;
import TREVO.api.product.Product;
import java.net.URI;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc //simula uma requisição
class ProductControllerTest {
    @Autowired
    private MockMvc mvc;//testes unitários, simula

    @Autowired
    private JacksonTester<ProductDTO> productDTOJacksonTester ;
    @Autowired
    private JacksonTester<ImageDTO> imageDTOJacksonTester;
    @Autowired
    private JacksonTester<CatalogDTO>  catalogDTOJacksonTester;

    @Autowired
    private ProductRepository repository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private CatalogRepository catalogRepository;

//    @Test
//    @DisplayName("Deve devolver código http 400 quando informacoes estão invalidas")
//    @WithMockUser
//    void cadastrar_cenario1() throws Exception{
//        //URI urlTemplate = null;
//        var response = mvc.perform(post(urlTemplat:"/cadastrar"))
//                .andReturn().getResponse();
//                assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
//    }
//    @Test
//    @DisplayName("Deve devolver código http 200 quando informacoes estão válidas")
//    @WithMockUser
//    void cadastrar_cenario2() throws Exception {
//        //URI urlTemplate = null;
//        var response = mvc.perform(
//                post(urlTemplat:"/cadastrar"));
//                        .contentType(MediaType.APPLICATION_JSON)
//                .content(productDTOJacksonTester, imageDTOJacksonTester, catalogDTOJacksonTester.write(
//                        new ProductDTO(id:1, imgs:1, catalogs:
//        1, name, size, status, description, date_register, culture)
//                                ).getJosn()
//                        )
//                .andReturn().getResponse();
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//
//        var jsonEsperado = repository.save(new Product(dados, imgs, catalogs));
//
//
   }