package com.example.booklisting;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * An {@link BookAdapter} knows how to create a list item layout for each book
 * in the data source (a list of {@link Book} objects).
 * <p>
 * These list item layouts will be provided to an adapter view like ListView
 * to be displayed to the user.
 */
public class BookAdapter extends ArrayAdapter<Book> {

    /**
     * Constructs a new {@link BookAdapter}.
     *
     * @param context of the app
     * @param books   is the list of books, which is the data source of the adapter
     */
    public BookAdapter(Context context, List<Book> books) {
        super(context, 0, books);
    }

    /**
     * Returns a list item view that displays information about the book at the given position
     * in the list of books.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.book_list_item, parent, false);
        }

        // Find the book at the given position in the list of books
        Book currentBook = getItem(position);

        LinearLayout container = listItemView.findViewById(R.id.container);

        ImageView thumbnail = listItemView.findViewById(R.id.thumbnail);
        TextView title = listItemView.findViewById(R.id.title);
        TextView author = listItemView.findViewById(R.id.author);
        TextView publisher = listItemView.findViewById(R.id.publisher);
        TextView publisherDate = listItemView.findViewById(R.id.publisherdate);
        TextView link = listItemView.findViewById(R.id.link);
        TextView description = listItemView.findViewById(R.id.description);

        description.setVisibility(View.GONE);

        container.setOnClickListener(view -> {
            if (description.getVisibility() == View.GONE) {
                description.setVisibility(View.VISIBLE);
            } else {
                description.setVisibility(View.GONE);
            }
        });

        //converts picture into imageview with picasso library
        Picasso.get().load(currentBook.getThumbnailLink()).into(thumbnail);

        title.setText(currentBook.getTitle());
        author.setText(currentBook.formatAuthorsToString());
        publisher.setText(currentBook.getPublisher());

        String publisherDateString = "(" + currentBook.getPublisherDate() + ")";
        publisherDate.setText(publisherDateString);

        //clickable textView to go to website about book
        link.setText(Html.fromHtml("<a href=currentBook.getLinkToPreview()>View Webpage</a>"));
        link.setMovementMethod(LinkMovementMethod.getInstance());
        link.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW);
            browserIntent.setData(Uri.parse(currentBook.getLinkToPreview()));
            getContext().startActivity(browserIntent);
        });

        description.setText(currentBook.getDescription());

        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }
}