package B5;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class Validators {

    private static final Pattern P_MAKH = Pattern.compile("^[a-zA-Z0-9]{6,10}$");
    private static final Pattern P_EMAIL = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final Pattern P_SDT = Pattern.compile("^0\\d{9,11}$"); // 10-12 số, bắt đầu 0

    public static String validateAll(
            String maKH,
            String hoTen,
            String email,
            String sdt,
            String diaChi,
            String matKhau,
            String xacNhan,
            String ngaySinhStr,
            boolean dongYDieuKhoan
    ) {
        if (isBlank(maKH) || isBlank(hoTen) || isBlank(email) || isBlank(sdt) || isBlank(diaChi) || isBlank(matKhau) || isBlank(xacNhan)) {
            return "Vui lòng nhập đầy đủ các trường bắt buộc (*).";
        }

        if (!P_MAKH.matcher(maKH).matches()) {
            return "Mã khách hàng: 6-10 ký tự, chỉ gồm chữ và số (a-z, A-Z, 0-9).";
        }

        if (hoTen.length() < 5 || hoTen.length() > 50) {
            return "Họ và tên: độ dài từ 5 đến 50 ký tự.";
        }

        if (!P_EMAIL.matcher(email).matches()) {
            return "Email không đúng định dạng (ví dụ: nguyenvana@email.com).";
        }

        if (!P_SDT.matcher(sdt).matches()) {
            return "Số điện thoại: phải bắt đầu bằng số 0 và dài 10-12 chữ số.";
        }

        if (diaChi.length() > 255) {
            return "Địa chỉ: tối đa 255 ký tự.";
        }

        if (matKhau.length() < 8) {
            return "Mật khẩu: tối thiểu 8 ký tự.";
        }

        if (!matKhau.equals(xacNhan)) {
            return "Xác nhận mật khẩu không khớp với mật khẩu.";
        }

        if (!isBlank(ngaySinhStr)) {
            LocalDate dob = parseDobOrNull(ngaySinhStr);
            if (dob == null) {
                return "Ngày sinh không hợp lệ. Nhập theo định dạng mm/dd/yyyy (ví dụ: 02/22/2003).";
            }
            int years = Period.between(dob, LocalDate.now()).getYears();
            if (years < 18) {
                return "Nếu nhập ngày sinh thì người dùng phải đủ 18 tuổi (tính đến hiện tại).";
            }
        }

        if (!dongYDieuKhoan) {
            return "Bạn phải tích chọn \"Tôi đồng ý với các điều khoản dịch vụ\".";
        }

        return null;
    }

    public static LocalDate parseDobOrNull(String s) {
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("M/d/yyyy");
            return LocalDate.parse(s.trim(), fmt);
        } catch (Exception e) {
            return null;
        }
    }

    private static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}
