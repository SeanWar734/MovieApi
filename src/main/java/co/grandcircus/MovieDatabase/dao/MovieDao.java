package co.grandcircus.MovieDatabase.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.grandcircus.MovieDatabase.objects.Movie;

public interface MovieDao extends JpaRepository<Movie, Long>{

	List<Movie> findByNameContainingIgnoreCase(String name);
	List<Movie> findByCategoryIgnoreCase(String category);
	
//	@Query("SELECT DISTINCT category FROM movie")
//	List<String> findAllGenre();
}
