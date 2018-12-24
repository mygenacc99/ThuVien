create database ThuVien;
use ThuVien;
create table DocGia(
	MaDocGia varchar(50) primary key,
	HoTen varchar(256) not null, 
	NgaySinh date not null, 
	NgayDangKy date not null, 
	NgayGiaHan date not null, 
	GhiChu varchar(256)
);
create table TheLoai(
	MaTheloai varchar(50) primary key, 
	Ten varchar(256) not null, 
	GhiChu varchar(256) 
);
create table TacGia(
	MaTacGia varchar(50) primary key, 
	HoTen varchar(256) not null, 
	NgaySinh date not null, 
	QuocTich varchar(50) not null, 
	GhiChu varchar(256)
);
create table Sach(
	MaSach varchar(50) primary key, 
	Ten varchar(256) not null, 
	SoLuong int not null, 
	MaTacGia varchar(50) not null, 
	MaTheLoai varchar(50) not null,
	constraint Sach_FK_TheLoai foreign key (MaTheLoai) references TheLoai(MaTheLoai),
	constraint Sach_FK_TacGia foreign key (MaTacGia) references TacGia(MaTacGia)
);
create table DocGiaMuonSach(
	MaDocGia varchar(50), 
	MaSach varchar(50) , 
	NgayMuon date not null, 
	GhiChu varchar(256),
	constraint DGMS_FK_DocGia foreign key (MaDocGia) references DocGia(MaDocGia),
	constraint DGMS_FK_Sach foreign key (MaSach) references Sach(MaSach)
);
create table LichSuMuonSach(
	MaLSMS varchar(50) primary key, 
	MaDocGia varchar(50) not null, 
	MaSach varchar(50) not null, 
	NgayMuon date not null, 
	NgayTra date not null,
	GhiChu varchar(256),
	constraint LSMS_FK_DocGia foreign key (MaDocGia) references DocGia(MaDocGia),
	constraint LSMS_FK_Sach foreign key (MaSach) references Sach(MaSach)
);
