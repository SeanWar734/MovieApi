package co.grandcircus.MovieDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.grandcircus.MovieDatabase.dao.MovieDao;
import co.grandcircus.MovieDatabase.objects.Movie;

@RestController
public class MovieController {

	@Autowired
	private MovieDao dao;

	@GetMapping("/movies")
	public List<Movie> listMovies(){
		return dao.findAll();
	}

	@GetMapping("/movies/category/{category}")
	public List<Movie> findByCategory(@PathVariable("category") String category) {
		return dao.findByCategoryIgnoreCase(category);
	}

	@GetMapping("/movies/random")
	public Movie randomMovie() {
		Random rand = new Random();
		Long num = (long) rand.nextInt((int) (dao.count() - 1));
		return (dao.findById(num).orElse(null));
	}

	@GetMapping("/movies/random/{category}")
	public Movie randomMovieWithCategory(@PathVariable("category") String category) {
		Random rand = new Random();

		List<Movie> list = new ArrayList<>();
		list.addAll(dao.findByCategoryIgnoreCase(category));

		int num = rand.nextInt((int) (list.size()));

		return (list.get(num));
	}

//	@GetMapping("/movies/categories")
//	public List<String> categories() {
//		return dao.findAllGenre();
//	}

	@GetMapping("/movies/name")
	public List<Movie> withName(@RequestParam("name") String name) {
		return dao.findByNameContainingIgnoreCase(name);
	}
	
	@GetMapping("/movies/randomList")
	public ArrayList<Movie> randomMovieList(@RequestParam(value = "size", required = false) int size) {
		List<Movie> allMovies = dao.findAll();
		ArrayList<Movie> movies = new ArrayList<>();
		for (int i = 1; i <= size; i++) {
			Random r = new Random();
			int rNum = r.nextInt(allMovies.size());
			movies.add(allMovies.get(rNum));
		}
		return movies;
	}

}
