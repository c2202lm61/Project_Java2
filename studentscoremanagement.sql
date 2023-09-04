-- phpMyAdmin SQL Dump
-- version 5.2.1deb1ubuntu0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Sep 03, 2023 at 02:41 PM
-- Server version: 8.0.34-0ubuntu0.23.04.1
-- PHP Version: 8.1.12-1ubuntu4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `studentscoremanagement`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`phamquan`@`localhost` PROCEDURE `deleteFromClass` (IN `classID` INT)   BEGIN
    DELETE score_student FROM `class` AS c INNER JOIN student ON c.class_code  = student.Class_code INNER JOIN subject_student AS sstudent ON student.Student_id = sstudent.student_id INNER JOIN score_student ON sstudent.Subject_student_id =  score_student.ss_id WHERE c.class_code = classID;
    DELETE sstudent FROM `class` AS c INNER JOIN student ON c.class_code  = student.Class_code INNER JOIN subject_student AS sstudent ON student.Student_id = sstudent.student_id WHERE c.class_code = classID;
    DELETE FROM student WHERE Class_code = classID;
    DELETE FROM `teach_class` WHERE Class_code = classID;
END$$

CREATE DEFINER=`phamquan`@`localhost` PROCEDURE `deleteFromGrant` (IN `gid` INT)   BEGIN
    DELETE sc FROM grants AS g INNER JOIN class AS c ON g.id = c.grant_id INNER JOIN student AS  s ON c.class_code = s.Class_code INNER JOIN subject_student AS ss ON  s.Student_id = ss.student_id INNER JOIN score_student AS sc ON  ss.Subject_student_id = sc.ss_id WHERE g.id = gid;
DELETE ss FROM grants AS g INNER JOIN class AS c ON g.id = c.grant_id INNER JOIN student AS  s ON c.class_code = s.Class_code INNER JOIN subject_student AS ss ON  s.Student_id = ss.student_id WHERE g.id = gid;
DELETE student FROM grants AS g INNER JOIN class AS c ON g.id = c.grant_id INNER JOIN student ON c.class_code =  student.Class_code WHERE g.id = gid;
DELETE tc FROM grants AS g INNER JOIN class AS c ON g.id = c.grant_id INNER JOIN teach_class AS tc ON c.class_code = tc.Class_code WHERE g.id =  gid;
DELETE ij FROM grants AS g INNER JOIN class AS c ON g.id = c.grant_id INNER JOIN instructor AS i ON c.ID_manager =  i.ID_NUMBER INNER JOIN instructor_subject AS ij ON i.ID_NUMBER = ij.ID_NUMBER WHERE g.id = gid;
DELETE s FROM grants AS g INNER JOIN subject AS s ON  g.id = s.grant_id WHERE g.id = gid;
DELETE c FROM grants AS g INNER JOIN class AS c ON g.id = c.grant_id WHERE g.id = gid;
END$$

CREATE DEFINER=`phamquan`@`localhost` PROCEDURE `deleteFromInstructor` (IN `id` INT)   BEGIN
	DELETE FROM teach_class WHERE ID_Teach IN (SELECT ID_Teach FROM instructor_subject WHERE ID_NUMBER = ID);
	DELETE FROM instructor_subject WHERE ID_NUMBER = ID;
    DELETE FROM instructor_role WHERE ID_NUMBER = ID;
    DELETE FROM instructor WHERE ID_NUMBER = ID;
END$$

CREATE DEFINER=`phamquan`@`localhost` PROCEDURE `deleteFromSubject` (IN `subjcode` INT)   BEGIN
    DELETE score_student FROM `subject` INNER JOIN subject_student ON subject.Subject_code = subject_student.Subject_code INNER JOIN score_student ON subject_student.Subject_student_id = score_student.ss_id WHERE subject.Subject_code = subjcode;
    DELETE  FROM subject_student WHERE Subject_code = subjcode;
    DELETE FROM `instructor_subject` WHERE Subject_code = subjcode;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `class`
--

CREATE TABLE `class` (
  `class_code` int NOT NULL,
  `grant_id` int DEFAULT NULL,
  `ID_manager` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `class`
--

INSERT INTO `class` (`class_code`, `grant_id`, `ID_manager`) VALUES
(1, 1, 38);

-- --------------------------------------------------------

--
-- Table structure for table `grants`
--

CREATE TABLE `grants` (
  `id` int NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `grants`
--

INSERT INTO `grants` (`id`, `name`) VALUES
(1, 'Khối 1'),
(2, 'Khối 2'),
(3, 'Khối 3'),
(4, 'Khối 4'),
(5, 'Khối 5');

-- --------------------------------------------------------

--
-- Table structure for table `instructor`
--

CREATE TABLE `instructor` (
  `ID_NUMBER` int NOT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `birthday` date DEFAULT NULL,
  `Gender` tinyint(1) DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Phone` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `instructor`
--

INSERT INTO `instructor` (`ID_NUMBER`, `name`, `birthday`, `Gender`, `password`, `Email`, `Phone`) VALUES
(38, 'Pham  Quan', '1999-01-01', 1, 'wwWW11@@', 'quanqqq111@gmail.com', '01234567'),
(53, 'nva', '2002-11-11', 1, 'wwWW@@11', 'qqq@gmail.com', '01234567');

-- --------------------------------------------------------

--
-- Table structure for table `instructor_role`
--

CREATE TABLE `instructor_role` (
  `id` int NOT NULL,
  `Role_id` int DEFAULT NULL,
  `ID_NUMBER` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `instructor_role`
--

INSERT INTO `instructor_role` (`id`, `Role_id`, `ID_NUMBER`) VALUES
(15, 11, 38),
(19, 4, 38),
(26, 6, 53);

-- --------------------------------------------------------

--
-- Table structure for table `instructor_subject`
--

CREATE TABLE `instructor_subject` (
  `ID_Teach` int NOT NULL,
  `ID_NUMBER` int DEFAULT NULL,
  `Subject_code` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `instructor_subject`
--

INSERT INTO `instructor_subject` (`ID_Teach`, `ID_NUMBER`, `Subject_code`) VALUES
(1, 38, 11);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` int NOT NULL,
  `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `student_mngs` tinyint(1) DEFAULT '0',
  `teach_mngs` tinyint(1) DEFAULT '0',
  `block_mngs` tinyint(1) DEFAULT '0',
  `class_mngs` tinyint(1) DEFAULT '0',
  `subject_mngs` tinyint(1) DEFAULT '0',
  `asignment_mngs` tinyint(1) DEFAULT '0',
  `type_score_mngs` tinyint(1) DEFAULT '0',
  `role_mngs` tinyint(1) DEFAULT '0',
  `position_mngs` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `role_name`, `student_mngs`, `teach_mngs`, `block_mngs`, `class_mngs`, `subject_mngs`, `asignment_mngs`, `type_score_mngs`, `role_mngs`, `position_mngs`) VALUES
(4, 'admin', 1, 1, 1, 1, 1, 1, 1, 1, 1),
(6, 'Phó hiệu trưởng', 1, 0, 1, 1, 0, 1, 0, 1, 0),
(8, 'Hiệu  trưởng', 1, 1, 1, 1, 0, 0, 0, 1, 0),
(11, 'Giáo viên', 0, 0, 0, 0, 0, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `score_student`
--

CREATE TABLE `score_student` (
  `ts_id` int DEFAULT NULL,
  `ss_id` int DEFAULT NULL,
  `ScoreValue` double DEFAULT NULL,
  `ScoreID` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `Student_id` int NOT NULL,
  `Social_Securty_Number` char(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Current_address` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Phone` char(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Birthday` date DEFAULT NULL,
  `Gender` bit(1) DEFAULT NULL,
  `Class_code` int DEFAULT NULL,
  `Name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

CREATE TABLE `subject` (
  `Subject_code` int NOT NULL,
  `Name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Credits` int DEFAULT NULL,
  `grant_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `subject`
--

INSERT INTO `subject` (`Subject_code`, `Name`, `Credits`, `grant_id`) VALUES
(11, 'Toán', 5, 1),
(12, 'Ngữ Văn', 5, 1);

-- --------------------------------------------------------

--
-- Table structure for table `subject_student`
--

CREATE TABLE `subject_student` (
  `Subject_student_id` int NOT NULL,
  `student_id` int DEFAULT NULL,
  `Subject_code` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `teach_class`
--

CREATE TABLE `teach_class` (
  `id_tc` int NOT NULL,
  `numberofsemester` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Class_code` int DEFAULT NULL,
  `ID_Teach` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `teach_class`
--

INSERT INTO `teach_class` (`id_tc`, `numberofsemester`, `Class_code`, `ID_Teach`) VALUES
(1, '2', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `type_score`
--

CREATE TABLE `type_score` (
  `ts_id` int NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `type_score`
--

INSERT INTO `type_score` (`ts_id`, `name`) VALUES
(1, 'Miệng ,15 phút'),
(2, '45 phút'),
(3, 'Học kỳ 1'),
(4, 'Học kỳ 2');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `class`
--
ALTER TABLE `class`
  ADD PRIMARY KEY (`class_code`),
  ADD KEY `grant_id` (`grant_id`),
  ADD KEY `ID_manager` (`ID_manager`);

--
-- Indexes for table `grants`
--
ALTER TABLE `grants`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `instructor`
--
ALTER TABLE `instructor`
  ADD PRIMARY KEY (`ID_NUMBER`);

--
-- Indexes for table `instructor_role`
--
ALTER TABLE `instructor_role`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_roleKEY` (`ID_NUMBER`),
  ADD KEY `fk_fl` (`Role_id`);

--
-- Indexes for table `instructor_subject`
--
ALTER TABLE `instructor_subject`
  ADD PRIMARY KEY (`ID_Teach`),
  ADD KEY `Subject_code` (`Subject_code`),
  ADD KEY `ID_NUMBER` (`ID_NUMBER`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `score_student`
--
ALTER TABLE `score_student`
  ADD PRIMARY KEY (`ScoreID`),
  ADD KEY `ss_id` (`ss_id`),
  ADD KEY `tc_id` (`ts_id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`Student_id`),
  ADD KEY `Class_code` (`Class_code`);

--
-- Indexes for table `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`Subject_code`),
  ADD KEY `grant_id` (`grant_id`);

--
-- Indexes for table `subject_student`
--
ALTER TABLE `subject_student`
  ADD PRIMARY KEY (`Subject_student_id`),
  ADD KEY `Subject_code` (`Subject_code`),
  ADD KEY `student_id` (`student_id`);

--
-- Indexes for table `teach_class`
--
ALTER TABLE `teach_class`
  ADD PRIMARY KEY (`id_tc`),
  ADD KEY `ID_Teach` (`ID_Teach`),
  ADD KEY `Class_code` (`Class_code`);

--
-- Indexes for table `type_score`
--
ALTER TABLE `type_score`
  ADD PRIMARY KEY (`ts_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `class`
--
ALTER TABLE `class`
  MODIFY `class_code` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `grants`
--
ALTER TABLE `grants`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `instructor`
--
ALTER TABLE `instructor`
  MODIFY `ID_NUMBER` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- AUTO_INCREMENT for table `instructor_role`
--
ALTER TABLE `instructor_role`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `score_student`
--
ALTER TABLE `score_student`
  MODIFY `ScoreID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `Student_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- AUTO_INCREMENT for table `subject`
--
ALTER TABLE `subject`
  MODIFY `Subject_code` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `subject_student`
--
ALTER TABLE `subject_student`
  MODIFY `Subject_student_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `teach_class`
--
ALTER TABLE `teach_class`
  MODIFY `id_tc` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `type_score`
--
ALTER TABLE `type_score`
  MODIFY `ts_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `class`
--
ALTER TABLE `class`
  ADD CONSTRAINT `Class_ibfk_1` FOREIGN KEY (`grant_id`) REFERENCES `grants` (`id`),
  ADD CONSTRAINT `ID_manager` FOREIGN KEY (`ID_manager`) REFERENCES `instructor` (`ID_NUMBER`);

--
-- Constraints for table `instructor_role`
--
ALTER TABLE `instructor_role`
  ADD CONSTRAINT `fk_fl` FOREIGN KEY (`Role_id`) REFERENCES `role` (`id`),
  ADD CONSTRAINT `fk_roleKEY` FOREIGN KEY (`ID_NUMBER`) REFERENCES `instructor` (`ID_NUMBER`);

--
-- Constraints for table `instructor_subject`
--
ALTER TABLE `instructor_subject`
  ADD CONSTRAINT `Instructor_subject_ibfk_1` FOREIGN KEY (`Subject_code`) REFERENCES `subject` (`Subject_code`),
  ADD CONSTRAINT `Instructor_subject_ibfk_2` FOREIGN KEY (`ID_NUMBER`) REFERENCES `instructor` (`ID_NUMBER`);

--
-- Constraints for table `score_student`
--
ALTER TABLE `score_student`
  ADD CONSTRAINT `Score_student_ibfk_1` FOREIGN KEY (`ss_id`) REFERENCES `subject_student` (`Subject_student_id`),
  ADD CONSTRAINT `Score_student_ibfk_2` FOREIGN KEY (`ts_id`) REFERENCES `type_score` (`ts_id`);

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `student_ibfk_1` FOREIGN KEY (`Class_code`) REFERENCES `class` (`class_code`);

--
-- Constraints for table `subject`
--
ALTER TABLE `subject`
  ADD CONSTRAINT `Subject_ibfk_1` FOREIGN KEY (`grant_id`) REFERENCES `grants` (`id`);

--
-- Constraints for table `subject_student`
--
ALTER TABLE `subject_student`
  ADD CONSTRAINT `Subject_student_ibfk_1` FOREIGN KEY (`Subject_code`) REFERENCES `subject` (`Subject_code`),
  ADD CONSTRAINT `Subject_student_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`Student_id`);

--
-- Constraints for table `teach_class`
--
ALTER TABLE `teach_class`
  ADD CONSTRAINT `Teach_class_ibfk_1` FOREIGN KEY (`ID_Teach`) REFERENCES `instructor_subject` (`ID_Teach`),
  ADD CONSTRAINT `Teach_class_ibfk_2` FOREIGN KEY (`Class_code`) REFERENCES `class` (`class_code`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
