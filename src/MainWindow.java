import io.qt.gui.QIcon;
import io.qt.widgets.*;

public class MainWindow extends QMainWindow {
    public MainWindow(){
        setWindowTitle("Temperature Converse");
        setWindowIcon(new QIcon("src/resources/images/icon.jpg"));
        setupSize();
        setupUI();
    }

    private void setupSize(){
        resize(300, 100);
        setMinimumSize(175, 125);
        setMaximumSize(320, 150);
    }

    private void setupUI(){
        QWidget centralWidget = new QWidget();
        QFormLayout vLayout = new QFormLayout();

        QLabel label = new QLabel("Temperature: ");
        QLineEdit entry = new QLineEdit();
        entry.setPlaceholderText("Ex: 100.00");
        vLayout.addRow(label, entry);

        QPushButton convertToF = new QPushButton("Convert to Fahrenheit");
        QPushButton convertToC = new QPushButton("Convert to Celsius");
        vLayout.addRow(convertToF);
        vLayout.addRow(convertToC);

        QLineEdit out = new QLineEdit();
        out.setReadOnly(true);
        QLabel textOut = new QLabel("Result: ");
        vLayout.addRow(textOut, out);

        convertToF.clicked.connect(() -> {
            try{
                double n1 = Double.parseDouble(entry.getText());
                String result = String.format("%.2f", (n1*1.8)+32);
                out.setText(result + " °F");
            } catch(NumberFormatException e){
                out.setText("Error: Invalid Input!");
            }
        });

        convertToC.clicked.connect(() -> {
            try{
                double n1 = Double.parseDouble(entry.getText());
                String result = String.format("%.2f", ((n1-32)*0.555));
                out.setText(result + " °C");
            } catch(NumberFormatException e){
                out.setText("Error: Invalid Input!");
            }
        });

        centralWidget.setLayout(vLayout);
        setCentralWidget(centralWidget);
    }
}