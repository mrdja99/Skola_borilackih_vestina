/*
SQLyog Community v13.1.9 (64 bit)
MySQL - 10.4.21-MariaDB : Database - skolaborilackihvestina
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`skolaborilackihvestina` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `skolaborilackihvestina`;

/*Table structure for table `kurs` */

DROP TABLE IF EXISTS `kurs`;

CREATE TABLE `kurs` (
  `KursID` int(10) NOT NULL AUTO_INCREMENT,
  `NazivKursa` varchar(50) DEFAULT NULL,
  `Mesto` varchar(50) DEFAULT NULL,
  `Opis` varchar(100) DEFAULT NULL,
  `VelicinaGrupe` int(10) DEFAULT NULL,
  PRIMARY KEY (`KursID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Data for the table `kurs` */

insert  into `kurs`(`KursID`,`NazivKursa`,`Mesto`,`Opis`,`VelicinaGrupe`) values 
(1,'Rvanje','DIF','Kurs na kome se mogu nauciti osnove rvanja',30),
(2,'Karate','Sportska giimnazija','Jedan od najpopularnijih kurseva u nasoj skoli',20),
(3,'Tekvondo','DIF','Pridruzite nam se ako zelite da postanete olimpijski sampion',10);

/*Table structure for table `polaznik` */

DROP TABLE IF EXISTS `polaznik`;

CREATE TABLE `polaznik` (
  `PolaznikID` int(10) NOT NULL AUTO_INCREMENT,
  `Ime` varchar(50) DEFAULT NULL,
  `Prezime` varchar(50) DEFAULT NULL,
  `Telefon` varchar(50) DEFAULT NULL,
  `Godina` int(10) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `DatumUpisa` date DEFAULT NULL,
  PRIMARY KEY (`PolaznikID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Data for the table `polaznik` */

insert  into `polaznik`(`PolaznikID`,`Ime`,`Prezime`,`Telefon`,`Godina`,`Email`,`DatumUpisa`) values 
(1,'Mirko','Mirkovic','0635553333',25,'mirko@gmail.com','2021-12-12'),
(2,'Ana','Simic','065382345',19,'anasim@gmail.com','2022-05-03'),
(3,'Dragan','Nikolic','060789456',29,'drago@gmail.com','2022-05-12'),
(4,'Mitar','Mitrovic','0655789345',24,'mitar@gmail.com','2022-07-21'),
(5,'John','Johnic','0664537896',28,'john@gmail.com','2022-03-12');

/*Table structure for table `stavkazakazivanjatreninga` */

DROP TABLE IF EXISTS `stavkazakazivanjatreninga`;

CREATE TABLE `stavkazakazivanjatreninga` (
  `ZakazivanjeTreningaID` int(10) NOT NULL,
  `RB` int(10) NOT NULL,
  `KursID` int(10) DEFAULT NULL,
  `TreningID` int(10) DEFAULT NULL,
  PRIMARY KEY (`ZakazivanjeTreningaID`,`RB`),
  KEY `fk_kurs_id` (`KursID`),
  KEY `fk_tg_id` (`TreningID`),
  CONSTRAINT `fk_kurs_id` FOREIGN KEY (`KursID`) REFERENCES `kurs` (`KursID`),
  CONSTRAINT `fk_tg_id` FOREIGN KEY (`TreningID`) REFERENCES `trening` (`TreningID`),
  CONSTRAINT `fk_zt-id` FOREIGN KEY (`ZakazivanjeTreningaID`) REFERENCES `zakazivanjetreninga` (`ZakazivanjeTreningaID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `stavkazakazivanjatreninga` */

insert  into `stavkazakazivanjatreninga`(`ZakazivanjeTreningaID`,`RB`,`KursID`,`TreningID`) values 
(6,1,1,1),
(6,2,2,2);

/*Table structure for table `trener` */

DROP TABLE IF EXISTS `trener`;

CREATE TABLE `trener` (
  `TrenerID` int(10) NOT NULL AUTO_INCREMENT,
  `Ime` varchar(50) DEFAULT NULL,
  `Prezime` varchar(50) DEFAULT NULL,
  `KorisnickoIme` varchar(50) DEFAULT NULL,
  `Lozinka` varchar(50) DEFAULT NULL,
  `Ulogovan` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`TrenerID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Data for the table `trener` */

insert  into `trener`(`TrenerID`,`Ime`,`Prezime`,`KorisnickoIme`,`Lozinka`,`Ulogovan`) values 
(1,'Marko','Markovic','marko','12345',0),
(2,'Nikola','Nikolic','nikola','54321',0),
(3,'Dimitrije','Dimic','dime','23345',0),
(4,'Nenad','Nesovic','nesa','33344',0);

/*Table structure for table `trening` */

DROP TABLE IF EXISTS `trening`;

CREATE TABLE `trening` (
  `TreningID` int(10) NOT NULL AUTO_INCREMENT,
  `DatumVremeTreninga` datetime DEFAULT NULL,
  PRIMARY KEY (`TreningID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `trening` */

insert  into `trening`(`TreningID`,`DatumVremeTreninga`) values 
(1,'2022-09-22 20:00:00'),
(2,'2022-09-30 20:00:00'),
(3,'2022-10-04 21:00:00'),
(4,'2022-10-06 21:00:00');

/*Table structure for table `zakazivanjetreninga` */

DROP TABLE IF EXISTS `zakazivanjetreninga`;

CREATE TABLE `zakazivanjetreninga` (
  `ZakazivanjeTreningaID` int(10) NOT NULL AUTO_INCREMENT,
  `PolaznikID` int(10) DEFAULT NULL,
  `TrenerID` int(10) DEFAULT NULL,
  PRIMARY KEY (`ZakazivanjeTreningaID`),
  KEY `fk_po_id` (`PolaznikID`),
  KEY `fk_tr_id` (`TrenerID`),
  CONSTRAINT `fk_po_id` FOREIGN KEY (`PolaznikID`) REFERENCES `polaznik` (`PolaznikID`),
  CONSTRAINT `fk_tr_id` FOREIGN KEY (`TrenerID`) REFERENCES `trener` (`TrenerID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

/*Data for the table `zakazivanjetreninga` */

insert  into `zakazivanjetreninga`(`ZakazivanjeTreningaID`,`PolaznikID`,`TrenerID`) values 
(6,2,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
