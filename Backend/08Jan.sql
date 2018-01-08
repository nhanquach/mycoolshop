-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 08, 2018 at 12:03 PM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 5.6.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ecommercedb`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `_id` int(11) NOT NULL,
  `productID` int(11) NOT NULL,
  `totalAmount` int(11) NOT NULL,
  `totalMoney` int(11) NOT NULL,
  `createdTime` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `_id` int(11) NOT NULL,
  `id` varchar(45) NOT NULL,
  `name` varchar(50) NOT NULL,
  `type` varchar(45) NOT NULL DEFAULT 'PK'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`_id`, `id`, `name`, `type`) VALUES
(2, 'Chuot', 'Chuột', 'Phụ kiện'),
(11, 'Banphim', 'Bàn phím', 'PK'),
(12, 'Tainghe', 'Tai nghe', 'PK'),
(13, 'Danmanhinh', 'Miếng dán màn hình', 'PK');

-- --------------------------------------------------------

--
-- Table structure for table `log`
--

CREATE TABLE `log` (
  `_id` int(11) NOT NULL,
  `productID` int(11) NOT NULL,
  `updatedTime` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `_id` int(11) NOT NULL,
  `id_order_products` varchar(45) NOT NULL,
  `id_user` varchar(45) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `address` varchar(1000) NOT NULL,
  `status` varchar(45) NOT NULL DEFAULT 'PROGRESSING',
  `delivery_note` varchar(1000) DEFAULT 'None',
  `user_name` varchar(255) NOT NULL,
  `price` int(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`_id`, `id_order_products`, `id_user`, `created_at`, `address`, `status`, `delivery_note`, `user_name`, `price`, `phone`, `email`) VALUES
(4, '746493855', '4', '2017-12-13 22:40:50', 'Someplace in the Earth.', 'PROGRESSING', 'none', 'Quách Trọng Nhân', 250000, '0938217232', 'nhan@nhan.com'),
(13, '054915212', '179432170', '2017-12-13 15:03:52', 'nhan', 'PROGRESSING', NULL, 'Nhan', 0, '', ''),
(14, '180912377', '794614416', '2017-12-13 15:05:19', 'nhan', 'PROGRESSING', NULL, 'Nhan', 0, '', ''),
(15, '572739410', '750244540', '2017-12-13 15:07:31', 'nhan', 'PROGRESSING', 'nhan', 'Nhan', 0, '', ''),
(16, '305124725', '263292995', '2017-12-13 15:09:17', 'nhan', 'PROGRESSING', 'None', 'Nhan', 0, '', ''),
(17, '723360422', '766722524', '2017-12-13 15:12:12', 'nhan', 'PROGRESSING', 'none', 'nhan', 0, '', ''),
(18, '282573951', '895684988', '2017-12-13 15:15:45', '9123791379127aksjdhkajsdh', 'PROGRESSING', 'This is a note.', 'Nhan', 152999, '', ''),
(19, '379719204', '075917095', '2017-12-13 15:26:45', '12309', 'PROGRESSING', 'Note this.', 'Nhan', 152999, '', ''),
(20, '207034721', '738257899', '2017-12-13 15:28:12', 'nhan', 'PROGRESSING', 'nhan', 'Nhan', 152999, '', ''),
(21, '982713476', '686445075', '2017-12-13 15:29:34', 'nhan', 'PROGRESSING', 'nhan', 'Nhan', 152999, '', ''),
(22, '073149573', '481001522', '2017-12-13 15:30:05', 'nhan', 'PROGRESSING', 'nhan', 'Nhan', 852999, '', ''),
(23, '118493502', '235562885', '2017-12-13 15:40:11', 'askjdh', 'PROGRESSING', 'jsadh0192381', 'Nhan Qusch', 852999, '', ''),
(24, '771429082', '537540842', '2017-12-13 15:44:55', 'Googlex', 'PROGRESSING', 'GOoasdasd', 'NhanQussch', 852999, '1293', 'nhan@nhan.com'),
(25, '292982027', '408212922', '2017-12-13 15:47:29', 'ajshd', 'PROGRESSING', 'Nothng', 'Nhan', 852999, '123131498', '219kda@gmail.com'),
(26, '382061297', '152208569', '2017-12-13 15:48:45', 'jasdh', 'PROGRESSING', 'none', 'Nhan', 852999, '12387', 'asajsd@askdjh.com'),
(27, '650510525', '4', '2017-12-13 22:48:21', 'Someplace in the Earth.', 'PROGRESSING', 'none', 'Quách Trọng Nhân', 2999, '0938217232', 'nhan@nhan.com'),
(28, '949950648', '4', '2017-12-14 22:38:31', 'Someplace in the Earth.', 'PROGRESSING', 'Take a very good care of this.', 'Quách Trọng Nhân', 202999, '0938217232', 'nhan@nhan.com'),
(29, '523238967', '4', '2017-12-14 22:40:00', 'Someplace in the Earth.', 'PROGRESSING', 'none', 'Quách Trọng Nhân', 100000, '0938217232', 'nhan@nhan.com'),
(30, '866171325', '616155630', '2017-12-14 23:15:11', 'Someplace in the Earth.', 'PROGRESSING', 'none', 'Quách Trọng Nhân', 100000, '0938217232', 'nhan@nhan.com');

-- --------------------------------------------------------

--
-- Table structure for table `order_products`
--

CREATE TABLE `order_products` (
  `_id` int(11) NOT NULL,
  `order_id` varchar(45) NOT NULL,
  `product_id` varchar(45) NOT NULL,
  `product_name` varchar(500) NOT NULL,
  `product_price` int(10) NOT NULL,
  `product_quantity` int(10) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `order_products`
--

INSERT INTO `order_products` (`_id`, `order_id`, `product_id`, `product_name`, `product_price`, `product_quantity`) VALUES
(1, '982713476', '2', 'Chuột có dây', 100000, 1),
(2, '982713476', '963615725', 'Razer BlackWidow X', 2999, 1),
(3, '982713476', '1', 'Chuột không dây', 50000, 1),
(4, '073149573', '1', 'Chuột không dây', 50000, 1),
(5, '073149573', '2', 'Chuột có dây', 100000, 8),
(6, '073149573', '963615725', 'Razer BlackWidow X', 2999, 1),
(7, '118493502', '2', 'Chuột có dây', 100000, 8),
(8, '118493502', '963615725', 'Razer BlackWidow X', 2999, 1),
(9, '118493502', '1', 'Chuột không dây', 50000, 1),
(10, '771429082', '1', 'Chuột không dây', 50000, 1),
(11, '771429082', '963615725', 'Razer BlackWidow X', 2999, 1),
(12, '771429082', '2', 'Chuột có dây', 100000, 8),
(13, '292982027', '963615725', 'Razer BlackWidow X', 2999, 1),
(14, '292982027', '1', 'Chuột không dây', 50000, 1),
(15, '292982027', '2', 'Chuột có dây', 100000, 8),
(16, '382061297', '963615725', 'Razer BlackWidow X', 2999, 1),
(17, '382061297', '1', 'Chuột không dây', 50000, 1),
(18, '382061297', '2', 'Chuột có dây', 100000, 8),
(19, '369708069', '2', 'Chuột có dây', 100000, 8),
(20, '369708069', '963615725', 'Razer BlackWidow X', 2999, 1),
(21, '369708069', '1', 'Chuột không dây', 50000, 1),
(22, '769411627', '963615725', 'Razer BlackWidow X', 2999, 1),
(23, '769411627', '2', 'Chuột có dây', 100000, 1),
(24, '837920050', '1', 'Chuột không dây', 50000, 5),
(25, '746493855', '1', 'Chuột không dây', 50000, 5),
(26, '650510525', '963615725', 'Razer BlackWidow X', 2999, 1),
(27, '949950648', '2', 'Chuột có dây', 100000, 1),
(28, '949950648', '1', 'Chuột không dây', 50000, 2),
(29, '949950648', '963615725', 'Razer BlackWidow X', 2999, 1),
(30, '523238967', '2', 'Chuột có dây', 100000, 1),
(31, '866171325', '2', 'Chuột có dây', 100000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(2555) NOT NULL,
  `price` int(11) NOT NULL,
  `image` varchar(400) DEFAULT NULL,
  `available_start` varchar(25) CHARACTER SET latin1 DEFAULT NULL,
  `available_end` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `seller` varchar(45) NOT NULL DEFAULT 'mycoolshop',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `name`, `description`, `price`, `image`, `available_start`, `available_end`, `seller`, `created_at`) VALUES
(35393972, 'Tai nghe Microlab E130', 'Tương thích với mọi thiết bị\nSử dụng đầu cắm 3.5mm\nTích hợp micro', 205000, 'https://firebasestorage.googleapis.com/v0/b/dazzling-torch-5860.appspot.com/o/product_images%2Fmty48n0ct37?alt=media&token=979da16e-ea03-4ca0-8f97-4fc187558cc2', NULL, NULL, 'mycoolshop', '2018-01-08 16:25:16'),
(258509296, 'Tai nghe Awei A930BL', 'Tai nghe dạng móc vành tai trong chống rơi rớt.\nKhông lo rơi tai nghe khi không dùng đến nhờ 2 đầu hít nam châm.\nThời gian thoại 6 giờ, nghe nhạc 4 giờ, sạc 1 tiếng.\nCông nghệ bluetooth 4.1 cho khoảng cách kết nối tốt lên đến 10 m.\nCó nút ấn nhận cuộc gọi, ngừng/chơi nhạc, tăng giảm âm lượng.', 450000, 'https://firebasestorage.googleapis.com/v0/b/dazzling-torch-5860.appspot.com/o/product_images%2F6lrn6zuu0x7?alt=media&token=ca253d7a-99a0-4233-a851-1ac4164670ca', NULL, NULL, 'mycoolshop', '2018-01-08 16:47:03'),
(295626935, 'Bàn phím Logitech K270', 'Bàn phím bluetooth Logitech\nThiết kế mỏng, nhỏ gọn\nKết nối được với nhiều thiết bị\nSử dụng Pin 2 AAA', 400000, 'https://firebasestorage.googleapis.com/v0/b/dazzling-torch-5860.appspot.com/o/product_images%2Fuaakgms4o6q?alt=media&token=c75eb061-89ad-4123-abd5-ac4aead97d16', NULL, NULL, 'mycoolshop', '2018-01-08 16:17:28'),
(333409718, 'Bàn phím Prolink PKCS-1003', 'Kết nối cổng USB\nCáp dài 1.35m\nSố phím: 103', 180000, 'https://firebasestorage.googleapis.com/v0/b/dazzling-torch-5860.appspot.com/o/product_images%2Fxhijrsld10b?alt=media&token=ce4808af-f0ac-4c9f-845b-5325b04d713b', NULL, NULL, 'mycoolshop', '2018-01-08 16:09:50'),
(461544068, 'Chuột Prolink G9501', 'Số nút: 8 nút\nĐộ nhạy: 750/1200/1600/2400 DPI\nTốc độ phản hồi tối đa: 1000 Hz\nTốc độ chuột tối đa: 28\"/giây\nFPS: 3000 lần/giây\nTuổi thọ phím bấm: 5 triệu lần nhấn\nKích thước: 120 x 63 x 35 mm\nTrọng lượng: 149g', 500000, 'https://firebasestorage.googleapis.com/v0/b/dazzling-torch-5860.appspot.com/o/product_images%2Fu0f6989hzv?alt=media&token=f117f9af-1c5e-449f-848c-a0e9fc27e368', NULL, NULL, 'mycoolshop', '2018-01-08 15:49:05'),
(484890717, 'Chuột Quang Prolink C6001', 'Độ phân giải quang học cao: 1000dpi\nDi chuyển chính xác trên nhiều bề mặt\n05 nút điều khiển\nThiết kế cân đối, phù hợp cả với người thuận tay trái hoặc tay phải', 300000, 'https://firebasestorage.googleapis.com/v0/b/dazzling-torch-5860.appspot.com/o/product_images%2Fp1435yxnlfc?alt=media&token=f913fba0-cdaa-4edb-9ed0-4225ef3944b4', NULL, NULL, 'mycoolshop', '2018-01-08 15:42:15'),
(567774026, 'Miếng dán màn hình iPhone X', 'Chống trầy xước tối ưu cho màn hình điện thoại iPhone X\nLàm giảm các dấu vân tay và vết ố\nChất liệu Nhật Bản và đóng gói tại Đài Loan', 60000, 'https://firebasestorage.googleapis.com/v0/b/dazzling-torch-5860.appspot.com/o/product_images%2Fyzzzwqs0o3r?alt=media&token=82dd14f2-17d7-4a93-b63e-07a39c9bd224', NULL, NULL, 'mycoolshop', '2018-01-08 17:28:41');

-- --------------------------------------------------------

--
-- Table structure for table `product_extra`
--

CREATE TABLE `product_extra` (
  `_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `category_id` varchar(45) NOT NULL,
  `subcategory_id` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product_extra`
--

INSERT INTO `product_extra` (`_id`, `product_id`, `category_id`, `subcategory_id`) VALUES
(3, 1, 'Chuot', 'C1'),
(4, 2, 'Chuot', 'C2'),
(5, 2, 'Chuot', 'C3'),
(6, 963615725, 'Banphim', 'GB'),
(7, 484890717, 'Chuot', 'C1'),
(8, 461544068, 'Chuot', 'GM'),
(9, 461544068, 'Chuot', 'C2'),
(10, 295626935, 'Banphim', 'B2'),
(11, 35393972, 'Tainghe', 'T1'),
(12, 258509296, 'Tainghe', 'T3');

-- --------------------------------------------------------

--
-- Table structure for table `subcategory`
--

CREATE TABLE `subcategory` (
  `_id` int(11) NOT NULL,
  `id` varchar(45) NOT NULL,
  `name` varchar(50) NOT NULL,
  `type` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `subcategory`
--

INSERT INTO `subcategory` (`_id`, `id`, `name`, `type`) VALUES
(3, 'C1', 'Chuột không dây', NULL),
(4, 'C2', 'Chuột có dây', NULL),
(5, 'C3', 'Chuột biến hình', NULL),
(6, 'T1', 'Tai nghe có dây', NULL),
(7, 'T2', 'Tai nghe không dây', NULL),
(8, 'T3', 'Tai nghe Bluetooth', NULL),
(9, 'GM', 'Gaming Mouse', NULL),
(10, 'GB', 'Gaming Keyboard', NULL),
(11, 'B1', 'Bàn phím có dây', NULL),
(12, 'B2', 'Bàn phím không dây', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `_id` int(11) NOT NULL,
  `email` varchar(45) NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 NOT NULL,
  `password` text NOT NULL,
  `userType` varchar(10) DEFAULT 'user',
  `phone` varchar(20) DEFAULT NULL,
  `address` varchar(500) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`_id`, `email`, `username`, `password`, `userType`, `phone`, `address`) VALUES
(1, 'nhan@gmail.com', 'admin', 'admin', 'Admin', NULL, NULL),
(4, 'nhan@nhan.com', 'Quách Trọng Nhân', 'user', 'user', '0938217232', 'Someplace in the Earth.'),
(5, 'tuanuy@cinderella.com', 'Trương Tuấn Uy', 'user', 'user', NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`_id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`_id`),
  ADD UNIQUE KEY `id_UNIQUE` (`id`);

--
-- Indexes for table `log`
--
ALTER TABLE `log`
  ADD PRIMARY KEY (`_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`_id`),
  ADD UNIQUE KEY `_id_UNIQUE` (`_id`);

--
-- Indexes for table `order_products`
--
ALTER TABLE `order_products`
  ADD PRIMARY KEY (`_id`),
  ADD UNIQUE KEY `_id_UNIQUE` (`_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product_extra`
--
ALTER TABLE `product_extra`
  ADD PRIMARY KEY (`_id`),
  ADD UNIQUE KEY `id_UNIQUE` (`_id`),
  ADD KEY `product_id_idx` (`product_id`);

--
-- Indexes for table `subcategory`
--
ALTER TABLE `subcategory`
  ADD PRIMARY KEY (`_id`),
  ADD UNIQUE KEY `id_subcategory_UNIQUE` (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `order_products`
--
ALTER TABLE `order_products`
  MODIFY `_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=567774027;

--
-- AUTO_INCREMENT for table `product_extra`
--
ALTER TABLE `product_extra`
  MODIFY `_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `subcategory`
--
ALTER TABLE `subcategory`
  MODIFY `_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
