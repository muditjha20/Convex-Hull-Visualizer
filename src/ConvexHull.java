import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ConvexHull {
    public static void main(String[] args) {

        SwingShell project = new SwingShell();
        project.addWindowListener(new WindowAdapter() {
                                      public void
                                      windowClosing(WindowEvent e) {
                                          System.exit(0);
                                      }
                                  }
        );
        project.pack();
        project.setVisible(true);
    }
}