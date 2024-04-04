package View;

import Connection.ConnectionSQL;

import javax.imageio.ImageIO;
import javax.swing.*;
import Process.*;
import com.github.sarxos.webcam.Webcam;
import com.google.zxing.WriterException;

import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class TicketIn extends JFrame {
    private JButton btnInsert;
    private JButton btnExit;
    private JLabel camera;
    private JLabel camera1;
    private JLabel imageQR;
    private JLabel lbNumber;
    private JLabel lbPrice;
    private JLabel lbPosition;
    private JLabel lbCustomer;
    private JTextField tfNumber;
    private JTextField tfPrice;
    private JTextField tfPosition;
    private JTextField tfCustomer;
    private JButton btnTicketOut;
    private JButton btnCustomer;
    private JLabel lbMoto;
    private JLabel lbCar;
    private JLabel lbU;
    private JButton btnRefresh;
    private JTextField tfMoto;
    private JTextField tfCar;
    private JTextField tfU;
    private ImageIcon i;
    private Image m;
    private JPanel panel;
    private String path;
    private String path1;
    private ConnectionSQL connectionSQL;
    private Ticket ticket = new Ticket();
    private Webcam webcam;
    private Check check = new Check();
    private Positions positions = new Positions();
    public TicketIn(String user,Boolean type) throws SQLException, ClassNotFoundException {
        connectionSQL = new ConnectionSQL();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0,0,1920, 1080);
        panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panel);
        panel.setLayout(null);

        lbNumber = new JLabel("Number");
        lbNumber.setBounds(900,117,100,50);
        lbNumber.setFont(new Font("Verdana", Font.PLAIN, 18));
        panel.add(lbNumber);
        lbPrice = new JLabel("Price");
        lbPrice.setBounds(900,282,100,20);
        lbPrice.setFont(new Font("Verdana", Font.PLAIN, 18));
        panel.add(lbPrice);
        lbPosition = new JLabel("Position");
        lbPosition.setBounds(900,373,100,20);
        lbPosition.setFont(new Font("Verdana", Font.PLAIN, 18));
        panel.add(lbPosition);
        lbCustomer = new JLabel("Customer");
        lbCustomer.setBounds(900,470,100,20);
        lbCustomer.setFont(new Font("Verdana", Font.PLAIN, 18));
        panel.add(lbCustomer);
        lbU = new JLabel("Nhân viên");
        lbU.setBounds(900,680,100,20);
        lbU.setFont(new Font("Verdana", Font.PLAIN, 18));
        panel.add(lbU);
        lbMoto = new JLabel("Xe máy");
        lbMoto.setBounds(900,817,100,20);
        lbMoto.setFont(new Font("Verdana", Font.PLAIN, 18));
        panel.add(lbMoto);
        lbCar = new JLabel("Xe ô tô");
        lbCar.setBounds(900,923,100,20);
        lbCar.setFont(new Font("Verdana", Font.PLAIN, 18));
        panel.add(lbCar);

        btnTicketOut = new JButton("RA");
        btnTicketOut.setForeground(Color.WHITE);
        btnTicketOut.setBounds(250,10,200,50);
        i = new ImageIcon("src\\icon\\btnOutTicket.png");
        m = i.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        btnTicketOut.setIcon(new ImageIcon(m));
        btnTicketOut.setBackground(new Color(42, 115, 196));
        panel.add(btnTicketOut);
        btnTicketOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                webcam.close();
                TicketOut ticketOut = new TicketOut(user,type);
                ticketOut.setVisible(true);
                dispose();

            }
        });

        btnCustomer = new JButton("KHÁCH HÀNG");
        btnCustomer.setBounds(490,10,200,50);
        btnCustomer.setForeground(Color.WHITE);
        i =new ImageIcon("src\\icon\\peopel.png");
        m = i.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        btnCustomer.setIcon(new ImageIcon(m));
        btnCustomer.setBackground(new Color(42, 115, 196));
        panel.add(btnCustomer);
        btnCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                webcam.close();
                Register form = new Register(user,type);
                form.setVisible(true);
                dispose();
            }
        });

        tfNumber = new JTextField();
        tfNumber.setBounds(1015,110,200,50);
        tfNumber.setFont(new Font("Verdana", Font.PLAIN, 18));
        panel.add(tfNumber);
        tfPrice = new JTextField();
        tfPrice.setBounds(1015,269,200,50);
        tfPrice.setFont(new Font("Verdana", Font.PLAIN, 18));
        tfPrice.setEditable(false);
        panel.add(tfPrice);
        tfPosition = new JTextField();
        tfPosition.setBounds(1015,360,200,50);
        tfPosition.setFont(new Font("Verdana", Font.PLAIN, 18));
        tfPosition.setEditable(false);
        panel.add(tfPosition);
        tfCustomer = new JTextField();
        tfCustomer.setBounds(1015,457,200,50);
        tfCustomer.setFont(new Font("Verdana", Font.PLAIN, 18));
        tfCustomer.setEditable(false);


        panel.add(tfCustomer);
        tfMoto = new JTextField();
        tfMoto.setBounds(1015,805,200,50);
        tfMoto.setFont(new Font("Verdana", Font.PLAIN, 18));
        tfMoto.setEditable(false);
        panel.add(tfMoto);
        tfCar = new JTextField();
        tfCar.setBounds(1015,911,200,50);
        tfCar.setFont(new Font("Verdana", Font.PLAIN, 18));
        tfCar.setEditable(false);
        panel.add(tfCar);
        tfU = new JTextField();
        tfU.setBounds(1015,668,200,50);
        tfU.setFont(new Font("Verdana", Font.PLAIN, 18));
        tfU.setEditable(false);
        tfU.setText(user);
        panel.add(tfU);

        camera = new JLabel();
        panel.add(camera);
        camera.setBounds(40,80,600,450);
        camera.setBackground(Color.black);
        JPanel p1 = new JPanel();
        p1.setBackground(Color.darkGray);
        p1.setBounds(40,80,600,450);
        panel.add(p1);

        camera1 = new JLabel();
        panel.add(camera1);
        camera1.setBounds(40,580,600,450);
        JPanel p2 = new JPanel();
        p2.setBackground(Color.darkGray);
        p2.setBounds(40,580,600,450);
        panel.add(p2);


        // ====================== Webcam =================
        webcam= Webcam.getDefault();
        webcam.setViewSize(new Dimension(640,480));
        webcam.open();
        new Thread(new Runnable() {
            @Override public void run()
            {
                while(true){
                    try {
                        Image image = webcam.getImage();
                        camera.setIcon(new ImageIcon(image));
                        camera1.setIcon(new ImageIcon(image));
                        Thread.sleep(80);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();


        btnInsert = new JButton("THÊM");
        panel.add(btnInsert);
        btnInsert.setForeground(Color.WHITE);
        btnInsert.setBounds(1600,450,200,50);
        i =new ImageIcon("src\\icon\\btnInsert.png");
        m = i.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        btnInsert.setIcon(new ImageIcon(m));
        btnInsert.setBackground(new Color(42, 115, 196));
        btnInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(check.checkString(tfNumber.getText())==false){
                        JOptionPane.showMessageDialog(null, "Biển số không hợp lệ!");
                        return;
                    }
                    String v = String.valueOf(ticket.countTicket());
                    ImageIO.write(webcam.getImage(),"png",new File("src\\image\\"+v+"1.png"));
                    ImageIO.write(webcam.getImage(),"png",new File("src\\image\\"+v+"2.png"));
                    String a[] = ticket.insertTicket(tfNumber.getText(),type,user,"src\\image\\"+v+"1.png","src\\image\\"+v+"2.png");
                    if(a==null){
                        return;
                    }
                    tfCustomer.setText(a[0]);
                    tfPosition.setText(a[1]);
                    tfPrice.setText(a[2]);
                    setImageQR(imageQR,ticket.createQR(Integer.parseInt(a[3])));
                    tfNumber.setEditable(false);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (WriterException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnRefresh = new JButton("LÀM MỚI");
        panel.add(btnRefresh);
        btnRefresh.setForeground(Color.WHITE);
        btnRefresh.setBounds(1600,668,200,50);
        i =new ImageIcon("src\\icon\\btnRefresh.png");
        m = i.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        btnRefresh.setIcon(new ImageIcon(m));
        btnRefresh.setBackground(new Color(42, 115, 196));
        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    resetForm(tfMoto,tfCar,tfPrice,tfPosition,tfCustomer,tfNumber);
                    tfNumber.setEditable(true);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        imageQR = new JLabel();
        panel.add(imageQR);
        imageQR.setBounds(1540,110,300,300);
        JPanel p4 = new JPanel();
        p4.setBackground(Color.darkGray);
        p4.setBounds(1540,110,300,300);
        panel.add(p4);


        JPanel p3 = new JPanel();
        p3.setBackground(Color.GRAY);
        p3.setBounds(810,80,1060,450);
        panel.add(p3);

        JPanel p5 = new JPanel();
        p5.setBackground(Color.GRAY);
        p5.setBounds(810,580,1060,450);
        panel.add(p5);

        btnExit = new JButton("THOÁT");
        btnExit.setForeground(Color.WHITE);
        btnExit.setBounds(10,10,200,50);
        i = new ImageIcon("src\\icon\\logout.png");
        m = i.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        btnExit.setIcon(new ImageIcon(m));
        btnExit.setBackground(new Color(42, 115, 196));
        panel.add(btnExit);
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                webcam.close();
                ChoseVehicle form = new ChoseVehicle("khaibui");
                form.setVisible(true);
                dispose();
            }
        });
        resetForm(tfMoto,tfCar,tfPrice,tfPosition,tfCustomer,tfNumber);
    }
    private void setImageQR(JLabel label,String path){
        ImageIcon ii = new ImageIcon(path);
        Image image = ii.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(image));
    }
    private void resetForm(JTextField rmoto,JTextField rcar,JTextField rprice,JTextField rposition,JTextField rcustomer,JTextField rnumber) throws SQLException, ClassNotFoundException {
        String a[] = positions.countPositionEmpty();
        ImageIcon ii = new ImageIcon("");
        Image image = ii.getImage().getScaledInstance(imageQR.getWidth(), imageQR.getHeight(), Image.SCALE_SMOOTH);
        imageQR.setIcon(new ImageIcon(image));
        rcar.setText(a[1]);
        rmoto.setText(a[0]);
        rprice.setText("");
        rnumber.setText("");
        rposition.setText("");
        rcustomer.setText("");
    }
}
