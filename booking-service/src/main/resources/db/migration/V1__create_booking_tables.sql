CREATE TABLE passengers (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            first_name VARCHAR(100) NOT NULL,
                            last_name VARCHAR(100) NOT NULL,
                            document_type VARCHAR(50) NOT NULL,
                            document_number VARCHAR(50) NOT NULL,
                            age INT NOT NULL
);

CREATE TABLE flight_tickets (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                ticket_number VARCHAR(100) NOT NULL,
                                airline VARCHAR(100) NOT NULL,
                                seat_number VARCHAR(20) NOT NULL,
                                flight_number VARCHAR(50) NOT NULL,
                                flight_time DATETIME NOT NULL,
                                passenger_id BIGINT NOT NULL,
                                CONSTRAINT fk_ticket_passenger FOREIGN KEY (passenger_id) REFERENCES passengers(id)
);

CREATE TABLE lodging_reservations (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      hotel_name VARCHAR(150) NOT NULL,
                                      check_in_date DATE NOT NULL,
                                      check_out_date DATE NOT NULL,
                                      room_type VARCHAR(50) NOT NULL,
                                      passenger_id BIGINT NOT NULL,
                                      CONSTRAINT fk_lodging_passenger FOREIGN KEY (passenger_id) REFERENCES passengers(id)
);