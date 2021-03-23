package com.example.demo;

import com.example.demo.entities.Account;
import com.example.demo.entities.Customer;
import com.example.demo.repositories.AccountRepository;
import com.example.demo.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    public void findCustomer() throws Exception {
        mockMvc.perform(get("/gui/email/findCustomer/"+customerId))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(customerId)))
                .andExpect(jsonPath("$.firstName", is("jane")))
                .andExpect(jsonPath("$.lastName", is("doe")));

        verify(mockRepository, times(1)).findById(customerId);

    }

}
