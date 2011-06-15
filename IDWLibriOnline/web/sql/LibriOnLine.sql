-- phpMyAdmin SQL Dump
-- version 3.3.7deb5build0.10.10.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generato il: 15 giu, 2011 at 05:13 PM
-- Versione MySQL: 5.1.49
-- Versione PHP: 5.3.3-1ubuntu9.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `LibriOnLine`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `autore`
--

CREATE TABLE IF NOT EXISTS `autore` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dump dei dati per la tabella `autore`
--


-- --------------------------------------------------------

--
-- Struttura della tabella `copiaelettronica`
--

CREATE TABLE IF NOT EXISTS `copiaelettronica` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mimetype` varchar(25) NOT NULL,
  `url` varchar(50) NOT NULL,
  `libro` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `libro` (`libro`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dump dei dati per la tabella `copiaelettronica`
--


-- --------------------------------------------------------

--
-- Struttura della tabella `gruppo`
--

CREATE TABLE IF NOT EXISTS `gruppo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gruppo` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dump dei dati per la tabella `gruppo`
--

INSERT INTO `gruppo` (`id`, `gruppo`) VALUES
(1, 'bibliotecario'),
(2, 'registrato');

-- --------------------------------------------------------

--
-- Struttura della tabella `gruppo_servizio`
--

CREATE TABLE IF NOT EXISTS `gruppo_servizio` (
  `gruppo` int(11) NOT NULL,
  `servizio` int(11) NOT NULL,
  PRIMARY KEY (`gruppo`,`servizio`),
  KEY `servizio` (`servizio`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `gruppo_servizio`
--


-- --------------------------------------------------------

--
-- Struttura della tabella `libro`
--

CREATE TABLE IF NOT EXISTS `libro` (
  `isbn` varchar(13) NOT NULL,
  `titolo` varchar(50) NOT NULL,
  `editore` varchar(25) DEFAULT NULL,
  `annoPubblicazione` int(4) DEFAULT NULL,
  `recensione` text,
  `lingua` int(11) DEFAULT NULL,
  `data_ins` date NOT NULL,
  PRIMARY KEY (`isbn`),
  KEY `lingua` (`lingua`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `libro`
--

INSERT INTO `libro` (`isbn`, `titolo`, `editore`, `annoPubblicazione`, `recensione`, `lingua`, `data_ins`) VALUES
('9788850329649', 'Sviluppare siti con gli standard web', NULL, 2010, 'Questo libro (tutto a colori) si rivolge a progettisti web, sviluppatori software, imprenditori e manager che vogliono disporre di siti web a costi ridotti, che funzionino al meglio e che siano in grado di raggiungere il maggior numero di persone, e tutto questo non solo tenendo conto di browser, dispositivi mobili e altri sistemi di visualizzazione presenti al momento, ma anche di apparecchiature che l’evoluzione tecnologica saprà proporre in futuro.\r\n\r\nChi lavora sul Web conosce bene l’obsolescenza che caratterizza lo sviluppo frenetico della tecnologia. Si realizzano siti solo per ricostruirli continuamente. Accade troppo spesso che la ricostruzione di un sito debba essere effettuata non tanto per aumentare o perfezionare le funzionalità di accesso e navigazione, ma semplicemente per tenere il passo di browser e dispositivi che sembrano sempre più avanzati dei cicli di design e sviluppo di un sito web. \r\n\r\nQuesta situazione è così consolidata da apparire più che normale, al punto da costituire il prezzo da pagare per il corretto mantenimento di un sito professionale. È altrettanto vero però che si tratta di un prezzo sempre più difficile da affrontare.\r\n\r\nGli standard web permettono di porre fine a tutto questo.', NULL, '0000-00-00');

-- --------------------------------------------------------

--
-- Struttura della tabella `libro_autore`
--

CREATE TABLE IF NOT EXISTS `libro_autore` (
  `libro` varchar(10) NOT NULL,
  `autore` int(11) NOT NULL,
  PRIMARY KEY (`libro`,`autore`),
  KEY `autore` (`autore`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `libro_autore`
--


-- --------------------------------------------------------

--
-- Struttura della tabella `libro_tag`
--

CREATE TABLE IF NOT EXISTS `libro_tag` (
  `libro` varchar(10) NOT NULL,
  `tag` int(11) NOT NULL,
  PRIMARY KEY (`libro`,`tag`),
  KEY `tag` (`tag`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `libro_tag`
--


-- --------------------------------------------------------

--
-- Struttura della tabella `lingua`
--

CREATE TABLE IF NOT EXISTS `lingua` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lingua` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dump dei dati per la tabella `lingua`
--


-- --------------------------------------------------------

--
-- Struttura della tabella `prestito`
--

CREATE TABLE IF NOT EXISTS `prestito` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dataPrestito` date NOT NULL,
  `dataRestituzione` date NOT NULL,
  `restituito` tinyint(1) NOT NULL DEFAULT '0',
  `volume` int(11) NOT NULL,
  `user` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `volume` (`volume`,`user`),
  KEY `user` (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dump dei dati per la tabella `prestito`
--


-- --------------------------------------------------------

--
-- Struttura della tabella `servizio`
--

CREATE TABLE IF NOT EXISTS `servizio` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `servizio` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dump dei dati per la tabella `servizio`
--


-- --------------------------------------------------------

--
-- Struttura della tabella `stato`
--

CREATE TABLE IF NOT EXISTS `stato` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stato` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dump dei dati per la tabella `stato`
--


-- --------------------------------------------------------

--
-- Struttura della tabella `tag`
--

CREATE TABLE IF NOT EXISTS `tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tag` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dump dei dati per la tabella `tag`
--


-- --------------------------------------------------------

--
-- Struttura della tabella `traduzione`
--

CREATE TABLE IF NOT EXISTS `traduzione` (
  `libro1` varchar(10) NOT NULL,
  `libro2` varchar(10) NOT NULL,
  PRIMARY KEY (`libro1`,`libro2`),
  KEY `libro2` (`libro2`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `traduzione`
--


-- --------------------------------------------------------

--
-- Struttura della tabella `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(25) NOT NULL,
  `password` varchar(32) NOT NULL,
  `email` varchar(25) DEFAULT NULL,
  `telefono` varchar(25) DEFAULT NULL,
  `nome` varchar(25) NOT NULL,
  `cognome` varchar(25) NOT NULL,
  `codiceFiscale` varchar(16) NOT NULL,
  `indirizzo` varchar(50) NOT NULL,
  `citta` varchar(50) NOT NULL,
  `provincia` varchar(2) NOT NULL,
  `cap` int(5) NOT NULL,
  `gruppo` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `gruppo` (`gruppo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dump dei dati per la tabella `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `email`, `telefono`, `nome`, `cognome`, `codiceFiscale`, `indirizzo`, `citta`, `provincia`, `cap`, `gruppo`) VALUES
(1, 'zilfio', '8759ded2b55ddaf88bc34a4625bc1ae7', 'zilfio88@gmail.com', '3487145593', 'Silvio', 'D''Orazio', 'DRZSLV88L24A515D', 'Via Fiume, 99', 'Roccavivi', 'AQ', 67050, 1),
(2, 'alessio', 'd2462e55381a20059ed811cefd42493e', 'alessio@gmail.com', '390939239', 'Alessio', 'Felicioni', 'JCJNCNRENCREJNCR', 'Via Fiume, 9', 'Roccavivi', 'AQ', 67050, NULL),
(4, 'paperino', 'paperina', 'paperino@pluto.it', '12341234', 'Nome', 'Cognome', 'per123te5g51', 'Via Roma', 'Paperopoli', 'Pa', 12412, 2),
(5, 'giacomolm', 'dcc4ed45e6d3fb1c13044163a464b44a', 'giacomolm@hotmail.it', '3201534917', 'Giacomo', 'Lamonaco', 'LMNGCM89L22G141E', 'via san migliazzo 21', 'Miglianico', 'CH', 66100, NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella `volume`
--

CREATE TABLE IF NOT EXISTS `volume` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stato` int(11) DEFAULT NULL,
  `libro` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `stato` (`stato`),
  KEY `libro` (`libro`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dump dei dati per la tabella `volume`
--


--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `copiaelettronica`
--
ALTER TABLE `copiaelettronica`
  ADD CONSTRAINT `copiaelettronica_ibfk_1` FOREIGN KEY (`libro`) REFERENCES `libro` (`isbn`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `gruppo_servizio`
--
ALTER TABLE `gruppo_servizio`
  ADD CONSTRAINT `gruppo_servizio_ibfk_1` FOREIGN KEY (`gruppo`) REFERENCES `gruppo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `gruppo_servizio_ibfk_2` FOREIGN KEY (`servizio`) REFERENCES `servizio` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `libro`
--
ALTER TABLE `libro`
  ADD CONSTRAINT `libro_ibfk_1` FOREIGN KEY (`lingua`) REFERENCES `lingua` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Limiti per la tabella `libro_autore`
--
ALTER TABLE `libro_autore`
  ADD CONSTRAINT `libro_autore_ibfk_1` FOREIGN KEY (`libro`) REFERENCES `libro` (`isbn`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `libro_autore_ibfk_2` FOREIGN KEY (`autore`) REFERENCES `autore` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `libro_tag`
--
ALTER TABLE `libro_tag`
  ADD CONSTRAINT `libro_tag_ibfk_1` FOREIGN KEY (`libro`) REFERENCES `libro` (`isbn`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `libro_tag_ibfk_2` FOREIGN KEY (`tag`) REFERENCES `tag` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `prestito`
--
ALTER TABLE `prestito`
  ADD CONSTRAINT `prestito_ibfk_1` FOREIGN KEY (`volume`) REFERENCES `volume` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `prestito_ibfk_2` FOREIGN KEY (`user`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Limiti per la tabella `traduzione`
--
ALTER TABLE `traduzione`
  ADD CONSTRAINT `traduzione_ibfk_1` FOREIGN KEY (`libro1`) REFERENCES `libro` (`isbn`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `traduzione_ibfk_2` FOREIGN KEY (`libro2`) REFERENCES `libro` (`isbn`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`gruppo`) REFERENCES `gruppo` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Limiti per la tabella `volume`
--
ALTER TABLE `volume`
  ADD CONSTRAINT `volume_ibfk_1` FOREIGN KEY (`stato`) REFERENCES `stato` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `volume_ibfk_2` FOREIGN KEY (`libro`) REFERENCES `libro` (`isbn`) ON DELETE CASCADE ON UPDATE CASCADE;