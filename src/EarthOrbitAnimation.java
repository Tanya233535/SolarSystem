import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EarthOrbitAnimation extends JPanel implements ActionListener {
    private final Timer timer;
    private final Image sunImage;
    private final Image earthImage;

    private double angle = 0;
    private final int orbitRadius = 150;
    private final int centerX = 400;
    private final int centerY = 300;

    public EarthOrbitAnimation() {
        // Загрузка изображений
        sunImage = new ImageIcon(new ImageIcon("C:\\Users\\tanymet\\IdeaProjects\\SolarSystem\\src\\Sun.png")
                .getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)).getImage();
        earthImage = new ImageIcon(new ImageIcon("C:\\Users\\tanymet\\IdeaProjects\\SolarSystem\\src\\Earth.png")
                .getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)).getImage();

        // Настройка таймера
        timer = new Timer(16, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Рисуем Солнце в центре окна
        int sunWidth = sunImage.getWidth(this);
        int sunHeight = sunImage.getHeight(this);
        g.drawImage(sunImage, centerX - sunWidth / 2, centerY - sunHeight / 2, this);

        // Вычисляем координаты Земли
        int earthX = centerX + (int) (orbitRadius * Math.cos(angle)) - earthImage.getWidth(this) / 2;
        int earthY = centerY + (int) (orbitRadius * Math.sin(angle)) - earthImage.getHeight(this) / 2;

        // Рисуем Землю
        g.drawImage(earthImage, earthX, earthY, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        angle += 0.02;
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Земля");
        EarthOrbitAnimation animationPanel = new EarthOrbitAnimation();

        frame.add(animationPanel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
