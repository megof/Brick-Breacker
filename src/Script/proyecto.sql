-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.1.34-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win32
-- HeidiSQL Versión:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para proyecto
CREATE DATABASE IF NOT EXISTS `proyecto` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `proyecto`;

-- Volcando estructura para tabla proyecto.proyecto_jugadores
CREATE TABLE IF NOT EXISTS `proyecto_jugadores` (
  `Jugadores_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Jugadores_Nick` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Jugadores_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla proyecto.proyecto_jugadores: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `proyecto_jugadores` DISABLE KEYS */;
INSERT INTO `proyecto_jugadores` (`Jugadores_Id`, `Jugadores_Nick`) VALUES
	(1, 'Megof'),
	(2, 'Marín'),
	(3, 'Morphen');
/*!40000 ALTER TABLE `proyecto_jugadores` ENABLE KEYS */;

-- Volcando estructura para tabla proyecto.proyecto_partidas
CREATE TABLE IF NOT EXISTS `proyecto_partidas` (
  `Partidas_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Partidas_Jugador` int(11) NOT NULL DEFAULT '0',
  `Partidas_Puntaje` int(11) NOT NULL DEFAULT '1',
  `Partidas_Fecha` datetime NOT NULL DEFAULT '0101-01-00 00:00:00',
  PRIMARY KEY (`Partidas_Id`),
  KEY `FK_Proyecto_Partidas_proyecto_jugadores` (`Partidas_Jugador`),
  CONSTRAINT `FK_Proyecto_Partidas_proyecto_jugadores` FOREIGN KEY (`Partidas_Jugador`) REFERENCES `proyecto_jugadores` (`Jugadores_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla proyecto.proyecto_partidas: ~5 rows (aproximadamente)
/*!40000 ALTER TABLE `proyecto_partidas` DISABLE KEYS */;
INSERT INTO `proyecto_partidas` (`Partidas_Id`, `Partidas_Jugador`, `Partidas_Puntaje`, `Partidas_Fecha`) VALUES
	(1, 3, 500, '0001-01-01 01:01:01'),
	(2, 3, 100, '2019-04-08 19:01:14'),
	(3, 1, 300, '2019-04-09 15:12:21'),
	(4, 2, 550, '2019-04-09 15:25:04'),
	(5, 2, 3450, '2019-04-10 20:29:45');
/*!40000 ALTER TABLE `proyecto_partidas` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
