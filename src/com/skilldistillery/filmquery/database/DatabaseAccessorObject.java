
package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private static final String url = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	private static final String user = "student";
	private static final String pass = "student";

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet actorResult = stmt.executeQuery();
			if (actorResult.next()) {
				actor = new Actor();
				actor.setId(actorResult.getInt("id"));
				actor.setFirstName(actorResult.getString("first_name"));
				actor.setLastName(actorResult.getString("last_name"));

				actor.setFilms(findFilmsByActorId(actorId));
			} else {
				System.out.println("No such actor found");
			}
			stmt.close();
			conn.close();
			actorResult.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actor;
	}

	public List<Film> findFilmsByActorId(int actorId) {
		List<Film> films = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT *" + " FROM film JOIN film_actor ON film.id = film_actor.film_id "
					+ " WHERE actor_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int filmId = rs.getInt("id");
				String title = rs.getString("title");
				String desc = rs.getString("description");
				short releaseYear = rs.getShort("release_year");
				int langId = rs.getInt("language_id");
				int rentDur = rs.getInt("rental_duration");
				double rate = rs.getDouble("rental_rate");
				int length = rs.getInt("length");
				double repCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String features = rs.getString("special_features");
				List<Actor> actors = findActorsByFilmId(filmId);
				String genre = findFilmGenreByFilmId(filmId);
				int copies = findNumFilmCopiesInInventory(filmId);
				Film film = new Film(filmId, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating,
						features, actors, genre, copies);
				films.add(film);

			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT *" + " FROM film "
					+ " JOIN language ON language.id = film.language_id WHERE film.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String title = rs.getString("title");
				String desc = rs.getString("description");
				short releaseYear = rs.getShort("release_year");
				int langId = rs.getInt("language_id");
				int rentDur = rs.getInt("rental_duration");
				double rate = rs.getDouble("rental_rate");
				int length = rs.getInt("length");
				double repCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String features = rs.getString("special_features");
				List<Actor> actors = findActorsByFilmId(filmId);
				String genre = findFilmGenreByFilmId(filmId);
				int copies = findNumFilmCopiesInInventory(filmId);
				String language = findFilmLanguage(filmId);
				film = new Film(filmId, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating,
						features, actors, genre, copies);
				film.setLanguage(language);
			} else {
				System.out.println("No such film found");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	public List<Film> findFilmByKeyword(String keyword) {
		List<Film> films = new ArrayList<>();
		Film film = null;
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT *" + " FROM film " + " WHERE title like ? or description like ? ";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int filmId = rs.getInt("id");
				String title = rs.getString("title");
				String desc = rs.getString("description");
				short releaseYear = rs.getShort("release_year");
				int langId = rs.getInt("language_id");
				int rentDur = rs.getInt("rental_duration");
				double rate = rs.getDouble("rental_rate");
				int length = rs.getInt("length");
				double repCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String features = rs.getString("special_features");
				List<Actor> actors = findActorsByFilmId(filmId);
				String genre = findFilmGenreByFilmId(filmId);
				int copies = findNumFilmCopiesInInventory(filmId);
				film = new Film(filmId, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating,
						features, actors, genre, copies);
				film.setLanguage(findFilmLanguage(filmId));
				films.add(film);
			}
			if (films.isEmpty()) {
				System.out.println("No films found matching that criteria");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	private int findNumFilmCopiesInInventory(int filmId) {
		int copies = 0;
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT COUNT(inventory_item.film_id) " + "FROM inventory_item JOIN film "
					+ "ON film.id = inventory_item.film_id " + "WHERE film.id = ? " + "GROUP BY inventory_item.film_id";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, filmId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				copies = rs.getInt("COUNT(inventory_item.film_id)");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return copies;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		Actor actor = null;
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT actor.id, actor.first_name, actor.last_name FROM actor join film_actor on actor.id = film_actor.actor_id WHERE film_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet actorResult = stmt.executeQuery();
			while (actorResult.next()) {
				actor = new Actor();
				actor.setId(actorResult.getInt("id"));
				actor.setFirstName(actorResult.getString("first_name"));
				actor.setLastName(actorResult.getString("last_name"));
				actors.add(actor);
			}
			if (actors.isEmpty()) {
				System.out.println("No such actor found");
			}
			stmt.close();
			conn.close();
			actorResult.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;
	}

	public String findFilmGenreByFilmId(int filmId) {
		String genre = "unknown";
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT category.name " + "FROM category JOIN film_category "
					+ "ON category.id = film_category.category_id " + "JOIN film ON film_category.film_id = film.id "
					+ "WHERE film.id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, filmId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				genre = rs.getString("name");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return genre;
	}

	private String findFilmLanguage(int filmId) {
		String language = "";
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT *" + " FROM film "
					+ " JOIN language ON language.id = film.language_id WHERE film.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				language = rs.getString("name");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return language;
	}

}
