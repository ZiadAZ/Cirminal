package com.example.cirminal;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class PhotoFragmet extends DialogFragment {
ImageView imageView;
    Bitmap bitmap;

    private File mPhotoFile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        View v=LayoutInflater.from(getActivity()).inflate(R.layout.photo_fragment,null);

        imageView=v.findViewById(R.id.zoom_photo);
        bitmap = (Bitmap) getArguments().getParcelable("Photozz");
        imageView.setImageBitmap(bitmap);
        Toast.makeText(getActivity(),bitmap.toString(),Toast.LENGTH_LONG).show();



        return  new AlertDialog.Builder(getActivity()).setView(v).create();

    }
}
