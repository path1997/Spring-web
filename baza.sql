-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 14 Lis 2019, 10:14
-- Wersja serwera: 10.4.6-MariaDB
-- Wersja PHP: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `test`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `car`
--

CREATE TABLE `car` (
  `id` bigint(20) NOT NULL,
  `color` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `mark` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `model` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `price` int(11) NOT NULL,
  `state` int(11) NOT NULL,
  `year` int(11) NOT NULL,
  `rental_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `car`
--

INSERT INTO `car` (`id`, `color`, `mark`, `model`, `price`, `state`, `year`, `rental_id`, `user_id`) VALUES
(9, 'Czarny', 'BMW', 'M5 F90', 600, 0, 2019, 3, 2),
(8, 'Zielony', 'Porsche', '911 GT3 RS', 1000, 0, 2018, 2, 2),
(10, 'Szary', 'Audi', 'RS3', 400, 0, 2018, 3, 2),
(12, '2019', 'Skoda', 'Superb', 300, 0, 2019, 2, 2);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `orders`
--

CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL,
  `data_oddania` datetime DEFAULT NULL,
  `data_wypozyczenia` datetime DEFAULT NULL,
  `car_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `orders`
--

INSERT INTO `orders` (`id`, `data_oddania`, `data_wypozyczenia`, `car_id`, `user_id`) VALUES
(22, '2019-11-11 12:22:23', '2019-11-11 12:22:21', 10, 2),
(21, '2019-11-11 12:22:19', '2019-11-11 12:22:17', 8, 2),
(20, '2019-11-11 12:22:16', '2019-11-11 12:22:13', 9, 2),
(23, '2019-11-11 12:25:28', '2019-11-11 12:25:27', 12, 2),
(24, '2019-11-14 09:48:50', '2019-11-12 11:06:05', 8, 2),
(25, '2019-11-12 11:06:16', '2019-11-12 11:06:07', 12, 2),
(26, '2019-11-12 11:07:02', '2019-11-12 11:06:09', 9, 2),
(27, '2019-11-12 11:06:19', '2019-11-12 11:06:10', 10, 2),
(28, '2019-11-14 09:48:51', '2019-11-12 11:07:05', 10, 2),
(29, '2019-11-14 09:49:00', '2019-11-14 09:48:55', 9, 2),
(30, '2019-11-14 09:49:02', '2019-11-14 09:48:57', 10, 2),
(31, '2019-11-14 10:00:19', '2019-11-14 10:00:07', 9, 2);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `photo`
--

CREATE TABLE `photo` (
  `id` bigint(20) NOT NULL,
  `sciezka` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `car_id` bigint(20) DEFAULT NULL,
  `pozycja` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `photo`
--

INSERT INTO `photo` (`id`, `sciezka`, `car_id`, `pozycja`) VALUES
(29, 'g-power-bmw-m5-f90-1.jpg', 9, 1),
(28, 'p17_1158_a3_rgb.jpg', 8, 3),
(27, 'porsche-gt-ge1.jpg', 8, 2),
(26, 'used-2019-porsche-911-gt3rscoupe-8431-18012404-1-640.jpg', 8, 1),
(30, 'g-power-bmw-m5-f90-3.jpg', 9, 2),
(31, 'maxresdefault.jpg', 9, 3),
(32, 'audi-rs3-sedan-abt-2-f3768ca2eeb.jpg', 10, 1),
(33, '2880-1800-crop-audi-rs3-sportback-8v-2018-c643103012018131118_1.jpg', 10, 2),
(34, 'ff2f028d5803b67e8017c2e87f3ee8a4.jpg', 10, 3),
(38, 'SKODA-SUPERB-2019-3.jpg', 12, 2),
(40, 'SKODA-Superb-2019-FL.jpg', 12, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `rental`
--

CREATE TABLE `rental` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `city` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `rental`
--

INSERT INTO `rental` (`id`, `address`, `city`, `email`, `phone`) VALUES
(1, '', 'Brak', '', ''),
(2, 'Miła 32', 'Grójec', 'grojec@carrental.com', '566654565'),
(3, 'Narutowicza 32', 'Łódź', 'lodz@carrental.com', '566654321');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `admin` int(11) NOT NULL,
  `email` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `fname` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `sname` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `user`
--

INSERT INTO `user` (`id`, `admin`, `email`, `fname`, `password`, `phone`, `sname`) VALUES
(1, 1, '', 'admin', 'admin', '', 'admin'),
(2, 0, 'damian@wp.pl', 'Damian', 'aaaa', '4.65465465465465465e+23', 'Kowalski'),
(3, 1, 'robert@wp.pl', 'Robert', 'aaa', '545554555', 'Kowalski');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `car`
--
ALTER TABLE `car`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKj3q505x6k5oe468g1xwnwdpfh` (`rental_id`),
  ADD KEY `FKja1j4mm4rqlv6cnhgp1qqgtuj` (`user_id`);

--
-- Indeksy dla tabeli `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK8ptpdp8fxan4etjis2wkqg1ea` (`car_id`),
  ADD KEY `FKel9kyl84ego2otj2accfd8mr7` (`user_id`);

--
-- Indeksy dla tabeli `photo`
--
ALTER TABLE `photo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKa8cqhuyx228468h5sqxo5dht5` (`car_id`);

--
-- Indeksy dla tabeli `rental`
--
ALTER TABLE `rental`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `car`
--
ALTER TABLE `car`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT dla tabeli `orders`
--
ALTER TABLE `orders`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT dla tabeli `photo`
--
ALTER TABLE `photo`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT dla tabeli `rental`
--
ALTER TABLE `rental`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT dla tabeli `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
