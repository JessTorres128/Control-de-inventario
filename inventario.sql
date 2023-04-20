-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 20, 2023 at 05:26 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `inventario`
--

-- --------------------------------------------------------

--
-- Table structure for table `herramienta`
--

CREATE TABLE `herramienta` (
  `cb_herramienta` varchar(10) NOT NULL,
  `id_herramienta` int(10) NOT NULL,
  `tipo` varchar(50) NOT NULL,
  `caracteristicas` varchar(60) NOT NULL,
  `frecuencia_de_uso` varchar(40) NOT NULL,
  `cantidad` int(10) NOT NULL,
  `cantidad_min` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `herramienta`
--

INSERT INTO `herramienta` (`cb_herramienta`, `id_herramienta`, `tipo`, `caracteristicas`, `frecuencia_de_uso`, `cantidad`, `cantidad_min`) VALUES
('1', 2, 'inglesa', 'dsadsadsadsadsa', 'Medio', 111, 11),
('2090219315', 2, 'wertyu', 'fgghjkuiyutrdff', 'Bajo', 99, 4),
('4786689832', 3, 'iyutre', 'uhgfds', 'Alto', 12, 1);

-- --------------------------------------------------------

--
-- Table structure for table `herramientas`
--

CREATE TABLE `herramientas` (
  `id_herramienta` int(10) NOT NULL,
  `herramienta` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `herramientas`
--

INSERT INTO `herramientas` (`id_herramienta`, `herramienta`) VALUES
(1, 'Pala'),
(2, 'Llave'),
(3, 'Rastrillo');

-- --------------------------------------------------------

--
-- Table structure for table `material`
--

CREATE TABLE `material` (
  `cb_material` varchar(10) NOT NULL,
  `tipo_de_armario` varchar(40) NOT NULL,
  `gaveta` varchar(20) NOT NULL,
  `sub_compartimento` varchar(30) NOT NULL,
  `id_material` int(10) NOT NULL,
  `tipo` varchar(60) NOT NULL,
  `numero_parte` varchar(60) NOT NULL,
  `valor` float NOT NULL,
  `unidad_de_medida` varchar(10) NOT NULL,
  `caracteristicas` varchar(200) NOT NULL,
  `frecuencia_de_uso` varchar(20) NOT NULL,
  `cantidad` int(10) NOT NULL,
  `cantidad_min` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `material`
--

INSERT INTO `material` (`cb_material`, `tipo_de_armario`, `gaveta`, `sub_compartimento`, `id_material`, `tipo`, `numero_parte`, `valor`, `unidad_de_medida`, `caracteristicas`, `frecuencia_de_uso`, `cantidad`, `cantidad_min`) VALUES
('1234567890', 'D', 'D6', 'B', 2, 'Diodo', '432432AJD', 0, 'N/A', '7W / 9V', 'Medio', 100, 20),
('1592483282', 'd', 'd2', 'a', 1, 'tttt', '5432fdfsd', 23, 'fdsfdfds', 'gfdgdfgdfgdfgdf', 'Alto', 32, 54),
('4737075182', 'D', 'D5', 'B', 1, 'Grande', '543543', 100, 'Ohms', 'aaaaaaaaaaaaaaaaaACABO DE EDITAR ESTO', 'Medio', 100, 20),
('7533218178', 'd', '3', 'd44', 2, 'fdsfdsfdsdsadsadeeeeeeeeeee', '6546gfg', 23, 'fdfssdf', 'gfhgfhgfhgfh', 'Bajo', 6, 1);

-- --------------------------------------------------------

--
-- Table structure for table `materiales`
--

CREATE TABLE `materiales` (
  `id_material` int(10) NOT NULL,
  `material` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `materiales`
--

INSERT INTO `materiales` (`id_material`, `material`) VALUES
(1, 'Diodo'),
(2, 'Resistencia');

-- --------------------------------------------------------

--
-- Table structure for table `pedido`
--

CREATE TABLE `pedido` (
  `id_pedido` int(10) NOT NULL,
  `num_control` varchar(8) NOT NULL,
  `estado` varchar(30) NOT NULL,
  `fecha` datetime NOT NULL,
  `profesor` varchar(100) NOT NULL,
  `materia` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `pedido_material`
--

CREATE TABLE `pedido_material` (
  `id_registro` int(10) NOT NULL,
  `id_pedido` int(10) NOT NULL,
  `cb_material` varchar(10) NOT NULL,
  `cantidad` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tipo_usuario`
--

CREATE TABLE `tipo_usuario` (
  `id_rol` int(10) NOT NULL,
  `nombre_rol` varchar(100) NOT NULL,
  `crud_articulo` tinyint(1) NOT NULL,
  `crud_material` tinyint(1) NOT NULL,
  `crud_pedido` tinyint(1) NOT NULL,
  `crud_user` tinyint(1) NOT NULL,
  `restaurar_bd` tinyint(1) NOT NULL,
  `respaldar_bd` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tipo_usuario`
--

INSERT INTO `tipo_usuario` (`id_rol`, `nombre_rol`, `crud_articulo`, `crud_material`, `crud_pedido`, `crud_user`, `restaurar_bd`, `respaldar_bd`) VALUES
(1, 'Administrador', 1, 1, 1, 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE `usuario` (
  `id_user` int(10) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `nombre_rol` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`id_user`, `username`, `password`, `nombre_rol`) VALUES
(1, 'admin', 'admin', 'Administrador');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `herramienta`
--
ALTER TABLE `herramienta`
  ADD PRIMARY KEY (`cb_herramienta`);

--
-- Indexes for table `herramientas`
--
ALTER TABLE `herramientas`
  ADD PRIMARY KEY (`id_herramienta`);

--
-- Indexes for table `material`
--
ALTER TABLE `material`
  ADD PRIMARY KEY (`cb_material`),
  ADD UNIQUE KEY `idx_cb_material` (`cb_material`);

--
-- Indexes for table `materiales`
--
ALTER TABLE `materiales`
  ADD PRIMARY KEY (`id_material`);

--
-- Indexes for table `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id_pedido`);

--
-- Indexes for table `pedido_material`
--
ALTER TABLE `pedido_material`
  ADD PRIMARY KEY (`id_registro`);

--
-- Indexes for table `tipo_usuario`
--
ALTER TABLE `tipo_usuario`
  ADD PRIMARY KEY (`id_rol`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `herramientas`
--
ALTER TABLE `herramientas`
  MODIFY `id_herramienta` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `materiales`
--
ALTER TABLE `materiales`
  MODIFY `id_material` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id_pedido` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pedido_material`
--
ALTER TABLE `pedido_material`
  MODIFY `id_registro` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tipo_usuario`
--
ALTER TABLE `tipo_usuario`
  MODIFY `id_rol` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_user` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
