-- phpMyAdmin SQL Dump
-- version 5.3.0-dev+20220616.7a6bd9eb57
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 18, 2022 at 10:03 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rentalserviceproject`
--

-- --------------------------------------------------------

--
-- Table structure for table `asset`
--

CREATE TABLE `asset` (
  `id` bigint(20) NOT NULL,
  `location` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price_per_day` double DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `asset`
--

INSERT INTO `asset` (`id`, `location`, `name`, `price_per_day`, `status`, `type`, `user_id`) VALUES
(6, 'johor', 'Coat-Pent sq', 1000, 'Published', 'clothes', 2),
(7, 'johor', 'Generator(gen)', 2000, 'Published', 'machine', 3),
(8, 'shah-faisal', 'Speakers', 500, 'Published', 'device', 6),
(9, 'shah-faisal', 'headset', 300, 'in_review', 'device', 2);

-- --------------------------------------------------------

--
-- Table structure for table `asset_booking`
--

CREATE TABLE `asset_booking` (
  `id` bigint(20) NOT NULL,
  `end_date` date DEFAULT NULL,
  `price` double DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `asset_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `asset_booking`
--

INSERT INTO `asset_booking` (`id`, `end_date`, `price`, `start_date`, `status`, `asset_id`, `user_id`) VALUES
(1, '2022-03-08', 300, '2022-08-08', 'in_review', 9, 4),
(2, '2022-08-20', 1000, '2022-08-08', 'Published', 6, 4),
(3, '2022-07-18', 500, '2022-07-12', 'Published', 7, 5);

-- --------------------------------------------------------

--
-- Table structure for table `asset_review`
--

CREATE TABLE `asset_review` (
  `id` bigint(20) NOT NULL,
  `rating` double DEFAULT NULL,
  `review` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `asset_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `request_of_service`
--

CREATE TABLE `request_of_service` (
  `id` bigint(20) NOT NULL,
  `details` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `request_of_service`
--

INSERT INTO `request_of_service` (`id`, `details`, `status`, `type`, `user_id`) VALUES
(1, 'I want to list a car honda civic', 'Published', 'automobile', 6),
(2, 'I want to list kitchen things', 'in_review', 'kitchen gadgets', 3),
(3, 'I have a cow cattle farm', 'in_review', 'cow cattle farm', 6);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `cnic` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `cnic`, `email`, `name`, `number`, `status`, `type`) VALUES
(2, '542222', 'Fahdkhan@gmail.com', 'Fahd', '323221', 'Published', 'seller'),
(3, '542222', 'salmankhan@gmail.com', 'Salmankhan', '323221', 'Published', 'seller'),
(4, '542222', 'umerkhan@gmail.com', 'umer', '323221', 'Published', 'customer'),
(5, '542222', 'zunairkhan@gmail.com', 'zunair', '323221', 'Published', 'customer'),
(6, '542233', 'umairkhan@gmail.com', 'umair', '323221', 'Published', 'seller'),
(8, '222233', 'shammerkhan@gmail.com', 'shameer', '2323', 'in_review', 'seller');

-- --------------------------------------------------------

--
-- Table structure for table `user_rating_and_review`
--

CREATE TABLE `user_rating_and_review` (
  `user_rating_and_review_id` bigint(20) NOT NULL,
  `rating` double DEFAULT NULL,
  `review` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `provider_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_rating_and_review`
--

INSERT INTO `user_rating_and_review` (`user_rating_and_review_id`, `rating`, `review`, `status`, `provider_id`, `user_id`) VALUES
(1, 4.2, 'The seller is very cooperative', 'in_review', 5, 2),
(2, 3, 'Communication skill of the seller is very bad', 'in_review', 6, 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `asset`
--
ALTER TABLE `asset`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKi2t8rfq8blfbh1rpvbxqrmgvd` (`user_id`);

--
-- Indexes for table `asset_booking`
--
ALTER TABLE `asset_booking`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK76ei856idenbgeaqggs0q615p` (`asset_id`),
  ADD KEY `FKt9s424ean1swopious1aolv7e` (`user_id`);

--
-- Indexes for table `asset_review`
--
ALTER TABLE `asset_review`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKhwyoao8dcr6ucdara2jib3omf` (`asset_id`),
  ADD KEY `FKdgoog6uus3muehl9l8iibgsh0` (`user_id`);

--
-- Indexes for table `request_of_service`
--
ALTER TABLE `request_of_service`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKby5y4f72w395qcbxqsot32qgw` (`user_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_rating_and_review`
--
ALTER TABLE `user_rating_and_review`
  ADD PRIMARY KEY (`user_rating_and_review_id`),
  ADD KEY `FKdurpyc14lefvylp4v380lke1f` (`provider_id`),
  ADD KEY `FKagbmcjdw5w2puw8li6roykjjg` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `asset`
--
ALTER TABLE `asset`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `asset_booking`
--
ALTER TABLE `asset_booking`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `asset_review`
--
ALTER TABLE `asset_review`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `request_of_service`
--
ALTER TABLE `request_of_service`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `user_rating_and_review`
--
ALTER TABLE `user_rating_and_review`
  MODIFY `user_rating_and_review_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `asset`
--
ALTER TABLE `asset`
  ADD CONSTRAINT `FKi2t8rfq8blfbh1rpvbxqrmgvd` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `asset_booking`
--
ALTER TABLE `asset_booking`
  ADD CONSTRAINT `FK76ei856idenbgeaqggs0q615p` FOREIGN KEY (`asset_id`) REFERENCES `asset` (`id`),
  ADD CONSTRAINT `FKt9s424ean1swopious1aolv7e` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `asset_review`
--
ALTER TABLE `asset_review`
  ADD CONSTRAINT `FKdgoog6uus3muehl9l8iibgsh0` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKhwyoao8dcr6ucdara2jib3omf` FOREIGN KEY (`asset_id`) REFERENCES `asset` (`id`);

--
-- Constraints for table `request_of_service`
--
ALTER TABLE `request_of_service`
  ADD CONSTRAINT `FKby5y4f72w395qcbxqsot32qgw` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `user_rating_and_review`
--
ALTER TABLE `user_rating_and_review`
  ADD CONSTRAINT `FKagbmcjdw5w2puw8li6roykjjg` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKdurpyc14lefvylp4v380lke1f` FOREIGN KEY (`provider_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;



