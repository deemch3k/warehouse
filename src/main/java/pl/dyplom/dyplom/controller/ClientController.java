package pl.dyplom.dyplom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.dyplom.dyplom.domain.Client;
import pl.dyplom.dyplom.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("{id}")
    public Client getOne(@PathVariable("id") Client client){
        return client;
    }

    @GetMapping
    public List<Client> getList(){
        return clientService.getList();
    }

    @PostMapping
    public Client create(@RequestBody Client client){
        return clientService.create(client);
    }

    @PutMapping("{id}")
    public Client update(@PathVariable("id") Client clientFromDB, @RequestBody Client client ){
        return clientService.update(clientFromDB, client);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Client client){

        clientService.delete(client);
    }

}
