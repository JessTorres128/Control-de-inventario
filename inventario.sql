-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 05, 2023 at 02:20 AM
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
  `id_herramienta` int(10) NOT NULL,
  `tipo` varchar(50) NOT NULL,
  `caracteristicas` varchar(60) NOT NULL,
  `frecuencia_de_uso` varchar(40) NOT NULL,
  `cantidad` int(10) NOT NULL,
  `cantidad_min` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `herramientas`
--

CREATE TABLE `herramientas` (
  `id_herramienta` int(10) NOT NULL,
  `herramienta` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
  `nuero_parte` varchar(60) NOT NULL,
  `valor` float NOT NULL,
  `unidad_de_medida` varchar(10) NOT NULL,
  `caracteristicas` varchar(200) NOT NULL,
  `frecuencia_de_uso` varchar(20) NOT NULL,
  `cantidad` int(10) NOT NULL,
  `cantidad_min` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `materiales`
--

CREATE TABLE `materiales` (
  `id_material` int(10) NOT NULL,
  `material` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
  `nombre_rol` int(100) NOT NULL,
  `crud_articulo` tinyint(1) NOT NULL,
  `crud_material` tinyint(1) NOT NULL,
  `crud_pedido` tinyint(1) NOT NULL,
  `crud_user` tinyint(1) NOT NULL,
  `restaurar_bd` tinyint(1) NOT NULL,
  `respaldar_bd` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
-- Indexes for dumped tables
--

--
-- Indexes for table `herramienta`
--
ALTER TABLE `herramienta`
  ADD PRIMARY KEY (`id_herramienta`);

--
-- Indexes for table `herramientas`
--
ALTER TABLE `herramientas`
  ADD PRIMARY KEY (`id_herramienta`);

--
-- Indexes for table `material`
--
ALTER TABLE `material`
  ADD PRIMARY KEY (`cb_material`);

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
-- AUTO_INCREMENT for table `herramienta`
--
ALTER TABLE `herramienta`
  MODIFY `id_herramienta` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `herramientas`
--
ALTER TABLE `herramientas`
  MODIFY `id_herramienta` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `materiales`
--
ALTER TABLE `materiales`
  MODIFY `id_material` int(10) NOT NULL AUTO_INCREMENT;

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
  MODIFY `id_rol` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_user` int(10) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;