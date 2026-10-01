package primer.intento.time.passenger.application.service;

import primer.intento.time.passenger.application.port.in.CreatePassengerCommand;
import primer.intento.time.passenger.application.port.in.CreatePassengerUseCase;
import primer.intento.time.passenger.application.port.in.GetPassengerUseCase;
import primer.intento.time.passenger.application.port.out.PassengerRepositoryPort;
import primer.intento.time.passenger.domain.exception.PassengerNotFoundException;
import primer.intento.time.passenger.domain.model.Passenger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService implements CreatePassengerUseCase, GetPassengerUseCase {

    private final PassengerRepositoryPort passengerRepositoryPort;

    public PassengerService(PassengerRepositoryPort passengerRepositoryPort) {
        this.passengerRepositoryPort = passengerRepositoryPort;
    }

    @Override
    public Passenger createPassenger(CreatePassengerCommand command) {
        Passenger passenger = new Passenger(
                null,
                command.firstName(),
                command.lastName(),
                command.email(),
                command.documentNumber()
        );
        return passengerRepositoryPort.save(passenger);
    }

    @Override
    public Passenger getById(Long id) {
        return passengerRepositoryPort.findById(id)
                .orElseThrow(() -> new PassengerNotFoundException(id));
    }

    @Override
    public Passenger getByEmail(String email) {
        return passengerRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new PassengerNotFoundException(email));
    }

    @Override
    public List<Passenger> getAll() {
        return passengerRepositoryPort.findAll();
    }
}