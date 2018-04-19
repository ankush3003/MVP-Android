package com.tutorial.mvp.masterdetail.fragments;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tutorial.mvp.masterdetail.R;
import com.tutorial.mvp.masterdetail.adapters.MainCategoryAdapter;
import com.tutorial.mvp.masterdetail.constants.AppConstants;
import com.tutorial.mvp.masterdetail.interfaces.RecyclerViewClickListener;
import com.tutorial.mvp.masterdetail.models.CardData;
import com.tutorial.mvp.masterdetail.parsers.MainParser;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ankush3003 on 1/6/2018.
 */

public class CardListFragment extends Fragment implements RecyclerViewClickListener{
    private RecyclerView recyclerView;
    private MainCategoryAdapter adapter;
    private List<CardData> cardDataList;
    private int fragmentType = AppConstants.FRAGMENT_TYPE_MASTER_LIST;
    public static final int writerPermissionRequestCode = 901;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cardlist, container, false);

        // get arguments
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            fragmentType = bundle.getInt(AppConstants.FRAGMENT_TYPE_KEY, AppConstants.FRAGMENT_TYPE_MASTER_LIST);
        }

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        if(fragmentType == AppConstants.FRAGMENT_TYPE_MASTER_LIST) {
            cardDataList = new ArrayList<CardData>(MainParser.masterDataset.keySet());
        } else {
            CardData parentObj = (CardData) bundle.getParcelable(AppConstants.SELECTED_MASTER_LIST_OBJECT_KEY);
            cardDataList = MainParser.masterDataset.get(parentObj);
        }
        adapter = new MainCategoryAdapter(getActivity(), this, cardDataList, fragmentType);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        return view;
    }

    // Card Click Listener
    @Override
    public void onViewClicked(View view) {
        CardData cardData = (CardData) view.getTag();
        if (cardData.getHyperlink().contains("http")) {
            Uri uri = Uri.parse(cardData.getHyperlink()); // missing 'http://' will cause crashed
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        } else {
            if (Build.VERSION.SDK_INT >= 23) {
                //do your check here
                if (getActivity().checkSelfPermission
                        (android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    // pdf from assets
                    OpenFileFromAssets(cardData.getHyperlink());
                } else {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, writerPermissionRequestCode);
                }
            } else {
                OpenFileFromAssets(cardData.getHyperlink());
            }
        }
    }

    private void OpenFileFromAssets(String filename)
    {
        AssetManager assetManager = getActivity().getAssets();

        InputStream in = null;
        OutputStream out = null;
        String state = Environment.getExternalStorageState();
        if (!Environment.MEDIA_MOUNTED.equals(state)) {
            Toast.makeText(getActivity(), "External Storage is not Available", Toast.LENGTH_SHORT).show();
        }
        File file = new File(Environment.getExternalStorageDirectory() + "/" + filename);

        try
        {
            in = assetManager.open(filename);
            out = new BufferedOutputStream(new FileOutputStream(file));
            copyFile(in, out);
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
        } catch (Exception e)
        {
            Log.e("tag", e.getMessage());
            Toast.makeText(getActivity(), "Exception: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        if (file.exists()) //Checking for the file is exist or not
        {
            Uri path = FileProvider.getUriForFile(getActivity(), getActivity().getApplicationContext().getPackageName() + ".com.masterdetail.gulati.masterdetail.provider", file);//Uri.fromFile(file);
            Intent objIntent = new Intent(Intent.ACTION_VIEW);
            objIntent.setDataAndType(path, "application/pdf");
            objIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            objIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Intent intent1 = Intent.createChooser(objIntent, "Open PDF with..");
            intent1.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            try {
                startActivity(intent1);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(getActivity(), "Activity Not Found Exception ", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity(), "The file not exists! ", Toast.LENGTH_SHORT).show();
        }
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException
    {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1)
        {
            out.write(buffer, 0, read);
        }
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
