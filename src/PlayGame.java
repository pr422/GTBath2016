import javax.swing.SwingUtilities;

import controller.Controller;
import model.Model;
import view.View;

public class PlayGame {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			private View view;
			private Model model;
			private Controller controller;

			public void run() {
				view = new View();
				model = new Model();
				controller = new Controller(view, model);
			}
		});

	}

}
