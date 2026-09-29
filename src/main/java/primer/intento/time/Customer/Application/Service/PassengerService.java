package primer.intento.time.Customer.Application.Service;


import org.springframework.stereotype.Service;
import primer.intento.time.Customer.Application.Port.in.CreatePassengerComand;
import primer.intento.time.Customer.Application.Port.in.CreatePassengerUseCase;
import primer.intento.time.Customer.Application.Port.in.GetPassengerUseCase;
import primer.intento.time.Customer.Application.Port.out.PassengerRepositoryPort;
import primer.intento.time.Customer.Domain.Exception.PassengerNotFoundException;
import primer.intento.time.Customer.Domain.Model.Passenger;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerService implements GetPassengerUseCase, CreatePassengerUseCase {

    PassengerRepositoryPort repository;

    public PassengerService(PassengerRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Passenger create(CreatePassengerComand cmd) {
        Passenger passenger = new Passenger();
        passenger.setFirstname(cmd.getName());
        passenger.setSecondname(cmd.getSecondname());
        passenger.setLastname(cmd.getLastname());
        passenger.setEmail(cmd.getEmail());
        passenger.getId(cmd.getNumberId());
        passenger.getFlightNumber(cmd.getFlightNumber());

        return this.repository.save(passenger);
    }

    @Override
    public Passenger findById(Long id) {
        Optional<Passenger> optionalPassenger = Optional.ofNullable(this.repository.findById(id));
        if (optionalPassenger.isEmpty()) {
            throw new PassengerNotFoundException("Passenger not found with id " + id);
        }
        return optionalPassenger.get();
    }

    @Override
    public List<Passenger> findAll() {
        return this.repository.findAll();
    }
}