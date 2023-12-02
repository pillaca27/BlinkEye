CREATE DATABASE BD_BLINKEYE;
GO
--
-- ArplSpring.dbo.AplicacionesMast definition

-- Drop table

-- DROP TABLE ArplSpring.dbo.AplicacionesMast;

CREATE TABLE AplicacionesMast (
	AplicacionCodigo char(2) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	DescripcionCorta char(20) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	DescripcionLarga char(40) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	UltimoPeriodoContable char(6) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	SistemaFuente char(8) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	EstaDisponible char(10) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	DepartamentoRevisor char(3) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	UltimoPeriodoProcesado char(6) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	AplicacionUsuario char(2) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	AplicacionUsuario02 char(2) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	AplicacionUsuario03 char(2) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	AplicacionUsuario04 char(2) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	Estado char(1) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	UltimoUsuario char(10) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	UltimaFechaModif datetime NULL,
	CONSTRAINT PK__AplicacionesMast__160F4887 PRIMARY KEY (AplicacionCodigo)
);
--
CREATE TABLE ParametrosMast (
	CompaniaCodigo char(10) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	AplicacionCodigo char(2) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	ParametroClave char(10) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	DescripcionParametro varchar(1000) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	Explicacion varchar(2000) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	TipodeDatoFlag char(1) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	Texto char(20) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	Numero money NULL,
	Fecha datetime NULL,
	FinanceComunFlag char(1) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	Estado char(1) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	UltimoUsuario char(10) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	UltimaFechaModif datetime NULL,
	CONSTRAINT PK__ParametrosMast__7D439ABD PRIMARY KEY (CompaniaCodigo,AplicacionCodigo,ParametroClave)
);
--
ALTER TABLE ParametrosMast ADD CONSTRAINT FK__Parametro__Aplic__65370702 FOREIGN KEY (AplicacionCodigo) REFERENCES AplicacionesMast(AplicacionCodigo);
--
CREATE TABLE Usuario (
	Usuario char(20) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	UsuarioPerfil char(2) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	Nombre char(30) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	Clave char(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	ExpirarPasswordFlag char(1) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	FechaExpiracion datetime NULL,
	UltimoLogin datetime NULL,
	NumeroLoginsDisponible int NULL,
	NumeroLoginsUsados int NULL,
	UsuarioRed char(20) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	SQLLogin char(20) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	SQLPassword char(10) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	Estado char(1) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	UltimoUsuario char(10) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	UltimaFechaModif datetime NULL,
	clave2 varchar(200) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	CONSTRAINT PK__Usuario__6C190EBB PRIMARY KEY (Usuario)
);
--
INSERT INTO Usuario
(Usuario, UsuarioPerfil, Nombre, Clave, ExpirarPasswordFlag, FechaExpiracion, UltimoLogin, NumeroLoginsDisponible, NumeroLoginsUsados, UsuarioRed, SQLLogin, SQLPassword, Estado, UltimoUsuario, UltimaFechaModif, clave2)
VALUES(N'MISESF              ', N'US', N'ROYAL SYSTEMS                 ', N'246                                                                                                 ', NULL, NULL, '2010-05-26 18:56:45.127', NULL, 0, NULL, NULL, NULL, N'A', N'SYSTEM    ', '2018-01-02 09:58:00.537', N'246');
--

