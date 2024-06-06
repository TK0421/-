import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class CountdownTimerWithImage extends Object {
    static int secondsLeft = 10; // カウントダウンする秒数
    static int initialSeconds = secondsLeft; // 初期秒数を保存
    static JLabel timerLabel;
    static JFrame frame;
    static Timer timer;
    static TimerTask task;
    static boolean timerRunning = false;

    public  void perfom() {
        frame = new JFrame("Countdown Timer");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        timerLabel = new JLabel("残り時間: " + secondsLeft + "秒", JLabel.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 48));
        frame.add(timerLabel, BorderLayout.CENTER);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTimer();
            }
        });
        frame.add(startButton, BorderLayout.SOUTH);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetTimer();
            }
        });
        frame.add(resetButton, BorderLayout.NORTH);

        frame.setVisible(true);
    }

    static void startTimer() {
        if (!timerRunning) {
            timer = new Timer();
            task = new TimerTask() {
                @Override
                public void run() {
                    if (secondsLeft > 0) {
                        updateTimerLabel();
                        secondsLeft--;
                    } else {
                        cancelTimer();
                        showImage();
                    }
                }
            };

            // 1秒ごとにタスクを実行
            timer.scheduleAtFixedRate(task, 0, 1000);
            timerRunning = true;
        }
    }

    static void updateTimerLabel() {
        timerLabel.setText("残り時間: " + secondsLeft + "秒");
    }

    static void cancelTimer() {
        frame.setTitle("タイマー終了");
    }

    static void showImage() {
        ImageIcon icon = new ImageIcon("bakuhatsu.png"); // 画像ファイルのパスを指定
        JLabel imageLabel = new JLabel(icon);
        frame.getContentPane().removeAll(); // コンポーネントをすべて削除
        frame.add(imageLabel, BorderLayout.CENTER); // 画像を表示
        frame.revalidate(); // レイアウトの再構築
        frame.repaint(); // コンポーネントの再描画
    }

    static void resetTimer() {
        if (timerRunning) {
            timer.cancel();
            timerRunning = false;
        }
        secondsLeft = initialSeconds;
        frame.setTitle("Countdown Timer");
        updateTimerLabel();
    }

    public void actionPerformed() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}


