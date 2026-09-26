import java.util.ArrayList;
import java.util.List;

public class MovieManager {
    private List<Movie> movies = new ArrayList<>();

    public void addMovie(Movie movie) {
        movies.add(movie);
        System.out.println("Added: " + movie.getTitle());
    }

    public void listAll() {
        if (movies.isEmpty()) {
            System.out.println("No movies yet.");
            return;
        }
        for (Movie m : movies) {
            m.printDetails();
        }
    }

    public Movie findByTitle(String title) {
        for (Movie m : movies) {
            if (m.getTitle().equalsIgnoreCase(title)) {
                return m;
            }
        }
        throw new MovieNotFoundException("No movie found with title: " + title);
    }

    public void deleteByTitle(String title) {
        Movie found = findByTitle(title);
        movies.remove(found);
        System.out.println("Deleted: " + found.getTitle());
    }

    public void listNowShowing() {
        for (Movie m : movies) {
            if (m.isNowShowing()) {
                m.printDetails();
            }
        }
    }
}