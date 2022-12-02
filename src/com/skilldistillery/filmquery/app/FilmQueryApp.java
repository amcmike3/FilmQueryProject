package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
//    app.test();
		app.launch();
	}

	private void test() {
		Film film = db.findFilmById(1);
		System.out.println(film);
	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
		String choice = "";
		DatabaseAccessorObject dao = new DatabaseAccessorObject();
		while (!(choice.equalsIgnoreCase("quit"))) {
//		  TODO fix this non working while loop garbage
			menu();
			choice = input.nextLine();
			switch (choice) {
			case "1":
				System.out.println("What is the ID of the Actor you'd like to look up?");
				choice = input.nextLine();
				Actor actor = null;
				try {
					actor = dao.findActorById(Integer.parseInt(choice));
				} catch (NumberFormatException e) {
					System.err.println("Invalid ID");
				}
				if (!(actor == null)) {
					System.out.println(actor);
				}
				break;
			case "2":
				System.out.println("What is the ID of the Film you'd like to look up?");
				choice = input.nextLine();
				String filmId = choice;
				Film film = null;
				try {
					film = dao.findFilmById(Integer.parseInt(choice));
				} catch (NumberFormatException e) {
					System.err.println("Invalid ID");
				}
				if(!(film == null)) {
					System.out.println(film.getTitle() + " - " + film.getDesc());
					System.out.println("Enter D for more details:");
					choice = input.nextLine();
					if (choice.equalsIgnoreCase("D")) {
						System.out.println(film);

					} else {
						System.out.println("Thank you! please come again!");
					}
				}
				
				break;
			case "3":
				System.out.println("What is the ID of the Actor?");
				choice = input.nextLine();
				List<Film> films = null;
				try {
					films = dao.findFilmsByActorId(Integer.parseInt(choice));
				} catch (NumberFormatException e) {
					System.err.println("Invalid ID");
				}
				if (!(films == null || films.isEmpty())) {
					printFilms(films);
				} else {
					System.out.println("None found");
				}

				break;
			case "4":
				System.out.println("What is the ID of the Film?");
				choice = input.nextLine();
				List<Actor> actors = null;
				try {
					actors = dao.findActorsByFilmId(Integer.parseInt(choice));
				} catch (NumberFormatException e) {
					System.err.println("Invalid ID");
				} 
				if (!(actors == null || actors.isEmpty())) {
					for (Actor a : actors) {
						System.out.println(a);
					}					
				} else {
					System.out.println("None found");
				}
				break;
			case "5":
				System.out.println("What is the keyword in the films title or description?");
				choice = input.nextLine();
				List<Film> filmsByKeyword = dao.findFilmByKeyword(choice);
				if (!( filmsByKeyword == null || filmsByKeyword.isEmpty())) {
					printFilms(filmsByKeyword);
				} else {
					System.out.println("None found");
				}
				break;
			default:
				System.out.println("Sorry that was not a valid option...");
				break;
			}

			System.out.println("Would you like to continue? \nquit to exit: ");
			choice = input.nextLine();

		}

	}

	private void printFilms(List<Film> films) {
		for (Film f : films) {
			System.out.println(f);
		}
	}

	private void menu() {
		System.out.println("Please make a choice by number:" + " \n1. look up an Actor by their ID."
				+ "\n2. look up a Film by ID." + "\n3. look up all the films an actor has been in by actor ID."
				+ "\n4. look up all the actors in a film by film ID." + "\n5. look up a film by a keyword");

	}

	/*
	 * method to check if films by actor id !foo.isempty();
	 */

}
