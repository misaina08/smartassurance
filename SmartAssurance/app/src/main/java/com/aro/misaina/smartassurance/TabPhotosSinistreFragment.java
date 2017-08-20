package com.aro.misaina.smartassurance;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

import async.automoto.ListePhotosSinistreAsync;
import async.automoto.SavePhotoSinistreAsync;
import modeles.automoto.AmSinistreView;
import modeles.automoto.PhotosSinistreView;
import utilitaire.FileUtil;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabPhotosSinistreFragment extends Fragment implements View.OnClickListener {
    TabPhotosSinistreFragment fragment;
    private String dataJson;
    private AmSinistreView sinistreView;

//    private ImageView imageView;


    private Button gallery;

    //keep track of camera capture intent
    static final int CAMERA_CAPTURE = 1;
    //keep track of cropping intent
    final int PIC_CROP = 3;
    //keep track of gallery intent
    final int PICK_IMAGE_REQUEST = 2;
    //captured picture uri
    private Uri picUri;
    String mCurrentPhotoPath;

    public TabPhotosSinistreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragment = this;
        Bundle bundle = getArguments();
        dataJson = bundle.getString("dataJson");
        Gson gson = new Gson();
        sinistreView = gson.fromJson(dataJson, AmSinistreView.class);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_photos_sinistre, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);

        gallery = (Button) getView().findViewById(R.id.gallery);

        gallery.setOnClickListener(this);

        initData();
    }

    public void initData() {
        Integer[] params = new Integer[1];
        params[0] = sinistreView.getId();
        ListePhotosSinistreAsync async = new ListePhotosSinistreAsync();
        async.setFragment(this);
        async.execute(params);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case camera:
//                try {
//                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                    String imageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Camera/picture.jpg";
//                    File imageFile = new File(imageFilePath);
//                    picUri = Uri.fromFile(imageFile); // convert path to Uri
//                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, picUri);
//                    startActivityForResult(takePictureIntent, CAMERA_CAPTURE);
//                } catch (ActivityNotFoundException anfe) {
//                    //display an error message
//                    String errorMessage = "Whoops - your device doesn't support capturing images!";
//                    Toast.makeText(this.getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
//                }
//
//                break;

            case R.id.gallery:
                System.out.println("galleryyyyyyyyyyyyyyy");
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // Start the Intent
                startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
                break;

            default:
                break;
        }
    }

    private void performCrop() {
        try {
            //call the standard crop action intent (the user device may not support it)
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            //indicate image type and Uri
            cropIntent.setDataAndType(picUri, "image/*");
            //set crop properties
            cropIntent.putExtra("crop", "true");
            //indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            //indicate output X and Y
            cropIntent.putExtra("outputX", 256);
            cropIntent.putExtra("outputY", 256);
            //retrieve data on return
            cropIntent.putExtra("return-data", true);
            //start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, PIC_CROP);
        } catch (ActivityNotFoundException anfe) {
            //display an error message
            String errorMessage = "Whoops - your device doesn't support the crop action!";
            Toast toast = Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            //user is returning from capturing an image using the camera
            if (requestCode == CAMERA_CAPTURE) {
                //get the Uri for the captured image
                Uri uri = picUri;
                //carry out the crop operation
                performCrop();

            } else if (requestCode == PICK_IMAGE_REQUEST) {
                picUri = data.getData();
                performCrop();
            }

            //user is returning from cropping the image
            else if (requestCode == PIC_CROP) {
                //get the returned data
                Bundle extras = data.getExtras();
                //get the cropped bitmap
                Bitmap thePic = (Bitmap) extras.get("data");

                //display the returned cropped image
//                imageView.setImageBitmap(thePic);


                String root = Environment.getExternalStorageDirectory().toString();
                File myDir = new File(root + "/saved_images");
                myDir.mkdirs();
                Random generator = new Random();
                int n = 10000;
                n = generator.nextInt(n);
                String fname = "Image-"+sinistreView.getId()+"-"+ n +".jpg";
                File file = new File (myDir, fname);
                if (file.exists ()) file.delete ();
                try {
                    FileOutputStream out = new FileOutputStream(file);
                    thePic.compress(Bitmap.CompressFormat.JPEG, 90, out);
                    out.flush();
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }


                PhotosSinistreView par = new PhotosSinistreView();
                par.setIdsinistre(sinistreView.getId());
                FileUtil util = new FileUtil();

//                par.setPhoto("file://" + util.getRealPathFromURI(this.getActivity().getApplicationContext(), picUri));
                par.setPhoto("file:///storage/emulated/0/saved_images/" + fname);

                PhotosSinistreView[] params = new PhotosSinistreView[1];
                params[0] = par;
                SavePhotoSinistreAsync asyncSave = new SavePhotoSinistreAsync();
                asyncSave.setFragment(this);
                asyncSave.execute(params);
            }

        }
    }
}

