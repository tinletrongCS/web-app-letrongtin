**Danh sách nhóm**

  Họ và tên: Lê Trọng Tín

  MSSV: 2313452

**Public URL của Web Service đã deploy ở Lab 5**

  Link truy cập: https://web-app-letrongtin.onrender.com/students

**Hướng dẫn chạy dự án**
1. Yêu cầu hệ thống
   
Java: JDK 17 hoặc 21

Database: PostgreSQL 

IDE: IntelliJ IDEA, Eclipse, hoặc VS Code

2. Cài đặt Cơ sở dữ liệu
   
Đảm bảo service của PostgreSQL đang được chạy (ở trạng thái *Running*).
<img width="781" height="37" alt="image" src="https://github.com/user-attachments/assets/f88fa970-cb74-4c7f-8b53-7d29d4fcc0bc" />

Tạo một cơ sở dữ liệu trống có tên là student_management, với user được gán các quyền có tên *student*

<img width="877" height="682" alt="image" src="https://github.com/user-attachments/assets/80ec397d-903b-44c6-ad7d-fa557d82a28f" />

<img width="840" height="518" alt="image" src="https://github.com/user-attachments/assets/233cd2a7-0ffc-4cad-bd65-b47178e9de1f" />

<img width="726" height="297" alt="image" src="https://github.com/user-attachments/assets/e74a96dd-0ca6-40fc-ba1a-a074be4cc7d9" />

3. Cấu hình Môi trường
   
Tạo một file có tên .env ở thư mục gốc của dự án, và điền các tham số môi trường cấu hính cho dự án
<img width="1025" height="327" alt="image" src="https://github.com/user-attachments/assets/22eb79e7-502b-42df-94b1-467d544061b8" />


4. Khởi chạy Ứng dụng

Mở Terminal / Command Prompt tại thư mục gốc của dự án và chạy lệnh sau :

Bash
./mvnw spring-boot:run
Sau khi thấy dòng log Started StudentManagementApplication, mở trình duyệt và truy cập vào: http://localhost:8080/students.
<img width="1918" height="1020" alt="image" src="https://github.com/user-attachments/assets/90e867b8-fa9c-418c-93ca-937b335a8c09" />

**Trả Lời Câu Hỏi Lý Thuyết (Lab 1)**
1. Ràng buộc Khóa Chính (Primary Key)

Hiện tượng: Khi cố tình Insert một sinh viên có id trùng với một sinh viên đã có sẵn, cơ sở dữ liệu báo lỗi *UNIQUE constraint failed.*

Giải thích: Trường id được thiết lập làm Khóa chính (Primary Key). Khóa chính có chức năng định danh duy nhất cho mỗi bản ghi trong bảng để phân biệt chúng với nhau. Việc chặn thao tác trùng lặp nhằm đảm bảo tính toàn vẹn của dữ liệu, giúp các truy vấn tìm kiếm, cập nhật (UPDATE) hay xóa (DELETE) tác động chính xác đến đúng một đối tượng duy nhất.

2. Toàn vẹn dữ liệu (Constraints)

Hiện tượng: Khi Insert một sinh viên nhưng bỏ trống cột name (để NULL), SQLite không báo lỗi và vẫn cho phép lưu thành công nếu cột này chưa được thiết lập ràng buộc NOT NULL khi tạo bảng.

Hậu quả khi code Java đọc dữ liệu: Khi lấy dữ liệu lên, thuộc tính name của object Student sẽ có giá trị null. Nếu code Java hoặc file giao diện HTML vô tình gọi các hàm xử lý chuỗi trên thuộc tính này (ví dụ: student.getName().toUpperCase()), ứng dụng sẽ ngay lập tức ném ra lỗi NullPointerException (NPE) và gây gián đoạn chương trình.

3. Cấu hình Hibernate

Câu hỏi: Tại sao mỗi lần tắt ứng dụng và chạy lại, dữ liệu cũ trong Database lại bị mất hết?

Giải thích: Nguyên nhân là do trong file application.properties, thuộc tính tự động hóa schema của Hibernate đang được cấu hình là *spring.jpa.hibernate.ddl-auto=create*. Với cấu hình này, mỗi lần Spring Boot khởi động, nó sẽ tự động chạy lệnh DROP TABLE để xóa toàn bộ cấu trúc bảng và xóa cả dữ liệu bên trong, sau đó mới tiến hành CREATE TABLE lại từ đầu. Để khắc phục và giữ lại dữ liệu cho các lần chạy sau, cần đổi cấu hình này thành *update*.

**Ảnh Chụp Các Module (Lab 4)**
1. Trang Danh sách sinh viên và Chức năng Tìm kiếm
Trang hiển thị toàn bộ sinh viên. Đã tích hợp ô tìm kiếm theo tên và các nút điều hướng.

<img width="1918" height="967" alt="image" src="https://github.com/user-attachments/assets/596fa991-9f5d-46f0-b635-75a568a768ad" />


2. Trang Chi Tiết (Detail View)
Trang hiển thị đầy đủ thông tin của một sinh viên cụ thể theo ID.

<img width="1750" height="110" alt="image" src="https://github.com/user-attachments/assets/61eb4b1f-c511-4802-959c-695e26d5cf04" />

<img width="1503" height="643" alt="image" src="https://github.com/user-attachments/assets/9eeecd31-f733-41a3-8112-5615c3beb435" />


3. Giao diện Thêm Mới / Chỉnh Sửa Thông Tin
Form nhập liệu tái sử dụng cho cả hai chức năng Create và Update.

<img width="1461" height="741" alt="image" src="https://github.com/user-attachments/assets/fec96e05-6742-412b-9849-181ec2388467" />



4. Xác Nhận Xóa (Delete Confirmation)
<img width="1057" height="580" alt="image" src="https://github.com/user-attachments/assets/2dfbb020-bd95-45fe-8746-9ea8ef596eec" />


