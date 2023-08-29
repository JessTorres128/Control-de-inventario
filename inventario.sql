-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 29, 2023 at 03:33 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

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
-- Table structure for table `alumnos`
--

CREATE TABLE `alumnos` (
  `num_control` varchar(8) NOT NULL,
  `nombre_alumno` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `alumnos`
--

INSERT INTO `alumnos` (`num_control`, `nombre_alumno`) VALUES
('20cg0077', 'pepe'),
('20CG0180', 'JOSE'),
('20cg0188', 'Pepe'),
('20cg9323', 'Omar');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `herramienta`
--

INSERT INTO `herramienta` (`cb_herramienta`, `id_herramienta`, `tipo`, `caracteristicas`, `frecuencia_de_uso`, `cantidad`, `cantidad_min`) VALUES
('1000000592', 690, 'española', 'llave mecanica milimetrica  de 6 mm ', 'Alto', 1, 0),
('1000000593', 690, 'española', 'llave mecanica  de 5/16  ', 'Alto', 2, 0),
('1000000594', 690, 'española', 'llave mecanica  de 3/8', 'Alto', 1, 0),
('1000000595', 690, 'española', 'llave mecanica  de 7/16', 'Alto', 2, 0),
('1000000596', 690, 'española', 'llave mecanica  de 1/2', 'Alto', 1, 0),
('1000000597', 690, 'española', 'llave mecanica  de 9/16', 'Alto', 1, 0),
('1000000598', 690, 'española', 'llave mecanica  de 5/8', 'Alto', 1, 0),
('1000000599', 690, 'española', 'llave mecanica  de 11/16', 'Alto', 2, 0),
('1000000600', 690, 'española', 'llave mecanica  de 3/4', 'Alto', 1, 0),
('1000000601', 690, 'española', 'llave mecanica  de 13/16', 'Alto', 2, 0),
('1000000602', 690, 'española', 'llave mecanica  de 7/8', 'Alto', 2, 0),
('1000000603', 690, 'española', 'llave mecanica  de 15/16', 'Alto', 3, 0),
('1000000604', 690, 'española', 'llave mecanica  de 1\"', 'Alto', 2, 0),
('1000000605', 690, 'española', 'llave mecanica  de 1\" 1/16', 'Alto', 2, 0),
('1000000606', 690, 'española', 'llave mecanica  de 1\" 1/8', 'Alto', 1, 0),
('1000000607', 690, 'española', 'llave mecanica  de 1 1/4', 'Alto', 1, 0),
('1000000608', 690, 'española', 'llave mecanica milimetrica de 10mm', 'Alto', 2, 0),
('1000000609', 690, 'española', 'llave mecanica milimetrica de 12mm', 'Alto', 1, 0),
('1000000610', 690, 'española', 'llave mecanica milimetrica de 13mm', 'Alto', 2, 0),
('1000000611', 690, 'española', 'llave mecanica milimetrica de 15mm', 'Alto', 2, 0),
('1000000612', 690, 'española', 'llave mecanica milimetrica de 16mm', 'Alto', 1, 0),
('1000000613', 690, 'stilson', 'llave stilson de 254mm PRETUL', 'Alto', 1, 0),
('1000000614', 690, 'stilson', 'llave stilson de 250mm URREA', 'Alto', 1, 0),
('1000000615', 690, 'inglesa', 'llave inglesa URREA', 'Alto', 1, 0),
('1000000616', 690, 'inglesa', 'llave inglesa de 250mm FULLER', 'Alto', 1, 0),
('1000000617', 690, 'presion', 'pinza de presion (perras)', 'Alto', 2, 0),
('1000000618', 690, 'Mecanizas', 'pinzas mecanicas', 'Alto', 3, 0),
('1000000619', 691, 'Electrisista', 'Pinzas para electricista', 'Alto', 3, 0),
('1000000620', 691, 'punta', 'Pinzas de punta', 'Alto', 5, 0),
('1000000621', 691, 'Ponchadoras', 'Pinzas pochadoraas de cable', 'Alto', 5, 0),
('1000000622', 691, 'persion', 'pinzas de presion Tipo sargento', 'Alto', 2, 0),
('1000000623', 691, 'martillo', 'martillo', 'Alto', 2, 0),
('1000000624', 691, 'marro', 'marro ???', 'Alto', 4, 0),
('1000000625', 692, 'para cegeta', 'carco para cegeta', 'Alto', 5, 0),
('1000000626', 693, 'para lamina', 'tijeras para lamina', 'Alto', 2, 0),
('1000000627', 694, 'escudra', 'escudra', 'Alto', 1, 0),
('1000000628', 695, 'segudidad', 'cascos de seguridad', 'Alto', 3, 0),
('1000000629', 696, 'para soldar', 'caretas para soldar', 'Alto', 3, 0),
('1000000630', 697, 'seguridad', 'caretas para proteccion facial', 'Alto', 2, 0),
('1000000631', 698, 'cruz', 'desarmador de cruz', 'Alto', 13, 0),
('1000000632', 698, 'paleta', 'desarmador de paleta', 'Alto', 14, 0),
('1000000633', 699, 'allen', 'juego de llaves allen estandar', 'Alto', 1, 0),
('1000000634', 699, 'allen', 'juego de llaves allen milimetrico', 'Alto', 1, 0),
('1000000635', 690, 'torpedo', 'nivel tipo torpedo', 'Alto', 2, 0),
('1000000636', 690, 'N/A', 'Lima', 'Alto', 4, 0),
('1000000637', 700, 'N/A', 'cincel', 'Alto', 1, 0),
('1000000638', 701, 'N/A', 'remachadora', 'Alto', 1, 0),
('1000000639', 702, 'socket para foco', 'socket peueño para foco', 'Alto', 95, 0),
('1000000640', 703, 'socket para foco', 'socket redondo para foco', 'Bajo', 27, 0),
('1000000641', 704, 'luz estrobo', 'Luz estrobo 75 destelos por minuto de 6 a 12 VCD', 'Sin utilizar', 9, 0),
('1000000642', 704, 'foco emisor de luz', 'Foco emisor de luz de 25 a 100 w ', 'Alto', 40, 0),
('1000000643', 705, 'seguridad', 'letes de seguridad', 'Alto', 32, 0),
('1000000644', 672, 'gogles', 'lentes tipo gogle de seguridad', 'Bajo', 6, 0),
('1000000645', 706, 'cinta', '½\" x 0.025\" x 64½\"  pulgadas', 'Alto', 0, 0),
('1000000646', 706, 'N/A', 'N/A', 'Sin utilizar', 0, 0),
('1000000647', 707, 'N/A', 'N/A', 'Sin utilizar', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `materia`
--

CREATE TABLE `materia` (
  `id_materia` int(20) NOT NULL,
  `num_control` varchar(20) NOT NULL,
  `dia` varchar(20) NOT NULL,
  `hora_inicio` time NOT NULL,
  `hora_fin` time NOT NULL,
  `profesor` varchar(100) NOT NULL,
  `nom_materia` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
  `valor` varchar(30) DEFAULT NULL,
  `unidad_de_medida` varchar(10) NOT NULL,
  `caracteristicas` text NOT NULL,
  `frecuencia_de_uso` varchar(20) NOT NULL,
  `cantidad` int(10) NOT NULL,
  `cantidad_min` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `material`
--

INSERT INTO `material` (`cb_material`, `tipo_de_armario`, `gaveta`, `sub_compartimento`, `id_material`, `tipo`, `numero_parte`, `valor`, `unidad_de_medida`, `caracteristicas`, `frecuencia_de_uso`, `cantidad`, `cantidad_min`) VALUES
('1000000001', 'Metálico', 'A-1', 'a', 653, 'Óptico Reflectante', 'CNY70', 'N/A', 'N/A', 'Sensor Óptico Reflectivo c/salida transistor', 'Alto', 42, 0),
('1000000002', 'Metálico', 'A-1', 'b', 653, 'Óptico', 'IS471F', 'N/A', 'N/A', 'Detector Óptico de Luz', 'Bajo', 34, 0),
('1000000003', 'Metálico', 'A-1', 'c', 653, 'Optointerruptor', 'ITR8102', 'N/A', 'N/A', 'Optointerruptor', 'Medio', 56, 0),
('1000000004', 'Metálico', 'A-2', 'a', 653, 'Optointerruptor Reflectivo', 'QRD1114', 'N/A', 'N/A', 'Sensor Reflectivo Opuesto', 'Bajo', 50, 0),
('1000000005', 'Metálico', 'A-2', 'c', 654, 'Fuente de Corriente Ajustable', 'LM334Z', 'N/A', 'N/A', 'Fuente de Corriente Ajustable', 'Bajo', 35, 0),
('1000000006', 'Metálico', 'A-3', 'b', 653, 'Humedad y Temperatura', 'SHT11', 'N/A', 'N/A', 'Sensor de Temperatura y Humedad  ', 'Bajo', 7, 0),
('1000000007', 'Metálico', 'A-3', 'c', 653, 'Medición de Distancia', 'GP2Y0A21YK', 'N/A', 'N/A', 'Unidad  Sensor de Medicion de Distancia', 'Bajo', 9, 0),
('1000000008', 'Metálico', 'A-4', 'a', 653, 'Temperatura Digital y Memoria', 'DS1624', 'N/A', 'N/A', 'Termometro Digital y Memoria', 'Sin utilizar', 9, 0),
('1000000009', 'Metálico', 'A-4', 'c', 655, 'Osilador de Cuarzo', 'N/A', '1.8', 'Mhz', 'Cristal de Cuarzo 1.8 MHz', 'Medio', 25, 0),
('1000000010', 'Metálico', 'A-5', 'a', 655, 'Osilador de Cuarzo', 'N/A', '3.5', 'Mhz', 'Cristal de Cuarzo 3.5 MHz', 'Medio', 33, 0),
('1000000011', 'Metálico', 'A-5', 'b', 655, 'Osilador de Cuarzo', 'N/A', '4.0', 'Mhz', 'Cristal de Cuarzo 4.0 MHz', 'Medio', 90, 0),
('1000000012', 'Metálico', 'A-5', 'c', 655, 'Osilador de Cuarzo', 'N/A', '10.0', 'Mhz', 'Cristal de Cuarzo 10.0 MHz', 'Alto', 36, 0),
('1000000013', 'Metálico', 'A-6', 'b', 656, 'Cerámico', '23-58M', '3.5', 'Mhz', 'Resonador Ceramico 3.5 MHz', 'Medio', 32, 0),
('1000000014', 'Metálico', 'A-6', 'c', 656, 'Cerámico', '4.0 M', '4.0', 'Mhz', 'Resonador Ceramico 4.0 MHz', 'Alto', 75, 0),
('1000000015', 'Metálico', 'B-1', 'a', 656, 'Cerámico', 'ZTA 8,0 M', '8.0', 'Mhz', 'Resonador Ceramico 8.0 MHz', 'Medio', 11, 0),
('1000000016', 'Metálico', 'B-1', 'b', 654, 'Corregir Factor de Potencia', 'AN829S', 'N/A', 'N/A', 'Atenuador Electronic Control volumen/voltaje', 'Sin utilizar', 20, 0),
('1000000017', 'Metálico', 'B-1', 'c', 654, 'Amplificador de Voltaje Controlado', 'UPC1252H2', 'N/A', 'N/A', 'Circuito Integrado Analogico Bipolar', 'Sin utilizar', 20, 0),
('1000000018', 'Metálico', 'B-2', 'a', 654, 'Regulador de Velocidad de Motor', 'UPC1470LM', 'N/A', 'N/A', 'C.I. Analogico Bipolar (reg. de vel. De motor)', 'Sin utilizar', 19, 0),
('1000000019', 'Metálico', 'B-2', 'b', 654, 'Grabador y Reproductor de Voz', 'APR9600', 'N/A', 'N/A', 'Grabador y Reproductor de Voz', 'Medio', 14, 0),
('1000000020', 'Metálico', 'B-2', 'c', 654, 'LED Controlador Interfaz  I²c Bus', 'SAA1064', 'N/A', 'N/A', 'Interface. I²c Bus c/control 4 Display de 7 segm.', 'Sin utilizar', 7, 0),
('1000000021', 'Metálico', 'B-3', 'a', 654, 'Expansor Remoto I²c Bus', 'PCF8574P', 'N/A', 'N/A', 'Expansor para Bus 1²c 8 bits', 'Sin utilizar', 11, 0),
('1000000022', 'Metálico', 'B-3', 'b', 654, 'Interruptor Bilateral', 'CD4066', 'N/A', 'N/A', 'Cuatro Switch Bilateral', 'Sin utilizar', 23, 0),
('1000000023', 'Metálico', 'B-3', 'c', 654, 'Amplificador de Voltaje', 'LM386N-1', 'N/A', 'N/A', ' Amp.Audio de Potencia Bajo Voltaje.', 'Medio', 67, 0),
('1000000024', 'Metálico', 'N/A', 'N/A', 654, 'Cotrolador de Pantalla ', 'LM3914', 'N/A', 'N/A', 'Driver/Display de Puntos y Barras', 'Sin utilizar', 28, 0),
('1000000025', 'Metálico', 'B-4', 'a', 654, 'Controlador / Receptor RS-232', 'MAX232CPE', 'N/A', 'N/A', 'Driver/Receptor RS-232 Multicanal', 'Sin utilizar', 28, 0),
('1000000026', 'Metálico', 'B-4', 'b', 654, 'Cotrolador de Pantalla Flourecente', 'NTE2022', 'N/A', 'N/A', 'Driver/Display 8 Digit-8 Segment. Fluorecente', 'Sin utilizar', 6, 0),
('1000000027', 'Metálico', 'B-4', 'c', 654, 'Empuje Pulsador', 'L293B', 'N/A', 'N/A', 'Driver 4 canales Push-Pull', 'Alto', 38, 0),
('1000000028', 'Metálico', 'B-5', 'a', 654, 'Empuje Pulsador', 'L293C', 'N/A', 'N/A', 'Doble Driver 4 canales Push-Pull', 'Alto', 54, 0),
('1000000029', 'Metálico', 'B-5', 'b', 654, 'Sumador', 'TC74HC283', 'N/A', 'N/A', 'Sumador Binario con Acarreo Rapido', 'Sin utilizar', 19, 0),
('1000000030', 'Metálico', 'B-6', 'b', 654, 'PLD Matriz lógico Generico', 'GAL22V10D', 'N/A', 'N/A', '   PLD,Arreglo Logico Generico E²CMOS', 'Bajo', 20, 0),
('1000000031', 'Metálico', 'B-6', 'c', 654, 'Matriz de Transistores Darlington', 'ULN2003A', 'N/A', 'N/A', '7 Arregos Darlington 7 canales', 'Alto', 39, 0),
('1000000032', 'Metálico', 'C-1', 'a', 654, 'Matriz de Transistores Darlington', 'ULN2803A', 'N/A', 'N/A', '8 Aarreglos Darlington con Emisor Comun', 'Alto', 55, 0),
('1000000033', 'Metálico', 'C-1', 'b', 654, 'Osciladores', 'VCO055', 'N/A', 'N/A', 'Oscilador Controlado por Voltaje', 'Sin utilizar', 15, 0),
('1000000034', 'Metálico', 'C-1', 'c', 654, 'Osciladores', 'ICL8038', 'N/A', 'N/A', 'Oscilador Controlado por Voltaje', 'Sin utilizar', 10, 0),
('1000000035', 'Metálico', 'C-2', 'a', 654, 'Osciladores', 'LM566CN', 'N/A', 'N/A', 'Oscilador Controlado por Voltaje', 'Bajo', 38, 0),
('1000000036', 'Metálico', 'C-2', 'b', 654, 'Amplificador Operacional', 'TL062', 'N/A', 'N/A', 'Dual Op-Amp de Potencia Entrada JFET,', 'Medio', 50, 0),
('1000000037', 'Metálico', 'C-2', 'c', 654, 'Amplificador Operacional', 'TL082', 'N/A', 'N/A', 'Dual Op-Amp de bajo Ruido Entrada JFET,', 'Medio', 89, 0),
('1000000038', 'Metálico', 'C-3', 'a', 654, 'Amplificador Operacional', 'LM301AN', 'N/A', 'N/A', 'Amp. Operacional Sencillo ', 'Sin utilizar', 18, 0),
('1000000039', 'Metálico', 'C-3', 'b', 654, 'Amplificador Operacional', 'LM324N', 'N/A', 'N/A', 'Amp.Operacional Quad. Baja Potencia', 'Medio', 100, 0),
('1000000040', 'Metálico', 'C-3', 'c', 654, 'Amplificador Operacional', 'LF351N', 'N/A', 'N/A', 'Op Amp. gran ancho de banda entrada JFET', 'Medio', 55, 0),
('1000000041', 'Metálico', 'C-4', 'a', 654, 'Amplificador Operacional', 'LF356N', 'N/A', 'N/A', 'Amp.Operacional Sencillo entrada. JFET', 'Medio', 21, 0),
('1000000042', 'Metálico', 'C-4', 'b', 654, 'Amplificador Operacional', 'NE5532', 'N/A', 'N/A', 'Dual Op-Amp de bajo Ruido ', 'Bajo', 23, 0),
('1000000043', 'Metálico', 'C-4', 'c', 654, 'Amplificador Operacional', 'UA741CN', 'N/A', 'N/A', 'Amp. Operacional Bipolar. Prop. Gral.', 'Alto', 190, 0),
('1000000044', 'Metálico', 'C-5', 'a', 654, 'Buffers', 'SN74LS125AN', 'N/A', 'N/A', 'Quadruple Bus Buffer 3 Estados de Salida', 'Medio', 58, 0),
('1000000045', 'Metálico', 'C-5', 'b', 654, 'Buffers', 'SN74LS126N', 'N/A', 'N/A', 'Quad Buffer 3 states ', 'Medio', 31, 0),
('1000000046', 'Metálico', 'C-5', 'c', 654, 'Buffers', 'SN74LS244N', 'N/A', 'N/A', 'Octal buffer  Salida 3 Estados/Line Driver', 'Bajo', 48, 0),
('1000000047', 'Metálico', 'C-6', 'a', 654, 'Bloqueador de Fase', 'LM565', 'N/A', 'N/A', 'Bloqueador de bucle de fase', 'Sin utilizar', 25, 0),
('1000000048', 'Metálico', 'C-6', 'b', 654, 'Decodificador', 'CD4028BE', 'N/A', 'N/A', 'Decodif./Driver BCD/Decimal o Bin/Octal', 'Bajo', 12, 0),
('1000000049', 'Metálico', 'C-6', 'c', 654, 'Decodificador', 'CD4511BE', 'N/A', 'N/A', 'Decodif./Driver Latch BCD  to  7/ Segment', 'Bajo', 104, 0),
('1000000050', 'Metálico', 'D-1', 'a', 654, 'Decodificador', 'CD4543BE', 'N/A', 'N/A', 'Decod/Driver para LCD .  BCD/7 Segm', 'Bajo', 30, 0),
('1000000051', 'Metálico', 'D-1', 'b', 654, 'Decodificador', 'DM7446 AP', 'N/A', 'N/A', 'Decodificador Driver BCD a 7 Seg', 'Medio', 26, 0),
('1000000052', 'Metálico', 'D-1', 'c', 654, 'Decodificador', 'SN74LS 42', 'N/A', 'N/A', 'Decodif. Decimal-4 Lineas  BCD-10Lineas', 'Medio', 25, 0),
('1000000053', 'Metálico', 'D-2', 'a', 654, 'Decodificador', 'SN74LS47N', 'N/A', 'N/A', 'Decodificador Driver BCD a 7 Seg', 'Alto', 62, 0),
('1000000054', 'Metálico', 'D-2', 'b', 654, 'Decodificador', 'HD74LS48P', 'N/A', 'N/A', 'Decodificador/Driver de BCD a 7/segmen.', 'Alto', 65, 0),
('1000000055', 'Metálico', 'D-2', 'c', 654, 'Decodificador', 'SN74LS138N', 'N/A', 'N/A', 'Decodificador Demultiplexor 1 de 8', 'Bajo', 52, 0),
('1000000056', 'Metálico', 'D-3', 'a', 654, 'Decodificador', 'SN74LS139B', 'N/A', 'N/A', 'Decodificador Demultiplexor  1 de 4', 'Bajo', 23, 0),
('1000000057', 'Metálico', 'D-3', 'b', 654, 'Decodificador', 'MC74HC154N', 'N/A', 'N/A', 'Decodificador/Demultiplexor 1 de 16 lineas', 'Sin utilizar', 43, 0),
('1000000058', 'Metálico', 'D-3', 'c', 654, 'Decodificador', 'SN74LS247N', 'N/A', 'N/A', 'Decodificador/Driver de BCD a 7/segmen.', 'Sin utilizar', 31, 0),
('1000000059', 'Metálico', 'D-4', 'c', 654, 'Codificador', 'CD4532BE', 'N/A', 'N/A', 'Codificador Prioridad  8 bits', 'Bajo', 50, 0),
('1000000060', 'Metálico', 'D-5', 'a', 654, 'Codificador', 'SN74LS147N', 'N/A', 'N/A', 'Codificador Priority 10 -4 Lineas y 8 -3 Lineas', 'Bajo', 13, 0),
('1000000061', 'Metálico', 'D-5', 'b', 654, 'Codificador', 'CD74HC147E', 'N/A', 'N/A', 'Codificador 10-4 Lineas CMOS Logic  A/Veloci  ', 'Bajo', 67, 0),
('1000000062', 'Metálico', 'D-5', 'c', 654, 'Codificador', 'MM74C 922N', 'N/A', 'N/A', 'Codificador 16 Claves', 'Bajo', 7, 0),
('1000000063', 'Metálico', 'D-6', 'c', 654, 'Convertidor', 'ADC0804LCN', 'N/A', 'N/A', 'Convert. Analogico/Digital   8 bits', 'Medio', 64, 0),
('1000000064', 'Metálico', 'E-1', 'a', 654, 'Convertidor', 'DAC0806LCN', 'N/A', 'N/A', 'Convertidor Digital/Analogico  8 bits', 'Bajo', 20, 0),
('1000000065', 'Metálico', 'E-1', 'b', 654, 'Convertidor', 'DAC1020LCN', 'N/A', 'N/A', 'Convertidor D/A Multiplicador Binario 10-12 Bits', 'Sin utilizar', 23, 0),
('1000000066', 'Metálico', 'E-1', 'c', 654, 'Convertidor', 'DAC312CP', 'N/A', 'N/A', 'Convertidor D/A Multiplicador A7 velicidad', 'Sin utilizar', 25, 0),
('1000000067', 'Metálico', 'E-2', 'a', 654, 'Convertidor', 'AD7541JN', 'N/A', 'N/A', 'Convertidor Multiplicando Dig/Analog  12 bits', 'Sin utilizar', 25, 0),
('1000000068', 'Metálico', 'E-3', 'c', 654, 'Convertidor', 'MCP3304', 'N/A', 'N/A', 'Convertidor A/D baja potencia 13 bits', 'Sin utilizar', 25, 0),
('1000000069', 'Metálico', 'E-4', 'a', 654, 'Convertidor', 'MCP3202', 'N/A', 'N/A', 'Convertidor A/D 12 Bits', 'Sin utilizar', 21, 0),
('1000000070', 'Metálico', 'E-4', 'b', 654, 'Convertidor', 'MC1408-8N', 'N/A', 'N/A', 'Convertidor multiplicador D/A 8 Bits', 'Sin utilizar', 23, 0),
('1000000071', 'Metálico', 'E-4', 'c', 654, 'Registro', 'SN74LS164N', 'N/A', 'N/A', 'Registro de Corr. 8 bits Ent/Ser-Sal/Par', 'Sin utilizar', 72, 0),
('1000000072', 'Metálico', 'E-5', 'a', 654, 'Registro', 'MC14021BCP', 'N/A', 'N/A', 'Reegistro de Corrimiento Estatico', 'Sin utilizar', 49, 0),
('1000000073', 'Metálico', 'E-5', 'b', 654, 'Registro', 'SN74LS173', 'N/A', 'N/A', 'Reg c/Salida de 3/Estados  4 Bits Tipo D', 'Sin utilizar', 52, 0),
('1000000074', 'Metálico', 'F-3', 'c', 654, 'Comparador', 'LM311', 'N/A', 'N/A', 'Comparador de Voltaje', 'Alto', 41, 0),
('1000000075', 'Metálico', 'F-4', 'a', 654, 'Comparador', 'LM339', 'N/A', 'N/A', 'Comparador de Voltaje Baja Potencia', 'Medio', 54, 0),
('1000000076', 'Metálico', 'F-4', 'b', 654, 'Comparador', 'LM393', 'N/A', 'N/A', 'Dual Comparador de Voltaje Baja Potencia', 'Medio', 31, 0),
('1000000077', 'Metálico', 'F-4', 'c', 654, 'Compuerta Lógica', 'SN74LS00', 'N/A', 'N/A', 'Cuatro Compuertas NAND 2 entradas', 'Alto', 109, 0),
('1000000078', 'Metálico', 'H-5', 'b', 654, 'Multiplexor', 'MC74HC151N', 'N/A', 'N/A', 'CMOS 8 Input Data Selector/multiplexor', 'Sin utilizar', 48, 0),
('1000000079', 'Metálico', 'H-5', 'c', 654, 'Multiplexor', 'MC74HC153N', 'N/A', 'N/A', 'Multiplexor Selec. de datos 4 entrad. doble', 'Sin utilizar', 70, 0),
('1000000080', 'Metálico', 'H-6', 'a', 654, 'Multiplexor', '74HC157B1', 'N/A', 'N/A', 'Multiplexor Quad 2 canales', 'Sin utilizar', 42, 0),
('1000000081', 'Metálico', 'H-6', 'b', 654, 'Multiplexor', 'HD74LS157P', 'N/A', 'N/A', 'Multiplex.con s/noinversora sel. De datos', 'Sin utilizar', 34, 0),
('1000000082', 'Metálico', 'H-6', 'c', 654, 'Multiplexor', 'SN74LS251N', 'N/A', 'N/A', 'Selector de datos 3 estados  1 a 8 lineas', 'Sin utilizar', 56, 0),
('1000000083', 'Metálico', 'I-5', 'a', 654, 'Timer', 'NE556N', 'N/A', 'N/A', 'Timer Bipolar Dual de Proposito Genereal', 'Bajo', 35, 0),
('1000000084', 'Metálico', 'I-5', 'b', 654, 'Timer', 'DS 1307', 'N/A', 'N/A', 'Reloj de Tiempo Real 64 X 8', 'Sin utilizar', 11, 0),
('1000000085', 'Metálico', 'I-5', 'c', 654, 'Timer', 'DS 1302', 'N/A', 'N/A', 'Chip de Carga Horaria', 'Sin utilizar', 15, 0),
('1000000086', 'Metálico', 'I-6', 'a', 654, 'Timer', 'MC14541', 'N/A', 'N/A', 'Timer Programable', 'Sin utilizar', 15, 0),
('1000000087', 'Metalico', 'E-5', 'a', 654, 'Microprocesador', 'INS8080AN', 'N/A', 'N/A', 'Microprocesador 8 bits, CANAL N, 20V, 1.5 W', 'Bajo', 45, 0),
('1000000088', 'Metalico', 'E-6', 'a', 657, 'Programable', 'EP4CE622CB', 'N/A', 'N/A', 'Arreglo de Compuertas Programable en Campo', 'Alto', 13, 0),
('1000000089', 'Metalico', 'F-1', 'b', 654, 'Compuerta Lógica', 'HEF4011BP', 'S/N', 'N/A', 'Compuerta NAND Cuadruple 2 Entradas', 'Alto', 50, 0),
('1000000090', 'Metálico', 'A-2', 'b', 653, 'Temperatura', 'LM35DZ', 'N/A', 'N/A', 'Sensor de Temperatura de Precisión  ', 'Alto', 47, 0),
('1000000091', 'Metálico', 'A-3', 'a', 653, 'Infrarrojo', 'TSOP34156', 'N/A', 'N/A', 'Receptor Infrarrojo para control Remoto', 'Alto', 42, 0),
('1000000092', 'Metálico', 'A-4', 'b', 653, 'Temperatura Digital Serial', 'DS18B20', 'N/A', 'N/A', 'Termometro Digital de 9 a 12 bits', 'Sin utilizar', 10, 0),
('1000000093', 'Metálico', 'A-6', 'a', 655, 'Osilador de Cuarzo', 'N/A', '20.0', 'Mhz', 'Cristal de Cuarzo 20.0 MHz', 'Alto', 37, 0),
('1000000094', 'Metálico', 'B-5', 'c', 654, 'PLD Matriz lógico Generico', 'GAL16V8D', 'N/A', 'N/A', '   PLD,Arreglo Logico Generico E²CMOS', 'Alto', 52, 0),
('1000000095', 'Metálico', 'B-6', 'a', 654, 'PLD Matriz lógico Generico', 'GAL20V8B', 'N/A', 'N/A', '   PLD,Arreglo Logico Generico E²CMOS', 'Alto', 38, 0),
('1000000096', 'Metálico', 'D-4', 'a', 654, 'Decodificador', '74LS155', 'N/A', 'N/A', 'Decodificador - demultiplexor dual 1 de 4', 'Sin utilizar', 10, 0),
('1000000097', 'Metálico', 'D-4', 'b', 658, 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'Sin utilizar', 0, 0),
('1000000098', 'Metálico', 'D-6', 'a', 654, 'Convertidor', 'AD537JH', 'N/A', 'N/A', 'Convertidor Voltaje/Frecuencia', 'Sin utilizar', 20, 0),
('1000000099', 'Metálico', 'D-6', 'b', 654, 'Convertidor', 'ADC0801', 'N/A', 'N/A', 'Convert. A/D Compatible C/μprocesador 8 bits', 'Sin utilizar', 0, 0),
('1000000100', 'Metálico', 'E-2', 'b', 654, 'Convertidor', 'TL507CP', 'N/A', 'N/A', 'Convertidor Analogico/Digital', 'Sin utilizar', 70, 0),
('1000000101', 'Metálico', 'E-2', 'c', 654, 'Convertidor', 'LM331N', 'N/A', 'N/A', 'Convertidor Voltaje/Frecuencia', 'Bajo', 24, 0),
('1000000102', 'Metálico', 'E-3', 'a', 654, 'Convertidor', 'XR4151', 'N/A', 'N/A', 'Convertidor Voltaje/Frecuencia', 'Sin utilizar', 10, 0),
('1000000103', 'Metálico', 'E-3', 'b', 654, 'Convertidor', 'AD7523JN', 'N/A', 'N/A', 'Convertidor Digital/Analogico  8 bits', 'Sin utilizar', 25, 0),
('1000000104', 'Metálico', 'E-5', 'c', 654, 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'Sin utilizar', 0, 0),
('1000000105', 'Metálico', 'E-6', 'a', 654, 'Contador', 'DM74LS90N', 'N/A', 'N/A', 'Contador de Decadas y Binario', 'Medio', 32, 0),
('1000000106', 'Metálico', 'E-6', 'b', 654, 'Contador', 'SN74LS93N', 'N/A', 'N/A', 'Contador.Binario 4bits ', 'Alto', 34, 0),
('1000000107', 'Metálico', 'E-6', 'c', 654, 'Contador', 'SN74LS165N', 'N/A', 'N/A', 'Registro Shift Serie/Paralelo 8bits', 'Medio', 51, 0),
('1000000108', 'Metálico', 'F-1', 'a', 654, 'Contador', 'HD74LS192P', 'N/A', 'N/A', 'Contad. de Decad c/dual line clock up/down', 'Medio', 48, 0),
('1000000109', 'Metálico', 'F-1', 'b', 654, 'Contador', 'SN74LS193N', 'N/A', 'N/A', 'Cont. Decadas Up/Down-BCD/Preajustable', 'Medio', 42, 0),
('1000000110', 'Metálico', 'F-1', 'c', 654, 'Contador', 'CD74HC4518E', 'N/A', 'N/A', 'Cont Doble BCD-hacia/arriba. A/Velocidad', 'Alto', 88, 0),
('1000000111', 'Metálico', 'F-2', 'a', 654, 'Contador', 'HD14017BCP', 'N/A', 'N/A', 'Contador/Divisor de Decadas', 'Alto', 102, 0),
('1000000112', 'Metálico', 'F-2', 'b', 654, 'Contador', 'MC14060', 'N/A', 'N/A', 'Oscilador y Contador Binario de 14bits', 'Medio', 4, 0),
('1000000113', 'Metálico', 'F-2', 'c', 654, 'Contador', 'MC14520BCP', 'N/A', 'N/A', 'Contador Binario Doble Ascendente', 'Sin utilizar', 10, 0),
('1000000114', 'Metálico', 'F-3', 'a', 654, 'Contador', 'MC14553BCP', 'N/A', 'N/A', 'Contador BCD de 3 Digitos', 'Sin utilizar', 47, 0),
('1000000115', 'Metálico', 'F-3', 'b', 654, 'Timer', 'MC14541', 'N/A', 'N/A', 'Timer Programable', 'Bajo', 15, 0),
('1000000116', 'Metálico', 'F-5', 'a', 654, 'Compuerta Lógica', 'SN7400N', 'N/A', 'N/A', 'Compuerta NAND Cuadruple 2 Entradas', 'Bajo', 109, 0),
('1000000117', 'Metálico', 'F-5', 'b', 654, 'Compuerta Lógica', 'SN74LS02', 'N/A', 'N/A', 'Cuatro Compuertas NOR 2 entradas', 'Alto', 145, 0),
('1000000118', 'Metálico', 'F-5', 'c', 654, 'Compuerta Lógica', 'CD4001BE', 'N/A', 'N/A', 'Cmos Compuera NOR 2 Entradas Cuadruple', 'Bajo', 50, 0),
('1000000119', 'Metálico', 'F-6', 'a', 654, 'Compuerta Lógica', 'SN74LS04', 'N/A', 'N/A', 'Hex Inversor  NOT', 'Alto', 98, 0),
('1000000120', 'Metálico', 'F-6', 'b', 654, 'Compuerta Lógica', 'SN74LS08', 'N/A', 'N/A', 'Cuatro Compuertas AND 2 entradas', 'Alto', 88, 0),
('1000000121', 'Metálico', 'F-6', 'c', 654, 'Compuerta Lógica', 'SN74LS11', 'N/A', 'N/A', 'Triple Compuerta  AND  3 Entradas', 'Medio', 53, 0),
('1000000122', 'Metálico', 'G-1', 'a', 654, 'Compuerta Lógica', 'SN74LS32', 'N/A', 'N/A', 'Cuadruple Compuerta OR  2 Entradas', 'Alto', 82, 0),
('1000000123', 'Metálico', 'G-1', 'b', 654, 'Compuerta Lógica', 'SN74LS86AN', 'N/A', 'N/A', 'Cuad. Compuerta XOR  (OR exclusiva)', 'Alto', 118, 0),
('1000000124', 'Metálico', 'G-1', 'c', 654, 'Compuerta Lógica', 'CD4077', 'N/A', 'N/A', '4 Comouerta XOR Y XNOR', 'Sin utilizar', 18, 0),
('1000000125', 'Metálico', 'G-2', 'a', 654, 'Compuerta Lógica', 'SN74LS266', 'N/A', 'N/A', 'Cuad. Compuerta XNOR  (NOR exclusiva)', 'Alto', 72, 0),
('1000000126', 'Metálico', 'G-2', 'b', 654, 'Compuerta Lógica', 'CD74HC4049', 'N/A', 'N/A', 'Seis Inversor/Noinversor Buffers Logicos ', 'Bajo', 25, 0),
('1000000127', 'Metálico', 'G-2', 'c', 654, 'Compuerta Lógica', '74 LS 14', 'N/A', 'N/A', 'Doble comp.Seis Inversores Disparo Schmit', 'Sin utilizar', 10, 0),
('1000000128', 'Metálico', 'G-3', 'a', 654, 'Compuerta Lógica', 'MC74HC132N', 'N/A', 'N/A', 'Quad 2-entrad.Comp. NAND c/disparo Schmit', 'Bajo', 25, 0),
('1000000129', 'Metálico', 'G-3', 'b', 654, 'Microcontrolador', 'MC908QY4CP', 'N/A', 'N/A', 'Microcontrolador', 'Alto', 46, 0),
('1000000130', 'Metálico', 'G-3', 'c', 654, 'Microcontrolador', 'PIC12C508A', 'N/A', 'N/A', 'Microcontrolador PIC  8bits', 'Sin utilizar', 7, 0),
('1000000131', 'Metálico', 'G-4', 'a', 654, 'Microcontrolador', 'PIC16F84A-04/P', 'N/A', 'N/A', 'Flash/EEPROM. Microcontrolador 8 bits', 'Alto', 41, 0),
('1000000132', 'Metálico', 'G-4', 'b', 654, 'Microcontrolador', 'PIC16F628', 'N/A', 'N/A', 'Microcontrolador  8 bits  4MHz', 'Alto', 67, 0),
('1000000133', 'Metálico', 'G-4', 'c', 654, 'Microcontrolador', 'PIC16F873-04', 'N/A', 'N/A', 'Microcont.8bits Flash/CMOS 28  pins', 'Alto', 15, 0),
('1000000134', 'Metálico', 'G-5', 'a', 654, 'Microcontrolador', 'PIC16F874-A', 'N/A', 'N/A', 'Microcont.8bits Flash/CMOS 40  pins', 'Alto', 45, 0),
('1000000135', 'Metálico', 'G-5', 'b', 654, 'Microcontrolador', 'Z80PIO', 'N/A', 'N/A', 'Microcontrolador  8 bits  4MHz', 'Sin utilizar', 2, 0),
('1000000136', 'Metálico', 'G-5', 'c', 654, 'Microcontrolador', 'DSPIC30F3011', 'N/A', 'N/A', 'Controlador de Señal Digital', 'Sin utilizar', 11, 0),
('1000000137', 'Metálico', 'G-6', 'a', 654, 'Microcontrolador', 'DSPIC30F4013', 'N/A', 'N/A', 'Controlad. de Señal Digital (proposito gen)', 'Sin utilizar', 7, 0),
('1000000138', 'Metálico', 'G-6', 'b', 654, 'Microcontrolador', 'DSPIC30F3012', 'N/A', 'N/A', 'Controlad. de Señal Digital (familia de sensores)', 'Sin utilizar', 9, 0),
('1000000139', 'Metálico', 'G-6', 'c', 654, 'Microcontrolador', 'PIC18F4550', 'N/A', 'N/A', 'Microcontrolador Tecnologia nanowatt', 'Alto', 29, 0),
('1000000140', 'Metálico', 'H-1', 'a', 654, 'Microcontrolador', 'PIC16F877', 'N/A', 'N/A', 'Microcontrolador CMOS 8 Bits 40 PIN', 'Alto', 30, 0),
('1000000141', 'Metálico', 'H-1', 'b', 654, 'Flip Flop', 'HD74LS73', 'N/A', 'N/A', 'Doble Flip-Flop  \"J-K\"', 'Alto', 89, 0),
('1000000142', 'Metálico', 'H-1', 'c', 654, 'Flip Flop', 'HC74LS74', 'N/A', 'N/A', 'Flip-Flop Tipo \"D\" Positivo Edge Triggered', 'Alto', 50, 0),
('1000000143', 'Metálico', 'H-2', 'a', 654, 'Flip Flop', 'HD74LS76', 'N/A', 'N/A', 'Doble Flip-Flop  \"J-K\" C/Reloj Individual', 'Alto', 26, 0),
('1000000144', 'Metálico', 'H-2', 'b', 654, 'Flip Flop', '74LS107N', 'N/A', 'N/A', 'J-K Flip-Flop Lo-Pwr Schottky Dual', 'Alto', 55, 0),
('1000000145', 'Metálico', 'H-2', 'c', 654, 'Flip Flop', 'SN74LS174N', 'N/A', 'N/A', 'Seis Flip-Flop tipo  D', 'Alto', 57, 0),
('1000000146', 'Metálico', 'H-3', 'a', 654, 'Flip Flop', 'SN74LS175N', 'N/A', 'N/A', 'Cuatro Flip-Flop  tipo  D', 'Alto', 31, 0),
('1000000147', 'Metálico', 'H-3', 'b', 654, 'Flip Flop', 'SN74LS376N', 'N/A', 'N/A', 'Flip-Flop Octal tipo D 3 Estados de Salida', 'Sin utilizar', 19, 0),
('1000000148', 'Metálico', 'H-3', 'c', 654, 'Flip Flop', 'MC845L', 'N/A', 'N/A', 'FLIP-FLOP Sencillo R-S', 'Alto', 30, 0),
('1000000149', 'Metálico', 'H-4', 'a', 654, 'Flip Flop', 'CD4013BE', 'N/A', 'N/A', 'FLIP-FLOP Dual  CMOS  Tipo D', 'Alto', 25, 0),
('1000000150', 'Metálico', 'H-4', 'b', 658, 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'Sin utilizar', 0, 0),
('1000000151', 'Metálico', 'H-4', 'c', 654, 'Sumador', '74 LS 83', 'N/A', 'N/A', 'Sumador Binario c/Transportador rapido', 'Sin utilizar', 19, 0),
('1000000152', 'Metálico', 'H-5', 'a', 658, 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'Sin utilizar', 0, 0),
('1000000153', 'Metálico', 'I-1', 'a', 654, 'Optoaislador', '4N25', 'N/A', 'N/A', 'Optoacoplador/Fototransistor 6 pin ', 'Alto', 26, 0),
('1000000154', 'Metálico', 'I-1', 'b', 654, 'Optoaislador', '4N28', 'N/A', 'N/A', 'Optoacoplador/Fototransistor 6 pin ', 'Alto', 32, 0),
('1000000155', 'Metálico', 'I-1', 'c', 654, 'Optoaislador', '4N30', 'N/A', 'N/A', 'Optoacoplador/FotoDarlington  Propo. Gral ', 'Medio', 14, 0),
('1000000156', 'Metálico', 'I-2', 'a', 654, 'Optoaislador', '4N32', 'N/A', 'N/A', 'Optoacoplador/FotoDarlington 6 pin ', 'Medio', 68, 0),
('1000000157', 'Metálico', 'I-2', 'b', 654, 'Optoaislador', '4N40', 'N/A', 'N/A', 'Optoacoplador  Foto SCR', 'Medio', 27, 0),
('1000000158', 'Metálico', 'I-2', 'c', 654, 'Optoaislador', 'MOC3010', 'N/A', 'N/A', 'Optoaislador Salida TRIAC Fase/Random', 'Alto', 51, 0),
('1000000159', 'Metálico', 'I-3', 'a', 654, 'Optoaislador', 'MOC3011', 'N/A', 'N/A', 'Optoaislador Salida TRIAC Fase/Random', 'Alto', 70, 0),
('1000000160', 'Metálico', 'I-3', 'b', 654, 'Optoaislador', 'MOC 3041', 'N/A', 'N/A', 'Optoaislador Conductor Salida TRIAC', 'Medio', 39, 0),
('1000000161', 'Metálico', 'I-3', 'c', 654, 'Optoaislador', 'H11C3', 'N/A', 'N/A', 'Optoaisladsor Foto SCR', 'Sin utilizar', 13, 0),
('1000000162', 'Metálico', 'I-4', 'a', 654, 'Optoaislador', 'H11AA1', 'N/A', 'N/A', 'Optoacoplador Entrada Fototransistor AC', 'Sin utilizar', 12, 0),
('1000000163', 'Metálico', 'I-4', 'b', 654, 'Optoaislador', 'PS2002B', 'N/A', 'N/A', 'Optoacoplador salida Darlington', 'Sin utilizar', 14, 0),
('1000000164', 'Metálico', 'I-4', 'c', 654, 'Timer', 'NE555N', 'N/A', 'N/A', 'Timer Monolitico Lineal', 'Alto', 109, 0),
('1000000165', 'Metálico', 'I-6', 'b', 654, 'Driver', 'NTE 2022', 'N/A', 'N/A', 'Display Driver 8 Digitos/Segmentos', 'Sin utilizar', 6, 0),
('1000000166', 'Metálico', 'I-6', 'c', 654, 'Driver', 'L 293 C', 'N/A', 'N/A', 'Cuatro Drivers Half-H', 'Alto', 52, 0),
('1000000167', 'Metálico', 'J-1', 'a', 654, 'Driver', 'L296B', 'N/A', 'N/A', 'Driver 4 Canales Push-Pull', 'Alto', 38, 0),
('1000000168', 'Metálico', 'J-1', 'b', 659, '7 Segmentos / 1 Digito', 'DA-05', 'N/A', 'N/A', 'Anodo Comun', 'Alto', 99, 0),
('1000000169', 'Metálico', 'J-1', 'c', 659, '7 Segmentos / 2 Digitos', 'DA-05-2', 'N/A', 'N/A', 'Anodo Comun', 'Medio', 43, 0),
('1000000170', 'Metálico', 'J-2', 'a', 659, '7 Segmentos / 1 Digito', 'DC-05', 'N/A', 'N/A', 'Catodo Comun', 'Alto', 55, 0),
('1000000171', 'Metálico', 'J-2', 'b', 659, '7 Segmentos / 2 Digitos', 'DC05-2', 'N/A', 'N/A', 'Catodo Comun', 'Medio', 34, 0),
('1000000172', 'Metálico', 'J-2', 'c', 659, '7 Segmentos / 3 Digitos', 'BA5611HWA', 'N/A', 'N/A', 'Anodo Comun', 'Bajo', 9, 0),
('1000000173', 'Metálico', 'J-3', 'a', 659, '7 Segmentos / 4 Digitos', 'CA56-11HWA', 'N/A', 'N/A', 'Anodo Comun', 'Bajo', 10, 0),
('1000000174', 'Metálico', 'J-3', 'b', 659, 'Alfanumerico 16 Segmentos / 2 Digitos', 'GM2-5241CSB', 'N/A', 'N/A', 'Catodo Comun', 'Bajo', 30, 0),
('1000000175', 'Metálico', 'J-3', 'c', 659, 'Alfanumerico 16 Segmentos / 1 Digito', 'MSA5180C', 'N/A', 'N/A', 'Catodo Comun', 'Bajo', 29, 0),
('1000000176', 'Metálico', 'N/A', 'b', 659, 'Alfanumerico 16 Seg./1 Digito 4.3x3.1 cm', 'GM1-15101CSB', 'N/A', 'N/A', 'Catodo Comun', 'Sin utilizar', 15, 0),
('1000000177', 'Metálico', 'J-4', 'a', 659, 'LCD', 'HDM116216L-2-L30P', 'N/A', 'N/A', 'Modulo Display LCD 16X2 Lineas', 'Alto', 7, 0),
('1000000178', 'Metálico', 'J-4', 'b', 659, 'LCD', '1602A', 'N/A', 'N/A', 'Modulo LCD 2x16 Fondo Azul, Arduino/PIC', 'Alto', 10, 0),
('1000000179', 'Metálico', 'J-4', 'c', 659, 'LCD', 'LCD1602+12C', 'N/A', 'N/A', 'Modulo LCD 2x16 Para Arduino', 'Alto', 10, 0),
('1000000180', 'Metálico', 'J-5', 'a', 659, 'LCD', '2004A', 'N/A', 'N/A', 'Modulo LCD Alfanumerico P/ Arduino 20X4 lineas', 'Alto', 5, 0),
('1000000181', 'Metálico', 'J-5', 'b', 659, '10 Segmentos', 'BAR-10UR', 'N/A', 'N/A', 'Barra de LED`S 10 Segmentos', 'Alto', 17, 0),
('1000000182', 'Metálico', 'J-5', 'c', 660, 'Rojo', 'N/A', 'N/A', 'N/A', '  LED      2.1 V', 'Alto', 200, 0),
('1000000183', 'Metálico', 'J-6', 'a', 660, 'Verde', 'N/A', 'N/A', 'N/A', '  LED      2.1 V', 'Alto', 285, 0),
('1000000184', 'Metálico', 'J-6', 'b', 660, 'Ambar', 'N/A', 'N/A', 'N/A', '  LED      2.1 V', 'Alto', 225, 0),
('1000000185', 'Metálico', 'J-6', 'c', 660, 'Bicolor', 'N/A', 'N/A', 'N/A', '  LED      2.1 V', 'Alto', 224, 0),
('1000000186', 'Metálico', 'K-1', 'a', 660, 'Rojo', 'N/A', 'N/A', 'N/A', 'LED  Gigante Rojo                2.1 V  20mA', 'Medio', 88, 0),
('1000000187', 'Metálico', 'K-1', 'b', 660, 'Verde', 'N/A', 'N/A', 'N/A', 'LED  Gigante Verde               2.1 V  20mA', 'Medio', 19, 0),
('1000000188', 'Metálico', 'K-1', 'c', 660, 'Ambar', 'N/A', 'N/A', 'N/A', 'LED  Gigante Ambar              2.1 V  20mA', 'Medio', 6, 0),
('1000000189', 'Metálico', 'K-2', 'a', 660, 'Infrarrojo', 'N/A', 'N/A', 'N/A', '1.5 V', 'Alto', 167, 0),
('1000000190', 'Metálico', 'K-2', 'b', 660, 'Diodo Emisor de Luz', 'BTGC-30 TA', 'N/A', 'N/A', '3V', 'Medio', 3, 0),
('1000000191', 'Metálico', 'K-2', 'c', 660, 'Diodo Emisor de Luz', '30RGBCSC UP105', 'N/A', 'N/A', 'N/A', 'Bajo', 83, 0),
('1000000192', 'Metálico', 'K-3', 'a', 660, 'Blanco Brillante', 'Blanco Brillante', 'N/A', 'N/A', 'LED        2.1V', 'Medio', 19, 0),
('1000000193', 'Metálico', 'K-3', 'b', 661, 'Diodo Emisor de Luz', 'GMM-12088 ASB', 'N/A', 'N/A', 'Matriz de LED 8x8  y  7x5', 'Bajo', 18, 0),
('1000000194', 'Metálico', 'N/A', 'a', 660, 'Matriz de LED', 'GMM-23088 ASB', 'N/A', 'N/A', 'Matriz de LED  5mm  8x8  Rojo, Catodo a Renglon', 'Bajo', 18, 0),
('1000000195', 'Metálico', 'K-3', 'c', 662, 'SCR', 'C122B', 'N/A', 'N/A', 'SCR  8A RMS  50-800V', 'Medio', 8, 0),
('1000000196', 'Metálico', 'K-4', 'a', 662, 'SCR', 'C122F', 'N/A', 'N/A', 'SCR 8A RMS 800V', 'Medio', 89, 0),
('1000000197', 'Metálico', 'K-4', 'b', 662, 'SCR', '2N5061(L9)', 'N/A', 'N/A', 'SCR 60 V  0,8A Puerta sensitivo', 'Medio', 26, 0),
('1000000198', 'Metálico', 'K-4', 'c', 662, 'SCR', '2N5062(L8)', 'N/A', 'N/A', 'SCR 100 V  0,8A Puerta sensitivo', 'Medio', 30, 0),
('1000000199', 'Metálico', 'K-5', 'a', 662, 'SCR', '2N6397', 'N/A', 'N/A', 'SCR 12A RMS 50-800V', 'Medio', 53, 0),
('1000000200', 'Metálico', 'K-5', 'b', 662, 'SCR', '2N6506(L911)', 'N/A', 'N/A', 'SCR 200 VRM  25A', 'Medio', 25, 0),
('1000000201', 'Metálico', 'K-5', 'c', 662, 'SCR', 'C106D', 'N/A', 'N/A', 'SCR  4A RMS 200-600V', 'Medio', 135, 0),
('1000000202', 'Metálico', 'K-6', 'a', 662, 'SCR', 'TIC106D', 'N/A', 'N/A', 'SCR 8 A    400-800V', 'Medio', 25, 0),
('1000000203', 'Metálico', 'K-6', 'b', 662, 'SCR', 'S6010L', 'N/A', 'N/A', 'SCR 1-70A  Puerta no Sensitiva', 'Medio', 29, 0),
('1000000204', 'Metálico', 'K-6', 'c', 662, 'SCR', '2N6395', 'N/A', 'N/A', 'SCR  0,8A Puerta sensitivo', 'Medio', 10, 0),
('1000000205', 'Metálico', 'L-1', 'a', 662, 'SCR', '2N 6239', 'N/A', 'N/A', 'SCR 200 VRM  25 A', 'Medio', 28, 0),
('1000000206', 'Metálico', 'L-1', 'b', 662, 'SCR', '2N6508', 'n/a', 'N/A', 'SCR 50-800 VMR 25A', 'Medio', 40, 0),
('1000000207', 'Metálico', 'L-1', 'c', 663, 'Cerámico', '1.8pF', '1.8', 'pF', 'Cerámico         1.8pF        0.0018nF', 'Medio', 191, 0),
('1000000208', 'Metálico', 'L-2', 'a', 663, 'Cerámico', '2.5', '2.5', 'pF', 'Cerámico        2.5pF         0.0025nF', 'Medio', 85, 0),
('1000000209', 'Metálico', 'L-2', 'b', 663, 'Cerámico', '3.3', '3.3', 'pF', 'Cerámico        3.3pF         0.0033nF', 'Medio', 30, 0),
('1000000210', 'Metálico', 'L-2', 'c', 663, 'Cerámico', '3.9', '3.9', 'pF', 'Cerámico        3.9pF        0.0039nF', 'Medio', 51, 0),
('1000000211', 'Metálico', 'L-3', 'a', 663, 'Cerámico', '4.7', '4.7', 'pF', 'Cerámico         4.7pF        0.0047nF', 'Medio', 97, 0),
('1000000212', 'Metálico', 'L-3', 'b', 663, 'Cerámico', '5.6', '5.6', 'pF', 'Cerámico        5.6pF        0.0056nF', 'Medio', 53, 0),
('1000000213', 'Metálico', 'L-3', 'c', 663, 'Cerámico', '10.0', '10.0', 'pF', 'Cerámico         10pF        0.010nF', 'Alto', 81, 0),
('1000000214', 'Metálico', 'L-4', 'a', 663, 'Cerámico', '22CH', '22.0', 'pF', 'Cerámico           22pF        0.022nF', 'Alto', 208, 0),
('1000000215', 'Metálico', 'L-4', 'b', 663, 'Cerámico', '33CH', '33.0', 'pF', 'Cerámico         33pF          0.033nF', 'Medio', 60, 0),
('1000000216', 'Metálico', 'L-4', 'c', 663, 'Cerámico', '51J', '51.0', 'pF', 'Cerámico         51pF          0.051nF', 'Alto', 26, 0),
('1000000217', 'Metálico', 'L-5', 'a', 663, 'Cerámico', '101.0', '101.0', 'pF', 'Cerámico       101pF         0.101nF', 'Alto', 131, 0),
('1000000218', 'Metálico', 'L-5', 'b', 663, 'Cerámico', '270C', '270.0', 'pF', 'Cerámico       270pF    63V    0.270nF', 'Alto', 21, 0),
('1000000219', 'Metálico', 'L-5', 'c', 663, 'Cerámico', '331k', '330.0', 'pF', 'Cerámico           330 Pf       0.33 nF', 'Medio', 50, 0),
('1000000220', 'Metálico', 'L-6', 'a', 663, 'Cerámico', 'n47', '470.0', 'pF', 'Cerámico          470pF        0.47nF', 'Medio', 87, 0),
('1000000221', 'Metálico', 'L-6', 'b', 663, 'Cerámico', '821csm', '820.0', 'pF', 'Cerámico          820pF        0.82nF', 'Medio', 28, 0),
('1000000222', 'Metálico', 'L-6', 'c', 663, 'Cerámico', '102.0', '1.0', 'nF', 'Cerámico              1nF         .001µF', 'Alto', 26, 0),
('1000000223', 'Metálico', 'M-1', 'a', 663, 'Cerámico', '222.0', '2.2', 'nF', 'Cerámico              2,2nF         .0022µF', 'Alto', 70, 0),
('1000000224', 'Metálico', 'M-1', 'b', 663, 'Cerámico', '472.0', '4.7', 'nF', 'Cerámico            4,7nF       .0047µF', 'Alto', 38, 0),
('1000000225', 'Metálico', 'M-1', 'c', 663, 'Cerámico', '103.0', '10.0', 'nF', 'Cerámico        10nF 25V       0.01µF', 'Alto', 31, 0),
('1000000226', 'Metálico', 'M-2', 'a', 663, 'Cerámico', '22.0', '22.0', 'nF', 'Cerámico            22nF       .022µF', 'Alto', 29, 0),
('1000000227', 'Metálico', 'M-2', 'b', 663, 'Cerámico', '473.0', '47.0', 'nF', 'Cerámico          47nF          0.047µF', 'Alto', 121, 0),
('1000000228', 'Metálico', 'M-2', 'c', 663, 'Cerámico', '104Z', '100.0', 'nF', 'Cerámico           100nF           0.1µF', 'Alto', 193, 0),
('1000000229', 'Metálico', 'M-3', 'a', 663, 'Cerámico', '224.0', '220.0', 'nF', 'Cerámico          220nF          0.22µF', 'Alto', 78, 0),
('1000000230', 'Metálico', 'M-3', 'b', 663, 'Electrolítico', 'MKT 1,0', '1.0', 'μF', '1.0 μF          1.0 µF', 'Alto', 74, 0),
('1000000231', 'Metálico', 'M-3', 'c', 663, 'Electrolítico', '0,22µF', '0.22', 'μF', 'Electrolítico  50V', 'Medio', 33, 0),
('1000000232', 'Metálico', 'M-4', 'a', 663, 'Electrolítico', '0,33µF', '0.33', 'μF', 'Electrolítico  200V', 'Medio', 82, 0),
('1000000233', 'Metálico', 'M-4', 'b', 663, 'Electrolítico', '0,47µF', '0.47', 'μF', 'Electrolítico  200V', 'Medio', 56, 0),
('1000000234', 'Metálico', 'M-4', 'c', 663, 'Electrolítico', ',1μF', '0.1', 'μF', 'Electrolítico  50 V', 'Alto', 55, 0),
('1000000235', 'Metálico', 'M-5', 'a', 663, 'Electrolítico', '1µF', '1.0', 'μF', 'Electrolítico  63V', 'Alto', 90, 0),
('1000000236', 'Metálico', 'M-5', 'b', 663, 'Electrolítico', '2,2µF', '2.2', 'μF', 'Electrolítico  50V', 'Alto', 117, 0),
('1000000237', 'Metálico', 'M-5', 'c', 663, 'Electrolítico', '4,7µF', '4.7', 'μF', 'Electrolítico  50V-350V', 'Alto', 199, 0),
('1000000238', 'Metálico', 'M-6', 'a', 663, 'Electrolítico', '10µF', '10.0', 'μF', 'Electrolítico  50V,63V', 'Alto', 292, 0),
('1000000239', 'Metálico', 'M-6', 'b', 663, 'Electrolítico', '15 µf', '15.0', 'μF', 'Electrolítico  63V', 'Alto', 22, 0),
('1000000240', 'Metálico', 'M-6', 'c', 663, 'Electrolítico', '22µF', '22.0', 'μF', 'Electrolítico  63V', 'Alto', 140, 0),
('1000000241', 'Metálico', 'N-1', 'a', 663, 'Electrolítico', '47µF', '47.0', 'μF', 'Electrolítico  63V', 'Alto', 56, 0),
('1000000242', 'Metálico', 'N-1', 'b', 663, 'Electrolítico', '100µF', '100.0', 'μF', 'Electrolítico  50V, 63v', 'Alto', 77, 0),
('1000000243', 'Metálico', 'N-1', 'c', 663, 'Electrolítico', '220µF', '220.0', 'μF', 'Electrolítico  63V', 'Alto', 78, 0),
('1000000244', 'Metálico', 'N-2', 'a', 663, 'Electrolítico', '470µF', '470.0', 'μF', 'Electrolítico  25/50V', 'Alto', 51, 0),
('1000000245', 'Metálico', 'N-2', 'b', 663, 'Electrolítico', '1000µF', '1000.0', 'μF', 'Electrolítico  25V', 'Alto', 24, 0),
('1000000246', 'Metálico', 'N-2', 'c', 663, 'Electrolítico', '2200µF', '2200.0', 'μF', 'Electrolítico  50V', 'Alto', 35, 0),
('1000000247', 'Metálico', 'N-3', 'a', 663, 'Electrolítico', '100µF', '100.0', 'μF', 'Electrolítico  400V', 'Medio', 16, 0),
('1000000248', 'Metálico', 'N-3', 'b', 663, 'Electrolítico', '470µF', '470.0', 'μF', 'Electrolítico  400 V', 'Medio', 17, 0),
('1000000249', 'Metálico', 'N-3', 'c', 663, 'Electrolítico', '4700µF', '4700.0', 'μF', 'Electrolítico  50 V', 'Medio', 11, 0),
('1000000250', 'Metálico', 'N-4', 'a', 663, 'Electrolítico', '2.2nf', '2.2', 'nF', 'Electrolítico  250V', 'Bajo', 20, 0),
('1000000251', 'Metálico', 'N-4', 'b', 663, 'Electrolítico', '4.70µF', '4.7', 'μF', 'Electrolítico  250V', 'Bajo', 20, 0),
('1000000252', 'Metálico', 'N-4', 'c', 663, 'Electrolítico', '10000µF', '10000.0', 'μF', 'Electrolítico  50 V', 'Medio', 9, 0),
('1000000253', 'Metálico', 'N-5', 'a', 664, 'Triac', 'TIC206D', 'N/A', 'N/A', '400 VRM 8A Sensitive Gate', 'Bajo', 14, 0),
('1000000254', 'Metálico', 'N-5', 'b', 664, 'Triac', 'TIC226D', 'N/A', 'N/A', '400 VRM 8A ', 'Bajo', 4, 0),
('1000000255', 'Metálico', 'N-5', 'c', 664, 'Triac', '2N6644', 'N/A', 'N/A', '600 VRM 15 A', 'Bajo', 16, 0),
('1000000256', 'Metálico', 'N-6', 'a', 664, 'Triac', 'MAC12', 'N/A', 'N/A', 'TRIAC, 12 A  800 V', 'Alto', 78, 0),
('1000000257', 'Metálico', 'N-6', 'b', 664, 'Triac', 'MAC15', 'N/A', 'N/A', 'TRIAC, 15A', 'Alto', 38, 0),
('1000000258', 'Metálico', 'N-6', 'c', 664, 'Triac', 'MAC97AB', 'N/A', 'N/A', '200 VRM, 08A', 'Bajo', 28, 0),
('1000000259', 'Metálico', 'O-1', 'a', 664, 'Triac', 'SC146B', 'N/A', 'N/A', '200 VRM  10A', 'Bajo', 31, 0),
('1000000260', 'Metálico', 'O-1', 'b', 664, 'Triac', 'Q4008L4', 'N/A', 'N/A', '50 VRM  10A', 'Bajo', 28, 0),
('1000000261', 'Metálico', 'O-1', 'c', 665, 'Positivo', '7805CT', 'N/A', 'N/A', 'Regulador de Voltaje Positivo  1A, 5V', 'Alto', 66, 0),
('1000000262', 'Metálico', 'O-2', 'a', 665, 'Positivo', 'L7808C', 'N/A', 'N/A', 'Regulador de Voltaje Positivo  1A, 8V', 'Alto', 20, 0),
('1000000263', 'Metálico', 'O-2', 'b', 665, 'Positivo', 'MC7812CT', 'N/A', 'N/A', 'Regulador de Voltaje Positivo  1A, 12V', 'Alto', 54, 0),
('1000000264', 'Metálico', 'O-2', 'c', 665, 'Positivo', 'MC7815CT', 'N/A', 'N/A', 'Regulador de Voltaje Positivo  1A, 15V', 'Alto', 28, 0),
('1000000265', 'Metálico', 'O-3', 'a', 665, 'Positivo', 'LM7818T', 'N/A', 'N/A', 'Regulador de Voltaje Positivo  1A, 18V', 'Alto', 45, 0),
('1000000266', 'Metálico', 'O-3', 'b', 665, 'Positivo', 'LM7824T', 'N/A', 'N/A', 'Regulador de Voltaje Positivo  1A, 24V', 'Alto', 44, 0),
('1000000267', 'Metálico', 'O-3', 'c', 665, 'Negativo', 'LM7905CT', 'N/A', 'N/A', 'Regulador de Voltaje Negativo  1A, 5V', 'Alto', 20, 0),
('1000000268', 'Metálico', 'O-4', 'a', 665, 'Negativo', 'L7908A', 'N/A', 'N/A', 'Regulador de Voltaje Negativo  1A, 8V', 'Alto', 21, 0),
('1000000269', 'Metálico', 'O-4', 'b', 665, 'Negativo', 'MC7912C', 'N/A', 'N/A', 'Regulador de Voltaje Negativo  1A, 12V', 'Alto', 28, 0),
('1000000270', 'Metálico', 'O-4', 'c', 665, 'Negativo', 'MC7918C', 'N/A', 'N/A', 'Regulador de Voltaje Negativo  1A, 18V', 'Alto', 27, 0),
('1000000271', 'Metálico', 'O-5', 'a', 665, 'Negativo', 'LM7924', 'N/A', 'N/A', 'Regulador de Voltaje Negativo  1A, 24V', 'Sin utilizar', 23, 0),
('1000000272', 'Metálico', 'O-5', 'b', 665, 'Positivo Ajustable', 'LM317', 'N/A', 'N/A', 'Reg.de Voltaje Pos. Ajustable 1,2 a 37V 3A', 'Medio', 25, 0),
('1000000273', 'Metálico', 'O-5', 'c', 665, 'Positivo Ajustable', 'LM338K', 'N/A', 'N/A', 'Reg.de Voltaje Pos. Ajustable 1,2 a 32V 5A', 'Medio', 20, 0),
('1000000274', 'Metálico', 'O-6', 'a', 665, 'Positivo Ajustable', 'LM350K', 'N/A', 'N/A', 'Reg.de Voltaje Pos. Ajustable 1,2 a 33V.3A', 'Medio', 27, 0),
('1000000275', 'Metálico', 'O-6', 'b', 665, 'NPN', 'J13007-2', 'N/A', 'N/A', 'Transistor Epitaxial De Silicio NPN', 'Bajo', 48, 0),
('1000000276', 'Metálico', 'E-3', 'a', 666, 'NPN', 'MJ15015', 'N/A', 'N/A', 'Transistor NPN de Potencia de 15 A', 'Bajo', 14, 0),
('1000000277', 'Metálico', 'P-1', 'a', 666, 'PNP', 'BD136', 'N/A', 'N/A', 'Transistor PNP de Silicio', 'Bajo', 39, 0),
('1000000278', 'Metálico', 'P-1', 'b', 666, 'NPN', 'TIP-31C', 'N/A', 'N/A', 'Transistor de Potencia NPN 3 A', 'Alto', 0, 0),
('1000000279', 'Metálico', 'P-1', 'c', 666, 'NPN', 'TIP-41C', 'N/A', 'N/A', 'Transistor de Potencia NPN  6 A', 'Alto', 63, 0),
('1000000280', 'Metálico', 'P-2', 'a', 666, 'PNP', 'TIP-42C', 'N/A', 'N/A', 'Transistor de Potencia PNP  6 A', 'Alto', 87, 0),
('1000000281', 'Metálico', 'P-2', 'b', 666, 'NPN', '2N3055', 'N/A', 'N/A', 'Transistor NPN de Potencia de 15 A', 'Alto', 47, 0),
('1000000282', 'Metálico', 'P-2', 'c', 666, 'NPN', '2N6284', 'N/A', 'N/A', 'Transistor NPN de Potencia de 20 A', 'Bajo', 35, 0),
('1000000283', 'Metálico', 'P-3', 'a', 666, 'PNP', '2N6287', 'N/A', 'N/A', 'Transistor PNP de Potencia de 20 A', 'Bajo', 23, 0),
('1000000284', 'Metálico', 'P-3', 'b', 666, 'PNP / NPN', '2SD745', 'N/A', 'N/A', 'Transistor Difuso Triple  PNP/NPN', 'Bajo', 17, 0),
('1000000285', 'Metálico', 'P-3', 'c', 666, 'PNP Darlington', 'MJ3500', 'N/A', 'S/N', 'Transistor de potencia Darlington 10A PNP', 'Bajo', 0, 0),
('1000000286', 'Metálico', 'P-4', 'a', 666, 'PNP', 'MJ15016', 'S/N', 'S/N', 'Transistor de Potencia 15A, 120V, PNP', 'Bajo', 26, 0),
('1000000287', 'Metálico', 'P-4', 'b', 666, 'PNP', '2SA1301', 'N/A', 'N/A', 'Transistor PNP ', 'Bajo', 42, 0),
('1000000288', 'Metálico', 'P-4', 'c', 667, 'Peine', '8X-1-101 LF', '100.0', 'Ω', 'Banco de resitencias Tipo Peine  100 Ω', 'Sin utilizar', 79, 0),
('1000000289', 'Metálico', 'P-5', 'a', 667, 'Peine', '8X-1-331 LF', '330.0', 'Ω', 'Banco de resitencias Tipo Peine  330 Ω', 'Sin utilizar', 67, 0),
('1000000290', 'Metálico', 'P-5', 'b', 667, 'Peine', 'L083S 103LF', '10.0', 'kΩ', 'Banco de resitencias Tipo Peine  10 KΩ', 'Sin utilizar', 76, 0),
('1000000291', 'Metálico', 'P-5', 'c', 667, 'Peine', '06G0 472B0E', '4.7', 'kΩ', 'Banco de resitencias Tipo Peine  4,7 KΩ', 'Sin utilizar', 80, 0),
('1000000292', 'Metálico', 'P-6', 'a', 667, 'Circuito Integrado', 'LF-1-101', '100.0', 'Ω', 'Banco de resitencias Tipo   C:I    100 Ω', 'Sin utilizar', 69, 0),
('1000000293', 'Metálico', 'P-6', 'b', 667, 'Circuito Integrado', 'LF-2-103', '10.0', 'Ω', 'Banco de resitencias Tipo   C:I    10 KΩ', 'Sin utilizar', 84, 0),
('1000000294', 'Metálico', 'P-6', 'c', 667, 'Circuito Integrado', ' LF-1-334', '330.0', 'Ω', 'Banco de resitencias Tipo   C:I   330 KΩ', 'Sin utilizar', 78, 0),
('1000000295', 'N/A', 'N/A', 'a', 668, 'Multivuelta', '200Ω', '200.0', 'Ω', 'Multivuelta o Trim-pot ,05w', 'Bajo', 25, 0),
('1000000296', 'N/A', 'N/A', 'b', 668, 'Multivuelta', '500Ω', '500.0', 'Ω', 'Multivuelta o Trim-pot ,05w', 'Bajo', 32, 0),
('1000000297', 'N/A', 'N/A', 'c', 668, 'Multivuelta', '1 KΩ', '1.0', 'kΩ', 'Multuvuelta ò Trim-Pot ,05W', 'Bajo', 38, 0),
('1000000298', 'N/A', 'N/A', 'a', 668, 'Multivuelta', '5 KΩ', '5.0', 'kΩ', 'Multuvuelta ò Trim-Pot ,05W', 'Bajo', 14, 0),
('1000000299', 'N/A', 'N/A', 'b', 668, 'Multivuelta', '10 KΩ', '10.0', 'kΩ', 'Multuvuelta ò Trim-Pot ,05W', 'Bajo', 16, 0),
('1000000300', 'N/A', 'N/A', 'c', 668, 'Multivuelta', '50 KΩ', '50.0', 'kΩ', 'Multuvuelta ò Trim-Pot ,05W', 'Bajo', 21, 0),
('1000000301', 'N/A', 'N/A', 'a', 668, 'Multivuelta', '100 KΩ', '100.0', 'kΩ', 'Multuvuelta ò Trim-Pot ,05W', 'Alto', 13, 0),
('1000000302', 'N/A', 'N/A', 'b', 668, 'Rotatorio', '1 KΩ', '1.0', 'kΩ', 'Miniatura c/Caña Estriada s/Interruptor', 'Alto', 48, 0),
('1000000303', 'N/A', 'N/A', 'c', 668, 'Rotatorio', '5 KΩ', '5.0', 'kΩ', 'De Cartbòn c/Caña Estriada c/Switch', 'Alto', 24, 0),
('1000000304', 'N/A', 'N/A', 'a', 668, 'Rotatorio', '10 KΩ', '10.0', 'kΩ', 'c/caña Estriada', 'Alto', 61, 0),
('1000000305', 'N/A', 'N/A', 'b', 668, 'Rotatorio', '50 KΩ', '50.0', 'kΩ', 'Miniatura c/Caña Estriada s/Interruptor', 'Alto', 46, 0),
('1000000306', 'N/A', 'N/A', 'c', 668, 'Rotatorio', '100 KΩ', '100.0', 'kΩ', 'Miniatura c/Caña Estriada s/Interruptor', 'Alto', 47, 0),
('1000000307', 'N/A', 'N/A', 'a', 668, 'Rotatorio', '500 KΩ', '500.0', 'kΩ', 'De Cartbòn c/Caña Estriada s/Switch', 'Medio', 53, 0),
('1000000308', 'N/A', 'N/A', 'b', 668, 'Rotatorio', '1 MΩ', '1.0', 'MΩ', 'De Cartbòn c/Caña Lisa s/Switch', 'Bajo', 18, 0),
('1000000309', 'N/A', 'N/A', 'c', 668, 'Rotatorio', '10 MΩ', '10.0', 'MΩ', 'De Cartbòn c/Caña Lisa s/Switch', 'Bajo', 36, 0),
('1000000310', 'N/A', 'N/A', 'a', 668, 'Rotatorio', '15 MΩ', '15.0', 'MΩ', 'De Cartbòn c/Caña Estriada s/Switch', 'Bajo', 4, 0),
('1000000311', 'N/A', 'N/A', 'b', 669, 'Motor', 'EG-500AD-9F', 'N/A', 'N/A', '9 VCD 2400/RPM, (Motor universal de 9.0V)', 'Alto', 27, 0),
('1000000312', 'N/A', 'N/A', 'c', 669, 'Motor', 'SDS-ES-R-WSK', 'N/A', 'N/A', '12 VCD 3200/1600 RPM', 'Alto', 47, 0),
('1000000313', 'N/A', 'N/A', 'N/A', 669, 'Reductor', 'BO-1', 'N/A', 'N/A', 'Motoreductor Recto, Doble Eje, 1:48', 'Alto', 16, 0),
('1000000314', 'N/A', 'N/A', 'a', 669, 'Reductor', 'B0-1', 'N/A', 'N/A', 'Motor Reductor DC  Arduino, 1:260, ( 180º) ', 'Alto', 12, 0),
('1000000315', 'N/A', 'N/A', 'b', 669, 'Reductor', 'B01', 'N/A', 'N/A', 'Motor Reductor en \"L\", 1:260   90º ', 'Alto', 8, 0),
('1000000316', 'N/A', 'N/A', 'c', 669, 'De Pasos', '25BY1801', 'N/A', 'N/A', '5 VCD Unipolar 4-Phase 5VCD', 'Bajo', 5, 0),
('1000000317', 'N/A', 'N/A', 'N/A', 669, 'De Pasos', 'PF55-48D1', 'N/A', 'N/A', ' Pasos/ unipolar - 5v-pf55 150 mn-m - 48 Paso', 'Bajo', 5, 0),
('1000000318', 'N/A', 'N/A', 'a', 669, 'Normal', 'N/A', 'N/A', 'N/A', '5 VCD', 'Alto', 20, 0),
('1000000319', 'N/A', 'N/A', 'b', 669, 'Normal', 'N/A', 'N/A', 'N/A', 'Motor 365, 6 -12VCD, 5000RPM Electronica.', 'Alto', 16, 0),
('1000000320', 'N/A', 'M-3', 'c', 670, 'Grippres', 'WS005', 'N/A', 'N/A', 'Tenazas P/Brazo Robot', 'Sin utilizar', 1, 0),
('1000000321', 'N/A', 'M-4', 'a', 669, 'Servomotor', 'HS311', 'N/A', 'N/A', 'Vel. 0,19 seg  4,8 V', 'Alto', 27, 0),
('1000000322', 'N/A', 'N/A', 'N/A', 669, 'De Pasos', '17HS15-0406S', 'N/A', 'N/A', 'Motor a Pasos Unipolar 1.8º , 0.4A, 12V', 'Alto', 5, 0),
('1000000323', 'N/A', 'M-5', 'b', 669, 'De Pasos', 'P/N 55432', 'N/A', 'N/A', '12 VCD UNIPOLAR', 'Alto', 11, 0),
('1000000324', 'N/A', 'M-6', 'c', 671, 'switch miniatura de palanca', 'S-116', 'N/A', 'N/A', 'switch miniatura de palanca 3/6A 125VCA', 'Bajo', 40, 0),
('1000000325', 'N/A', 'M-6', 'a', 671, 'switch miniatura de palanca', 'S-120', 'N/A', 'N/A', 'switch miniatura de palanca 3/6A 3 pocic.', 'Bajo', 0, 0),
('1000000326', 'N/A', 'N-1', 'b', 671, 'Micro/Switch Boton', 'AU-1012', 'N/A', 'N/A', 'Push/boton 2 terminales 50mA  12VCC', 'Alto', 157, 0),
('1000000327', 'N/A', 'N-1', 'c', 671, 'Switch Push-Boton', 'AU102R', 'N/A', 'N/A', 'Rojo N/A Dos Terminales', 'Alto', 76, 0),
('1000000328', 'N/A', 'N-1', 'a', 671, 'Switch Push-Boton', 'AU1013N', 'N/A', 'N/A', 'Negro N/C Dos Terminales', 'Alto', 92, 0),
('1000000329', 'N/A', 'N-1', 'b', 671, 'Switch Push-Boton', 'AU105', 'N/A', 'N/A', 'Boton  2 Terminales ON/OFF', 'Bajo', 0, 0),
('1000000330', 'N/A', 'N-2', 'c', 671, 'Switch Circuito Integrado', 'DIP-4P', 'N/A', 'N/A', 'Cuatro Interruptores 1Polo-1Tiro  1A', 'Medio', 44, 0),
('1000000331', 'N/A', 'N-2', 'a', 671, 'Switch Circuito Integrado', 'DIP-6P', 'N/A', 'N/A', 'Seis Interruptores 1Polo-1Tiro  1A', 'Medio', 37, 0),
('1000000332', 'Metálico', 'N-2', 'b', 671, 'Switch Circuito Integrado', 'DIP-8P', 'N/A', 'N/A', 'Ocho Interruptores 1Polo-1Tiro  1A', 'Medio', 42, 0),
('1000000333', 'Metálico', 'N-2', 'c', 671, 'Switch Push Boton', 'SS0500A', 'N/A', 'N/A', 'Microswitch  5A  125VCC', 'Bajo', 27, 0),
('1000000334', 'Metálico', 'N-3', 'a', 671, 'Switch Push-Boton', 'SS2214P', 'N/A', 'N/A', 'Deslizable', 'Bajo', 41, 0),
('1000000335', 'Metálico', 'N-3', 'b', 671, 'Switch Balancin', 'BTS-16', 'N/A', 'N/A', '2Polos 2Tiros 2Posiciones  10/6A', 'Sin utilizar', 0, 0),
('1000000336', 'Metálico', 'N-3', 'c', 671, 'Switch Balancin', 'BTS-18', 'N/A', 'N/A', '1polo, 1tiro, 2posiciones 16ACC/12V', 'Sin utilizar', 5, 0),
('1000000337', 'Metálico', 'N-3', 'a', 671, 'Switch', '82602.0', 'N/A', 'N/A', '125/250V-15/10A 1/Polo, 2/Tiro, 3/Posiciones', 'Sin utilizar', 50, 0),
('1000000338', 'Metálico', 'N/A', 'b', 671, 'Switch', '82608.0', 'N/A', 'N/A', '125/250V-15/10A 2/Polo, 2/Tiro, 3/Posiciones', 'Sin utilizar', 42, 0),
('1000000339', 'Metálico', 'N/A', 'a', 671, 'Switch', 'S/N', 'N/A', 'N/A', 'Swich Roker 1 Polo con Luz Piloto Azul', 'Bajo', 39, 0),
('1000000340', 'Metálico', 'N/A', 'b', 671, 'Switch', 'S/N', 'N/A', 'N/A', 'Swich Roker 1 Polo con Luz Piloto Verde', 'Bajo', 40, 0),
('1000000341', 'Metálico', 'N/A', 'a', 671, 'Switch', '1365555.0', 'N/A', 'N/A', 'Apagador Interruptor de Palanca On/Off Cola Rata', 'Medio', 32, 0),
('1000000342', 'Metálico', 'N/A', 'b', 671, 'Switch', '1P-1T SW185 ', 'N/A', 'N/A', 'Switch Palanca On/Off  4A, 125V,  2P', 'Medio', 23, 0),
('1000000343', 'Metálico b', 'N-4', 'c', 672, 'Rosca', 'Nº 222', 'N/A', 'N/A', 'De  Rosca   2,2 VCD', 'Medio', 25, 0),
('1000000344', 'Metálico', 'N-4', 'a', 672, 'Rosca', 'Nº 46', 'N/A', 'N/A', 'De  Rosca   6,3 VCD', 'Alto', 39, 0),
('1000000345', 'Metálico', 'N-4', 'b', 672, 'Rosca', 'S/N', 'N/A', 'N/A', 'De  Rosca   12  VCD', 'Alto', 61, 0),
('1000000346', 'Metálico', 'N-4', 'c', 673, 'Rosca', 'ES 1091', 'N/A', 'N/A', 'Porta Focos de Rosca', 'Alto', 60, 0),
('1000000347', 'Metálico', 'N-5', 'a', 672, 'Bayoneta', 'Nº 47', 'N/A', 'N/A', 'De Bayoneta  6,3  VCD', 'Alto', 42, 0),
('1000000348', 'Metálico', 'N-5', 'b', 672, 'Bayoneta', 'S/N', 'N/A', 'N/A', 'De Bayoneta 12   VCD', 'Alto', 71, 0),
('1000000349', 'Metálico', 'N-5', 'c', 672, 'Foco ', 'BEL-(Am/R/V)', 'N/A', 'N/A', 'Encapsulado  (Amarillo, Rojo  ò Verde)', 'Sin utilizar', 14, 0),
('1000000350', 'Metálico', 'N-5', 'a', 673, 'Bayoneta', 'BS-921', 'N/A', 'N/A', 'Porta Focos de Bayoneta', 'Alto', 30, 0),
('1000000351', 'Metálico b', 'N-6', 'b', 672, 'Neon', 'NE-2', 'N/A', 'N/A', 'Neon Transparente  85 VCA / 110  VCD', 'Sin utilizar', 27, 0),
('1000000352', 'Metálico', 'N-6', 'c', 674, 'Bobina', '3R3', '3.3', 'μH', 'bobina inductor 3.3 µH', 'Medio', 20, 0),
('1000000353', 'Metálico', 'N-6', 'a', 674, 'Bobina', '100 2z', '10.0', 'μH', 'bobina inductor 10 µH', 'Medio', 17, 0),
('1000000354', 'Metálico', 'N-6', 'b', 674, 'Bobina', '101.0', '100.0', 'μH', 'Bobina inductor 100 µH', 'Medio', 30, 0),
('1000000355', 'Metálico', 'O-1', 'c', 675, 'Electrónico', 'SRD-05VCD-SL-C', 'N/A', 'N/A', 'Electronico1 Polo 1 Tiros .Bobina:5 VCD', 'Alto', 29, 0),
('1000000356', 'Metálico', 'O-1', 'a', 675, 'Electrónico', 'H2AL012T', 'N/A', 'N/A', 'Electronico1 Polo 1 Tiros .Bobina:12 VCD', 'Sin utilizar', 13, 0),
('1000000357', 'Metálico', 'O-1', 'b', 675, 'Electrónico', 'SRD-06VCD-SL-C', 'N/A', 'N/A', 'Electronico 1 Polo 2 Tiros .Bobina:6 VCD', 'Alto', 22, 0),
('1000000358', 'Metálico', 'O-2', 'c', 675, 'Electrónico', 'RAS-1210', 'N/A', 'N/A', 'Electronico 1 Polo 2 Tiros .Bobina:12 VCD', 'Alto', 54, 0),
('1000000359', 'Metálico', 'O-2', 'a', 675, 'Electrónico', 'HR33A6369', 'N/A', 'N/A', 'Electronico 1 Polo 2 Tiros .Bobina:12 VCD', 'Sin utilizar', 17, 0),
('1000000360', 'Metálico', 'O-2', 'b', 675, 'Electrónico', 'OMI-SS-224L', 'N/A', 'N/A', 'Electronico2 Polo 2 Tiros .Bobina:24 VCD', 'Sin utilizar', 28, 0),
('1000000361', 'Metálico', 'O-4', 'c', 675, 'Electromecánico', 'KRP11AN', 'N/A', 'N/A', '120 VCA ', 'Sin utilizar', 29, 0),
('1000000362', 'Metálico', 'O-5', 'a', 675, 'Temporizador ', 'JCK11V14', 'N/A', 'N/A', '24 VCD  On Delay/Conexión con Retardo', 'Medio', 9, 0),
('1000000363', 'Metálico', 'O-6', 'b', 675, 'Temporizador ', 'JCK22V14', 'N/A', 'N/A', '24 VCD  Off Delay/Desconexión con Retardo', 'Medio', 6, 0),
('1000000364', 'N/A', 'N/A', 'c', 675, 'Industrial', 'R152012235120WT', 'N/A', 'N/A', 'RELE INDUSTRIAL 8 PINES 120V 50/60 Hz', 'Medio', 5, 0),
('1000000365', 'N/A', 'N/A', 'a', 675, 'Serie 60', '60.12.9.024.0040', 'N/A', 'N/A', 'Relevador serie 60, potencia,  dpdt 120V 10A', 'Medio', 8, 0),
('1000000366', 'Metálico', 'P-1', 'b', 676, 'Con Punto Medio', 'TR06', 'N/A', 'N/A', '110 VCA- 6 VCD - 500 mA y  1,2 A', 'Alto', 64, 0),
('1000000367', 'Metálico', 'P-2', 'c', 676, 'Con Punto Medio', 'TR12', 'N/A', 'N/A', '110 VCA- 12 VCD - 1,2 A  y  3  A', 'Alto', 32, 0),
('1000000368', 'Metálico', 'P-3', 'a', 676, 'Con Punto Medio', 'TR24', 'N/A', 'N/A', '110 VCA- 24 VCD - 500 mA ,1,2  A  y  3  A', 'Bajo', 27, 0);
INSERT INTO `material` (`cb_material`, `tipo_de_armario`, `gaveta`, `sub_compartimento`, `id_material`, `tipo`, `numero_parte`, `valor`, `unidad_de_medida`, `caracteristicas`, `frecuencia_de_uso`, `cantidad`, `cantidad_min`) VALUES
('1000000369', 'Metálico', 'P-4', 'b', 676, 'Con Punto Medio', 'TR32', 'N/A', 'N/A', '110 VCA 32 VCD 2A', 'Sin utilizar', 14, 0),
('1000000370', 'Metálico', 'P-5', 'c', 674, 'Bobina', '101K', '100.0', 'μH', 'Bobina inductor 100 µH', 'Medio', 39, 0),
('1000000371', 'Metálico', 'P-6', 'a', 674, 'Bobina', '331.0', '330.0', 'μH', 'Bobina inductor 330 µH', 'Medio', 11, 0),
('1000000372', 'Metalico', 'N/A', 'b', 674, 'Bobina', 'S/N', '220.0', 'μH', 'Bobina Inductor 220 µH', 'Medio', 25, 0),
('1000000373', 'Metalico', 'N/A', 'c', 674, 'Bobina', 'S/N', '470.0', 'μH', 'Bobina Inductor 470 µH', 'Medio', 25, 0),
('1000000374', 'D', 'D1', 'A', 677, 'Rectificador', '1N4001', 'N/A', 'N/A', 'Rect. de Prop. General 50/1000V 1,0 A', 'Alto', 50, 0),
('1000000375', 'N/A', 'N/A', 'N/A', 677, 'Rectificador', '1N4002', 'N/A', 'N/A', 'Rectificador de Sil. 600V', 'Alto', 42, 0),
('1000000376', 'D', 'D1', 'B', 677, 'Rectificador', '1N4004', 'N/A', 'N/A', 'Rect. de Prop. General 50/1000V 1,0 A', 'Alto', 65, 0),
('1000000377', 'D', 'D2', 'A', 677, 'Rectificador', '1N4005', 'N/A', 'N/A', 'Rect. de Prop. General 50/1000V 1,0 A', 'Alto', 67, 0),
('1000000378', 'D', 'D2', 'B', 677, 'Rectificador', '1N4007', 'N/A', 'N/A', 'Rect. de Prop. General 50/1000V 1,0 A', 'Alto', 104, 0),
('1000000379', 'D', 'D3', 'A', 677, 'Rectificador', 'MR502', 'N/A', 'N/A', 'Rectificador de Proposito General', 'Medio', 7, 0),
('1000000380', 'D', 'D3', 'B', 677, 'Rectificador', 'GI8311', 'N/A', 'N/A', 'Rectificador de Proposito General', 'Bajo', 98, 0),
('1000000381', 'D', 'D4', 'A', 677, 'Rectificador', '1S1944', 'N/A', 'N/A', 'Rectificador de Proposito General', 'Sin utilizar', 10, 0),
('1000000382', 'N/A', 'N/A', 'N/A', 677, 'Rectificador', 'G18532', 'N/A', 'N/A', 'Rectificador de Proposito General', 'Sin utilizar', 75, 0),
('1000000383', 'N/A', 'N/A', 'N/A', 677, 'Rectificador', '1N4148', 'N/A', 'N/A', 'Diodo switch A/Velocidad', 'Medio', 73, 0),
('1000000384', 'D', 'D4', 'B', 677, 'Rectificador Schottky', 'MBR1045', 'N/A', 'N/A', 'Rectificador Schottky', 'Alto', 35, 0),
('1000000385', 'D', 'D5', 'A', 677, 'Rectificador', 'G1510', 'N/A', 'N/A', 'Rectificador Schottky Barrier', 'Bajo', 18, 0),
('1000000386', 'D', 'D5', 'B', 677, 'Tiristor', 'MBS4991', 'N/A', 'N/A', 'Switch Bidireccional de Silicio', 'Sin utilizar', 0, 0),
('1000000387', 'D', 'D6', 'A', 677, 'Zenner', 'IN4733A ', 'N/A', 'N/A', '5.1V 1W', 'Medio', 45, 0),
('1000000388', 'D', 'D6', 'B', 677, 'Zenner', 'BZX55C9V1', 'N/A', 'N/A', '9.1V 1/2W', 'Medio', 19, 0),
('1000000389', 'D', 'D7', 'A', 677, 'Zenner', '1N4739A', 'N/A', 'N/A', '9.1V 1W', 'Medio', 45, 0),
('1000000390', 'D', 'D7', 'B', 677, 'Zenner', 'IN4740A', 'N/A', 'N/A', '10V 1W', 'Medio', 50, 0),
('1000000391', 'D', 'D8', 'A', 677, 'Zenner', 'BZX55C12', 'N/A', 'N/A', '12V 1/2W', 'Medio', 21, 0),
('1000000392', 'D', 'D8', 'B', 677, 'Zenner', 'IN4740A', 'N/A', 'N/A', '12V 1W', 'Medio', 75, 0),
('1000000393', 'N/A', 'N/A', 'N/A', 677, 'Zenner', '12C', 'N/A', 'N/A', '13V 1/2W', 'Medio', 31, 0),
('1000000394', 'D', 'D9', 'A', 677, 'Zenner', '1N967B', 'N/A', 'N/A', '18V 1/2W', 'Medio', 5, 0),
('1000000395', 'D', 'D9', 'B', 677, 'Zenner', '1N968B', 'N/A', 'N/A', '20V 1/2W', 'Medio', 49, 0),
('1000000396', 'D', 'D10', 'A', 677, 'Zenner', 'BZX55C24', 'N/A', 'N/A', '24V 1/2W', 'Medio', 96, 0),
('1000000397', 'D', 'D11', 'A', 678, 'Fotorresistiva', '9P5-1L', '2.0', 'MΩ', '2 MOhms  100VAC', 'Alto', 50, 0),
('1000000398', 'D', 'D11', 'B', 678, 'Fotorresistiva', '9P5-A', '10.0', 'MΩ', '10 MOhoms  250 VAC', 'Alto', 12, 0),
('1000000399', 'N/A', 'N/A', 'N/A', 678, 'Fotorresistiva', '20MΩ', '20.0', 'MΩ', 'Fotorrecistencia 20MΩ', 'Alto', 33, 0),
('1000000400', 'D', 'D12', 'A', 662, 'DIAC', 'HT-32', 'N/A', 'N/A', 'Diodo de Disparo Bilateral', 'Medio', 67, 0),
('1000000401', 'D', 'D12', 'B', 662, 'DIAC', 'ECG6408 (DB3)', 'N/A', 'N/A', 'D Bilateral 2A 150mV  32V', 'Medio', 80, 0),
('1000000402', 'D', 'D13', 'A', 679, 'BJT', 'MPS2222', 'N/A', 'N/A', 'NPN Transistor proposito general', 'Alto', 125, 0),
('1000000403', 'N/A', 'N/A', 'N/A', 679, 'N/A', '2N3904', 'S/N', 'S/N', 'NPN Transistor Amplificador proposito general', 'Sin utilizar', 44, 0),
('1000000404', 'D', 'D14', 'B', 679, 'NPN Alta Frecuencia', '7814(NTE108', 'N/A', 'N/A', ' NPN de Silicio Ampl. de Alta Frecuencia', 'Sin utilizar', 50, 0),
('1000000405', 'D', 'D15', 'A', 679, 'NPN Darlington', 'MPSA13', 'N/A', 'N/A', 'Transistor Darlington NPN', 'Sin utilizar', 25, 0),
('1000000406', 'D', 'D15', 'B', 679, 'NPN Difuso', '23D745', 'N/A', 'N/A', ' NPN  Transistor Difuso Triple', 'Sin utilizar', 0, 0),
('1000000407', 'D', 'D16', 'A', 680, 'NPN', 'AC127/187', 'N/A', 'N/A', 'Germanio NPN Amp. Media Potencia', 'Sin utilizar', 44, 0),
('1000000408', 'D', 'D16', 'B', 680, 'NPN', '2SB75', 'N/A', 'N/A', 'Germanio NPN Amp. Media Potencia', 'Sin utilizar', 47, 0),
('1000000409', 'D', 'D17', 'A', 679, 'PNP UJT', '2N1671', 'N/A', 'N/A', 'PNP Silicio UJT', 'Sin utilizar', 0, 0),
('1000000410', 'D', 'D17', 'B', 679, 'PNP UJT', '2N2646-2647', 'N/A', 'N/A', 'PNP Silicio UJT', 'Sin utilizar', 163, 0),
('1000000411', 'D', 'D18', 'A', 679, 'PNP UJT', '2N4870-4871', 'N/A', 'N/A', 'PNP Silicio UJT', 'Sin utilizar', 52, 0),
('1000000412', 'D', 'D18', 'B', 679, 'UJT', '2N6027-6028', 'N/A', 'N/A', 'Transistor UJT Programable', 'Sin utilizar', 64, 0),
('1000000413', 'D', 'D19', 'A', 679, 'PNP', '2N4991', 'N/A', 'N/A', '(SBS) Switch Bilateral de Silicio', 'Sin utilizar', 26, 0),
('1000000414', 'D', 'D19', 'B', 679, 'PNP', '2N3906', 'N/A', 'N/A', 'PNP Proposito General', 'Medio', 252, 0),
('1000000415', 'D', 'D20', 'A', 679, 'PNP', 'AC187', 'N/A', 'N/A', 'Germanio PNP.Amp. Media Potencia', 'Sin utilizar', 15, 0),
('1000000416', 'D', 'D20', 'B', 679, 'PNP', '2N2905', 'N/A', 'N/A', 'PNP, SI,AF,Preamp.Dr', 'Sin utilizar', 40, 0),
('1000000417', 'D', 'D21', 'A', 679, 'PNP', 'SFT243', 'N/A', 'N/A', 'PNP,Ge,AF,PO', 'Sin utilizar', 15, 0),
('1000000418', 'D', 'D22', 'A', 679, 'PNP', '2N2907', 'N/A', 'N/A', 'PNP Transistor Epitaxial Planar', 'Medio', 60, 0),
('1000000419', 'D', 'D23', 'A', 679, 'FET / JFET', 'MPF102', 'N/A', 'N/A', 'JFET Canal N  UHF/VHF Amp.', 'Bajo', 84, 0),
('1000000420', 'D', 'D23', 'B', 679, 'FET / JFET', '2N5457', 'N/A', 'N/A', 'Amplif Propos Genereal JFET Canal-N', 'Sin utilizar', 50, 0),
('1000000421', 'D', 'D24', 'A', 666, 'FET / JFET', 'RFZ-42', 'N/A', 'N/A', 'Transistor FET de Potencia  TMOS', 'Sin utilizar', 0, 0),
('1000000422', 'D', 'D24', 'B', 666, 'FET / JFET', 'IRFZ44N', 'N/A', 'N/A', 'Hexfeet de Potencia Mosfet', 'Alto', 35, 0),
('1000000423', 'D', 'N/A', 'N/A', 666, 'IGBT', 'IRGB14C40L', 'N/A', 'N/A', 'Transistor bipolar de Puerta Aislada', 'Sin utilizar', 54, 0),
('1000000424', 'D', 'N/A', 'N/A', 666, 'NPN', 'FJP13007', 'N/A', 'N/A', 'Transistor NPN', 'Sin utilizar', 0, 0),
('1000000425', 'D', 'N/A', 'N/A', 666, 'MOSFET', 'IRF9540', 'N/A', 'N/A', 'Mosfet de Potencia', 'Sin utilizar', 14, 0),
('1000000426', 'N/A', 'N/A', 'N/A', 666, 'N/A', 'BC324', 'N/A', 'N/A', 'Transistor Ampificador PNP.', 'Sin utilizar', 69, 0),
('1000000427', 'N/A', 'N/A', 'N/A', 679, 'NPN', 'BC337', 'N/A', 'N/A', 'Transistor Proposito General', 'Sin utilizar', 255, 0),
('1000000428', 'N/A', 'N/A', 'N/A', 679, 'NPN BJT Amplificador', 'C1815', 'N/A', 'N/A', 'Transistor Audio- Amplificador', 'Sin utilizar', 98, 0),
('1000000429', 'N/A', 'N/A', 'N/A', 680, 'NPN', 'BD135', 'N/A', 'N/A', 'Transistor Silicio Media Potencia', 'Sin utilizar', 47, 0),
('1000000430', 'N/A', 'N/A', 'N/A', 666, 'NPN', 'C2690', 'N/A', 'N/A', 'Transistor Silicio Potencia', 'Sin utilizar', 91, 0),
('1000000431', 'N/A', 'N/A', 'N/A', 679, 'SCS', '3N84', 'N/A', 'N/A', 'TERT suwitch control. D/silicio', 'Sin utilizar', 26, 0),
('1000000432', 'N/A', 'N/A', 'N/A', 679, 'PNP', '9012F', 'N/A', 'N/A', 'Transistor Audio- Amplificador', 'Bajo', 10, 0),
('1000000433', 'N/A', 'N/A', 'N/A', 679, 'PNP', '2N3702', 'N/A', 'N/A', 'Transistor Audio- Amplificador. Potencia', 'Sin utilizar', 50, 0),
('1000000434', 'N/A', 'N/A', 'N/A', 679, 'PNP', '2N2363', 'N/A', 'N/A', 'transistor (Ge) Switch de A/ Velocidad', 'Sin utilizar', 0, 0),
('1000000435', 'N/A', 'N/A', 'N/A', 679, 'Programable', 'N13T1', 'N/A', 'N/A', 'Transistor Union Programable', 'Sin utilizar', 23, 0),
('1000000436', 'N/A', 'N/A', 'N/A', 679, 'JFET', '2N4339', 'N/A', 'N/A', 'JFET CH-N AMP. BAJO RUIDO', 'Sin utilizar', 19, 0),
('1000000437', 'N/A', 'N/A', 'N/A', 666, 'NPN', 'SS4164', 'N/A', 'N/A', 'Transistor NPN baja saturación', 'Sin utilizar', 41, 0),
('1000000438', 'N/A', 'N/A', 'N/A', 666, 'MOSFET ', '2N5485', 'N/A', 'N/A', 'Mosfet CH - N Doble Puerta', 'Sin utilizar', 30, 0),
('1000000439', 'N/A', 'N/A', 'N/A', 666, 'MOSFET ', '3N203', 'N/A', 'N/A', 'MOSFET Doble Puerta AMP. VHF', 'Sin utilizar', 27, 0),
('1000000440', 'N/A', 'N/A', 'N/A', 666, 'MOSFET ', 'MTP2955', 'N/A', 'N/A', 'MOSFET DE Potencia Canal \"p\"', 'Sin utilizar', 39, 0),
('1000000441', 'N/A', 'N/A', 'N/A', 666, 'MOSFET ', 'IRF4905', 'N/A', 'N/A', 'POT. MOSFET CH \"P\"-74A - 55V', 'Sin utilizar', 20, 0),
('1000000442', 'N/A', 'N/A', 'N/A', 666, 'MOSFET ', 'IRFP350', 'N/A', 'N/A', 'POT. MOSFET CH \"P\"-16A - 400V', 'Bajo', 5, 0),
('1000000443', 'N/A', 'N/A', 'N/A', 666, 'MOSFET ', 'IRF640', 'N/A', 'N/A', 'POT. MOSFET CH \"P\"-18A - 200V', 'Sin utilizar', 27, 0),
('1000000444', 'N/A', 'N/A', 'N/A', 666, 'IGBT', 'GB10NB37LZ', 'N/A', 'N/A', 'CH \"N\" MALLA DE POT. INT. 20A', 'Bajo', 23, 0),
('1000000445', 'N/A', 'N/A', 'N/A', 666, 'IGBT', 'FGPF4536', 'N/A', 'N/A', 'Transistor bipolar Pueta aislada 360V', 'Sin utilizar', 23, 0),
('1000000446', 'D', 'N/A', 'N/A', 666, 'MOSFET', 'MTP 50N06V', 'N/A', 'N/A', 'Mosfet de Potencia', 'Sin utilizar', 18, 0),
('1000000447', 'D', 'N/A', 'N/A', 674, 'Bobina', '3R3', 'N/A', 'N/A', 'Inductor Bobina  3.3µH', 'Alto', 17, 0),
('1000000448', 'D', 'N/A', 'N/A', 674, 'Bobina', '100 2Z', 'N/A', 'N/A', 'Inductor Bobina  10µH', 'Alto', 20, 0),
('1000000449', 'D', 'N/A', 'N/A', 674, 'Bobina', '101K', 'N/A', 'N/A', 'Inductor Bobina  100µH', 'Alto', 38, 0),
('1000000450', 'D', 'D29', 'B', 654, 'Oscilador', 'VCO55', 'N/A', 'N/A', 'Oscilador de Voltaje Controlado', 'Sin utilizar', 15, 0),
('1000000451', 'N/A', 'D30', 'B', 681, 'Fototransistor', 'PT13302B', 'N/A', 'N/A', 'Fototrans.Silicon  C/Filtro de luz de Dia', 'Alto', 74, 0),
('1000000452', 'N/A', 'D31', 'A', 681, 'Fototransistor', 'PT202C', 'N/A', 'N/A', 'Fototrans.Silicon  C/Filtro de luz de Dia', 'Medio', 22, 0),
('1000000453', 'N/A', 'N/A', 'N/A', 681, 'Fototransistor', 'OP 844', 'N/A', 'N/A', 'Fototransistor de silicio NPN', 'Bajo', 50, 0),
('1000000454', 'N/A', 'N/A', 'N/A', 682, 'Fotodiodo', 'VTB8440B', 'N/A', 'N/A', 'Fotodiodo proceso VTB', 'Sin utilizar', 50, 0),
('1000000455', 'N/A', 'N/A', 'N/A', 682, 'Fotodiodo', 'PW14', 'N/A', 'N/A', 'Fotodiodo ', 'Sin utilizar', 49, 0),
('1000000456', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '1.6Ω', '1.6', 'Ω', 'Resistencia Cerámica de 1.6 Ω 1/4 W', 'Alto', 154, 0),
('1000000457', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '1.2Ω', '1.2', 'Ω', 'Resistencia Cerámica de 1.2 Ω 1/4W', 'Alto', 32, 0),
('1000000458', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '1.8Ω', '1.8', 'Ω', 'Resistencia Cerámica de 1.8 Ω 1/4W', 'Alto', 91, 0),
('1000000459', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '2.2Ω', '2.2', 'Ω', 'Resistencia Cerámica de 2.2 Ω 1/4W', 'Alto', 87, 0),
('1000000460', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '2.7Ω', '2.7', 'Ω', 'Resistencia Cerámica de 2.7Ω 1/4W', 'Alto', 69, 0),
('1000000461', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '3.3Ω', '3.3', 'Ω', 'Resistencia Cerámica de 3.3Ω 1/4W', 'Alto', 42, 0),
('1000000462', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '4.7Ω', '4.7', 'Ω', 'Resistencia Cerámica de 4.7Ω 1/4W', 'Alto', 90, 0),
('1000000463', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '5.7Ω', '5.7', 'Ω', 'Resistencia Cerámica de 5.7Ω 1/4W', 'Alto', 49, 0),
('1000000464', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '6.8Ω', '6.8', 'Ω', 'Resistencia Cerámica de 6.8Ω 1/4W', 'Alto', 76, 0),
('1000000465', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '10Ω', '10.0', 'Ω', 'Resistencia Cerámica de 10Ω 1/4W', 'Alto', 136, 0),
('1000000466', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '12Ω', '12.0', 'Ω', 'Resistencia Cerámica de 12Ω 1/4W', 'Alto', 75, 0),
('1000000467', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '15Ω', '15.0', 'Ω', 'Resistencia Cerámica de 15Ω 1/4W', 'Alto', 79, 0),
('1000000468', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '18Ω', '18.0', 'Ω', 'Resistencia Cerámica de 18Ω 1/4W', 'Alto', 77, 0),
('1000000469', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '22Ω', '22.0', 'Ω', 'Resistencia Cerámica de 22Ω 1/4W', 'Alto', 117, 0),
('1000000470', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '27Ω', '27.0', 'Ω', 'Resistencia Cerámica de 27Ω 1/4W', 'Alto', 65, 0),
('1000000471', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '100Ω', '100.0', 'Ω', 'Resistencia Cerámica de 100Ω 1/2 W', 'Alto', 233, 0),
('1000000472', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '220Ω', '220.0', 'Ω', 'Resistencia Cerámica de 220Ω 1/2W', 'Alto', 50, 0),
('1000000473', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '330Ω', '330.0', 'Ω', 'Resistencia Cerámica de 330Ω 1/2W', 'Alto', 20, 0),
('1000000474', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '390Ω', '390.0', 'Ω', 'Resistencia Cerámica de 390Ω 1/2W', 'Alto', 80, 0),
('1000000475', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '270Ω', '270.0', 'Ω', 'Resistencia Cerámica de 270Ω 1/2W', 'Alto', 250, 0),
('1000000476', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '300Ω', '300.0', 'Ω', 'Resistencia Cerámica de 300Ω 1/2 W', 'Alto', 62, 0),
('1000000477', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '33Ω', '33.0', 'Ω', 'Resistencia Cerámica de 33Ω 1/4 W', 'Alto', 125, 0),
('1000000478', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '39Ω', '39.0', 'Ω', 'Resistencia Cerámica de 39Ω 1/4 W', 'Alto', 44, 0),
('1000000479', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '47Ω', '47.0', 'Ω', 'Resistencia Cerámica de 47Ω 1/4 W', 'Alto', 36, 0),
('1000000480', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '56Ω', '56.0', 'Ω', 'Resistencia Cerámica de 56Ω 1/4W', 'Alto', 4, 0),
('1000000481', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '68Ω', '68.0', 'Ω', 'Resistencia Cerámica de 68Ω 1/4 W', 'Alto', 62, 0),
('1000000482', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '82Ω', '82.0', 'Ω', 'Resistencia Cerámica de 82Ω 1/4 W', 'Alto', 54, 0),
('1000000483', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '100Ω', '100.0', 'Ω', 'Resistencia Cerámica de 100Ω 1/4 W', 'Alto', 74, 0),
('1000000484', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '120Ω', '120.0', 'Ω', 'Resistencia Cerámica de 120Ω 1/4 W', 'Alto', 91, 0),
('1000000485', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '150Ω', '150.0', 'Ω', 'Resistencia Cerámica de 150Ω 1/4 W', 'Alto', 89, 0),
('1000000486', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '200Ω', '200.0', 'Ω', 'Resistencia Cerámica de 200Ω 1/4 W', 'Alto', 17, 0),
('1000000487', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '220Ω', '220.0', 'Ω', 'Resistencia Cerámica de 220Ω 1/4W', 'Alto', 118, 0),
('1000000488', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '270Ω', '270.0', 'Ω', 'Resistencia Cerámica de 270Ω 1/4 W', 'Alto', 132, 0),
('1000000489', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '330Ω', '330.0', 'Ω', 'Resistencia Cerámica de 330Ω 1/4 W', 'Alto', 89, 0),
('1000000490', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '360Ω', '360.0', 'Ω', 'Resistencia Cerámica de 360Ω 1/4 W', 'Alto', 37, 0),
('1000000491', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '390Ω', '390.0', 'Ω', 'Resistencia Cerámica de 300Ω 1/4W', 'Alto', 44, 0),
('1000000492', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '80Ω', '820.0', 'Ω', 'Resistencia Cerámica de 8200Ω 1/2 W', 'Alto', 147, 0),
('1000000493', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '1KΩ', '1.0', 'kΩ', 'Resistencia Cerámica de 1KΩ 1/2 W', 'Alto', 100, 0),
('1000000494', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '470Ω', '470.0', 'Ω', 'Resistencia Cerámica de 4700Ω 1/4 W', 'Alto', 100, 0),
('1000000495', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '560Ω', '560.0', 'Ω', 'Resistencia Cerámica de 560Ω 1/4 W', 'Alto', 39, 0),
('1000000496', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '680Ω', '680.0', 'Ω', 'Resistencia Cerámica de 680Ω 1/4 W', 'Alto', 99, 0),
('1000000497', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '820Ω', '820.0', 'Ω', 'Resistencia Cerámica de 820Ω 1/4 W', 'Alto', 15, 0),
('1000000498', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '1KΩ', '1.0', 'kΩ', 'Resistencia Cerámica de 1KΩ 1/4 W', 'Alto', 167, 0),
('1000000499', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '1.2KΩ', '1.2', 'kΩ', 'Resistencia Cerámica de 1.2KΩ 1/4 W', 'Alto', 28, 0),
('1000000500', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '1.5KΩ', '1.5', 'kΩ', 'Resistencia Cerámica de 1.5KΩ 1/4 W', 'Alto', 1, 0),
('1000000501', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '2.2KΩ', '2.2', 'kΩ', 'Resistencia Cerámica de 2.2KΩ 1/4 W', 'Alto', 119, 0),
('1000000502', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '1.8KΩ', '1.8', 'kΩ', 'Resistencia Cerámica de 1.8KΩ 1/4 W', 'Alto', 164, 0),
('1000000503', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '2.7KΩ', '2.7', 'kΩ', 'Resistencia Cerámica de 2.7KΩ 1/4 W', 'Alto', 26, 0),
('1000000504', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '3.3KΩ', '3.3', 'kΩ', 'Resistencia Cerámica de 3.3KΩ 1/4 W', 'Alto', 48, 0),
('1000000505', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '3.9KΩ', '3.9', 'kΩ', 'Resistencia Cerámica de 3.9KΩ 1/4 W', 'Alto', 51, 0),
('1000000506', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '4.7KΩ', '4.7', 'kΩ', 'Resistencia Cerámica de 4.7KΩ 1/4 W', 'Alto', 87, 0),
('1000000507', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '5.6KΩ', '5.6', 'kΩ', 'Resistencia Cerámica de 5.6KΩ 1/4 W', 'Alto', 139, 0),
('1000000508', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '6.8KΩ', '6.8', 'kΩ', 'Resistencia Cerámica de 6.8KΩ 1/4 W', 'Alto', 22, 0),
('1000000509', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '8.2KΩ', '8.2', 'kΩ', 'Resistencia Cerámica de 8.2KΩ 1/4 W', 'Alto', 40, 0),
('1000000510', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '1.2KΩ', '1.2', 'kΩ', 'Resistencia Cerámica de 1.2KΩ 1/4 W', 'Alto', 15, 0),
('1000000511', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '2KΩ', '2.0', 'kΩ', 'Resistencia Cerámica de 2KΩ 1/4 W', 'Alto', 108, 0),
('1000000512', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '10MΩ', '10.0', 'MΩ', 'Resistencia Cerámica de 10MΩ 1/2 W', 'Alto', 152, 0),
('1000000513', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '1.5Ω', '1.5', 'Ω', 'Resistencia Cerámica de 1.5Ω 1/2 W', 'Alto', 30, 0),
('1000000514', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '10KΩ', '10.0', 'kΩ', 'Resistencia Cerámica de 10KΩ 1/4 W', 'Alto', 15, 0),
('1000000515', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '12KΩ', '12.0', 'kΩ', 'Resistencia Cerámica de 12KΩ 1/4 W', 'Alto', 128, 0),
('1000000516', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '15KΩ', '15.0', 'kΩ', 'Resistencia Cerámica de 15KΩ 1/4 W', 'Alto', 92, 0),
('1000000517', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '18KΩ', '18.0', 'kΩ', 'Resistencia Cerámica de 18KΩ 1/4 W', 'Alto', 53, 0),
('1000000518', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '22KΩ', '22.0', 'kΩ', 'Resistencia Cerámica de 22KΩ 1/4 W', 'Alto', 73, 0),
('1000000519', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '27KΩ', '27.0', 'kΩ', 'Resistencia Cerámica de 27KΩ 1/4 W', 'Alto', 39, 0),
('1000000520', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '33KΩ', '33.0', 'kΩ', 'Resistencia Cerámica de 33KΩ 1/4 W', 'Alto', 54, 0),
('1000000521', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '39KΩ', '39.0', 'kΩ', 'Resistencia Cerámica de 39KΩ 1/4 W', 'Alto', 69, 0),
('1000000522', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '47KΩ', '47.0', 'kΩ', 'Resistencia Cerámica de 47KΩ 1/4 W', 'Alto', 128, 0),
('1000000523', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '56KΩ', '56.0', 'kΩ', 'Resistencia Cerámica de 56KΩ 1/4 W', 'Alto', 37, 0),
('1000000524', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '68KΩ', '68.0', 'kΩ', 'Resistencia Cerámica de 68KΩ 1/4 W', 'Alto', 84, 0),
('1000000525', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '82KΩ', '82.0', 'kΩ', 'Resistencia Cerámica de 82KΩ 1/4 W', 'Alto', 49, 0),
('1000000526', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '10KΩ', '100.0', 'kΩ', 'Resistencia Cerámica de 100KΩ 1/4 W', 'Alto', 221, 0),
('1000000527', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '120KΩ', '120.0', 'kΩ', 'Resistencia Cerámica de 120KΩ 1/4 W', 'Alto', 30, 0),
('1000000528', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '3KΩ', '3.0', 'kΩ', 'Resistencia Cerámica de 3KΩ 1/2 W', 'Alto', 25, 0),
('1000000529', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '3.3KΩ', '3.3', 'kΩ', 'Resistencia Cerámica de 3.3KΩ 1/4 W', 'Alto', 88, 0),
('1000000530', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '10KΩ', '10.0', 'kΩ', 'Resistencia Cerámica de 10KΩ 1/2 W', 'Alto', 35, 0),
('1000000531', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '15KΩ', '15.0', 'kΩ', 'Resistencia Cerámica de 15KΩ 1/2 W', 'Alto', 90, 0),
('1000000532', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '150KΩ', '150.0', 'kΩ', 'Resistencia Cerámica de 150KΩ 1/4 W', 'Alto', 62, 0),
('1000000533', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '180KΩ', '180.0', 'kΩ', 'Resistencia Cerámica de 180KΩ 1/4 W', 'Alto', 80, 0),
('1000000534', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '220KΩ', '220.0', 'kΩ', 'Resistencia Cerámica de 220KΩ 1/4 W', 'Alto', 41, 0),
('1000000535', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '270KΩ', '270.0', 'kΩ', 'Resistencia Cerámica de 270KΩ 1/4 W', 'Alto', 42, 0),
('1000000536', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '330KΩ', '330.0', 'kΩ', 'Resistencia Cerámica de 330KΩ 1/4 W', 'Alto', 95, 0),
('1000000537', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '390KΩ', '390.0', 'kΩ', 'Resistencia Cerámica de 390KΩ 1/4 W', 'Alto', 195, 0),
('1000000538', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '470KΩ', '470.0', 'kΩ', 'Resistencia Cerámica de 470KΩ 1/4 W', 'Alto', 133, 0),
('1000000539', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '560KΩ', '560.0', 'kΩ', 'Resistencia Cerámica de 560KΩ 1/4 W', 'Alto', 60, 0),
('1000000540', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '1.0MΩ', '1.0', 'kΩ', 'Resistencia Cerámica de 1.0MΩ 1/4 W', 'Alto', 127, 0),
('1000000541', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '4.7MΩ', '4.7', 'MΩ', 'Resistencia Cerámica de 4.7MΩ 1/4 W', 'Alto', 25, 0),
('1000000542', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '820KΩ', '820.0', 'kΩ', 'Resistencia Cerámica de 820KΩ 1/4 W', 'Alto', 126, 0),
('1000000543', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '5.1MΩ', '5.1', 'MΩ', 'Resistencia Cerámica de 5.1MΩ 1/4 W', 'Alto', 25, 0),
('1000000544', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '10MΩ', '10.0', 'MΩ', 'Resistencia Cerámica de 10MΩ 1/4 W', 'Alto', 156, 0),
('1000000545', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '2.2MΩ', '2.2', 'MΩ', 'Resistencia Cerámica de 2.2MΩ 1/2 W', 'Alto', 31, 0),
('1000000546', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '3.0MΩ', '3.0', 'MΩ', 'Resistencia Cerámica de 3.0KΩ 1/2 W', 'Alto', 13, 0),
('1000000547', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '53KΩ', '53.0', 'kΩ', 'Resistencia Cerámica de 53KΩ 1/2 W', 'Alto', 22, 0),
('1000000548', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '680KΩ', '680.0', 'kΩ', 'Resistencia Cerámica de 680KΩ 1/4 W', 'Alto', 32, 0),
('1000000549', 'N/A', 'N/A', 'N/A', 654, 'Amplificador Operacional', 'TL 071', 'N/A', 'N/A', 'Entrada JFET Bajo Ruido', 'Sin utilizar', 25, 0),
('1000000550', 'N/A', 'N/A', 'N/A', 654, 'Amplificador Operacional', 'MC1458P', 'N/A', 'N/A', 'Dual Op-Amp de Proposito General ', 'Sin utilizar', 25, 0),
('1000000551', 'N/A', 'N/A', 'N/A', 654, 'Compuerta Lógica', 'CD4019', 'N/A', 'N/A', 'Compuerta Selecta Cuadruple AND-OR', 'Sin utilizar', 15, 0),
('1000000552', 'N/A', 'N/A', 'N/A', 654, 'Flip Flop', '74LS78', 'N/A', 'N/A', 'Doble F-F  J-K con Preset, limpiador común, reloj común', 'Sin utilizar', 10, 0),
('1000000553', 'N/A', 'N/A', 'N/A', 654, 'Amplificador de Audio', 'TDA2030', '14.0', 'W', 'Audio Amplificador Hi-Fi', 'Sin utilizar', 28, 0),
('1000000554', 'N/A', 'N/A', 'N/A', 653, 'Infrarrojo', 'TSOP1738 CB1', '38.0', 'khz', 'Fotomòdulo Para sist. De Control Remoto PCM', 'Sin utilizar', 23, 0),
('1000000555', 'N/A', 'N/A', 'N/A', 653, 'Infrarrojo', 'TSOP1738 TB1', '38.0', 'khz', 'Fotomòdulo Para sist. De Control Remoto PCM', 'Sin utilizar', 24, 0),
('1000000556', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '8.6KΩ', '8.6', 'kΩ', 'Resistencia Cerámica de 8.6KΩ 1/4 W', 'Sin utilizar', 89, 0),
('1000000557', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '3.9KΩ', '3.9', 'kΩ', 'Resistencia Cerámica de 3.3KΩ 1/2 W', 'Sin utilizar', 50, 0),
('1000000558', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '200KΩ', '200.0', 'kΩ', 'Resistencia Cerámica de 200KΩ 1/4 W', 'Sin utilizar', 61, 0),
('1000000559', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '120KΩ', '120.0', 'kΩ', 'Resistencia Cerámica de 120KΩ 1/2 W', 'Sin utilizar', 85, 0),
('1000000560', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '330Ω', '330.0', 'Ω', 'Resistencia Cerámica de 330Ω 1 W', 'Sin utilizar', 30, 0),
('1000000561', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '470Ω', '470.0', 'Ω', 'Resistencia Cerámica de 470Ω 1/2 W', 'Sin utilizar', 31, 0),
('1000000562', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '680Ω', '680.0', 'Ω', 'Resistencia Cerámica de 680Ω 1/2 W', 'Sin utilizar', 49, 0),
('1000000563', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '10Ω', '10.0', 'Ω', 'Resistencia Cerámica de 10Ω 1/2W', 'Sin utilizar', 149, 0),
('1000000564', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '470Ω', '470.0', 'Ω', 'Resistencia Cerámica de 470Ω 1/2W', 'Sin utilizar', 12, 0),
('1000000565', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '1.5MΩ', '1.5', 'MΩ', 'Resistencia Cerámica de 1.5Ω 1/4W', 'Sin utilizar', 0, 0),
('1000000566', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '560Ω', '560.0', 'Ω', 'Resistencia Cerámica de 560Ω 1/2 W', 'Sin utilizar', 50, 0),
('1000000567', 'N/A', 'N/A', 'N/A', 679, 'NPN', 'BC548A', 'N/A', 'N/A', 'Silico, Epitaxial Planar', 'Sin utilizar', 100, 0),
('1000000568', 'N/A', 'N/A', 'N/A', 653, 'Temperatura', 'DS1820', 'N/A', 'N/A', 'Termòmetro Digital de un Solo Cable', 'Sin utilizar', 8, 0),
('1000000569', 'N/A', 'N/A', 'N/A', 659, 'LCD', 'HDM16216L-B', 'N/A', 'N/A', '16 Caracteres X 2 Lineas LED  Backligth', 'Alto', 5, 0),
('1000000570', 'N/A', 'N/A', 'N/A', 659, ' 16 Segmentos / 1 Digito', 'S/N', 'N/A', 'N/A', ' 16 Segmentos / 1 Digito', 'Sin utilizar', 15, 0),
('1000000571', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '1.1Ω', '1.1', 'Ω', 'Resistencia ceramica de 1.1Ω 1/4', 'Sin utilizar', 34, 0),
('1000000572', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '20Ω', '20.0', 'Ω', 'Resistencia ceramica de 20Ω 1/2', 'Sin utilizar', 19, 0),
('1000000573', 'N/A', 'N/A', 'N/A', 683, 'Cerámica', '150Ω', '150.0', 'Ω', 'Resistencia ceramica de 150Ω 1/2', 'Sin utilizar', 40, 0),
('1000000574', 'N/A', 'N/A', 'N/A', 684, 'Protoboard', 'S/N', 'S/N', 'S/N', 'Tablilla Protoboard de 1 bolque 2 tiras', 'Alto', 31, 0),
('1000000575', 'N/A', 'N/A', 'N/A', 684, 'Protoboard', 'S/N', 'S/N', 'S/N', 'Tablilla Protoboard de 2 bolque 3tiras', 'Alto', 34, 0),
('1000000576', 'N/A', 'N/A', 'N/A', 684, 'Protoboard', 'S/N', 'S/N', 'S/N', 'Tablilla Protoboard de 3 bolque 7tiras', 'Alto', 34, 0),
('1000000577', 'N/A', 'N/A', 'N/A', 685, 'Banana', 'B-36', 'S/N', 'S/N', 'Cable tipo banana', 'Alto', 35, 0),
('1000000578', 'N/A', 'N/A', 'N/A', 685, 'Banana', 'S/N', 'S/N', 'S/N', 'Cable tipo banana con caiman', 'Alto', 22, 0),
('1000000579', 'N/A', 'N/A', 'N/A', 685, 'Caiman', 'S/N', 'S/N', 'S/N', 'Cable tipo caiman de 26 cm, caiman chico', 'Alto', 97, 0),
('1000000580', 'N/A', 'N/A', 'N/A', 685, 'Caiman', 'S/N', 'S/N', 'S/N', 'Cable tipo caiman de 40 cm, caiman medino', 'Alto', 34, 0),
('1000000581', 'N/A', 'N/A', 'N/A', 685, 'Caiman', 'S/N', 'S/N', 'S/N', 'Cable tipo caiman de 40 cm, caiman grande', 'Alto', 27, 0),
('1000000582', 'N/A', 'N/A', 'N/A', 685, 'Termopar', 'S/N', 'S/N', 'S/N', 'Cable tipo termopar', 'Alto', 17, 0),
('1000000583', 'N/A', 'N/A', 'N/A', 686, 'Ventilador', 'S/N', 'S/N', 'S/N', 'Abanico tipo ventilador de 12V ', 'Alto', 10, 0),
('1000000584', 'N/A', 'N/A', 'N/A', 686, 'Ventilador', '16-12038A', 'S/N', 'S/N', 'Abanico tipo ventilador de 110 - 127V ', 'Alto', 8, 0),
('1000000585', 'Metalico', 'N/A', 'N/A', 687, 'Miniatura', 'S/N', 'S/N', 'S/N', 'Microfono Electret Condensador 9x7mm Pastilla', 'Alto', 64, 0),
('1000000586', 'Metalico', 'N/A', 'N/A', 688, 'Puente Rectificador de silicio Monofasico', '2W04', '2.0', 'Amper', 'Puente Rectificador 2.0 A  400V', 'Alto', 40, 0),
('1000000587', 'Metalico', 'N/A', 'N/A', 688, 'Puente Rectificador Monofasico', 'BR64', '6.0', 'Amper', 'Puente Rectificador 6.0 A  400V', 'Alto', 16, 0),
('1000000588', 'Metalico', 'N/A', 'N/A', 689, 'Disipador', 'TO-220', 'S/N', 'S/N', 'Disipador de Calor de Aluminio 19x15x10 mm', 'Bajo', 43, 0),
('1000000589', 'Metalico', 'N/A', 'N/A', 689, 'Disipador', '30M0268', 'S/N', 'S/N', 'Disipador de Calor para Transistor To3', 'Bajo', 11, 0),
('1000000590', 'Metalico', 'N/A', 'N/A', 689, 'Disipador', 'S/N', 'S/N', 'S/N', 'Disipador de Calor Aluminio  22X32X42mm', 'Bajo', 11, 0),
('1000000591', 'Metalico', 'N/A', 'N/A', 689, 'Disipador', 'S/N', 'S/N', 'S/N', 'Disipador de Calor de Potencia Aluminio  ', 'Bajo', 54, 0);

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tipo_material`
--

CREATE TABLE `tipo_material` (
  `id_material` int(10) NOT NULL,
  `material` varchar(40) NOT NULL,
  `tipo_material` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tipo_material`
--

INSERT INTO `tipo_material` (`id_material`, `material`, `tipo_material`) VALUES
(653, 'Sensor', 'Material Consumible'),
(654, 'Circuito Integrado', 'Material Consumible'),
(655, 'Cristal', 'Material Consumible'),
(656, 'Resonador', 'Material Consumible'),
(657, 'Tablilla Electronica', 'Material Consumible'),
(658, 'N/A', 'Material Consumible'),
(659, 'Display', 'Material Consumible'),
(660, 'LED', 'Material Consumible'),
(661, 'Matriz de LED', 'Material Consumible'),
(662, 'Tiristor', 'Material Consumible'),
(663, 'Capacitor', 'Material Consumible'),
(664, 'Triodo', 'Material Consumible'),
(665, 'Regulador de Voltaje', 'Material Consumible'),
(666, 'Transistor Potencia', 'Material Consumible'),
(667, 'Banco de Resistencias', 'Material Consumible'),
(668, 'Potenciómetro', 'Material Consumible'),
(669, 'Motor', 'Material Consumible'),
(670, 'Tenaza', 'Material Consumible'),
(671, 'Interruptor', 'Material Consumible'),
(672, 'Foco', 'Material Consumible'),
(673, 'Portafoco', 'Material Consumible'),
(674, 'Inductor', 'Material Consumible'),
(675, 'Relevador', 'Material Consumible'),
(676, 'Transformador', 'Material Consumible'),
(677, 'Diodo', 'Material Consumible'),
(678, 'Celda', 'Material Consumible'),
(679, 'Transistor Baja Potencia', 'Material Consumible'),
(680, 'Transistor Media Potencia', 'Material Consumible'),
(681, 'Fototransistores', 'Material Consumible'),
(682, 'Fotodiodo', 'Material Consumible'),
(683, 'Resistencia', 'Material Consumible'),
(684, 'Tablilla', 'Material Consumible'),
(685, 'Cable', 'Material Consumible'),
(686, 'Abanico', 'Material Consumible'),
(687, 'Microfono', 'Material Consumible'),
(688, 'Puente Rectificador', 'Material Consumible'),
(689, 'Aluminio', 'Material Consumible'),
(690, 'llaves ', 'Herramienta'),
(691, 'Pinzas', 'Herramienta'),
(692, 'martillo', 'Herramienta'),
(693, 'marro', 'Herramienta'),
(694, 'arco', 'Herramienta'),
(695, 'tijeras', 'Herramienta'),
(696, 'escudra', 'Herramienta'),
(697, 'cascos', 'Herramienta'),
(698, 'caretas', 'Herramienta'),
(699, 'desarmador', 'Herramienta'),
(700, 'nivel', 'Herramienta'),
(701, 'lima', 'Herramienta'),
(702, 'cincel', 'Herramienta'),
(703, 'remachadora', 'Herramienta'),
(704, 'socket', 'Herramienta'),
(705, 'estrobo', 'Herramienta'),
(706, 'lentes', 'Herramienta'),
(707, 'sierra', 'Herramienta');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
-- Indexes for table `alumnos`
--
ALTER TABLE `alumnos`
  ADD PRIMARY KEY (`num_control`);

--
-- Indexes for table `herramienta`
--
ALTER TABLE `herramienta`
  ADD PRIMARY KEY (`cb_herramienta`);

--
-- Indexes for table `materia`
--
ALTER TABLE `materia`
  ADD PRIMARY KEY (`id_materia`);

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
-- AUTO_INCREMENT for table `materia`
--
ALTER TABLE `materia`
  MODIFY `id_materia` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id_pedido` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `pedido_material`
--
ALTER TABLE `pedido_material`
  MODIFY `id_registro` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=193;

--
-- AUTO_INCREMENT for table `tipo_material`
--
ALTER TABLE `tipo_material`
  MODIFY `id_material` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=708;

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
