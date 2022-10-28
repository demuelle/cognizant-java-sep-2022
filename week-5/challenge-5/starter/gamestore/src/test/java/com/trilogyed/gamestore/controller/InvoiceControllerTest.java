package com.trilogyed.gamestore.controller;

import com.trilogyed.gamestore.service.GameStoreServiceLayer;
import com.trilogyed.gamestore.viewModel.InvoiceViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceController.class)
@AutoConfigureMockMvc(addFilters = false)
public class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // The aim of this unit test is to test the controller and NOT the service layer.
    // Therefore mock the service layer.
    @MockBean
    private GameStoreServiceLayer storeServiceLayer;

    @Autowired
    //used to move between Objects and JSON
    private ObjectMapper mapper;

    @Test
    public void shouldAddPurchase() throws Exception{
        //Object to JSON in String
        String outputJson = null;
        String inputJson=null;

        InvoiceViewModel inInvoice = new InvoiceViewModel();
        inInvoice.setName("Joe Black");
        inInvoice.setStreet("123 Main St");
        inInvoice.setCity("any City");
        inInvoice.setState("NY");
        inInvoice.setZipcode("10016");
        inInvoice.setItemType("T-Shirt");
        inInvoice.setItemId(12);//pretending item exists with this id...
        inInvoice.setUnitPrice(new BigDecimal("12.50"));//pretending item exists with this price...
        inInvoice.setQuantity(2);

        InvoiceViewModel savedInvoice = new InvoiceViewModel();
        savedInvoice.setName("Joe Black");
        savedInvoice.setStreet("123 Main St");
        savedInvoice.setCity("any City");
        savedInvoice.setState("NY");
        savedInvoice.setZipcode("10016");
        savedInvoice.setItemType("T-Shirt");
        savedInvoice.setItemId(12);//pretending item exists with this id...
        savedInvoice.setUnitPrice(new BigDecimal("12.50"));//pretending item exists with this price...
        savedInvoice.setQuantity(2);
        savedInvoice.setSubtotal(inInvoice.getUnitPrice().multiply(new BigDecimal(inInvoice.getQuantity())));
        savedInvoice.setTax(savedInvoice.getSubtotal().multiply(new BigDecimal("0.06")));
        savedInvoice.setProcessingFee(new BigDecimal("10.00"));
        savedInvoice.setTotal(savedInvoice.getSubtotal().add(savedInvoice.getTax()).add(savedInvoice.getProcessingFee()));
        savedInvoice.setId(22);

        inputJson = mapper.writeValueAsString(inInvoice);
        outputJson = mapper.writeValueAsString(savedInvoice);

        //Mock call to service layer...
        when(storeServiceLayer.createInvoice(inInvoice)).thenReturn(savedInvoice);

        //Act & Assert
        this.mockMvc.perform(post("/invoice")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldFindInvoice() throws Exception{

        InvoiceViewModel savedInvoice = new InvoiceViewModel();
        savedInvoice.setName("Joe Black");
        savedInvoice.setStreet("123 Main St");
        savedInvoice.setCity("any City");
        savedInvoice.setState("NY");
        savedInvoice.setZipcode("10016");
        savedInvoice.setItemType("T-Shirt");
        savedInvoice.setItemId(12);//pretending item exists with this id...
        savedInvoice.setUnitPrice(new BigDecimal("12.50"));//pretending item exists with this price...
        savedInvoice.setQuantity(2);
        savedInvoice.setSubtotal(savedInvoice.getUnitPrice().multiply(new BigDecimal(savedInvoice.getQuantity())));
        savedInvoice.setTax(savedInvoice.getSubtotal().multiply(new BigDecimal("0.06")));
        savedInvoice.setProcessingFee(new BigDecimal("10.00"));
        savedInvoice.setTotal(savedInvoice.getSubtotal().add(savedInvoice.getTax()).add(savedInvoice.getProcessingFee()));
        savedInvoice.setId(22);

        String outputJson = mapper.writeValueAsString(savedInvoice);

        //Mock call to service layer...
        when(storeServiceLayer.getInvoice(22)).thenReturn(savedInvoice);

        //Act & Assert
        this.mockMvc.perform(get("/invoice/{id}", 22))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

        //Mock call to service layer...
        when(storeServiceLayer.getInvoice(-1)).thenReturn(null);

        //Act & Assert
        this.mockMvc.perform(get("/invoice/{id}", -1))
                .andDo(print())
                .andExpect(status().isNotFound());

    }

    @Test
    public void shouldFindAllInvoices() throws Exception{
        //Arrange
        InvoiceViewModel savedInvoice1 = new InvoiceViewModel();
        savedInvoice1.setName("Joe Black");
        savedInvoice1.setStreet("123 Main St");
        savedInvoice1.setCity("any City");
        savedInvoice1.setState("NY");
        savedInvoice1.setZipcode("10016");
        savedInvoice1.setItemType("T-Shirt");
        savedInvoice1.setItemId(12);//pretending item exists with this id...
        savedInvoice1.setUnitPrice(new BigDecimal("12.50"));//pretending item exists with this price...
        savedInvoice1.setQuantity(2);
        savedInvoice1.setSubtotal(savedInvoice1.getUnitPrice().multiply(new BigDecimal(savedInvoice1.getQuantity())));
        savedInvoice1.setTax(savedInvoice1.getSubtotal().multiply(new BigDecimal("0.06")));
        savedInvoice1.setProcessingFee(new BigDecimal("10.00"));
        savedInvoice1.setTotal(savedInvoice1.getSubtotal().add(savedInvoice1.getTax()).add(savedInvoice1.getProcessingFee()));
        savedInvoice1.setId(22);

        InvoiceViewModel savedInvoice2 = new InvoiceViewModel();
        savedInvoice2.setName("Rob Bank");
        savedInvoice2.setStreet("888 Main St");
        savedInvoice2.setCity("any town");
        savedInvoice2.setState("NJ");
        savedInvoice2.setZipcode("08234");
        savedInvoice2.setItemType("Console");
        savedInvoice2.setItemId(120);//pretending item exists with this id...
        savedInvoice2.setUnitPrice(new BigDecimal("129.50"));//pretending item exists with this price...
        savedInvoice2.setQuantity(1);
        savedInvoice2.setSubtotal(savedInvoice2.getUnitPrice().multiply(new BigDecimal(savedInvoice2.getQuantity())));
        savedInvoice2.setTax(savedInvoice2.getSubtotal().multiply(new BigDecimal("0.08")));
        savedInvoice2.setProcessingFee(new BigDecimal("10.00"));
        savedInvoice2.setTotal(savedInvoice2.getSubtotal().add(savedInvoice2.getTax()).add(savedInvoice2.getProcessingFee()));
        savedInvoice2.setId(12);

        InvoiceViewModel savedInvoice3 = new InvoiceViewModel();
        savedInvoice3.setName("Sandy Beach");
        savedInvoice3.setStreet("123 Broad St");
        savedInvoice3.setCity("any where");
        savedInvoice3.setState("CA");
        savedInvoice3.setZipcode("90016");
        savedInvoice3.setItemType("Game");
        savedInvoice3.setItemId(19);//pretending item exists with this id...
        savedInvoice3.setUnitPrice(new BigDecimal("12.50"));//pretending item exists with this price...
        savedInvoice3.setQuantity(4);
        savedInvoice3.setSubtotal(savedInvoice3.getUnitPrice().multiply(new BigDecimal(savedInvoice3.getQuantity())));
        savedInvoice3.setTax(savedInvoice3.getSubtotal().multiply(new BigDecimal("0.09")));
        savedInvoice3.setProcessingFee(BigDecimal.ZERO);
        savedInvoice3.setTotal(savedInvoice3.getSubtotal().add(savedInvoice3.getTax()).add(savedInvoice3.getProcessingFee()));
        savedInvoice3.setId(73);

        List<InvoiceViewModel> foundAllInvoices = new ArrayList<>();
        foundAllInvoices.add(savedInvoice1);
        foundAllInvoices.add(savedInvoice2);
        foundAllInvoices.add(savedInvoice3);

        String outputJson = mapper.writeValueAsString(foundAllInvoices);

        //Mock call to service layer...
        when(storeServiceLayer.getAllInvoices()).thenReturn(foundAllInvoices);

        //Act & Assert
        this.mockMvc.perform(get("/invoice"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

        //Mock call to service layer...
        when(storeServiceLayer.getAllInvoices()).thenReturn(null);

        //Act & Assert
        this.mockMvc.perform(get("/invoice"))
                .andDo(print())
                .andExpect(status().isNotFound());

    }

    @Test
    public void shouldFindInvoicesByCustomerName() throws Exception{
        //Arrange
        InvoiceViewModel savedInvoice1 = new InvoiceViewModel();
        savedInvoice1.setName("Sandy Beach");
        savedInvoice1.setStreet("123 Main St");
        savedInvoice1.setCity("any City");
        savedInvoice1.setState("NY");
        savedInvoice1.setZipcode("10016");
        savedInvoice1.setItemType("T-Shirt");
        savedInvoice1.setItemId(12);//pretending item exists with this id...
        savedInvoice1.setUnitPrice(new BigDecimal("12.50"));//pretending item exists with this price...
        savedInvoice1.setQuantity(2);
        savedInvoice1.setSubtotal(savedInvoice1.getUnitPrice().multiply(new BigDecimal(savedInvoice1.getQuantity())));
        savedInvoice1.setTax(savedInvoice1.getSubtotal().multiply(new BigDecimal("0.06")));
        savedInvoice1.setProcessingFee(new BigDecimal("10.00"));
        savedInvoice1.setTotal(savedInvoice1.getSubtotal().add(savedInvoice1.getTax()).add(savedInvoice1.getProcessingFee()));
        savedInvoice1.setId(22);

        InvoiceViewModel savedInvoice2 = new InvoiceViewModel();
        savedInvoice2.setName("Rob Bank");
        savedInvoice2.setStreet("888 Main St");
        savedInvoice2.setCity("any town");
        savedInvoice2.setState("NJ");
        savedInvoice2.setZipcode("08234");
        savedInvoice2.setItemType("Console");
        savedInvoice2.setItemId(120);//pretending item exists with this id...
        savedInvoice2.setUnitPrice(new BigDecimal("129.50"));//pretending item exists with this price...
        savedInvoice2.setQuantity(1);
        savedInvoice2.setSubtotal(savedInvoice2.getUnitPrice().multiply(new BigDecimal(savedInvoice2.getQuantity())));
        savedInvoice2.setTax(savedInvoice2.getSubtotal().multiply(new BigDecimal("0.08")));
        savedInvoice2.setProcessingFee(new BigDecimal("10.00"));
        savedInvoice2.setTotal(savedInvoice2.getSubtotal().add(savedInvoice2.getTax()).add(savedInvoice2.getProcessingFee()));
        savedInvoice2.setId(12);

        InvoiceViewModel savedInvoice3 = new InvoiceViewModel();
        savedInvoice3.setName("Sandy Beach");
        savedInvoice3.setStreet("123 Broad St");
        savedInvoice3.setCity("any where");
        savedInvoice3.setState("CA");
        savedInvoice3.setZipcode("90016");
        savedInvoice3.setItemType("Game");
        savedInvoice3.setItemId(19);//pretending item exists with this id...
        savedInvoice3.setUnitPrice(new BigDecimal("12.50"));//pretending item exists with this price...
        savedInvoice3.setQuantity(4);
        savedInvoice3.setSubtotal(savedInvoice3.getUnitPrice().multiply(new BigDecimal(savedInvoice3.getQuantity())));
        savedInvoice3.setTax(savedInvoice3.getSubtotal().multiply(new BigDecimal("0.09")));
        savedInvoice3.setProcessingFee(BigDecimal.ZERO);
        savedInvoice3.setTotal(savedInvoice3.getSubtotal().add(savedInvoice3.getTax()).add(savedInvoice3.getProcessingFee()));
        savedInvoice3.setId(73);

        List<InvoiceViewModel> foundAllInvoices = new ArrayList<>();
        foundAllInvoices.add(savedInvoice1);
        foundAllInvoices.add(savedInvoice3);

        String outputJson = mapper.writeValueAsString(foundAllInvoices);

        //Mock call to service layer...
        when(storeServiceLayer.getInvoicesByCustomerName("Sandy Beach")).thenReturn(foundAllInvoices);

        //Act & Assert
        this.mockMvc.perform(get("/invoice/cname/{name}","Sandy Beach"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

        //Mock call to service layer...
        when(storeServiceLayer.getInvoicesByCustomerName("no customer")).thenReturn(null);

        //Act & Assert
        this.mockMvc.perform(get("/invoice/cname/{name}","no customer"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldFailCreateUnvoiceWithBadData() throws Exception{
        InvoiceViewModel inInvoiceMV = new InvoiceViewModel();
        inInvoiceMV.setName("");
        inInvoiceMV.setStreet("123 Main St");
        inInvoiceMV.setCity("any City");
        inInvoiceMV.setState("NY");
        inInvoiceMV.setZipcode("10016");
        inInvoiceMV.setItemType("T-Shirt");
        inInvoiceMV.setItemId(12);//pretending item exists with this id...
        inInvoiceMV.setUnitPrice(new BigDecimal("12.50"));//pretending item exists with this price...
        inInvoiceMV.setQuantity(2);
        inInvoiceMV.setSubtotal(inInvoiceMV.getUnitPrice().multiply(new BigDecimal(inInvoiceMV.getQuantity())));
        inInvoiceMV.setTax(inInvoiceMV.getSubtotal().multiply(new BigDecimal("0.06")));
        inInvoiceMV.setProcessingFee(new BigDecimal("10.00"));
        inInvoiceMV.setTotal(inInvoiceMV.getSubtotal().add(inInvoiceMV.getTax()).add(inInvoiceMV.getProcessingFee()));

        when(this.storeServiceLayer.createInvoice(inInvoiceMV)).thenReturn(null);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/invoice")
                        .content(mapper.writeValueAsString(inInvoiceMV)) //converts object to JSON and places into RequestBody
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) //for debugging purposes. Prints the request, handler,... and response objects to the console below.
                .andExpect(status().isUnprocessableEntity()); //Expected response status code.

        inInvoiceMV = new InvoiceViewModel();
        inInvoiceMV.setName(null);
        inInvoiceMV.setStreet("123 Main St");
        inInvoiceMV.setCity("any City");
        inInvoiceMV.setState("NY");
        inInvoiceMV.setZipcode("10016");
        inInvoiceMV.setItemType("T-Shirt");
        inInvoiceMV.setItemId(12);//pretending item exists with this id...
        inInvoiceMV.setUnitPrice(new BigDecimal("12.50"));//pretending item exists with this price...
        inInvoiceMV.setQuantity(2);
        inInvoiceMV.setSubtotal(inInvoiceMV.getUnitPrice().multiply(new BigDecimal(inInvoiceMV.getQuantity())));
        inInvoiceMV.setTax(inInvoiceMV.getSubtotal().multiply(new BigDecimal("0.06")));
        inInvoiceMV.setProcessingFee(new BigDecimal("10.00"));
        inInvoiceMV.setTotal(inInvoiceMV.getSubtotal().add(inInvoiceMV.getTax()).add(inInvoiceMV.getProcessingFee()));

        when(this.storeServiceLayer.createInvoice(inInvoiceMV)).thenReturn(null);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/invoice")
                        .content(mapper.writeValueAsString(inInvoiceMV)) //converts object to JSON and places into RequestBody
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) //for debugging purposes. Prints the request, handler,... and response objects to the console below.
                .andExpect(status().isUnprocessableEntity()); //Expected response status code.

        //Street...
        inInvoiceMV = new InvoiceViewModel();
        inInvoiceMV.setName("Rob Bank");
        inInvoiceMV.setStreet("");
        inInvoiceMV.setCity("any City");
        inInvoiceMV.setState("NY");
        inInvoiceMV.setZipcode("10016");
        inInvoiceMV.setItemType("T-Shirt");
        inInvoiceMV.setItemId(12);//pretending item exists with this id...
        inInvoiceMV.setUnitPrice(new BigDecimal("12.50"));//pretending item exists with this price...
        inInvoiceMV.setQuantity(2);
        inInvoiceMV.setSubtotal(inInvoiceMV.getUnitPrice().multiply(new BigDecimal(inInvoiceMV.getQuantity())));
        inInvoiceMV.setTax(inInvoiceMV.getSubtotal().multiply(new BigDecimal("0.06")));
        inInvoiceMV.setProcessingFee(new BigDecimal("10.00"));
        inInvoiceMV.setTotal(inInvoiceMV.getSubtotal().add(inInvoiceMV.getTax()).add(inInvoiceMV.getProcessingFee()));

        when(this.storeServiceLayer.createInvoice(inInvoiceMV)).thenReturn(null);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/invoice")
                        .content(mapper.writeValueAsString(inInvoiceMV)) //converts object to JSON and places into RequestBody
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) //for debugging purposes. Prints the request, handler,... and response objects to the console below.
                .andExpect(status().isUnprocessableEntity()); //Expected response status code.

        inInvoiceMV = new InvoiceViewModel();
        inInvoiceMV.setName("Rob Bank");
        inInvoiceMV.setStreet(null);
        inInvoiceMV.setCity("any City");
        inInvoiceMV.setState("NY");
        inInvoiceMV.setZipcode("10016");
        inInvoiceMV.setItemType("T-Shirt");
        inInvoiceMV.setItemId(12);//pretending item exists with this id...
        inInvoiceMV.setUnitPrice(new BigDecimal("12.50"));//pretending item exists with this price...
        inInvoiceMV.setQuantity(2);
        inInvoiceMV.setSubtotal(inInvoiceMV.getUnitPrice().multiply(new BigDecimal(inInvoiceMV.getQuantity())));
        inInvoiceMV.setTax(inInvoiceMV.getSubtotal().multiply(new BigDecimal("0.06")));
        inInvoiceMV.setProcessingFee(new BigDecimal("10.00"));
        inInvoiceMV.setTotal(inInvoiceMV.getSubtotal().add(inInvoiceMV.getTax()).add(inInvoiceMV.getProcessingFee()));

        when(this.storeServiceLayer.createInvoice(inInvoiceMV)).thenReturn(null);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/invoice")
                        .content(mapper.writeValueAsString(inInvoiceMV)) //converts object to JSON and places into RequestBody
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) //for debugging purposes. Prints the request, handler,... and response objects to the console below.
                .andExpect(status().isUnprocessableEntity()); //Expected response status code.

        //City...
        inInvoiceMV = new InvoiceViewModel();
        inInvoiceMV.setName("Rob Bank");
        inInvoiceMV.setStreet("123 Main St");
        inInvoiceMV.setCity("");
        inInvoiceMV.setState("NY");
        inInvoiceMV.setZipcode("10016");
        inInvoiceMV.setItemType("T-Shirt");
        inInvoiceMV.setItemId(12);//pretending item exists with this id...
        inInvoiceMV.setUnitPrice(new BigDecimal("12.50"));//pretending item exists with this price...
        inInvoiceMV.setQuantity(2);
        inInvoiceMV.setSubtotal(inInvoiceMV.getUnitPrice().multiply(new BigDecimal(inInvoiceMV.getQuantity())));
        inInvoiceMV.setTax(inInvoiceMV.getSubtotal().multiply(new BigDecimal("0.06")));
        inInvoiceMV.setProcessingFee(new BigDecimal("10.00"));
        inInvoiceMV.setTotal(inInvoiceMV.getSubtotal().add(inInvoiceMV.getTax()).add(inInvoiceMV.getProcessingFee()));

        when(this.storeServiceLayer.createInvoice(inInvoiceMV)).thenReturn(null);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/invoice")
                        .content(mapper.writeValueAsString(inInvoiceMV)) //converts object to JSON and places into RequestBody
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) //for debugging purposes. Prints the request, handler,... and response objects to the console below.
                .andExpect(status().isUnprocessableEntity()); //Expected response status code.

        inInvoiceMV = new InvoiceViewModel();
        inInvoiceMV.setName("Rob Bank");
        inInvoiceMV.setStreet("123 Main St");
        inInvoiceMV.setCity(null);
        inInvoiceMV.setState("NY");
        inInvoiceMV.setZipcode("10016");
        inInvoiceMV.setItemType("T-Shirt");
        inInvoiceMV.setItemId(12);//pretending item exists with this id...
        inInvoiceMV.setUnitPrice(new BigDecimal("12.50"));//pretending item exists with this price...
        inInvoiceMV.setQuantity(2);
        inInvoiceMV.setSubtotal(inInvoiceMV.getUnitPrice().multiply(new BigDecimal(inInvoiceMV.getQuantity())));
        inInvoiceMV.setTax(inInvoiceMV.getSubtotal().multiply(new BigDecimal("0.06")));
        inInvoiceMV.setProcessingFee(new BigDecimal("10.00"));
        inInvoiceMV.setTotal(inInvoiceMV.getSubtotal().add(inInvoiceMV.getTax()).add(inInvoiceMV.getProcessingFee()));

        when(this.storeServiceLayer.createInvoice(inInvoiceMV)).thenReturn(null);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/invoice")
                        .content(mapper.writeValueAsString(inInvoiceMV)) //converts object to JSON and places into RequestBody
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) //for debugging purposes. Prints the request, handler,... and response objects to the console below.
                .andExpect(status().isUnprocessableEntity()); //Expected response status code.

        //state...
        inInvoiceMV = new InvoiceViewModel();
        inInvoiceMV.setName("Rob Bank");
        inInvoiceMV.setStreet("123 Main St");
        inInvoiceMV.setCity("any City");
        inInvoiceMV.setState("");
        inInvoiceMV.setZipcode("10016");
        inInvoiceMV.setItemType("T-Shirt");
        inInvoiceMV.setItemId(12);//pretending item exists with this id...
        inInvoiceMV.setUnitPrice(new BigDecimal("12.50"));//pretending item exists with this price...
        inInvoiceMV.setQuantity(2);
        inInvoiceMV.setSubtotal(inInvoiceMV.getUnitPrice().multiply(new BigDecimal(inInvoiceMV.getQuantity())));
        inInvoiceMV.setTax(inInvoiceMV.getSubtotal().multiply(new BigDecimal("0.06")));
        inInvoiceMV.setProcessingFee(new BigDecimal("10.00"));
        inInvoiceMV.setTotal(inInvoiceMV.getSubtotal().add(inInvoiceMV.getTax()).add(inInvoiceMV.getProcessingFee()));

        when(this.storeServiceLayer.createInvoice(inInvoiceMV)).thenReturn(null);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/invoice")
                        .content(mapper.writeValueAsString(inInvoiceMV)) //converts object to JSON and places into RequestBody
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) //for debugging purposes. Prints the request, handler,... and response objects to the console below.
                .andExpect(status().isUnprocessableEntity()); //Expected response status code.

        inInvoiceMV = new InvoiceViewModel();
        inInvoiceMV.setName("Rob Bank");
        inInvoiceMV.setStreet("123 Main St");
        inInvoiceMV.setCity("any City");
        inInvoiceMV.setState(null);
        inInvoiceMV.setZipcode("10016");
        inInvoiceMV.setItemType("T-Shirt");
        inInvoiceMV.setItemId(12);//pretending item exists with this id...
        inInvoiceMV.setUnitPrice(new BigDecimal("12.50"));//pretending item exists with this price...
        inInvoiceMV.setQuantity(2);
        inInvoiceMV.setSubtotal(inInvoiceMV.getUnitPrice().multiply(new BigDecimal(inInvoiceMV.getQuantity())));
        inInvoiceMV.setTax(inInvoiceMV.getSubtotal().multiply(new BigDecimal("0.06")));
        inInvoiceMV.setProcessingFee(new BigDecimal("10.00"));
        inInvoiceMV.setTotal(inInvoiceMV.getSubtotal().add(inInvoiceMV.getTax()).add(inInvoiceMV.getProcessingFee()));

        when(this.storeServiceLayer.createInvoice(inInvoiceMV)).thenReturn(null);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/invoice")
                        .content(mapper.writeValueAsString(inInvoiceMV)) //converts object to JSON and places into RequestBody
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) //for debugging purposes. Prints the request, handler,... and response objects to the console below.
                .andExpect(status().isUnprocessableEntity()); //Expected response status code.

        //Zip...
        inInvoiceMV = new InvoiceViewModel();
        inInvoiceMV.setName("Rob Bank");
        inInvoiceMV.setStreet("123 Main St");
        inInvoiceMV.setCity("any City");
        inInvoiceMV.setState("NY");
        inInvoiceMV.setZipcode("");
        inInvoiceMV.setItemType("T-Shirt");
        inInvoiceMV.setItemId(12);//pretending item exists with this id...
        inInvoiceMV.setUnitPrice(new BigDecimal("12.50"));//pretending item exists with this price...
        inInvoiceMV.setQuantity(2);
        inInvoiceMV.setSubtotal(inInvoiceMV.getUnitPrice().multiply(new BigDecimal(inInvoiceMV.getQuantity())));
        inInvoiceMV.setTax(inInvoiceMV.getSubtotal().multiply(new BigDecimal("0.06")));
        inInvoiceMV.setProcessingFee(new BigDecimal("10.00"));
        inInvoiceMV.setTotal(inInvoiceMV.getSubtotal().add(inInvoiceMV.getTax()).add(inInvoiceMV.getProcessingFee()));

        when(this.storeServiceLayer.createInvoice(inInvoiceMV)).thenReturn(null);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/invoice")
                        .content(mapper.writeValueAsString(inInvoiceMV)) //converts object to JSON and places into RequestBody
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) //for debugging purposes. Prints the request, handler,... and response objects to the console below.
                .andExpect(status().isUnprocessableEntity()); //Expected response status code.

        inInvoiceMV = new InvoiceViewModel();
        inInvoiceMV.setName("Rob Bank");
        inInvoiceMV.setStreet("123 Main St");
        inInvoiceMV.setCity("any City");
        inInvoiceMV.setState("NY");
        inInvoiceMV.setZipcode(null);
        inInvoiceMV.setItemType("T-Shirt");
        inInvoiceMV.setItemId(12);//pretending item exists with this id...
        inInvoiceMV.setUnitPrice(new BigDecimal("12.50"));//pretending item exists with this price...
        inInvoiceMV.setQuantity(2);
        inInvoiceMV.setSubtotal(inInvoiceMV.getUnitPrice().multiply(new BigDecimal(inInvoiceMV.getQuantity())));
        inInvoiceMV.setTax(inInvoiceMV.getSubtotal().multiply(new BigDecimal("0.06")));
        inInvoiceMV.setProcessingFee(new BigDecimal("10.00"));
        inInvoiceMV.setTotal(inInvoiceMV.getSubtotal().add(inInvoiceMV.getTax()).add(inInvoiceMV.getProcessingFee()));

        when(this.storeServiceLayer.createInvoice(inInvoiceMV)).thenReturn(null);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/invoice")
                        .content(mapper.writeValueAsString(inInvoiceMV)) //converts object to JSON and places into RequestBody
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) //for debugging purposes. Prints the request, handler,... and response objects to the console below.
                .andExpect(status().isUnprocessableEntity()); //Expected response status code.

        //Item type...
        inInvoiceMV = new InvoiceViewModel();
        inInvoiceMV.setName("Rob Bank");
        inInvoiceMV.setStreet("123 Main St");
        inInvoiceMV.setCity("any City");
        inInvoiceMV.setState("NY");
        inInvoiceMV.setZipcode("10016");
        inInvoiceMV.setItemType("");
        inInvoiceMV.setItemId(12);//pretending item exists with this id...
        inInvoiceMV.setUnitPrice(new BigDecimal("12.50"));//pretending item exists with this price...
        inInvoiceMV.setQuantity(2);
        inInvoiceMV.setSubtotal(inInvoiceMV.getUnitPrice().multiply(new BigDecimal(inInvoiceMV.getQuantity())));
        inInvoiceMV.setTax(inInvoiceMV.getSubtotal().multiply(new BigDecimal("0.06")));
        inInvoiceMV.setProcessingFee(new BigDecimal("10.00"));
        inInvoiceMV.setTotal(inInvoiceMV.getSubtotal().add(inInvoiceMV.getTax()).add(inInvoiceMV.getProcessingFee()));

        when(this.storeServiceLayer.createInvoice(inInvoiceMV)).thenReturn(null);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/invoice")
                        .content(mapper.writeValueAsString(inInvoiceMV)) //converts object to JSON and places into RequestBody
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) //for debugging purposes. Prints the request, handler,... and response objects to the console below.
                .andExpect(status().isUnprocessableEntity()); //Expected response status code.

        inInvoiceMV = new InvoiceViewModel();
        inInvoiceMV.setName("Rob Bank");
        inInvoiceMV.setStreet("123 Main St");
        inInvoiceMV.setCity("any City");
        inInvoiceMV.setState("NY");
        inInvoiceMV.setZipcode("10016");
        inInvoiceMV.setItemType(null);
        inInvoiceMV.setItemId(12);//pretending item exists with this id...
        inInvoiceMV.setUnitPrice(new BigDecimal("12.50"));//pretending item exists with this price...
        inInvoiceMV.setQuantity(2);
        inInvoiceMV.setSubtotal(inInvoiceMV.getUnitPrice().multiply(new BigDecimal(inInvoiceMV.getQuantity())));
        inInvoiceMV.setTax(inInvoiceMV.getSubtotal().multiply(new BigDecimal("0.06")));
        inInvoiceMV.setProcessingFee(new BigDecimal("10.00"));
        inInvoiceMV.setTotal(inInvoiceMV.getSubtotal().add(inInvoiceMV.getTax()).add(inInvoiceMV.getProcessingFee()));

        when(this.storeServiceLayer.createInvoice(inInvoiceMV)).thenReturn(null);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/invoice")
                        .content(mapper.writeValueAsString(inInvoiceMV)) //converts object to JSON and places into RequestBody
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) //for debugging purposes. Prints the request, handler,... and response objects to the console below.
                .andExpect(status().isUnprocessableEntity()); //Expected response status code.

        //Quantity...
        inInvoiceMV = new InvoiceViewModel();
        inInvoiceMV.setName("Rob Bank");
        inInvoiceMV.setStreet("123 Main St");
        inInvoiceMV.setCity("any City");
        inInvoiceMV.setState("NY");
        inInvoiceMV.setZipcode("10016");
        inInvoiceMV.setItemType("T-Shirt");
        inInvoiceMV.setItemId(12);//pretending item exists with this id...
        inInvoiceMV.setUnitPrice(new BigDecimal("12.50"));//pretending item exists with this price...
        inInvoiceMV.setQuantity(0);
        inInvoiceMV.setSubtotal(inInvoiceMV.getUnitPrice().multiply(new BigDecimal(inInvoiceMV.getQuantity())));
        inInvoiceMV.setTax(inInvoiceMV.getSubtotal().multiply(new BigDecimal("0.06")));
        inInvoiceMV.setProcessingFee(new BigDecimal("10.00"));
        inInvoiceMV.setTotal(inInvoiceMV.getSubtotal().add(inInvoiceMV.getTax()).add(inInvoiceMV.getProcessingFee()));

        when(this.storeServiceLayer.createInvoice(inInvoiceMV)).thenReturn(null);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/invoice")
                        .content(mapper.writeValueAsString(inInvoiceMV)) //converts object to JSON and places into RequestBody
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) //for debugging purposes. Prints the request, handler,... and response objects to the console below.
                .andExpect(status().isUnprocessableEntity()); //Expected response status code.

        inInvoiceMV = new InvoiceViewModel();
        inInvoiceMV.setName("Rob Bank");
        inInvoiceMV.setStreet("123 Main St");
        inInvoiceMV.setCity("any City");
        inInvoiceMV.setState("NY");
        inInvoiceMV.setZipcode("10016");
        inInvoiceMV.setItemType("T-Shirt");
        inInvoiceMV.setItemId(12);//pretending item exists with this id...
        inInvoiceMV.setUnitPrice(new BigDecimal("12.50"));//pretending item exists with this price...
        inInvoiceMV.setQuantity(50001);
        inInvoiceMV.setSubtotal(inInvoiceMV.getUnitPrice().multiply(new BigDecimal(inInvoiceMV.getQuantity())));
        inInvoiceMV.setTax(inInvoiceMV.getSubtotal().multiply(new BigDecimal("0.06")));
        inInvoiceMV.setProcessingFee(new BigDecimal("10.00"));
        inInvoiceMV.setTotal(inInvoiceMV.getSubtotal().add(inInvoiceMV.getTax()).add(inInvoiceMV.getProcessingFee()));

        when(this.storeServiceLayer.createInvoice(inInvoiceMV)).thenReturn(null);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/invoice")
                        .content(mapper.writeValueAsString(inInvoiceMV)) //converts object to JSON and places into RequestBody
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) //for debugging purposes. Prints the request, handler,... and response objects to the console below.
                .andExpect(status().isUnprocessableEntity()); //Expected response status code.

    }
}