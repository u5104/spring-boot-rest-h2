package com.example.demo;

import com.example.demo.entities.Customer;
import com.example.demo.repositories.AccountRepository;
import com.example.demo.repositories.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class MyEmailGuiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerRepository mockRepository;

    @MockBean
    private AccountRepository mockAccountRepository;

    private static final ObjectMapper om = new ObjectMapper();

    private static final int customerId = 1;

    @Before
    public void init() {
        Customer customer = new Customer();
        customer.setFirstName("jane");
        customer.setLastName("doe");
        customer.setId(customerId);
        when(mockRepository.findById(customerId)).thenReturn(Optional.of(customer));
    }

    @Test
    public void testCanFindSingleCustomer() throws Exception {
        mockMvc.perform(get("/gui/email/findCustomer/"+customerId))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(customerId)))
                .andExpect(jsonPath("$.firstName", is("jane")))
                .andExpect(jsonPath("$.lastName", is("doe")));

        verify(mockRepository, times(1)).findById(customerId);
    }

    @Test
    public void testCanFindAllCustomers() throws Exception {

        final Customer testCustomer1 = new Customer();
        testCustomer1.setFirstName("customer1FirstName");
        testCustomer1.setLastName("customer1LastName");
        testCustomer1.setId(1);

        final Customer testCustomer2 = new Customer();
        testCustomer2.setFirstName("customer2FirstName");
        testCustomer2.setLastName("customer2LastName");
        testCustomer2.setId(2);

        List<Customer> customersList = Arrays.asList(
                testCustomer1,
                testCustomer2);

        when(mockRepository.findAll()).thenReturn(customersList);

        mockMvc.perform(get("/gui/email/listCustomers"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstName", is("customer1FirstName")))
                .andExpect(jsonPath("$[0].lastName", is("customer1LastName")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].firstName", is("customer2FirstName")))
                .andExpect(jsonPath("$[1].lastName", is("customer2LastName")));

        verify(mockRepository, times(1)).findAll();
    }

    @Test
    public void testReturnsA404() throws Exception {
        mockMvc.perform(get("/gui/email/findCustomer/666")).andExpect(status().isNotFound());
    }

    @Test
    public void testSaveNewCustomer() throws Exception {
        Customer newCustomer = new Customer();
        newCustomer.setId(999);
        newCustomer.setFirstName("john");
        newCustomer.setLastName("smith");

        when(mockRepository.save(any(Customer.class))).thenReturn(newCustomer);

        mockMvc.perform(post("/gui/email/addCustomer")
                .param("first", newCustomer.getFirstName())
                .param("last", newCustomer.getLastName())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(newCustomer.getId())))
                .andExpect(jsonPath("$.firstName", is(newCustomer.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(newCustomer.getLastName())));

        verify(mockRepository, times(1)).save(any(Customer.class));
    }

}
