# HT_Cham_Ma_Nguon_Java

Công cụ hỗ trợ chấm tự động mã nguồn Java

1. Thành viên
- B16DCCN370 – Hoàng Mậu Trung – hoangmautrung@gmail.com - 0334535251 
- B16DCCN122 – Hoàng Đức Hải – hoangduchai9x@gmail.com - 0354511410
- B16DCCN122 – Lưu Quang Tân – quantan5998@gmail.com - 0354511410
2. Chức năng (chính/quan trọng/được yêu cầu)
- Module gửi/nhận mã nguồn theo mô hình client/server TCP.
- Module chấm tự động mã nguồn Java theo input/output. 
- Module phân tích cấu trúc mã nguồn Java (package, class, variable, method, property, ...) và xuất ra cây cấu trúc xml hoặc json.
- Module hỗ trợ admin thêm input/ouput.
- Module xem lịch sử phiên chấm mã nguồn của client và cập nhật vào cơ sở dữ liệu.
3. Môi trường phát triển (công cụ/kỹ thuật/công nghệ/thư viện/fw)
- [Công cụ]: 
+ IDE: NetBeans
+ Database: SQL Server 2016 Evalution.
- [Kỹ thuật]: 
+ Phân tích cây cấu trúc file Java: Reflection.
+ Chấm mã nguồn input/ouput: Java.lang.Runtime.
+ Mô hình client/server TCP.
+ Luồng vào ra cơ bản trong Java: ObjectInput/OutputStream, DataInput/OuputStream.
4. Hạn chế/Hướng phát triển
- Hạn chế:
	+ Chưa check được độ phức tạp thuật toán và limit thời gian chạy 1 file.
	+ Thời gian submit và nhận yêu cầu chưa thực sự nhanh.
	+ Vẫn còn 1 số Exception.
- Hướng phát triển/ý tưởng
	+ Kiểm tra thời gian chạy.
	+ Admin có thể xem file của client upload lên.
	+ Bắt thêm 1 số excerption.
	+ Cải tiến hiệu năng runtime.
	+ Phân tích file .java để đánh giá cấu trúc của code OOP.

5. Mô tả triển khai
+ Chấm mã nguồn Java: Người dùng up file java thì hệ thống sẽ chấm file theo với đầu vào là 1 file input và cho ra một file out_pred sau đó sẽ được so sánh với output_True và đưa ra điểm số.
+ Admin có thể đăng nhập vào để thêm bài bằng việc add vào tên bài sau đó up 2 file input và output hệ thống tự tạo 2 file như vậy tại 
vị trí lưu trên máy chủ.

6. Cấu trúc của mã nguồn

+ DAO là package dùng để lấy dữ liệu từ databse.
+ View là các lớp view sử dụng Java Swing.
+ Controlder là các lớp controler sử lí gửi nhận các yêu cầu cho view để hiện thị
+ Utils dùng để code các hàm bổ trợ trong quá trình chạy
