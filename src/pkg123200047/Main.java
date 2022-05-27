package pkg123200047;

import controller.MovieController;
import model.MovieModel;
import view.MovieView;

public class Main {
    public static void main(String[] args) {
        MovieView movieView = new MovieView();
        MovieModel movieModel = new MovieModel();
        MovieController movieController = new MovieController(movieModel, movieView);
    } 
}
