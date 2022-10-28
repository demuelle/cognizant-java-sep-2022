package com.trilogyed.gamestore.service;

import com.trilogyed.gamestore.repository.*;
import com.trilogyed.gamestore.model.*;
import com.trilogyed.gamestore.viewModel.ConsoleViewModel;
import com.trilogyed.gamestore.viewModel.GameViewModel;
import com.trilogyed.gamestore.viewModel.InvoiceViewModel;
import com.trilogyed.gamestore.viewModel.TShirtViewModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class GameStoreServiceLayerTest {

    ConsoleRepository consoleRepository;
    GameRepository gameRepository;
    TShirtRepository tShirtRepository;
    InvoiceRepository invoiceRepository;
    ProcessingFeeRepository processingFeeRepository;
    TaxRepository taxRepository;
    GameStoreServiceLayer service;

    @Before
    public void setUp() throws Exception {
        setUpConsoleRepositoryMock();
        setUpGameRepositoryMock();
        setUpTShirtRepositoryMock();
        setUpInvoiceRepositoryMock();
        setUpProcessingFeeRepositoryMock();
        setUpTaxRepositoryMock();

        service = new GameStoreServiceLayer(
                gameRepository, consoleRepository, tShirtRepository, invoiceRepository,
                taxRepository, processingFeeRepository);
    }

    //Testing Invoice Operations...
    @Test
    public void shouldCreateFindInvoice() {
        TShirtViewModel tShirt = new TShirtViewModel();
        tShirt.setSize("Medium");
        tShirt.setColor("Blue");
        tShirt.setDescription("V-Neck");
        tShirt.setPrice(new BigDecimal("19.99"));
        tShirt.setQuantity(5);
        tShirt = service.createTShirt(tShirt);

        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setName("John Jake");
        invoiceViewModel.setStreet("street");
        invoiceViewModel.setCity("Charlotte");
        invoiceViewModel.setState("NC");
        invoiceViewModel.setZipcode("83749");
        invoiceViewModel.setItemType("T-Shirt");
        invoiceViewModel.setItemId(54);
        invoiceViewModel.setQuantity(2);

        invoiceViewModel = service.createInvoice(invoiceViewModel);

        InvoiceViewModel ivmfromService = service.getInvoice(invoiceViewModel.getId());

        assertEquals(invoiceViewModel, ivmfromService);
    }

    @Test
    public void shouldFindAllInvoices(){
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

        List<InvoiceViewModel> currInvoices = new ArrayList<>();
        currInvoices.add(savedInvoice1);
        currInvoices.add(savedInvoice2);
        currInvoices.add(savedInvoice3);

        List<InvoiceViewModel> foundAllInvoices = service.getAllInvoices();

        assertEquals(currInvoices.size(), foundAllInvoices.size());
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldFailCreateFindInvoiceWithBadState() {
        TShirtViewModel tShirt = new TShirtViewModel();
        tShirt.setId(99);
        tShirt.setSize("Small");
        tShirt.setColor("Red");
        tShirt.setDescription("sleeveless");
        tShirt.setPrice(new BigDecimal("400"));
        tShirt.setQuantity(30);

        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setName("John Jake");
        invoiceViewModel.setStreet("street");
        invoiceViewModel.setCity("Charlotte");
        invoiceViewModel.setState("NY");
        invoiceViewModel.setZipcode("83749");
        invoiceViewModel.setItemType("T-Shirt");
        invoiceViewModel.setItemId(99);
        invoiceViewModel.setQuantity(2);

        invoiceViewModel = service.createInvoice(invoiceViewModel);

        InvoiceViewModel ivmfromService = service.getInvoice(invoiceViewModel.getId());

        assertEquals(invoiceViewModel, ivmfromService);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailCreateFindInvoiceWithBadItemType() {
        TShirtViewModel tShirt = new TShirtViewModel();
        tShirt.setSize("Medium");
        tShirt.setColor("Blue");
        tShirt.setDescription("V-Neck");
        tShirt.setPrice(new BigDecimal("19.99"));
        tShirt.setQuantity(5);
        tShirt = service.createTShirt(tShirt);

        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setName("John Jake");
        invoiceViewModel.setStreet("street");
        invoiceViewModel.setCity("Charlotte");
        invoiceViewModel.setState("NC");
        invoiceViewModel.setZipcode("83749");
        invoiceViewModel.setItemType("Bad Item Type");
        invoiceViewModel.setItemId(54);
        invoiceViewModel.setQuantity(2);

        invoiceViewModel = service.createInvoice(invoiceViewModel);

        InvoiceViewModel ivmfromService = service.getInvoice(invoiceViewModel.getId());

        assertEquals(invoiceViewModel, ivmfromService);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailCreateFindInvoiceWithNoInventory() {
        TShirtViewModel tShirt = new TShirtViewModel();
        tShirt.setSize("Medium");
        tShirt.setColor("Blue");
        tShirt.setDescription("V-Neck");
        tShirt.setPrice(new BigDecimal("19.99"));
        tShirt.setQuantity(5);
        tShirt = service.createTShirt(tShirt);

        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setName("John Jake");
        invoiceViewModel.setStreet("street");
        invoiceViewModel.setCity("Charlotte");
        invoiceViewModel.setState("NC");
        invoiceViewModel.setZipcode("83749");
        invoiceViewModel.setItemType("T-Shirt");
        invoiceViewModel.setItemId(54);
        invoiceViewModel.setQuantity(6);

        invoiceViewModel = service.createInvoice(invoiceViewModel);

        InvoiceViewModel ivmfromService = service.getInvoice(invoiceViewModel.getId());

        assertEquals(invoiceViewModel, ivmfromService);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenCreateInvoiceInvalidItem() {
        TShirtViewModel tShirt = new TShirtViewModel();
        tShirt.setSize("Medium");
        tShirt.setColor("Blue");
        tShirt.setDescription("V-Neck");
        tShirt.setPrice(new BigDecimal("19.99"));
        tShirt.setQuantity(5);
        tShirt = service.createTShirt(tShirt);

        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setName("John Jake");
        invoiceViewModel.setStreet("street");
        invoiceViewModel.setCity("Charlotte");
        invoiceViewModel.setState("NC");
        invoiceViewModel.setZipcode("83749");
        invoiceViewModel.setItemType("nothing");
        invoiceViewModel.setItemId(54);
        invoiceViewModel.setQuantity(2);

        invoiceViewModel = service.createInvoice(invoiceViewModel);

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenCreateInvoiceInvalidQuantity() {
        TShirtViewModel tShirt = new TShirtViewModel();
        tShirt.setSize("Medium");
        tShirt.setColor("Blue");
        tShirt.setDescription("V-Neck");
        tShirt.setPrice(new BigDecimal("19.99"));
        tShirt.setQuantity(5);
        tShirt = service.createTShirt(tShirt);

        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setName("John Jake");
        invoiceViewModel.setStreet("street");
        invoiceViewModel.setCity("Charlotte");
        invoiceViewModel.setState("NC");
        invoiceViewModel.setZipcode("83749");
        invoiceViewModel.setItemType("T-Shirt");
        invoiceViewModel.setItemId(54);
        invoiceViewModel.setQuantity(0);

        invoiceViewModel = service.createInvoice(invoiceViewModel);
    }

    @Test(expected = NullPointerException.class)
    public void shouldFailWhenCreateInvoiceInvalidInvoiceMV() {
        TShirtViewModel tShirt = new TShirtViewModel();
        tShirt.setSize("Medium");
        tShirt.setColor("Blue");
        tShirt.setDescription("V-Neck");
        tShirt.setPrice(new BigDecimal("19.99"));
        tShirt.setQuantity(5);
        tShirt = service.createTShirt(tShirt);

        InvoiceViewModel invoiceViewModel = null;

        invoiceViewModel = service.createInvoice(invoiceViewModel);
    }

    //Testing Console Operations...
    @Test
    public void shouldCreateGetConsole() {

        ConsoleViewModel console = new ConsoleViewModel();
        console.setModel("Playstation");
        console.setManufacturer("Sony");
        console.setMemoryAmount("120gb");
        console.setProcessor("Intel I7-9750H");
        console.setPrice(new BigDecimal("299.99"));
        console.setQuantity(4);
        console = service.createConsole(console);

        ConsoleViewModel console1 = service.getConsoleById(console.getId());
        assertEquals(console, console1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenCreateConsoleWithNullViewModel() {

        ConsoleViewModel console = new ConsoleViewModel();

        console = null;
        console = service.createConsole(console);
    }

    @Test
    public void shouldUpdateConsole() {
        ConsoleViewModel console2 = new ConsoleViewModel();
        console2.setModel("Playstation");
        console2.setManufacturer("Sony");
        console2.setMemoryAmount("120gb");
        console2.setProcessor("Intel I7-9750H");
        console2.setPrice(new BigDecimal("299.99"));
        console2.setQuantity(4);
        console2 = service.createConsole(console2);

        console2.setQuantity(6);
        console2.setPrice(new BigDecimal(289.99));

        service.updateConsole(console2);

        verify(consoleRepository, times(2)).save(any(Console.class));

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailUpdateConsoleWithNullModelView() {
        ConsoleViewModel console2 = null;

        service.updateConsole(console2);

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenUpdateConsoleWithBadId() {
        ConsoleViewModel console2 = new ConsoleViewModel();
        console2.setModel("Playstation");
        console2.setManufacturer("Sony");
        console2.setMemoryAmount("120gb");
        console2.setProcessor("Intel I7-9750H");
        console2.setPrice(new BigDecimal("299.99"));
        console2.setQuantity(4);
        console2 = service.createConsole(console2);

        console2.setQuantity(6);
        console2.setPrice(new BigDecimal(289.99));

        //change Id to an invalid one.
        console2.setId(console2.getId()+1);

        service.updateConsole(console2);
    }

    @Test
    public void shouldDeleteConsole() {

        ConsoleViewModel console2 = new ConsoleViewModel();
        console2.setModel("Playstation");
        console2.setManufacturer("Sony");
        console2.setMemoryAmount("120gb");
        console2.setProcessor("Intel I7-9750H");
        console2.setPrice(new BigDecimal("299.99"));
        console2.setQuantity(4);
        console2 = service.createConsole(console2);

        service.deleteConsole(console2.getId());

        verify(consoleRepository).deleteById(console2.getId());
    }

    @Test
    public void shouldFindConsoleByManufacturer() {
        List<ConsoleViewModel> cvmList = new ArrayList<>();

        ConsoleViewModel console2 = new ConsoleViewModel();
        console2.setModel("Playstation");
        console2.setManufacturer("Sony");
        console2.setMemoryAmount("120gb");
        console2.setProcessor("Intel I7-9750H");
        console2.setPrice(new BigDecimal("299.99"));
        console2.setQuantity(4);

        console2 = service.createConsole(console2);
        cvmList.add(console2);

        ConsoleViewModel console3 = new ConsoleViewModel();
        console3.setModel("Xbox");
        console3.setManufacturer("Sony");
        console3.setMemoryAmount("256gb");
        console3.setProcessor("Intel I7-9750H");
        console3.setPrice(new BigDecimal("399.99"));
        console3.setQuantity(4);

        console3 = service.createConsole(console3);
        cvmList.add(console3);

        List<ConsoleViewModel> cvmFromService = service.getConsoleByManufacturer("Sony");

        assertEquals(cvmList, cvmFromService);
    }

    @Test
    public void shouldFindAllConsoles() throws Exception{
        List<ConsoleViewModel> cvmList = new ArrayList<>();

        ConsoleViewModel console1 = new ConsoleViewModel();
        console1.setModel("Playstation");
        console1.setManufacturer("Sony");
        console1.setMemoryAmount("120gb");
        console1.setProcessor("Intel I7-9750H");
        console1.setPrice(new BigDecimal("299.99"));
        console1.setQuantity(4);

        console1 = service.createConsole(console1);
        cvmList.add(console1);

        ConsoleViewModel console2 = new ConsoleViewModel();
        console2.setModel("Xbox");
        console2.setManufacturer("Sony");
        console2.setMemoryAmount("256gb");
        console2.setProcessor("Intel I7-9750H");
        console2.setPrice(new BigDecimal("399.99"));
        console2.setQuantity(4);

        console2 = service.createConsole(console2);
        cvmList.add(console2);

        ConsoleViewModel console3 = new ConsoleViewModel();
        console3.setModel("PS III");
        console3.setManufacturer("Sony");
        console3.setMemoryAmount("512Gb");
        console3.setProcessor("AMD I7-9750A");
        console3.setPrice(new BigDecimal("199.99"));
        console3.setQuantity(40);

        console3 = service.createConsole(console3);
        cvmList.add(console3);

        List<ConsoleViewModel> cvmFromService = service.getAllConsoles();

        assertEquals(cvmList.size(), cvmFromService.size());
    }

    //Testing TShirt operations...
    @Test
    public void shouldCreateFindTShirt() {
        TShirtViewModel tShirt = new TShirtViewModel();
        tShirt.setSize("Medium");
        tShirt.setColor("Blue");
        tShirt.setDescription("V-Neck");
        tShirt.setPrice(new BigDecimal("19.99"));
        tShirt.setQuantity(5);
        tShirt = service.createTShirt(tShirt);

        TShirtViewModel tShirtFromService = service.getTShirt(tShirt.getId());

        assertEquals(tShirt, tShirtFromService);

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFaileWhenCreateTShirtWithNullViewModel() {
        TShirtViewModel tShirt = null;
        tShirt = service.createTShirt(tShirt);
    }

    @Test
    public void shouldUpdateTShirt() {

        TShirtViewModel tShirt = new TShirtViewModel();
        tShirt.setSize("Medium");
        tShirt.setColor("Blue");
        tShirt.setDescription("V-Neck");
        tShirt.setPrice(new BigDecimal("19.99"));
        tShirt.setQuantity(5);
        tShirt = service.createTShirt(tShirt);

        tShirt.setQuantity(3);
        tShirt.setPrice(new BigDecimal("18.99"));

        service.updateTShirt(tShirt);

        verify(tShirtRepository, times(2)).save(any(TShirt.class));

    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldFailUpdateTShirtWithNullViewModel() {

        TShirtViewModel tShirt = null;
        service.updateTShirt(tShirt);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailUpdateTShirtWithBadId() {

        TShirtViewModel tShirt = new TShirtViewModel();
        tShirt.setSize("Medium");
        tShirt.setColor("Blue");
        tShirt.setDescription("V-Neck");
        tShirt.setPrice(new BigDecimal("19.99"));
        tShirt.setQuantity(5);
        tShirt = service.createTShirt(tShirt);

        tShirt.setQuantity(3);
        tShirt.setPrice(new BigDecimal("18.99"));

        tShirt.setId(tShirt.getId()+1);
        service.updateTShirt(tShirt);
    }

    @Test
    public void shouldDeleteTShirt() {

        TShirtViewModel tShirt = new TShirtViewModel();
        tShirt.setSize("Medium");
        tShirt.setColor("Blue");
        tShirt.setDescription("V-Neck");
        tShirt.setPrice(new BigDecimal("19.99"));
        tShirt.setQuantity(5);
        tShirt = service.createTShirt(tShirt);

        service.deleteTShirt(tShirt.getId());

        verify(tShirtRepository).deleteById(any(Long.class));

    }

    @Test
    public void shouldFindTShirtByColor() {
        List<TShirtViewModel> tvmList = new ArrayList<>();

        TShirtViewModel tShirt = new TShirtViewModel();
        tShirt.setSize("Medium");
        tShirt.setColor("Blue");
        tShirt.setDescription("V-Neck");
        tShirt.setPrice(new BigDecimal("19.99"));
        tShirt.setQuantity(5);
        tShirt = service.createTShirt(tShirt);
        tvmList.add(tShirt);

        TShirtViewModel tShirtExtra2 = new TShirtViewModel();
        tShirtExtra2.setSize("Large");
        tShirtExtra2.setColor("Blue");
        tShirtExtra2.setDescription("long sleeve");
        tShirtExtra2.setPrice(new BigDecimal("30.99"));
        tShirtExtra2.setQuantity(8);
        tShirtExtra2 = service.createTShirt(tShirtExtra2);
        tvmList.add(tShirtExtra2);

        List<TShirtViewModel> tvmFromService = service.getTShirtByColor("Blue");

        assertEquals(tvmList, tvmFromService);

    }

    @Test
    public void shouldFindTShirtBySize() {
        List<TShirtViewModel> tvmList = new ArrayList<>();

        TShirtViewModel tShirt = new TShirtViewModel();
        tShirt.setSize("Medium");
        tShirt.setColor("Blue");
        tShirt.setDescription("V-Neck");
        tShirt.setPrice(new BigDecimal("19.99"));
        tShirt.setQuantity(5);
        tShirt = service.createTShirt(tShirt);
        tvmList.add(tShirt);

        TShirtViewModel tShirtExtra3 = new TShirtViewModel();
        tShirtExtra3.setSize("Medium");
        tShirtExtra3.setColor("orange");
        tShirtExtra3.setDescription("sleeveless");
        tShirtExtra3.setPrice(new BigDecimal("15.99"));
        tShirtExtra3.setQuantity(3);
        tShirtExtra3 = service.createTShirt(tShirtExtra3);
        tvmList.add(tShirtExtra3);

        List<TShirtViewModel> tvmFromService = service.getTShirtBySize("Medium");

        assertEquals(tvmList, tvmFromService);

    }

    @Test
    public void shouldFindAllTShirts() {
        List<TShirtViewModel> tvmList = new ArrayList<>();

        TShirtViewModel newTShirt1 = new TShirtViewModel();
        newTShirt1.setSize("Medium");
        newTShirt1.setColor("Blue");
        newTShirt1.setDescription("V-Neck");
        newTShirt1.setPrice(new BigDecimal("19.99"));
        newTShirt1.setQuantity(5);

        newTShirt1 = service.createTShirt(newTShirt1);
        tvmList.add(newTShirt1);

        TShirtViewModel newTShirt2 = new TShirtViewModel();
        newTShirt2.setSize("Large");
        newTShirt2.setColor("Blue");
        newTShirt2.setDescription("long sleeve");
        newTShirt2.setPrice(new BigDecimal("30.99"));
        newTShirt2.setQuantity(8);

        newTShirt2 = service.createTShirt(newTShirt2);
        tvmList.add(newTShirt2);

        TShirtViewModel newTShirt3 = new TShirtViewModel();
        newTShirt3.setSize("Medium");
        newTShirt3.setColor("orange");
        newTShirt3.setDescription("sleeveless");
        newTShirt3.setPrice(new BigDecimal("15.99"));
        newTShirt3.setQuantity(3);

        newTShirt3 = service.createTShirt(newTShirt3);
        tvmList.add(newTShirt3);

        List<TShirtViewModel> tvmFromService = service.getAllTShirts();

        assertEquals(tvmList, tvmFromService);
    }

    //Testing Game operations...
    @Test
    public void shouldCreateFindGame() {

        GameViewModel gameViewModel = new GameViewModel();
        gameViewModel.setTitle("Halo");
        gameViewModel.setEsrbRating("E10+");
        gameViewModel.setDescription("Puzzles and Math");
        gameViewModel.setPrice(new BigDecimal("23.99"));
        gameViewModel.setStudio("Xbox Game Studios");
        gameViewModel.setQuantity(5);
        gameViewModel = service.createGame(gameViewModel);

        GameViewModel gameViewModel2 = service.getGame(32);
        assertEquals(gameViewModel, gameViewModel2);
    }

    @Test(expected = NullPointerException.class)
    public void shouldFailWhenCreateGameNullTitle() {

        GameViewModel gameViewModel = new GameViewModel();
        gameViewModel.setTitle(null);
        gameViewModel.setEsrbRating("E10+");
        gameViewModel.setDescription("Puzzles and Math");
        gameViewModel.setPrice(new BigDecimal("23.99"));
        gameViewModel.setStudio("Xbox Game Studios");
        gameViewModel.setQuantity(5);
        gameViewModel = service.createGame(gameViewModel);

        GameViewModel gameViewModel2 = service.getGame(32);

        assertEquals(gameViewModel, gameViewModel2);
    }

    @Test
    public void shouldUpdateGame() {

        GameViewModel game = new GameViewModel();
        game.setTitle("Halo");
        game.setEsrbRating("E10+");
        game.setDescription("Puzzles and Math");
        game.setPrice(new BigDecimal("23.99"));
        game.setStudio("Xbox Game Studios");
        game.setQuantity(5);
        game = service.createGame(game);

        game.setPrice(new BigDecimal("20.99"));
        game.setQuantity(3);
        service.updateGame(game);

        verify(gameRepository, times(2)).save(any(Game.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenUpdateGameInvalidId() {

        GameViewModel game = new GameViewModel();
        game.setTitle("Halo");
        game.setEsrbRating("E10+");
        game.setDescription("Puzzles and Math");
        game.setPrice(new BigDecimal("23.99"));
        game.setStudio("Xbox Game Studios");
        game.setQuantity(5);
        game = service.createGame(game);

        game.setPrice(new BigDecimal("20.99"));
        game.setQuantity(3);

        //set game id to invalid id...
        game.setId(game.getId()+1);
        service.updateGame(game);

        System.out.println(game);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenUpdateGameNullViewModel() {
        service.updateGame(null);
    }

    @Test
    public void shouldDeleteGame() {

        GameViewModel game = new GameViewModel();
        game.setTitle("Halo");
        game.setEsrbRating("E10+");
        game.setDescription("Puzzles and Math");
        game.setPrice(new BigDecimal("23.99"));
        game.setStudio("Xbox Game Studios");
        game.setQuantity(5);
        game = service.createGame(game);

        service.deleteGame(game.getId());

        verify(gameRepository).deleteById(any(Long.class));

    }

    @Test
    public void shouldFindGameByEsrb() {

        List<GameViewModel> gamesByEsrb = new ArrayList<>();

        GameViewModel game1 = new GameViewModel();
        game1.setTitle("Halo");
        game1.setEsrbRating("E10+");
        game1.setDescription("Puzzles and Math");
        game1.setPrice(new BigDecimal("23.99"));
        game1.setStudio("Xbox Game Studios");
        game1.setQuantity(5);
        game1 = service.createGame(game1);
        gamesByEsrb.add(game1);

        GameViewModel gameExtra = new GameViewModel();
        gameExtra.setTitle("Tetris");
        gameExtra.setEsrbRating("E10+");
        gameExtra.setDescription("block puzzle game");
        gameExtra.setPrice(new BigDecimal("10.99"));
        gameExtra.setStudio("Dolby Studios");
        gameExtra.setQuantity(9);
        gameExtra = service.createGame(gameExtra);
        gamesByEsrb.add(gameExtra);

        List<GameViewModel> gvmFromService = service.getGameByEsrb("E10+");

        assertEquals(gamesByEsrb, gvmFromService);

        //Test Esrb with no games...
        gvmFromService = service.getGameByEsrb("E18+");
        assertEquals(gvmFromService.size(),0);

    }

    @Test
    public void shouldFindGameByTitle() {
        List<GameViewModel> gvmList = new ArrayList<>();

        GameViewModel game = new GameViewModel();
        game.setTitle("Halo");
        game.setEsrbRating("E10+");
        game.setDescription("Puzzles and Math");
        game.setPrice(new BigDecimal("23.99"));
        game.setStudio("Xbox Game Studios");
        game.setQuantity(5);
        game = service.createGame(game);
        gvmList.add(game);

        GameViewModel game2 = new GameViewModel();
        game2.setTitle("Fort Lines");
        game2.setEsrbRating("M");
        game2.setDescription("Zombie shooter game");
        game2.setPrice(new BigDecimal("37.99"));
        game2.setStudio("Dolby Studios");
        game2.setQuantity(3);
        game2 = service.createGame(game2);
        gvmList.add(game2);

        List<GameViewModel> gvmFromService = service.getGameByTitle("Halo");

        //Test title with no games...
        gvmFromService = service.getGameByTitle("Shalo");
        assertEquals(gvmFromService.size(),0);
    }

    @Test
    public void shouldFindGameByStudio() {
        List<GameViewModel> gvmList = new ArrayList<>();

        GameViewModel gameExtra2 = new GameViewModel();
        gameExtra2.setTitle("Tetris");
        gameExtra2.setEsrbRating("E10+");
        gameExtra2.setDescription("block puzzle game");
        gameExtra2.setPrice(new BigDecimal("10.99"));
        gameExtra2.setStudio("Dolby Studios");
        gameExtra2.setQuantity(9);
        gameExtra2 = service.createGame(gameExtra2);
        gvmList.add(gameExtra2);

        GameViewModel gameExtra3 = new GameViewModel();
        gameExtra3.setTitle("Fort Lines");
        gameExtra3.setEsrbRating("M");
        gameExtra3.setDescription("Zombie shooter game");
        gameExtra3.setPrice(new BigDecimal("37.99"));
        gameExtra3.setStudio("Dolby Studios");
        gameExtra3.setQuantity(3);
        gameExtra3 = service.createGame(gameExtra3);
        gvmList.add(gameExtra3);

        List<GameViewModel> gvmFromService = service.getGameByStudio("Dolby Studios");
        assertEquals(gvmList, gvmFromService);

        //Test title with no games...
        gvmFromService = service.getGameByStudio("EA");
        assertEquals(gvmFromService.size(),0);
    }

    @Test
    public void shouldFindAllGames() {
        List<GameViewModel> gvmList = new ArrayList<>();

        GameViewModel newGame1 = new GameViewModel();
        newGame1.setTitle("Halo");
        newGame1.setEsrbRating("E10+");
        newGame1.setDescription("Puzzles and Math");
        newGame1.setPrice(new BigDecimal("23.99"));
        newGame1.setStudio("Xbox Game Studios");
        newGame1.setQuantity(5);

        newGame1 = service.createGame(newGame1);
        gvmList.add(newGame1);

        GameViewModel newGame2 = new GameViewModel();
        newGame2.setTitle("Tetris");
        newGame2.setEsrbRating("E10+");
        newGame2.setDescription("block puzzle game");
        newGame2.setPrice(new BigDecimal("10.99"));
        newGame2.setStudio("Dolby Studios");
        newGame2.setQuantity(9);

        newGame2 = service.createGame(newGame2);
        gvmList.add(newGame2);

        GameViewModel newGame3 = new GameViewModel();
        newGame3.setTitle("Fort Lines");
        newGame3.setEsrbRating("M");
        newGame3.setDescription("Zombie shooter game");
        newGame3.setPrice(new BigDecimal("37.99"));
        newGame3.setStudio("Dolby Studios");
        newGame3.setQuantity(3);
        newGame3 = service.createGame(newGame3);
        gvmList.add(newGame3);

        List<GameViewModel> gvmFromService = service.getAllGames();
        assertEquals(gvmList, gvmFromService);

    }

    //DAO Mocks...
    private void setUpConsoleRepositoryMock() {

        consoleRepository = mock(ConsoleRepository.class);

        List<Console> allConsoles = new ArrayList<>();
        List<Console> consoleByManufacturer = new ArrayList<>();

        Console newConsole1 = new Console();
        newConsole1.setModel("Playstation");
        newConsole1.setManufacturer("Sony");
        newConsole1.setMemoryAmount("120gb");
        newConsole1.setProcessor("Intel I7-9750H");
        newConsole1.setPrice(new BigDecimal("299.99"));
        newConsole1.setQuantity(4);

        Console savedConsole1 = new Console();
        savedConsole1.setId(40);
        savedConsole1.setModel("Playstation");
        savedConsole1.setManufacturer("Sony");
        savedConsole1.setMemoryAmount("120gb");
        savedConsole1.setProcessor("Intel I7-9750H");
        savedConsole1.setPrice(new BigDecimal("299.99"));
        savedConsole1.setQuantity(4);

        consoleByManufacturer.add(savedConsole1);
        allConsoles.add(savedConsole1);

        Console newConsole2 = new Console();
        newConsole2.setModel("Xbox");
        newConsole2.setManufacturer("Sony");
        newConsole2.setMemoryAmount("256gb");
        newConsole2.setProcessor("Intel I7-9750H");
        newConsole2.setPrice(new BigDecimal("399.99"));
        newConsole2.setQuantity(4);

        Console savedConsole2 = new Console();
        savedConsole2.setId(34);
        savedConsole2.setModel("Xbox");
        savedConsole2.setManufacturer("Sony");
        savedConsole2.setMemoryAmount("256gb");
        savedConsole2.setProcessor("Intel I7-9750H");
        savedConsole2.setPrice(new BigDecimal("399.99"));
        savedConsole2.setQuantity(4);

        consoleByManufacturer.add(savedConsole2);
        allConsoles.add(savedConsole2);

        Console newConsole3 = new Console();
        newConsole3.setModel("PS III");
        newConsole3.setManufacturer("Sony");
        newConsole3.setMemoryAmount("512Gb");
        newConsole3.setProcessor("AMD I7-9750A");
        newConsole3.setPrice(new BigDecimal("199.99"));
        newConsole3.setQuantity(40);

        Console savedConsole3 = new Console();
        savedConsole3.setId(38);
        savedConsole3.setModel("PS III");
        savedConsole3.setManufacturer("Sony");
        savedConsole3.setMemoryAmount("512Gb");
        savedConsole3.setProcessor("AMD I7-9750A");
        savedConsole3.setPrice(new BigDecimal("199.99"));
        savedConsole3.setQuantity(40);

        allConsoles.add(savedConsole3);

        doReturn(savedConsole1).when(consoleRepository).save(newConsole1);
        doReturn(savedConsole2).when(consoleRepository).save(newConsole2);
        doReturn(savedConsole3).when(consoleRepository).save(newConsole3);
        doReturn(Optional.of(savedConsole1)).when(consoleRepository).findById(40L);
        doReturn(consoleByManufacturer).when(consoleRepository).findAllByManufacturer("Sony");
        doReturn(allConsoles).when(consoleRepository).findAll();

    }

    private void setUpGameRepositoryMock() {
        gameRepository = mock(GameRepository.class);

        List<Game> gamesByEsrb = new ArrayList<>();
        List<Game> gamesByTitle = new ArrayList<>();
        List<Game> gamesByStudio = new ArrayList<>();
        List<Game> allGames = new ArrayList<>();

        //No ID in this "game" object
        Game newGame1 = new Game();
        newGame1.setTitle("Halo");
        newGame1.setEsrbRating("E10+");
        newGame1.setDescription("Puzzles and Math");
        newGame1.setPrice(new BigDecimal("23.99"));
        newGame1.setStudio("Xbox Game Studios");
        newGame1.setQuantity(5);

        Game savedGame1 = new Game();
        savedGame1.setId(32);
        savedGame1.setTitle("Halo");
        savedGame1.setEsrbRating("E10+");
        savedGame1.setDescription("Puzzles and Math");
        savedGame1.setPrice(new BigDecimal("23.99"));
        savedGame1.setStudio("Xbox Game Studios");
        savedGame1.setQuantity(5);
        gamesByEsrb.add(savedGame1);
        gamesByTitle.add(savedGame1);
        allGames.add(savedGame1);

        Game newGame2 = new Game();
        newGame2.setTitle("Tetris");
        newGame2.setEsrbRating("E10+");
        newGame2.setDescription("block puzzle game");
        newGame2.setPrice(new BigDecimal("10.99"));
        newGame2.setStudio("Dolby Studios");
        newGame2.setQuantity(9);

        Game savedGame2 = new Game();
        savedGame2.setId(25);
        savedGame2.setTitle("Tetris");
        savedGame2.setEsrbRating("E10+");
        savedGame2.setDescription("block puzzle game");
        savedGame2.setPrice(new BigDecimal("10.99"));
        savedGame2.setStudio("Dolby Studios");
        savedGame2.setQuantity(9);
        gamesByEsrb.add(savedGame2);
        gamesByStudio.add(savedGame2);
        allGames.add(savedGame2);

        Game newGame3 = new Game();
        newGame3.setTitle("Fort Lines");
        newGame3.setEsrbRating("M");
        newGame3.setDescription("Zombie shooter game");
        newGame3.setPrice(new BigDecimal("37.99"));
        newGame3.setStudio("Dolby Studios");
        newGame3.setQuantity(3);

        Game savedGame3 = new Game();
        savedGame3.setId(60);
        savedGame3.setTitle("Fort Lines");
        savedGame3.setEsrbRating("M");
        savedGame3.setDescription("Zombie shooter game");
        savedGame3.setPrice(new BigDecimal("37.99"));
        savedGame3.setStudio("Dolby Studios");
        savedGame3.setQuantity(3);
        gamesByTitle.add(savedGame3);
        gamesByStudio.add(savedGame3);
        allGames.add(savedGame3);

        doReturn(savedGame1).when(gameRepository).save(newGame1);
        doReturn(Optional.of(savedGame3)).when(gameRepository).findById(60L);
        doReturn(Optional.of(savedGame1)).when(gameRepository).findById(32L);
        doReturn(Optional.of(savedGame2)).when(gameRepository).findById(25L);
        doReturn(savedGame2).when(gameRepository).save(newGame2);
        doReturn(savedGame3).when(gameRepository).save(newGame3);

        doReturn(gamesByEsrb).when(gameRepository).findAllByEsrbRating("E10+");
        doReturn(gamesByStudio).when(gameRepository).findAllByStudio("Dolby Studios");
        doReturn(gamesByTitle).when(gameRepository).findAllByTitle("Halo");
        doReturn(allGames).when(gameRepository).findAll();

    }

    private void setUpTShirtRepositoryMock() {
        tShirtRepository = mock(TShirtRepository.class);

        List<TShirt> tShirtsByColor = new ArrayList<>();
        List<TShirt> tShirtsBySize = new ArrayList<>();
        List<TShirt> allTtShirts = new ArrayList<>();

        TShirt newTShirt1 = new TShirt();
        newTShirt1.setSize("Medium");
        newTShirt1.setColor("Blue");
        newTShirt1.setDescription("V-Neck");
        newTShirt1.setPrice(new BigDecimal("19.99"));
        newTShirt1.setQuantity(5);

        TShirt savedTShirt1 = new TShirt();
        savedTShirt1.setId(54);
        savedTShirt1.setSize("Medium");
        savedTShirt1.setColor("Blue");
        savedTShirt1.setDescription("V-Neck");
        savedTShirt1.setPrice(new BigDecimal("19.99"));
        savedTShirt1.setQuantity(5);

        tShirtsByColor.add(savedTShirt1);
        tShirtsBySize.add(savedTShirt1);
        allTtShirts.add(savedTShirt1);

        TShirt newTShirt2 = new TShirt();
        newTShirt2.setSize("Large");
        newTShirt2.setColor("Blue");
        newTShirt2.setDescription("long sleeve");
        newTShirt2.setPrice(new BigDecimal("30.99"));
        newTShirt2.setQuantity(8);

        TShirt savedTShirt2 = new TShirt();
        savedTShirt2.setId(60);
        savedTShirt2.setSize("Large");
        savedTShirt2.setColor("Blue");
        savedTShirt2.setDescription("long sleeve");
        savedTShirt2.setPrice(new BigDecimal("30.99"));
        savedTShirt2.setQuantity(8);

        allTtShirts.add(savedTShirt2);
        tShirtsByColor.add(savedTShirt2);

        TShirt newTShirt3 = new TShirt();
        newTShirt3.setSize("Medium");
        newTShirt3.setColor("orange");
        newTShirt3.setDescription("sleeveless");
        newTShirt3.setPrice(new BigDecimal("15.99"));
        newTShirt3.setQuantity(3);

        TShirt savedTShirt3 = new TShirt();
        savedTShirt3.setId(72);
        savedTShirt3.setSize("Medium");
        savedTShirt3.setColor("orange");
        savedTShirt3.setDescription("sleeveless");
        savedTShirt3.setPrice(new BigDecimal("15.99"));
        savedTShirt3.setQuantity(3);

        allTtShirts.add(savedTShirt3);
        tShirtsBySize.add(savedTShirt3);

        TShirt newTShirt4 = new TShirt();
        newTShirt4.setSize("Small");
        newTShirt4.setColor("Red");
        newTShirt4.setDescription("sleeveless");
        newTShirt4.setPrice(new BigDecimal("400"));
        newTShirt4.setQuantity(30);

        TShirt savedTShirt4 = new TShirt();
        savedTShirt4.setId(99);
        savedTShirt4.setSize("Small");
        savedTShirt4.setColor("Red");
        savedTShirt4.setDescription("sleeveless");
        savedTShirt4.setPrice(new BigDecimal("400"));
        savedTShirt4.setQuantity(30);

        doReturn(savedTShirt1).when(tShirtRepository).save(newTShirt1);
        doReturn(savedTShirt2).when(tShirtRepository).save(newTShirt2);
        doReturn(savedTShirt3).when(tShirtRepository).save(newTShirt3);
        doReturn(Optional.of(savedTShirt3)).when(tShirtRepository).findById(72L);
        doReturn(Optional.of(savedTShirt1)).when(tShirtRepository).findById(54L);
        doReturn(Optional.of(savedTShirt4)).when(tShirtRepository).findById(99L);

        doReturn(tShirtsByColor).when(tShirtRepository).findAllByColor("Blue");
        doReturn(tShirtsBySize).when(tShirtRepository).findAllBySize("Medium");
        doReturn(allTtShirts).when(tShirtRepository).findAll();

    }

    private void setUpInvoiceRepositoryMock() {
        invoiceRepository = mock(InvoiceRepository.class);

        Invoice invoice = new Invoice();
        invoice.setName("John Jake");
        invoice.setStreet("street");
        invoice.setCity("Charlotte");
        invoice.setState("NC");
        invoice.setZipcode("83749");
        invoice.setItemType("T-Shirt");
        invoice.setItemId(54);
        invoice.setUnitPrice(new BigDecimal("19.99"));
        invoice.setQuantity(2);
        invoice.setSubtotal(new BigDecimal("39.98"));
        invoice.setTax(new BigDecimal("2"));
        invoice.setProcessingFee(new BigDecimal("1.98"));
        invoice.setTotal(new BigDecimal("43.96"));

        Invoice invoice1 = new Invoice();
        invoice1.setId(20);
        invoice1.setName("John Jake");
        invoice1.setStreet("street");
        invoice1.setCity("Charlotte");
        invoice1.setState("NC");
        invoice1.setZipcode("83749");
        invoice1.setItemType("T-Shirt");
        invoice1.setItemId(54);
        invoice1.setUnitPrice(new BigDecimal("19.99"));
        invoice1.setQuantity(2);
        invoice1.setSubtotal(new BigDecimal("39.98"));
        invoice1.setTax(new BigDecimal("2"));
        invoice1.setProcessingFee(new BigDecimal("1.98"));
        invoice1.setTotal(new BigDecimal("43.96"));

        doReturn(invoice1).when(invoiceRepository).save(invoice);
        doReturn(Optional.of(invoice1)).when(invoiceRepository).findById(20L);

        //Get All...
        Invoice savedInvoice1 = new Invoice();
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

        Invoice savedInvoice2 = new Invoice();
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

        Invoice savedInvoice3 = new Invoice();
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

        List<Invoice> allList = new ArrayList<>();
        allList.add(savedInvoice1);
        allList.add(savedInvoice2);
        allList.add(savedInvoice3);

        doReturn(allList).when(invoiceRepository).findAll();
    }

    private void setUpProcessingFeeRepositoryMock() {

        processingFeeRepository = mock(ProcessingFeeRepository.class);

        ProcessingFee processingFee = new ProcessingFee();
        processingFee.setFee(new BigDecimal("1.98"));
        processingFee.setProductType("T-Shirt");

        doReturn(Optional.of(processingFee)).when(processingFeeRepository).findById("T-Shirt");

    }

    private void setUpTaxRepositoryMock() {
        taxRepository = mock(TaxRepository.class);

        Tax taxNC = new Tax();
        taxNC.setRate(new BigDecimal(".05"));
        taxNC.setState("NC");

        Tax taxNY = new Tax();
        taxNY.setRate(BigDecimal.ZERO);
        taxNY.setState("NY");

        doReturn(Optional.of(taxNC)).when(taxRepository).findById("NC");
        doReturn(Optional.of(taxNY)).when(taxRepository).findById("NY");

    }


}