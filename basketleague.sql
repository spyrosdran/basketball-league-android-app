-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 26, 2022 at 09:04 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `basketleague`
--

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `content` text NOT NULL,
  `timestamp` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `league`
--

CREATE TABLE `league` (
  `ID` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `photoURI` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `league`
--

INSERT INTO `league` (`ID`, `name`, `photoURI`) VALUES
(1, 'Greek HEBA A1', '');

-- --------------------------------------------------------

--
-- Table structure for table `match`
--

CREATE TABLE `match` (
  `matchID` int(11) NOT NULL,
  `homeID` varchar(30) NOT NULL,
  `awayID` varchar(30) NOT NULL,
  `leagueID` int(11) NOT NULL,
  `startTime` datetime NOT NULL,
  `homePts` int(11) NOT NULL,
  `awayPts` int(11) NOT NULL,
  `status` varchar(15) NOT NULL,
  `home1` int(11) NOT NULL,
  `home2` int(11) NOT NULL,
  `home3` int(11) NOT NULL,
  `home4` int(11) NOT NULL,
  `home5` int(11) NOT NULL,
  `away1` int(11) NOT NULL,
  `away2` int(11) NOT NULL,
  `away3` int(11) NOT NULL,
  `away4` int(11) NOT NULL,
  `away5` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `match`
--

INSERT INTO `match` (`matchID`, `homeID`, `awayID`, `leagueID`, `startTime`, `homePts`, `awayPts`, `status`, `home1`, `home2`, `home3`, `home4`, `home5`, `away1`, `away2`, `away3`, `away4`, `away5`) VALUES
(1, 'Panathinaikos', 'Aris BC', 1, '2022-06-30 21:00:00', 60, 40, 'live', 300, 301, 302, 305, 306, 100, 101, 102, 103, 108),
(2, 'Olympiacos', 'PAOK BC', 1, '2022-05-10 21:00:00', 0, 0, 'upcoming', 203, 203, 202, 204, 205, 400, 401, 403, 404, 406),
(3, 'AEK', 'Apollon Patras', 1, '2022-05-24 21:00:00', 80, 68, 'ended', 500, 501, 502, 503, 504, 600, 601, 602, 603, 604);

-- --------------------------------------------------------

--
-- Table structure for table `match_event`
--

CREATE TABLE `match_event` (
  `playerID` int(11) NOT NULL,
  `matchID` int(11) NOT NULL,
  `type` varchar(50) NOT NULL,
  `minute` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `match_event`
--

INSERT INTO `match_event` (`playerID`, `matchID`, `type`, `minute`) VALUES
(300, 1, '2pt', 0),
(301, 1, '3pt', 0),
(302, 1, '2pt', 0),
(303, 1, '2pt', 0),
(304, 1, '1pt', 0),
(302, 1, '1pt', 0),
(304, 1, '2pt', 0),
(303, 1, '2pt', 0),
(301, 1, '3pt', 0),
(100, 1, '1pt', 0),
(101, 1, '1pt', 0),
(102, 1, '3pt', 0),
(103, 1, '2pt', 0),
(104, 1, '3pt', 0),
(102, 1, '3pt', 0),
(103, 1, '3pt', 0),
(100, 1, '1pt', 0),
(101, 1, '2pt', 0),
(300, 1, 'rebound', 0),
(301, 1, 'steal', 0),
(302, 1, 'turnover', 0),
(303, 1, 'assist', 0),
(304, 1, 'foul', 0),
(302, 1, 'self pass (mistake)', 0),
(304, 1, 'foul', 0),
(303, 1, 'cut', 0),
(301, 1, 'assist', 0),
(100, 1, 'turnover', 0),
(101, 1, 'steal', 0),
(102, 1, 'block', 0),
(103, 1, 'assist', 0),
(104, 1, 'rebound', 0),
(102, 1, 'cut', 0),
(103, 1, 'change out', 0),
(100, 1, 'change in', 0),
(101, 1, 'block', 0),
(300, 1, 'steal', 0),
(300, 1, '2pt', 0),
(301, 1, '3pt', 0),
(302, 1, '2pt', 0),
(303, 1, '2pt', 0),
(304, 1, '1pt', 0),
(302, 1, '1pt', 0),
(304, 1, '2pt', 0),
(303, 1, '2pt', 0),
(301, 1, '3pt', 0),
(100, 1, '1pt', 0),
(101, 1, '1pt', 0),
(102, 1, '3pt', 0),
(103, 1, '2pt', 0),
(104, 1, '3pt', 0),
(102, 1, '3pt', 0),
(103, 1, '3pt', 0),
(100, 1, '1pt', 0),
(101, 1, '2pt', 0),
(300, 1, 'rebound', 0),
(301, 1, 'steal', 0),
(302, 1, 'turnover', 0),
(303, 1, 'assist', 0),
(304, 1, 'foul', 0),
(302, 1, 'self pass (mistake)', 0),
(304, 1, 'foul', 0),
(303, 1, 'cut', 0),
(301, 1, 'assist', 0),
(100, 1, 'turnover', 0),
(101, 1, 'steal', 0),
(102, 1, 'block', 0),
(103, 1, 'assist', 0),
(104, 1, 'rebound', 0),
(102, 1, 'cut', 0),
(104, 1, 'rebound', 0),
(100, 1, 'foul', 0),
(101, 1, 'block', 0),
(300, 1, 'steal', 0),
(300, 1, '2pt', 0),
(301, 1, '3pt', 0),
(302, 1, '2pt', 0),
(303, 1, '2pt', 0),
(304, 1, '1pt', 0),
(302, 1, '1pt', 0),
(304, 1, '2pt', 0),
(303, 1, '2pt', 0),
(301, 1, '3pt', 0),
(100, 1, '1pt', 0),
(101, 1, '1pt', 0),
(102, 1, '3pt', 0),
(103, 1, '2pt', 0),
(104, 1, '3pt', 0),
(102, 1, '3pt', 0),
(103, 1, '3pt', 0),
(100, 1, '1pt', 0),
(101, 1, '2pt', 0),
(300, 1, 'rebound', 0),
(301, 1, 'steal', 0),
(302, 1, 'turnover', 0),
(303, 1, 'assist', 0),
(304, 1, 'foul', 0),
(302, 1, 'self pass (mistake)', 0),
(304, 1, 'foul', 0),
(303, 1, 'cut', 0),
(301, 1, 'assist', 0),
(100, 1, 'turnover', 0),
(101, 1, 'steal', 0),
(102, 1, 'block', 0),
(103, 1, 'assist', 0),
(104, 1, 'rebound', 0),
(102, 1, 'cut', 0),
(103, 1, 'change in', 0),
(100, 1, 'change out', 0),
(101, 1, 'block', 0),
(300, 1, 'steal', 0),
(300, 1, '2pt', 0),
(301, 1, '3pt', 0),
(302, 1, '2pt', 0),
(303, 1, '2pt', 0),
(304, 1, '1pt', 0),
(302, 1, '1pt', 0),
(304, 1, '2pt', 0),
(303, 1, '2pt', 0),
(301, 1, '3pt', 0),
(100, 1, '1pt', 0),
(101, 1, '1pt', 0),
(102, 1, '3pt', 0),
(103, 1, '2pt', 0),
(104, 1, '3pt', 0),
(102, 1, '3pt', 0),
(103, 1, '3pt', 0),
(100, 1, '1pt', 0),
(101, 1, '2pt', 0),
(300, 1, 'rebound', 0),
(301, 1, 'steal', 0),
(302, 1, 'turnover', 0),
(303, 1, 'assist', 0),
(304, 1, 'foul', 0),
(302, 1, 'self pass (mistake)', 0),
(304, 1, 'foul', 0),
(303, 1, 'cut', 0),
(301, 1, 'assist', 0),
(100, 1, 'turnover', 0),
(101, 1, 'steal', 0),
(102, 1, 'block', 0),
(103, 1, 'assist', 0),
(104, 1, 'rebound', 0),
(102, 1, 'cut', 0),
(102, 1, 'assist', 0),
(101, 1, 'three second violation (mistake)', 0),
(101, 1, 'block', 0),
(200, 2, 'steal', 0),
(200, 2, '2pt', 0),
(201, 2, '3pt', 0),
(202, 2, '2pt', 0),
(203, 2, '2pt', 0),
(204, 2, '1pt', 0),
(202, 2, '1pt', 0),
(204, 2, '2pt', 0),
(203, 2, '2pt', 0),
(201, 2, '3pt', 0),
(200, 2, '1pt', 0),
(201, 2, '1pt', 0),
(202, 2, '3pt', 0),
(203, 2, '2pt', 0),
(204, 2, '3pt', 0),
(202, 2, '3pt', 0),
(203, 2, '3pt', 0),
(200, 2, '1pt', 0),
(201, 2, '2pt', 0),
(200, 2, 'rebound', 0),
(201, 2, 'steal', 0),
(202, 2, 'turnover', 0),
(203, 2, 'assist', 0),
(204, 2, 'foul', 0),
(202, 2, 'self pass (mistake)', 0),
(204, 2, 'foul', 0),
(203, 2, 'cut', 0),
(201, 2, 'assist', 0),
(400, 2, 'turnover', 0),
(401, 2, 'steal', 0),
(402, 2, 'block', 0),
(403, 2, 'assist', 0),
(404, 2, 'rebound', 0),
(402, 2, 'cut', 0),
(403, 2, 'change out', 0),
(400, 2, 'change in', 0),
(401, 2, 'block', 0),
(400, 2, 'steal', 0),
(400, 2, '2pt', 0),
(401, 2, '3pt', 0),
(402, 2, '2pt', 0),
(403, 2, '2pt', 0),
(404, 2, '1pt', 0),
(402, 2, '1pt', 0),
(404, 2, '2pt', 0),
(403, 2, '2pt', 0),
(401, 2, '3pt', 0),
(400, 2, '1pt', 0),
(401, 2, '1pt', 0),
(402, 2, '3pt', 0),
(403, 2, '2pt', 0),
(404, 2, '3pt', 0),
(402, 2, '3pt', 0),
(403, 2, '3pt', 0),
(400, 2, '1pt', 0),
(401, 2, '2pt', 0),
(400, 2, 'rebound', 0),
(401, 2, 'steal', 0),
(402, 2, 'turnover', 0),
(403, 2, 'assist', 0),
(404, 2, 'foul', 0),
(402, 2, 'self pass (mistake)', 0),
(404, 2, 'foul', 0),
(403, 2, 'cut', 0),
(401, 2, 'assist', 0),
(400, 2, 'turnover', 0),
(401, 2, 'steal', 0),
(402, 2, 'block', 0),
(403, 2, 'assist', 0),
(404, 2, 'rebound', 0),
(402, 2, 'cut', 0),
(404, 2, 'rebound', 0),
(400, 2, 'foul', 0),
(401, 2, 'block', 0),
(400, 2, 'steal', 0),
(400, 2, '2pt', 0),
(401, 2, '3pt', 0),
(402, 2, '2pt', 0),
(403, 2, '2pt', 0),
(404, 2, '1pt', 0),
(402, 2, '1pt', 0),
(404, 2, '2pt', 0),
(403, 2, '2pt', 0),
(401, 2, '3pt', 0),
(400, 2, '1pt', 0),
(401, 2, '1pt', 0),
(402, 2, '3pt', 0),
(403, 2, '2pt', 0),
(404, 2, '3pt', 0),
(402, 2, '3pt', 0),
(403, 2, '3pt', 0),
(400, 2, '1pt', 0),
(401, 2, '2pt', 0),
(400, 2, 'rebound', 0),
(401, 2, 'steal', 0),
(402, 2, 'turnover', 0),
(403, 2, 'assist', 0),
(404, 2, 'foul', 0),
(402, 2, 'self pass (mistake)', 0),
(404, 2, 'foul', 0),
(403, 2, 'cut', 0),
(401, 2, 'assist', 0),
(400, 2, 'turnover', 0),
(401, 2, 'steal', 0),
(402, 2, 'block', 0),
(403, 2, 'assist', 0),
(404, 2, 'rebound', 0),
(402, 2, 'cut', 0),
(403, 2, 'change in', 0),
(400, 2, 'change out', 0),
(401, 2, 'block', 0),
(400, 2, 'steal', 0),
(400, 2, '2pt', 0),
(401, 2, '3pt', 0),
(402, 2, '2pt', 0),
(403, 2, '2pt', 0),
(403, 2, '1pt', 0),
(404, 2, '1pt', 0),
(404, 2, '2pt', 0),
(403, 2, '2pt', 0),
(401, 2, '3pt', 0),
(400, 2, '1pt', 0),
(401, 2, '1pt', 0),
(402, 2, '3pt', 0),
(403, 2, '2pt', 0),
(404, 2, '3pt', 0),
(402, 2, '3pt', 0),
(403, 2, '3pt', 0),
(400, 2, '1pt', 0),
(401, 2, '2pt', 0),
(400, 2, 'rebound', 0),
(401, 2, 'steal', 0),
(402, 2, 'turnover', 0),
(403, 2, 'assist', 0),
(404, 2, 'foul', 0),
(402, 2, 'self pass (mistake)', 0),
(403, 2, 'foul', 0),
(403, 2, 'cut', 0),
(401, 2, 'assist', 0),
(400, 2, 'turnover', 0),
(401, 2, 'steal', 0),
(402, 2, 'block', 0),
(403, 2, 'assist', 0),
(404, 2, 'rebound', 0),
(402, 2, 'cut', 0),
(402, 2, 'assist', 0),
(401, 2, 'three second violation (mistake)', 0),
(401, 2, 'block', 0),
(400, 2, 'steal', 0),
(500, 3, 'steal', 0),
(500, 3, '2pt', 0),
(501, 3, '3pt', 0),
(502, 3, '2pt', 0),
(503, 3, '2pt', 0),
(504, 3, '1pt', 0),
(502, 3, '1pt', 0),
(504, 3, '2pt', 0),
(503, 3, '2pt', 0),
(501, 3, '3pt', 0),
(500, 3, '1pt', 0),
(501, 3, '1pt', 0),
(502, 3, '3pt', 0),
(503, 3, '2pt', 0),
(504, 3, '3pt', 0),
(502, 3, '3pt', 0),
(503, 3, '3pt', 0),
(500, 3, '1pt', 0),
(501, 3, '2pt', 0),
(500, 3, 'rebound', 0),
(501, 3, 'steal', 0),
(502, 3, 'turnover', 0),
(503, 3, 'assist', 0),
(504, 3, 'foul', 0),
(502, 3, 'self pass (mistake)', 0),
(504, 3, 'foul', 0),
(503, 3, 'cut', 0),
(501, 3, 'assist', 0),
(600, 3, 'turnover', 0),
(601, 3, 'steal', 0),
(602, 3, 'block', 0),
(603, 3, 'assist', 0),
(604, 3, 'rebound', 0),
(602, 3, 'cut', 0),
(603, 3, 'change out', 0),
(600, 3, 'change in', 0),
(601, 3, 'block', 0),
(600, 3, 'steal', 0),
(600, 3, '2pt', 0),
(601, 3, '3pt', 0),
(603, 3, '2pt', 0),
(603, 3, '2pt', 0),
(604, 3, '1pt', 0),
(602, 3, '1pt', 0),
(604, 3, '2pt', 0),
(603, 3, '2pt', 0),
(601, 3, '3pt', 0),
(600, 3, '1pt', 0),
(601, 3, '1pt', 0),
(602, 3, '3pt', 0),
(603, 3, '2pt', 0),
(604, 3, '3pt', 0),
(602, 3, '3pt', 0),
(603, 3, '3pt', 0),
(600, 3, '1pt', 0),
(601, 3, '2pt', 0),
(600, 3, 'rebound', 0),
(601, 3, 'steal', 0),
(602, 3, 'turnover', 0),
(603, 3, 'assist', 0),
(604, 3, 'foul', 0),
(602, 3, 'self pass (mistake)', 0),
(604, 3, 'foul', 0),
(603, 3, 'cut', 0),
(601, 3, 'assist', 0),
(600, 3, 'turnover', 0),
(601, 3, 'steal', 0),
(602, 3, 'block', 0),
(603, 3, 'assist', 0),
(604, 3, 'rebound', 0),
(602, 3, 'cut', 0),
(604, 3, 'rebound', 0),
(600, 3, 'foul', 0),
(601, 3, 'block', 0),
(600, 3, 'steal', 0),
(600, 3, '2pt', 0),
(601, 3, '3pt', 0),
(602, 3, '2pt', 0),
(603, 3, '2pt', 0),
(604, 3, '1pt', 0),
(602, 3, '1pt', 0),
(604, 3, '2pt', 0),
(603, 3, '2pt', 0),
(601, 3, '3pt', 0),
(600, 3, '1pt', 0),
(601, 3, '1pt', 0),
(602, 3, '3pt', 0),
(603, 3, '2pt', 0),
(604, 3, '3pt', 0),
(602, 3, '3pt', 0),
(603, 3, '3pt', 0),
(604, 3, '1pt', 0),
(601, 3, '2pt', 0),
(600, 3, 'rebound', 0),
(601, 3, 'steal', 0),
(602, 3, 'turnover', 0),
(603, 3, 'assist', 0),
(604, 3, 'foul', 0),
(602, 3, 'self pass (mistake)', 0),
(604, 3, 'foul', 0),
(603, 3, 'cut', 0),
(601, 3, 'assist', 0),
(600, 3, 'turnover', 0),
(601, 3, 'steal', 0),
(602, 3, 'block', 0),
(603, 3, 'assist', 0),
(604, 3, 'rebound', 0),
(602, 3, 'cut', 0),
(603, 3, 'change in', 0),
(604, 3, 'change out', 0),
(601, 3, 'block', 0),
(600, 3, 'steal', 0),
(600, 3, '2pt', 0),
(601, 3, '3pt', 0),
(602, 3, '2pt', 0),
(603, 3, '2pt', 0),
(603, 3, '1pt', 0),
(604, 3, '1pt', 0),
(604, 3, '2pt', 0),
(603, 3, '2pt', 0),
(601, 3, '3pt', 0),
(600, 3, '1pt', 0),
(601, 3, '1pt', 0),
(602, 3, '3pt', 0),
(603, 3, '2pt', 0),
(604, 3, '3pt', 0),
(602, 3, '3pt', 0),
(603, 3, '3pt', 0),
(600, 3, '1pt', 0),
(601, 3, '2pt', 0),
(600, 3, 'rebound', 0),
(601, 3, 'steal', 0),
(602, 3, 'turnover', 0),
(603, 3, 'assist', 0),
(604, 3, 'foul', 0),
(602, 3, 'self pass (mistake)', 0),
(603, 3, 'foul', 0),
(603, 3, 'cut', 0),
(601, 3, 'assist', 0),
(600, 3, 'turnover', 0),
(601, 3, 'steal', 0),
(602, 3, 'block', 0),
(603, 3, 'assist', 0),
(604, 3, 'rebound', 0),
(602, 3, 'cut', 0),
(602, 3, 'assist', 0),
(601, 3, 'three second violation (mistake)', 0),
(601, 3, 'block', 0),
(600, 3, 'steal', 0),
(600, 3, 'change out', 7),
(601, 3, 'change in', 7),
(200, 0, 'change out', 7),
(0, 0, 'change in', 7),
(0, 1, 'change out', 10),
(0, 1, 'change in', 10),
(0, 1, 'change out', 10),
(0, 1, 'change in', 10),
(100, 1, 'change out', 10),
(303, 1, 'change in', 10),
(202, 0, 'change out', 10),
(0, 0, 'change in', 10),
(210, 0, 'change out', 10),
(0, 0, 'change in', 10),
(201, 0, 'change out', 10),
(0, 0, 'change in', 10),
(100, 1, 'change out', 10),
(303, 1, 'change in', 10),
(100, 1, 'change out', 12),
(303, 1, 'change in', 12),
(200, 0, 'change out', 12),
(0, 0, 'change in', 12),
(201, 0, 'change out', 12),
(0, 0, 'change in', 12),
(202, 0, 'change out', 12),
(0, 0, 'change in', 12),
(201, 2, 'change out', 12),
(203, 2, 'change in', 12),
(100, 1, 'change out', 15),
(303, 1, 'change in', 15),
(100, 1, 'change out', 15),
(303, 1, 'change in', 15),
(100, 1, 'change out', 15),
(303, 1, 'change in', 15),
(100, 1, 'change out', 15),
(303, 1, 'change in', 15),
(100, 1, 'change out', 15),
(303, 1, 'change in', 15),
(303, 1, 'change in', 15),
(200, 2, 'change out', 15),
(203, 2, 'change in', 15),
(203, 2, 'change in', 15),
(200, 2, 'change out', 15),
(203, 2, 'change in', 15),
(203, 2, 'change in', 15),
(200, 2, 'change out', 15),
(203, 2, 'change in', 15),
(203, 2, 'change in', 15),
(200, 2, 'change out', 15),
(203, 2, 'change in', 15),
(203, 2, 'change in', 15),
(200, 2, 'change out', 15),
(203, 2, 'change in', 15),
(203, 2, 'change in', 15),
(200, 2, 'change out', 15),
(203, 2, 'change in', 15),
(200, 2, 'change out', 15),
(203, 2, 'change in', 15),
(200, 2, 'change out', 15),
(203, 2, 'change in', 15),
(201, 2, 'change out', 28),
(211, 2, 'change in', 28),
(211, 2, 'change out', 28),
(203, 2, 'change in', 28);

-- --------------------------------------------------------

--
-- Table structure for table `player`
--

CREATE TABLE `player` (
  `ID` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `position` varchar(2) NOT NULL,
  `teamID` varchar(30) DEFAULT NULL,
  `photoURI` text DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `player`
--

INSERT INTO `player` (`ID`, `name`, `position`, `teamID`, `photoURI`) VALUES
(100, 'Stylianos Chavales', 'GF', 'Aris BC', ','),
(101, 'Isaiah Cousins', 'G ', 'Aris BC', ','),
(102, 'Anthony Cowan', 'PG', 'Aris BC', ','),
(103, 'Michael Finke', 'PF', 'Aris BC', ','),
(104, 'Olivier Hanlan', 'G', 'Aris BC', ','),
(105, 'Shakur Juiston', 'PF', 'Aris BC', ','),
(106, 'Nikolaos Kamaras', 'GF', 'Aris BC', ','),
(107, 'James Kelly', 'F', 'Aris BC', ','),
(108, 'Jaka Klobucar', 'SG', 'Aris BC', ','),
(109, 'Anastasios Konstantinidis', 'F', 'Aris BC', ','),
(110, 'Thomas Kottas', 'C', 'Aris BC', ','),
(111, 'Eric Locket', 'SF', 'Aris BC', ','),
(112, 'Omer Netzip Oglu', 'SG', 'Aris BC', ','),
(113, 'Kyriakos Petanidis', 'F', 'Aris BC', ','),
(114, 'Stylianos Poulianitis', 'SG', 'Aris BC', ','),
(115, 'Stavros Schizas', 'GF', 'Aris BC', ','),
(116, 'Giannis Sidiroilias', 'SG', 'Aris BC', ','),
(117, 'Xeyrius Williams', 'PF', 'Aris BC', ','),
(200, 'Quincy Acy', 'F', 'Olympiacos', ','),
(201, 'Vasilis Christidis', 'PF', 'Olympiacos', ','),
(202, 'Tyler Dorsey', 'SG', 'Olympiacos', ','),
(203, 'Moustapha Fall', 'PF', 'Olympiacos', ','),
(204, 'Livio Jean-Charles', 'SF', 'Olympiacos', ','),
(205, 'Brandon Knight', 'G', 'Olympiacos', ','),
(206, 'Giannoulis Larentzakis', 'GF', 'Olympiacos', ','),
(207, 'Michail Lountzis', 'PG', 'Olympiacos', ','),
(208, 'Hassan Martin', 'F', 'Olympiacos', ','),
(209, 'Shaquille McKissic', 'F', 'Olympiacos', ','),
(210, 'Sotirios Oikonomopoulos', 'SG', 'Olympiacos', ','),
(211, 'Kostas Papanikolaou', 'PF', 'Olympiacos', ','),
(212, 'Georgios Printezis', 'F', 'Olympiacos', ','),
(213, 'Kostas Sloukas', 'PG', 'Olympiacos', ','),
(214, 'Sasha Vezenkov', 'PF', 'Olympiacos', ','),
(215, 'Thomas Walkup', 'G', 'Olympiacos', ','),
(300, 'Neoklis Avdalas', 'GF', 'Panathinaikos', ','),
(301, 'Lefteris Bochoridis', 'PG', 'Panathinaikos', ','),
(302, 'Nikos Chougkaz', 'F', 'Panathinaikos', ','),
(303, 'Emmanouil Dimou', 'PG', 'Panathinaikos', ','),
(304, 'Jeremy Evans', 'F', 'Panathinaikos', ','),
(305, 'Yogi Ferell', 'G', 'Panathinaikos', ','),
(306, 'Jehyve Floyd', 'SF', 'Panathinaikos', ','),
(307, 'Stefan Jovic', 'G', 'Panathinaikos', ','),
(308, 'Leonidas Kaselakis', 'C', 'Panathinaikos', ','),
(309, 'Vasisilis Kavvadas', 'PF', 'Panathinaikos', ','),
(310, 'Daryl Macon', 'PG', 'Panathinaikos', ','),
(311, 'Lefteris Mantzoukas', 'F', 'Panathinaikos', ','),
(312, 'Nemanja Nedovic', 'SG', 'Panathinaikos', ','),
(313, 'George Papagiannis', 'C', 'Panathinaikos', ','),
(314, 'Ioannis Papapetrou', 'SF', 'Panathinaikos', ','),
(315, 'Kendrick Perry', 'G', 'Panathinaikos', ','),
(316, 'Howard Sant-Roos', 'SF', 'Panathinaikos', ','),
(317, 'Antonios Sigalas', 'G', 'Panathinaikos', ','),
(318, 'Peyton Siva', 'PG', 'Panathinaikos', ','),
(319, 'Okaro White', 'F', 'Panathinaikos', ','),
(400, 'Josh Carter', 'SF', 'PAOK BC', ','),
(401, 'Andreas Christodoulou', 'F', 'PAOK BC', ','),
(402, 'David DiLeo', 'SF', 'PAOK BC', ','),
(403, 'Phil Green IV', 'PG', 'PAOK BC', ','),
(404, 'Malcolm Griffin', 'G', 'PAOK BC', ','),
(405, 'Vladimir Jankovic', 'SF', 'PAOK BC', ','),
(406, 'Marvin Jones', 'FC', 'PAOK BC', ','),
(407, 'Nikolaos Kamarianos', 'PG', 'PAOK BC', ','),
(408, 'George Kamperidis', 'SG', 'PAOK BC', ','),
(409, 'Michalis Kamperidis', 'SG', 'PAOK BC', ','),
(410, 'Anthony Lee', 'F', 'PAOK BC', ','),
(411, 'Jermaine Love-Roberts', 'G', 'PAOK BC', ','),
(412, 'Evangelos Mantzaris', 'SG', 'PAOK BC', ','),
(413, 'Derek Ogbeide', 'C', 'PAOK BC', ','),
(414, 'Nate Renfro', 'SF', 'PAOK BC', ','),
(415, 'Demetre Rivers', 'G', 'PAOK BC', ','),
(416, 'Apostolos Roumoglou', 'SG', 'PAOK BC', ','),
(417, 'Vasilis Tolipoulos', 'G', 'PAOK BC', ','),
(500, 'Braian Angola-Rodas', 'F', 'AEK', ','),
(501, 'Georgios Bogris', 'FC', 'AEK', ','),
(502, 'Quim Colom', 'PG', 'AEK', ','),
(503, 'Terrace Ferguson', 'SG', 'AEK', ','),
(504, 'Panagiotis Filippakos', 'C', 'AEK', ','),
(505, 'Dimitrios Flionis', 'SG', 'AEK', ','),
(506, 'Eric Griffin', 'F', 'AEK', ','),
(507, 'Manny Harris', 'G', 'AEK', ','),
(508, 'Ian Hummer', 'SF', 'AEK', ','),
(509, 'Stevan Jelovac', 'PF', 'AEK', ','),
(510, 'Michalis Karlis', 'PG', 'AEK', ','),
(511, 'Antonios Koniaris', 'PG', 'AEK', ','),
(512, 'Ioannis Kouzeloglou', 'F', 'AEK', ','),
(513, 'Keith Langford', 'G', 'AEK', ','),
(514, 'Dimitris Mavroeidis', 'C', 'AEK', ','),
(515, 'Nikolaos Pappas', 'SG', 'AEK', ','),
(516, 'Andreas Petropoulos', 'G', 'AEK', ','),
(517, 'Andy Rautins', 'G', 'AEK', ','),
(518, 'Rayvonte Rice', 'F', 'AEK', ','),
(519, 'Kostas Saxionis', 'G', 'AEK', ','),
(520, 'Juan Vaulet', 'F', 'AEK', ','),
(600, 'Sedrick Barefield', 'PG', 'Apollon Patras', ','),
(601, 'Periklis Bazinas', 'G', 'Apollon Patras', ','),
(602, 'Simeon Carter', 'PF', 'Apollon Patras', ','),
(603, 'Orlando Coleman', 'GF', 'Apollon Patras', ','),
(604, 'De\'Jon Davis', 'SF', 'Apollon Patras', ','),
(605, 'Georgios Diamantakos', 'C', 'Apollon Patras', ','),
(606, 'Nikolaos Diplaros', 'SG', 'Apollon Patras', ','),
(607, 'Dayon Griffin', 'G', 'Apollon Patras', ','),
(608, 'Gerogios Kogionis', 'C', 'Apollon Patras', ','),
(609, 'Jeremy McLaughlin', 'C', 'Apollon Patras', ','),
(610, 'Tre McLean', 'GF', 'Apollon Patras', ','),
(611, 'Elijah Mitrou-Long', 'PG', 'Apollon Patras', ','),
(612, 'Giannis Molfetas', 'GF', 'Apollon Patras', ','),
(613, 'Robert Montgomery Jr.', 'SF', 'Apollon Patras', ','),
(614, 'Fotis Papafotiou', 'G', 'Apollon Patras', ','),
(615, 'Gerel Simons', 'G', 'Apollon Patras', ','),
(616, 'Pancake Thomas', 'G', 'Apollon Patras', ','),
(617, 'Georgios Tsalmpouris', 'FC', 'Apollon Patras', ','),
(618, 'Nikos Tsiakmas', 'G', 'Apollon Patras', ','),
(619, 'Fotios Zioumpos', 'SG', 'Apollon Patras', ',');

-- --------------------------------------------------------

--
-- Table structure for table `statistics`
--

CREATE TABLE `statistics` (
  `playerID` int(11) NOT NULL,
  `matchID` int(11) NOT NULL,
  `points` int(11) NOT NULL,
  `rebounds` int(11) NOT NULL,
  `steals` int(11) NOT NULL,
  `turnovers` int(11) NOT NULL,
  `assists` int(11) NOT NULL,
  `blocks` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `team`
--

CREATE TABLE `team` (
  `name` varchar(50) NOT NULL,
  `city` varchar(30) NOT NULL,
  `badgeURI` text NOT NULL,
  `leagueID` int(11) NOT NULL,
  `photoURI` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `team`
--

INSERT INTO `team` (`name`, `city`, `badgeURI`, `leagueID`, `photoURI`) VALUES
('Aris BC', 'Thessaloniki', 'https://api.sofascore.app/api/v1/team/3505/image', 1, ''),
('Olympiacos', 'Athens', 'https://api.sofascore.app/api/v1/team/3501/image', 1, ''),
('Panathinaikos', 'Athens', 'https://api.sofascore.app/api/v1/team/3508/image', 1, ''),
('PAOK BC', 'Thessaloniki', 'https://api.sofascore.app/api/v1/team/3509/image', 1, ''),
('AEK', 'Athens', 'https://api.sofascore.app/api/v1/team/3502/image', 1, ''),
('Apollon Patras', 'Patra', 'https://api.sofascore.app/api/v1/team/5620/image', 1, '');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `ID` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `type` varchar(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`ID`, `username`, `password`, `type`) VALUES
(1, 'admin', '123456789', 'admin'),
(2, 'user1', '123456789', 'normal'),
(3, 'user2', '123456789', 'normal'),
(4, 'user3', '123456789', 'normal');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `league`
--
ALTER TABLE `league`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `match`
--
ALTER TABLE `match`
  ADD PRIMARY KEY (`matchID`);

--
-- Indexes for table `player`
--
ALTER TABLE `player`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `unique user` (`username`) USING BTREE;

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `league`
--
ALTER TABLE `league`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `match`
--
ALTER TABLE `match`
  MODIFY `matchID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `player`
--
ALTER TABLE `player`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=620;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
