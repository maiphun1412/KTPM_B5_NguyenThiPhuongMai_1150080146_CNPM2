package B5;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.time.LocalDate;

public class DangKyKhachHangForm extends JFrame {

    // ====== Fields ======
    private final JTextField txtMaKH = new JTextField();
    private final JTextField txtHoTen = new JTextField();
    private final JTextField txtEmail = new JTextField();
    private final JTextField txtSDT = new JTextField();
    private final JTextArea txtDiaChi = new JTextArea(3, 20);

    private final JPasswordField txtMatKhau = new JPasswordField();
    private final JPasswordField txtXacNhan = new JPasswordField();
    private final JTextField txtNgaySinh = new JTextField();

    private final JRadioButton rbNam = new JRadioButton("Nam");
    private final JRadioButton rbNu = new JRadioButton("Nữ");
    private final JRadioButton rbKhac = new JRadioButton("Khác");

    private final JCheckBox cbDieuKhoan = new JCheckBox("Tôi đồng ý với các điều khoản dịch vụ *");

    private final JButton btnDangKy = new JButton("Đăng ký");
    private final JButton btnNhapLai = new JButton("Nhập lại");

    public DangKyKhachHangForm() {
        setTitle("Đăng ký tài khoản khách hàng");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(820, 760);
        setLocationRelativeTo(null);

        // Root background
        JPanel root = new JPanel(new GridBagLayout());
        root.setBackground(new Color(245, 247, 250));
        setContentPane(root);

        // Card
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(110, 160, 255), 2, true),
                new EmptyBorder(20, 26, 20, 26)
        ));

        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0; gc.gridy = 0;
        gc.insets = new Insets(18, 18, 18, 18);
        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1; gc.weighty = 1;
        root.add(card, gc);

        // Title
        JLabel title = new JLabel("ĐĂNG KÝ TÀI KHOẢN KHÁCH HÀNG", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBorder(new EmptyBorder(10, 0, 16, 0));
        card.add(title, BorderLayout.NORTH);

        // Form panel
        JPanel form = new JPanel(new GridBagLayout());
        form.setOpaque(false);
        form.setBorder(new EmptyBorder(6, 10, 6, 10));
        card.add(form, BorderLayout.CENTER);

        // Style inputs
        styleText(txtMaKH, "6-10 ký tự, chỉ chữ và số");
        styleText(txtHoTen, "Nhập họ tên đầy đủ");
        styleText(txtEmail, "ví dụ: nguyenvana@email.com");
        styleText(txtSDT, "Bắt đầu bằng số 0, 10-12 số");
        styleText(txtNgaySinh, "mm/dd/yyyy (không bắt buộc)");

        stylePassword(txtMatKhau, "Ít nhất 8 ký tự");
        stylePassword(txtXacNhan, "Nhập lại mật khẩu");

        txtDiaChi.setLineWrap(true);
        txtDiaChi.setWrapStyleWord(true);
        txtDiaChi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtDiaChi.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 225, 230), 1, true),
                new EmptyBorder(10, 12, 10, 12)
        ));

        // Gender group
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbNam); bg.add(rbNu); bg.add(rbKhac);
        styleRadio(rbNam); styleRadio(rbNu); styleRadio(rbKhac);

        cbDieuKhoan.setOpaque(false);
        cbDieuKhoan.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        // Add rows
        int r = 0;
        r = addRow(form, r, required("Mã Khách Hàng"), txtMaKH);
        r = addRow(form, r, required("Họ và Tên"), txtHoTen);
        r = addRow(form, r, required("Email"), txtEmail);
        r = addRow(form, r, required("Số điện thoại"), txtSDT);
        r = addRowArea(form, r, required("Địa chỉ"), new JScrollPane(txtDiaChi));
        r = addRow(form, r, required("Mật khẩu"), txtMatKhau);
        r = addRow(form, r, required("Xác nhận Mật khẩu"), txtXacNhan);
        r = addRow(form, r, optional("Ngày sinh"), txtNgaySinh);

        JPanel pGender = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 0));
        pGender.setOpaque(false);
        pGender.add(rbNam);
        pGender.add(rbNu);
        pGender.add(rbKhac);
        r = addRowCustom(form, r, optional("Giới tính"), pGender);

        GridBagConstraints gck = new GridBagConstraints();
        gck.gridx = 1; gck.gridy = r;
        gck.insets = new Insets(10, 8, 10, 8);
        gck.anchor = GridBagConstraints.WEST;
        gck.fill = GridBagConstraints.HORIZONTAL;
        gck.weightx = 1;
        form.add(cbDieuKhoan, gck);
        r++;

        // Bottom buttons
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 10));
        bottom.setOpaque(false);

        styleButtonPrimary(btnDangKy);
        styleButtonSecondary(btnNhapLai);

        bottom.add(btnDangKy);
        bottom.add(btnNhapLai);
        card.add(bottom, BorderLayout.SOUTH);

        // Events
        btnNhapLai.addActionListener(e -> resetForm());
        btnDangKy.addActionListener(e -> handleDangKy());
    }

    private void handleDangKy() {
        String maKH = txtMaKH.getText().trim();
        String hoTen = txtHoTen.getText().trim();
        String email = txtEmail.getText().trim();
        String sdt = txtSDT.getText().trim();
        String diaChi = txtDiaChi.getText().trim();
        String mk = new String(txtMatKhau.getPassword());
        String xn = new String(txtXacNhan.getPassword());
        String ngaySinhStr = txtNgaySinh.getText().trim();

        boolean dongY = cbDieuKhoan.isSelected();

        String err = Validators.validateAll(maKH, hoTen, email, sdt, diaChi, mk, xn, ngaySinhStr, dongY);
        if (err != null) {
            JOptionPane.showMessageDialog(this, err, "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String gioiTinh = null;
        if (rbNam.isSelected()) gioiTinh = "Nam";
        else if (rbNu.isSelected()) gioiTinh = "Nữ";
        else if (rbKhac.isSelected()) gioiTinh = "Khác";

        LocalDate dob = Validators.parseDobOrNull(ngaySinhStr); // có thể null

        // Lưu DB
        try (Connection conn = DbUtil.open()) {

            // Check trùng MaKH (PRIMARY KEY)
            if (DbUtil.exists(conn, "SELECT 1 FROM KHACH_HANG WHERE MaKH = ?", maKH)) {
                JOptionPane.showMessageDialog(this, "Mã khách hàng đã tồn tại (bị trùng).", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check trùng Email (UNIQUE)
            if (DbUtil.exists(conn, "SELECT 1 FROM KHACH_HANG WHERE Email = ?", email)) {
                JOptionPane.showMessageDialog(this, "Email đã tồn tại (bị trùng).", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String sql = "INSERT INTO KHACH_HANG " +
                    "(MaKH, HoTen, Email, SoDienThoai, DiaChi, MatKhauHash, NgaySinh, GioiTinh, DongYDieuKhoan) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, maKH);
                ps.setString(2, hoTen);
                ps.setString(3, email);
                ps.setString(4, sdt);
                ps.setString(5, diaChi);

                // SHA-256 -> VARCHAR(64)
                ps.setString(6, PasswordUtil.sha256Hex(mk));

                if (dob == null) ps.setNull(7, Types.DATE);
                else ps.setDate(7, java.sql.Date.valueOf(dob));

                if (gioiTinh == null) ps.setNull(8, Types.NVARCHAR);
                else ps.setString(8, gioiTinh);

                ps.setBoolean(9, true);

                ps.executeUpdate();
            }

            JOptionPane.showMessageDialog(this, "Đăng ký tài khoản thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            // Nếu muốn: reset luôn sau khi đăng ký
            // resetForm();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Không lưu được vào SQL Server.\n" + ex.getMessage(),
                    "Lỗi kết nối/SQL",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void resetForm() {
        txtMaKH.setText("");
        txtHoTen.setText("");
        txtEmail.setText("");
        txtSDT.setText("");
        txtDiaChi.setText("");
        txtMatKhau.setText("");
        txtXacNhan.setText("");
        txtNgaySinh.setText("");

        rbNam.setSelected(false);
        rbNu.setSelected(false);
        rbKhac.setSelected(false);
        cbDieuKhoan.setSelected(false);

        txtMaKH.requestFocus();
    }

    // ================= UI Helpers =================

    private JLabel required(String text) {
        JLabel lb = new JLabel(text + " *");
        lb.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lb.setForeground(new Color(55, 55, 55));
        return lb;
    }

    private JLabel optional(String text) {
        JLabel lb = new JLabel(text);
        lb.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lb.setForeground(new Color(55, 55, 55));
        return lb;
    }

    private void styleText(JTextField t, String tooltip) {
        t.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        t.setToolTipText(tooltip);
        t.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 225, 230), 1, true),
                new EmptyBorder(10, 12, 10, 12)
        ));
    }

    private void stylePassword(JPasswordField p, String tooltip) {
        p.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        p.setToolTipText(tooltip);
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 225, 230), 1, true),
                new EmptyBorder(10, 12, 10, 12)
        ));
    }

    private void styleRadio(JRadioButton rb) {
        rb.setOpaque(false);
        rb.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        rb.setForeground(new Color(60, 60, 60));
        rb.setFocusable(false);
    }

    private void styleButtonPrimary(JButton b) {
        b.setFont(new Font("Segoe UI", Font.BOLD, 13));
        b.setForeground(Color.WHITE);
        b.setBackground(new Color(22, 119, 255));
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createEmptyBorder(10, 28, 10, 28));
    }

    private void styleButtonSecondary(JButton b) {
        b.setFont(new Font("Segoe UI", Font.BOLD, 13));
        b.setForeground(Color.WHITE);
        b.setBackground(new Color(130, 140, 150));
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createEmptyBorder(10, 28, 10, 28));
    }

    private int addRow(JPanel form, int row, JLabel label, JComponent field) {
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(10, 10, 10, 10);
        gc.fill = GridBagConstraints.HORIZONTAL;

        gc.gridx = 0; gc.gridy = row; gc.weightx = 0;
        form.add(label, gc);

        gc.gridx = 1; gc.gridy = row; gc.weightx = 1;
        form.add(field, gc);

        return row + 1;
    }

    private int addRowArea(JPanel form, int row, JLabel label, JComponent field) {
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(10, 10, 10, 10);
        gc.fill = GridBagConstraints.BOTH;

        gc.gridx = 0; gc.gridy = row; gc.weightx = 0; gc.weighty = 0;
        gc.anchor = GridBagConstraints.NORTHWEST;
        form.add(label, gc);

        gc.gridx = 1; gc.gridy = row; gc.weightx = 1; gc.weighty = 0;
        form.add(field, gc);

        return row + 1;
    }

    private int addRowCustom(JPanel form, int row, JLabel label, JComponent custom) {
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(10, 10, 10, 10);
        gc.fill = GridBagConstraints.HORIZONTAL;

        gc.gridx = 0; gc.gridy = row; gc.weightx = 0;
        form.add(label, gc);

        gc.gridx = 1; gc.gridy = row; gc.weightx = 1;
        form.add(custom, gc);

        return row + 1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DangKyKhachHangForm().setVisible(true));
    }
}
