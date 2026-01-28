package B4;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;

public class PaymentFormVN_Checkbox extends JFrame {

    private final JCheckBox cbNam = new JCheckBox("Nam");
    private final JCheckBox cbNu = new JCheckBox("Nữ");
    private final JCheckBox cbTreEm = new JCheckBox("Trẻ em (0 - 17 tuổi)");

    private final JTextField txtTuoi = new JTextField();
    private final JTextField txtTien = new JTextField();

    private final JButton btnTinh = new JButton("Tính");

    public PaymentFormVN_Checkbox() {
        setTitle("Tính tiền khám theo tuổi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 260);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout(10, 10));
        root.setBorder(new EmptyBorder(12, 12, 12, 12));
        setContentPane(root);

        JLabel title = new JLabel("TÍNH TIỀN CHO BỆNH NHÂN", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 16));
        root.add(title, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridBagLayout());
        root.add(center, BorderLayout.CENTER);

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(6, 6, 6, 6);
        g.fill = GridBagConstraints.HORIZONTAL;

        // ===== Hàng 0: checkbox (tick được) =====
        g.gridx = 0; g.gridy = 0; g.weightx = 0;
        center.add(cbNam, g);

        g.gridx = 1;
        center.add(cbNu, g);

        g.gridx = 2; g.gridwidth = 2;
        center.add(cbTreEm, g);
        g.gridwidth = 1;

        // Khi tick 1 checkbox -> tự bỏ tick 2 cái còn lại (để giống radio nhưng vẫn là checkbox)
        cbNam.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                cbNu.setSelected(false);
                cbTreEm.setSelected(false);
            }
        });
        cbNu.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                cbNam.setSelected(false);
                cbTreEm.setSelected(false);
            }
        });
        cbTreEm.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                cbNam.setSelected(false);
                cbNu.setSelected(false);
            }
        });

        // ===== Hàng 1: Tuổi + nút Tính =====
        JLabel lbTuoi = new JLabel("Tuổi (năm)");
        g.gridx = 0; g.gridy = 1;
        center.add(lbTuoi, g);

        txtTuoi.setColumns(10);
        g.gridx = 1; g.gridy = 1;
        center.add(txtTuoi, g);

        g.gridx = 2; g.gridy = 1; g.gridwidth = 2;
        btnTinh.setPreferredSize(new Dimension(140, 28));
        center.add(btnTinh, g);
        g.gridwidth = 1;

        // ===== Hàng 2: Tiền + euro =====
        JLabel lbTien = new JLabel("Tiền phải trả");
        g.gridx = 0; g.gridy = 2;
        center.add(lbTien, g);

        txtTien.setEditable(false);
        g.gridx = 1; g.gridy = 2;
        center.add(txtTien, g);

        JLabel lbEuro = new JLabel("euro €");
        g.gridx = 2; g.gridy = 2; g.gridwidth = 2;
        center.add(lbEuro, g);
        g.gridwidth = 1;

        btnTinh.addActionListener(e -> onTinh());
    }

    private void onTinh() {
        try {
            String type = getSelectedType();
            int age = parseTuoi();

            int payment = PaymentCalculator.calculate(type, age);
            txtTien.setText(String.valueOf(payment));

        } catch (Exception ex) {
            txtTien.setText("");
            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private String getSelectedType() {
        if (cbNam.isSelected()) return "NAM";
        if (cbNu.isSelected()) return "NU";
        if (cbTreEm.isSelected()) return "TRE_EM";
        throw new IllegalArgumentException("Vui lòng tick chọn Nam / Nữ / Trẻ em.");
    }

    private int parseTuoi() {
        String s = txtTuoi.getText().trim();
        if (s.isEmpty()) throw new IllegalArgumentException("Vui lòng nhập tuổi.");
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Tuổi phải là số nguyên.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PaymentFormVN_Checkbox().setVisible(true));
    }
}
