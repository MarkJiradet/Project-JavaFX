import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CatHotelGUI {
	// ประกาศ UI components และตัวแปรที่เกี่ยวข้อง
    private JFrame frame;
    private JTextField customerNameField, phoneField, daysField;
    private JRadioButton oneCat, twoCats, threeCats;
    private JList<String> roomList;
    private JComboBox<String> discountBox;
    private JButton calculateBtn, resetBtn, closeBtn;
    private boolean[] roomOccupied = new boolean[20];

   // Constructor สำหรับ GUI
    public CatHotelGUI() {
    	// กำหนดค่าเริ่มต้นสำหรับ JFrame
        frame = new JFrame("Cat Hotel Booking System");
        frame.setSize(500, 400);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        
        // โหลดรูปภาพและปรับขนาดรูปภาพ
        int desiredWidth = 150; 
        int desiredHeight = 150; 
        URL imageURL = CatHotelGUI.class.getResource("Cat.png");
        ImageIcon catIcon = new ImageIcon(imageURL);
        Image scaledImage = catIcon.getImage().getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        // แสดงรูปภาพแมวบน GUI
        JLabel catLabel = new JLabel(scaledIcon);
        catLabel.setBounds(300, 10, desiredWidth, desiredHeight);
        frame.add(catLabel);

        // แสดงข้อความ "Welcome to Cat Hotel"
        JLabel hotelLabel = new JLabel("Welcome to Cat Hotel");
        hotelLabel.setBounds(20, 10, 160, 25);
        frame.add(hotelLabel);

        // แสดงวันที่ปัจจุบัน
        JLabel dateLabel = new JLabel("Date : " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        dateLabel.setBounds(20, 40, 200, 25);  
        frame.add(dateLabel);

        // สร้างและเพิ่ม field สำหรับป้อนชื่อลูกค้า
        JLabel nameLabel = new JLabel("Customer Name:");
        nameLabel.setBounds(20, 70, 100, 25);
        frame.add(nameLabel);
        customerNameField = new JTextField();
        customerNameField.setBounds(130, 70, 150, 25);
        frame.add(customerNameField);

        // สร้างและเพิ่ม field สำหรับป้อนเบอร์โทรศัพท์
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(20, 100, 100, 25);
        frame.add(phoneLabel);
        phoneField = new JTextField();
        phoneField.setBounds(130, 100, 150, 25);
        frame.add(phoneField);

        // สร้างและเพิ่ม field สำหรับป้อนจำนวนวัน
        JLabel daysLabel = new JLabel("Days:");
        daysLabel.setBounds(20, 130, 100, 25);
        frame.add(daysLabel);
        daysField = new JTextField();
        daysField.setBounds(130, 130, 150, 25);
        frame.add(daysField);

        // สร้างและเพิ่ม radio buttons สำหรับเลือกจำนวนแมว
        JPanel catPanel = new JPanel();
        catPanel.setBounds(20, 160, 250, 50);
        oneCat = new JRadioButton("1 Cat", true);
        twoCats = new JRadioButton("2 Cats");
        threeCats = new JRadioButton("3 Cats");
        catPanel.add(oneCat);
        catPanel.add(twoCats);
        catPanel.add(threeCats);
        ButtonGroup group = new ButtonGroup();
        group.add(oneCat);
        group.add(twoCats);
        group.add(threeCats);
        frame.add(catPanel);

        // สร้างและเพิ่ม list สำหรับเลือกห้อง
        roomList = new JList<>(new String[]{"Room 1", "Room 2", "Room 3", "Room 4", "Room 5", "Room 6", "Room 7", "Room 8", "Room 9", "Room 10",
                "Room 11", "Room 12", "Room 13", "Room 14", "Room 15", "Room 16", "Room 17", "Room 18", "Room 19", "Room 20"});
        roomList.setVisibleRowCount(3);
        roomList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        roomList.setCellRenderer(new RoomCellRenderer());
        JScrollPane scrollPane = new JScrollPane(roomList);
        scrollPane.setBounds(20, 220, 220, 80);
        frame.add(scrollPane);

        // สร้างและเพิ่ม comboBox สำหรับเลือกส่วนลด
        JLabel discountLabel = new JLabel("Discount:");
        discountLabel.setBounds(270, 220, 100, 25);
        frame.add(discountLabel);
        discountBox = new JComboBox<>(new String[]{"0%", "5%", "10%", "15%", "20%"});
        discountBox.setBounds(330, 220, 140, 25);
        frame.add(discountBox);

        // สร้างและเพิ่มปุ่มสำหรับคำนวณราคา
        calculateBtn = new JButton("Calculate Price");
        calculateBtn.setBounds(80, 310, 130, 25);
        frame.add(calculateBtn);

        // สร้างและเพิ่มปุ่มสำหรับรีเซ็ตข้อมูล
        resetBtn = new JButton("Reset");
        resetBtn.setBounds(230, 310, 80, 25);
        frame.add(resetBtn);

        // สร้างและเพิ่มปุ่มสำหรับปิดโปรแกรม
        closeBtn = new JButton("Close");
        closeBtn.setBounds(330, 310, 80, 25);
        frame.add(closeBtn);

        // กำหนด ActionListener สำหรับปุ่มที่มีใน GUI
        calculateBtn.addActionListener(e -> calculatePrice());
        resetBtn.addActionListener(e -> resetFields());
        closeBtn.addActionListener(e -> System.exit(0));

        // แสดง JFrame
        frame.setVisible(true);
    }

 // คำนวณราคาโดยยึดตามจำนวนวัน, จำนวนแมว, และส่วนลด
    private void calculatePrice() {
        int days = Integer.parseInt(daysField.getText());
        int catCount = oneCat.isSelected() ? 1 : (twoCats.isSelected() ? 2 : 3);
        double discount = getDiscount();
        double totalCost = days * catCount * 150 * (1 - discount);

        int selectedRoom = roomList.getSelectedIndex();
        if (roomOccupied[selectedRoom]) {
            JOptionPane.showMessageDialog(frame, "This room is already booked. Please select another room.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int result = JOptionPane.showConfirmDialog(frame, "Total price: " + totalCost + " Baht. Confirm booking?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            roomOccupied[selectedRoom] = true;
            updateRoomColor();
        }
    }

 // ดึงค่าส่วนลดจาก comboBox ที่ผู้ใช้เลือก
    private double getDiscount() {
        String selectedDiscount = (String) discountBox.getSelectedItem();
        switch (selectedDiscount) {
            case "5%":
                return 0.05;
            case "10%":
                return 0.10;
            case "15%":
                return 0.15;
            case "20%":
                return 0.20;
            default:
                return 0;
        }
    }

 // รีเซ็ตข้อมูลที่ป้อนใน fields ทั้งหมด
    private void resetFields() {
        customerNameField.setText("");
        phoneField.setText("");
        daysField.setText("");
        oneCat.setSelected(true);
        discountBox.setSelectedIndex(0);
        roomList.clearSelection();
    }

 // อัปเดตสีของรายการห้องที่ถูกจอง
    private void updateRoomColor() {
        roomList.repaint();
    }

 // Custom Renderer สำหรับ JList เพื่อเปลี่ยนสีข้อความของห้องที่ถูกจอง
    private class RoomCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (roomOccupied[index]) {
                component.setForeground(Color.RED);
            } else {
                component.setForeground(Color.BLACK);
            }

            return component;
        }
    }

 // สร้างและแสดง GUI โดยใช้ Event Dispatch Thread
    public static void main(String[] args) {
        SwingUtilities.invokeLater(CatHotelGUI::new);
    }
}
