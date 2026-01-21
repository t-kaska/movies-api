package com.example.moviesapi;

import com.example.moviesapi.entity.Actor;
import com.example.moviesapi.entity.Genre;
import com.example.moviesapi.entity.Movie;
import com.example.moviesapi.repository.ActorRepository;
import com.example.moviesapi.repository.GenreRepository;
import com.example.moviesapi.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final GenreRepository genreRepository;
    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;

    public DataLoader(GenreRepository genreRepository, MovieRepository movieRepository,
            ActorRepository actorRepository) {
        this.genreRepository = genreRepository;
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
    }

    @Override
    public void run(String... args) {
        // creating genres
        Genre action = new Genre();
        action.setName("Action");
        genreRepository.save(action);

        Genre comedy = new Genre();
        comedy.setName("Comedy");
        genreRepository.save(comedy);

        Genre sciFi = new Genre();
        sciFi.setName("Sci-Fi");
        genreRepository.save(sciFi);

        Genre drama = new Genre();
        drama.setName("Drama");
        genreRepository.save(drama);

        Genre thriller = new Genre();
        thriller.setName("Thriller");
        genreRepository.save(thriller);

        // actors
        Actor keanu = new Actor();
        keanu.setName("Keanu Reeves");
        keanu.setBirthDate(LocalDate.of(1964, 9, 2));
        actorRepository.save(keanu);

        Actor laurence = new Actor();
        laurence.setName("Laurence Fishburne");
        laurence.setBirthDate(LocalDate.of(1961, 7, 30));
        actorRepository.save(laurence);

        Actor carrie = new Actor();
        carrie.setName("Carrie-Anne Moss");
        carrie.setBirthDate(LocalDate.of(1967, 8, 21));
        actorRepository.save(carrie);

        Actor leo = new Actor();
        leo.setName("Leonardo DiCaprio");
        leo.setBirthDate(LocalDate.of(1974, 11, 11));
        actorRepository.save(leo);

        Actor tom = new Actor();
        tom.setName("Tom Hanks");
        tom.setBirthDate(LocalDate.of(1956, 7, 9));
        actorRepository.save(tom);

        Actor morgan = new Actor();
        morgan.setName("Morgan Freeman");
        morgan.setBirthDate(LocalDate.of(1937, 6, 1));
        actorRepository.save(morgan);

        Actor brad = new Actor();
        brad.setName("Brad Pitt");
        brad.setBirthDate(LocalDate.of(1963, 12, 18));
        actorRepository.save(brad);

        Actor natalie = new Actor();
        natalie.setName("Natalie Portman");
        natalie.setBirthDate(LocalDate.of(1981, 6, 9));
        actorRepository.save(natalie);

        Actor scarlett = new Actor();
        scarlett.setName("Scarlett Johansson");
        scarlett.setBirthDate(LocalDate.of(1984, 11, 22));
        actorRepository.save(scarlett);

        Actor robert = new Actor();
        robert.setName("Robert Downey Jr.");
        robert.setBirthDate(LocalDate.of(1965, 4, 4));
        actorRepository.save(robert);

        Actor chris = new Actor();
        chris.setName("Chris Evans");
        chris.setBirthDate(LocalDate.of(1981, 6, 13));
        actorRepository.save(chris);

        Actor emma = new Actor();
        emma.setName("Emma Stone");
        emma.setBirthDate(LocalDate.of(1988, 11, 6));
        actorRepository.save(emma);

        Actor ryan = new Actor();
        ryan.setName("Ryan Gosling");
        ryan.setBirthDate(LocalDate.of(1980, 11, 12));
        actorRepository.save(ryan);

        Actor meryl = new Actor();
        meryl.setName("Meryl Streep");
        meryl.setBirthDate(LocalDate.of(1949, 6, 22));
        actorRepository.save(meryl);

        Actor denzel = new Actor();
        denzel.setName("Denzel Washington");
        denzel.setBirthDate(LocalDate.of(1954, 12, 28));
        actorRepository.save(denzel);

        // movies
        Movie matrix = new Movie();
        matrix.setTitle("Matrix");
        matrix.setReleaseYear(1999);
        matrix.setDuration(136);
        matrix.getGenres().add(action);
        matrix.getGenres().add(sciFi);
        matrix.getActors().add(keanu);
        matrix.getActors().add(laurence);
        matrix.getActors().add(carrie);
        movieRepository.save(matrix);

        Movie inception = new Movie();
        inception.setTitle("Inception");
        inception.setReleaseYear(2010);
        inception.setDuration(148);
        inception.getGenres().add(action);
        inception.getGenres().add(sciFi);
        inception.getGenres().add(thriller);
        inception.getActors().add(leo);
        movieRepository.save(inception);

        Movie forrestGump = new Movie();
        forrestGump.setTitle("Forrest Gump");
        forrestGump.setReleaseYear(1994);
        forrestGump.setDuration(142);
        forrestGump.getGenres().add(drama);
        forrestGump.getGenres().add(comedy);
        forrestGump.getActors().add(tom);
        movieRepository.save(forrestGump);

        Movie shawshank = new Movie();
        shawshank.setTitle("The Shawshank Redemption");
        shawshank.setReleaseYear(1994);
        shawshank.setDuration(142);
        shawshank.getGenres().add(drama);
        shawshank.getActors().add(morgan);
        movieRepository.save(shawshank);

        Movie fightClub = new Movie();
        fightClub.setTitle("Fight Club");
        fightClub.setReleaseYear(1999);
        fightClub.setDuration(139);
        fightClub.getGenres().add(drama);
        fightClub.getGenres().add(thriller);
        fightClub.getActors().add(brad);
        movieRepository.save(fightClub);

        Movie blackSwan = new Movie();
        blackSwan.setTitle("Black Swan");
        blackSwan.setReleaseYear(2010);
        blackSwan.setDuration(108);
        blackSwan.getGenres().add(drama);
        blackSwan.getGenres().add(thriller);
        blackSwan.getActors().add(natalie);
        movieRepository.save(blackSwan);

        Movie lostInTranslation = new Movie();
        lostInTranslation.setTitle("Lost in Translation");
        lostInTranslation.setReleaseYear(2003);
        lostInTranslation.setDuration(102);
        lostInTranslation.getGenres().add(drama);
        lostInTranslation.getGenres().add(comedy);
        lostInTranslation.getActors().add(scarlett);
        movieRepository.save(lostInTranslation);

        Movie ironMan = new Movie();
        ironMan.setTitle("Iron Man");
        ironMan.setReleaseYear(2008);
        ironMan.setDuration(126);
        ironMan.getGenres().add(action);
        ironMan.getGenres().add(sciFi);
        ironMan.getActors().add(robert);
        ironMan.getActors().add(scarlett);
        movieRepository.save(ironMan);

        Movie avengers = new Movie();
        avengers.setTitle("The Avengers");
        avengers.setReleaseYear(2012);
        avengers.setDuration(143);
        avengers.getGenres().add(action);
        avengers.getGenres().add(sciFi);
        avengers.getActors().add(robert);
        avengers.getActors().add(chris);
        avengers.getActors().add(scarlett);
        movieRepository.save(avengers);

        Movie laLaLand = new Movie();
        laLaLand.setTitle("La La Land");
        laLaLand.setReleaseYear(2016);
        laLaLand.setDuration(128);
        laLaLand.getGenres().add(drama);
        laLaLand.getGenres().add(comedy);
        laLaLand.getActors().add(emma);
        laLaLand.getActors().add(ryan);
        movieRepository.save(laLaLand);

        Movie devilWearsPrada = new Movie();
        devilWearsPrada.setTitle("The Devil Wears Prada");
        devilWearsPrada.setReleaseYear(2006);
        devilWearsPrada.setDuration(109);
        devilWearsPrada.getGenres().add(comedy);
        devilWearsPrada.getGenres().add(drama);
        devilWearsPrada.getActors().add(meryl);
        movieRepository.save(devilWearsPrada);

        Movie trainingDay = new Movie();
        trainingDay.setTitle("Training Day");
        trainingDay.setReleaseYear(2001);
        trainingDay.setDuration(122);
        trainingDay.getGenres().add(action);
        trainingDay.getGenres().add(thriller);
        trainingDay.getGenres().add(drama);
        trainingDay.getActors().add(denzel);
        movieRepository.save(trainingDay);

        Movie departed = new Movie();
        departed.setTitle("The Departed");
        departed.setReleaseYear(2006);
        departed.setDuration(151);
        departed.getGenres().add(thriller);
        departed.getGenres().add(drama);
        departed.getActors().add(leo);
        movieRepository.save(departed);

        Movie castAway = new Movie();
        castAway.setTitle("Cast Away");
        castAway.setReleaseYear(2000);
        castAway.setDuration(143);
        castAway.getGenres().add(drama);
        castAway.getActors().add(tom);
        movieRepository.save(castAway);

        Movie se7en = new Movie();
        se7en.setTitle("Se7en");
        se7en.setReleaseYear(1995);
        se7en.setDuration(127);
        se7en.getGenres().add(thriller);
        se7en.getGenres().add(drama);
        se7en.getActors().add(brad);
        se7en.getActors().add(morgan);
        movieRepository.save(se7en);

        Movie johnWick = new Movie();
        johnWick.setTitle("John Wick");
        johnWick.setReleaseYear(2014);
        johnWick.setDuration(101);
        johnWick.getGenres().add(action);
        johnWick.getGenres().add(thriller);
        johnWick.getActors().add(keanu);
        movieRepository.save(johnWick);

        Movie captainAmerica = new Movie();
        captainAmerica.setTitle("Captain America: The Winter Soldier");
        captainAmerica.setReleaseYear(2014);
        captainAmerica.setDuration(136);
        captainAmerica.getGenres().add(action);
        captainAmerica.getGenres().add(sciFi);
        captainAmerica.getActors().add(chris);
        captainAmerica.getActors().add(scarlett);
        movieRepository.save(captainAmerica);

        Movie wolfOfWallStreet = new Movie();
        wolfOfWallStreet.setTitle("The Wolf of Wall Street");
        wolfOfWallStreet.setReleaseYear(2013);
        wolfOfWallStreet.setDuration(180);
        wolfOfWallStreet.getGenres().add(comedy);
        wolfOfWallStreet.getGenres().add(drama);
        wolfOfWallStreet.getActors().add(leo);
        movieRepository.save(wolfOfWallStreet);

        Movie crazyStupiLove = new Movie();
        crazyStupiLove.setTitle("Crazy, Stupid, Love");
        crazyStupiLove.setReleaseYear(2011);
        crazyStupiLove.setDuration(118);
        crazyStupiLove.getGenres().add(comedy);
        crazyStupiLove.getGenres().add(drama);
        crazyStupiLove.getActors().add(ryan);
        crazyStupiLove.getActors().add(emma);
        movieRepository.save(crazyStupiLove);

        Movie vForVendetta = new Movie();
        vForVendetta.setTitle("V for Vendetta");
        vForVendetta.setReleaseYear(2005);
        vForVendetta.setDuration(132);
        vForVendetta.getGenres().add(action);
        vForVendetta.getGenres().add(thriller);
        vForVendetta.getGenres().add(sciFi);
        vForVendetta.getActors().add(natalie);
        movieRepository.save(vForVendetta);
    }

}
