-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 20 Okt 2016 pada 18.57
-- Versi Server: 10.1.13-MariaDB
-- PHP Version: 5.6.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbaplikasipenjualan`
--
DROP DATABASE `dbaplikasipenjualan`;
CREATE DATABASE IF NOT EXISTS `dbaplikasipenjualan` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `dbaplikasipenjualan`;

-- --------------------------------------------------------

--
-- Struktur dari tabel `barang`
--

DROP TABLE IF EXISTS `barang`;
CREATE TABLE `barang` (
  `kode` varchar(15) NOT NULL,
  `nama` varchar(32) NOT NULL,
  `harga` bigint(11) NOT NULL,
  `stok` int(11) NOT NULL,
  `satuan` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `barang`
--

INSERT INTO `barang` (`kode`, `nama`, `harga`, `stok`, `satuan`) VALUES
('abc123', '546787', 30000, 350, 'pcs'),
('abc12345', 'Kecap bcd', 2000, 200, 'pcs'),
('fgh678', 'asdeh', 65500, 1000, 'kg'),
('fghj67676', 'baaranglk', 530000, 350, 'pcs');

-- --------------------------------------------------------

--
-- Struktur dari tabel `customer`
--

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `nama` varchar(32) NOT NULL,
  `alamat` varchar(32) DEFAULT NULL,
  `telp` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `customer`
--

INSERT INTO `customer` (`id`, `nama`, `alamat`, `telp`) VALUES
(2, 'abcdef', 'disana', '12345'),
(3, 'cdefgh', 'siru', '3314');

-- --------------------------------------------------------

--
-- Struktur dari tabel `penjualan`
--

DROP TABLE IF EXISTS `penjualan`;
CREATE TABLE `penjualan` (
  `kodepenjualan` varchar(32) NOT NULL,
  `tanggal` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `idcustomer` int(11) DEFAULT NULL,
  `kasir` varchar(32) NOT NULL,
  `kodebarang` varchar(15) NOT NULL,
  `qty` int(11) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `penjualan`
--

INSERT INTO `penjualan` (`kodepenjualan`, `tanggal`, `idcustomer`, `kasir`, `kodebarang`, `qty`, `id`) VALUES
('ja123', '1970-01-01 00:00:00', 3, 'null', 'abc123', 3, 2),
('ja123', '1970-01-01 00:00:00', 3, 'null', 'fgh678', 2, 3),
('laris231', '1970-01-01 00:00:00', 2, 'null', 'abc123', 1, 4),
('laris231', '1970-01-01 00:00:00', 2, 'null', 'fgh678', 2, 5),
('laris345', '1970-01-01 00:00:00', 2, 'null', 'fgh678', 3, 6),
('abcde9898', '2016-10-16 00:00:00', 2, 'kasir', 'abc123', 1, 7),
('abcde9898', '2016-10-16 00:00:00', 2, 'kasir', 'fgh678', 2, 8),
('laris1234', '2016-10-16 00:00:00', 3, 'kasir', 'abc123', 5, 9),
('laris1234', '2016-10-16 00:00:00', 3, 'kasir', 'fgh678', 2, 10),
('abc1234', '2016-10-17 00:00:00', 3, 'kasir', 'fgh678', 89, 11),
('abc1234', '2016-10-17 00:00:00', 3, 'kasir', 'abc123', 34, 12);

-- --------------------------------------------------------

--
-- Struktur dari tabel `suplier`
--

DROP TABLE IF EXISTS `suplier`;
CREATE TABLE `suplier` (
  `id` int(11) NOT NULL,
  `nama` varchar(32) NOT NULL,
  `alamat` varchar(32) DEFAULT NULL,
  `telp` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `suplier`
--

INSERT INTO `suplier` (`id`, `nama`, `alamat`, `telp`) VALUES
(1, 'grtrtyy', 'asfasfas', '343223');

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(32) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`username`, `password`) VALUES
('admin', '21232f297a57a5a743894a0e4a801fc3'),
('andi', 'ce0e5bf55e4f71749eade7a8b95c4e46');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`kode`),
  ADD UNIQUE KEY `kode` (`kode`),
  ADD KEY `kode_2` (`kode`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `penjualan`
--
ALTER TABLE `penjualan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `suplier`
--
ALTER TABLE `suplier`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`),
  ADD UNIQUE KEY `namalogin` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `penjualan`
--
ALTER TABLE `penjualan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `suplier`
--
ALTER TABLE `suplier`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
