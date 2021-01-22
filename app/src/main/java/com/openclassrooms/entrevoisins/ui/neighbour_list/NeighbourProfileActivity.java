package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourApiService;
import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.List;

public class NeighbourProfileActivity extends AppCompatActivity {

    private NeighbourApiService mApiService;
    private ImageView mItemProfileAvatar;
    private TextView mUsername;
    private ImageButton mReturnButton;
    private FloatingActionButton mFavoriteButton;
    private CardView mInformation;
    private TextView mUserInfo;
    private ImageButton mLocationButton;
    private TextView mLocation;
    private ImageButton mPhoneButton;
    private TextView mPhoneNumber;
    private ImageButton mFacebookButton;
    private TextView mFacebookLink;
    private CardView mAboutMe;
    private TextView mAboutMeTitle;
    private TextView mAboutMeText;
    private List<Neighbour> favoriteneighbours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Neighbour neighbour = (Neighbour) intent.getSerializableExtra("neighbour");
        setContentView(R.layout.activity_neighbour_profile);
        mApiService = DI.getNeighbourApiService();
        mItemProfileAvatar = (ImageView) findViewById(R.id.item_profile_avatar);
        Glide.with(mItemProfileAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.overrideOf(1000,1000))
                .into(mItemProfileAvatar);
        mUsername = (TextView) findViewById(R.id.username);
        mUsername.setText(neighbour.getName());
        mReturnButton = (ImageButton) findViewById(R.id.return_button);
        mFavoriteButton = (FloatingActionButton) findViewById(R.id.favorite_button);
        mInformation = (CardView) findViewById(R.id.Information);
        mUserInfo = (TextView) findViewById(R.id.user_info);
        mUserInfo.setText(neighbour.getName());
        mLocationButton = (ImageButton) findViewById(R.id.location_button);
        mLocation = (TextView) findViewById(R.id.location);
        mLocation.setText(neighbour.getAddress());
        mPhoneButton = (ImageButton) findViewById(R.id.phone_button);
        mPhoneNumber = (TextView) findViewById(R.id.phone_number);
        mPhoneNumber.setText(neighbour.getPhoneNumber());
        mFacebookButton = (ImageButton) findViewById(R.id.facebook_button);
        mFacebookLink = (TextView) findViewById(R.id.facebook_link);
        mFacebookLink.setText("www.facebook.fr/" + neighbour.getName());
        mAboutMe = (CardView) findViewById(R.id.aboutMe);
        mAboutMeTitle = (TextView) findViewById(R.id.aboutMe_title);
        mAboutMeText = (TextView) findViewById(R.id.aboutMe_text);
        mAboutMeText.setText(neighbour.getAboutMe());
        favoriteneighbours = mApiService.getFavoritesNeighbours();
        mReturnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(favoriteneighbours.contains(neighbour)){
                    mApiService.deleteFromFavorite(neighbour);
                    Toast.makeText(NeighbourProfileActivity.this, "This Neighbour has been delete from favorites with success", Toast.LENGTH_SHORT).show();
                }
                else{
                    mApiService.addToFavorite(neighbour);
                    Toast.makeText(NeighbourProfileActivity.this, "This Neighbour has been add to favorite with success", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}