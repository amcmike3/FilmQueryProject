package com.skilldistillery.filmquery.database;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

class DatabaseAccessTests {
	private DatabaseAccessorObject db;

	@BeforeEach
	void setUp() throws Exception {
		db = new DatabaseAccessorObject();
	}

	@AfterEach
	void tearDown() throws Exception {
		db = null;
	}
	
	@Test
	void test_findFilmGenreByKeyword_returns_genre_when_given_valid_input () {
		String genre = db.findFilmGenreByFilmId(1);
		assertEquals(genre, "Documentary");
	}
	@Test
	void test_findFilmGenreByKeyword_returns_unknown_when_given_garbage_input () {
		String genre = db.findFilmGenreByFilmId(-1);
		assertEquals(genre, "unknown");
	}
	@Test
	void test_findFilmByKeyword_returns_empty_List_when_given_garbage_string () {
		List<Film> films = db.findFilmByKeyword("grdxcjkhbljnogic");
		assertEquals(films.size(), 0);
	}
	
	@Test
	void test_findFilmsByActorId_returns_list_when_given_valid_String () {
		List<Film> films = db.findFilmByKeyword("Pirate");
		
		assertEquals(films.size(), 2);
	}
	
	@Test
	void test_findFilmByActorId_returns_empty_List_when_given_negative_num () {
		List<Film> films = db.findFilmsByActorId(-1);
		assertEquals(films.size(), 0);
	}
	
	@Test
	void test_findFilmsByActorId_returns_list_when_given_valid_num () {
		List<Film> films = db.findFilmsByActorId(1);
		
		assertEquals(films.size(), 19);
	}
	
	@Test
	void test_findActorbyFilmId_returns_empty_List_when_given_negative_num () {
		List<Actor> actor = db.findActorsByFilmId(-1);
		assertEquals(actor.size(), 0);
	}
	
	@Test
	void test_findActorbyFilmId_returns_list_when_given_valid_num () {
		List<Actor> actor = db.findActorsByFilmId(1);
	
		assertEquals(actor.size(), 10);
	}

	@Test
	void test_getActorById_returns_null_when_given_negative_num() throws SQLException {
		int given = -1;
		Actor actual = db.findActorById(given);
		String expected = null;
		assertEquals(expected, actual);
		
	}
	@Test
	void test_getActorById_returns_actor_when_given_valid_num() throws SQLException {
		int given = 1;
		Actor actual = db.findActorById(given);
		assertNotNull(actual);
		
	}

	@Test
	void test_getFilmById_returns_null_when_given_negative_num() {
		int given = -1;
		Film actual = db.findFilmById(given);
		String expected = null;
		assertEquals(expected, actual);

	}

	@Test
	void test_getFilmById_returns_film_with_id() {
		Film f = db.findFilmById(1);
		assertNotNull(f);
		assertEquals("ACADEMY DINOSAUR", f.getTitle());
	}

	@Test
	void test_getFilmById_with_invalid_id_returns_null() {
		Film f = db.findFilmById(-42);
		assertNull(f);
	}

}
