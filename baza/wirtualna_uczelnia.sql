-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 20 Cze 2022, 21:59
-- Wersja serwera: 10.4.19-MariaDB
-- Wersja PHP: 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `wirtualna_uczelnia`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `administratorzy`
--

CREATE TABLE `administratorzy` (
  `id_administratora` int(11) NOT NULL,
  `imie` varchar(255) COLLATE utf8mb4_polish_ci NOT NULL,
  `nazwisko` varchar(255) COLLATE utf8mb4_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Zrzut danych tabeli `administratorzy`
--

INSERT INTO `administratorzy` (`id_administratora`, `imie`, `nazwisko`) VALUES
(1, 'Jan', 'Kowalski');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `ankiety`
--

CREATE TABLE `ankiety` (
  `id_ankiety` int(11) NOT NULL,
  `id_zestawu_pytan` int(11) NOT NULL,
  `id_zajec` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `kierunki`
--

CREATE TABLE `kierunki` (
  `id_kierunku` int(11) NOT NULL,
  `nazwa` varchar(255) COLLATE utf8mb4_polish_ci NOT NULL,
  `rocznik` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Zrzut danych tabeli `kierunki`
--

INSERT INTO `kierunki` (`id_kierunku`, `nazwa`, `rocznik`) VALUES
(1, 'Informatyka', 2019),
(2, 'Informatyka', 2020),
(3, 'Informatyka', 2021),
(4, 'Informatyka', 2022),
(5, 'Grafika komputerowa', 2019),
(6, 'Grafika komputerowa', 2020),
(7, 'Grafika komputerowa', 2021),
(8, 'Matematyka', 2019),
(9, 'Matematyka', 2020),
(10, 'Matematyka', 2021),
(11, 'Matematyka', 2022),
(12, 'Biologia', 2019),
(13, 'Biologia', 2020),
(14, 'Biologia', 2021),
(15, 'Biologia', 2022);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `odpowiedzi`
--

CREATE TABLE `odpowiedzi` (
  `id_odpowiedzi` int(11) NOT NULL,
  `tresc` text COLLATE utf8mb4_polish_ci NOT NULL,
  `id_pytania` int(11) NOT NULL,
  `id_zestawu_odpowiedzi` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `ogloszenia`
--

CREATE TABLE `ogloszenia` (
  `id_ogloszenia` int(11) NOT NULL,
  `tytul` varchar(255) COLLATE utf8mb4_polish_ci NOT NULL,
  `tresc` text COLLATE utf8mb4_polish_ci NOT NULL,
  `id_uzytkownika` int(11) NOT NULL,
  `dla_wszystkich` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Zrzut danych tabeli `ogloszenia`
--

INSERT INTO `ogloszenia` (`id_ogloszenia`, `tytul`, `tresc`, `id_uzytkownika`, `dla_wszystkich`) VALUES
(1, 'Kolokwium', 'I Kolokwium odbędzie się 12.04 w sali 201 o godzinie 12:30.\r\nPozdrawiam', 1, 0),
(2, 'Kolokwium', 'H Kolokwium odbędzie się 12.04 w sali 201 o godzinie 12:30.\r\nPozdrawiam', 1, 0),
(3, 'Kolokwium', 'G Kolokwium odbędzie się 12.04 w sali 201 o godzinie 12:30.\r\nPozdrawiam', 1, 0),
(4, 'Kolokwium', 'F Kolokwium odbędzie się 12.04 w sali 201 o godzinie 12:30.\r\nPozdrawiam', 1, 0),
(5, 'Kolokwium', 'E Kolokwium odbędzie się 12.04 w sali 201 o godzinie 12:30.\r\nPozdrawiam', 1, 0),
(6, 'Kolokwium', 'D Kolokwium odbędzie się 12.04 w sali 201 o godzinie 12:30.\r\nPozdrawiam', 1, 1),
(7, 'Kolokwium', 'C Kolokwium odbędzie się 12.04 w sali 201 o godzinie 12:30.\r\nPozdrawiam', 1, 1),
(8, 'Kolokwium', 'B Kolokwium odbędzie się 12.04 w sali 201 o godzinie 12:30.\r\nPozdrawiam', 1, 1),
(9, 'Kolokwium', 'A kolokwium odbędzie się 12.04 w sali 201 o godzinie 12:30.\r\nPozdrawiam', 1, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `ogloszenia_kierunki`
--

CREATE TABLE `ogloszenia_kierunki` (
  `id_ogloszenia` int(11) NOT NULL,
  `id_kierunku` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Zrzut danych tabeli `ogloszenia_kierunki`
--

INSERT INTO `ogloszenia_kierunki` (`id_ogloszenia`, `id_kierunku`) VALUES
(1, 1),
(2, 12);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `ogloszenia_zajecia`
--

CREATE TABLE `ogloszenia_zajecia` (
  `id_ogloszenia` int(11) NOT NULL,
  `id_zajec` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Zrzut danych tabeli `ogloszenia_zajecia`
--

INSERT INTO `ogloszenia_zajecia` (`id_ogloszenia`, `id_zajec`) VALUES
(3, 1),
(4, 13),
(5, 9);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `prowadzacy`
--

CREATE TABLE `prowadzacy` (
  `id_prowadzacego` int(11) NOT NULL,
  `imie` varchar(255) COLLATE utf8mb4_polish_ci NOT NULL,
  `nazwisko` varchar(255) COLLATE utf8mb4_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Zrzut danych tabeli `prowadzacy`
--

INSERT INTO `prowadzacy` (`id_prowadzacego`, `imie`, `nazwisko`) VALUES
(11, 'Anatol', 'Walczak'),
(14, 'Alisa', 'Janin'),
(15, 'Amanda', 'Zos'),
(18, 'Anita', 'Wolkas');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `przedmioty`
--

CREATE TABLE `przedmioty` (
  `id_przedmiotu` int(11) NOT NULL,
  `nazwa` varchar(255) COLLATE utf8mb4_polish_ci NOT NULL,
  `ects` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Zrzut danych tabeli `przedmioty`
--

INSERT INTO `przedmioty` (`id_przedmiotu`, `nazwa`, `ects`) VALUES
(1, 'Programowanie zespołowe', 6),
(2, 'AiSD', 6),
(3, 'Język C', 6),
(4, 'Język Java', 6),
(5, 'Języki i paradygmaty programowania', 6),
(6, 'Bazy danych', 6),
(7, 'Wykład monograficzny', 6),
(8, 'Sieci internetowe', 3),
(9, 'Języki skryptowe', 3),
(11, 'Podstawy Przedsiębiorczości', 3),
(12, 'Sieci sematyczne', 3),
(13, 'Eksploracja danych internetowych', 3),
(14, 'PII', 6),
(15, 'ASK', 3),
(16, 'Aplikacje ', 3),
(17, 'WZWI', 3),
(18, 'Seminarium Dyplomowe', 3);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `pytania`
--

CREATE TABLE `pytania` (
  `id_pytania` int(11) NOT NULL,
  `id_zestawu_pytan` int(11) NOT NULL,
  `tresc` text COLLATE utf8mb4_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `studenci`
--

CREATE TABLE `studenci` (
  `id_studenta` int(11) NOT NULL,
  `id_kierunku` int(11) NOT NULL,
  `imie` varchar(255) COLLATE utf8mb4_polish_ci NOT NULL,
  `nazwisko` varchar(255) COLLATE utf8mb4_polish_ci NOT NULL,
  `semestr` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Zrzut danych tabeli `studenci`
--

INSERT INTO `studenci` (`id_studenta`, `id_kierunku`, `imie`, `nazwisko`, `semestr`) VALUES
(2, 1, 'Mariusz', 'Polak', 6),
(3, 1, 'Leonardo', 'Las', 1),
(4, 1, 'Kajetan', 'Amas', 1),
(5, 1, 'Bolesław', 'Lom', 1),
(6, 1, 'Kazimierz', 'Sop', 2),
(7, 1, 'Filip', 'Zims', 2),
(8, 1, 'Korneliusz', 'Walczak', 2),
(9, 1, 'Kondrad', 'Walczt', 2),
(10, 1, 'Jakub', 'Jases', 3),
(12, 1, 'Weronika', 'Mak', 3),
(13, 1, 'Lara', 'Gol', 3),
(16, 1, 'Lila', 'Jope', 3),
(17, 1, 'Wioletta', 'Wakursa', 4),
(19, 1, 'Irena', 'Wakser', 4),
(20, 1, 'Janina', 'Jano', 4);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `uzytkownicy`
--

CREATE TABLE `uzytkownicy` (
  `id_uzytkownika` int(11) NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_polish_ci NOT NULL,
  `haslo` varchar(60) COLLATE utf8mb4_polish_ci NOT NULL,
  `zarchiwizowany` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Zrzut danych tabeli `uzytkownicy`
--

INSERT INTO `uzytkownicy` (`id_uzytkownika`, `email`, `haslo`, `zarchiwizowany`) VALUES
(1, 'jk000001@admin.studia.edu.pl', '21232f297a57a5a743894a0e4a801fc3', 0),
(2, 'mp000002@student.studia.edu.pl', 'a4054b18b2e7f8c3688bbaa418323baf', 0),
(3, 'll000003@student.studia.edu.pl', '020e60c6a84db8c5d4c2d56a4e4fe082', 0),
(4, 'ka000004@student.studia.edu.pl', '16d46fa817924166a6ea60a673df728d', 0),
(5, 'bl000005@student.studia.edu.pl', '57f8c5a6b88cd334c67355752572ea8d', 0),
(6, 'ks000006@student.studia.edu.pl', '7515adc96b9dfaa871f70fd658750efb', 0),
(7, 'fz000007@student.studia.edu.pl', '3a30e08c1a8b88386d984e7708fee71e', 0),
(8, 'kw000008@student.studia.edu.pl', '865b61b0761a2be59d6c621fe5d58760', 0),
(9, 'kw000009@student.studia.edu.pl', 'de3ff6a19eb030a257e867012e2a4061', 0),
(10, 'jj000010@student.studia.edu.pl', 'eccdacd4709395e97e6b19756c7b45c1', 0),
(11, 'aw000011@prac.studia.edu.pl', '18807d9ff40b7f842212aa860fa6a1a4', 0),
(12, 'wm000012@student.studia.edu.pl', '2c4c8979a63e1eb71c23ae173a60dd0c', 0),
(13, 'lg000013@student.studia.edu.pl', 'd3c327c84f809a5330bbf0d74438500c', 0),
(14, 'aj000014@prac.studia.edu.pl', '0e84db373d02b6beaa5b05510fb7a9a1', 0),
(15, 'az000015@prac.studia.edu.pl', '6209804952225ab3d14348307b5a4a27', 0),
(16, 'lj000016@student.studia.edu.pl', 'fda6ef9f6ba8382c875468cd70d33ecf', 0),
(17, 'ww000017@student.studia.edu.pl', '60b792de21111b35c84728e1a4355c3e', 0),
(18, 'aw000018@prac.studia.edu.pl', '83349cbdac695f3943635a4fd1aaa7d0', 0),
(19, 'iw000019@student.studia.edu.pl', '4feaf7069df098a519b1aa5c5b065ec5', 0),
(20, 'jj000020@student.studia.edu.pl', '5fc127a8af156fc0ae394940d7efd0f4', 0);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `zajecia`
--

CREATE TABLE `zajecia` (
  `id_zajec` int(11) NOT NULL,
  `id_przedmiotu` int(11) NOT NULL,
  `id_prowadzacego` int(11) NOT NULL,
  `id_kierunku` int(11) NOT NULL,
  `forma_zajec` varchar(255) COLLATE utf8mb4_polish_ci NOT NULL,
  `forma_zaliczenia` varchar(255) COLLATE utf8mb4_polish_ci NOT NULL,
  `liczba_godzin` int(11) NOT NULL,
  `semestr` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Zrzut danych tabeli `zajecia`
--

INSERT INTO `zajecia` (`id_zajec`, `id_przedmiotu`, `id_prowadzacego`, `id_kierunku`, `forma_zajec`, `forma_zaliczenia`, `liczba_godzin`, `semestr`) VALUES
(1, 1, 11, 1, 'Wykład', 'Zaliczenie', 15, 6),
(2, 1, 11, 1, 'Ćwiczenia laboratoryjne', 'Zaliczenie na ocenę', 30, 6),
(3, 1, 11, 1, 'Ćwiczenia projektowe', 'Zaliczenie na ocenę', 15, 6),
(4, 13, 14, 1, 'Wykład', 'Egzamin', 0, 6),
(5, 13, 14, 1, 'Wykład', 'Zaliczenie', 15, 6),
(6, 13, 14, 1, 'Ćwiczenia laboratoryjne', 'Zaliczenie na ocenę', 30, 6),
(7, 12, 15, 1, 'Wykład', 'Egzamin', 0, 6),
(8, 12, 15, 1, 'Wykład', 'Zaliczenie', 15, 4),
(9, 12, 15, 1, 'Ćwiczenia laboratoryjne', 'Zaliczenie na ocenę', 30, 4),
(10, 18, 18, 1, 'Seminarium', 'Zaliczenie', 30, 2),
(11, 5, 11, 1, 'Wykład', 'Zaliczenie', 30, 3),
(12, 5, 11, 1, 'Ćwiczenia laboratoryjne', 'Zaliczenie na ocenę', 30, 3),
(13, 11, 14, 1, 'Wykład', 'Zaliczenie', 15, 1),
(14, 11, 11, 1, 'Ćwiczenia audytoryjne', 'Zaliczenie na ocenę', 15, 1),
(15, 7, 18, 1, 'Wykład', 'Zaliczenie', 15, 5),
(16, 7, 18, 1, 'Ćwiczenia audytoryjne', 'Zaliczenie na ocenę', 30, 5);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `zaliczenia`
--

CREATE TABLE `zaliczenia` (
  `id_zaliczenia` int(11) NOT NULL,
  `id_zajec` int(11) NOT NULL,
  `id_studenta` int(11) NOT NULL,
  `ocena` varchar(11) COLLATE utf8mb4_polish_ci NOT NULL,
  `data` date NOT NULL,
  `ocena_poprawa` varchar(11) COLLATE utf8mb4_polish_ci DEFAULT NULL,
  `data_poprawa` date DEFAULT NULL,
  `ocena_komis` varchar(11) COLLATE utf8mb4_polish_ci DEFAULT NULL,
  `data_komis` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Zrzut danych tabeli `zaliczenia`
--

INSERT INTO `zaliczenia` (`id_zaliczenia`, `id_zajec`, `id_studenta`, `ocena`, `data`, `ocena_poprawa`, `data_poprawa`, `ocena_komis`, `data_komis`) VALUES
(1, 1, 2, 'zal', '2022-06-27', NULL, NULL, NULL, NULL),
(2, 2, 2, '4.0', '2022-06-27', NULL, NULL, NULL, NULL),
(3, 3, 2, '3.5', '2022-06-25', NULL, NULL, NULL, NULL),
(4, 5, 2, 'nzal', '2022-06-27', 'zal', '2022-07-12', NULL, NULL),
(5, 4, 2, '2.0', '2022-06-27', '3.5', '2022-07-12', NULL, NULL),
(6, 6, 2, '5.0', '2022-06-22', NULL, NULL, NULL, NULL),
(7, 7, 2, 'zal', '2022-06-29', NULL, NULL, NULL, NULL),
(8, 8, 2, 'zal', '2022-06-29', NULL, NULL, NULL, NULL),
(9, 9, 2, '3.0', '2022-06-24', NULL, NULL, NULL, NULL),
(10, 10, 2, 'zal', '2022-06-29', NULL, NULL, NULL, NULL),
(11, 11, 2, 'nzal', '2022-06-29', 'nzal', '2022-07-13', 'zal', '2022-09-19'),
(12, 12, 2, '2.0', '2022-06-28', '5.0', '2022-07-11', NULL, NULL),
(13, 13, 2, 'zal', '2022-06-28', NULL, NULL, NULL, NULL),
(14, 14, 2, '5.0', '2022-06-25', NULL, NULL, NULL, NULL),
(15, 15, 2, 'zal', '2022-06-25', NULL, NULL, NULL, NULL),
(16, 16, 2, '4.5', '2022-06-25', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `zestawy_pytan`
--

CREATE TABLE `zestawy_pytan` (
  `id_zestawu_pytan` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `zestaw_odpowiedzi`
--

CREATE TABLE `zestaw_odpowiedzi` (
  `id_zestawu_odpowiedzi` int(11) NOT NULL,
  `id_studenta` int(11) NOT NULL,
  `id_ankiety` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `administratorzy`
--
ALTER TABLE `administratorzy`
  ADD PRIMARY KEY (`id_administratora`);

--
-- Indeksy dla tabeli `ankiety`
--
ALTER TABLE `ankiety`
  ADD PRIMARY KEY (`id_ankiety`),
  ADD KEY `id_zajec` (`id_zajec`),
  ADD KEY `id_zestawu_pytan` (`id_zestawu_pytan`);

--
-- Indeksy dla tabeli `kierunki`
--
ALTER TABLE `kierunki`
  ADD PRIMARY KEY (`id_kierunku`);

--
-- Indeksy dla tabeli `odpowiedzi`
--
ALTER TABLE `odpowiedzi`
  ADD PRIMARY KEY (`id_odpowiedzi`),
  ADD KEY `id_pytania` (`id_pytania`),
  ADD KEY `id_zestawu_odpowiedzi` (`id_zestawu_odpowiedzi`);

--
-- Indeksy dla tabeli `ogloszenia`
--
ALTER TABLE `ogloszenia`
  ADD PRIMARY KEY (`id_ogloszenia`),
  ADD KEY `id_uzytkownika` (`id_uzytkownika`);

--
-- Indeksy dla tabeli `ogloszenia_kierunki`
--
ALTER TABLE `ogloszenia_kierunki`
  ADD PRIMARY KEY (`id_ogloszenia`,`id_kierunku`),
  ADD KEY `id_kierunku` (`id_kierunku`);

--
-- Indeksy dla tabeli `ogloszenia_zajecia`
--
ALTER TABLE `ogloszenia_zajecia`
  ADD PRIMARY KEY (`id_ogloszenia`,`id_zajec`),
  ADD KEY `id_zajec` (`id_zajec`);

--
-- Indeksy dla tabeli `prowadzacy`
--
ALTER TABLE `prowadzacy`
  ADD PRIMARY KEY (`id_prowadzacego`);

--
-- Indeksy dla tabeli `przedmioty`
--
ALTER TABLE `przedmioty`
  ADD PRIMARY KEY (`id_przedmiotu`),
  ADD UNIQUE KEY `nazwa` (`nazwa`);

--
-- Indeksy dla tabeli `pytania`
--
ALTER TABLE `pytania`
  ADD PRIMARY KEY (`id_pytania`),
  ADD KEY `id_zestawu_pytan` (`id_zestawu_pytan`);

--
-- Indeksy dla tabeli `studenci`
--
ALTER TABLE `studenci`
  ADD PRIMARY KEY (`id_studenta`),
  ADD KEY `id_kierunku` (`id_kierunku`);

--
-- Indeksy dla tabeli `uzytkownicy`
--
ALTER TABLE `uzytkownicy`
  ADD PRIMARY KEY (`id_uzytkownika`),
  ADD UNIQUE KEY `unique email` (`email`);

--
-- Indeksy dla tabeli `zajecia`
--
ALTER TABLE `zajecia`
  ADD PRIMARY KEY (`id_zajec`),
  ADD KEY `id_kierunku` (`id_kierunku`),
  ADD KEY `id_prowadzacego` (`id_prowadzacego`),
  ADD KEY `id_przedmiotu` (`id_przedmiotu`);

--
-- Indeksy dla tabeli `zaliczenia`
--
ALTER TABLE `zaliczenia`
  ADD PRIMARY KEY (`id_zaliczenia`),
  ADD KEY `id_zajec` (`id_zajec`),
  ADD KEY `id_studenta` (`id_studenta`);

--
-- Indeksy dla tabeli `zestawy_pytan`
--
ALTER TABLE `zestawy_pytan`
  ADD PRIMARY KEY (`id_zestawu_pytan`);

--
-- Indeksy dla tabeli `zestaw_odpowiedzi`
--
ALTER TABLE `zestaw_odpowiedzi`
  ADD PRIMARY KEY (`id_zestawu_odpowiedzi`),
  ADD KEY `id_ankiety` (`id_ankiety`),
  ADD KEY `id_studenta` (`id_studenta`);

--
-- AUTO_INCREMENT dla zrzuconych tabel
--

--
-- AUTO_INCREMENT dla tabeli `ankiety`
--
ALTER TABLE `ankiety`
  MODIFY `id_ankiety` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `kierunki`
--
ALTER TABLE `kierunki`
  MODIFY `id_kierunku` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT dla tabeli `odpowiedzi`
--
ALTER TABLE `odpowiedzi`
  MODIFY `id_odpowiedzi` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `ogloszenia`
--
ALTER TABLE `ogloszenia`
  MODIFY `id_ogloszenia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT dla tabeli `przedmioty`
--
ALTER TABLE `przedmioty`
  MODIFY `id_przedmiotu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT dla tabeli `pytania`
--
ALTER TABLE `pytania`
  MODIFY `id_pytania` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `uzytkownicy`
--
ALTER TABLE `uzytkownicy`
  MODIFY `id_uzytkownika` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT dla tabeli `zajecia`
--
ALTER TABLE `zajecia`
  MODIFY `id_zajec` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT dla tabeli `zaliczenia`
--
ALTER TABLE `zaliczenia`
  MODIFY `id_zaliczenia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT dla tabeli `zestawy_pytan`
--
ALTER TABLE `zestawy_pytan`
  MODIFY `id_zestawu_pytan` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `zestaw_odpowiedzi`
--
ALTER TABLE `zestaw_odpowiedzi`
  MODIFY `id_zestawu_odpowiedzi` int(11) NOT NULL AUTO_INCREMENT;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `administratorzy`
--
ALTER TABLE `administratorzy`
  ADD CONSTRAINT `administratorzy_ibfk_1` FOREIGN KEY (`id_administratora`) REFERENCES `uzytkownicy` (`id_uzytkownika`);

--
-- Ograniczenia dla tabeli `ankiety`
--
ALTER TABLE `ankiety`
  ADD CONSTRAINT `ankiety_ibfk_1` FOREIGN KEY (`id_zajec`) REFERENCES `zajecia` (`id_zajec`),
  ADD CONSTRAINT `ankiety_ibfk_2` FOREIGN KEY (`id_zestawu_pytan`) REFERENCES `zestawy_pytan` (`id_zestawu_pytan`);

--
-- Ograniczenia dla tabeli `odpowiedzi`
--
ALTER TABLE `odpowiedzi`
  ADD CONSTRAINT `odpowiedzi_ibfk_1` FOREIGN KEY (`id_pytania`) REFERENCES `pytania` (`id_pytania`),
  ADD CONSTRAINT `odpowiedzi_ibfk_2` FOREIGN KEY (`id_zestawu_odpowiedzi`) REFERENCES `zestaw_odpowiedzi` (`id_zestawu_odpowiedzi`);

--
-- Ograniczenia dla tabeli `ogloszenia`
--
ALTER TABLE `ogloszenia`
  ADD CONSTRAINT `ogloszenia_ibfk_1` FOREIGN KEY (`id_uzytkownika`) REFERENCES `uzytkownicy` (`id_uzytkownika`);

--
-- Ograniczenia dla tabeli `ogloszenia_kierunki`
--
ALTER TABLE `ogloszenia_kierunki`
  ADD CONSTRAINT `ogloszenia_kierunki_ibfk_1` FOREIGN KEY (`id_kierunku`) REFERENCES `kierunki` (`id_kierunku`),
  ADD CONSTRAINT `ogloszenia_kierunki_ibfk_2` FOREIGN KEY (`id_ogloszenia`) REFERENCES `ogloszenia` (`id_ogloszenia`);

--
-- Ograniczenia dla tabeli `ogloszenia_zajecia`
--
ALTER TABLE `ogloszenia_zajecia`
  ADD CONSTRAINT `ogloszenia_zajecia_ibfk_1` FOREIGN KEY (`id_ogloszenia`) REFERENCES `ogloszenia` (`id_ogloszenia`),
  ADD CONSTRAINT `ogloszenia_zajecia_ibfk_2` FOREIGN KEY (`id_zajec`) REFERENCES `zajecia` (`id_zajec`);

--
-- Ograniczenia dla tabeli `prowadzacy`
--
ALTER TABLE `prowadzacy`
  ADD CONSTRAINT `prowadzacy_ibfk_1` FOREIGN KEY (`id_prowadzacego`) REFERENCES `uzytkownicy` (`id_uzytkownika`);

--
-- Ograniczenia dla tabeli `pytania`
--
ALTER TABLE `pytania`
  ADD CONSTRAINT `pytania_ibfk_1` FOREIGN KEY (`id_zestawu_pytan`) REFERENCES `zestawy_pytan` (`id_zestawu_pytan`);

--
-- Ograniczenia dla tabeli `studenci`
--
ALTER TABLE `studenci`
  ADD CONSTRAINT `studenci_ibfk_1` FOREIGN KEY (`id_kierunku`) REFERENCES `kierunki` (`id_kierunku`),
  ADD CONSTRAINT `studenci_ibfk_2` FOREIGN KEY (`id_studenta`) REFERENCES `uzytkownicy` (`id_uzytkownika`);

--
-- Ograniczenia dla tabeli `zajecia`
--
ALTER TABLE `zajecia`
  ADD CONSTRAINT `zajecia_ibfk_1` FOREIGN KEY (`id_kierunku`) REFERENCES `kierunki` (`id_kierunku`),
  ADD CONSTRAINT `zajecia_ibfk_2` FOREIGN KEY (`id_prowadzacego`) REFERENCES `prowadzacy` (`id_prowadzacego`),
  ADD CONSTRAINT `zajecia_ibfk_3` FOREIGN KEY (`id_przedmiotu`) REFERENCES `przedmioty` (`id_przedmiotu`);

--
-- Ograniczenia dla tabeli `zaliczenia`
--
ALTER TABLE `zaliczenia`
  ADD CONSTRAINT `zaliczenia_ibfk_1` FOREIGN KEY (`id_zajec`) REFERENCES `zajecia` (`id_zajec`),
  ADD CONSTRAINT `zaliczenia_ibfk_2` FOREIGN KEY (`id_studenta`) REFERENCES `studenci` (`id_studenta`);

--
-- Ograniczenia dla tabeli `zestaw_odpowiedzi`
--
ALTER TABLE `zestaw_odpowiedzi`
  ADD CONSTRAINT `zestaw_odpowiedzi_ibfk_1` FOREIGN KEY (`id_ankiety`) REFERENCES `ankiety` (`id_ankiety`),
  ADD CONSTRAINT `zestaw_odpowiedzi_ibfk_2` FOREIGN KEY (`id_studenta`) REFERENCES `studenci` (`id_studenta`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
