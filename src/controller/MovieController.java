package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import model.MovieModel;
import view.MovieView;

public class MovieController {
    MovieModel movieModel;
    MovieView movieView;
    Object header[] = {"Title", "Plot", "Character", "Acting","Score"};

    public MovieController(MovieModel movieModel, MovieView movieView) {
        this.movieModel = movieModel;
        this.movieView = movieView;
        
        if (movieModel.getMovieData()!=0) {
            String dataMovie[][] = movieModel.readMovie();
            movieView.table.setModel((new JTable(dataMovie, header)).getModel());
        }
        else {
            JOptionPane.showMessageDialog(null, "No Data");
        }
        
        movieView.btnAdd.addActionListener(new ActionListener() {           
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String title = movieView.getJudul();
                String plot = movieView.getAlur();
                String character = movieView.getPenokohan();
                String acting = movieView.getAkting();
                movieModel.insertMovide(title, plot, character, acting);
                String dataMovie[][] = movieModel.readMovie();
                movieView.table.setModel((new JTable(dataMovie, header)).getModel());
            }
        });
        
        movieView.btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String title = movieView.getJudul();
                String plot = movieView.getAlur();
                String character = movieView.getPenokohan();
                String acting = movieView.getAkting();
                movieModel.updateMovie(title, plot, character, acting);
                String dataMovie[][] = movieModel.readMovie();
                movieView.table.setModel((new JTable(dataMovie, header)).getModel());
            }
        });
        
        movieView.btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String title = movieView.getJudul();
                
                movieModel.deleteMovie(title);
                String dataMovie[][] = movieModel.readMovie();
                movieView.table.setModel((new JTable(dataMovie, header)).getModel());
            }
        });
        
        movieView.btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                movieView.setTitle();
                movieView.setPlot();
                movieView.setCharacter();
                movieView.setAction();
            }
        });
    }
    
    
}
