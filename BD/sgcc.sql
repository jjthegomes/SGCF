-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 25-Maio-2019 às 21:12
-- Versão do servidor: 10.1.40-MariaDB
-- versão do PHP: 7.1.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sgcc`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `administrador`
--

CREATE TABLE `administrador` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `administrador`
--

INSERT INTO `administrador` (`id`, `email`, `nome`, `senha`) VALUES
(1, 'admin@gmail.com', 'Administrador', '123');

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE `cliente` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `bairro` varchar(255) DEFAULT NULL,
  `celular` varchar(255) DEFAULT NULL,
  `cep` varchar(255) DEFAULT NULL,
  `cidade` varchar(255) DEFAULT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  `cpf` varchar(255) DEFAULT NULL,
  `data_nascimento` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `estado_civil` varchar(255) DEFAULT NULL,
  `logradouro` varchar(255) DEFAULT NULL,
  `nome_social` varchar(255) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `rg` varchar(255) DEFAULT NULL,
  `sexo` varchar(255) DEFAULT NULL,
  `situacao` varchar(255) DEFAULT NULL,
  `telefone` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`id`, `email`, `nome`, `senha`, `bairro`, `celular`, `cep`, `cidade`, `complemento`, `cpf`, `data_nascimento`, `estado`, `estado_civil`, `logradouro`, `nome_social`, `numero`, `rg`, `sexo`, `situacao`, `telefone`) VALUES
(3, 'lucas@gmail.com', 'Lucas Gama', '123', 'São Geraldo', '32999859717', '36031369', 'Juiz de Fora', '', '073.099.056-79', '17/04/1998', 'MG', 'Casado', 'Jacinto Furtado de Mendonça, São Geraldo, 131', NULL, '131', '', 'M', NULL, '(32)99985-9717'),
(4, 'thalita@gmail.com', 'Thalita Arena', '123', 'São Geraldo', '32999859717', '36031369', 'Juiz de Fora', '', '073.099.056-79', '17/04/1998', 'MG', 'Solteira', 'Jacinto Furtado de Mendonça, São Geraldo, 131', NULL, '131', '', 'F', NULL, '(32)99985-9717'),
(5, 'gabi@gmail.com', 'Gabriela Pinto', '123', 'Ipiranga', '32999859717', '36031450', 'Juiz de Fora', '', '001.051.215-64', '18/05/1990', 'MG', 'Casada', 'Rua Carlota Penaforte', NULL, '120', '', 'F', NULL, '(32)99985-9717'),
(6, 'jjthegomes@gmail.com', 'Jonas ', '123', 'São Geraldo', '(32)9998-59717', '36031369', 'Juiz de Fora', '', '123.538.496-95', '17/04/1998', 'MG', 'Solteiro', 'Rua Jacinto Furtado de Mendonça', NULL, '131', '', 'M', NULL, '(32)9956-6528');

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionario`
--

CREATE TABLE `funcionario` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `bairro` varchar(255) DEFAULT NULL,
  `celular` varchar(255) DEFAULT NULL,
  `cep` varchar(255) DEFAULT NULL,
  `cidade` varchar(255) DEFAULT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  `cpf` varchar(255) DEFAULT NULL,
  `data_nascimento` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `estado_civil` varchar(255) DEFAULT NULL,
  `logradouro` varchar(255) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `rg` varchar(255) DEFAULT NULL,
  `sexo` varchar(255) DEFAULT NULL,
  `telefone` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `funcionario`
--

INSERT INTO `funcionario` (`id`, `email`, `nome`, `senha`, `bairro`, `celular`, `cep`, `cidade`, `complemento`, `cpf`, `data_nascimento`, `estado`, `estado_civil`, `logradouro`, `numero`, `rg`, `sexo`, `telefone`) VALUES
(1, 'ibernard@gmail.com', 'Icaro Bernard', '123', 'São Geraldo', '32999859717', '36031369', 'Juiz de Fora', '', '12345678911', '17/04/1998', 'MG', 'Solteiro', 'Jacinto Furtado de Mendonça, São Geraldo, 131', '131', '', 'M', '(32)99985-9717'),
(2, 'marcos@gmail.com', 'Marcos Pereiraa', '123', 'São Geraldo', '(32)99985-9717', '36031369', 'Juiz de Fora', '', '12345678911', '17/04/1998', 'MG', 'Casado', 'Jacinto Furtado de Mendonça, São Geraldo, 131', '131', '', 'M', '(32)99985-9717');

-- --------------------------------------------------------

--
-- Estrutura da tabela `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(7),
(7),
(7);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `administrador`
--
ALTER TABLE `administrador`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_h121ki47maojpkmvdvqf87ybo` (`email`);

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_cmxo70m08n43599l3h0h07cc6` (`email`);

--
-- Indexes for table `funcionario`
--
ALTER TABLE `funcionario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_t45qja1wnv0hu1cdw6vqjljgy` (`email`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
