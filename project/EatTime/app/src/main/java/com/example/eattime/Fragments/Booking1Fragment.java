package com.example.eattime.Fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.eattime.Adapter.MyRestaurantAdater;
import com.example.eattime.Common.Common;
import com.example.eattime.Common.SpacesItemDecoration;
import com.example.eattime.Interface.IAllRestaurantLoadListener;
import com.example.eattime.Interface.IBranchLoadListener;
import com.example.eattime.Model.Restaurant;
import com.example.eattime.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dmax.dialog.SpotsDialog;

public class Booking1Fragment extends Fragment implements IAllRestaurantLoadListener, IBranchLoadListener {

    // Khai bao bien (b2)
    CollectionReference allRestaurantRef;
    CollectionReference branchRef;

    IAllRestaurantLoadListener iAllRestaurantLoadListener;
    IBranchLoadListener iBranchLoadListener;

    @BindView(R.id.spinner)
    MaterialSpinner spinner;
    @BindView(R.id.recycler_restaurant)
    RecyclerView recycler_restaurant;

    Unbinder unbinder;
    AlertDialog dialog;

    static Booking1Fragment instance;
    public static Booking1Fragment getInstance() {
        if (instance == null)
            instance = new Booking1Fragment();
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allRestaurantRef = FirebaseFirestore.getInstance().collection("Restaurant");
        iAllRestaurantLoadListener = this;
        iBranchLoadListener = this;

        dialog = new SpotsDialog.Builder().setContext(getActivity()).build();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View itemView =  inflater.inflate(R.layout.fragment_booking1,container,false);
        unbinder = ButterKnife.bind(this, itemView);

        // khoi tao view cho cac chi nhanh
        initView();
        loadAllRestaurant();    
        return itemView;
    }

    private void initView() {
        recycler_restaurant.setHasFixedSize(true);
        recycler_restaurant.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recycler_restaurant.addItemDecoration(new SpacesItemDecoration(4));
    }

    private void loadAllRestaurant() {
        allRestaurantRef.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful())
                        {
                            List<String> list = new ArrayList<>();
                            list.add("Vui lòng chọn nhà hàng");
                            for (QueryDocumentSnapshot documentSnapshot:task.getResult())
                                list.add(documentSnapshot.getId());
                            iAllRestaurantLoadListener.onAllRestaurantLoadSuccess(list);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                iAllRestaurantLoadListener.onAllRestaurantLoadFailed(e.getMessage());
            }
        });
    }

    @Override
    public void onAllRestaurantLoadSuccess(List<String> areaNameList) {
        spinner.setItems(areaNameList);
        // set cho moi lua chon spinner se xua hien cac chi nhanh
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                if (position > 0)
                {
                    loadBranch(item.toString());
                }
                else
                {
                    recycler_restaurant.setVisibility(View.GONE);
                }
            }
        });
    }

    private void loadBranch(String cityName) {
        dialog.show();

        Common.city = cityName;

        branchRef = FirebaseFirestore.getInstance()
                .collection("Restaurant")
                .document(cityName)
                .collection("Branch");

        branchRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                List<Restaurant> list = new ArrayList<>();
                if (task.isSuccessful())
                {
                    for (QueryDocumentSnapshot documentSnapshot:task.getResult())
                    {
                        Restaurant restaurant = documentSnapshot.toObject(Restaurant.class);
                        restaurant.setResId(documentSnapshot.getId());
                        list.add(restaurant);
                    }
                    iBranchLoadListener.onAllBranchLoadSuccess(list);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                iBranchLoadListener.onAllBranchLoadFailed(e.getMessage());
            }
        });
    }

    @Override
    public void onAllRestaurantLoadFailed(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAllBranchLoadSuccess(List<Restaurant> restaurantList) {
        MyRestaurantAdater adater = new MyRestaurantAdater(getActivity(),restaurantList);
        recycler_restaurant.setAdapter(adater);
        recycler_restaurant.setVisibility(View.VISIBLE);
        dialog.dismiss();
    }

    @Override
    public void onAllBranchLoadFailed(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        dialog.dismiss();
    }
}
