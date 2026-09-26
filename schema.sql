USE cinebook;

DROP TABLE IF EXISTS seats;
DROP TABLE IF EXISTS screens;
DROP TABLE IF EXISTS theatres;
DROP TABLE IF EXISTS movies;

CREATE TABLE movies (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    tmdb_id     INT UNIQUE,
    title       VARCHAR(200) NOT NULL,
    genre       VARCHAR(100),
    language    VARCHAR(50),
    duration    INT,                 -- minutes
    poster_url  VARCHAR(500),
    status      ENUM('NOW_SHOWING', 'COMING_SOON', 'ENDED')
                NOT NULL DEFAULT 'COMING_SOON'
);

CREATE TABLE theatres (
    id       INT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(100) NOT NULL,
    city     VARCHAR(50)  NOT NULL,
    address  VARCHAR(255)
);

CREATE TABLE screens (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    theatre_id  INT NOT NULL,
    name        VARCHAR(50) NOT NULL,
    CONSTRAINT fk_screens_theatre
        FOREIGN KEY (theatre_id) REFERENCES theatres(id),
    CONSTRAINT uq_screen_name
        UNIQUE (theatre_id, name)
);

CREATE TABLE seats (
    id           INT AUTO_INCREMENT PRIMARY KEY,
    screen_id    INT NOT NULL,
    seat_row     VARCHAR(2) NOT NULL,
    seat_number  INT NOT NULL,
    seat_type    ENUM('REGULAR', 'PREMIUM') NOT NULL DEFAULT 'REGULAR',
    CONSTRAINT fk_seats_screen
        FOREIGN KEY (screen_id) REFERENCES screens(id),
    CONSTRAINT uq_seat_position
        UNIQUE (screen_id, seat_row, seat_number)
);

INSERT INTO theatres (name, city, address) VALUES
    ('CineStar Central', 'Mumbai',    'Lower Parel'),
    ('Galaxy Multiplex', 'Bengaluru', 'Koramangala');

INSERT INTO screens (theatre_id, name) VALUES
    (1, 'Screen 1'),
    (1, 'Screen 2'),
    (2, 'Audi 1');

INSERT INTO seats (screen_id, seat_row, seat_number, seat_type) VALUES
    (1, 'A', 1, 'REGULAR'), (1, 'A', 2, 'REGULAR'), (1, 'A', 3, 'REGULAR'),
    (1, 'B', 1, 'PREMIUM'), (1, 'B', 2, 'PREMIUM'),
    (2, 'A', 1, 'REGULAR'), (2, 'A', 2, 'REGULAR'),
    (3, 'A', 1, 'REGULAR'), (3, 'B', 1, 'PREMIUM'), (3, 'B', 2, 'PREMIUM');

INSERT INTO movies (title, genre, language, duration, status) VALUES
    ('Interstellar', 'Sci-Fi', 'English', 169, 'NOW_SHOWING'),
    ('3 Idiots',     'Comedy', 'Hindi',   170, 'NOW_SHOWING'),
    ('Dune Part 3',  'Sci-Fi', 'English', 160, 'COMING_SOON');
  
  SELECT t.name AS theatre,
       s.name AS screen,
       se.seat_row,
       se.seat_number,
       se.seat_type
FROM seats se
JOIN screens  s ON se.screen_id = s.id
JOIN theatres t ON s.theatre_id = t.id
ORDER BY t.name, s.name, se.seat_row, se.seat_number;

DROP TABLE IF EXISTS booked_seats;
DROP TABLE IF EXISTS bookings;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS shows;
-- then the old ones: seats, screens, theatres, movies

CREATE TABLE shows (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    movie_id    INT NOT NULL,
    screen_id   INT NOT NULL,
    start_time  DATETIME NOT NULL,
    price       DECIMAL(8,2) NOT NULL,
    CONSTRAINT fk_shows_movie  FOREIGN KEY (movie_id)  REFERENCES movies(id),
    CONSTRAINT fk_shows_screen FOREIGN KEY (screen_id) REFERENCES screens(id),
    CONSTRAINT uq_screen_time  UNIQUE (screen_id, start_time)
);

CREATE TABLE users (
    id        INT AUTO_INCREMENT PRIMARY KEY,
    name      VARCHAR(100) NOT NULL,
    email     VARCHAR(150) NOT NULL UNIQUE,
    password  VARCHAR(255) NOT NULL,
    role      ENUM('USER', 'ADMIN') NOT NULL DEFAULT 'USER'
);

CREATE TABLE bookings (
    id            INT AUTO_INCREMENT PRIMARY KEY,
    user_id       INT NOT NULL,
    show_id       INT NOT NULL,
    total_amount  DECIMAL(10,2) NOT NULL,
    status        ENUM('PENDING', 'CONFIRMED', 'CANCELLED') NOT NULL DEFAULT 'PENDING',
    created_at    DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_bookings_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_bookings_show FOREIGN KEY (show_id) REFERENCES shows(id)
);

CREATE TABLE booked_seats (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    booking_id  INT NOT NULL,
    show_id     INT NOT NULL,
    seat_id     INT NOT NULL,
    CONSTRAINT fk_bs_booking FOREIGN KEY (booking_id) REFERENCES bookings(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_bs_show    FOREIGN KEY (show_id)    REFERENCES shows(id),
    CONSTRAINT fk_bs_seat    FOREIGN KEY (seat_id)    REFERENCES seats(id),
    CONSTRAINT uq_show_seat  UNIQUE (show_id, seat_id)
);

INSERT INTO shows (movie_id, screen_id, start_time, price) VALUES
    (1, 1, '2026-10-01 18:00:00', 250.00),   -- Interstellar, CineStar Screen 1
    (1, 3, '2026-10-01 21:00:00', 300.00),   -- Interstellar, Galaxy Audi 1
    (2, 2, '2026-10-02 15:00:00', 200.00);   -- 3 Idiots, CineStar Screen 2
-- Movie 3 (Dune) has no show, so it is NOT bookable.

INSERT INTO users (name, email, password, role) VALUES
    ('Astha', 'astha@test.com', 'hash-later', 'USER'),
    ('Admin', 'admin@test.com', 'hash-later', 'ADMIN');

INSERT INTO bookings (user_id, show_id, total_amount, status) VALUES
    (1, 1, 500.00, 'CONFIRMED');

INSERT INTO booked_seats (booking_id, show_id, seat_id) VALUES
    (1, 1, 4),   -- B1
    (1, 1, 5);   -- B2
    

SELECT DISTINCT m.id, m.title
FROM movies m
JOIN shows sh ON sh.movie_id = m.id
WHERE sh.start_time > NOW();

SELECT se.id, se.seat_row, se.seat_number, se.seat_type
FROM shows sh
JOIN seats se          ON se.screen_id = sh.screen_id
LEFT JOIN booked_seats bs ON bs.seat_id = se.id
                         AND bs.show_id = sh.id
WHERE sh.id = 1
  AND bs.id IS NULL;
    
SELECT b.id AS booking_id, u.name, m.title,
       t.name AS theatre, sc.name AS screen, sh.start_time,
       GROUP_CONCAT(CONCAT(se.seat_row, se.seat_number)
                    ORDER BY se.seat_row, se.seat_number) AS seats,
       b.total_amount
FROM bookings b
JOIN users u         ON b.user_id = u.id
JOIN shows sh        ON b.show_id = sh.id
JOIN movies m        ON sh.movie_id = m.id
JOIN screens sc      ON sh.screen_id = sc.id
JOIN theatres t      ON sc.theatre_id = t.id
JOIN booked_seats bs ON bs.booking_id = b.id
JOIN seats se        ON bs.seat_id = se.id
WHERE b.id = 1
GROUP BY b.id, u.name, m.title, t.name, sc.name, sh.start_time, b.total_amount;