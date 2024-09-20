CREATE TABLE people (
id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    has_license BOOLEAN NOT NULL
);

CREATE TABLE cars (
    id SERIAL PRIMARY KEY,
    brand VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE people_cars (
    person_id INT REFERENCES people(person_id),
    car_id INT REFERENCES cars(car_id),
    PRIMARY KEY (person_id, car_id)
);