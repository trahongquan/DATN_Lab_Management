USE [master]
GO
/****** Object:  Database [classroom_management]    Script Date: 4/4/2024 1:21:07 PM ******/
CREATE DATABASE [classroom_management]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'classroom_management', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.TRHQUANMS\MSSQL\DATA\classroom_management.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'classroom_management_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.TRHQUANMS\MSSQL\DATA\classroom_management_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [classroom_management] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [classroom_management].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [classroom_management] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [classroom_management] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [classroom_management] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [classroom_management] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [classroom_management] SET ARITHABORT OFF 
GO
ALTER DATABASE [classroom_management] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [classroom_management] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [classroom_management] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [classroom_management] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [classroom_management] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [classroom_management] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [classroom_management] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [classroom_management] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [classroom_management] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [classroom_management] SET  ENABLE_BROKER 
GO
ALTER DATABASE [classroom_management] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [classroom_management] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [classroom_management] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [classroom_management] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [classroom_management] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [classroom_management] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [classroom_management] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [classroom_management] SET RECOVERY FULL 
GO
ALTER DATABASE [classroom_management] SET  MULTI_USER 
GO
ALTER DATABASE [classroom_management] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [classroom_management] SET DB_CHAINING OFF 
GO
ALTER DATABASE [classroom_management] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [classroom_management] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [classroom_management] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [classroom_management] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'classroom_management', N'ON'
GO
ALTER DATABASE [classroom_management] SET QUERY_STORE = ON
GO
ALTER DATABASE [classroom_management] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [classroom_management]
GO
/****** Object:  User [trhquantest]    Script Date: 4/4/2024 1:21:07 PM ******/
CREATE USER [trhquantest] FOR LOGIN [trhquantest] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  User [Trhquan]    Script Date: 4/4/2024 1:21:07 PM ******/
CREATE USER [Trhquan] FOR LOGIN [Trhquan] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  User [IIS APPPOOL\DefaultAppPool]    Script Date: 4/4/2024 1:21:07 PM ******/
CREATE USER [IIS APPPOOL\DefaultAppPool] FOR LOGIN [IIS APPPOOL\DefaultAppPool] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  Table [dbo].[accounts]    Script Date: 4/4/2024 1:21:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[accounts](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[username] [nvarchar](255) NOT NULL,
	[password] [nvarchar](255) NOT NULL,
	[fullname] [nvarchar](255) NOT NULL,
	[role_id] [int] NULL,
	[is_deleted] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[bookings]    Script Date: 4/4/2024 1:21:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[bookings](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[room_id] [int] NULL,
	[user_id] [int] NULL,
	[booking_date] [date] NULL,
	[booking_status] [bit] NULL,
	[confirmation_status] [tinyint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[equipments]    Script Date: 4/4/2024 1:21:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[equipments](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[equipment_number] [varchar](255) NOT NULL,
	[equipment_type] [nvarchar](255) NOT NULL,
	[origin] [nvarchar](255) NOT NULL,
	[production_year] [int] NOT NULL,
	[voltage] [int] NULL,
	[status] [bit] NOT NULL,
	[room_id] [int] NULL,
	[is_deleted] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[roles]    Script Date: 4/4/2024 1:21:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[roles](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[role_name] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[rooms]    Script Date: 4/4/2024 1:21:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[rooms](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[room_name] [nvarchar](100) NOT NULL,
	[capacity] [int] NOT NULL,
	[location] [nvarchar](100) NULL,
	[leader_id] [int] NULL,
	[is_deleted] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[accounts] ADD  DEFAULT ((3)) FOR [role_id]
GO
ALTER TABLE [dbo].[accounts] ADD  DEFAULT ((0)) FOR [is_deleted]
GO
ALTER TABLE [dbo].[equipments] ADD  DEFAULT ((220)) FOR [voltage]
GO
ALTER TABLE [dbo].[equipments] ADD  DEFAULT ((1)) FOR [status]
GO
ALTER TABLE [dbo].[equipments] ADD  DEFAULT ((0)) FOR [is_deleted]
GO
ALTER TABLE [dbo].[rooms] ADD  DEFAULT ((0)) FOR [is_deleted]
GO
ALTER TABLE [dbo].[accounts]  WITH CHECK ADD  CONSTRAINT [FK_account_role] FOREIGN KEY([role_id])
REFERENCES [dbo].[roles] ([id])
GO
ALTER TABLE [dbo].[accounts] CHECK CONSTRAINT [FK_account_role]
GO
ALTER TABLE [dbo].[bookings]  WITH CHECK ADD FOREIGN KEY([room_id])
REFERENCES [dbo].[rooms] ([id])
GO
ALTER TABLE [dbo].[bookings]  WITH CHECK ADD FOREIGN KEY([user_id])
REFERENCES [dbo].[accounts] ([id])
GO
ALTER TABLE [dbo].[equipments]  WITH CHECK ADD  CONSTRAINT [FK_equip_room] FOREIGN KEY([room_id])
REFERENCES [dbo].[rooms] ([id])
GO
ALTER TABLE [dbo].[equipments] CHECK CONSTRAINT [FK_equip_room]
GO
ALTER TABLE [dbo].[rooms]  WITH CHECK ADD  CONSTRAINT [FK_room_leader] FOREIGN KEY([leader_id])
REFERENCES [dbo].[accounts] ([id])
GO
ALTER TABLE [dbo].[rooms] CHECK CONSTRAINT [FK_room_leader]
GO
USE [master]
GO
ALTER DATABASE [classroom_management] SET  READ_WRITE 
GO
