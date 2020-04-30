package pl.dyplom.dyplom.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dyplom.dyplom.domain.Client;
import pl.dyplom.dyplom.repo.ClientRepo;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepo clientRepo;

    @Autowired
    public ClientService(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    public List<Client> getList(){
        return clientRepo.findAll();
    }


    public Client create(Client client) {
       return clientRepo.save(client);
    }

    public Client update(Client clientFromDB, Client client) {
        BeanUtils.copyProperties(client,clientFromDB);
        return clientRepo.save(clientFromDB);
    }

    public void delete(Client client) {
        clientRepo.delete(client);

    }
}
