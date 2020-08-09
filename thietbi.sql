-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 09, 2020 at 12:31 PM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.2.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `thietbi`
--

-- --------------------------------------------------------

--
-- Table structure for table `loaisanpham`
--

CREATE TABLE `loaisanpham` (
  `id` int(11) NOT NULL,
  `tenSp` varchar(200) NOT NULL,
  `hinhanhSp` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `loaisanpham`
--

INSERT INTO `loaisanpham` (`id`, `tenSp`, `hinhanhSp`) VALUES
(1, 'Thực phẩm', 'http://ptdtnttinhquangninh.edu.vn/wp-content/uploads/2018/08/Photo-basket-icon-300x295.png'),
(2, 'Đồ gia dụng', 'https://sieuthibep123.com/wp-content/uploads/2019/07/do-gia-dung-sieuthibep123.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

CREATE TABLE `sanpham` (
  `id` int(5) NOT NULL,
  `tensanpham` varchar(255) NOT NULL,
  `giasanpham` int(255) NOT NULL,
  `hinhanhsanpham` varchar(1000) NOT NULL,
  `motasanpham` varchar(10000) NOT NULL,
  `idloaisanpham` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sanpham`
--

INSERT INTO `sanpham` (`id`, `tensanpham`, `giasanpham`, `hinhanhsanpham`, `motasanpham`, `idloaisanpham`) VALUES
(2, 'Xương heo MN ', 4780000, 'https://lh3.googleusercontent.com/UF6oHa5SkgtcbmObp7G7up2jYv1bjCIGtzuMb-GUSmv3vG8vBPitMMkQloQlFcuDMsslZ9hHNZSaT3_z2g=w185', 'Meat Deli thương hiệu thịt sạch áp dụng Công Nghệ Oxy Fresh 9 đến từ Châu Âu mang tới những sản phẩm đảm bảo an toàn chất lượng tới tận tay người tiêu dùng. Khép kín mọi công đoạn giúp nâng cao dinh dưỡng trong bữa ăn của mỗi gia đình', 1),
(3, 'Rau muống', 12500, 'https://lh3.googleusercontent.com/XP97TyiXi6PvsgQIu_-IxNbQn8h7VhOYr8vtDlVFZXTRkPSgbEhgbv9K_EuicD1xA_fWdIYt3YafqEwVhC9L=w185', 'VinEco Rau muống L1 (450g-500g) là một trong những loại thực phẩm quen thuộc hàng ngày, có giá rất rẻ, dễ ăn và hầu như ai cũng biết đến. Loại rau này chứa nhiều kali, canxi, đồng, và các khoáng chất khác như sắt, magie, phospho, natri. Ngoài ra các loại Vitamin như C, E, K, B1, niacin, B5, B6, folate và biotin.', 1),
(4, 'Chuối sứ', 8950, 'https://lh3.googleusercontent.com/U0HldFvyhcYYkM-vZPbIhkSavHzggz0AU-6eJLCOP-Mzws1YNMYWUNZxXf1w5rt0QuWme5JXVyg89VLNPnY=w185', 'Chuối sứ được trồng phổ biến ở các miền quê Việt Nam, đây là loại trái cây an toàn vì dễ trồng nên không cần sử dụng thuốc trừ sâu, hay chất tăng trưởng. Chuối sứ thu hoạch từ vườn nhà, chuối chín tự nhiên, vỏ chuối chuyển từ xanh sang vàng, đôi khi vỏ có những dấu lá nên da không mịn màng nhưng vị ngọt thanh tự nhiên và dẻo. Chuối có nhiều tác dụng tốt cho cơ thể, điều trị chứng thiếu máu, hỗ trợ trí nhớ, não bộ, bổ sung dưỡng chất, giúp da dưỡng ẩm, ngăn ngừa, trị mụn.', 1),
(5, 'Dưa chuột 500g', 10000, 'https://lh3.googleusercontent.com/MY4tiX7Dwram_cFD6RPgKARdHGEjERNXTWhcjLyC9hd_Xa96DXQqtzB7-0t9EtLdkOpu0H1FNMHwuD8YS0I=w185', 'VinEco Dưa chuột (dưa leo) DL L1 (400g-600g) là thực phẩm dân dã, từ lâu đã được biết tới với nhiều công dụng tốt cho sức khỏe. Dưa chuột không chứa calo nên là bữa ăn nhẹ rất tốt trong mùa hè. Không chỉ có lợi cho làn da mà nó còn làm tăng hàm lượng nước và chất xơ trong cơ thể. Hơn thế nữa, lý do dưa chuột được coi là loại thực phẩm mùa hè tuyệt vời vì nó có tác dụng làm giảm nhiệt và kháng viêm. ', 1),
(6, 'Ấm siêu tốc', 124000, 'https://image.yes24.vn/Upload/ProductImage/NAGAKAWA2019/2050564_M.jpg', 'Ấm siêu tốc Nagakawa NAG0308 có khả năng đun sôi 1.8 lít nước nên rất phù hợp với các gia đình cần sử dụng nước nóng thường xuyên để pha trà, nấu mì, pha sữa… Sản phẩm còn có thiết kế nhỏ gọn, hài hòa với không gian bếp nên được nhiều chị em nội trợ lựa chọn. Thân bình bằng inox an toàn cho sức khỏe, chống bám bẩn, dễ dàng vệ sinh, cho phép bạn đun sôi nước chỉ trong vài phút.', 2),
(7, 'Bếp Từ Đơn Nagakawa', 588000, 'https://image.yes24.vn/Upload/ProductImage/NAGAKAWA2019/2050957_M.jpg', 'Bếp từ Nagakawa NAG0706 là một trong những loại bếp hiệu quả nhất hiện nay, có thiết kế nhỏ gọn, thuận tiện cho bạn di chuyển nhiều nơi. Vỏ bếp từ thiết kế bằng nhựa cao cấp nên có độ bền cao, cách điện cách nhiệt tốt khi sử dụng.', 2),
(8, 'Lẩu điện Nagakawa ', 451000, 'https://image.yes24.vn/Upload/ProductImage/NAGAKAWA2019/2050982_M.jpg', 'Lẩu điện Nagakawa NAG1902 có thiết kế trang nhã, đơn giản, dung tích lên đến 3.5 lít chứa được nhiều thức ăn, điều chỉnh nhiệt độ bằng núm xoay thông minh. Lòng nồi được làm bằng nhôm cao cấp phủ nhiều lớp chống dính sẽ giúp bạn chế biến được nhiều món ăn như nấu lẩu hay chiên xào rau, thực phẩm.', 2),
(9, 'Cà chua', 12500, 'https://lh3.googleusercontent.com/e-UD-aWMMOwqiOABoBjdNExbaVF55IiYNiKax3z3FhLGmv6JD7qXuvjOk6BCY0nBeT1kDS9TZOAKH2-KHe5q=w185', 'VinEco Dưa chuột (dưa leo) DL L1 (400g-600g) là thực phẩm dân dã, từ lâu đã được biết tới với nhiều công dụng tốt cho sức khỏe. Dưa chuột không chứa calo nên là bữa ăn nhẹ rất tốt trong mùa hè. Không chỉ có lợi cho làn da mà nó còn làm tăng hàm lượng nước và chất xơ trong cơ thể. ', 1),
(10, 'Máy hút bụi', 1960000, 'https://cdn.tgdd.vn/Category/1990/May-hut-bui-l-20-11-2019.png', 'Máy hút bụi có đầu hút thảm, đầu hút mini kèm thêm ống hút nối dài bằng nhựa giúp người dùng dễ dàng vệ sinh mọi ngóc ngách\r\nTừ những vị trí cao hay các góc sâu khuất, kệ tủ, màn treo, hay cả bàn phím máy tính... Máy hút bụi giúp làm sạch toàn diện ngôi nhà bạn.', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `loaisanpham`
--
ALTER TABLE `loaisanpham`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `loaisanpham`
--
ALTER TABLE `loaisanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
