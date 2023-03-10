import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieCollection
{
    private ArrayList<Movie> movies;
    private Scanner scanner;

    public MovieCollection(String fileName)
    {
        importMovieList(fileName);
        scanner = new Scanner(System.in);
    }

    public ArrayList<Movie> getMovies()
    {
        return movies;
    }

    public void menu()
    {
        String menuOption = "";

        System.out.println("Welcome to the movie collection!");
        System.out.println("Total: " + movies.size() + " movies");

        while (!menuOption.equals("q"))
        {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (k)eywords");
            System.out.println("- search (c)ast");
            System.out.println("- see all movies of a (g)enre");
            System.out.println("- list top 50 (r)ated movies");
            System.out.println("- list top 50 (h)igest revenue movies");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scanner.nextLine();

            if (!menuOption.equals("q"))
            {
                processOption(menuOption);
            }
        }
    }

    private void processOption(String option)
    {
        if (option.equals("t"))
        {
            searchTitles();
        }
        else if (option.equals("c"))
        {
            searchCast();
        }
        else if (option.equals("k"))
        {
            searchKeywords();
        }
        else if (option.equals("g"))
        {
            listGenres();
        }
        else if (option.equals("r"))
        {
            listHighestRated();
        }
        else if (option.equals("h"))
        {
            listHighestRevenue();
        }
        else
        {
            System.out.println("Invalid choice!");
        }
    }

    private void searchTitles()
    {
        System.out.print("Enter a tital search term: ");
        String searchTerm = scanner.nextLine();

        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();

        // arraylist to hold search results
        ArrayList<Movie> results = new ArrayList<Movie>();

        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
            String movieTitle = movies.get(i).getTitle();
            movieTitle = movieTitle.toLowerCase();

            if (movieTitle.indexOf(searchTerm) != -1)
            {
                //add the Movie objest to the results list
                results.add(movies.get(i));
            }
        }

        // sort the results by title
        sortResults(results);

        // now, display them all to the user
        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void sortResults(ArrayList<Movie> listToSort)
    {
        for (int j = 1; j < listToSort.size(); j++)
        {
            Movie temp = listToSort.get(j);
            String tempTitle = temp.getTitle();

            int possibleIndex = j;
            while (possibleIndex > 0 && tempTitle.compareTo(listToSort.get(possibleIndex - 1).getTitle()) < 0)
            {
                listToSort.set(possibleIndex, listToSort.get(possibleIndex - 1));
                possibleIndex--;
            }
            listToSort.set(possibleIndex, temp);
        }
    }

    private void displayMovieInfo(Movie movie)
    {
        System.out.println();
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Tagline: " + movie.getTagline());
        System.out.println("Runtime: " + movie.getRuntime() + " minutes");
        System.out.println("Year: " + movie.getYear());
        System.out.println("Directed by: " + movie.getDirector());
        System.out.println("Cast: " + movie.getCast());
        System.out.println("Overview: " + movie.getOverview());
        System.out.println("User rating: " + movie.getUserRating());
        System.out.println("Box office revenue: " + movie.getRevenue());
    }

    private void searchCast() {
        ArrayList<String> cast = new ArrayList<>();
        ArrayList<String> actors = new ArrayList<String>();
        for (int j = 0; j < movies.size(); j++){
            String str = movies.get(j).getCast();
            while(str.indexOf("|") > 0){
                if(!cast.contains(str.substring(0,str.indexOf("|")))) {
                    cast.add(str.substring(0, str.indexOf("|")));

                }
                str = str.substring(str.indexOf("|"));
            }

        }



        int count = 0;
        for (int i = 0; i < cast.size()-1; i++){
            int minIndex = i;
            for (int k = i; k < cast.size(); k++){
                if (cast.get(k).compareTo(cast.get(minIndex)) < 0){
                    minIndex = k;
                    count++;
                }
            }
            String temp = cast.get(i);
            cast.set(i,cast.get(minIndex));
            cast.set(minIndex, temp);
        }
        for (int i = 0; i < cast.size(); i++)
        {

            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + cast.get(i));
        }
        int num = scanner.nextInt();
        String person = cast.get(num);

        ArrayList<Movie> results = new ArrayList<Movie>();

        for (int i = 0; i < movies.size(); i++)
        {
            String name = movies.get(i).getCast();


            if (name.indexOf(person) != -1)
            {
                //add the Movie objest to the results list
                results.add(movies.get(i));
            }
        }
        sortResults(results);

        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i).getTitle();


            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }



    private void searchKeywords()
    {
        System.out.println("Enter a keyword to search");
        String keyword = scanner.nextLine();
        keyword.toLowerCase();
        ArrayList<Movie> results = new ArrayList<Movie>();


        for (int i = 0; i < movies.size(); i++)
        {
            String movieTitle = movies.get(i).getKeywords();
            movieTitle = movieTitle.toLowerCase();

            if (movieTitle.indexOf(keyword) != -1)
            {
                //add the Movie objest to the results list
                results.add(movies.get(i));
            }
        }
        sortResults(results);


        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i).getTitle();


            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }
        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void listGenres() {
        ArrayList<String> movieGenres = new ArrayList<>();
        movieGenres.add(movies.get(0).getGenres());
        for (int i = 1; i < movies.size(); i++) {
            String str = movies.get(i).getGenres();
            while(str.indexOf("|") > 0){
                if(!movieGenres.contains(str.substring(0,str.indexOf("|")))) {
                    movieGenres.add(str.substring(0, str.indexOf("|")));

                }
                str = str.substring(str.indexOf("|"));
            }
        }
        int count = 0;
        for (int i = 0; i < movieGenres.size() - 1; i++) {
            int minIndex = i;

                if (movieGenres.get(i).compareTo(movieGenres.get(minIndex)) < 0) {
                    minIndex = i;
                    count++;
                }

                String temp = movieGenres.get(i);
                movieGenres.set(i, movieGenres.get(minIndex));
                movieGenres.set(minIndex, temp);
            }
            for (int k = 0; k < movieGenres.size(); k++) {

                int choiceNum = k + 1;

                System.out.println("" + choiceNum + ". " + movieGenres.get(k));
            }
            int num = scanner.nextInt();
            String genre = movieGenres.get(num);
            ArrayList<Movie> movie = new ArrayList<>();
            for(int i = 0; i < movies.size(); i++){
                if(!movies.get(i).getGenres().contains(genre)){
                    movie.add(movies.get(i));
                }
            }
        sortResults(movie);

        for (int i = 0; i < movie.size(); i++)
        {
            String title = movie.get(i).getTitle();


            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = movies.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
        }


    private void listHighestRated()
    {

        int count = 0;
        for (int i = 0; i < movies.size() - 1; i++) {
            int minIndex = i;

            if (movies.get(i).getUserRating() > movies.get(minIndex).getUserRating() ) {
                minIndex = i;
                count++;
            }

            Movie temp = movies.get(i);
            movies.set(i, movies.get(minIndex));
            movies.set(minIndex, temp);
        }


        for (int i = 0; i < 50; i++)
        {
            String title = movies.get(i).getTitle();


            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }
        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = movies.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();

    }

    private void listHighestRevenue()
    {
        int count = 0;
        for (int i = 0; i < movies.size() - 1; i++) {
            int minIndex = i;

            if (movies.get(i).getRevenue() > movies.get(minIndex).getRevenue() ) {
                minIndex = i;
                count++;
            }

            Movie temp = movies.get(i);
            movies.set(i, movies.get(minIndex));
            movies.set(minIndex, temp);
        }
        for (int i = 0; i < 50; i++)
        {
            String title = movies.get(i).getTitle();


            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }
        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = movies.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();


    }

    private void importMovieList(String fileName)
    {
        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            movies = new ArrayList<Movie>();

            while ((line = bufferedReader.readLine()) != null)
            {
                String[] movieFromCSV = line.split(",");

                String title = movieFromCSV[0];
                String cast = movieFromCSV[1];
                String director = movieFromCSV[2];
                String tagline = movieFromCSV[3];
                String keywords = movieFromCSV[4];
                String overview = movieFromCSV[5];
                int runtime = Integer.parseInt(movieFromCSV[6]);
                String genres = movieFromCSV[7];
                double userRating = Double.parseDouble(movieFromCSV[8]);
                int year = Integer.parseInt(movieFromCSV[9]);
                int revenue = Integer.parseInt(movieFromCSV[10]);

                Movie nextMovie = new Movie(title, cast, director, tagline, keywords, overview, runtime, genres, userRating, year, revenue);
                movies.add(nextMovie);
            }
            bufferedReader.close();
        }
        catch(IOException exception)
        {
            // Print out the exception that occurred
            System.out.println("Unable to access " + exception.getMessage());
        }
    }
}