package B4;

public class PaymentCalculator {

    public static int calculate(String type, int age) {
        if (age < 0 || age > 145) {
            throw new IllegalArgumentException("Tuổi không hợp lệ (0 - 145).");
        }

        switch (type) {
            case "TRE_EM":
                if (age <= 17) return 50;
                throw new IllegalArgumentException("Trẻ em chỉ áp dụng 0 - 17 tuổi.");

            case "NAM":
                if (age < 18) throw new IllegalArgumentException("Nam chỉ áp dụng từ 18 tuổi.");
                if (age <= 35) return 100;
                if (age <= 50) return 120;
                return 140;

            case "NU":
                if (age < 18) throw new IllegalArgumentException("Nữ chỉ áp dụng từ 18 tuổi.");
                if (age <= 35) return 80;
                if (age <= 50) return 110;
                return 140;

            default:
                throw new IllegalArgumentException("Vui lòng chọn Nam / Nữ / Trẻ em.");
        }
    }
}
