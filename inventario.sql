-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 26, 2023 at 10:59 PM
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
('1', 4, 'inglesa', 'dsadsadsadsadsa', 'Medio', 111, 11),
('2090219315', 4, 'wertyu', 'fgghjkuiyutrdff', 'Bajo', 99, 4),
('3979478150', 4, 'rrrrrr', 'vdvdfvf', 'Alto', 64, 1),
('4786689832', 5, 'iyutre', 'uhgfds', 'Alto', 12, 1);

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
  `valor` float DEFAULT NULL,
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
('1234567890', 'D', 'D6', 'B', 2, 'Diodo', '432432AJD', 0, 'N/A', '7W / 9V', 'Medio', 0, 20),
('1592483282', 'd', 'd2', 'a', 1, 'tttt', '5432fdfsd', 23, 'fdsfdfds', 'gfdgdfgdfgdfgdf', 'Alto', 56, 54),
('2841715231', 'd', 'd4', '43535', 1, 'gfhgf', '654654ytrh', 34324, 'ohms', 'jhgfxdfds', 'Bajo', 11, 1),
('3007874738', 'r4', 'd1', '5', 8, 'gfdgfdgdf', '54534gfdg', 123, 'Volts', 'hgfhgfbgfb', 'Alto', 7, 10),
('3721416545', 'A5', 'B', 'B5', 1, 'A5', '32', 55, 'ohms', 'tabla blanca', 'Medio', 15, 20),
('4737075182', 'D', 'D5', 'B', 1, 'Grande', '543543', 100, 'Ohms', 'ACABO DE EDITAR ESTO', 'Medio', 100, 20),
('6166654638', 'N/A', 'N/A', 'N/A', 2, 'fdsfdsf', 'fsdfdsfds', 12, 'fdgfd', 'fdsfds', 'Bajo', 12, 21);

-- --------------------------------------------------------

--
-- Table structure for table `pedido`
--

CREATE TABLE `pedido` (
  `id_pedido` int(10) NOT NULL,
  `nombre_persona` varchar(100) NOT NULL,
  `num_control` varchar(8) NOT NULL,
  `estado` varchar(30) NOT NULL,
  `fecha` datetime NOT NULL,
  `profesor` varchar(100) NOT NULL,
  `materia` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pedido`
--

INSERT INTO `pedido` (`id_pedido`, `nombre_persona`, `num_control`, `estado`, `fecha`, `profesor`, `materia`) VALUES
(1, 'Jesse Anthony Torres Rodriguez', '20cg0165', 'Pendiente', '2023-05-03 10:26:02', 'Pando', 'Lenguajes de interfaz'),
(2, 'fdsfsdfdsf', 'fdsfdsf', 'Entregado', '2023-05-03 22:51:13', 'fdsfdsf', 'fdsfds'),
(3, 'fdsfdsf', 'dsadsad', 'Entregado', '2023-05-04 13:18:08', 'fdsds', 'fdsfds'),
(4, 'fdsfdsfsddfsfdsfds', 'fdsfdsf', 'Pendiente', '2023-05-04 13:19:27', 'fdsfdsfdsfdsfds', 'fdsfdsfdsdsffds'),
(6, 'gfdgdfgdfg', 'gfdgfd', 'Entregado', '2023-05-09 16:51:09', 'gfdgdfg', 'gfdgdfg'),
(7, 'fdsfdsfds', 'N/A', 'Entregado', '2023-05-09 16:51:34', 'fdsfds', 'fdsfsd'),
(8, 'fdsfdsfdsfsdf', 'N/A', 'Entregado', '2023-05-09 16:51:49', 'N/A', 'N/A'),
(10, 'luis', '20CG0180', 'Pendiente', '2023-05-11 18:00:54', 'pando', 'pando'),
(11, 'dsadasdsad', 'N/A', 'Entregado', '2023-05-11 18:41:45', 'dsad', 'dsadas'),
(12, 'fdsfsdf', 'fdsfdsf', 'Entregado', '2023-05-11 18:42:17', 'N/A', 'N/A'),
(13, 'joel', 'N/A', 'Entregado', '2023-05-11 18:44:48', 'N/A', 'N/A'),
(14, 'rewrewrwe', '43243', 'Entregado', '2023-05-11 18:50:22', 'N/A', 'N/A'),
(15, 'jess', 'N/A', 'Pendiente', '2023-05-14 20:05:09', 'N/A', 'N/A'),
(16, 'wery', 'N/A', 'Entregado', '2023-05-14 20:11:18', 'N/A', 'N/A'),
(17, 'jesss', 'N/A', 'Pendiente', '2023-05-18 20:56:30', 'N/A', 'N/A'),
(18, 'dsadsadas', 'N/A', 'Pendiente', '2023-05-20 13:15:43', 'N/A', 'N/A'),
(19, 'Jose Juan', '20cg0206', 'Pendiente', '2023-05-26 13:28:23', 'Lilia', 'Ing software');

-- --------------------------------------------------------

--
-- Table structure for table `pedido_material`
--

CREATE TABLE `pedido_material` (
  `id_registro` int(10) NOT NULL,
  `id_pedido` int(10) NOT NULL,
  `cb_material` varchar(10) NOT NULL,
  `cantidad` int(10) NOT NULL,
  `estado` varchar(20) NOT NULL DEFAULT 'Pendiente'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pedido_material`
--

INSERT INTO `pedido_material` (`id_registro`, `id_pedido`, `cb_material`, `cantidad`, `estado`) VALUES
(16, 7, '4737075182', 3, 'Pendiente'),
(17, 8, '6343343238', 1, 'Pendiente'),
(18, 8, '4737075182', 1, 'Pendiente'),
(25, 11, '3007874738', 1, 'Pendiente'),
(26, 12, '1234567890', 1, 'Pendiente'),
(27, 13, '2841715231', 3, 'Pendiente'),
(28, 13, '75009809', 3, 'Pendiente'),
(29, 14, '4737075182', 1, 'Pendiente'),
(30, 14, '1592483282', 1, 'Pendiente'),
(31, 14, '6343343238', 1, 'Pendiente'),
(34, 16, '3979478150', 11, 'Pendiente'),
(48, 2, '1592483282', 3, 'Pendiente'),
(49, 4, '6343343238', 1, 'Pendiente'),
(120, 6, '3007874738', 3, 'Pendiente'),
(121, 6, '4737075182', 1, 'Pendiente'),
(124, 17, '3007874738', 4, 'Pendiente'),
(125, 17, '4737075182', 3, 'Pendiente'),
(126, 15, '1592483282', 13, 'Pendiente'),
(170, 10, '1234567890', 1, 'Entregado'),
(171, 10, '1', 11, 'Entregado'),
(172, 10, '4737075182', 2, 'Pendiente'),
(173, 10, '6343343238', 1, 'Pendiente'),
(174, 18, '1234567890', 11, 'Pendiente'),
(175, 19, '3721416545', 5, 'Pendiente');

-- --------------------------------------------------------

--
-- Table structure for table `tipo_material`
--

CREATE TABLE `tipo_material` (
  `id_material` int(10) NOT NULL,
  `material` varchar(40) NOT NULL,
  `tipo_material` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tipo_material`
--

INSERT INTO `tipo_material` (`id_material`, `material`, `tipo_material`) VALUES
(1, 'Diodo', 'Material Consumible'),
(2, 'Resistencia', 'Material Consumible'),
(3, 'Pala', 'Herramienta'),
(4, 'Llave', 'Herramienta'),
(5, 'Rastrillo', 'Herramienta'),
(7, 'Pico2', 'Herramienta'),
(8, 'Fuente de poder', 'Material Fijo');

-- --------------------------------------------------------

--
-- Table structure for table `tipo_usuario`
--

CREATE TABLE `tipo_usuario` (
  `id_rol` int(10) NOT NULL,
  `nombre_rol` varchar(100) NOT NULL,
  `create_material` tinyint(1) NOT NULL,
  `update_material` tinyint(1) NOT NULL,
  `delete_material` tinyint(1) NOT NULL,
  `create_herramienta` tinyint(1) NOT NULL,
  `update_herramienta` tinyint(1) NOT NULL,
  `delete_herramienta` tinyint(1) NOT NULL,
  `crud_pedido` tinyint(1) NOT NULL,
  `create_t_articulo` tinyint(1) NOT NULL,
  `update_t_articulo` tinyint(1) NOT NULL,
  `delete_t_articulo` tinyint(1) NOT NULL,
  `crud_roles` tinyint(1) NOT NULL,
  `crud_empleados` tinyint(1) NOT NULL,
  `restaurar_bd` tinyint(1) NOT NULL,
  `respaldar_bd` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tipo_usuario`
--

INSERT INTO `tipo_usuario` (`id_rol`, `nombre_rol`, `create_material`, `update_material`, `delete_material`, `create_herramienta`, `update_herramienta`, `delete_herramienta`, `crud_pedido`, `create_t_articulo`, `update_t_articulo`, `delete_t_articulo`, `crud_roles`, `crud_empleados`, `restaurar_bd`, `respaldar_bd`) VALUES
(1, 'Administrador', 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
(2, 'Becario', 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0),
(3, 'Almacenero', 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1),
(5, 'Contador', 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0),
(6, 'Dios', 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0),
(7, 'Invitado', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE `usuario` (
  `id_user` int(10) NOT NULL,
  `nombre_completo` varchar(100) NOT NULL,
  `sexo` varchar(20) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `nombre_rol` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`id_user`, `nombre_completo`, `sexo`, `username`, `password`, `nombre_rol`) VALUES
(1, '', '', 'admin', 'admin', 'Administrador'),
(2, 'jose el pro', 'Femenino', 'Jose', 'jose123', 'Becario'),
(3, 'joel', 'F', 'joel224', 'joel224', 'Contador'),
(4, 'Invitado', 'M', 'Invitado', 'Invitado', 'Invitado'),
(6, 'Perla Martinez', 'F', 'Perla', '1234', 'Becario');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `herramienta`
--
ALTER TABLE `herramienta`
  ADD PRIMARY KEY (`cb_herramienta`);

--
-- Indexes for table `material`
--
ALTER TABLE `material`
  ADD PRIMARY KEY (`cb_material`),
  ADD UNIQUE KEY `idx_cb_material` (`cb_material`);

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
-- Indexes for table `tipo_material`
--
ALTER TABLE `tipo_material`
  ADD PRIMARY KEY (`id_material`);

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
-- AUTO_INCREMENT for table `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id_pedido` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `pedido_material`
--
ALTER TABLE `pedido_material`
  MODIFY `id_registro` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=176;

--
-- AUTO_INCREMENT for table `tipo_material`
--
ALTER TABLE `tipo_material`
  MODIFY `id_material` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `tipo_usuario`
--
ALTER TABLE `tipo_usuario`
  MODIFY `id_rol` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_user` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
