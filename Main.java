import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main extends JFrame {
    private JTextArea textArea;
    private JTextField commandField;
    private JButton powerButton;
    private boolean powerOn = false; // 시스템 전원 상태를 나타내는 변수
    private String deleteFileName;

    public Main() {
        setTitle("Command Processor");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        initializeUI();
    }

    private void initializeUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(new Color(0, 255, 0)); // 야광 네온 초록색
        textArea.setEditable(false);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        commandField = new JTextField();
        commandField.setBackground(Color.BLACK);
        commandField.setForeground(new Color(0, 255, 0)); // 야광 네온 초록색
        commandField.addActionListener(e -> {
            if (powerOn) {
                String command = commandField.getText();
                textArea.append("> " + command + "\n");
                File[] fileList = new File[0];
                processCommand(command, fileList); // 입력받은 텍스트를 processCommand 메소드에 전달하여 호출합니다.
                commandField.setText(""); // 명령어 처리 후 입력 필드 초기화
            }
        });
        panel.add(commandField, BorderLayout.SOUTH);

        powerButton = new JButton("전원");
        powerButton.addActionListener(e -> togglePower());
        add(powerButton, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
    }


    private void processCommand(String command, File[] fileList) {
        switch (command.toLowerCase()) {
            case "exit":
                textArea.append("시스템을 종료합니다.\n");
                try {
                    Thread.sleep(1000); // 1초 후에 창을 닫습니다.
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                System.exit(0);
                break;
            case "cls":
                textArea.setText("");
                break;


            case "?":
                textArea.append("도움말: 사용 가능한 명령어는 exit, cls, edit, del, ls, help,date,ip 입니다.\n");
                break;
            case "del":
                deleteFileName = JOptionPane.showInputDialog(this, "지울 파일 이름:");
                if (deleteFileName != null && !deleteFileName.trim().isEmpty()) {
                    File fileToDelete = new File(deleteFileName);
                    if (fileToDelete.exists()) {
                        int confirm = JOptionPane.showConfirmDialog(this, "정말로 파일을 지우시겠습니까?", "파일 삭제 확인", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            if (fileToDelete.delete()) {
                                textArea.append(deleteFileName + " 파일을 삭제하였습니다.\n");
                            } else {
                                textArea.append("파일 삭제에 실패하였습니다.\n");
                            }
                        }
                    } else {
                        textArea.append("해당 파일이 존재하지 않습니다.\n");
                    }
                }
                break;

            case "ls":
                File currentDir = new File(".");
                fileList = currentDir.listFiles();
                if (fileList != null) {
                    for (File f : fileList) {
                        if (f.isFile()) {
                            textArea.append(f.getName() + "\n");
                        }
                    }
                }
                break;
            // switch 문 내에 추가
            case "cd":
                String commandText = commandField.getText();
                if (commandText.length() > 3) {
                    String targetDirectory = commandText.substring(3); // "cd " 이후의 문자열을 경로로 사용
                    File newDir = new File(targetDirectory);
                    if (newDir.exists() && newDir.isDirectory()) {
                        try {
                            // 현재 작업 디렉토리를 새 경로로 변경
                            System.setProperty("user.dir", newDir.getCanonicalPath());
                            textArea.append("현재 디렉토리가 변경되었습니다: " + System.getProperty("user.dir") + "\n");
                        } catch (IOException e) {
                            textArea.append("디렉토리 변경 중 오류가 발생했습니다.\n");
                        }
                    } else {
                        textArea.append("해당 디렉토리가 존재하지 않습니다.\n");
                    }
                } else {
                    textArea.append("유효한 경로를 입력해주세요.\n");
                }
                break;



            case "date":
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH시 mm분 ss초");
                String dayOfWeek = now.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN);

                textArea.append("현재 날짜: " + now.format(dateFormatter) + "\n");
                textArea.append("현재 시간: " + now.format(timeFormatter) + "\n");
                textArea.append("오늘 요일: " + dayOfWeek + "\n");
                break;

            case "ip":
                try {
                    // 로컬 IP 주소 찾기
                    InetAddress localIP = InetAddress.getLocalHost();
                    textArea.append("현재 로컬 IP 주소: " + localIP.getHostAddress() + "\n");

                    // 외부 IP 주소 찾기
                    URL url = new URL("http://checkip.amazonaws.com");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                        String externalIP = reader.readLine();
                        textArea.append("현재 외부 IP 주소: " + externalIP + "\n");
                    }
                } catch (Exception e) {
                    textArea.append("IP 주소를 가져오는 중 오류가 발생했습니다.\n");
                }
                break;




            default:
                textArea.append("알 수 없는 명령어입니다.\n");
                break;
        }
    }

    private void editFile(File ignoredFile) {
    }


    private void togglePower() {
        if (powerOn) {
            int confirm = JOptionPane.showConfirmDialog(this, "시스템을 종료하시겠습니까?", "종료 확인", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                powerOn = false;
                powerButton.setBackground(Color.RED);
                textArea.setVisible(false);
                commandField.setVisible(false);
            }
        } else {
            powerOn = true;
            powerButton.setBackground(Color.CYAN);
            textArea.setText(""); // textArea 내용 초기화
            textArea.setVisible(true);
            commandField.setVisible(true);
            simulateLoading();
        }
    }

    private void simulateLoading() {
        new Thread(() -> {
            try {
                playMusic(); // 배경 음악 재생
                Thread.sleep(1000); // 로딩 시간 1초 가정
                SwingUtilities.invokeLater(this::updateUIComponents);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void updateUIComponents() {
        if (powerOn) {
            textArea.setVisible(true);
            commandField.setVisible(true);
            textArea.append("> ");

            commandField.requestFocusInWindow(); // 전원이 켜지면 commandField에 포커스를 맞춥니다.
        }
    }

    private void playMusic() {
        try {
            File musicPath = new File("원하는WAV파일");
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            } else {
                System.out.println("음악 파일을 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
