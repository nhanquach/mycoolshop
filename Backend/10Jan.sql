-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 10, 2018 at 11:47 AM
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
(1, '964896023', '5', '2018-01-10 17:23:33', 'Switzerland', 'PROGRESSING', 'What a peaceful land !!!', 'Trương Tuấn Uy', 75, '0934xxxxxx', 'tuanuy@cinderella.com'),
(2, '030754818', '5', '2018-01-10 17:26:35', 'Australia', 'PROGRESSING', 'This land is so hot at this time.', 'Trương Tuấn Uy', 38, '0934xxxxxx', 'tuanuy@cinderella.com'),
(3, '393148146', '5', '2018-01-10 17:31:11', 'Canada', 'DONE', 'Maybe I will travel to Canada someday !!!', 'Trương Tuấn Uy', 132, '0934xxxxxx', 'tuanuy@cinderella.com'),
(4, '746493855', '4', '2017-12-13 22:40:50', 'Someplace in the Earth.', 'CANCELLED', 'none', 'Quách Trọng Nhân', 250000, '0938217232', 'nhan@nhan.com'),
(5, '705658634', '5', '2018-01-10 17:42:55', 'Germany', 'PROGRESSING', 'I guess there \'ll be a lot of delicious food there', 'Trương Tuấn Uy', 165, '0934xxxxxx', 'tuanuy@cinderella.com'),
(18, '282573951', '895684988', '2017-12-13 15:15:45', '9123791379127aksjdhkajsdh', 'CANCELLED', 'This is a note.', 'Nhan', 152999, '0938217232', 'nhan@nhan.com'),
(19, '379719204', '075917095', '2017-12-13 15:26:45', '12309', 'DONE', 'Note this.', 'Nhan', 152999, '0938217232', 'nhan@nhan.com'),
(20, '207034721', '738257899', '2017-12-13 15:28:12', 'nhan', 'DONE', 'nhan', 'Nhan', 152999, '0938217232', 'nhan@nhan.com'),
(21, '982713476', '686445075', '2017-12-13 15:29:34', 'nhan', 'PROGRESSING', 'nhan', 'Nhan', 152999, '0938217232', 'nhan@nhan.com'),
(22, '073149573', '481001522', '2017-12-13 15:30:05', 'nhan', 'PROGRESSING', 'nhan', 'Nhan', 852999, '0938217232', 'nhan@nhan.com'),
(23, '118493502', '235562885', '2017-12-13 15:40:11', 'askjdh', 'PROGRESSING', 'jsadh0192381', 'Nhan Qusch', 852999, '0938217232', ''),
(24, '771429082', '537540842', '2017-12-13 15:44:55', 'Googlex', 'PROGRESSING', 'GOoasdasd', 'NhanQussch', 852999, '1293', 'nhan@nhan.com'),
(25, '292982027', '408212922', '2017-12-13 15:47:29', 'ajshd', 'DONE', 'Nothng', 'Nhan', 852999, '123131498', '219kda@gmail.com'),
(26, '382061297', '152208569', '2017-12-13 15:48:45', 'jasdh', 'DONE', 'none', 'Nhan', 852999, '12387', 'asajsd@askdjh.com'),
(27, '650510525', '4', '2017-12-13 22:48:21', 'Someplace in the Earth.', 'CANCELLED', 'none', 'Quách Trọng Nhân', 2999, '0938217232', 'nhan@nhan.com'),
(28, '949950648', '4', '2017-12-14 22:38:31', 'Someplace in the Earth.', 'DONE', 'Take a very good care of this.', 'Quách Trọng Nhân', 202999, '0938217232', 'nhan@nhan.com'),
(29, '523238967', '4', '2017-12-14 22:40:00', 'Someplace in the Earth.', 'PROGRESSING', 'none', 'Quách Trọng Nhân', 100000, '0938217232', 'nhan@nhan.com'),
(30, '866171325', '616155630', '2017-12-14 23:15:11', 'Someplace in the Earth.', 'PROGRESSING', 'none', 'Quách Trọng Nhân', 100000, '0938217232', 'nhan@nhan.com'),
(31, '878334140', '327880109', '2017-12-15 21:17:06', '480/58 Bình Quới, phường 28, Quận Bình Thạnh', 'DONE', 'Hi, be nice with my Stuffs!!!!', 'Nhân Trọng Quách', 68977, '+84938217232', 'xomix1802@gmail.com'),
(32, '362389100', '4', '2017-12-19 13:11:29', 'Someplace in the Earth.', 'PROGRESSING', 'none', 'Quách Trọng Nhân', 150000, '0938217232', 'nhan@nhan.com'),
(33, '455299119', '4', '2017-12-19 13:18:07', 'Someplace in the Earth.', 'PROGRESSING', 'none', 'Quách Trọng Nhân', 100000, '0938217232', 'nhan@nhan.com'),
(34, '304487179', '239130298', '2018-01-03 21:09:49', '480/58 Bình Quới, phường 28, Quận Bình Thạnh', 'PROGRESSING', 'Please delivery with care!', 'Nhân Trọng Quách', 1313, '+84938217232', 'xomix1802@gmail.com'),
(35, '040845512', '4', '2018-01-10 17:20:56', 'Someplace in the Earth.', 'PROGRESSING', 'none', 'Quách Trọng Nhân', 50, '0938217232', 'nhan@nhan.com');

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
(31, '866171325', '2', 'Chuột có dây', 100000, 1),
(32, '878334140', '963615725', 'Razer BlackWidow X', 2999, 23),
(33, '362389100', '1', 'Chuột không dây', 50000, 1),
(34, '362389100', '2', 'Chuột có dây', 100000, 1),
(35, '455299119', '2', 'Chuột có dây', 100000, 1),
(36, '304487179', '1', 'Logitech M510 Wireless Mouse', 13, 7),
(37, '304487179', '2', 'HP x500 Optical Wired USB Mouse', 13, 2),
(38, '304487179', '963615725', 'Razer BlackWidow X', 299, 4),
(39, '040845512', '73988542', 'Chuột Prolink G9501', 25, 2),
(40, '964896023', '73988542', 'Chuột Prolink G9501', 25, 3),
(41, '030754818', '295626935', 'Bàn phím Logitech K270', 19, 2),
(42, '393148146', '776001255', 'Tai nghe Sony MDRZX100 ZX', 15, 7),
(43, '393148146', '35393972', 'Tai nghe Microlab E130', 9, 3),
(44, '705658634', '484890717', 'Chuột Quang Prolink C6001', 14, 2),
(45, '705658634', '57619580', 'Obins Anne Pro Ergonomic Backlight', 62, 1),
(46, '705658634', '258509296', 'Tai nghe Awei A930BL', 21, 3),
(47, '705658634', '567774026', 'Miếng dán màn hình iPhone X', 3, 4);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(2555) NOT NULL,
  `price` float NOT NULL,
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
(1, 'Logitech M510 Wireless Mouse', 'Logitech Wireless Mouse M510\nExtra comfort and control to get more done\nGet the mouse that gives you more control to get things done—and feels good doing it. With extra controls at your fingertips, M510 lets you browse the web, flip through photo albums and navigate documents faster and easier. Point-and-click precision, long battery life, and a hand-friendly shape combine to help you get more done, more comfortably every day.\n\n* Requires Logitech Options software available for download at www.logitech.com/downloads\n\n** Battery life may vary based on user and computing conditions.', 13, 'https://firebasestorage.googleapis.com/v0/b/dazzling-torch-5860.appspot.com/o/product_images%2F7vccsnf5f0t?alt=media&token=5fe3b411-67b9-4988-93ca-3dc79dd396a8', '2017-12-08 14:16:13', '2018-12-08 14:16:13', 'mycoolshop', '2016-12-01 14:38:46'),
(2, 'HP x500 Optical Wired USB Mouse', 'HP X500 Wired Mouse Stylish enhancement to a classic.\nEnjoy the comfortable HP Wired Mouse X500 with simple USB connection.\nThe optical tracking provides smooth tracking and programmable 3 button controls that can work with either hand.\nSupport through the subtle form: Elongated arch and contoured design provide relaxed control for either hand.\n1 step USB: By simply inserting the USB cord, you power your mouse and avoid ever replacing batteries.\nNo software or downloads. Just start scrolling and clicking away.\nFamiliar controls: Navigate Windows easily with 3 button control and scroll wheel. \nOptimal light: The optical tracking technology provides ideal tracking on most surfaces for accurate and smooth control.\nCompatibility Windows 8 Windows 7 Windows Vista Windows XP Mac OSX', 13, 'https://firebasestorage.googleapis.com/v0/b/dazzling-torch-5860.appspot.com/o/product_images%2Fvjbhw3g3phs?alt=media&token=8d436c05-8c17-4caf-bec4-84cdd6f1261c', '2017-12-08 14:16:13', '2018-12-08 14:16:13', 'mycoolshop', '2017-11-01 14:38:46'),
(35393972, 'Tai nghe Microlab E130', 'Tương thích với mọi thiết bị\nSử dụng đầu cắm 3.5mm\nTích hợp micro', 9, 'https://firebasestorage.googleapis.com/v0/b/dazzling-torch-5860.appspot.com/o/product_images%2Fgo9abbcirx9?alt=media&token=43c7ff3e-c4ec-4998-b474-d22e67b26892', NULL, NULL, 'mycoolshop', '2018-01-08 16:25:16'),
(57619580, 'Obins Anne Pro Ergonomic Backlight', 'Condition:	\nNew: A brand-new, unused, unopened, undamaged item in its original packaging (where packaging is ... Read more\nKeyboard/Keypad:	Keyboard\nBrand:	\nObins\nMPN:	\nDoes Not Apply\nCountry/Region of Manufacture:	China', 62, 'https://firebasestorage.googleapis.com/v0/b/dazzling-torch-5860.appspot.com/o/product_images%2F428izrdcuj?alt=media&token=34dece32-1e90-4791-9339-26b5f56c4669', NULL, NULL, 'mycoolshop', '2018-01-04 11:26:36'),
(73988542, 'Chuột Prolink G9501', 'Cảm biến: Optical (Quang học)\nSố nút: 8 nút\nĐộ nhạy: 750/1200/1600/2400 DPI\nTốc độ phản hồi tối đa: 1000 Hz\nTốc độ chuột tối đa: 28\"/giây\nFPS: 3000 lần/giây\nTuổi thọ phím bấm: 5 triệu lần nhấn\nKích thước: 120 x 63 x 35 mm\nTrọng lượng: 149g', 25, 'https://firebasestorage.googleapis.com/v0/b/dazzling-torch-5860.appspot.com/o/product_images%2Frl4fbwkz74i?alt=media&token=794a9738-f25a-46d4-89cc-938179a3c425', NULL, NULL, 'mycoolshop', '2018-01-10 16:39:44'),
(156410961, 'Wireless Keyboard+Mouse Macbook', 'Condition:	\nNew: A brand-new, unused, unopened, undamaged item in its original packaging (where packaging is ... Read more\nBrand:	\nUnbranded/Generic\nKeyboard size:	284x134x18mm	MPN:	\nDoes Not Apply\nInterface:	USB 2.0	UPC:	\nDoes not apply\nOperating Range:	10 meters', 25, 'https://firebasestorage.googleapis.com/v0/b/dazzling-torch-5860.appspot.com/o/product_images%2F0n9xbz728yjc?alt=media&token=6f2dad23-5ac0-4cd8-a2b6-c38956e5e96f', NULL, NULL, 'mycoolshop', '2018-01-04 11:32:43'),
(258509296, 'Tai nghe Awei A930BL', 'Tai nghe dạng móc vành tai trong chống rơi rớt.\nKhông lo rơi tai nghe khi không dùng đến nhờ 2 đầu hít nam châm.\nThời gian thoại 6 giờ, nghe nhạc 4 giờ, sạc 1 tiếng.\nCông nghệ bluetooth 4.1 cho khoảng cách kết nối tốt lên đến 10 m.\nCó nút ấn nhận cuộc gọi, ngừng/chơi nhạc, tăng giảm âm lượng.', 21, 'https://firebasestorage.googleapis.com/v0/b/dazzling-torch-5860.appspot.com/o/product_images%2Fson5f62dbhc?alt=media&token=2cae5de5-2d57-43dc-8b0a-2ddd44678b91', NULL, NULL, 'mycoolshop', '2018-01-08 16:47:03'),
(295626935, 'Bàn phím Logitech K270', 'Bàn phím bluetooth Logitech\nThiết kế mỏng, nhỏ gọn\nKết nối được với nhiều thiết bị\nSử dụng Pin 2 AAA', 19, 'https://firebasestorage.googleapis.com/v0/b/dazzling-torch-5860.appspot.com/o/product_images%2Fuaakgms4o6q?alt=media&token=c75eb061-89ad-4123-abd5-ac4aead97d16', NULL, NULL, 'mycoolshop', '2018-01-08 16:17:28'),
(333409718, 'Bàn phím Prolink PKCS-1003', 'Kết nối cổng USB\nCáp dài 1.35m\nSố phím: 103', 18, 'https://firebasestorage.googleapis.com/v0/b/dazzling-torch-5860.appspot.com/o/product_images%2Fxhijrsld10b?alt=media&token=ce4808af-f0ac-4c9f-845b-5325b04d713b', NULL, NULL, 'mycoolshop', '2018-01-08 16:09:50'),
(484890717, 'Chuột Quang Prolink C6001', 'Độ phân giải quang học cao: 1000dpi\nDi chuyển chính xác trên nhiều bề mặt\n05 nút điều khiển\nThiết kế cân đối, phù hợp cả với người thuận tay trái hoặc tay phải', 14, 'https://firebasestorage.googleapis.com/v0/b/dazzling-torch-5860.appspot.com/o/product_images%2Fyihz87suf9?alt=media&token=3d3b2c93-0227-4db5-8ea6-90bcf31ca3c6', NULL, NULL, 'mycoolshop', '2018-01-08 15:42:15'),
(567774026, 'Miếng dán màn hình iPhone X', 'Chống trầy xước tối ưu cho màn hình điện thoại iPhone X\nLàm giảm các dấu vân tay và vết ố\nChất liệu Nhật Bản và đóng gói tại Đài Loan', 3, 'https://firebasestorage.googleapis.com/v0/b/dazzling-torch-5860.appspot.com/o/product_images%2Frwey2ms9gm?alt=media&token=e9bfeebf-e668-4fc4-89c6-c39ea2fff35e', NULL, NULL, 'mycoolshop', '2018-01-08 17:28:41'),
(665589510, 'OMEN Wired Gaming Mouse 600', 'Brand new and authentic HP products ship from and are sold by Amazon.com (check above)\nDPI levels from 800–12,000: Fine-tune your accuracy with a full range of DPI levels and an optical sensor designed for eSports\nEasily adjustable settings: Buttons on the mouse let you adjust settings without interrupting your gameplay or opening additional software\nRemovable weights: Customize the center of gravity using three removable weights\nMechanical switches: Optimized so you get fast response times while withstanding the pressure of up to 50 million clicks', 41, 'https://firebasestorage.googleapis.com/v0/b/dazzling-torch-5860.appspot.com/o/product_images%2Fdf2xpv17yd6?alt=media&token=f4cbf00f-64ab-4ca1-b26c-dbafeb083f23', NULL, NULL, 'mycoolshop', '2018-01-03 22:38:50'),
(776001255, 'Tai nghe Sony MDRZX100 ZX', 'Connectivity Technology: Wired\n30mm Multi-layer film diaphragms create a powerful sound\nHigh energy drivers deliver powerful bass & clear treble\nLightweight & adjust. ABS housing for rugged durability\nPressure-relieving earpads for long-wear comfort\nA rugged Y-type design makes for a tangle-free experience', 15, 'https://firebasestorage.googleapis.com/v0/b/dazzling-torch-5860.appspot.com/o/product_images%2Fusyvt2q28sb?alt=media&token=351c01a0-aa2f-4e34-b374-262588bab95c', NULL, NULL, 'mycoolshop', '2018-01-03 21:53:05'),
(815783248, 'Sony Wireless Viridian Blue MDR100', 'Hear music at its best with Hi-Res Audio compatibility, Connect and stream music easily with Bluetooth, Focus on the music with Digital Noise Cancelling. Connectivity Technology: Wireless\nListen smarter with Noise Cancelling that adapts to your surroundings, Let the beats flow with Beat Response Control\nEnjoy high-quality, hands-free calls, Long-listen comfort\nCompatible devices - iPhone, iPad, or iPod touch (iOS 5.0 or later) supporting Hands-free Profile (HFP) iOS 7.X / 8.X / 9.X ; Smartphones - NFC-compatible smartphones installed with Android 4.1 or later. Refer the user manual under technical specification', 395, 'https://firebasestorage.googleapis.com/v0/b/dazzling-torch-5860.appspot.com/o/product_images%2Fllkfawhdkti?alt=media&token=69fe6823-c9a1-4eb6-a689-a5e09c978a31', NULL, NULL, 'mycoolshop', '2018-01-03 22:09:05'),
(963615725, 'Razer BlackWidow X', 'The Razer BlackWidow X gives you the same stellar performance as the Razer BlackWidow, deconstructed into an impressive piece of true craftsmanship. The exposed military grade metal construction is sure to turn heads while giving you durability that will withstand the test of time.', 299, 'https://firebasestorage.googleapis.com/v0/b/dazzling-torch-5860.appspot.com/o/product_images%2Fx963885n3dt?alt=media&token=e590138a-32fa-4721-89b4-b325d90cd2e4', '2017-12-08 14:16:13', '2018-12-08 14:16:13', 'mycoolshop', '2017-12-08 14:16:13');

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
(1, 963615725, 'Banphim', 'B2'),
(2, 963615725, 'Banphim', 'GB'),
(3, 1, 'Chuot', 'C1'),
(4, 2, 'Chuot', 'C2'),
(5, 2, 'Chuot', 'C3'),
(6, 57619580, 'Banphim', 'B2'),
(7, 29962118, 'Tainghe', 'T1'),
(8, 776001255, 'Tainghe', 'T1'),
(9, 926270760, 'Tainghe', 'T2'),
(10, 484890717, 'Chuot', 'C1'),
(11, 73988542, 'Chuot', 'C2'),
(12, 73988542, 'Chuot', 'GM'),
(13, 258509296, 'Tainghe', 'T1'),
(14, 35393972, 'Tainghe', 'T3'),
(15, 567774026, 'Danmanhinh', 'MD1'),
(16, 295626935, 'Banphim', 'B2'),
(17, 333409718, 'Banphim', 'B1'),
(18, 156410961, 'Banphim', 'B2'),
(19, 156410961, 'Chuot', 'C1'),
(20, 665589510, 'Chuot', 'GM'),
(21, 665589510, 'Chuot', 'C2'),
(22, 815783248, 'Tainghe', 'T2');

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
(12, 'B2', 'Bàn phím Bluetooth', NULL),
(15, 'MD1', 'Miếng dán màn hình điện thoại', NULL),
(16, 'MD2', 'Miếng dán màn hình Laptop', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `timebase`
--

CREATE TABLE `timebase` (
  `_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `show_1` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `show_2` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `days` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `time_1` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `time_2` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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
(1, 'xomix1802@gmail.com', 'admin', 'admin', 'Admin', NULL, NULL),
(4, 'nhan@nhan.com', 'Quách Trọng Nhân', 'user', 'user', '0938217232', 'Someplace in the Earth.'),
(5, 'tuanuy@cinderella.com', 'Trương Tuấn Uy', 'user', 'user', '0934xxxxxx', 'Switzerland'),
(6, 'tuanuy@admin.com', 'admin', 'admin', 'Admin', NULL, NULL);

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
-- Indexes for table `timebase`
--
ALTER TABLE `timebase`
  ADD PRIMARY KEY (`_id`);

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
  MODIFY `_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT for table `order_products`
--
ALTER TABLE `order_products`
  MODIFY `_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=963615726;

--
-- AUTO_INCREMENT for table `product_extra`
--
ALTER TABLE `product_extra`
  MODIFY `_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `subcategory`
--
ALTER TABLE `subcategory`
  MODIFY `_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
