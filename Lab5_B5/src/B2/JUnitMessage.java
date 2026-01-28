package B2;

public class JUnitMessage {
    private String message;

    public JUnitMessage(String message) {
        this.message = message;
    }

    // Hàm in / trả về message
    // Nếu muốn giống mẫu "exception", ta chủ động gây ArithmeticException
    public String printMessage() {
        System.out.println(message);

        // Cố tình gây lỗi chia 0 để test expected exception
        int x = 10 / 0;   // -> ArithmeticException

        return message;   // dòng này sẽ không chạy tới
    }

    // Hàm trả về "Hi!" + message (không gây lỗi)
    public String printHiMessage() {
        return "Hi!" + message;
    }
}
