import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class CustomNotification extends BorderLayoutNotification {
    private JLabel m_label;
    private JButton m_button;
    private JProgressBar m_progress;

    private TextTheme m_theme;

    public CustomNotification() {
        m_label = new JLabel();
        m_button = new JButton("Click me!");
        m_progress = new JProgressBar();

        m_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeComponent(m_button);
                addComponent(m_progress, BorderLayout.SOUTH);
                final Timer timer = new Timer(100, null);

                timer.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        m_progress.setValue(m_progress.getValue() + 1);
                        m_progress.repaint();

                        if (m_progress.getValue() == 100) {
                            timer.stop();
                            hide();
                        }
                    }
                });

                timer.start();
            }
        });

        this.addComponent(m_label, BorderLayout.CENTER);
        this.addComponent(m_button, BorderLayout.SOUTH);
    }

    public void setTextTeme(TextTheme theme) {
        m_label.setFont(theme.title);
        m_label.setForeground(theme.titleColor);
        m_button.setFont(theme.subtitle);
        m_button.setForeground(theme.subtitleColor);

        m_theme = theme;
    }

    public String getText() {
        return m_label.getText();
    }

    public void setText(String text) {
        m_label.setText(text);
    }

    @Override
    protected void themeSet(WindowTheme theme) {
        super.themeSet(theme);

        if (m_theme != null) {
            // the WindowNotification is going to automatically give all our labels with the
            // set foreground color, but
            // we want to change this to the title color of the font
            m_label.setForeground(m_theme.titleColor);
            m_button.setForeground(m_theme.subtitleColor);
        }
    }

    public static class CustomBuilder implements NotificationBuilder<CustomNotification> {
        @Override
        public CustomNotification buildNotification(ThemePackage pack, Object[] args) {
            CustomNotification notification = new CustomNotification();
            // handled by WindowNotification
            notification.setWindowTheme(pack.windowTheme);
            // handled by us
            notification.setTextTeme(pack.textTheme);
            if (args.length > 0) {
                notification.setText((String) args[0]);
            } else {
                notification.setText("No text supplied");
            }
            return notification;
        }
    }
}