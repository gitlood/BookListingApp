package com.example.booklisting;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {
    public static final String LOG_TAG = MainActivity.class.getName();

    private static final int BOOK_LOADER_ID = 1;

    private static final String BASE_URL = "https://www.googleapis.com/books/v1/volumes?q=";

    public String Url;

    private BookAdapter bookAdapters;
    private TextView emptyView;
    private ProgressBar progressbar;
    private ListView bookListView;
    LoaderManager loaderManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bookListView = findViewById(R.id.list);
        emptyView = findViewById(R.id.empty_view);
        progressbar = findViewById(R.id.progressbar);

        bookListView.setEmptyView(emptyView);

        bookAdapters = new BookAdapter(this, new ArrayList<>());

        // Get a reference to the LoaderManager, in order to interact with loaders.
        loaderManager = getLoaderManager();

        // If there is a network connection, fetch data
        if (checkOnlineStatus()) {
            Log.d(TAG, "onQueryTextSubmit: internet is available");
            emptyView.setVisibility(View.INVISIBLE);
            progressbar.setVisibility(View.VISIBLE);
            Url = BASE_URL + "android";
            Log.d(TAG, "onQueryTextSubmit: " + Url);

            bookAdapters.clear();

            loaderManager.initLoader(1, null, MainActivity.this);

            Url = "";

            //set The title of the activity to the search query
            MainActivity.this.setTitle("Android Books");
        } else {
            emptyView.setText(R.string.no_internet_connection);
            bookAdapters.clear();
            emptyView.setVisibility(View.VISIBLE);
            progressbar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new BookLoader(this, Url);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
        progressbar.setVisibility(View.GONE);
        // Set empty state text to display "No books found."
        emptyView.setText(R.string.no_books);

        bookListView.setAdapter(bookAdapters);

        // Clear the adapter of previous book data
        bookAdapters.clear();

        if (books != null && !books.isEmpty()) {
            // If there is a valid list of {@link Book}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            bookAdapters.addAll(books);
            bookAdapters.notifyDataSetChanged();
        }
        loaderManager.destroyLoader(1);
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        bookAdapters.clear();
    }

    /**
     * Loads a list of books by using an AsyncTaskLoader to perform the
     * network request to the given URL.
     */
    public static class BookLoader extends AsyncTaskLoader<List<Book>> {

        /**
         * Tag for log messages
         */
        private final String LOG_TAG = BookLoader.class.getName();

        /**
         * Query URL
         */
        private final String mUrl;

        /**
         * Constructs a new {@link BookLoader}.
         *
         * @param context of the activity
         * @param url     to load data from
         */
        public BookLoader(Context context, String url) {
            super(context);
            mUrl = url;
        }

        @Override
        protected void onStartLoading() {
            forceLoad();
        }

        /**
         * This is on a background thread.
         */
        @Override
        public List<Book> loadInBackground() {
            if (mUrl == null) {
                return null;
            }

            // Perform the network request, parse the response, and extract a list of books.
            return Utils.fetchBookData(mUrl);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Log.d(TAG, "onQueryTextSubmit: called");

                // If there is a network connection, fetch data
                if (checkOnlineStatus()) {
                    Log.d(TAG, "onQueryTextSubmit: internet is available");
                    emptyView.setVisibility(View.INVISIBLE);
                    progressbar.setVisibility(View.VISIBLE);
                    Url = BASE_URL + query;
                    Log.d(TAG, "onQueryTextSubmit: " + Url);

                    bookAdapters.clear();

                    loaderManager.initLoader(1, null, MainActivity.this);

                    //reset the search view
                    searchView.setQuery("", false);
                    searchView.setIconified(true);
                    searchItem.collapseActionView();

                    Url = "";

                    //set The title of the activity to the search query
                    MainActivity.this.setTitle(query);
                } else {
                    emptyView.setText(R.string.no_internet_connection);
                    bookAdapters.clear();
                    emptyView.setVisibility(View.VISIBLE);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    public boolean checkOnlineStatus() {
        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}