CREATE DATABASE Stoom
GO


USE Stoom
CREATE TABLE [Address]
(
    Id INT IDENTITY(1,1) PRIMARY KEY,
    Street_Name VARCHAR(255) NOT NULL,
    Address_Number NUMERIC NOT NULL,
    Complement VARCHAR(255),
    Neighbourhood VARCHAR(255) NOT NULL,
    City VARCHAR(255) NOT NULL,
    [State] VARCHAR(255) NOT NULL,
    Country VARCHAR(255) NOT NULL,
    Zip_Code VARCHAR(255) NOT NULL,
    Latitude VARCHAR(255),
    Longitude VARCHAR(255)
)
GO